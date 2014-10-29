package xjnu.edu.weixin.mall.controller.weixin;

import javax.servlet.http.HttpServletRequest;

import org.jeecgframework.core.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import weixin.guanjia.account.service.WeixinAccountServiceI;
@Scope("prototype")
@Controller
@RequestMapping("/accountController")
public class AccountController extends BaseController {
	@Autowired
	private WeixinAccountServiceI weixinAccountServiceI;
	@RequestMapping(params="display")
	public ModelAndView account(HttpServletRequest request){
		String openuserid=request.getParameter("open_user_id");
		request.setAttribute("open_user_id",openuserid);
		request.setAttribute("app_code", "account");
		return new ModelAndView("xjnu/edu/weixin/mall_user/account");
		
	}

}
