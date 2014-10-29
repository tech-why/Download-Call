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
import javax.persistence.SequenceGenerator;

/**   
 * @Title: Entity
 * @Description: 兼职
 * @author zhangdaihao
 * @date 2014-09-16 12:29:09
 * @version V1.0   
 *
 */
@Entity
@Table(name = "tb_parttime_job", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class TbParttimeJobEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**工作名称*/
	private java.lang.String name;
	/**工资外键*/
	private java.lang.String salaryTypeId;
	/**工资*/
	private java.lang.String salary;
	/**需要的人数*/
	private java.lang.String needConnt;
	/**工作描述*/
	private java.lang.String jobDescription;
	/**工作时间*/
	private java.lang.String workTime;
	/**公司外键*/
	private java.lang.String companyId;
	/**工作小类外键*/
	private java.lang.String jobCatalogId;
	/**地址*/
	private java.lang.String address;
	/**区域小类外键*/
	private java.lang.String areaCatalogId;
	/**创建时间*/
	private java.util.Date creatTime;
	
	/**图片*/
	private java.lang.String imagesUrl;
	@Column(name ="IMAGESURL",nullable=true,length=255)
	public java.lang.String getImagesUrl() {
		return imagesUrl;
	}
	public void setImagesUrl(java.lang.String imagesUrl) {
		this.imagesUrl = imagesUrl;
	}
	
	/**关联区域小类*/
	private TbAreaCatalogEntity tbAreaCatalogEntity;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "area_catalog_id")
	public TbAreaCatalogEntity getTbAreaCatalogEntity() {
		return tbAreaCatalogEntity;
	}
	public void setTbAreaCatalogEntity(
			TbAreaCatalogEntity tbAreaCatalogEntity) {
		this.tbAreaCatalogEntity = tbAreaCatalogEntity;
	}
	
	
	/**关联工作小类*/
	private TbJobCatalogEntity tbJobCatalogEntity;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "job_catalog_id")
	public TbJobCatalogEntity getTbJobCatalogEntity() {
		return tbJobCatalogEntity;
	}
	public void setTbJobCatalogEntity(
			TbJobCatalogEntity tbJobCatalogEntity) {
		this.tbJobCatalogEntity = tbJobCatalogEntity;
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
	
	/**关联工资类别*/
	private TbSalaryTypeEntity tbSalaryTypeEntity;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "salary_type_id")
	public TbSalaryTypeEntity getTbSalaryTypeEntity() {
		return tbSalaryTypeEntity;
	}
	public void setTbSalaryTypeEntity(
			TbSalaryTypeEntity tbSalaryTypeEntity) {
		this.tbSalaryTypeEntity = tbSalaryTypeEntity;
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
	 *@return: java.lang.String  工作名称
	 */
	@Column(name ="NAME",nullable=true,length=50)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  工作名称
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  工资外键
	 */
	@Column(name ="SALARY_TYPE_ID",nullable=true,length=50)
	public java.lang.String getSalaryTypeId(){
		return this.salaryTypeId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  工资外键
	 */
	public void setSalaryTypeId(java.lang.String salaryTypeId){
		this.salaryTypeId = salaryTypeId;
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  工作时间
	 */
	@Column(name ="WORK_TIME",nullable=true,length=255)
	public java.lang.String getWorkTime(){
		return this.workTime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  工作时间
	 */
	public void setWorkTime(java.lang.String workTime){
		this.workTime = workTime;
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
	 *@return: java.lang.String  工作小类外键
	 */
	@Column(name ="JOB_CATALOG_ID",nullable=true,length=50)
	public java.lang.String getJobCatalogId(){
		return this.jobCatalogId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  工作小类外键
	 */
	public void setJobCatalogId(java.lang.String jobCatalogId){
		this.jobCatalogId = jobCatalogId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  地址
	 */
	@Column(name ="ADDRESS",nullable=true,length=255)
	public java.lang.String getAddress(){
		return this.address;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  地址
	 */
	public void setAddress(java.lang.String address){
		this.address = address;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  区域小类外键
	 */
	@Column(name ="AREA_CATALOG_ID",nullable=true,length=50)
	public java.lang.String getAreaCatalogId(){
		return this.areaCatalogId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  区域小类外键
	 */
	public void setAreaCatalogId(java.lang.String areaCatalogId){
		this.areaCatalogId = areaCatalogId;
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
}
