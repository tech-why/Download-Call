package xjnu.edu.weixin.mall.entity.mall;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * TbMallCart entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tb_mall_cart")
public class TbMallCart implements java.io.Serializable {

	// Fields

	private String id;
	private TbMallProductEntity tbMallProduct;
	private String weixinUserId;
	private Integer count;
	private Timestamp creatTime;
	private String isActive;
	private int state;

	// Constructors
	@Column(name = "state", length = 50)
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	/** default constructor */
	public TbMallCart() {
	}

	/** full constructor */
	public TbMallCart(TbMallProductEntity tbMallProduct,
			String weixinUserId, Integer count, Timestamp creatTime,
			String isActive, int state) {
		this.tbMallProduct = tbMallProduct;
		this.weixinUserId = weixinUserId;
		this.count = count;
		this.creatTime = creatTime;
		this.isActive = isActive;
		this.state = state;
	}
	

	// Property accessors
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name = "id", nullable = false, length = 50)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_item_id")
	public TbMallProductEntity getTbMallProduct() {
		return this.tbMallProduct;
	}

	public void setTbMallProduct(TbMallProductEntity tbMallProduct) {
		this.tbMallProduct = tbMallProduct;
	}

	@Column(name = "weixin_user_id", length = 50)
	public String getWeixinUserId() {
		return this.weixinUserId;
	}

	public void setWeixinUserId(String weixinUserId) {
		this.weixinUserId = weixinUserId;
	}
	
	


	@Column(name = "count")
	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Column(name = "creat_time", length = 19)
	public Timestamp getCreatTime() {
		return this.creatTime;
	}

	public void setCreatTime(Timestamp creatTime) {
		this.creatTime = creatTime;
	}

	@Column(name = "is_active", length = 50)
	public String getIsActive() {
		return this.isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

}