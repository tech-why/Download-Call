package xjnu.edu.weixin.mall.service.mall;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.service.CommonService;

import xjnu.edu.weixin.mall.entity.mall.TbBuilddingClass;
import xjnu.edu.weixin.mall.entity.mall.TbMallProductClassEntity;

public interface BuilddingServiceI extends CommonService{
	
	public List<TbBuilddingClass> getBuilddingClassBySchoolId(String schoolId);
}
