package xjnu.edu.weixin.mall.controller.mall;
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

import xjnu.edu.weixin.mall.entity.mall.TbMallWorkTimeTemplaneEntity;
import xjnu.edu.weixin.mall.service.mall.TbMallWorkTimeTemplaneServiceI;

/**   
 * @Title: Controller
 * @Description: 送货时间管理
 * @date 2014-08-05 12:52:45
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/tbMallWorkTimeTemplaneController")
public class TbMallWorkTimeTemplaneController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TbMallWorkTimeTemplaneController.class);

	@Autowired
	private TbMallWorkTimeTemplaneServiceI tbMallWorkTimeTemplaneService;
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
	 * 送货时间管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "tbMallWorkTimeTemplane")
	public ModelAndView tbMallWorkTimeTemplane(HttpServletRequest request) {
		return new ModelAndView("xjnu/edu/weixin/mall/mall/tbMallWorkTimeTemplaneList");
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
	public void datagrid(TbMallWorkTimeTemplaneEntity tbMallWorkTimeTemplane,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TbMallWorkTimeTemplaneEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tbMallWorkTimeTemplane, request.getParameterMap());
		this.tbMallWorkTimeTemplaneService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除送货时间管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TbMallWorkTimeTemplaneEntity tbMallWorkTimeTemplane, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		tbMallWorkTimeTemplane = systemService.getEntity(TbMallWorkTimeTemplaneEntity.class, tbMallWorkTimeTemplane.getId());
		message = "送货时间管理删除成功";
		tbMallWorkTimeTemplaneService.delete(tbMallWorkTimeTemplane);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加送货时间管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(TbMallWorkTimeTemplaneEntity tbMallWorkTimeTemplane, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(tbMallWorkTimeTemplane.getId())) {
			message = "送货时间管理更新成功";
			TbMallWorkTimeTemplaneEntity t = tbMallWorkTimeTemplaneService.get(TbMallWorkTimeTemplaneEntity.class, tbMallWorkTimeTemplane.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(tbMallWorkTimeTemplane, t);
				tbMallWorkTimeTemplaneService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "送货时间管理更新失败";
			}
		} else {
			message = "送货时间管理添加成功";
			tbMallWorkTimeTemplaneService.save(tbMallWorkTimeTemplane);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 送货时间管理列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TbMallWorkTimeTemplaneEntity tbMallWorkTimeTemplane, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(tbMallWorkTimeTemplane.getId())) {
			tbMallWorkTimeTemplane = tbMallWorkTimeTemplaneService.getEntity(TbMallWorkTimeTemplaneEntity.class, tbMallWorkTimeTemplane.getId());
			req.setAttribute("tbMallWorkTimeTemplanePage", tbMallWorkTimeTemplane);
		}
		return new ModelAndView("xjnu/edu/weixin/mall/mall/tbMallWorkTimeTemplane");
	}
}
