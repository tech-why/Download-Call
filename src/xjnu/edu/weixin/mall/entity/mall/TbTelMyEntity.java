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
 * @Description: 我的号码
 * @author zhangdaihao
 * @date 2014-08-25 18:13:10
 * @version V1.0   
 *
 */
@Entity
@Table(name = "tb_tel_my", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class TbTelMyEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**号码外键*/
	private java.lang.String itemId;
	
	
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
}
