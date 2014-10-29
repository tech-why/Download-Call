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
 * @Description: 大类管理
 * @date 2014-08-05 12:48:09
 * @version V1.0   
 *
 */
@Entity
@Table(name = "tb_mall_product_class", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class TbMallProductClassEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**大类名称*/
	private java.lang.String className;
	/**显示顺序*/
	private java.lang.Integer displaySequence;
	/**大类图片*/
	private java.lang.String classImageUrl;
	
	/**一对多关联*/
	private Set<TbMallProductCatalogEntity> tbMallProductCatalogEntitys = new HashSet<TbMallProductCatalogEntity>(
			0);
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tbMallProductClassEntity")
	public Set<TbMallProductCatalogEntity> getTbMallProductCatalogEntitys() {
		return this.tbMallProductCatalogEntitys;
	}

	public void setTbMallProductCatalogEntitys(
			Set<TbMallProductCatalogEntity> tbMallProductCatalogs) {
		this.tbMallProductCatalogEntitys = tbMallProductCatalogs;
	}
	
	
	/**关联图片*/
	private TbImageEntity tbImageEntity;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "class_Image_url")
	public TbImageEntity getTbImageEntity() {
		return tbImageEntity;
	}

	public void setTbImageEntity(TbImageEntity tbImageEntity) {
		this.tbImageEntity = tbImageEntity;
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
	 *@return: java.lang.String  大类名称
	 */
	@Column(name ="CLASS_NAME",nullable=false,length=50)
	public java.lang.String getClassName(){
		return this.className;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  大类名称
	 */
	public void setClassName(java.lang.String className){
		this.className = className;
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
	 *@return: java.lang.String  大类图片
	 */
	@Column(name ="CLASS_IMAGE_URL",nullable=false,length=250)
	public java.lang.String getClassImageUrl(){
		return this.classImageUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  大类图片
	 */
	public void setClassImageUrl(java.lang.String classImageUrl){
		this.classImageUrl = classImageUrl;
	}
}
