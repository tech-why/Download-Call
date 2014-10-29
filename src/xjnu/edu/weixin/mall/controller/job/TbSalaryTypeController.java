package xjnu.edu.weixin.mall.controller.job;
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
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import xjnu.edu.weixin.mall.entity.job.TbSalaryTypeEntity;
import xjnu.edu.weixin.mall.service.job.TbSalaryTypeServiceI;

/**   
 * @Title: Controller
 * @Description: 工资类别
 * @author zhangdaihao
 * @date 2014-09-16 12:32:48
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/tbSalaryTypeController")
public class TbSalaryTypeController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TbSalaryTypeController.class);

	@Autowired
	private TbSalaryTypeServiceI tbSalaryTypeService;
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
	 * 工资类别列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "tbSalaryType")
	public ModelAndView tbSalaryType(HttpServletRequest request) {
		return new ModelAndView("xjnu/edu/weixin/mall/job/tbSalaryTypeList");
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
	public void datagrid(TbSalaryTypeEntity tbSalaryType,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TbSalaryTypeEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tbSalaryType, request.getParameterMap());
		this.tbSalaryTypeService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除工资类别
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TbSalaryTypeEntity tbSalaryType, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		tbSalaryType = systemService.getEntity(TbSalaryTypeEntity.class, tbSalaryType.getId());
		message = "工资类别删除成功";
		tbSalaryTypeService.delete(tbSalaryType);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加工资类别
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(TbSalaryTypeEntity tbSalaryType, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(tbSalaryType.getId())) {
			message = "工资类别更新成功";
			TbSalaryTypeEntity t = tbSalaryTypeService.get(TbSalaryTypeEntity.class, tbSalaryType.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(tbSalaryType, t);
				tbSalaryTypeService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "工资类别更新失败";
			}
		} else {
			message = "工资类别添加成功";
			tbSalaryTypeService.save(tbSalaryType);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 工资类别列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TbSalaryTypeEntity tbSalaryType, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(tbSalaryType.getId())) {
			tbSalaryType = tbSalaryTypeService.getEntity(TbSalaryTypeEntity.class, tbSalaryType.getId());
			req.setAttribute("tbSalaryTypePage", tbSalaryType);
		}
		return new ModelAndView("xjnu/edu/weixin/mall/job/tbSalaryType");
	}
}
