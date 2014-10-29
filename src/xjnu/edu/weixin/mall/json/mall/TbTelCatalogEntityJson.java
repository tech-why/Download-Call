package xjnu.edu.weixin.mall.json.mall;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import xjnu.edu.weixin.mall.entity.mall.TbTelCatalogEntity;

import javax.persistence.SequenceGenerator;
public class TbTelCatalogEntityJson {
	/**主键*/
	private java.lang.String id;
	/**号码小类名称*/
	private java.lang.String telCatalogName;
	/**号码大类外键*/
	private java.lang.String classId;
	/**显示顺序*/
	private java.lang.Integer displaySequence;
	
	
	
	
	public TbTelCatalogEntityJson(TbTelCatalogEntity tbTelCatalogEntity) {
		super();
		this.id = tbTelCatalogEntity.getId();
		this.telCatalogName = tbTelCatalogEntity.getTelCatalogName();
		this.classId = tbTelCatalogEntity.getClassId();
		this.displaySequence = tbTelCatalogEntity.getDisplaySequence();
	}
	public java.lang.String getId() {
		return id;
	}
	public void setId(java.lang.String id) {
		this.id = id;
	}
	public java.lang.String getTelCatalogName() {
		return telCatalogName;
	}
	public void setTelCatalogName(java.lang.String telCatalogName) {
		this.telCatalogName = telCatalogName;
	}
	public java.lang.String getClassId() {
		return classId;
	}
	public void setClassId(java.lang.String classId) {
		this.classId = classId;
	}
	public java.lang.Integer getDisplaySequence() {
		return displaySequence;
	}
	public void setDisplaySequence(java.lang.Integer displaySequence) {
		this.displaySequence = displaySequence;
	}
	
}
