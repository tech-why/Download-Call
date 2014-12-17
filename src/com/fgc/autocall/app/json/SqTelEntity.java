package com.fgc.autocall.app.json;

import java.math.BigDecimal;
import java.util.Date;


public class SqTelEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**电话*/
	private java.lang.String telephone;
	private java.lang.String telename;
	
	public java.lang.String getTelename() {
		return telename;
	}

	public void setTelename(java.lang.String telename) {
		this.telename = telename;
	}

	
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  id
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  电话
	 */
	
	public java.lang.String getTelephone(){
		return this.telephone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  电话
	 */
	public void setTelephone(java.lang.String telephone){
		this.telephone = telephone;
	}
}
