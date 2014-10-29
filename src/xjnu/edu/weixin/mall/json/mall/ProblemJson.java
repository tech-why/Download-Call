package xjnu.edu.weixin.mall.json.mall;

import java.util.Date;

import xjnu.edu.weixin.mall.entity.mall.TbAddressEntity;
import xjnu.edu.weixin.mall.entity.mall.TbBuilddingEntity;
import xjnu.edu.weixin.repair.entity.repair.TbProblemTypeEntity;

public class ProblemJson {
	private java.lang.String id;
	/**用户外键*/
	private java.lang.String userId;
	/**地址外键*/
	private TbAddressEntity address;
	private TbBuilddingEntity building;
	/**故障类型外键*/
	private TbProblemTypeEntity problemType;
	/**问题描述*/
	private java.lang.String problemDescriptionf;
	/**state*/
	private java.lang.String state;
	/**提交时间*/
	private java.util.Date submitTime;
	/**completeTime*/
	private java.util.Date completeTime;
	private java.lang.Integer problemCount;
	public java.lang.Integer getProblemCount() {
		return problemCount;
	}
	public void setProblemCount(java.lang.Integer problemCount) {
		this.problemCount = problemCount;
	}
	public java.lang.String getId() {
		return id;
	}
	public void setId(java.lang.String id) {
		this.id = id;
	}
	public java.lang.String getUserId() {
		return userId;
	}
	public void setUserId(java.lang.String userId) {
		this.userId = userId;
	}
	public TbAddressEntity getAddress() {
		return address;
	}
	public void setAddress(TbAddressEntity address) {
		this.address = address;
	}
	public TbBuilddingEntity getBuilding() {
		return building;
	}
	public void setBuilding(TbBuilddingEntity building) {
		this.building = building;
	}
	public TbProblemTypeEntity getProblemType() {
		return problemType;
	}
	public void setProblemType(TbProblemTypeEntity problemType) {
		this.problemType = problemType;
	}
	public java.lang.String getProblemDescriptionf() {
		return problemDescriptionf;
	}
	public void setProblemDescriptionf(java.lang.String problemDescriptionf) {
		this.problemDescriptionf = problemDescriptionf;
	}
	public java.lang.String getState() {
		return state;
	}
	public void setState(java.lang.String state) {
		this.state = state;
	}
	public java.util.Date getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(java.util.Date submitTime) {
		this.submitTime = submitTime;
	}
	public java.util.Date getCompleteTime() {
		return completeTime;
	}
	public void setCompleteTime(java.util.Date completeTime) {
		this.completeTime = completeTime;
	}
	public ProblemJson(String id, String userId, TbAddressEntity address,
			TbBuilddingEntity building, TbProblemTypeEntity problemType,
			String problemDescriptionf, String state, Date submitTime,
			Date completeTime, Integer problemCount) {
		super();
		this.id = id;
		this.userId = userId;
		this.address = address;
		this.building = building;
		this.problemType = problemType;
		this.problemDescriptionf = problemDescriptionf;
		this.state = state;
		this.submitTime = submitTime;
		this.completeTime = completeTime;
		this.problemCount = problemCount;
		
	}
	
	
}
