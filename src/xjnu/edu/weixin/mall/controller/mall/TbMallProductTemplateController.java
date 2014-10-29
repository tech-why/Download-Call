package xjnu.edu.weixin.mall.controller.mall;
import java.io.IOException;
import java.io.PrintWriter;
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

import xjnu.edu.weixin.mall.entity.mall.TbMallProductCatalogEntity;
import xjnu.edu.weixin.mall.entity.mall.TbMallProductClassEntity;
import xjnu.edu.weixin.mall.entity.mall.TbMallProductTemplateEntity;
import xjnu.edu.weixin.mall.json.mall.TbMallProductCatalogEntityJson;
import xjnu.edu.weixin.mall.service.mall.TbMallProductTemplateServiceI;

/**   
 * @Title: Controller
 * @Description: 商品管理
 * @date 2014-08-05 12:49:12
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/tbMallProductTemplateController")
public class TbMallProductTemplateController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TbMallProductTemplateController.class);

	@Autowired
	private TbMallProductTemplateServiceI tbMallProductTemplateService;
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
	 * 商品管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "tbMallProductTemplate")
	public ModelAndView tbMallProductTemplate(HttpServletRequest request) {
		   // 给小类外键查询条件中的下拉框准备数据
			List<TbMallProductCatalogEntity> tbMallProductCatalogEntityList = systemService.getList(TbMallProductCatalogEntity.class);
			String catalogName=RoletoJson.listToReplaceStr(tbMallProductCatalogEntityList, "catalogName", "id");
			if(!catalogName.equals("")){
				request.setAttribute("catalogNameReplace", catalogName);
			}else{
				request.setAttribute("catalogNameReplace", " _0");
			}
		
		 // 给大类外键查询条件中的下拉框准备数据
		List<TbMallProductClassEntity> tbMallProductClassEntityList = systemService.getList(TbMallProductClassEntity.class);
		String className=RoletoJson.listToReplaceStr(tbMallProductClassEntityList, "className", "id");
		if(!className.equals("")){
			request.setAttribute("classNameReplace", className);
		}else{
			request.setAttribute("classNameReplace", " _0");
		}
		
		return new ModelAndView("xjnu/edu/weixin/mall/mall/tbMallProductTemplateList");
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
	public void datagrid(TbMallProductTemplateEntity tbMallProductTemplate,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TbMallProductTemplateEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tbMallProductTemplate, request.getParameterMap());
		this.tbMallProductTemplateService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除商品管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TbMallProductTemplateEntity tbMallProductTemplate, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		tbMallProductTemplate = systemService.getEntity(TbMallProductTemplateEntity.class, tbMallProductTemplate.getId());
		message = "商品管理删除成功";
		tbMallProductTemplateService.delete(tbMallProductTemplate);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加商品管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(TbMallProductTemplateEntity tbMallProductTemplate, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(tbMallProductTemplate.getId())) {
			message = "商品管理更新成功";
			TbMallProductTemplateEntity t = tbMallProductTemplateService.get(TbMallProductTemplateEntity.class, tbMallProductTemplate.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(tbMallProductTemplate, t);
				tbMallProductTemplateService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "商品管理更新失败";
			}
		} else {
			message = "商品管理添加成功";
			tbMallProductTemplateService.save(tbMallProductTemplate);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 商品管理列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TbMallProductTemplateEntity tbMallProductTemplate, HttpServletRequest req) {
		//传入小类信息
		 List<TbMallProductCatalogEntity> tbMallProductCatalogEntityList = new ArrayList<TbMallProductCatalogEntity>();
		 tbMallProductCatalogEntityList.addAll((List)systemService.getList(TbMallProductCatalogEntity.class));
		    req.setAttribute("tbMallProductCatalogEntityList", tbMallProductCatalogEntityList);
		if (StringUtil.isNotEmpty(tbMallProductTemplate.getId())) {
			tbMallProductTemplate = tbMallProductTemplateService.getEntity(TbMallProductTemplateEntity.class, tbMallProductTemplate.getId());
			req.setAttribute("tbMallProductTemplatePage", tbMallProductTemplate);
		}
		return new ModelAndView("xjnu/edu/weixin/mall/mall/tbMallProductTemplate");
	}
	
	
	
	/**
	 *小类json
	 * 
	 * @return
	 */
	@RequestMapping(params = "getCatalog")
	public void getCatalog( HttpServletRequest req,HttpServletResponse response) {
//		System.out.println("-------------------------");
		AjaxJson j = new AjaxJson();
	    String classId=req.getParameter("parentId");
		List<TbMallProductCatalogEntity> catalogList = systemService.findByProperty(TbMallProductCatalogEntity.class, "classId", classId);
		List<TbMallProductCatalogEntityJson> catalog=new ArrayList<TbMallProductCatalogEntityJson>();
		for(TbMallProductCatalogEntity pc : catalogList){
			TbMallProductCatalogEntityJson ca =new TbMallProductCatalogEntityJson();
			ca.setId(pc.getId());
			ca.setCatalogName(pc.getCatalogName());
			catalog.add(ca);
		}
		j.setObj(catalog);
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
