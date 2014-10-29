package weixin.idea.extend.function.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.jeecgframework.core.util.ApplicationContextUtil;
import org.springframework.beans.factory.annotation.Autowired;

import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.base.service.WeixinUserServiceI;
import weixin.guanjia.core.entity.message.resp.Article;
import weixin.guanjia.core.entity.message.resp.NewsMessageResp;
import weixin.guanjia.core.entity.message.resp.TextMessageResp;
import weixin.guanjia.core.util.MessageUtil;
import weixin.idea.extend.function.KeyServiceI;
import xjnu.edu.weixin.mall.entity.mall.WeixinUserinfoEntity;
import xjnu.edu.weixin.mall.service.impl.mall.WeixinUserinfoServiceImpl;
import xjnu.edu.weixin.mall.service.mall.TbSchoolServiceI;
import xjnu.edu.weixin.mall.service.mall.WeixinUserinfoServiceI;

/**
 * 微网站
 * @author zhangdaihao
 *
 */
public class ChangeSchoolService extends BaseExtendService implements KeyServiceI {
	
	
	public String excute(String content, TextMessageResp defaultMessage,
			HttpServletRequest request) {
		this.updateOrSaveUser(defaultMessage);
		
		TbSchoolServiceI schoolService =(TbSchoolServiceI)ApplicationContextUtil.getContext().getBean("tbSchoolService");
		String respMessage = null;
		String keyWord = content.trim();
		if ("".equals(keyWord) || "sch".equals(keyWord)) {
			defaultMessage.setContent(schoolService.getChangeSchoolString());
			
		} else {
			if ( !schoolService.changeSchool(keyWord, defaultMessage.getToUserName()) )
			{
				respMessage = "设置学校出错，请稍后重试！";
			}
			else
			{
				respMessage = "设置学校成功！";
			}
			defaultMessage.setContent(respMessage);
		}	
		respMessage = MessageUtil.textMessageToXml(defaultMessage);
		return respMessage ;
		
	}

	@Override
	public String getKey() {

		return "学校,sch";
	}

}
