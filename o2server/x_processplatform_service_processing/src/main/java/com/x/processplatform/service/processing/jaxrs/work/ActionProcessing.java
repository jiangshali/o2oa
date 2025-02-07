package com.x.processplatform.service.processing.jaxrs.work;

import java.util.concurrent.Callable;

import com.google.gson.JsonElement;
import com.x.base.core.container.EntityManagerContainer;
import com.x.base.core.container.factory.EntityManagerContainerFactory;
import com.x.base.core.project.http.ActionResult;
import com.x.base.core.project.http.EffectivePerson;
import com.x.base.core.project.jaxrs.WoId;
import com.x.processplatform.core.entity.content.Work;
import com.x.processplatform.service.processing.ExecutorServiceFactory;
import com.x.processplatform.service.processing.Processing;
import com.x.processplatform.service.processing.ProcessingAttributes;

class ActionProcessing extends BaseAction {

	ActionResult<Wo> execute(EffectivePerson effectivePerson, String id, JsonElement jsonElement) throws Exception {

		/** 校验work是否存在 */
		try (EntityManagerContainer emc = EntityManagerContainerFactory.instance().create()) {
			ActionResult<Wo> result = new ActionResult<>();
			Wi wi = this.convertToWrapIn(jsonElement, Wi.class);
			if (null == wi) {
				wi = new Wi();
			}
			wi.setDebugger(effectivePerson.getDebugger());
			Work work = emc.find(id, Work.class);
			if (null == work) {
				throw new ExceptionWorkNotExist(id);
			}

			Processing processing = new Processing(wi);

			Callable<String> callable = new Callable<String>() {
				public String call() throws Exception {
					processing.processing(id);
					return id;
				}
			};

			String value = ExecutorServiceFactory.get(work.getJob()).submit(callable).get();

			Wo wo = new Wo();
			wo.setId(value);
			result.setData(wo);
			return result;
		}
	}

	public static class Wi extends ProcessingAttributes {

	}

	public static class Wo extends WoId {
	}

}