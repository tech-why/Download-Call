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
 * @Description: 号码小类
 * @author zhangdaihao
 * @date 2014-08-25 18:10:12
 * @version V1.0   
 *
 */
@Entity
@Table(name = "tb_tel_catalog", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class TbTelCatalogEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**号码小类名称*/
	private java.lang.String telCatalogName;
	/**号码大类外键*/
	private java.lang.String classId;
	/**显示顺序*/
	private java.lang.Integer displaySequence;
	
	
	/**关联大类*/
	private TbTelClassEntity tbTelClassEntity;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "class_id")
	public TbTelClassEntity getTbTelClassEntity() {
		return tbTelClassEntity;
	}

	public void setTbTelClassEntity(TbTelClassEntity tbTelClassEntity) {
		this.tbTelClassEntity = tbTelClassEntity;
	}
	
	/**一对多关联*/
	private Set<TbTelItemEntity> tbTelItemEntitys = new HashSet<TbTelItemEntity>(
			0);
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tbTelCatalogEntity")
	public Set<TbTelItemEntity> getTbTelItemEntitys() {
		return this.tbTelItemEntitys;
	}

	public void setTbTelItemEntitys(
			Set<TbTelItemEntity> tbTelItemEntitys) {
		this.tbTelItemEntitys = tbTelItemEntitys;
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
	 *@return: java.lang.String  号码小类名称
	 */
	@Column(name ="TEL_CATALOG_NAME",nullable=true,length=50)
	public java.lang.String getTelCatalogName(){
		return this.telCatalogName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  号码小类名称
	 */
	public void setTelCatalogName(java.lang.String telCatalogName){
		this.telCatalogName = telCatalogName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  号码大类外键
	 */
	@Column(name ="CLASS_ID",nullable=true,length=50)
	public java.lang.String getClassId(){
		return this.classId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  号码大类外键
	 */
	public void setClassId(java.lang.String classId){
		this.classId = classId;
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
