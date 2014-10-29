package xjnu.edu.weixin.mall.service.impl.mall;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xjnu.edu.weixin.mall.entity.mall.TbBuilddingCatalog;
import xjnu.edu.weixin.mall.entity.mall.TbBuilddingClass;
import xjnu.edu.weixin.mall.json.mall.TbBuilddingCatalogJson;
import xjnu.edu.weixin.mall.json.mall.TbBuilddingClassJson;
import xjnu.edu.weixin.mall.service.mall.TbBuilddingClassServiceI;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("tbBuilddingClassService")
@Transactional
public class TbBuilddingClassServiceImpl extends CommonServiceImpl implements TbBuilddingClassServiceI {
	
	private List<TbBuilddingClass> getTbBuilddingClassBySchoolid(String schoolId)
	{
		
		DetachedCriteria dc = DetachedCriteria.forClass(TbBuilddingClass.class);
		dc = dc.createCriteria("tbBuilddingCatalogs", "catalog");  
		dc = dc.createCriteria("catalog.builddingEntitySet", "builddingEntity");    
		dc.add(Restrictions.eq("builddingEntity.schoolId", schoolId));
		dc.setResultTransformer(DetachedCriteria.DISTINCT_ROOT_ENTITY);
		List<TbBuilddingClass> builddingClass= findByDetached(dc);
		return builddingClass ;
	}
	
	
	private List<TbBuilddingCatalog> getTbBuilddingCatalogBySchoolIdAndClassId(String schoolId , String classId)
	{
		
		DetachedCriteria dc = DetachedCriteria.forClass(TbBuilddingCatalog.class);
		dc.add(Restrictions.eq("tbBuilddingClass.id", classId));
		dc = dc.createCriteria("builddingEntitySet", "builddingEntity");    
		dc.add(Restrictions.eq("builddingEntity.schoolId", schoolId));
		dc.setResultTransformer(DetachedCriteria.DISTINCT_ROOT_ENTITY);
		List<TbBuilddingCatalog> builddingCatalog= findByDetached(dc);
		return builddingCatalog ;
	}
	
	public List<TbBuilddingClassJson> getTbBuilddingClassJsonBySchoolid(String schoolId)
	{
		List<TbBuilddingClassJson> builddingClassJsonList = new ArrayList<TbBuilddingClassJson>();
		
		List<TbBuilddingClass> builddingClassList =  getTbBuilddingClassBySchoolid(schoolId) ;
		for( TbBuilddingClass builddingClass : builddingClassList)
		{
			TbBuilddingClassJson builddingClassJson = new TbBuilddingClassJson(builddingClass);
			List<TbBuilddingCatalog> builddingCatalogList = getTbBuilddingCatalogBySchoolIdAndClassId( schoolId , builddingClass.getId());
			builddingClassJson.setTbBuilddingCatalogs(builddingCatalogList) ;
			builddingClassJsonList.add(builddingClassJson);
		}
		
		return builddingClassJsonList ;
	}
	
}