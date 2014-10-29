package xjnu.edu.weixin.mall.entity.job;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;

/**   
 * @Title: Entity
 * @Description: 区域大类表
 * @author zhangdaihao
 * @date 2014-09-16 12:31:14
 * @version V1.0   
 *
 */
@Entity
@Table(name = "tb_area_class", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class TbAreaClassEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**大类名*/
	private java.lang.String className;
	/**显示顺序*/
	private java.lang.Integer displayIndex;
	
	/**一对多关联  关联小类*/
	private Set<TbAreaCatalogEntity> tbAreaCatalogEntitys = new HashSet<TbAreaCatalogEntity>(
			0);
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tbAreaClassEntity")
	public Set<TbAreaCatalogEntity> getTbAreaCatalogEntitys() {
		return this.tbAreaCatalogEntitys;
	}

	public void setTbAreaCatalogEntitys(
			Set<TbAreaCatalogEntity> tbAreaCatalogEntitys) {
		this.tbAreaCatalogEntitys = tbAreaCatalogEntitys;
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
	 *@return: java.lang.String  大类名
	 */
	@Column(name ="CLASS_NAME",nullable=true,length=50)
	public java.lang.String getClassName(){
		return this.className;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  大类名
	 */
	public void setClassName(java.lang.String className){
		this.className = className;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  显示顺序
	 */
	@Column(name ="DISPLAY_INDEX",nullable=true,precision=10,scale=0)
	public java.lang.Integer getDisplayIndex(){
		return this.displayIndex;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  显示顺序
	 */
	public void setDisplayIndex(java.lang.Integer displayIndex){
		this.displayIndex = displayIndex;
	}
}
