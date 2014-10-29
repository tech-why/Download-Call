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
import xjnu.edu.weixin.mall.entity.job.TbCompanyEntity;
import xjnu.edu.weixin.mall.entity.job.TbJobCatalogEntity;
import xjnu.edu.weixin.mall.entity.job.TbParttimeJobEntity;
import xjnu.edu.weixin.mall.entity.job.TbSalaryTypeEntity;
import xjnu.edu.weixin.mall.service.job.TbParttimeJobServiceI;

/**   
 * @Title: Controller
 * @Description: 兼职
 * @author zhangdaihao
 * @date 2014-09-16 12:29:09
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/tbParttimeJobController")
public class TbParttimeJobController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TbParttimeJobController.class);

	@Autowired
	private TbParttimeJobServiceI tbParttimeJobService;
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
	 * 兼职列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "tbParttimeJob")
	public ModelAndView tbParttimeJob(HttpServletRequest request) {
		// 给区域小类外键查询条件中的下拉框准备数据
		List<TbAreaCatalogEntity> tbAreaCatalogEntityList = systemService.getList(TbAreaCatalogEntity.class);
		String catalogName=RoletoJson.listToReplaceStr(tbAreaCatalogEntityList, "catalogName", "id");
		if(!catalogName.equals("")){
			request.setAttribute("catalogNameReplace", catalogName);
		}else{
			request.setAttribute("catalogNameReplace", " _0");
		}
		// 给工作小类外键查询条件中的下拉框准备数据
		List<TbJobCatalogEntity> tbJobCatalogEntityList = systemService.getList(TbJobCatalogEntity.class);
		String jobCatalogName=RoletoJson.listToReplaceStr(tbJobCatalogEntityList, "jobCatalogName", "id");
		if(!catalogName.equals("")){
			request.setAttribute("jobCatalogNameReplace", jobCatalogName);
		}else{
			request.setAttribute("jobCatalogNameReplace", " _0");
		}
		// 给公司外键查询条件中的下拉框准备数据
		List<TbCompanyEntity> tbCompanyEntityList = systemService.getList(TbCompanyEntity.class);
		String name=RoletoJson.listToReplaceStr(tbCompanyEntityList, "name", "id");
		if(!name.equals("")){
			request.setAttribute("nameReplace", name);
		}else{
			request.setAttribute("nameReplace", " _0");
		}
		// 给工资类别外键查询条件中的下拉框准备数据
		List<TbSalaryTypeEntity> tbSalaryTypeEntityList = systemService.getList(TbSalaryTypeEntity.class);
		String salaryTypeName=RoletoJson.listToReplaceStr(tbSalaryTypeEntityList, "salaryTypeName", "id");
		if(!salaryTypeName.equals("")){
			request.setAttribute("salaryTypeNameReplace", salaryTypeName);
		}else{
			request.setAttribute("salaryTypeNameReplace", " _0");
		}
		return new ModelAndView("xjnu/edu/weixin/mall/job/tbParttimeJobList");
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
	public void datagrid(TbParttimeJobEntity tbParttimeJob,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TbParttimeJobEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tbParttimeJob, request.getParameterMap());
		this.tbParttimeJobService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除兼职
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TbParttimeJobEntity tbParttimeJob, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		tbParttimeJob = systemService.getEntity(TbParttimeJobEntity.class, tbParttimeJob.getId());
		message = "兼职删除成功";
		tbParttimeJobService.delete(tbParttimeJob);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加兼职
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(TbParttimeJobEntity tbParttimeJob, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(tbParttimeJob.getId())) {
			message = "兼职更新成功";
			TbParttimeJobEntity t = tbParttimeJobService.get(TbParttimeJobEntity.class, tbParttimeJob.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(tbParttimeJob, t);
				tbParttimeJobService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "兼职更新失败";
			}
		} else {
			message = "兼职添加成功";
			tbParttimeJobService.save(tbParttimeJob);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 兼职列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TbParttimeJobEntity tbParttimeJob, HttpServletRequest req) {
		//传入区域小类信息
		 List<TbAreaCatalogEntity> tbAreaCatalogEntityList = new ArrayList<TbAreaCatalogEntity>();
		 tbAreaCatalogEntityList.addAll((List)systemService.getList(TbAreaCatalogEntity.class));
	    req.setAttribute("tbAreaCatalogEntityList", tbAreaCatalogEntityList);
	    
	    //传入工作小类信息
	    List<TbJobCatalogEntity> tbJobCatalogEntityList = new ArrayList<TbJobCatalogEntity>();
	    tbJobCatalogEntityList.addAll((List)systemService.getList(TbJobCatalogEntity.class));
	    req.setAttribute("tbJobCatalogEntityList", tbJobCatalogEntityList);
	    
	    //传入公司信息
	    List<TbCompanyEntity> tbCompanyEntityList = new ArrayList<TbCompanyEntity>();
	    tbCompanyEntityList.addAll((List)systemService.getList(TbCompanyEntity.class));
	    req.setAttribute("tbCompanyEntityList", tbCompanyEntityList);
	    
	    //传入工资类别信息
	    List<TbSalaryTypeEntity> tbSalaryTypeEntityList = new ArrayList<TbSalaryTypeEntity>();
	    tbSalaryTypeEntityList.addAll((List)systemService.getList(TbSalaryTypeEntity.class));
	    req.setAttribute("tbSalaryTypeEntityList", tbSalaryTypeEntityList);
	    
		if (StringUtil.isNotEmpty(tbParttimeJob.getId())) {
			tbParttimeJob = tbParttimeJobService.getEntity(TbParttimeJobEntity.class, tbParttimeJob.getId());
			req.setAttribute("tbParttimeJobPage", tbParttimeJob);
		}
		return new ModelAndView("xjnu/edu/weixin/mall/job/tbParttimeJob");
	}
}
