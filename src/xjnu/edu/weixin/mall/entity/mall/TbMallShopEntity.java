package xjnu.edu.weixin.mall.entity.mall;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.core.common.entity.IdEntity;

import javax.persistence.SequenceGenerator;

/**   
 * @Title: Entity
 * @Description: 商铺基本信息
 * @date 2014-08-05 12:56:25
 * @version V1.0   
 *
 */
@Entity
@Table(name = "tb_mall_shop", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class TbMallShopEntity extends IdEntity implements java.io.Serializable  {
	/**主键*/
//	private java.lang.String id;
	/**商铺名称*/
	private java.lang.String shopName;
	/**显示顺序*/
	private java.lang.Integer displaySequence;
	/**商铺图片*/
	private java.lang.String shopImageUrl;
	/**联系电话*/
	private java.lang.String telephoneNumber;
	/**经度*/
	private java.lang.Double jindu;
	/**纬度*/
	private java.lang.Double weidu;
	/**商铺类型*/
	private java.lang.String shopType;
	/**邮费*/
	private java.lang.Double transportFee;
	/**免邮费金额*/
	private java.lang.Double freeTransportFeeAmount;
	
	
	
	/**一对多关联*/
	private Set<TbMallShopSchoolEntity> tbMallShopSchoolEntitys = new HashSet<TbMallShopSchoolEntity>(
			0);
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tbMallShopEntity")
	public Set<TbMallShopSchoolEntity> getTbMallShopSchoolEntitys() {
		return this.tbMallShopSchoolEntitys;
	}

	public void setTbMallShopSchoolEntitys(
			Set<TbMallShopSchoolEntity> tbMallShopEntitys) {
		this.tbMallShopSchoolEntitys = tbMallShopEntitys;
	
	}
	
	/**商铺描述*/
	private String shopDescription;
	@Column(name = "shop_description", length = 65535)
	public String getShopDescription() {
		return this.shopDescription;
	}

	public void setShopDescription(String shopDescription) {
		this.shopDescription = shopDescription;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	
	/*@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=50)
	public java.lang.String getId(){
		return this.id;
	}

	*//**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主键
	 *//*
	public void setId(java.lang.String id){
		this.id = id;
	}*/
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  商铺名称
	 */
	@Column(name ="SHOP_NAME",nullable=false,length=50)
	public java.lang.String getShopName(){
		return this.shopName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  商铺名称
	 */
	public void setShopName(java.lang.String shopName){
		this.shopName = shopName;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  显示顺序
	 */
	@Column(name ="DISPLAY_SEQUENCE",nullable=false,precision=10,scale=0)
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
	 *@return: java.lang.String  商铺图片
	 */
	@Column(name ="SHOP_IMAGE_URL",nullable=true,length=250)
	public java.lang.String getShopImageUrl(){
		return this.shopImageUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  商铺图片
	 */
	public void setShopImageUrl(java.lang.String shopImageUrl){
		this.shopImageUrl = shopImageUrl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  联系电话
	 */
	@Column(name ="TELEPHONE_NUMBER",nullable=true,length=20)
	public java.lang.String getTelephoneNumber(){
		return this.telephoneNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  联系电话
	 */
	public void setTelephoneNumber(java.lang.String telephoneNumber){
		this.telephoneNumber = telephoneNumber;
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
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  商铺类型
	 */
	@Column(name ="SHOP_TYPE",nullable=true,length=50)
	public java.lang.String getShopType(){
		return this.shopType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  商铺类型
	 */
	public void setShopType(java.lang.String shopType){
		this.shopType = shopType;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  邮费
	 */
	@Column(name ="TRANSPORT_FEE",nullable=true,precision=22)
	public java.lang.Double getTransportFee(){
		return this.transportFee;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  邮费
	 */
	public void setTransportFee(java.lang.Double transportFee){
		this.transportFee = transportFee;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  免邮费金额
	 */
	@Column(name ="FREE_TRANSPORT_FEE_AMOUNT",nullable=true,precision=22)
	public java.lang.Double getFreeTransportFeeAmount(){
		return this.freeTransportFeeAmount;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  免邮费金额
	 */
	public void setFreeTransportFeeAmount(java.lang.Double freeTransportFeeAmount){
		this.freeTransportFeeAmount = freeTransportFeeAmount;
	}
}
