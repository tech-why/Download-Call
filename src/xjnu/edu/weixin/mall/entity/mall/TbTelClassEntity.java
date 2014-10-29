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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;

/**   
 * @Title: Entity
 * @Description: 号码大类
 * @author zhangdaihao
 * @date 2014-08-25 18:08:51
 * @version V1.0   
 *
 */
@Entity
@Table(name = "tb_tel_class", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class TbTelClassEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**号码大类名称*/
	private java.lang.String telClassName;
	/**显示顺序*/
	private java.lang.Integer displaySequence;
	
	/**一对多关联*/
	private Set<TbTelCatalogEntity> tbTelCatalogEntitys = new HashSet<TbTelCatalogEntity>(
			0);
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tbTelClassEntity")
	public Set<TbTelCatalogEntity> getTbTelCatalogEntitys() {
		return this.tbTelCatalogEntitys;
	}

	public void setTbTelCatalogEntitys(
			Set<TbTelCatalogEntity> tbTelCatalogEntitys) {
		this.tbTelCatalogEntitys = tbTelCatalogEntitys;
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
	 *@return: java.lang.String  号码大类名称
	 */
	@Column(name ="TEL_CLASS_NAME",nullable=true,length=50)
	public java.lang.String getTelClassName(){
		return this.telClassName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  号码大类名称
	 */
	public void setTelClassName(java.lang.String telClassName){
		this.telClassName = telClassName;
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
	
}
