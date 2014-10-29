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

import xjnu.edu.weixin.mall.entity.mall.TbMallShopEntity;
import xjnu.edu.weixin.mall.entity.mall.TbSchoolEntity;
import xjnu.edu.weixin.mall.entity.mall.TbTelClassEntity;
import xjnu.edu.weixin.mall.service.mall.TbTelClassServiceI;

/**   
 * @Title: Controller
 * @Description: 号码大类
 * @author zhangdaihao
 * @date 2014-08-25 18:08:51
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/tbTelClassController")
public class TbTelClassController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TbTelClassController.class);

	@Autowired
	private TbTelClassServiceI tbTelClassService;
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
	 * 号码大类列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "tbTelClass")
	public ModelAndView tbTelClass(HttpServletRequest request) {
		      
		
		
		return new ModelAndView("xjnu/edu/weixin/mall/mall/tbTelClassList");
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
	public void datagrid(TbTelClassEntity tbTelClass,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TbTelClassEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tbTelClass, request.getParameterMap());
		this.tbTelClassService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除号码大类
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TbTelClassEntity tbTelClass, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		tbTelClass = systemService.getEntity(TbTelClassEntity.class, tbTelClass.getId());
		message = "号码大类删除成功";
		tbTelClassService.delete(tbTelClass);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加号码大类
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(TbTelClassEntity tbTelClass, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(tbTelClass.getId())) {
			message = "号码大类更新成功";
			TbTelClassEntity t = tbTelClassService.get(TbTelClassEntity.class, tbTelClass.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(tbTelClass, t);
				tbTelClassService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "号码大类更新失败";
			}
		} else {
			message = "号码大类添加成功";
			tbTelClassService.save(tbTelClass);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 号码大类列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TbTelClassEntity tbTelClass, HttpServletRequest req) {
		
	    
		if (StringUtil.isNotEmpty(tbTelClass.getId())) {
			tbTelClass = tbTelClassService.getEntity(TbTelClassEntity.class, tbTelClass.getId());
			req.setAttribute("tbTelClassPage", tbTelClass);
		}
		return new ModelAndView("xjnu/edu/weixin/mall/mall/tbTelClass");
	}
}
