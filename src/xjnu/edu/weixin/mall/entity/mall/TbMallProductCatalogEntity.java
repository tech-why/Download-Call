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
 * @Description: 小类管理
 * @date 2014-08-05 12:48:41
 * @version V1.0   
 *
 */
@Entity
@Table(name = "tb_mall_product_catalog", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class TbMallProductCatalogEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**小类名称*/
	private java.lang.String catalogName;
	/**显示顺序*/
	private java.lang.Integer displaySequence;
	/**类别图片*/
	private java.lang.String catalogImageUrl;
	/**大类外键*/
	private java.lang.String classId;
	
	/**关联大类*/
	private TbMallProductClassEntity tbMallProductClassEntity;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "class_id")
	public TbMallProductClassEntity getTbMallProductClassEntity() {
		return tbMallProductClassEntity;
	}
	public void setTbMallProductClassEntity(
			TbMallProductClassEntity tbMallProductClassEntity) {
		this.tbMallProductClassEntity = tbMallProductClassEntity;
	}
	
	
	
	/**一对多关联*/
	private Set<TbMallProductEntity> tbMallProductEntitys = new HashSet<TbMallProductEntity>(
			0);
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tbmallProductCatalogEntity")
	public Set<TbMallProductEntity> getTbMallProductEntitys() {
		return this.tbMallProductEntitys;
	}

	public void setTbMallProductEntitys(
			Set<TbMallProductEntity> tbMallProductEntitys) {
		this.tbMallProductEntitys = tbMallProductEntitys;
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
	 *@return: java.lang.String  小类名称
	 */
	@Column(name ="CATALOG_NAME",nullable=false,length=50)
	public java.lang.String getCatalogName(){
		return this.catalogName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  小类名称
	 */
	public void setCatalogName(java.lang.String catalogName){
		this.catalogName = catalogName;
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
	 *@return: java.lang.String  类别图片
	 */
	@Column(name ="CATALOG_IMAGE_URL",nullable=true,length=250)
	public java.lang.String getCatalogImageUrl(){
		return this.catalogImageUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  类别图片
	 */
	public void setCatalogImageUrl(java.lang.String catalogImageUrl){
		this.catalogImageUrl = catalogImageUrl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  大类外键
	 */
	@Column(name ="CLASS_ID",nullable=true,length=50)
	public java.lang.String getClassId(){
		return this.classId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  大类外键
	 */
	public void setClassId(java.lang.String classId){
		this.classId = classId;
	}
}
