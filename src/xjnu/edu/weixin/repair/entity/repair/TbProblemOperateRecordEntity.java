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
 * @Description: 维修记录
 * @author zhangdaihao
 * @date 2014-09-26 21:49:35
 * @version V1.0   
 *
 */
@Entity
@Table(name = "tb_problem_operate_record", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class TbProblemOperateRecordEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**problemId*/
	private java.lang.String problemId;
	/**operateState*/
	private java.lang.String operateState;
	/**operateId*/
	private java.lang.String operateId;
	/**operateTime*/
	private java.util.Date operateTime;
	
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
	 *@return: java.lang.String  problemId
	 */
	@Column(name ="PROBLEM_ID",nullable=true,length=50)
	public java.lang.String getProblemId(){
		return this.problemId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  problemId
	 */
	public void setProblemId(java.lang.String problemId){
		this.problemId = problemId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  operateState
	 */
	@Column(name ="OPERATE_STATE",nullable=true,length=50)
	public java.lang.String getOperateState(){
		return this.operateState;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  operateState
	 */
	public void setOperateState(java.lang.String operateState){
		this.operateState = operateState;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  operateId
	 */
	@Column(name ="OPERATE_ID",nullable=true,length=50)
	public java.lang.String getOperateId(){
		return this.operateId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  operateId
	 */
	public void setOperateId(java.lang.String operateId){
		this.operateId = operateId;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  operateTime
	 */
	@Column(name ="OPERATE_TIME",nullable=true)
	public java.util.Date getOperateTime(){
		return this.operateTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  operateTime
	 */
	public void setOperateTime(java.util.Date operateTime){
		this.operateTime = operateTime;
	}
}
