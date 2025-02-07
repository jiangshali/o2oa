package com.x.processplatform.service.processing.jaxrs.task;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;

import com.x.base.core.container.EntityManagerContainer;
import com.x.base.core.container.factory.EntityManagerContainerFactory;
import com.x.base.core.project.exception.ExceptionEntityNotExist;
import com.x.base.core.project.http.ActionResult;
import com.x.base.core.project.http.EffectivePerson;
import com.x.base.core.project.jaxrs.WoId;
import com.x.base.core.project.tools.ListTools;
import com.x.processplatform.core.entity.content.Task;
import com.x.processplatform.core.entity.content.Work;
import com.x.processplatform.core.entity.element.ActivityType;
import com.x.processplatform.core.entity.element.Manual;
import com.x.processplatform.core.entity.element.ManualMode;
import com.x.processplatform.service.processing.Business;
import com.x.processplatform.service.processing.ExecutorServiceFactory;
import com.x.processplatform.service.processing.MessageFactory;

class ActionGrab extends BaseAction {

	ActionResult<Wo> execute(EffectivePerson effectivePerson, String id) throws Exception {
		try (EntityManagerContainer emc = EntityManagerContainerFactory.instance().create()) {
			ActionResult<Wo> result = new ActionResult<>();
			Business business = new Business(emc);

			Task task = emc.find(id, Task.class);

			if (null == task) {
				throw new ExceptionEntityNotExist(id, Task.class);
			}

			Work work = emc.find(task.getWork(), Work.class);

			if (null == work) {
				throw new ExceptionEntityNotExist(task.getWork(), Work.class);
			}

			if (!Objects.equals(work.getActivityType(), ActivityType.manual)) {
				throw new ExceptionWorkNotAtManual(work.getId());
			}

			Callable<String> callable = new Callable<String>() {
				public String call() throws Exception {
					Manual manual = (Manual) business.element().get(work.getActivity(), ActivityType.manual);

					if (!Objects.equals(manual.getManualMode(), ManualMode.grab)) {
						throw new ExceptionWorkNotGrab(work.getId());
					}

					emc.beginTransaction(Task.class);
					emc.beginTransaction(Work.class);

					for (Task o : listTask(business, work)) {
						if (o != task) {
							emc.remove(o);
							MessageFactory.task_delete(o);
						}
					}

					work.setManualTaskIdentityList(ListTools.toList(task.getIdentity()));

					emc.commit();
					Wo wo = new Wo();
					wo.setId(task.getId());
					result.setData(wo);
					return "";
				}
			};

			ExecutorServiceFactory.get(task.getJob()).submit(callable).get();

			return result;
		}
	}

	private List<Task> listTask(Business business, Work work) throws Exception {
		return business.entityManagerContainer().listEqualAndEqual(Task.class, Task.activityToken_FIELDNAME,
				work.getActivityToken(), Task.work_FIELDNAME, work.getId());
	}

	public static class Wo extends WoId {
	}

}