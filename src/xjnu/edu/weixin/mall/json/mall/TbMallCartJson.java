package xjnu.edu.weixin.mall.json.mall;

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

import xjnu.edu.weixin.mall.entity.mall.TbMallCart;

/**
 * TbMallCart entity. @author MyEclipse Persistence Tools
 */

public class TbMallCartJson implements java.io.Serializable {

	// Fields

	private String id;
	private Integer count;
	private java.lang.String productName;
	private java.lang.Integer displaySequence;
	private java.lang.String productImageUrl;
	private java.lang.Double productPrice;
	private int state;

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}



	public void setCount(Integer count) {
		this.count = count;
	}


	public TbMallCartJson(TbMallCart cart) {
		super();
		this.state = cart.getState();
		this.id = cart.getId();
		this.count = cart.getCount();
		this.productName = cart.getTbMallProduct().getProductName();
		this.productImageUrl = cart.getTbMallProduct().getProductImageUrl();
		this.productPrice =  cart.getTbMallProduct().getProductPrice();
	}


	// Constructors

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	

	public Integer getCount() {
		return count;
	}


	
	public java.lang.String getProductName() {
		return productName;
	}


	public void setProductName(java.lang.String productName) {
		this.productName = productName;
	}


	public java.lang.Integer getDisplaySequence() {
		return displaySequence;
	}


	public void setDisplaySequence(java.lang.Integer displaySequence) {
		this.displaySequence = displaySequence;
	}


	public java.lang.String getProductImageUrl() {
		return productImageUrl;
	}


	public void setProductImageUrl(java.lang.String productImageUrl) {
		this.productImageUrl = productImageUrl;
	}


	public java.lang.Double getProductPrice() {
		return productPrice;
	}


	public void setProductPrice(java.lang.Double productPrice) {
		this.productPrice = productPrice;
	}


	/** default constructor */
	public TbMallCartJson() {
	}

	

}