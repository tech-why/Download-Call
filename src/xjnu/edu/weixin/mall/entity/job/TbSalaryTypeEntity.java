package xjnu.edu.weixin.mall.entity.job;

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
 * @Description: 工资类别
 * @author zhangdaihao
 * @date 2014-09-16 12:32:48
 * @version V1.0   
 *
 */
@Entity
@Table(name = "tb_salary_type", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class TbSalaryTypeEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**工资类别名*/
	private java.lang.String salaryTypeName;
	/**显示顺序*/
	private java.lang.Integer displayIndex;
	
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
	 *@return: java.lang.String  工资类别名
	 */
	@Column(name ="SALARY_TYPE_NAME",nullable=true,length=50)
	public java.lang.String getSalaryTypeName(){
		return this.salaryTypeName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  工资类别名
	 */
	public void setSalaryTypeName(java.lang.String salaryTypeName){
		this.salaryTypeName = salaryTypeName;
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
