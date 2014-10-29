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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.core.common.entity.IdEntity;

import javax.persistence.SequenceGenerator;

/**   
 * @Title: Entity
 * @Description: 学校管理
 * @date 2014-08-05 12:46:04
 * @version V1.0   
 *
 */
@Entity
@Table(name = "tb_school", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class TbSchoolEntity extends IdEntity implements java.io.Serializable  {
	/**主键*/
//	private java.lang.String id;
	/**学校名称*/
	private java.lang.String schoolName;
	/**学校描述*/
	private java.lang.String schoolDescription;
	/**显示顺序*/
	private java.lang.Integer displaySequence;
	
//	/**一对多关联*/
//	private Set<TbMallShopSchoolEntity> tbMallShopSchoolEntitys = new HashSet<TbMallShopSchoolEntity>(
//			0);
//	
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tbSchoolEntity")
//	public Set<TbMallShopSchoolEntity> getTbMallShopSchoolEntitys() {
//		return this.tbMallShopSchoolEntitys;
//	}
//
//	public void setTbMallShopSchoolEntitys(
//			Set<TbMallShopSchoolEntity> tbMallSchoolEntitys) {
//		this.tbMallShopSchoolEntitys = tbMallSchoolEntitys;
//	}
	
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	
	/*@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=50)
	public java.lang.String getId(){
		return this.id;
	}

	*//**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主键
	 *//*
	public void setId(java.lang.String id){
		this.id = id;
	}*/
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  学校名称
	 */
	
	@Column(name ="SCHOOL_NAME",nullable=false,length=50,unique = true)
	public java.lang.String getSchoolName(){
		return this.schoolName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  学校名称
	 */
	public void setSchoolName(java.lang.String schoolName){
		this.schoolName = schoolName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  学校描述
	 */
	@Column(name ="SCHOOL_DESCRIPTION",nullable=true,length=250)
	public java.lang.String getSchoolDescription(){
		return this.schoolDescription;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  学校描述
	 */
	public void setSchoolDescription(java.lang.String schoolDescription){
		this.schoolDescription = schoolDescription;
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
