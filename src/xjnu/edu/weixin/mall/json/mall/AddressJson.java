package xjnu.edu.weixin.mall.json.mall;
//加载地址数据
public class AddressJson {
	private java.lang.String id;
	/** 建筑外键 */
	private java.lang.String builddingname;
	/** 联系人 */
	private java.lang.String contact;
	/** 电话号码 */
	private java.lang.String phoneNumber;
	/** 移动电话 */
	private java.lang.String mobileNumber;
	/** 详细地址 */
	private java.lang.String houseCode;
	/** 用户名外键 */

	/** 是否活动 */
	private java.lang.String isActive;
	/** 是否选中 **/
	private java.lang.String isChoose;
	private java.lang.String schoolname;

	public java.lang.String getSchoolname() {
		return schoolname;
	}

	public void setSchoolname(java.lang.String schoolname) {
		this.schoolname = schoolname;
	}

	public java.lang.String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.lang.String getBuilddingname() {
		return builddingname;
	}

	public void setBuilddingname(java.lang.String builddingname) {
		this.builddingname = builddingname;
	}

	public java.lang.String getContact() {
		return contact;
	}

	public void setContact(java.lang.String contact) {
		this.contact = contact;
	}

	public java.lang.String getPhoneNumber() {
		return phoneNumber;

	}

	public AddressJson(String id, String builddingname, String contact,
			String phoneNumber, String mobileNumber, String houseCode,
			String isActive, String isChoose, String schoolname) {
		super();
		this.id = id;
		this.builddingname = builddingname;
		this.contact = contact;
		this.phoneNumber = phoneNumber;
		this.mobileNumber = mobileNumber;
		this.houseCode = houseCode;
		this.isActive = isActive;
		this.isChoose = isChoose;
		this.schoolname = schoolname;
	}

	public java.lang.String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(java.lang.String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public java.lang.String getHouseCode() {
		return houseCode;
	}

	public void setHouseCode(java.lang.String houseCode) {
		this.houseCode = houseCode;
	}

	public java.lang.String getIsActive() {
		return isActive;
	}

	public void setIsActive(java.lang.String isActive) {
		this.isActive = isActive;
	}

	public java.lang.String getIsChoose() {
		return isChoose;
	}

	public void setIsChoose(java.lang.String isChoose) {
		this.isChoose = isChoose;
	}

	public void setPhoneNumber(java.lang.String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}