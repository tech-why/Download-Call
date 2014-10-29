package xjnu.edu.weixin.mall.controller.weixin;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import xjnu.edu.weixin.mall.entity.mall.TbMallShopEntity;
import xjnu.edu.weixin.mall.entity.mall.TbTelCatalogEntity;
import xjnu.edu.weixin.mall.entity.mall.WeixinUserinfoEntity;
import xjnu.edu.weixin.mall.service.mall.TbTelCatalogServiceI;

/**   
 * @Title: Controller
 * @Description: 号码小类
 * @author zhangdaihao
 * @date 2014-08-25 18:10:12
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/telCatalogController")
public class TelCatalogController extends BaseController {
	
	@Autowired
	private SystemService systemService;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

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
