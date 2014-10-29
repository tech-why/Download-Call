package xjnu.edu.weixin.mall.json.mall;
//临时类用来订单显示界面的图片加载
public class OrderItmJson {
	private java.lang.String id;
	/**订单外键*/
	private java.lang.String orderId;
	/**商品名称*/
	private java.lang.String productName;
	/**商品描述*/
	private java.lang.String productDescription;
	/**商品图片*/
	private java.lang.String productImageUrl;
	/**单位*/
	private java.lang.String productUnit;
	/**价格*/
	private java.lang.Float productPrice;
	/**单位量*/
	private java.lang.Double unitAmount;
	/**积分系数*/
	private java.lang.Double scoreCoefficient;
	/**数量*/
	private java.lang.Integer count;
	public java.lang.String getId() {
		return id;
	}
	public void setId(java.lang.String id) {
		this.id = id;
	}
	public java.lang.String getOrderId() {
		return orderId;
	}
	public void setOrderId(java.lang.String orderId) {
		this.orderId = orderId;
	}
	public java.lang.String getProductName() {
		return productName;
	}
	public void setProductName(java.lang.String productName) {
		this.productName = productName;
	}
	public java.lang.String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(java.lang.String productDescription) {
		this.productDescription = productDescription;
	}
	public java.lang.String getProductImageUrl() {
		return productImageUrl;
	}
	public void setProductImageUrl(java.lang.String productImageUrl) {
		this.productImageUrl = productImageUrl;
	}
	public java.lang.String getProductUnit() {
		return productUnit;
	}
	public void setProductUnit(java.lang.String productUnit) {
		this.productUnit = productUnit;
	}
	public java.lang.Float getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(java.lang.Float productPrice) {
		this.productPrice = productPrice;
	}
	public java.lang.Double getUnitAmount() {
		return unitAmount;
	}
	public void setUnitAmount(java.lang.Double unitAmount) {
		this.unitAmount = unitAmount;
	}
	public java.lang.Double getScoreCoefficient() {
		return scoreCoefficient;
	}
	public void setScoreCoefficient(java.lang.Double scoreCoefficient) {
		this.scoreCoefficient = scoreCoefficient;
	}
	public java.lang.Integer getCount() {
		return count;
	}
	public void setCount(java.lang.Integer count) {
		this.count = count;
	}
	public OrderItmJson(String id, String orderId, String productName,
			String productDescription, String productImageUrl,
			String productUnit, Float productPrice, Double unitAmount,
			Double scoreCoefficient, Integer count) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productImageUrl = productImageUrl;
		this.productUnit = productUnit;
		this.productPrice = productPrice;
		this.unitAmount = unitAmount;
		this.scoreCoefficient = scoreCoefficient;
		this.count = count;
	}
	
	
}
