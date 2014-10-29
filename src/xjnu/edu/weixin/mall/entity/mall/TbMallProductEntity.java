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
 * @Description: 商品上架
 * @date 2014-08-05 12:53:53
 * @version V1.0   
 *
 */
@Entity
@Table(name = "tb_mall_product", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class TbMallProductEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**商品名称*/
	private java.lang.String productName;
	/**显示顺序*/
	private java.lang.Integer displaySequence;
	/**商品描述*//*
	private java.lang.String productDescription;*/
	
	/**商品图片*/
	private java.lang.String productImageUrl;
	/**商铺外键*/
	private java.lang.String shopId;
	/**销量*/
	private java.lang.Integer saleCount;
	/**是否活动*/
	private java.lang.String isActive;
	/**是否显示*/
	private java.lang.String isDisplay;
	/**剩余数量*/
	private java.lang.Double remainAmount;
	/**单位量*/
	private java.lang.Double unitAmount;
	/**积分系数*/
	private java.lang.Double scoreCoefficient;
	/**单位*/
	private java.lang.String productUnit;
	/**单价*/
	private java.lang.Double productPrice;
	/**参考价*/
	private java.lang.Double normalPrice;
	@Column(name ="NORMAL_PRICE",nullable=true,precision=22)
	public java.lang.Double getNormalPrice() {
		return normalPrice;
	}
	public void setNormalPrice(java.lang.Double normalPrice) {
		this.normalPrice = normalPrice;
	}

	/**小类外键*/
	private java.lang.String catalogId;
	
	/**关联小类*/
	private TbMallProductCatalogEntity tbmallProductCatalogEntity;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "catalog_id")
	public TbMallProductCatalogEntity getTbmallProductCatalogEntity() {
		return tbmallProductCatalogEntity;
	}
	public void setTbmallProductCatalogEntity(
			TbMallProductCatalogEntity tbmallProductCatalogEntity) {
		this.tbmallProductCatalogEntity = tbmallProductCatalogEntity;
	}

	/**关联商铺*/
	private TbMallShopEntity tbMallShopEntity;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "shop_id")
	public TbMallShopEntity getTbMallShopEntity() {
		return tbMallShopEntity;
	}

	public void setTbMallShopEntity(TbMallShopEntity tbMallShopEntity) {
		this.tbMallShopEntity = tbMallShopEntity;
	}
	
	
	
	/**商品描述*/
	private String productDescription;
	@Column(name = "product_description", length = 500)
	public String getProductDescription() {
		return this.productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
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
	 *@return: java.lang.String  商品名称
	 */
	@Column(name ="PRODUCT_NAME",nullable=false,length=50)
	public java.lang.String getProductName(){
		return this.productName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  商品名称
	 */
	public void setProductName(java.lang.String productName){
		this.productName = productName;
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
	 *@return: java.lang.String  商品描述
	 */
	/*@Column(name ="PRODUCT_DESCRIPTION",nullable=true,length=50)
	public java.lang.String getProductDescription(){
		return this.productDescription;
	}

	*//**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  商品描述
	 *//*
	public void setProductDescription(java.lang.String productDescription){
		this.productDescription = productDescription;
	}*/
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  商品图片
	 */
	@Column(name ="PRODUCT_IMAGE_URL",nullable=true,length=250)
	public java.lang.String getProductImageUrl(){
		return this.productImageUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  商品图片
	 */
	public void setProductImageUrl(java.lang.String productImageUrl){
		this.productImageUrl = productImageUrl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  商铺外键
	 */
	@Column(name ="SHOP_ID",nullable=true,length=50)
	public java.lang.String getShopId(){
		return this.shopId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  商铺外键
	 */
	public void setShopId(java.lang.String shopId){
		this.shopId = shopId;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  销量
	 */
	@Column(name ="SALE_COUNT",nullable=true,precision=10,scale=0)
	public java.lang.Integer getSaleCount(){
		return this.saleCount;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  销量
	 */
	public void setSaleCount(java.lang.Integer saleCount){
		this.saleCount = saleCount;
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
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否显示
	 */
	@Column(name ="IS_DISPLAY",nullable=true,length=50)
	public java.lang.String getIsDisplay(){
		return this.isDisplay;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否显示
	 */
	public void setIsDisplay(java.lang.String isDisplay){
		this.isDisplay = isDisplay;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  剩余数量
	 */
	@Column(name ="REMAIN_AMOUNT",nullable=true,precision=22)
	public java.lang.Double getRemainAmount(){
		return this.remainAmount;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  剩余数量
	 */
	public void setRemainAmount(java.lang.Double remainAmount){
		this.remainAmount = remainAmount;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  单位量
	 */
	@Column(name ="UNIT_AMOUNT",nullable=true,precision=22)
	public java.lang.Double getUnitAmount(){
		return this.unitAmount;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  单位量
	 */
	public void setUnitAmount(java.lang.Double unitAmount){
		this.unitAmount = unitAmount;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  积分系数
	 */
	@Column(name ="SCORE_COEFFICIENT",nullable=true,precision=22)
	public java.lang.Double getScoreCoefficient(){
		return this.scoreCoefficient;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  积分系数
	 */
	public void setScoreCoefficient(java.lang.Double scoreCoefficient){
		this.scoreCoefficient = scoreCoefficient;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  单位
	 */
	@Column(name ="PRODUCT_UNIT",nullable=true,length=50)
	public java.lang.String getProductUnit(){
		return this.productUnit;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  单位
	 */
	public void setProductUnit(java.lang.String productUnit){
		this.productUnit = productUnit;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  单价
	 */
	@Column(name ="PRODUCT_PRICE",nullable=true,precision=22)
	public java.lang.Double getProductPrice(){
		return this.productPrice;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  单价
	 */
	public void setProductPrice(java.lang.Double productPrice){
		this.productPrice = productPrice;
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
}
