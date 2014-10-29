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
import xjnu.edu.weixin.mall.service.mall.WeixinUserinfoServiceI;

/**
 * 微网站
 * @author zhangdaihao
 *
 */
public class PhoneNumberService  extends BaseExtendService  implements KeyServiceI {
	
	
	public String excute(String content, TextMessageResp defaultMessage,
			HttpServletRequest request) {
		this.updateOrSaveUser(defaultMessage);
		ResourceBundle bundler = ResourceBundle.getBundle("sysConfig");
		List<Article> articleList = new ArrayList<Article>();
		Article article = new Article();
		article.setTitle("号码通");
		article.setDescription("");
		article.setPicUrl(bundler.getString("domain")+ "/plug-in/weixin/images/haomabaishitong.jpg");
		
		article.setUrl(bundler.getString("domain")+ "/schoolController.do?display&app_code=phone&open_user_id="+ defaultMessage.getToUserName());
		articleList.add(article);
		NewsMessageResp newsMessage = new NewsMessageResp();
		newsMessage.setToUserName(defaultMessage.getToUserName());
		newsMessage.setFromUserName(defaultMessage.getFromUserName());
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
		newsMessage.setArticleCount(articleList.size());
		newsMessage.setArticles(articleList);
		return MessageUtil.newsMessageToXml(newsMessage);
	}

	@Override
	public String getKey() {

		return "微信CMS,CMS";
	}

}