package xjnu.edu.weixin.mall.json.mall;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import xjnu.edu.weixin.mall.entity.mall.TbBuilddingCatalog;

@SuppressWarnings("serial")
public class TbBuilddingCatalogJson implements java.io.Serializable {

	// Fields
	/**主键*/
	private String id;
	/**建筑小类名*/
	private String catalogName;
	/**显示顺序*/
	private Integer displaySequence;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCatalogName() {
		return catalogName;
	}
	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}
	public Integer getDisplaySequence() {
		return displaySequence;
	}
	public void setDisplaySequence(Integer displaySequence) {
		this.displaySequence = displaySequence;
	}
	
	
	public TbBuilddingCatalogJson(String id, String catalogName,
			Integer displaySequence) {
		super();
		this.id = id;
		this.catalogName = catalogName;
		this.displaySequence = displaySequence;
	}
	
	
	public TbBuilddingCatalogJson(TbBuilddingCatalog builddingCatalog) {
		this.id = builddingCatalog.getId();
		this.catalogName = builddingCatalog.getCatalogName();
		this.displaySequence = builddingCatalog.getDisplaySequence();
	}
	
	
	
	
	

}