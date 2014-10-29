package xjnu.edu.weixin.mall.json.mall;
public class TbMallProductItemJson{
	/**主键*/
	private java.lang.String id;
	/**商品名称*/
	private java.lang.String productName;
	
	/**显示顺序*/
//	private java.lang.Integer displaySequence;
	/**商品描述*//*
	private java.lang.String productDescription;*/	
	
	
	/**商品图片*/
	private java.lang.String productImageUrl;
	/**商铺外键*/
	private java.lang.String shopId;
	
	/**销量*/
	private java.lang.Integer saleCount;
	
	
	/**是否活动*/
//	private java.lang.String isActive;
	/**是否显示*/
//	private java.lang.String isDisplay;
	
	/**剩余数量*/
	private java.lang.Double remainAmount;
	
	/**单位量*/
//	private java.lang.Double unitAmount;
	/**积分系数*/
//	private java.lang.Double scoreCoefficient;
	
	/**单位*/
	private java.lang.String productUnit;
	
	public java.lang.String getProductUnit() {
		return productUnit;
	}
	public void setProductUnit(java.lang.String productUnit) {
		this.productUnit = productUnit;
	}
	public java.lang.Double getRemainAmount() {
		return remainAmount;
	}
	public void setRemainAmount(java.lang.Double remainAmount) {
		this.remainAmount = remainAmount;
	}
	/**微信价*/
	private java.lang.Double productPrice;
	/**参考价*/
	private java.lang.Double normalPrice;
	/**小类外键*/
	private java.lang.String catalogId;
	
	/**关联商铺*/
//	private TbMallShopEntity tbMallShopEntity;
	
	/**商品描述*/
	private String productDescription;
	
	
	public java.lang.String getId() {
		return id;
	}
	public void setId(java.lang.String id) {
		this.id = id;
	}
	public java.lang.String getProductName() {
		return productName;
	}
	public void setProductName(java.lang.String productName) {
		this.productName = productName;
	}
	
	public java.lang.String getProductImageUrl() {
		return productImageUrl;
	}
	public void setProductImageUrl(java.lang.String productImageUrl) {
		this.productImageUrl = productImageUrl;
	}
	public java.lang.String getShopId() {
		return shopId;
	}
	public void setShopId(java.lang.String shopId) {
		this.shopId = shopId;
	}
	public java.lang.Integer getSaleCount() {
		return saleCount;
	}
	public void setSaleCount(java.lang.Integer saleCount) {
		this.saleCount = saleCount;
	}
	
	public java.lang.Double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(java.lang.Double productPrice) {
		this.productPrice = productPrice;
	}
	public java.lang.Double getNormalPrice() {
		return normalPrice;
	}
	public void setNormalPrice(java.lang.Double normalPrice) {
		this.normalPrice = normalPrice;
	}
	public java.lang.String getCatalogId() {
		return catalogId;
	}
	public void setCatalogId(java.lang.String catalogId) {
		this.catalogId = catalogId;
	}
	
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	
	
	
}
