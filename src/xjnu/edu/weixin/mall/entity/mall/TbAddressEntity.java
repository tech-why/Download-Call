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
 * @Description: 地址管理
 * @author zhangdaihao
 * @date 2014-08-11 16:39:02
 * @version V1.0   
 *
 */
@Entity
@Table(name = "tb_address", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class TbAddressEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**建筑外键*/
	private java.lang.String builddingId;
	/**联系人*/
	private java.lang.String contact;
	/**电话号码*/
	private java.lang.String phoneNumber;
	/**移动电话*/
	private java.lang.String mobileNumber;
	/**详细地址*/
	private java.lang.String houseCode;
	/**用户名外键*/
	private java.lang.String weixinUserId;
	/**是否活动*/
	private java.lang.String isActive;
	
	/**是否选中**/
	private java.lang.String isChoose;
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否选中
	 */
	@Column(name ="IS_CHOOSE",nullable=true,length=50)
	public java.lang.String getIsChoose() {
		return isChoose;
	}
	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否选中
	 */
	public void setIsChoose(java.lang.String isChoose) {
		this.isChoose = isChoose;
	}
	
	
	
	/**关联建筑*/
	private TbBuilddingEntity tbBuilddingEntity;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "buildding_id")
	public TbBuilddingEntity getTbBuilddingEntity() {
		return tbBuilddingEntity;
	}

	public void setTbBuilddingEntity(TbBuilddingEntity tbBuilddingEntity) {
		this.tbBuilddingEntity = tbBuilddingEntity;
	}
	
	/**关联用户*/
	private WeixinUserinfoEntity weixinUserinfoEntity;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "weixin_user_id")
	public WeixinUserinfoEntity getWeixinUserinfoEntity() {
		return weixinUserinfoEntity;
	}

	public void setWeixinUserinfoEntity(WeixinUserinfoEntity weixinUserinfoEntity) {
		this.weixinUserinfoEntity = weixinUserinfoEntity;
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
	 *@return: java.lang.String  建筑外键
	 */
	@Column(name ="BUILDDING_ID",nullable=false,length=50)
	public java.lang.String getBuilddingId(){
		return this.builddingId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  建筑外键
	 */
	public void setBuilddingId(java.lang.String builddingId){
		this.builddingId = builddingId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  联系人
	 */
	@Column(name ="CONTACT",nullable=true,length=150)
	public java.lang.String getContact(){
		return this.contact;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  联系人
	 */
	public void setContact(java.lang.String contact){
		this.contact = contact;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  电话号码
	 */
	@Column(name ="PHONE_NUMBER",nullable=true,length=50)
	public java.lang.String getPhoneNumber(){
		return this.phoneNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  电话号码
	 */
	public void setPhoneNumber(java.lang.String phoneNumber){
		this.phoneNumber = phoneNumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  移动电话
	 */
	@Column(name ="MOBILE_NUMBER",nullable=true,length=50)
	public java.lang.String getMobileNumber(){
		return this.mobileNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  移动电话
	 */
	public void setMobileNumber(java.lang.String mobileNumber){
		this.mobileNumber = mobileNumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  详细地址
	 */
	@Column(name ="HOUSE_CODE",nullable=true,length=50)
	public java.lang.String getHouseCode(){
		return this.houseCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  详细地址
	 */
	public void setHouseCode(java.lang.String houseCode){
		this.houseCode = houseCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  用户名外键
	 */
	@Column(name ="WEIXIN_USER_ID",nullable=true,length=50)
	public java.lang.String getWeixinUserId(){
		return this.weixinUserId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  用户名外键
	 */
	public void setWeixinUserId(java.lang.String weixinUserId){
		this.weixinUserId = weixinUserId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否活动
	 */
	@Column(name ="IS_ACTIVE",nullable=true,length=50)
	public java.lang.String getIsActive(){
		return this.isActive;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否活动
	 */
	public void setIsActive(java.lang.String isActive){
		this.isActive = isActive;
	}
}
