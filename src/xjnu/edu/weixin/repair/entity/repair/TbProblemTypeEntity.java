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
 * @Description: 故障类型表
 * @author zhangdaihao
 * @date 2014-09-16 16:57:33
 * @version V1.0   
 *
 */
@Entity
@Table(name = "tb_problem_type", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class TbProblemTypeEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**typeName*/
	private java.lang.String typeName;
	/**displaysequence*/
	private java.lang.String displaysequence;
	
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
	 *@return: java.lang.String  typeName
	 */
	@Column(name ="TYPE_NAME",nullable=true,length=50)
	public java.lang.String getTypeName(){
		return this.typeName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  typeName
	 */
	public void setTypeName(java.lang.String typeName){
		this.typeName = typeName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  displaysequence
	 */
	@Column(name ="DISPLAYSEQUENCE",nullable=true,length=10)
	public java.lang.String getDisplaysequence(){
		return this.displaysequence;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  displaysequence
	 */
	public void setDisplaysequence(java.lang.String displaysequence){
		this.displaysequence = displaysequence;
	}
}
