package com.x.processplatform.assemble.surface.jaxrs.work;

import com.google.gson.JsonElement;
import com.x.base.core.container.EntityManagerContainer;
import com.x.base.core.container.factory.EntityManagerContainerFactory;
import com.x.base.core.entity.JpaObject;
import com.x.base.core.project.annotation.FieldDescribe;
import com.x.base.core.project.annotation.FieldTypeDescribe;
import com.x.base.core.project.bean.WrapCopier;
import com.x.base.core.project.bean.WrapCopierFactory;
import com.x.base.core.project.gson.GsonPropertyObject;
import com.x.base.core.project.http.ActionResult;
import com.x.base.core.project.http.EffectivePerson;
import com.x.base.core.project.tools.DateRange;
import com.x.base.core.project.tools.DateTools;
import com.x.base.core.project.tools.ListTools;
import com.x.processplatform.assemble.surface.Business;
import com.x.processplatform.core.entity.content.*;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class ActionManageListFilterPaging extends BaseAction {

	ActionResult<List<Wo>> execute(EffectivePerson effectivePerson, Integer page, Integer size, JsonElement jsonElement)
			throws Exception {
		try (EntityManagerContainer emc = EntityManagerContainerFactory.instance().create()) {
			Business business = new Business(emc);
			ActionResult<List<Wo>> result = new ActionResult<>();
			if (effectivePerson.isManager()) {
				Wi wi = this.convertToWrapIn(jsonElement, Wi.class);
				if (wi == null) {
					wi = new Wi();
				}
				Integer adjustPage = this.adjustPage(page);
				Integer adjustPageSize = this.adjustSize(size);
				List<Work> os = this.list(effectivePerson, business, adjustPage, adjustPageSize, wi);
				List<Wo> wos = Wo.copier.copy(os);
				result.setData(wos);
				result.setCount(this.count(effectivePerson, business, wi));
			}else{
				result.setData(new ArrayList<Wo>());
				result.setCount(0L);
			}
			return result;
		}
	}

	private List<Work> list(EffectivePerson effectivePerson, Business business, Integer adjustPage,
			Integer adjustPageSize, Wi wi) throws Exception {
		EntityManager em = business.entityManagerContainer().get(Work.class);
		List<String> person_ids = business.organization().person().list(wi.getCredentialList());
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Work> cq = cb.createQuery(Work.class);
		Root<Work> root = cq.from(Work.class);
		Predicate p = cb.conjunction();
		if (ListTools.isNotEmpty(wi.getApplicationList())) {
			p = cb.and(p, root.get(Work_.application).in(wi.getApplicationList()));
		}
		if (ListTools.isNotEmpty(wi.getProcessList())) {
			p = cb.and(p, root.get(Work_.process).in(wi.getProcessList()));
		}
		if(DateTools.isDateTimeOrDate(wi.getStartTime())){
			p = cb.and(p, cb.greaterThan(root.get(Work_.startTime), DateTools.parse(wi.getStartTime())));
		}
		if(DateTools.isDateTimeOrDate(wi.getEndTime())){
			p = cb.and(p, cb.lessThan(root.get(Work_.startTime), DateTools.parse(wi.getEndTime())));
		}
		if (ListTools.isNotEmpty(person_ids)) {
			p = cb.and(p, root.get(Work_.creatorPerson).in(person_ids));
		}
		if (StringUtils.isNotEmpty(wi.getKey())) {
			String key = StringUtils.trim(StringUtils.replace(wi.getKey(), "\u3000", " "));
			if (StringUtils.isNotEmpty(key)) {
				key = StringUtils.replaceEach(key, new String[] { "?", "%" }, new String[] { "", "" });
				p = cb.and(p,
						cb.or(cb.like(root.get(Work_.title), "%" + key + "%"),
						cb.like(root.get(Work_.serial), "%" + key + "%"),
						cb.like(root.get(Work_.creatorPerson), "%" + key + "%"),
						cb.like(root.get(Work_.creatorUnit), "%" + key + "%")));
			}
		}
		cq.select(root).where(p).orderBy(cb.desc(root.get(Work_.startTime)));
		return em.createQuery(cq).setFirstResult((adjustPage - 1) * adjustPageSize).setMaxResults(adjustPageSize)
				.getResultList();
	}

	private Long count(EffectivePerson effectivePerson, Business business, Wi wi) throws Exception {
		EntityManager em = business.entityManagerContainer().get(Work.class);
		List<String> person_ids = business.organization().person().list(wi.getCredentialList());
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<Work> root = cq.from(Work.class);
		Predicate p = cb.conjunction();
		if (ListTools.isNotEmpty(wi.getApplicationList())) {
			p = cb.and(p, root.get(Work_.application).in(wi.getApplicationList()));
		}
		if (ListTools.isNotEmpty(wi.getProcessList())) {
			p = cb.and(p, root.get(Work_.process).in(wi.getProcessList()));
		}
		if(DateTools.isDateTimeOrDate(wi.getStartTime())){
			p = cb.and(p, cb.greaterThan(root.get(Work_.startTime), DateTools.parse(wi.getStartTime())));
		}
		if(DateTools.isDateTimeOrDate(wi.getEndTime())){
			p = cb.and(p, cb.lessThan(root.get(Work_.startTime), DateTools.parse(wi.getEndTime())));
		}
		if (ListTools.isNotEmpty(person_ids)) {
			p = cb.and(p, root.get(Work_.creatorPerson).in(person_ids));
		}
		if (ListTools.isNotEmpty(wi.getCreatorUnitList())) {
			p = cb.and(p, root.get(Work_.creatorUnit).in(wi.getCreatorUnitList()));
		}
		if (ListTools.isNotEmpty(wi.getStartTimeMonthList())) {
			p = cb.and(p, root.get(Work_.startTimeMonth).in(wi.getStartTimeMonthList()));
		}
		if (ListTools.isNotEmpty(wi.getActivityNameList())) {
			p = cb.and(p, root.get(Work_.activityName).in(wi.getActivityNameList()));
		}
		if (ListTools.isNotEmpty(wi.getWorkStatusList())) {
			p = cb.and(p, root.get(Work_.workStatus).in(wi.getWorkStatusList()));
		}
		if (StringUtils.isNotEmpty(wi.getKey())) {
			String key = StringUtils.trim(StringUtils.replace(wi.getKey(), "\u3000", " "));
			if (StringUtils.isNotEmpty(key)) {
				key = StringUtils.replaceEach(key, new String[] { "?", "%" }, new String[] { "", "" });
				p = cb.and(p,
						cb.or(cb.like(root.get(Work_.title), "%" + key + "%"),
								cb.like(root.get(Work_.serial), "%" + key + "%"),
								cb.like(root.get(Work_.creatorPerson), "%" + key + "%")));
			}
		}
		return em.createQuery(cq.select(cb.count(root)).where(p)).getSingleResult();
	}

	public class Wi extends GsonPropertyObject {

		@FieldDescribe("应用")
		private List<String> applicationList;

		@FieldDescribe("流程")
		private List<String> processList;

		@FieldDescribe("开始时间yyyy-MM-dd HH:mm:ss")
		private String startTime;

		@FieldDescribe("结束时间yyyy-MM-dd HH:mm:ss")
		private String endTime;

		@FieldDescribe("启动月份")
		private List<String> startTimeMonthList;

		@FieldDescribe("创建组织")
		private List<String> creatorUnitList;

		@FieldDescribe("创建用户")
		private List<String> credentialList;

		@FieldDescribe("活动名称")
		private List<String> activityNameList;

		@FieldDescribe("工作状态")
		@FieldTypeDescribe(fieldType="enum",fieldValue="start|processing|hanging",fieldTypeName = "com.x.processplatform.core.entity.content.WorkStatus")
		private List<WorkStatus> workStatusList;

		@FieldDescribe("关键字")
		private String key;

		public List<String> getApplicationList() {
			return applicationList;
		}

		public void setApplicationList(List<String> applicationList) {
			this.applicationList = applicationList;
		}

		public List<String> getProcessList() {
			return processList;
		}

		public void setProcessList(List<String> processList) {
			this.processList = processList;
		}

		public List<String> getStartTimeMonthList() {
			return startTimeMonthList;
		}

		public void setStartTimeMonthList(List<String> startTimeMonthList) {
			this.startTimeMonthList = startTimeMonthList;
		}

		public List<String> getActivityNameList() {
			return activityNameList;
		}

		public void setActivityNameList(List<String> activityNameList) {
			this.activityNameList = activityNameList;
		}

		public List<WorkStatus> getWorkStatusList() {
			return workStatusList;
		}

		public void setWorkStatusList(List<WorkStatus> workStatusList) {
			this.workStatusList = workStatusList;
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public List<String> getCreatorUnitList() {
			return creatorUnitList;
		}

		public void setCreatorUnitList(List<String> creatorUnitList) {
			this.creatorUnitList = creatorUnitList;
		}

		public List<String> getCredentialList() {
			return credentialList;
		}

		public void setCredentialList(List<String> credentialList) {
			this.credentialList = credentialList;
		}

		public String getStartTime() {
			return startTime;
		}

		public void setStartTime(String startTime) {
			this.startTime = startTime;
		}

		public String getEndTime() {
			return endTime;
		}

		public void setEndTime(String endTime) {
			this.endTime = endTime;
		}
	}

	public static class Wo extends Work {

		private static final long serialVersionUID = 2279846765261247910L;

		static WrapCopier<Work, Wo> copier = WrapCopierFactory.wo(Work.class, Wo.class,
				JpaObject.singularAttributeField(Work.class, true, true), null);

	}

}
