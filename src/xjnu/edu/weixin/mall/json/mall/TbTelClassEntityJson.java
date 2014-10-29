package xjnu.edu.weixin.mall.json.mall;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
import xjnu.edu.weixin.mall.entity.mall.TbTelClassEntity;

import javax.persistence.SequenceGenerator;

/**   
 * @Title: Entity
 * @Description: 号码大类
 * @author zhangdaihao
 * @date 2014-08-25 18:08:51
 * @version V1.0   
 *
 */
@Entity
@Table(name = "tb_tel_class", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class TbTelClassEntityJson implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**号码大类名称*/
	private java.lang.String telClassName;
	/**显示顺序*/
	private java.lang.Integer displaySequence;
	private List<TbTelCatalogEntityJson> tbTelCatalogEntityJson;
	
	public List<TbTelCatalogEntityJson> getTbTelCatalogEntityJson() {
		return tbTelCatalogEntityJson;
	}
	public void setTbTelCatalogEntityJson(
			List<TbTelCatalogEntity> tbTelCatalogEntityList) {
		this.tbTelCatalogEntityJson = new ArrayList<TbTelCatalogEntityJson>();
		for(TbTelCatalogEntity tb:tbTelCatalogEntityList){
			TbTelCatalogEntityJson tbj=new TbTelCatalogEntityJson(tb);
			this.tbTelCatalogEntityJson.add(tbj);
		}
	}
	public java.lang.String getId() {
		return id;
	}
	public void setId(java.lang.String id) {
		this.id = id;
	}
	public java.lang.String getTelClassName() {
		return telClassName;
	}
	public void setTelClassName(java.lang.String telClassName) {
		this.telClassName = telClassName;
	}
	public java.lang.Integer getDisplaySequence() {
		return displaySequence;
	}
	public void setDisplaySequence(java.lang.Integer displaySequence) {
		this.displaySequence = displaySequence;
	}
	
	public TbTelClassEntityJson(TbTelClassEntity tbTelClassEntity) {
		super();
		this.id = tbTelClassEntity.getId();
		this.telClassName =tbTelClassEntity.getTelClassName();
		this.displaySequence =tbTelClassEntity.getDisplaySequence();
	}
}
