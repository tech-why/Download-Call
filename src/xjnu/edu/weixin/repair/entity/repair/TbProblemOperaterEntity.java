package xjnu.edu.weixin.repair.entity.repair;

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
 * @Description: 维修人员表
 * @author zhangdaihao
 * @date 2014-09-16 16:51:57
 * @version V1.0   
 *
 */
@Entity
@Table(name = "tb_problem_operater", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class TbProblemOperaterEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**weixinUserId*/
	private java.lang.String weixinUserId;
	/**userName*/
	private java.lang.String userName;
	/**userId*/
	private java.lang.String userId;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  id
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
	 *@param: java.lang.String  id
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  weixinUserId
	 */
	@Column(name ="WEIXIN_USER_ID",nullable=true,length=50)
	public java.lang.String getWeixinUserId(){
		return this.weixinUserId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  weixinUserId
	 */
	public void setWeixinUserId(java.lang.String weixinUserId){
		this.weixinUserId = weixinUserId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  userName
	 */
	@Column(name ="USER_NAME",nullable=true,length=50)
	public java.lang.String getUserName(){
		return this.userName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  userName
	 */
	public void setUserName(java.lang.String userName){
		this.userName = userName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  userId
	 */
	@Column(name ="USER_ID",nullable=true,length=50)
	public java.lang.String getUserId(){
		return this.userId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  userId
	 */
	public void setUserId(java.lang.String userId){
		this.userId = userId;
	}
}
