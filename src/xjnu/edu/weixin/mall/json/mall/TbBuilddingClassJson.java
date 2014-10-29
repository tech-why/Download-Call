package xjnu.edu.weixin.mall.json.mall;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import xjnu.edu.weixin.mall.entity.mall.TbBuilddingCatalog;
import xjnu.edu.weixin.mall.entity.mall.TbBuilddingClass;

/**
 *  @Title: Entity
 * @Description: 建筑大类管理
 */
@SuppressWarnings("serial")
public class TbBuilddingClassJson implements java.io.Serializable {

	// Fields
	/**主键*/
	private String id;
	/**建筑大类名*/
	private String className;
	/**显示顺序*/
	private Integer displaySequence;
	/**一对多关联*/
	private List<TbBuilddingCatalogJson> tbBuilddingCatalogs ;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public TbBuilddingClassJson(TbBuilddingClass builddingClass) {
		this.id = builddingClass.getId();
		this.className = builddingClass.getClassName();
		this.displaySequence = builddingClass.getDisplaySequence();
	}
	
	public TbBuilddingClassJson(String id, String className,
			Integer displaySequence,
			List<TbBuilddingCatalogJson> tbBuilddingCatalogs) {
		super();
		this.id = id;
		this.className = className;
		this.displaySequence = displaySequence;
		this.tbBuilddingCatalogs = tbBuilddingCatalogs;
	}
	
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public Integer getDisplaySequence() {
		return displaySequence;
	}
	public void setDisplaySequence(Integer displaySequence) {
		this.displaySequence = displaySequence;
	}
	public List<TbBuilddingCatalogJson> getTbBuilddingCatalogs() {
		return tbBuilddingCatalogs;
	}
	public void setTbBuilddingCatalogs(
			List<TbBuilddingCatalog> builddingCatalogList) {
		this.tbBuilddingCatalogs = new ArrayList<TbBuilddingCatalogJson>();
		
		for( TbBuilddingCatalog builddingCatalog : builddingCatalogList)
		{
			TbBuilddingCatalogJson builddingCatalogJson = new TbBuilddingCatalogJson(builddingCatalog);
			this.tbBuilddingCatalogs.add(builddingCatalogJson);
		}
	}
	
	
	
	
}