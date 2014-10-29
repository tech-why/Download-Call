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
import javax.persistence.SequenceGenerator;

/**   
 * @Title: Entity
 * @Description: 号码详情
 * @author zhangdaihao
 * @date 2014-08-25 18:11:19
 * @version V1.0   
 *
 */
@Entity
@Table(name = "tb_tel_item", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class TbTelItemEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**号码联系人名*/
	private java.lang.String telItemName;
	/**小类外键*/
	private java.lang.String catalogId;
	/**显示顺序*/
	private java.lang.Integer displaySequence;
	/**电话*/
	private java.lang.String tel;
	/**办公地址*/
	private java.lang.String officeAddress;
	/**办公室名*/
	private java.lang.String officeName;
	
	/**学校外键*/
	private java.lang.String schoolId;
	
	/**关联学校*/
	private TbSchoolEntity tbSchoolEntity;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "school_id")
	public TbSchoolEntity getTbSchoolEntity() {
		return tbSchoolEntity;
	}

	public void setTbSchoolEntity(TbSchoolEntity tbSchoolEntity) {
		this.tbSchoolEntity = tbSchoolEntity;
	}
	
	
	/**关联小类*/
	private TbTelCatalogEntity tbTelCatalogEntity;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "catalog_id")
	public TbTelCatalogEntity getTbTelCatalogEntity() {
		return tbTelCatalogEntity;
	}

	public void setTbTelCatalogEntity(TbTelCatalogEntity tbTelCatalogEntity) {
		this.tbTelCatalogEntity = tbTelCatalogEntity;
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
	 *@return: java.lang.String  号码联系人名
	 */
	@Column(name ="TEL_ITEM_NAME",nullable=true,length=50)
	public java.lang.String getTelItemName(){
		return this.telItemName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  号码联系人名
	 */
	public void setTelItemName(java.lang.String telItemName){
		this.telItemName = telItemName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  小类外键
	 */
	@Column(name ="CATALOG_ID",nullable=true,length=50)
	public java.lang.String getCatalogId(){
		return this.catalogId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  小类外键
	 */
	public void setCatalogId(java.lang.String catalogId){
		this.catalogId = catalogId;
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
	 *@return: java.lang.String  电话
	 */
	@Column(name ="TEL",nullable=true,length=11)
	public java.lang.String getTel(){
		return this.tel;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  电话
	 */
	public void setTel(java.lang.String tel){
		this.tel = tel;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  办公地址
	 */
	@Column(name ="OFFICE_ADDRESS",nullable=true,length=200)
	public java.lang.String getOfficeAddress(){
		return this.officeAddress;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  办公地址
	 */
	public void setOfficeAddress(java.lang.String officeAddress){
		this.officeAddress = officeAddress;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  办公室名
	 */
	@Column(name ="OFFICE_NAME",nullable=true,length=50)
	public java.lang.String getOfficeName(){
		return this.officeName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  办公室名
	 */
	public void setOfficeName(java.lang.String officeName){
		this.officeName = officeName;
	}
}
