package xjnu.edu.weixin.mall.service.impl.mall;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xjnu.edu.weixin.mall.entity.mall.TbBuilddingClass;
import xjnu.edu.weixin.mall.service.mall.BuilddingServiceI;
import xjnu.edu.weixin.mall.service.mall.TbBuilddingServiceI;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("builddingService")
@Transactional
public class BuilddingServiceImpl extends CommonServiceImpl implements BuilddingServiceI {
	
	@Override
	public List<TbBuilddingClass> getBuilddingClassBySchoolId(String schoolId)
	{
		DetachedCriteria dc = DetachedCriteria.forClass(TbBuilddingClass.class); 
		dc = dc.createCriteria("tbBuilddingCatalogs", "catalog");   
		dc.createCriteria("catalog.builddingEntitySet", "buildding");   
		dc.add(Restrictions.eq("buildding.schoolId", schoolId));
		dc.setResultTransformer(DetachedCriteria.DISTINCT_ROOT_ENTITY);
		List<TbBuilddingClass> builddingClassList =  findByDetached(dc);
		return builddingClassList ;
	}
	
}