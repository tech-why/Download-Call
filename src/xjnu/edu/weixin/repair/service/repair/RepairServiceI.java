package xjnu.edu.weixin.repair.service.repair;

import java.util.List;
import org.jeecgframework.core.common.model.json.ComboTree;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.service.CommonService;

import xjnu.edu.weixin.mall.entity.mall.TbBuilddingClass;
import xjnu.edu.weixin.mall.entity.mall.TbMallProductClassEntity;

public interface RepairServiceI extends CommonService{
	
	public List<ComboTree> getSchoolComboTree(String operateorId);
	public void updateBuilddingAssignment(String[] builddingIds , String operaterId) ;
}
