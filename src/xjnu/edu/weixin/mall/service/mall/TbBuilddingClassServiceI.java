package xjnu.edu.weixin.mall.service.mall;

import java.util.List;

import org.jeecgframework.core.common.service.CommonService;

import xjnu.edu.weixin.mall.json.mall.TbBuilddingClassJson;

public interface TbBuilddingClassServiceI extends CommonService{
	public List<TbBuilddingClassJson> getTbBuilddingClassJsonBySchoolid(String schoolId);

}
