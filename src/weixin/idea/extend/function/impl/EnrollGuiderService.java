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
public class EnrollGuiderService  extends BaseExtendService  implements KeyServiceI {
	
	
	public String excute(String content, TextMessageResp defaultMessage,
			HttpServletRequest request) {
		
		this.updateOrSaveUser(defaultMessage);
		
		String respMessage = null;
		String keyWord = content.trim();
		TbSchoolServiceI schoolService =(TbSchoolServiceI)ApplicationContextUtil.getContext().getBean("tbSchoolService");
		if ( !schoolService.isSetSchool(defaultMessage.getToUserName() ) ) {
			defaultMessage.setContent(schoolService.getChangeSchoolString());
			return MessageUtil.textMessageToXml(defaultMessage);
		}
		else  {
			
			ResourceBundle bundler = ResourceBundle.getBundle("sysConfig");
			List<Article> articleList = new ArrayList<Article>();
			Article article = new Article();
			article.setTitle("新生引导");
			article.setPicUrl(bundler.getString("domain")+ "/plug-in/weixin/images/xiaoyuanzhushou.jpg");
			// 此userid后期需要通过高级接口获取到微信帐号，此处先以加密后的ID为参数进行传递
			if( keyWord.equals("") ){
				article.setDescription("显示自己当前位置请点击聊天框右边的“+”，点击“位置”按钮，再点击发送即可。");
				article.setUrl(bundler.getString("domain")+ "/enrollGuiderController.do?display&open_user_id="+ defaultMessage.getToUserName());
			}
			else
			{
				String paramString = "&weidu=" + request.getAttribute("Location_X") + "&jindu=" + request.getAttribute("Location_Y");
				article.setUrl(bundler.getString("domain")+ "/enrollGuiderController.do?display&open_user_id="+ defaultMessage.getToUserName()+ paramString);
			}
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
		
		
	}

	@Override
	public String getKey() {

		return "微信CMS,CMS";
	}

}
