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

import xjnu.edu.weixin.mall.entity.job.TbJobClassEntity;
import xjnu.edu.weixin.mall.service.job.TbJobClassServiceI;

/**   
 * @Title: Controller
 * @Description: 工作大类
 * @author zhangdaihao
 * @date 2014-09-16 12:28:00
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/tbJobClassController")
public class TbJobClassController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TbJobClassController.class);

	@Autowired
	private TbJobClassServiceI tbJobClassService;
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
	 * 工作大类列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "tbJobClass")
	public ModelAndView tbJobClass(HttpServletRequest request) {
		return new ModelAndView("xjnu/edu/weixin/mall/job/tbJobClassList");
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
	public void datagrid(TbJobClassEntity tbJobClass,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TbJobClassEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tbJobClass, request.getParameterMap());
		this.tbJobClassService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除工作大类
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TbJobClassEntity tbJobClass, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		tbJobClass = systemService.getEntity(TbJobClassEntity.class, tbJobClass.getId());
		message = "工作大类删除成功";
		tbJobClassService.delete(tbJobClass);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加工作大类
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(TbJobClassEntity tbJobClass, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(tbJobClass.getId())) {
			message = "工作大类更新成功";
			TbJobClassEntity t = tbJobClassService.get(TbJobClassEntity.class, tbJobClass.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(tbJobClass, t);
				tbJobClassService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "工作大类更新失败";
			}
		} else {
			message = "工作大类添加成功";
			tbJobClassService.save(tbJobClass);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 工作大类列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TbJobClassEntity tbJobClass, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(tbJobClass.getId())) {
			tbJobClass = tbJobClassService.getEntity(TbJobClassEntity.class, tbJobClass.getId());
			req.setAttribute("tbJobClassPage", tbJobClass);
		}
		return new ModelAndView("xjnu/edu/weixin/mall/job/tbJobClass");
	}
}
