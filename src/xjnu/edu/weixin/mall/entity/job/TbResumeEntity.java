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

import xjnu.edu.weixin.mall.entity.mall.TbSchoolEntity;

import javax.persistence.SequenceGenerator;

/**   
 * @Title: Entity
 * @Description: 简历
 * @author zhangdaihao
 * @date 2014-09-16 12:33:34
 * @version V1.0   
 *
 */
@Entity
@Table(name = "tb_resume", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class TbResumeEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**姓名*/
	private java.lang.String name;
	/**性别*/
	private java.lang.String gender;
	/**生日*/
	private java.util.Date brithday;
	/**区域*/
	private java.lang.String area;
	/**学校*/
	private java.lang.String schoolId;
	/**期望职位*/
	private java.lang.String expirePosition;
	/**电话*/
	private java.lang.String phoneNumber;
	/**自我介绍*/
	private java.lang.String selfDiscription;
	/**图片*/
	private java.lang.String picture;
	/**QQ*/
	private java.lang.String qq;
	/**电子邮件*/
	private java.lang.String email;
	
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
	 *@return: java.lang.String  姓名
	 */
	@Column(name ="NAME",nullable=true,length=50)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  姓名
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  性别
	 */
	@Column(name ="GENDER",nullable=true,length=2)
	public java.lang.String getGender(){
		return this.gender;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  性别
	 */
	public void setGender(java.lang.String gender){
		this.gender = gender;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  生日
	 */
	@Column(name ="BRITHDAY",nullable=true)
	public java.util.Date getBrithday(){
		return this.brithday;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  生日
	 */
	public void setBrithday(java.util.Date brithday){
		this.brithday = brithday;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  区域
	 */
	@Column(name ="AREA",nullable=true,length=255)
	public java.lang.String getArea(){
		return this.area;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  区域
	 */
	public void setArea(java.lang.String area){
		this.area = area;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  学校
	 */
	@Column(name ="SCHOOL_ID",nullable=true,length=50)
	public java.lang.String getSchoolId(){
		return this.schoolId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  学校
	 */
	public void setSchoolId(java.lang.String schoolId){
		this.schoolId = schoolId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  期望职位
	 */
	@Column(name ="EXPIRE_POSITION",nullable=true,length=50)
	public java.lang.String getExpirePosition(){
		return this.expirePosition;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  期望职位
	 */
	public void setExpirePosition(java.lang.String expirePosition){
		this.expirePosition = expirePosition;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  电话
	 */
	@Column(name ="PHONE_NUMBER",nullable=true,length=20)
	public java.lang.String getPhoneNumber(){
		return this.phoneNumber;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  电话
	 */
	public void setPhoneNumber(java.lang.String phoneNumber){
		this.phoneNumber = phoneNumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  自我介绍
	 */
	@Column(name ="SELF_DISCRIPTION",nullable=true,length=255)
	public java.lang.String getSelfDiscription(){
		return this.selfDiscription;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  自我介绍
	 */
	public void setSelfDiscription(java.lang.String selfDiscription){
		this.selfDiscription = selfDiscription;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  图片
	 */
	@Column(name ="PICTURE",nullable=true,length=255)
	public java.lang.String getPicture(){
		return this.picture;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  图片
	 */
	public void setPicture(java.lang.String picture){
		this.picture = picture;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  QQ
	 */
	@Column(name ="QQ",nullable=true,length=20)
	public java.lang.String getQq(){
		return this.qq;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  QQ
	 */
	public void setQq(java.lang.String qq){
		this.qq = qq;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  电子邮件
	 */
	@Column(name ="EMAIL",nullable=true,length=20)
	public java.lang.String getEmail(){
		return this.email;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  电子邮件
	 */
	public void setEmail(java.lang.String email){
		this.email = email;
	}
}
