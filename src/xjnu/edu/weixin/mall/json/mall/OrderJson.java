package xjnu.edu.weixin.mall.json.mall;
import java.util.Date;
import java.util.List;

import xjnu.edu.weixin.mall.entity.mall.TbMallOrderItemEntity;
//临时类用来加载订单数据
public class OrderJson {
	private String orderid;
	private int orderseriaId;
	private String shopname;
	private String orderstate;
	private Date createtime;
	private List<OrderItmJson> orteritem;
	private float totalcount;
	private float totalprice;

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public int getOrderseriaId() {
		return orderseriaId;
	}

	public void setOrderseriaId(int orderseriaId) {
		this.orderseriaId = orderseriaId;
	}

	public String getShopname() {
		return shopname;
	}

	public void setShopname(String shopname) {
		this.shopname = shopname;
	}

	public String getOrderstate() {
		return orderstate;
	}

	public void setOrderstate(String orderstate) {
		this.orderstate = orderstate;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	


	public List<OrderItmJson> getOrteritem() {
		return orteritem;
	}

	public void setOrteritem(List<OrderItmJson> orteritem) {
		this.orteritem = orteritem;
	}

	public float getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(float totalprice) {
		this.totalprice = totalprice;
	}



	public float getTotalcount() {
		return totalcount;
	}

	public void setTotalcount(float totalcount) {
		this.totalcount = totalcount;
	}

	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}

	public OrderJson(
			String orderid,
			int orderseriaId,
			String shopname,
			String orderstate,
			Date createtime,
			List<OrderItmJson> orderitem,
			float totalcount, float totalprice) {
		super();
		this.orderid = orderid;
		this.orderseriaId = orderseriaId;
		this.shopname = shopname;
		this.orderstate = orderstate;
		this.createtime = createtime;
		orteritem = orderitem;
		this.totalcount = totalcount;
		this.totalprice = totalprice;
	}

}
