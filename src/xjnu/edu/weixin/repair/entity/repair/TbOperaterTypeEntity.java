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
 * @Description: 操作类型
 * @author zhangdaihao
 * @date 2014-09-24 11:32:51
 * @version V1.0   
 *
 */
@Entity
@Table(name = "tb_operater_type", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class TbOperaterTypeEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**操作类型*/
	private java.lang.String operateType;
	
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
	 *@return: java.lang.String  操作类型
	 */
	@Column(name ="OPERATE_TYPE",nullable=true,length=50)
	public java.lang.String getOperateType(){
		return this.operateType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  操作类型
	 */
	public void setOperateType(java.lang.String operateType){
		this.operateType = operateType;
	}
}
