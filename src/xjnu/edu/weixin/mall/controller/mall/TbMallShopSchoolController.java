package xjnu.edu.weixin.mall.controller.mall;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
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

import xjnu.edu.weixin.mall.entity.mall.TbBuilddingClass;
import xjnu.edu.weixin.mall.entity.mall.TbBuilddingEntity;
import xjnu.edu.weixin.mall.entity.mall.TbMallShopEntity;
import xjnu.edu.weixin.mall.entity.mall.TbMallShopSchoolEntity;
import xjnu.edu.weixin.mall.entity.mall.TbSchoolEntity;
import xjnu.edu.weixin.mall.json.mall.TbBuilddingEntityJson;
import xjnu.edu.weixin.mall.service.mall.TbMallShopSchoolServiceI;

/**   
 * @Title: Controller
 * @Description: 分配学校
 * @date 2014-08-05 12:50:07
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/tbMallShopSchoolController")
public class TbMallShopSchoolController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TbMallShopSchoolController.class);

	@Autowired
	private TbMallShopSchoolServiceI tbMallShopSchoolService;
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
	 * 分配学校列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "tbMallShopSchool")
	public ModelAndView tbMallShopSchool(HttpServletRequest request) {
		// 给学校外键查询条件中的下拉框准备数据
		List<TbSchoolEntity> TbSchoolEntityList = systemService.getList(TbSchoolEntity.class);
		String schoolName = RoletoJson.listToReplaceStr(TbSchoolEntityList, "schoolName", "id");
		List<TbMallShopEntity> TbMallShopEntityList = systemService.getList(TbMallShopEntity.class);
		String shopName=RoletoJson.listToReplaceStr(TbMallShopEntityList, "shopName", "id");
		if(!schoolName.equals("")){
			request.setAttribute("schoolReplace", schoolName);	
		}else{
			request.setAttribute("schoolReplace", " _0");
		}
		// 给商铺外键查询条件中的下拉框准备数据
		if(!shopName.equals("")){
			request.setAttribute("shopReplace",shopName );
		}else{
			request.setAttribute("shopReplace", " _0");
		}
		return new ModelAndView("xjnu/edu/weixin/mall/mall/tbMallShopSchoolList");
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
	public void datagrid(TbMallShopSchoolEntity tbMallShopSchool,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TbMallShopSchoolEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tbMallShopSchool, request.getParameterMap());
		this.tbMallShopSchoolService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除分配学校
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TbMallShopSchoolEntity tbMallShopSchool, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		tbMallShopSchool = systemService.getEntity(TbMallShopSchoolEntity.class, tbMallShopSchool.getId());
		message = "分配学校删除成功";
		tbMallShopSchoolService.delete(tbMallShopSchool);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加分配学校
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(TbMallShopSchoolEntity tbMallShopSchool, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(tbMallShopSchool.getId())) {
			message = "分配学校更新成功";
			TbMallShopSchoolEntity t = tbMallShopSchoolService.get(TbMallShopSchoolEntity.class, tbMallShopSchool.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(tbMallShopSchool, t);
				tbMallShopSchoolService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "分配学校更新失败";
			}
		} else {
			message = "分配学校添加成功";
			tbMallShopSchoolService.save(tbMallShopSchool);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 分配学校列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TbMallShopSchoolEntity tbMallShopSchool, HttpServletRequest req) {
		    //传入学校的信息
		    List<TbSchoolEntity> tbSchoolEntityList = new ArrayList<TbSchoolEntity>();
			tbSchoolEntityList.addAll((List)systemService.getList(TbSchoolEntity.class));
		    req.setAttribute("tbSchoolEntityList", tbSchoolEntityList);
		    //传入商铺的信息
		    List<TbMallShopEntity> tbMallShopEntityList = new ArrayList<TbMallShopEntity>();
		    tbMallShopEntityList.addAll((List)systemService.getList(TbMallShopEntity.class));
		    req.setAttribute("tbMallShopEntityList", tbMallShopEntityList);
		    
		if (StringUtil.isNotEmpty(tbMallShopSchool.getId())) {
			tbMallShopSchool = tbMallShopSchoolService.getEntity(TbMallShopSchoolEntity.class, tbMallShopSchool.getId());
			req.setAttribute("tbMallShopSchoolPage", tbMallShopSchool);
		}
		return new ModelAndView("xjnu/edu/weixin/mall/mall/tbMallShopSchool");
	}
}
