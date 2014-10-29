package xjnu.edu.weixin.mall.entity.mall;

import java.util.HashSet;
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

/**
 *  @Title: Entity
 * @Description: 建筑大类管理
 */
@Entity
@Table(name = "tb_buildding_class")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class TbBuilddingClass implements java.io.Serializable {

	// Fields
	/**主键*/
	private String id;
	/**建筑大类名*/
	private String className;
	/**显示顺序*/
	private Integer displaySequence;
	/**一对多关联*/
	private Set<TbBuilddingCatalog> tbBuilddingCatalogs = new HashSet<TbBuilddingCatalog>(
			0);
	// Constructors

	/** default constructor */
	public TbBuilddingClass() {
	}

	/** minimal constructor */
	public TbBuilddingClass(String id) {
		this.id = id;
	}

	/** full constructor */
	public TbBuilddingClass(String id, String className,
			Integer displaySequence, Set<TbBuilddingCatalog> tbBuilddingCatalogs) {
		this.id = id;
		this.className = className;
		this.displaySequence = displaySequence;
		this.tbBuilddingCatalogs = tbBuilddingCatalogs;
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

	@Column(name = "class_name", length = 50)
	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@Column(name = "display_sequence")
	public Integer getDisplaySequence() {
		return this.displaySequence;
	}

	public void setDisplaySequence(Integer displaySequence) {
		this.displaySequence = displaySequence;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tbBuilddingClass")
	public Set<TbBuilddingCatalog> getTbBuilddingCatalogs() {
		return this.tbBuilddingCatalogs;
	}

	public void setTbBuilddingCatalogs(
			Set<TbBuilddingCatalog> tbBuilddingCatalogs) {
		this.tbBuilddingCatalogs = tbBuilddingCatalogs;
	}

}