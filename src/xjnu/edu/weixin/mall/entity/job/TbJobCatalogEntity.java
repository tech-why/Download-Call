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

import xjnu.edu.weixin.mall.entity.mall.TbMallProductClassEntity;

import javax.persistence.SequenceGenerator;

/**   
 * @Title: Entity
 * @Description: 工作小类
 * @author zhangdaihao
 * @date 2014-09-16 12:28:22
 * @version V1.0   
 *
 */
@Entity
@Table(name = "tb_job_catalog", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class TbJobCatalogEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**小类名称*/
	private java.lang.String jobCatalogName;
	/**显示顺序*/
	private java.lang.Integer displayIndex;
	/**大类外键*/
	private java.lang.String jobClassId;
	
	
	/**关联大类*/
	private TbJobClassEntity tbJobClassEntity;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "job_class_id")
	public TbJobClassEntity getTbJobClassEntity() {
		return tbJobClassEntity;
	}
	public void setTbJobClassEntity(
			TbJobClassEntity tbJobClassEntity) {
		this.tbJobClassEntity = tbJobClassEntity;
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
	@Column(name ="JOB_CATALOG_NAME",nullable=true,length=50)
	public java.lang.String getJobCatalogName(){
		return this.jobCatalogName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  小类名称
	 */
	public void setJobCatalogName(java.lang.String jobCatalogName){
		this.jobCatalogName = jobCatalogName;
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
	@Column(name ="JOB_CLASS_ID",nullable=true,length=50)
	public java.lang.String getJobClassId(){
		return this.jobClassId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  大类外键
	 */
	public void setJobClassId(java.lang.String jobClassId){
		this.jobClassId = jobClassId;
	}
}
