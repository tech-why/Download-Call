package xjnu.edu.weixin.mall.json.mall;
import xjnu.edu.weixin.mall.entity.mall.TbMallProductEntity;
public class TbMallProductEntityJson {
	/**主键*/
	private java.lang.String id;
	/**商品名称*/
	private java.lang.String productName;
	/**显示顺序*/
	private java.lang.Integer displaySequence;
	
	/**商品图片*/
	private java.lang.String productImageUrl;
	/**商铺外键*/
	private java.lang.String shopId;
	/**销量*/
	private java.lang.Integer saleCount;
	/**是否活动*/
	private java.lang.String isActive;
	/**是否显示*/
	private java.lang.String isDisplay;
	/**剩余数量*/
	private java.lang.Double remainAmount;
	/**单位量*/
	private java.lang.Double unitAmount;
	/**积分系数*/
	private java.lang.Double scoreCoefficient;
	/**单位*/
	private java.lang.String productUnit;
	/**单价*/
	private java.lang.Double productPrice;
	/**参考价*/
	private java.lang.Double normalPrice;
	
	public java.lang.Double getNormalPrice() {
		return normalPrice;
	}

	public void setNormalPrice(java.lang.Double normalPrice) {
		this.normalPrice = normalPrice;
	}

	/**小类外键*/
	private java.lang.String catalogId;
	
	
	
	
	
	public TbMallProductEntityJson(TbMallProductEntity product) {
		super();
		this.id = product.getId();
		this.productName = product.getProductName();
		this.displaySequence = product.getDisplaySequence();
		this.productImageUrl = product.getProductImageUrl();
		this.shopId = product.getShopId();
		this.saleCount = product.getSaleCount();
		this.isActive = product.getIsActive();
		this.isDisplay = product.getIsDisplay();
		this.remainAmount = product.getRemainAmount();
		this.unitAmount = product.getUnitAmount();
		this.scoreCoefficient = product.getScoreCoefficient();
		this.productUnit = product.getProductUnit();
		this.productPrice = product.getProductPrice();
		this.normalPrice = product.getNormalPrice();
		this.catalogId = product.getCatalogId();
		this.productDescription = product.getProductDescription();
	}

	public TbMallProductEntityJson() {
		// TODO Auto-generated constructor stub
	}

	/**商品描述*/
	private String productDescription;
	
	public String getProductDescription() {
		return this.productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

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

	public java.lang.String getIsActive() {
		return isActive;
	}

	public void setIsActive(java.lang.String isActive) {
		this.isActive = isActive;
	}

	public java.lang.String getIsDisplay() {
		return isDisplay;
	}

	public void setIsDisplay(java.lang.String isDisplay) {
		this.isDisplay = isDisplay;
	}

	public java.lang.Double getRemainAmount() {
		return remainAmount;
	}

	public void setRemainAmount(java.lang.Double remainAmount) {
		this.remainAmount = remainAmount;
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

	public java.lang.String getProductUnit() {
		return productUnit;
	}

	public void setProductUnit(java.lang.String productUnit) {
		this.productUnit = productUnit;
	}

	public java.lang.Double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(java.lang.Double productPrice) {
		this.productPrice = productPrice;
	}

	public java.lang.String getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(java.lang.String catalogId) {
		this.catalogId = catalogId;
	}
	
	
}
