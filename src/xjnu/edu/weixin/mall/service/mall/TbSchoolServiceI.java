package xjnu.edu.weixin.mall.service.mall;

import org.jeecgframework.core.common.service.CommonService;

public interface TbSchoolServiceI extends CommonService{
	
	
	public String getChangeSchoolString();
	
	
	public boolean changeSchool(String schoolCode, String openUserId);
	
	public boolean isSetSchool(String openUserId) ;

}
