package com.fgc.autocall.app.json;

import java.math.BigDecimal;
import java.util.Date;
public class SqDownloadEntity implements java.io.Serializable {
	public java.lang.String getId() {
		return id;
	}
	public void setId(java.lang.String id) {
		this.id = id;
	}
	public java.lang.String getUrl() {
		return url;
	}
	public void setUrl(java.lang.String url) {
		this.url = url;
	}
	public java.lang.String getName() {
		return name;
	}
	public void setName(java.lang.String name) {
		this.name = name;
	}
	/**id*/
	private java.lang.String id;
	/**地址*/
	private java.lang.String url;
	/**名字*/
    private java.lang.String name;
}
