package xjnu.edu.weixin.mall.controller.weixin;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.RoletoJson;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import xjnu.edu.weixin.mall.entity.mall.TbBuilddingCatalog;
import xjnu.edu.weixin.mall.entity.mall.TbBuilddingClass;
import xjnu.edu.weixin.mall.entity.mall.TbBuilddingEntity;
import xjnu.edu.weixin.mall.entity.mall.TbSchoolEntity;
import xjnu.edu.weixin.mall.entity.mall.WeixinUserinfoEntity;
import xjnu.edu.weixin.mall.json.mall.TbBuilddingClassJson;
import xjnu.edu.weixin.mall.json.mall.TbBuilddingEntityJson;
import xjnu.edu.weixin.mall.service.mall.TbBuilddingClassServiceI;
import xjnu.edu.weixin.mall.service.mall.TbBuilddingServiceI;

/**   
 * @Title: Controller
 * @Description: 建筑管理
 * @author zhangdaihao
 * @date 2014-08-05 12:47:42
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/mapSearchController")
public class MapSearchController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(MapSearchController.class);

	@Autowired
	private TbBuilddingServiceI tbBuilddingService;
	@Autowired
	private TbBuilddingClassServiceI tbBuilddingClassService;
	@Autowired
	private SystemService systemService;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	
	@RequestMapping(params = "display")
	public String datagrid1(HttpServletRequest request, HttpServletResponse response) {
		
		String userOpenid = request.getParameter("userOpenid");
		//WeixinGzuserinfo userInfo = systemService.getEntity(WeixinGzuserinfo.class, userOpenid) ;
		List<WeixinUserinfoEntity> users = systemService.findByProperty(WeixinUserinfoEntity.class, "openid" , userOpenid) ;
		//设置学校经纬度
		//String schoolId = request.getParameter("schoolId");
		String schoolId = users.get(0).getSchoolId() ;
		
		TbSchoolEntity school = systemService.getEntity(TbSchoolEntity.class , schoolId) ;
		double builddingLat , builddingLng ;
		//添加学校经纬度信息
		builddingLat =  43.8;
		builddingLng =  87.6;
		
		//设置建筑类别
		AjaxJson j=new AjaxJson();
		List<TbBuilddingClassJson> tbclass = tbBuilddingClassService.getTbBuilddingClassJsonBySchoolid(schoolId);
		
		j.setObj(tbclass) ;
		message = "success" ;
		j.setMsg(message);
		String classListJson = j.getJsonStr() ;
		
		request.setAttribute("builddingLat", builddingLat) ;
		request.setAttribute("builddingLng", builddingLng) ;
		request.setAttribute("classListJson", classListJson) ;
		request.setAttribute("classList", tbclass) ;
		request.setAttribute("defautsCatalogList", tbclass.get(0).getTbBuilddingCatalogs()) ;
		
		return new String("xjnu/edu/weixin/map/mapSearch");
		
		
		
	}
	
	
	/**
	 * 添加建筑管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(TbBuilddingEntity tbBuildding, HttpServletRequest request) {
		
		AjaxJson j = new AjaxJson();
		
		if (StringUtil.isNotEmpty(tbBuildding.getId())) {
			message = "建筑管理更新成功";
			TbBuilddingEntity t = tbBuilddingService.get(TbBuilddingEntity.class, tbBuildding.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(tbBuildding, t);
				tbBuilddingService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "建筑管理更新失败";
			}
		} else {
			message = "建筑管理添加成功";
			tbBuilddingService.save(tbBuildding);
//			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}
	
	
	
	@RequestMapping(params = "mapSearch")
	@ResponseBody
	public AjaxJson searchBuilddingByMap(TbBuilddingEntity tbBuildding, 
			HttpServletRequest request, HttpServletResponse response) {
		 
		AjaxJson j = new AjaxJson();
		String userOpenid = request.getParameter("open_user_id");
		WeixinUserinfoEntity weixinUserinfoEntity = systemService.findUniqueByProperty(
				WeixinUserinfoEntity.class, "openid", userOpenid);
		String schoolId = weixinUserinfoEntity.getSchoolId() ;
		String catalogId = request.getParameter("catalogId");
		List<TbBuilddingEntity> builddingList = tbBuilddingService.findHql("from TbBuilddingEntity where tbSchoolEntity.id=? and tbBuilddingCatalogEntity.id=?", new Object [] {schoolId,catalogId});
		if( builddingList != null ){
			//临时实体类处理处理JSON数据
			List<TbBuilddingEntityJson> list=new ArrayList<TbBuilddingEntityJson>();
			for(TbBuilddingEntity s:builddingList){
				TbBuilddingEntityJson lstbej=new TbBuilddingEntityJson();
				lstbej.setBuilddingName(s.getBuilddingName());
				lstbej.setBuilddingDescription(s.getBuilddingDescription());
				lstbej.setId(s.getId());
				lstbej.setJindu(s.getJindu());
				lstbej.setPhoneTel(s.getPhoneTel());
				lstbej.setWeidu(s.getWeidu());
				list.add(lstbej);
				  }
		   j.setObj(list);
		   j.setMsg("true");
		}
		else
		{
			j.setMsg("false");
		}
		return j;
	}
	
	
	
}
