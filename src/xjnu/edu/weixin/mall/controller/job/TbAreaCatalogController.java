package xjnu.edu.weixin.mall.controller.job;
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

import xjnu.edu.weixin.mall.entity.job.TbAreaCatalogEntity;
import xjnu.edu.weixin.mall.entity.job.TbAreaClassEntity;
import xjnu.edu.weixin.mall.entity.job.TbJobClassEntity;
import xjnu.edu.weixin.mall.service.job.TbAreaCatalogServiceI;

/**   
 * @Title: Controller
 * @Description: 区域小类表
 * @author zhangdaihao
 * @date 2014-09-16 12:31:39
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/tbAreaCatalogController")
public class TbAreaCatalogController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TbAreaCatalogController.class);

	@Autowired
	private TbAreaCatalogServiceI tbAreaCatalogService;
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
	 * 区域小类表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "tbAreaCatalog")
	public ModelAndView tbAreaCatalog(HttpServletRequest request) {
		// 给大类外键查询条件中的下拉框准备数据
				List<TbAreaClassEntity> tbAreaClassEntityList = systemService.getList(TbAreaClassEntity.class);
				String className=RoletoJson.listToReplaceStr(tbAreaClassEntityList, "className", "id");
				if(!className.equals("")){
					request.setAttribute("classNameReplace", className);
				}else{
					request.setAttribute("classNameReplace", " _0");
				}
		return new ModelAndView("xjnu/edu/weixin/mall/job/tbAreaCatalogList");
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
	public void datagrid(TbAreaCatalogEntity tbAreaCatalog,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TbAreaCatalogEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tbAreaCatalog, request.getParameterMap());
		this.tbAreaCatalogService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除区域小类表
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TbAreaCatalogEntity tbAreaCatalog, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		tbAreaCatalog = systemService.getEntity(TbAreaCatalogEntity.class, tbAreaCatalog.getId());
		message = "区域小类表删除成功";
		tbAreaCatalogService.delete(tbAreaCatalog);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加区域小类表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(TbAreaCatalogEntity tbAreaCatalog, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(tbAreaCatalog.getId())) {
			message = "区域小类表更新成功";
			TbAreaCatalogEntity t = tbAreaCatalogService.get(TbAreaCatalogEntity.class, tbAreaCatalog.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(tbAreaCatalog, t);
				tbAreaCatalogService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "区域小类表更新失败";
			}
		} else {
			message = "区域小类表添加成功";
			tbAreaCatalogService.save(tbAreaCatalog);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 区域小类表列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TbAreaCatalogEntity tbAreaCatalog, HttpServletRequest req) {
		//传入大类信息
		 List<TbAreaClassEntity> tbAreaClassEntityList = new ArrayList<TbAreaClassEntity>();
		 tbAreaClassEntityList.addAll((List)systemService.getList(TbAreaClassEntity.class));
		    req.setAttribute("tbAreaClassEntityList", tbAreaClassEntityList);
		if (StringUtil.isNotEmpty(tbAreaCatalog.getId())) {
			tbAreaCatalog = tbAreaCatalogService.getEntity(TbAreaCatalogEntity.class, tbAreaCatalog.getId());
			req.setAttribute("tbAreaCatalogPage", tbAreaCatalog);
		}
		return new ModelAndView("xjnu/edu/weixin/mall/job/tbAreaCatalog");
	}
}
