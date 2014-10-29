package xjnu.edu.weixin.mall.entity.job;

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
 * @Description: 区域小类表
 * @author zhangdaihao
 * @date 2014-09-16 12:31:39
 * @version V1.0   
 *
 */
@Entity
@Table(name = "tb_area_catalog", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class TbAreaCatalogEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**小类名*/
	private java.lang.String catalogName;
	/**显示顺序*/
	private java.lang.Integer displayIndex;
	/**大类外键*/
	private java.lang.String classId;
	
	/**关联大类*/
	private TbAreaClassEntity tbAreaClassEntity;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "class_id")
	public TbAreaClassEntity getTbAreaClassEntity() {
		return tbAreaClassEntity;
	}
	public void setTbAreaClassEntity(
			TbAreaClassEntity tbAreaClassEntity) {
		this.tbAreaClassEntity = tbAreaClassEntity;
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
	 *@return: java.lang.String  小类名
	 */
	@Column(name ="CATALOG_NAME",nullable=true,length=50)
	public java.lang.String getCatalogName(){
		return this.catalogName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  小类名
	 */
	public void setCatalogName(java.lang.String catalogName){
		this.catalogName = catalogName;
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
