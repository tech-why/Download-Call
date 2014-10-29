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
import xjnu.edu.weixin.mall.entity.mall.TbTelCatalogEntity;
import xjnu.edu.weixin.mall.entity.mall.TbTelClassEntity;
import xjnu.edu.weixin.mall.service.mall.TbTelCatalogServiceI;

/**   
 * @Title: Controller
 * @Description: 号码小类
 * @author zhangdaihao
 * @date 2014-08-25 18:10:12
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/tbTelCatalogController")
public class TbTelCatalogController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TbTelCatalogController.class);

	@Autowired
	private TbTelCatalogServiceI tbTelCatalogService;
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
	 * 号码小类列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "tbTelCatalog")
	public ModelAndView tbTelCatalog(HttpServletRequest request) {
		
		 // 给大类外键查询条件中的下拉框准备数据
		List<TbTelClassEntity> TbTelClassEntityList = systemService.getList(TbTelClassEntity.class);
		String telClassName = RoletoJson.listToReplaceStr(TbTelClassEntityList, "telClassName", "id");
		if(!telClassName.equals("")){
			request.setAttribute("telClassNameReplace", telClassName);	
		}else{
			request.setAttribute("telClassNameReplace", " _0");
		}

		
		return new ModelAndView("xjnu/edu/weixin/mall/mall/tbTelCatalogList");
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
	public void datagrid(TbTelCatalogEntity tbTelCatalog,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TbTelCatalogEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tbTelCatalog, request.getParameterMap());
		this.tbTelCatalogService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除号码小类
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TbTelCatalogEntity tbTelCatalog, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		tbTelCatalog = systemService.getEntity(TbTelCatalogEntity.class, tbTelCatalog.getId());
		message = "号码小类删除成功";
		tbTelCatalogService.delete(tbTelCatalog);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加号码小类
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(TbTelCatalogEntity tbTelCatalog, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(tbTelCatalog.getId())) {
			message = "号码小类更新成功";
			TbTelCatalogEntity t = tbTelCatalogService.get(TbTelCatalogEntity.class, tbTelCatalog.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(tbTelCatalog, t);
				tbTelCatalogService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "号码小类更新失败";
			}
		} else {
			message = "号码小类添加成功";
			tbTelCatalogService.save(tbTelCatalog);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 号码小类列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TbTelCatalogEntity tbTelCatalog, HttpServletRequest req) {
		
		//传入大类的信息
	    List<TbTelClassEntity> tbTelClassEntityList = new ArrayList<TbTelClassEntity>();
	    tbTelClassEntityList.addAll((List)systemService.getList(TbTelClassEntity.class));
	    req.setAttribute("tbTelClassEntityList", tbTelClassEntityList);
		
		if (StringUtil.isNotEmpty(tbTelCatalog.getId())) {
			tbTelCatalog = tbTelCatalogService.getEntity(TbTelCatalogEntity.class, tbTelCatalog.getId());
			req.setAttribute("tbTelCatalogPage", tbTelCatalog);
		}
		return new ModelAndView("xjnu/edu/weixin/mall/mall/tbTelCatalog");
	}
}
