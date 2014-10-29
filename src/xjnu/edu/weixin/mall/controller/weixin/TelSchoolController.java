package xjnu.edu.weixin.mall.controller.weixin;
import java.util.ArrayList;
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

import xjnu.edu.weixin.mall.entity.mall.TbMallShopEntity;
import xjnu.edu.weixin.mall.entity.mall.TbSchoolEntity;
import xjnu.edu.weixin.mall.entity.mall.TbTelCatalogEntity;
import xjnu.edu.weixin.mall.entity.mall.TbTelClassEntity;
import xjnu.edu.weixin.mall.entity.mall.WeixinUserinfoEntity;
import xjnu.edu.weixin.mall.json.mall.TbTelCatalogEntityJson;
import xjnu.edu.weixin.mall.json.mall.TbTelClassEntityJson;
import xjnu.edu.weixin.mall.service.mall.TbTelClassServiceI;
/**   
 * @Title: Controller
 * @Description:  返回所有学校JSON(微信前端)
 * @date 2014-08-05 12:46:04
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/telSchoolController")
public class TelSchoolController extends BaseController {
	@Autowired
	private SystemService systemService;
	@Autowired
	private TbTelClassServiceI tbTelClassService;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	private static String url;
    private static String schoolId;
	@RequestMapping(params = "display")
	public String display(TbSchoolEntity tbSchool,HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String openuserid =request.getParameter("open_user_id");
		  List<WeixinUserinfoEntity> openId = systemService.findByProperty(WeixinUserinfoEntity.class, "openid", openuserid);
		 if(openId.size()<1){
			   List<TbSchoolEntity> tbBuild = systemService.loadAll(TbSchoolEntity.class);
				j.setObj(tbBuild);
				String schoolJson = j.getJsonStr().substring(20, j.getJsonStr().length()-16);
				request.setAttribute("schoolJson", schoolJson);
				request.setAttribute("openuserid", openuserid);
			    url="xjnu/edu/weixin/mall_user/school_select_tel";
		 }else{
			 List<TbTelClassEntityJson> tbTelclassList = null;
			for(WeixinUserinfoEntity wx : openId){
				 schoolId=wx.getSchoolId();
				 tbTelclassList = tbTelClassService.getTbTelClassEntityJsonBySchoolId(schoolId);
			 }
				 request.setAttribute("tbTelclassList", tbTelclassList);
				 request.setAttribute("openuserid", openuserid);
				 request.setAttribute("schoolId", schoolId);
				 url="xjnu/edu/weixin/mall_user/tel_class";
			}
		 
		 return url;
	}
	
}
