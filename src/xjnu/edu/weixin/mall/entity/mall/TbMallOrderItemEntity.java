package xjnu.edu.weixin.mall.entity.mall;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;

/**   
 * @Title: Entity
 * @Description: 订单明细
 * @date 2014-08-05 13:07:21
 * @version V1.0   
 *
 */
@Entity
@Table(name = "tb_mall_order_item", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class TbMallOrderItemEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**订单外键*/
	private java.lang.String orderId;
	/**商品外键*/
	private java.lang.String productId;

	/**商品名称*/
	private java.lang.String productName;
	/**商品描述*/
	private java.lang.String productDescription;
	/**商品图片*/
	private java.lang.String productImageUrl;
	/**单位*/
	private java.lang.String productUnit;
	/**价格*/
	private java.lang.Float productPrice;
	/**单位量*/
	private java.lang.Double unitAmount;
	/**积分系数*/
	private java.lang.Double scoreCoefficient;
	/**数量*/
	private java.lang.Double count;
	
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
	 *@return: java.lang.String  订单外键
	 */
	@Column(name ="ORDER_ID",nullable=true,length=50)
	public java.lang.String getOrderId(){
		return this.orderId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  订单外键
	 */
	public void setOrderId(java.lang.String orderId){
		this.orderId = orderId;
	}
	
	@Column(name ="PRODUCT_ID",nullable=true,length=50)
	public java.lang.String getProductId() {
		return productId;
	}

	public void setProductId(java.lang.String productId) {
		this.productId = productId;
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  商品描述
	 */
	@Column(name ="PRODUCT_DESCRIPTION",nullable=true,length=50)
	public java.lang.String getProductDescription(){
		return this.productDescription;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  商品描述
	 */
	public void setProductDescription(java.lang.String productDescription){
		this.productDescription = productDescription;
	}
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
	 *方法: 取得java.lang.Float
	 *@return: java.lang.Float  价格
	 */
	@Column(name ="PRODUCT_PRICE",nullable=true,precision=12)
	public java.lang.Float getProductPrice(){
		return this.productPrice;
	}

	/**
	 *方法: 设置java.lang.Float
	 *@param: java.lang.Float  价格
	 */
	public void setProductPrice(java.lang.Float productPrice){
		this.productPrice = productPrice;
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
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  数量
	 */
	@Column(name ="COUNT",nullable=true,precision=11,scale=0)
	public java.lang.Double getCount(){
		return this.count;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  数量
	 */
	public void setCount(java.lang.Double count){
		this.count = count;
	}
}
