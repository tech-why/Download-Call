package xjnu.edu.weixin.mall.controller.mall;
import java.io.IOException;
import java.io.PrintWriter;
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

import xjnu.edu.weixin.mall.entity.mall.TbMallProductTemplateEntity;
import xjnu.edu.weixin.mall.entity.mall.TbMallWorkTimeEntity;
import xjnu.edu.weixin.mall.entity.mall.TbMallWorkTimeTemplaneEntity;
import xjnu.edu.weixin.mall.service.mall.TbMallWorkTimeServiceI;

/**   
 * @Title: Controller
 * @Description: 商铺配送时间
 * @date 2014-08-05 12:55:24
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/tbMallWorkTimeController")
public class TbMallWorkTimeController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TbMallWorkTimeController.class);

	@Autowired
	private TbMallWorkTimeServiceI tbMallWorkTimeService;
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
	 * 商铺配送时间列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "tbMallWorkTime")
	public ModelAndView tbMallWorkTime(HttpServletRequest request) {
		return new ModelAndView("xjnu/edu/weixin/mall/mall/tbMallWorkTimeList");
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
	public void datagrid(TbMallWorkTimeEntity tbMallWorkTime,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TbMallWorkTimeEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tbMallWorkTime, request.getParameterMap());
		this.tbMallWorkTimeService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除商铺配送时间
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TbMallWorkTimeEntity tbMallWorkTime, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		tbMallWorkTime = systemService.getEntity(TbMallWorkTimeEntity.class, tbMallWorkTime.getId());
		message = "商铺配送时间删除成功";
		tbMallWorkTimeService.delete(tbMallWorkTime);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加商铺配送时间
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(TbMallWorkTimeEntity tbMallWorkTime, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(tbMallWorkTime.getId())) {
			message = "商铺配送时间更新成功";
			TbMallWorkTimeEntity t = tbMallWorkTimeService.get(TbMallWorkTimeEntity.class, tbMallWorkTime.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(tbMallWorkTime, t);
				tbMallWorkTimeService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "商铺配送时间更新失败";
			}
		} else {
			message = "商铺配送时间添加成功";
			tbMallWorkTimeService.save(tbMallWorkTime);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 商铺配送时间列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TbMallWorkTimeEntity tbMallWorkTime, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(tbMallWorkTime.getId())) {
			tbMallWorkTime = tbMallWorkTimeService.getEntity(TbMallWorkTimeEntity.class, tbMallWorkTime.getId());
			req.setAttribute("tbMallWorkTimePage", tbMallWorkTime);
		}
		return new ModelAndView("xjnu/edu/weixin/mall/mall/tbMallWorkTime");
	}
	
	
	
	
	
	/**
	 * 配送时间上架模板信息
	 * @return 
	 * @return
	 */
	@RequestMapping(params = "addWorkTime")
	public void addWorkTime(TbMallWorkTimeTemplaneEntity tbMallWorkTimeTemplane, HttpServletRequest request,HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		String workTimeId=request.getParameter("workTimeId");
		if (StringUtil.isNotEmpty(workTimeId)) {
			tbMallWorkTimeTemplane = tbMallWorkTimeService.getEntity(TbMallWorkTimeTemplaneEntity.class, workTimeId);
			request.setAttribute("tbMallWorkTimeTemplane", tbMallWorkTimeTemplane);
		}
		j.setObj(tbMallWorkTimeTemplane);
		System.out.println("------------------asd--------------------"+j.getJsonStr());
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(j.getJsonStr());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
