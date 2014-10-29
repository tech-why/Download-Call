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
 * @Description: 订单跟踪
 * @date 2014-08-05 13:07:21
 * @version V1.0   
 *
 */
@Entity
@Table(name = "tb_mall_order_operate", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class TbMallOrderOperateEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**操作类型*/
	private java.lang.String operateType;
	/**操作时间*/
	private java.util.Date operateTime;
	/**订单外键*/
	private java.lang.String orderId;
	
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
	 *@return: java.lang.String  操作类型
	 */
	@Column(name ="OPERATE_TYPE",nullable=true,length=20)
	public java.lang.String getOperateType(){
		return this.operateType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  操作类型
	 */
	public void setOperateType(java.lang.String operateType){
		this.operateType = operateType;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  操作时间
	 */
	@Column(name ="OPERATE_TIME",nullable=true)
	public java.util.Date getOperateTime(){
		return this.operateTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  操作时间
	 */
	public void setOperateTime(java.util.Date operateTime){
		this.operateTime = operateTime;
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
}
