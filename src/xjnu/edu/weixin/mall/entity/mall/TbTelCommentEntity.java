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
 * @Description: 号码评论
 * @author zhangdaihao
 * @date 2014-08-25 18:12:22
 * @version V1.0   
 *
 */
@Entity
@Table(name = "tb_tel_comment", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class TbTelCommentEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**评论内容*/
	private java.lang.String content;
	/**号码外键*/
	private java.lang.String itemId;
	/**显示顺序*/
	private java.lang.Integer displaySequence;
	/**评论时间*/
	private java.util.Date commentDate;
	/**开放id*/
	private java.lang.String openuserid;
	
	
	/**关联号码详情*/
	private TbTelItemEntity tbTelItemEntity;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id")
	public TbTelItemEntity getTbTelItemEntity() {
		return tbTelItemEntity;
	}

	public void setTbTelItemEntity(TbTelItemEntity tbTelItemEntity) {
		this.tbTelItemEntity = tbTelItemEntity;
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
	 *@return: java.lang.String  评论内容
	 */
	@Column(name ="CONTENT",nullable=true,length=200)
	public java.lang.String getContent(){
		return this.content;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  评论内容
	 */
	public void setContent(java.lang.String content){
		this.content = content;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  号码外键
	 */
	@Column(name ="ITEM_ID",nullable=true,length=50)
	public java.lang.String getItemId(){
		return this.itemId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  号码外键
	 */
	public void setItemId(java.lang.String itemId){
		this.itemId = itemId;
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
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  评论时间
	 */
	@Column(name ="COMMENT_DATE",nullable=true)
	public java.util.Date getCommentDate(){
		return this.commentDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  评论时间
	 */
	public void setCommentDate(java.util.Date commentDate){
		this.commentDate = commentDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  开放id
	 */
	@Column(name ="OPENUSERID",nullable=true,length=50)
	public java.lang.String getOpenuserid(){
		return this.openuserid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  开放id
	 */
	public void setOpenuserid(java.lang.String openuserid){
		this.openuserid = openuserid;
	}
}
