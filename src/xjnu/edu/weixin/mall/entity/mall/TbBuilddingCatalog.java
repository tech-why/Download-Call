package xjnu.edu.weixin.mall.entity.mall;

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

/**
 * @Title: Entity
 * @Description: 建筑小类管理
 */
@Entity
@Table(name = "tb_buildding_catalog")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class TbBuilddingCatalog implements java.io.Serializable {

	// Fields
	/**主键*/
	private String id;
	private TbBuilddingClass tbBuilddingClass;
	/**建筑小类名*/
	private String catalogName;
	/**显示顺序*/
	private Integer displaySequence;
	
	private Set<TbBuilddingEntity> builddingEntitySet ;
	
	
	
	@OneToMany(fetch = FetchType.LAZY , targetEntity = TbBuilddingEntity.class,cascade = CascadeType.ALL)
	@JoinColumns(value={@JoinColumn(name="catalog_id",referencedColumnName="id")})   //对应关系 Pid = id
	public Set<TbBuilddingEntity> getBuilddingEntitySet() {
		return builddingEntitySet;
	}

	public void setBuilddingEntitySet(Set<TbBuilddingEntity> builddingEntitySet) {
		this.builddingEntitySet = builddingEntitySet;
	}


	

	// Constructors

	/** default constructor */
	public TbBuilddingCatalog() {
	}

	/** minimal constructor */
	public TbBuilddingCatalog(String id) {
		this.id = id;
	}

	/** full constructor */
	public TbBuilddingCatalog(String id, TbBuilddingClass tbBuilddingClass,
			String catalogName, Integer displaySequence) {
		this.id = id;
		this.tbBuilddingClass = tbBuilddingClass;
		this.catalogName = catalogName;
		this.displaySequence = displaySequence;
	}

	// Property accessors

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name = "id", nullable = false, length = 50)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
	/**建筑大类关联*/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "class_id")
	public TbBuilddingClass getTbBuilddingClass() {
		return this.tbBuilddingClass;
	}

	public void setTbBuilddingClass(TbBuilddingClass tbBuilddingClass) {
		this.tbBuilddingClass = tbBuilddingClass;
	}

	@Column(name = "catalog_name", length = 50)
	public String getCatalogName() {
		return this.catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

	@Column(name = "display_sequence")
	public Integer getDisplaySequence() {
		return this.displaySequence;
	}

	public void setDisplaySequence(Integer displaySequence) {
		this.displaySequence = displaySequence;
	}

}