package weixin.idea.extend.function.impl;

import org.jeecgframework.core.util.ApplicationContextUtil;

import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.core.entity.message.resp.TextMessageResp;
import xjnu.edu.weixin.mall.entity.mall.WeixinUserinfoEntity;
import xjnu.edu.weixin.mall.service.mall.WeixinUserinfoServiceI;

public class BaseExtendService {
	
	protected void updateOrSaveUser( TextMessageResp defaultMessage)
	{
		WeixinAccountServiceI weixinAccountService =(WeixinAccountServiceI)ApplicationContextUtil.getContext().getBean("weixinAccountService");
		String accountid = weixinAccountService.findByToUsername(defaultMessage.getFromUserName()).getId();
		WeixinUserinfoServiceI weixinUserinfoService =(WeixinUserinfoServiceI)ApplicationContextUtil.getContext().getBean("weixinUserinfoService");
		WeixinUserinfoEntity weixinUser = weixinUserinfoService.getWeixinUserInfoById(defaultMessage.getToUserName());
		if( weixinUser == null )
		{
			weixinUserinfoService.saveWinxinUserInfo(defaultMessage.getToUserName(),accountid) ;
		}
		
	}
	
	

}
