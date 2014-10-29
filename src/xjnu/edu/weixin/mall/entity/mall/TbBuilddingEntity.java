package xjnu.edu.weixin.mall.entity.mall;

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
import org.jeecgframework.web.system.pojo.base.TSDepart;

import javax.persistence.SequenceGenerator;

/**   
 * @Title: Entity
 * @Description: 建筑管理
 * @date 2014-08-05 12:47:42
 * @version V1.0   
 *
 */
@Entity
@Table(name = "tb_buildding", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class TbBuilddingEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**建筑名称*/
	private java.lang.String builddingName;
	/**学校外键*/
	private java.lang.String schoolId;
	/**显示顺序*/
	private java.lang.Integer displaySequence;
	
	
	/**描述*/
	private java.lang.String builddingDescription;
	/**建筑小类外键*/
	private java.lang.String catalogId;
	/**联系方式*/
	private java.lang.String phoneTel;
	/**建筑类型*/
	private java.lang.String builddingType;
	/**经度*/
	private java.lang.Double jindu;
	/**纬度*/
	private java.lang.Double weidu;
	
	
	/**学校关联*/
	private TbSchoolEntity tbSchoolEntity;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "school_id")
	public TbSchoolEntity getTbSchoolEntity() {
		return tbSchoolEntity;
	}
	public void setTbSchoolEntity(TbSchoolEntity tbSchoolEntity) {
		this.tbSchoolEntity = tbSchoolEntity;
	}
	
	/**建筑小类关联*/
	private TbBuilddingCatalog tbBuilddingCatalogEntity;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "catalog_id")
	public TbBuilddingCatalog getTbBuilddingCatalogEntity() {
		return tbBuilddingCatalogEntity;
	}
	public void setTbBuilddingCatalogEntity(
			TbBuilddingCatalog tbBuilddingCatalogEntity) {
		this.tbBuilddingCatalogEntity = tbBuilddingCatalogEntity;
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
	 *@return: java.lang.String  建筑名称
	 */
	@Column(name ="BUILDDING_NAME",nullable=true,length=50)
	public java.lang.String getBuilddingName(){
		return this.builddingName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  建筑名称
	 */
	public void setBuilddingName(java.lang.String builddingName){
		this.builddingName = builddingName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  学校外键
	 */
	@Column(name ="SCHOOL_ID",nullable=true,length=50)
	public java.lang.String getSchoolId(){
		return this.schoolId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  学校外键
	 */
	public void setSchoolId(java.lang.String schoolId){
		this.schoolId = schoolId;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  显示顺序
	 */
	@Column(name ="DISPLAY_SEQUENCE",nullable=true,precision=10,scale=0)
	public java.lang.Integer getDisplaySequence(){
		return this.displaySequence;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  显示顺序
	 */
	public void setDisplaySequence(java.lang.Integer displaySequence){
		this.displaySequence = displaySequence;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  描述
	 */
	@Column(name ="BUILDDING_DESCRIPTION",nullable=true,length=50)
	public java.lang.String getBuilddingDescription(){
		return this.builddingDescription;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  描述
	 */
	public void setBuilddingDescription(java.lang.String builddingDescription){
		this.builddingDescription = builddingDescription;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  建筑小类外键
	 */
	@Column(name ="CATALOG_ID",nullable=true,length=50)
	public java.lang.String getCatalogId(){
		return this.catalogId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  建筑小类外键
	 */
	public void setCatalogId(java.lang.String catalogId){
		this.catalogId = catalogId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  联系方式
	 */
	@Column(name ="PHONE_TEL",nullable=true,length=20)
	public java.lang.String getPhoneTel(){
		return this.phoneTel;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  联系方式
	 */
	public void setPhoneTel(java.lang.String phoneTel){
		this.phoneTel = phoneTel;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  建筑类型
	 */
	@Column(name ="BUILDDING_TYPE",nullable=true,length=100)
	public java.lang.String getBuilddingType(){
		return this.builddingType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  建筑类型
	 */
	public void setBuilddingType(java.lang.String builddingType){
		this.builddingType = builddingType;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  经度
	 */
	@Column(name ="JINDU",nullable=true,precision=22)
	public java.lang.Double getJindu(){
		return this.jindu;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  经度
	 */
	public void setJindu(java.lang.Double jindu){
		this.jindu = jindu;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  纬度
	 */
	@Column(name ="WEIDU",nullable=true,precision=22)
	public java.lang.Double getWeidu(){
		return this.weidu;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  纬度
	 */
	public void setWeidu(java.lang.Double weidu){
		this.weidu = weidu;
	}
	
}
