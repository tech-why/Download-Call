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

import xjnu.edu.weixin.repair.entity.repair.TbOperaterTypeEntity;
import xjnu.edu.weixin.repair.service.repair.TbOperaterTypeServiceI;

/**   
 * @Title: Controller
 * @Description: 操作类型
 * @author zhangdaihao
 * @date 2014-09-24 11:32:51
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/tbOperaterTypeController")
public class TbOperaterTypeController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TbOperaterTypeController.class);

	@Autowired
	private TbOperaterTypeServiceI tbOperaterTypeService;
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
	 * 操作类型列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "tbOperaterType")
	public ModelAndView tbOperaterType(HttpServletRequest request) {
		return new ModelAndView("xjnu/edu/weixin/repair/repair/tbOperaterTypeList");
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
	public void datagrid(TbOperaterTypeEntity tbOperaterType,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TbOperaterTypeEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tbOperaterType, request.getParameterMap());
		this.tbOperaterTypeService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除操作类型
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TbOperaterTypeEntity tbOperaterType, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		tbOperaterType = systemService.getEntity(TbOperaterTypeEntity.class, tbOperaterType.getId());
		message = "操作类型删除成功";
		tbOperaterTypeService.delete(tbOperaterType);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加操作类型
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(TbOperaterTypeEntity tbOperaterType, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(tbOperaterType.getId())) {
			message = "操作类型更新成功";
			TbOperaterTypeEntity t = tbOperaterTypeService.get(TbOperaterTypeEntity.class, tbOperaterType.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(tbOperaterType, t);
				tbOperaterTypeService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "操作类型更新失败";
			}
		} else {
			message = "操作类型添加成功";
			tbOperaterTypeService.save(tbOperaterType);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 操作类型列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TbOperaterTypeEntity tbOperaterType, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(tbOperaterType.getId())) {
			tbOperaterType = tbOperaterTypeService.getEntity(TbOperaterTypeEntity.class, tbOperaterType.getId());
			req.setAttribute("tbOperaterTypePage", tbOperaterType);
		}
		return new ModelAndView("xjnu/edu/weixin/repair/repair/tbOperaterType");
	}
}
