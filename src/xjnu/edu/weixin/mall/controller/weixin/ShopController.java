package xjnu.edu.weixin.mall.controller.weixin;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.system.service.SystemService;
import xjnu.edu.weixin.mall.entity.mall.TbMallShopEntity;
import xjnu.edu.weixin.mall.entity.mall.WeixinUserinfoEntity;

/**   
 * @Title: Controller
 * @Description: 分配学校
 * @date 2014-08-05 12:50:07
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/shopController")
public class ShopController extends BaseController {
	@Autowired
	private SystemService systemService;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * 根据微信传过来的schoolId返回所有商铺JSON
	 *//*

	@RequestMapping(params = "display")
	public String display(HttpServletRequest request, HttpServletResponse response) {
		     String schoolId=request.getParameter("schoolId");
		    
			
		     DetachedCriteria dc = DetachedCriteria.forClass(TbMallShopEntity.class);
		     dc = dc.createCriteria("tbMallShopSchoolEntitys", "shop");   
			 dc.createCriteria("shop.tbSchoolEntity", "school");
			 dc.add(Restrictions.eq("school.id", schoolId));
			 List<TbMallShopEntity> list =  systemService.findByDetached(dc);
			 request.setAttribute("shopJson", list);
		return "xjnu/edu/weixin/mall_user/shop_select";
	}*/
	
	
	@RequestMapping(params = "select")
	public String select(HttpServletRequest request, HttpServletResponse response) {
		     String schoolId=request.getParameter("schoolId");
		     
		     String openuserid =request.getParameter("open_user_id");
			 WeixinUserinfoEntity openId = systemService.findUniqueByProperty(WeixinUserinfoEntity.class, "openid", openuserid);
			 
			 WeixinUserinfoEntity weixin=new WeixinUserinfoEntity();
			 weixin.setOpenid(openuserid);
			 weixin.setSchoolId(schoolId);
			 if(StringUtil.isNotEmpty(openId.getId())){
				 WeixinUserinfoEntity t = systemService.get(WeixinUserinfoEntity.class, openId.getId());
				 try {
					MyBeanUtils.copyBeanNotNull2Bean(weixin, t);
				} catch (Exception e) {
					e.printStackTrace();
				}
				 systemService.saveOrUpdate(t);
			 }else{
			     systemService.save(weixin);
			 }
		     DetachedCriteria dc = DetachedCriteria.forClass(TbMallShopEntity.class);
		     dc = dc.createCriteria("tbMallShopSchoolEntitys", "shop");   
			 dc.createCriteria("shop.tbSchoolEntity", "school");
			 dc.add(Restrictions.eq("school.id", schoolId));
			 List<TbMallShopEntity> list =  systemService.findByDetached(dc);
			 request.setAttribute("shopJson", list);
			 request.setAttribute("openuserid", openuserid);
		     return "xjnu/edu/weixin/mall_user/shop_select";
	}
}
