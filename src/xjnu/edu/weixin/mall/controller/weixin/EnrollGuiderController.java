package xjnu.edu.weixin.mall.controller.weixin;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.web.system.service.SystemService;

import xjnu.edu.weixin.mall.entity.mall.TbBuilddingClass;
import xjnu.edu.weixin.mall.entity.mall.TbMallShopEntity;
import xjnu.edu.weixin.mall.entity.mall.TbSchoolEntity;
import xjnu.edu.weixin.mall.entity.mall.WeixinUserinfoEntity;
import xjnu.edu.weixin.mall.json.mall.TbBuilddingClassJson;
import xjnu.edu.weixin.mall.service.mall.BuilddingServiceI;
import xjnu.edu.weixin.mall.service.mall.ShopServiceI;
import xjnu.edu.weixin.mall.service.mall.TbBuilddingClassServiceI;
import xjnu.edu.weixin.mall.service.mall.WeixinUserinfoServiceI;
/**   
 * @Title: Controller
 * @Description:  返回所有学校JSON(微信前端)
 * @date 2014-08-05 12:46:04
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/enrollGuiderController")
public class EnrollGuiderController extends BaseController {
	@Autowired
	private SystemService systemService;
	
	@Autowired
	private ShopServiceI shopService;
	
	@Autowired
	private BuilddingServiceI builddingService;
	
	@Autowired
	private TbBuilddingClassServiceI tbBuilddingClassService;
	
	@Autowired
	private WeixinUserinfoServiceI weixinUserinfoService ;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	private static String url;
	/**
	 * 返回所有学校JSON(微信前端)
	 */

	@RequestMapping(params = "display")
	public String display(HttpServletRequest request) {
		
		String forwardUrl = "";
		AjaxJson j = new AjaxJson();
		String openuserid =request.getParameter("open_user_id");
		WeixinUserinfoEntity weixinUser = weixinUserinfoService.getWeixinUserInfoById(openuserid);
		String schoolId = weixinUser.getSchoolId();
		TbSchoolEntity school = systemService.getEntity(TbSchoolEntity.class , schoolId);
		String jindu = (String)request.getParameter("jindu");
		String weidu = (String)request.getParameter("weidu");
		request.setAttribute("flag", "false");
		if( jindu != null && weidu != null)
		{
			request.setAttribute("jindu", jindu);
			request.setAttribute("weidu", weidu);
			request.setAttribute("flag", "true");
		}
		if(school.getSchoolName().equals("新疆师范大学"))
		{
			forwardUrl = "xjnu/edu/weixin/map/sfdxEnrollGuider";
		}
		else if(school.getSchoolName().equals("新疆财经大学"))
		{
			forwardUrl = "xjnu/edu/weixin/map/cjdxEnrollGuider";
		}
		else if(school.getSchoolName().equals("新疆医科大学"))
		{
			forwardUrl = "xjnu/edu/weixin/map/ykdxEnrollGuider";
		}
		else if(school.getSchoolName().equals("新疆农业大学"))
		{
			forwardUrl = "xjnu/edu/weixin/map/nydxEnrollGuider";
		}
		else if(school.getSchoolName().equals("新疆大学校本部"))
		{
			forwardUrl = "xjnu/edu/weixin/map/xjdxEnrollGuider";
		}
		else if(school.getSchoolName().equals("新疆大学南校区"))
		{
			forwardUrl = "xjnu/edu/weixin/map/xdnxEnrollGuider";
		}
		else if(school.getSchoolName().equals("乌鲁木齐职业技术大学"))
		{
			forwardUrl = "xjnu/edu/weixin/map/wzydxEnrollGuider";
		}
		else if(school.getSchoolName().equals("新疆职业大学"))
		{
			forwardUrl = "xjnu/edu/weixin/map/xzydxEnrollGuider";
		}
		else if(school.getSchoolName().equals("新师大青年政治学院"))
		{
			forwardUrl = "xjnu/edu/weixin/map/txEnrollGuider";
		}
		return forwardUrl;
	}
	
	
	
}
