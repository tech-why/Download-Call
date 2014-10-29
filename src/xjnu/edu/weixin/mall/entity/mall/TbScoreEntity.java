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
 * @Description: 积分管理
 * @author zhangdaihao
 * @date 2014-08-11 16:47:06
 * @version V1.0   
 *
 */
@Entity
@Table(name = "tb_score", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class TbScoreEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**用户外键*/
	private java.lang.String weixinUserId;
	/**创建时间*/
	private java.util.Date createTime;
	/**积分*/
	private java.lang.Integer score;
	/**积分原因*/
	private java.lang.String scoreReason;
	
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
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建时间
	 */
	@Column(name ="CREATE_TIME",nullable=true)
	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建时间
	 */
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  积分
	 */
	@Column(name ="SCORE",nullable=true,precision=10,scale=0)
	public java.lang.Integer getScore(){
		return this.score;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  积分
	 */
	public void setScore(java.lang.Integer score){
		this.score = score;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  积分原因
	 */
	@Column(name ="SCORE_REASON",nullable=true,length=50)
	public java.lang.String getScoreReason(){
		return this.scoreReason;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  积分原因
	 */
	public void setScoreReason(java.lang.String scoreReason){
		this.scoreReason = scoreReason;
	}
}
