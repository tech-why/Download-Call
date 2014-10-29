package xjnu.edu.weixin.mall.controller.mall;
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

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.RoletoJson;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import xjnu.edu.weixin.mall.entity.mall.TbMallShopEntity;
import xjnu.edu.weixin.mall.entity.mall.TbSchoolEntity;
import xjnu.edu.weixin.mall.entity.mall.WeixinUserinfoEntity;
import xjnu.edu.weixin.mall.service.mall.WeixinUserinfoServiceI;

/**   
 * @Title: Controller
 * @Description: 用户信息管理
 * @author zhangdaihao
 * @date 2014-08-11 20:31:44
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/weixinUserinfoController")
public class WeixinUserinfoController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(WeixinUserinfoController.class);

	@Autowired
	private WeixinUserinfoServiceI weixinUserinfoService;
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
	 * 用户信息管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "weixinUserinfo")
	public ModelAndView weixinUserinfo(HttpServletRequest request) {
		// 给学校外键查询条件中的下拉框准备数据
		List<TbSchoolEntity> TbSchoolEntityList = systemService.getList(TbSchoolEntity.class);
		String schoolName = RoletoJson.listToReplaceStr(TbSchoolEntityList, "schoolName", "id");
		
		if(!schoolName.equals("")){
			request.setAttribute("schoolReplace", schoolName);	
		}else{
			request.setAttribute("schoolReplace", " _0");
		}
		return new ModelAndView("xjnu/edu/weixin/mall/mall/weixinUserinfoList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(WeixinUserinfoEntity weixinUserinfo,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(WeixinUserinfoEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinUserinfo, request.getParameterMap());
		this.weixinUserinfoService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除用户信息管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(WeixinUserinfoEntity weixinUserinfo, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		weixinUserinfo = systemService.getEntity(WeixinUserinfoEntity.class, weixinUserinfo.getId());
		message = "用户信息管理删除成功";
		weixinUserinfoService.delete(weixinUserinfo);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加用户信息管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(WeixinUserinfoEntity weixinUserinfo, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(weixinUserinfo.getId())) {
			message = "用户信息管理更新成功";
			WeixinUserinfoEntity t = weixinUserinfoService.get(WeixinUserinfoEntity.class, weixinUserinfo.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(weixinUserinfo, t);
				weixinUserinfoService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "用户信息管理更新失败";
			}
		} else {
			message = "用户信息管理添加成功";
			weixinUserinfoService.save(weixinUserinfo);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 用户信息管理列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(WeixinUserinfoEntity weixinUserinfo, HttpServletRequest req) {
		//传入学校的信息
	    List<TbSchoolEntity> tbSchoolEntityList = new ArrayList<TbSchoolEntity>();
		tbSchoolEntityList.addAll((List)systemService.getList(TbSchoolEntity.class));
	    req.setAttribute("tbSchoolEntityList", tbSchoolEntityList);
		
		if (StringUtil.isNotEmpty(weixinUserinfo.getId())) {
			weixinUserinfo = weixinUserinfoService.getEntity(WeixinUserinfoEntity.class, weixinUserinfo.getId());
			req.setAttribute("weixinUserinfoPage", weixinUserinfo);
		}
		return new ModelAndView("xjnu/edu/weixin/mall/mall/weixinUserinfo");
	}
}
