package xjnu.edu.weixin.repair.page.repair;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import xjnu.edu.weixin.repair.entity.repair.TbProblemOperateRecordEntity;

import javax.persistence.SequenceGenerator;



/**   
 * @Title: Entity
 * @Description: 维修人员信息
 * @author zhangdaihao
 * @date 2014-09-26 18:12:26
 * @version V1.0   
 *
 */
@Entity
@Table(name = "tb_submit_problem", schema = "")
@SuppressWarnings("serial")
public class TbSubmitProblemPage implements java.io.Serializable {
	/**保存-维修记录*/
	private List<TbProblemOperateRecordEntity> tbProblemOperateRecordList = new ArrayList<TbProblemOperateRecordEntity>();
	public List<TbProblemOperateRecordEntity> getTbProblemOperateRecordList() {
		return tbProblemOperateRecordList;
	}
	public void setTbProblemOperateRecordList(List<TbProblemOperateRecordEntity> tbProblemOperateRecordList) {
		this.tbProblemOperateRecordList = tbProblemOperateRecordList;
	}


	/**id*/
	private java.lang.String id;
	/**用户外键*/
	private java.lang.String userId;
	/**地址外键*/
	private java.lang.String addressId;
	/**problemCount*/
	private java.lang.Integer problemCount;
	/**故障类型外键*/
	private java.lang.String problemTypeId;
	/**problemDescriptionf*/
	private java.lang.String problemDescriptionf;
	/**state*/
	private java.lang.String state;
	/**submitTime*/
	private java.util.Date submitTime;
	/**completeTime*/
	private java.util.Date completeTime;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  id
	 */
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=50)
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  id
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  用户外键
	 */
	@Column(name ="USER_ID",nullable=false,length=100)
	public java.lang.String getUserId(){
		return this.userId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  用户外键
	 */
	public void setUserId(java.lang.String userId){
		this.userId = userId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  地址外键
	 */
	@Column(name ="ADDRESS_ID",nullable=false,length=50)
	public java.lang.String getAddressId(){
		return this.addressId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  地址外键
	 */
	public void setAddressId(java.lang.String addressId){
		this.addressId = addressId;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  problemCount
	 */
	@Column(name ="PROBLEM_COUNT",nullable=false,precision=10,scale=0)
	public java.lang.Integer getProblemCount(){
		return this.problemCount;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  problemCount
	 */
	public void setProblemCount(java.lang.Integer problemCount){
		this.problemCount = problemCount;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  故障类型外键
	 */
	@Column(name ="PROBLEM_TYPE_ID",nullable=true,length=50)
	public java.lang.String getProblemTypeId(){
		return this.problemTypeId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  故障类型外键
	 */
	public void setProblemTypeId(java.lang.String problemTypeId){
		this.problemTypeId = problemTypeId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  problemDescriptionf
	 */
	@Column(name ="PROBLEM_DESCRIPTIONF",nullable=true,length=200)
	public java.lang.String getProblemDescriptionf(){
		return this.problemDescriptionf;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  problemDescriptionf
	 */
	public void setProblemDescriptionf(java.lang.String problemDescriptionf){
		this.problemDescriptionf = problemDescriptionf;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  state
	 */
	@Column(name ="STATE",nullable=true,length=50)
	public java.lang.String getState(){
		return this.state;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  state
	 */
	public void setState(java.lang.String state){
		this.state = state;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  submitTime
	 */
	@Column(name ="SUBMIT_TIME",nullable=true)
	public java.util.Date getSubmitTime(){
		return this.submitTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  submitTime
	 */
	public void setSubmitTime(java.util.Date submitTime){
		this.submitTime = submitTime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  completeTime
	 */
	@Column(name ="COMPLETE_TIME",nullable=true)
	public java.util.Date getCompleteTime(){
		return this.completeTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  completeTime
	 */
	public void setCompleteTime(java.util.Date completeTime){
		this.completeTime = completeTime;
	}
}
