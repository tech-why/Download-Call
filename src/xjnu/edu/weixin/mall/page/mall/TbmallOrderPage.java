package xjnu.edu.weixin.mall.page.mall;

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
import javax.persistence.SequenceGenerator;

import xjnu.edu.weixin.mall.entity.mall.TbMallOrderItemEntity;
import xjnu.edu.weixin.mall.entity.mall.TbMallOrderOperateEntity;

/**   
 * @Title: Entity
 * @Description: 订单管理
 * @date 2014-08-05 13:07:22
 * @version V1.0   
 *
 */
@Entity
@Table(name = "tb_mall_order", schema = "")
@SuppressWarnings("serial")
public class TbmallOrderPage implements java.io.Serializable {
	/**保存-订单明细*/
	private List<TbMallOrderItemEntity> tbMallOrderItemList = new ArrayList<TbMallOrderItemEntity>();
	public List<TbMallOrderItemEntity> getTbMallOrderItemList() {
		return tbMallOrderItemList;
	}
	public void setTbMallOrderItemList(List<TbMallOrderItemEntity> tbMallOrderItemList) {
		this.tbMallOrderItemList = tbMallOrderItemList;
	}
	/**保存-订单跟踪*/
	private List<TbMallOrderOperateEntity> tbMallOrderOperateList = new ArrayList<TbMallOrderOperateEntity>();
	public List<TbMallOrderOperateEntity> getTbMallOrderOperateList() {
		return tbMallOrderOperateList;
	}
	public void setTbMallOrderOperateList(List<TbMallOrderOperateEntity> tbMallOrderOperateList) {
		this.tbMallOrderOperateList = tbMallOrderOperateList;
	}


	/**主键*/
	private java.lang.String id;
	/**用户外键*/
	private java.lang.String weixinUserId;
	/**地址外键*/
	private java.lang.String addressId;
	/**订单状态*/
	private java.lang.String orderState;
	/**预约时间*/
	private java.util.Date orderRequireTime;
	/**完成时间*/
	private java.util.Date orderCompleteTime;
	/**用户留言*/
	private java.lang.String userMessage;
	/**订单积分*/
	private java.lang.Integer orderScore;
	/**积分外键*/
	private java.lang.String scoreId;
	/**总价格*/
	private java.lang.Float totalPrice;
	/**订单创建时间*/
	private java.util.Date orderCreateTime;
	/**订单完成时间*/
	private java.util.Date orderLastTime;
	/**折扣*/
	private java.lang.Float unchargeAmount;
	/**订单明细字符串*/
	private java.lang.String orderItemString;
	/**赠品字符串*/
	private java.lang.String giftString;
	/**订单序列号*/
	private java.lang.Integer orderCount;
	/**商铺外键*/
	private java.lang.String shopId;
	/**学校外键*/
	private java.lang.String schoolId;
	
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
	 *@return: java.lang.String  用户外键
	 */
	@Column(name ="WEIXIN_USER_ID",nullable=true,length=50)
	public java.lang.String getWeixinUserId(){
		return this.weixinUserId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  用户外键
	 */
	public void setWeixinUserId(java.lang.String weixinUserId){
		this.weixinUserId = weixinUserId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  地址外键
	 */
	@Column(name ="ADDRESS_ID",nullable=true,length=50)
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  订单状态
	 */
	@Column(name ="ORDER_STATE",nullable=true,length=50)
	public java.lang.String getOrderState(){
		return this.orderState;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  订单状态
	 */
	public void setOrderState(java.lang.String orderState){
		this.orderState = orderState;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  预约时间
	 */
	@Column(name ="ORDER_REQUIRE_TIME",nullable=true)
	public java.util.Date getOrderRequireTime(){
		return this.orderRequireTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  预约时间
	 */
	public void setOrderRequireTime(java.util.Date orderRequireTime){
		this.orderRequireTime = orderRequireTime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  完成时间
	 */
	@Column(name ="ORDER_COMPLETE_TIME",nullable=true)
	public java.util.Date getOrderCompleteTime(){
		return this.orderCompleteTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  完成时间
	 */
	public void setOrderCompleteTime(java.util.Date orderCompleteTime){
		this.orderCompleteTime = orderCompleteTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  用户留言
	 */
	@Column(name ="USER_MESSAGE",nullable=true,length=250)
	public java.lang.String getUserMessage(){
		return this.userMessage;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  用户留言
	 */
	public void setUserMessage(java.lang.String userMessage){
		this.userMessage = userMessage;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  订单积分
	 */
	@Column(name ="ORDER_SCORE",nullable=true,precision=10,scale=0)
	public java.lang.Integer getOrderScore(){
		return this.orderScore;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  订单积分
	 */
	public void setOrderScore(java.lang.Integer orderScore){
		this.orderScore = orderScore;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  积分外键
	 */
	@Column(name ="SCORE_ID",nullable=true,length=50)
	public java.lang.String getScoreId(){
		return this.scoreId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  积分外键
	 */
	public void setScoreId(java.lang.String scoreId){
		this.scoreId = scoreId;
	}
	/**
	 *方法: 取得java.lang.Float
	 *@return: java.lang.Float  总价格
	 */
	@Column(name ="TOTAL_PRICE",nullable=true,precision=12)
	public java.lang.Float getTotalPrice(){
		return this.totalPrice;
	}

	/**
	 *方法: 设置java.lang.Float
	 *@param: java.lang.Float  总价格
	 */
	public void setTotalPrice(java.lang.Float totalPrice){
		this.totalPrice = totalPrice;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  订单创建时间
	 */
	@Column(name ="ORDER_CREATE_TIME",nullable=true)
	public java.util.Date getOrderCreateTime(){
		return this.orderCreateTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  订单创建时间
	 */
	public void setOrderCreateTime(java.util.Date orderCreateTime){
		this.orderCreateTime = orderCreateTime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  订单完成时间
	 */
	@Column(name ="ORDER_LAST_TIME",nullable=true)
	public java.util.Date getOrderLastTime(){
		return this.orderLastTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  订单完成时间
	 */
	public void setOrderLastTime(java.util.Date orderLastTime){
		this.orderLastTime = orderLastTime;
	}
	/**
	 *方法: 取得java.lang.Float
	 *@return: java.lang.Float  折扣
	 */
	@Column(name ="UNCHARGE_AMOUNT",nullable=true,precision=12)
	public java.lang.Float getUnchargeAmount(){
		return this.unchargeAmount;
	}

	/**
	 *方法: 设置java.lang.Float
	 *@param: java.lang.Float  折扣
	 */
	public void setUnchargeAmount(java.lang.Float unchargeAmount){
		this.unchargeAmount = unchargeAmount;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  订单明细字符串
	 */
	@Column(name ="ORDER_ITEM_STRING",nullable=true,length=250)
	public java.lang.String getOrderItemString(){
		return this.orderItemString;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  订单明细字符串
	 */
	public void setOrderItemString(java.lang.String orderItemString){
		this.orderItemString = orderItemString;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  赠品字符串
	 */
	@Column(name ="GIFT_STRING",nullable=true,length=250)
	public java.lang.String getGiftString(){
		return this.giftString;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  赠品字符串
	 */
	public void setGiftString(java.lang.String giftString){
		this.giftString = giftString;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  订单序列号
	 */
	@Column(name ="ORDER_COUNT",nullable=false,precision=10,scale=0)
	public java.lang.Integer getOrderCount(){
		return this.orderCount;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  订单序列号
	 */
	public void setOrderCount(java.lang.Integer orderCount){
		this.orderCount = orderCount;
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
}
