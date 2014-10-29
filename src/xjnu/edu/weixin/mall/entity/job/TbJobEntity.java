package xjnu.edu.weixin.mall.entity.job;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.web.system.pojo.base.TSTerritory;

import javax.persistence.SequenceGenerator;

/**   
 * @Title: Entity
 * @Description: 工作表
 * @author zhangdaihao
 * @date 2014-09-16 12:34:03
 * @version V1.0   
 *
 */
@Entity
@Table(name = "tb_job", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class TbJobEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**职位名*/
	private java.lang.String positionname;
	/**工资*/
	private java.lang.String salary;
	/**需要的人数*/
	private java.lang.String needConnt;
	/**公司外键*/
	private java.lang.String companyId;
	/**工作描述*/
	private java.lang.String jobDescription;
	/**创建时间*/
	private java.util.Date creatTime;
	/**工作场所*/
	private java.lang.String workPlace;
	/**公司外键*/
	private java.lang.String tsterritoryId;
	@Column(name ="TSTERRITORY_ID",nullable=true,length=32)
   public java.lang.String getTsterritoryId() {
		return tsterritoryId;
	}
	public void setTsterritoryId(java.lang.String tsterritoryId) {
		this.tsterritoryId = tsterritoryId;
	}








	//	private java.lang.String tsterritoryId;
	/**图片*/
	private java.lang.String imagesUrl;
	@Column(name ="IMAGESURL",nullable=true,length=255)
	public java.lang.String getImagesUrl() {
		return imagesUrl;
	}
	public void setImagesUrl(java.lang.String imagesUrl) {
		this.imagesUrl = imagesUrl;
	}



	
	



	/**关联区域信息*/
	private TSTerritory tSTerritory;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tsterritory_id")
	public TSTerritory gettSTerritory() {
		return tSTerritory;
	}
	public void settSTerritory(TSTerritory tSTerritory) {
		this.tSTerritory = tSTerritory;
	}

	
	
	/**关联公司*/
	private TbCompanyEntity tbCompanyEntity;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	public TbCompanyEntity getTbCompanyEntity() {
		return tbCompanyEntity;
	}
	public void setTbCompanyEntity(
			TbCompanyEntity tbCompanyEntity) {
		this.tbCompanyEntity = tbCompanyEntity;
	}
	
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
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
	 *@param: java.lang.String  主键
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  职位名
	 */
	@Column(name ="POSITIONNAME",nullable=true,length=50)
	public java.lang.String getPositionname(){
		return this.positionname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  职位名
	 */
	public void setPositionname(java.lang.String positionname){
		this.positionname = positionname;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  工资
	 */
	@Column(name ="SALARY",nullable=true,length=20)
	public java.lang.String getSalary(){
		return this.salary;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  工资
	 */
	public void setSalary(java.lang.String salary){
		this.salary = salary;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  需要的人数
	 */
	@Column(name ="NEED_CONNT",nullable=true,length=100)
	public java.lang.String getNeedConnt(){
		return this.needConnt;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  需要的人数
	 */
	public void setNeedConnt(java.lang.String needConnt){
		this.needConnt = needConnt;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  公司外键
	 */
	@Column(name ="COMPANY_ID",nullable=true,length=50)
	public java.lang.String getCompanyId(){
		return this.companyId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  公司外键
	 */
	public void setCompanyId(java.lang.String companyId){
		this.companyId = companyId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  工作描述
	 */
	@Column(name ="JOB_DESCRIPTION",nullable=true,length=255)
	public java.lang.String getJobDescription(){
		return this.jobDescription;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  工作描述
	 */
	public void setJobDescription(java.lang.String jobDescription){
		this.jobDescription = jobDescription;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建时间
	 */
	@Column(name ="CREAT_TIME",nullable=true)
	public java.util.Date getCreatTime(){
		return this.creatTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建时间
	 */
	public void setCreatTime(java.util.Date creatTime){
		this.creatTime = creatTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  工作场所
	 */
	@Column(name ="WORK_PLACE",nullable=true,length=255)
	public java.lang.String getWorkPlace(){
		return this.workPlace;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  工作场所
	 */
	public void setWorkPlace(java.lang.String workPlace){
		this.workPlace = workPlace;
	}
}
