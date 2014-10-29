package xjnu.edu.weixin.repair.entity.repair;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;

/**   
 * @Title: Entity
 * @Description: 维修人员分配表
 * @author zhangdaihao
 * @date 2014-09-16 16:56:20
 * @version V1.0   
 *
 */
@Entity
@Table(name = "tb_operater_buildding", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class TbOperaterBuilddingEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**operateorId*/
	private java.lang.String operateorId;
	/**builddingId*/
	private java.lang.String builddingId;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  id
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
	 *@param: java.lang.String  id
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  operateorId
	 */
	@Column(name ="OPERATEOR_ID",nullable=true,length=50)
	public java.lang.String getOperateorId(){
		return this.operateorId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  operateorId
	 */
	public void setOperateorId(java.lang.String operateorId){
		this.operateorId = operateorId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  builddingId
	 */
	@Column(name ="BUILDDING_ID",nullable=true,length=50)
	public java.lang.String getBuilddingId(){
		return this.builddingId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  builddingId
	 */
	public void setBuilddingId(java.lang.String builddingId){
		this.builddingId = builddingId;
	}
}
