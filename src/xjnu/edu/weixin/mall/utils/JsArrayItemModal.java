package xjnu.edu.weixin.mall.utils;

public class JsArrayItemModal {
	
	
	private String idField;
	private String textField;
	private String childField;		// 子节点
	private String nameField;		//地址字段
	
	
	
	public JsArrayItemModal(String idField, String textField,
			String childField, String nameField) {
		super();
		this.idField = idField;
		this.textField = textField;
		this.childField = childField;
		this.nameField = nameField;
	}
	
	
	public String getIdField() {
		return idField;
	}
	public void setIdField(String idField) {
		this.idField = idField;
	}
	public String getTextField() {
		return textField;
	}
	public void setTextField(String textField) {
		this.textField = textField;
	}
	public String getChildField() {
		return childField;
	}
	public void setChildField(String childField) {
		this.childField = childField;
	}
	public String getNameField() {
		return nameField;
	}
	public void setNameField(String nameField) {
		this.nameField = nameField;
	}
	
	

}
