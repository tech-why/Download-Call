package xjnu.edu.weixin.mall.json.mall;
/**   
 * @Title: Entity
 * @Description: 处理建筑管理json临时类
 * @date 2014-08-05 12:47:42
 * @version V1.0   
 *
 */
public class TbBuilddingEntityJson {
	/**主键*/
	private String id;
	/**建筑名称*/
	private String builddingName;
	
	/**描述*/
	private String builddingDescription;
	
	/**联系方式*/
	private String phoneTel;
	
	/**经度*/
	private Double jindu;
	/**纬度*/
	private Double weidu;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBuilddingName() {
		return builddingName;
	}
	public void setBuilddingName(String builddingName) {
		this.builddingName = builddingName;
	}
	
	public String getBuilddingDescription() {
		return builddingDescription;
	}
	public void setBuilddingDescription(String builddingDescription) {
		this.builddingDescription = builddingDescription;
	}
	
	public String getPhoneTel() {
		return phoneTel;
	}
	public void setPhoneTel(String phoneTel) {
		this.phoneTel = phoneTel;
	}
	
	public Double getJindu() {
		return jindu;
	}
	public void setJindu(Double jindu) {
		this.jindu = jindu;
	}
	public Double getWeidu() {
		return weidu;
	}
	public void setWeidu(Double weidu) {
		this.weidu = weidu;
	}
	
	
}
