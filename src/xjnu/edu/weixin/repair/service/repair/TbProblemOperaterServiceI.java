package xjnu.edu.weixin.repair.service.repair;

import org.jeecgframework.core.common.service.CommonService;

public interface TbProblemOperaterServiceI extends CommonService{
	public String getBindOperator() ;
	public boolean BindOperater(String bindCode , String openUserId);
	public boolean isBindOperator(String openUserId);

}
