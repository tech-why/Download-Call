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
import javax.persistence.SequenceGenerator;

/**   
 * @Title: Entity
 * @Description: 图片管理
 * @author zhangdaihao
 * @date 2014-08-11 20:58:43
 * @version V1.0   
 *
 */
@Entity
@Table(name = "tb_image", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class TbImageEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**imageKey*/
	private java.lang.String imageKey;
	/**imageName*/
	private java.lang.String imageName;
	
	
	
	/**一对多关联*/
	private Set<TbMallProductClassEntity> tbMallProductClassEntitys = new HashSet<TbMallProductClassEntity>(
			0);
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tbImageEntity")
	public Set<TbMallProductClassEntity> getTbMallProductClassEntitys() {
		return this.tbMallProductClassEntitys;
	}

	public void setTbMallProductClassEntitys(
			Set<TbMallProductClassEntity> tbMallProductClassEntitys) {
		this.tbMallProductClassEntitys = tbMallProductClassEntitys;
	
	}
	
	
	
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
	 *@return: java.lang.String  imageKey
	 */
	@Column(name ="IMAGE_KEY",nullable=true,length=200)
	public java.lang.String getImageKey(){
		return this.imageKey;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  imageKey
	 */
	public void setImageKey(java.lang.String imageKey){
		this.imageKey = imageKey;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  imageName
	 */
	@Column(name ="IMAGE_NAME",nullable=true,length=50)
	public java.lang.String getImageName(){
		return this.imageName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  imageName
	 */
	public void setImageName(java.lang.String imageName){
		this.imageName = imageName;
	}
}
