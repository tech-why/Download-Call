package weixin.guanjia.base.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.jeecgframework.core.common.service.CommonService;

import xjnu.edu.weixin.mall.entity.mall.WeixinUserinfoEntity;

public interface WeixinUserServiceI extends CommonService{
	
	public WeixinUserinfoEntity getWeixinUserInfoById(String userOpenid);
	
	public void saveWinxinUserInfo(String userOpenid , String sys_accountId);

	
}
