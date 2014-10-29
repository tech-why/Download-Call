package xjnu.edu.weixin.mall.json.mall;

import java.util.Date;

import xjnu.edu.weixin.mall.entity.mall.WeixinUserinfoEntity;

public class OperatorRecordJson {
	/**id*/
	private java.lang.String id;
	/**problemId*/
	private java.lang.String problemId;
	/**operateState*/
	private java.lang.String operateState;
	public OperatorRecordJson(String id, String problemId, String operateState,
			WeixinUserinfoEntity weixinuser, Date operateTime) {
		super();
		this.id = id;
		this.problemId = problemId;
		this.operateState = operateState;
		this.weixinuser = weixinuser;
		this.operateTime = operateTime;
	}
	public java.lang.String getId() {
		return id;
	}
	public void setId(java.lang.String id) {
		this.id = id;
	}
	public java.lang.String getProblemId() {
		return problemId;
	}
	public void setProblemId(java.lang.String problemId) {
		this.problemId = problemId;
	}
	public java.lang.String getOperateState() {
		return operateState;
	}
	public void setOperateState(java.lang.String operateState) {
		this.operateState = operateState;
	}
	public WeixinUserinfoEntity getWeixinuser() {
		return weixinuser;
	}
	public void setWeixinuser(WeixinUserinfoEntity weixinuser) {
		this.weixinuser = weixinuser;
	}
	public java.util.Date getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(java.util.Date operateTime) {
		this.operateTime = operateTime;
	}
	/**operateId*/
	private WeixinUserinfoEntity weixinuser;
	/**operateTime*/
	private java.util.Date operateTime;

}
