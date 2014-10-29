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

import xjnu.edu.weixin.mall.entity.job.TbCompanyEntity;
import xjnu.edu.weixin.mall.service.job.TbCompanyServiceI;

/**   
 * @Title: Controller
 * @Description: 公司
 * @author zhangdaihao
 * @date 2014-09-16 12:29:41
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/tbCompanyController")
public class TbCompanyController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TbCompanyController.class);

	@Autowired
	private TbCompanyServiceI tbCompanyService;
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
	 * 公司列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "tbCompany")
	public ModelAndView tbCompany(HttpServletRequest request) {
		return new ModelAndView("xjnu/edu/weixin/mall/job/tbCompanyList");
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
	public void datagrid(TbCompanyEntity tbCompany,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TbCompanyEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tbCompany, request.getParameterMap());
		this.tbCompanyService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除公司
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TbCompanyEntity tbCompany, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		tbCompany = systemService.getEntity(TbCompanyEntity.class, tbCompany.getId());
		message = "公司删除成功";
		tbCompanyService.delete(tbCompany);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加公司
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(TbCompanyEntity tbCompany, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(tbCompany.getId())) {
			message = "公司更新成功";
			TbCompanyEntity t = tbCompanyService.get(TbCompanyEntity.class, tbCompany.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(tbCompany, t);
				tbCompanyService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "公司更新失败";
			}
		} else {
			message = "公司添加成功";
			tbCompanyService.save(tbCompany);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 公司列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TbCompanyEntity tbCompany, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(tbCompany.getId())) {
			tbCompany = tbCompanyService.getEntity(TbCompanyEntity.class, tbCompany.getId());
			req.setAttribute("tbCompanyPage", tbCompany);
		}
		return new ModelAndView("xjnu/edu/weixin/mall/job/tbCompany");
	}
}
