package xjnu.edu.weixin.mall.page.mall;

import xjnu.edu.weixin.mall.entity.mall.TbMallProductEntity;

public class CartPage {
	
	public CartPage() {
	}
	
	public CartPage(String id, int shopState, Integer count) {
		super();
		this.id = id;
		this.shopState = shopState;
		this.count = count;
	}
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getShopState() {
		return shopState;
	}
	public void setShopState(int shopState) {
		this.shopState = shopState;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	private int shopState;
	private Integer count;

}
