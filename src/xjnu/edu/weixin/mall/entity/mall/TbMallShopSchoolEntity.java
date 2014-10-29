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
import org.jeecgframework.core.common.entity.IdEntity;

import javax.persistence.SequenceGenerator;

/**   
 * @Title: Entity
 * @Description: 分配学校
 * @date 2014-08-05 12:50:07
 * @version V1.0   
 *
 */
@Entity
@Table(name = "tb_mall_shop_school", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class TbMallShopSchoolEntity extends IdEntity implements java.io.Serializable  {
	/**主键*/
//	private java.lang.String id;
	/**商铺外键*/
	private java.lang.String shopId;
	/**学校外键*/
	private java.lang.String schoolId;
	
	
	/**关联商铺*/               
	private TbMallShopEntity tbMallShopEntity;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "shop_id")
	public TbMallShopEntity getTbMallShopEntity() {
		return tbMallShopEntity;
	}

	public void setTbMallShopEntity(TbMallShopEntity tbMallShopEntity) {
		this.tbMallShopEntity = tbMallShopEntity;
	}
	/**关联学校*/
	private TbSchoolEntity tbSchoolEntity;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "school_id")
	public TbSchoolEntity getTbSchoolEntity() {
		return tbSchoolEntity;
	}

	public void setTbSchoolEntity(TbSchoolEntity tbSchoolEntity) {
		this.tbSchoolEntity = tbSchoolEntity;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	
//	@Id
//	@GeneratedValue(generator = "paymentableGenerator")
//	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
//	@Column(name ="ID",nullable=false,length=50)
//	public java.lang.String getId(){
//		return this.id;
//	}
//
//	
//
//	
//
//	/**
//	 *方法: 设置java.lang.String
//	 *@param: java.lang.String  主键
//	 */
//	public void setId(java.lang.String id){
//		this.id = id;
//	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  商铺外键
	 */
	@Column(name ="SHOP_ID",nullable=false,length=50)
	public java.lang.String getShopId(){
		return this.shopId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  商铺外键
	 */
	public void setShopId(java.lang.String shopId){
		this.shopId = shopId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  学校外键
	 */
	@Column(name ="SCHOOL_ID",nullable=false,length=50)
	public java.lang.String getSchoolId(){
		return this.schoolId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  学校外键
	 */
	public void setSchoolId(java.lang.String schoolId){
		this.schoolId = schoolId;
	}
}
