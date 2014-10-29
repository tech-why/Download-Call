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

import xjnu.edu.weixin.mall.entity.mall.TbMallProductCatalogEntity;
import xjnu.edu.weixin.mall.entity.mall.TbMallProductClassEntity;
import xjnu.edu.weixin.mall.entity.mall.TbSchoolEntity;
import xjnu.edu.weixin.mall.service.mall.TbMallProductCatalogServiceI;

/**   
 * @Title: Controller
 * @Description: 小类管理
 * @date 2014-08-05 12:48:40
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/tbMallProductCatalogController")
public class TbMallProductCatalogController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TbMallProductCatalogController.class);

	@Autowired
	private TbMallProductCatalogServiceI tbMallProductCatalogService;
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
	 * 小类管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "tbMallProductCatalog")
	public ModelAndView tbMallProductCatalog(HttpServletRequest request) {
		      // 给大类外键查询条件中的下拉框准备数据
				List<TbMallProductClassEntity> tbMallProductClassEntityList = systemService.getList(TbMallProductClassEntity.class);
				String className=RoletoJson.listToReplaceStr(tbMallProductClassEntityList, "className", "id");
				if(!className.equals("")){
					request.setAttribute("classNameReplace", className);
				}else{
					request.setAttribute("classNameReplace", " _0");
				}
		return new ModelAndView("xjnu/edu/weixin/mall/mall/tbMallProductCatalogList");
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
	public void datagrid(TbMallProductCatalogEntity tbMallProductCatalog,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TbMallProductCatalogEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tbMallProductCatalog, request.getParameterMap());
		this.tbMallProductCatalogService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除小类管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TbMallProductCatalogEntity tbMallProductCatalog, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		tbMallProductCatalog = systemService.getEntity(TbMallProductCatalogEntity.class, tbMallProductCatalog.getId());
		message = "小类管理删除成功";
		tbMallProductCatalogService.delete(tbMallProductCatalog);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加小类管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(TbMallProductCatalogEntity tbMallProductCatalog, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(tbMallProductCatalog.getId())) {
			message = "小类管理更新成功";
			TbMallProductCatalogEntity t = tbMallProductCatalogService.get(TbMallProductCatalogEntity.class, tbMallProductCatalog.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(tbMallProductCatalog, t);
				tbMallProductCatalogService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "小类管理更新失败";
			}
		} else {
			message = "小类管理添加成功";
			tbMallProductCatalogService.save(tbMallProductCatalog);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 小类管理列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TbMallProductCatalogEntity tbMallProductCatalog, HttpServletRequest req) {
		 //传入大类信息
		 List<TbMallProductClassEntity> tbMallProductClassEntityList = new ArrayList<TbMallProductClassEntity>();
		 tbMallProductClassEntityList.addAll((List)systemService.getList(TbMallProductClassEntity.class));
		    req.setAttribute("tbMallProductClassEntityList", tbMallProductClassEntityList);
		if (StringUtil.isNotEmpty(tbMallProductCatalog.getId())) {
			tbMallProductCatalog = tbMallProductCatalogService.getEntity(TbMallProductCatalogEntity.class, tbMallProductCatalog.getId());
			req.setAttribute("tbMallProductCatalogPage", tbMallProductCatalog);
		}
		return new ModelAndView("xjnu/edu/weixin/mall/mall/tbMallProductCatalog");
	}
}
