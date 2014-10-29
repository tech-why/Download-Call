package xjnu.edu.weixin.repair.controller.repair;
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

import xjnu.edu.weixin.repair.entity.repair.TbOperaterBuilddingEntity;
import xjnu.edu.weixin.repair.service.repair.TbOperaterBuilddingServiceI;

/**   
 * @Title: Controller
 * @Description: 维修人员分配表
 * @author zhangdaihao
 * @date 2014-09-16 16:56:20
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/tbOperaterBuilddingController")
public class TbOperaterBuilddingController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TbOperaterBuilddingController.class);

	@Autowired
	private TbOperaterBuilddingServiceI tbOperaterBuilddingService;
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
	 * 维修人员分配表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "tbOperaterBuildding")
	public ModelAndView tbOperaterBuildding(HttpServletRequest request) {
		return new ModelAndView("xjnu/edu/weixin/repair/repair/tbOperaterBuilddingList");
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
	public void datagrid(TbOperaterBuilddingEntity tbOperaterBuildding,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TbOperaterBuilddingEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tbOperaterBuildding, request.getParameterMap());
		this.tbOperaterBuilddingService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除维修人员分配表
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TbOperaterBuilddingEntity tbOperaterBuildding, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		tbOperaterBuildding = systemService.getEntity(TbOperaterBuilddingEntity.class, tbOperaterBuildding.getId());
		message = "维修人员分配表删除成功";
		tbOperaterBuilddingService.delete(tbOperaterBuildding);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加维修人员分配表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(TbOperaterBuilddingEntity tbOperaterBuildding, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(tbOperaterBuildding.getId())) {
			message = "维修人员分配表更新成功";
			TbOperaterBuilddingEntity t = tbOperaterBuilddingService.get(TbOperaterBuilddingEntity.class, tbOperaterBuildding.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(tbOperaterBuildding, t);
				tbOperaterBuilddingService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "维修人员分配表更新失败";
			}
		} else {
			message = "维修人员分配表添加成功";
			tbOperaterBuilddingService.save(tbOperaterBuildding);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 维修人员分配表列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TbOperaterBuilddingEntity tbOperaterBuildding, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(tbOperaterBuildding.getId())) {
			tbOperaterBuildding = tbOperaterBuilddingService.getEntity(TbOperaterBuilddingEntity.class, tbOperaterBuildding.getId());
			req.setAttribute("tbOperaterBuilddingPage", tbOperaterBuildding);
		}
		return new ModelAndView("xjnu/edu/weixin/repair/repair/tbOperaterBuildding");
	}
}
