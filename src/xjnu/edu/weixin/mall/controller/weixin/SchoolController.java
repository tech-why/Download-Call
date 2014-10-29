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
import xjnu.edu.weixin.mall.json.mall.TbTelClassEntityJson;
import xjnu.edu.weixin.mall.service.mall.BuilddingServiceI;
import xjnu.edu.weixin.mall.service.mall.ShopServiceI;
import xjnu.edu.weixin.mall.service.mall.TbBuilddingClassServiceI;
import xjnu.edu.weixin.mall.service.mall.TbTelClassServiceI;
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
@RequestMapping("/schoolController")
public class SchoolController extends BaseController {
	@Autowired
	private SystemService systemService;
	
	@Autowired
	private ShopServiceI shopService;
	
	@Autowired
	private TbTelClassServiceI tbTelClassService;
	
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
		
		AjaxJson j = new AjaxJson();
		String openuserid =request.getParameter("open_user_id");
		String appCode =request.getParameter("app_code");
		WeixinUserinfoEntity weixinUser = weixinUserinfoService.getWeixinUserInfoById(openuserid);
		
		
		if( weixinUser == null )
		{
			weixinUserinfoService.save(openuserid) ;
		}
		String schoolId = weixinUser.getSchoolId();
		//未设置学校，转到学校设置页面
		if(schoolId == null || schoolId.equals(""))
		{
			List<TbSchoolEntity> tbBuild = systemService.loadAll(TbSchoolEntity.class);
			j.setObj(tbBuild);
			String schoolJson = j.getJsonStr().substring(20, j.getJsonStr().length()-16);
			request.setAttribute("schoolJson", schoolJson);
			request.setAttribute("openuserid", openuserid);
		    url="xjnu/edu/weixin/mall_user/school_select";
		}
		else
		{
			url = forwardPage( appCode , schoolId , openuserid , request  ) ;
		}
		return url;
	}
	
	
	private String forwardPage(String appCode , String schoolId , String openuserid ,
			HttpServletRequest request  )
	{
		String forwardUrl = "" ;
		//校园助手应用
		if( appCode.equals("helper"))
		{
			AjaxJson j = new AjaxJson();
			TbSchoolEntity school = systemService.getEntity(TbSchoolEntity.class , schoolId) ;
			double builddingLat , builddingLng ;
			
			//添加学校经纬度信息
			builddingLat =  43.8;
			builddingLng =  87.6;
			
			//设置建筑类别
			List<TbBuilddingClassJson> tbclass = tbBuilddingClassService.getTbBuilddingClassJsonBySchoolid(schoolId);
			
			j.setObj(tbclass) ;
			
			j.setObj(tbclass) ;
			message = "success" ;
			j.setMsg(message);
			String classListJson = j.getJsonStr() ;
			
			request.setAttribute("builddingLat", builddingLat) ;
			request.setAttribute("builddingLng", builddingLng) ;
			request.setAttribute("classListJson", classListJson) ;
			request.setAttribute("classList", tbclass) ;
			request.setAttribute("defautsCatalogList", tbclass.get(0).getTbBuilddingCatalogs()) ;
			
			forwardUrl  = "xjnu/edu/weixin/map/mapSearch";
			
		}
		//微信超市应用
		 else if(  appCode.equals("shop")){
			 List<TbMallShopEntity> list = shopService.getShopBySchoolId(schoolId);
			 request.setAttribute("shopJson", list);
			 request.setAttribute("openuserid", openuserid);
			 forwardUrl="xjnu/edu/weixin/mall_user/shop_select";
		 }
		//号码通应用
		 else if(  appCode.equals("phone")){
			 List<TbTelClassEntityJson> tbTelclassList = tbTelClassService.getTbTelClassEntityJsonBySchoolId(schoolId);
			 request.setAttribute("tbTelclassList", tbTelclassList);
			 request.setAttribute("openuserid", openuserid);
			 request.setAttribute("schoolId", schoolId);
			 forwardUrl="xjnu/edu/weixin/mall_user/tel_class";
		 
		 }
		return forwardUrl ;
		
	}
	
	@RequestMapping(params = "select")
	public String select(TbSchoolEntity tbSchool,HttpServletRequest request) {
		
		   AjaxJson j = new AjaxJson();
		   String openuserid =request.getParameter("open_user_id");
		   List<TbSchoolEntity> tbBuild = systemService.loadAll(TbSchoolEntity.class);
		   j.setObj(tbBuild);
		   String schoolJson = j.getJsonStr().substring(20, j.getJsonStr().length()-16);
		   request.setAttribute("schoolJson", schoolJson);
		   request.setAttribute("openuserid", openuserid);
           url="xjnu/edu/weixin/mall_user/school_select";
		 return url;
	}
}
