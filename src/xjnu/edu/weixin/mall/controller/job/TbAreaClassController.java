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

import xjnu.edu.weixin.mall.entity.job.TbAreaClassEntity;
import xjnu.edu.weixin.mall.service.job.TbAreaClassServiceI;

/**   
 * @Title: Controller
 * @Description: 区域大类表
 * @author zhangdaihao
 * @date 2014-09-16 12:31:14
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/tbAreaClassController")
public class TbAreaClassController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TbAreaClassController.class);

	@Autowired
	private TbAreaClassServiceI tbAreaClassService;
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
	 * 区域大类表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "tbAreaClass")
	public ModelAndView tbAreaClass(HttpServletRequest request) {
		return new ModelAndView("xjnu/edu/weixin/mall/job/tbAreaClassList");
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
	public void datagrid(TbAreaClassEntity tbAreaClass,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TbAreaClassEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tbAreaClass, request.getParameterMap());
		this.tbAreaClassService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除区域大类表
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TbAreaClassEntity tbAreaClass, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		tbAreaClass = systemService.getEntity(TbAreaClassEntity.class, tbAreaClass.getId());
		message = "区域大类表删除成功";
		tbAreaClassService.delete(tbAreaClass);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加区域大类表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(TbAreaClassEntity tbAreaClass, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(tbAreaClass.getId())) {
			message = "区域大类表更新成功";
			TbAreaClassEntity t = tbAreaClassService.get(TbAreaClassEntity.class, tbAreaClass.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(tbAreaClass, t);
				tbAreaClassService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "区域大类表更新失败";
			}
		} else {
			message = "区域大类表添加成功";
			tbAreaClassService.save(tbAreaClass);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 区域大类表列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TbAreaClassEntity tbAreaClass, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(tbAreaClass.getId())) {
			tbAreaClass = tbAreaClassService.getEntity(TbAreaClassEntity.class, tbAreaClass.getId());
			req.setAttribute("tbAreaClassPage", tbAreaClass);
		}
		return new ModelAndView("xjnu/edu/weixin/mall/job/tbAreaClass");
	}
}
