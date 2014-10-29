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

import xjnu.edu.weixin.mall.entity.job.TbJobCatalogEntity;
import xjnu.edu.weixin.mall.entity.job.TbJobClassEntity;
import xjnu.edu.weixin.mall.entity.mall.TbMallProductClassEntity;
import xjnu.edu.weixin.mall.service.job.TbJobCatalogServiceI;

/**   
 * @Title: Controller
 * @Description: 工作小类
 * @author zhangdaihao
 * @date 2014-09-16 12:28:22
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/tbJobCatalogController")
public class TbJobCatalogController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TbJobCatalogController.class);

	@Autowired
	private TbJobCatalogServiceI tbJobCatalogService;
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
	 * 工作小类列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "tbJobCatalog")
	public ModelAndView tbJobCatalog(HttpServletRequest request) {
		// 给大类外键查询条件中的下拉框准备数据
		List<TbJobClassEntity> tbJobClassEntityList = systemService.getList(TbJobClassEntity.class);
		String jobClassName=RoletoJson.listToReplaceStr(tbJobClassEntityList, "jobClassName", "id");
		if(!jobClassName.equals("")){
			request.setAttribute("jobClassNameReplace", jobClassName);
		}else{
			request.setAttribute("jobClassNameReplace", " _0");
		}
		return new ModelAndView("xjnu/edu/weixin/mall/job/tbJobCatalogList");
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
	public void datagrid(TbJobCatalogEntity tbJobCatalog,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TbJobCatalogEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tbJobCatalog, request.getParameterMap());
		this.tbJobCatalogService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除工作小类
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TbJobCatalogEntity tbJobCatalog, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		tbJobCatalog = systemService.getEntity(TbJobCatalogEntity.class, tbJobCatalog.getId());
		message = "工作小类删除成功";
		tbJobCatalogService.delete(tbJobCatalog);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加工作小类
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(TbJobCatalogEntity tbJobCatalog, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(tbJobCatalog.getId())) {
			message = "工作小类更新成功";
			TbJobCatalogEntity t = tbJobCatalogService.get(TbJobCatalogEntity.class, tbJobCatalog.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(tbJobCatalog, t);
				tbJobCatalogService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "工作小类更新失败";
			}
		} else {
			message = "工作小类添加成功";
			tbJobCatalogService.save(tbJobCatalog);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 工作小类列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TbJobCatalogEntity tbJobCatalog, HttpServletRequest req) {
		//传入大类信息
		 List<TbJobClassEntity> tbJobClassEntityList = new ArrayList<TbJobClassEntity>();
		 tbJobClassEntityList.addAll((List)systemService.getList(TbJobClassEntity.class));
		    req.setAttribute("tbJobClassEntityList", tbJobClassEntityList);
		if (StringUtil.isNotEmpty(tbJobCatalog.getId())) {
			tbJobCatalog = tbJobCatalogService.getEntity(TbJobCatalogEntity.class, tbJobCatalog.getId());
			req.setAttribute("tbJobCatalogPage", tbJobCatalog);
		}
		return new ModelAndView("xjnu/edu/weixin/mall/job/tbJobCatalog");
	}
}
