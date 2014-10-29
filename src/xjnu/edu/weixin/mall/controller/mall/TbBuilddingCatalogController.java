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
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;
import xjnu.edu.weixin.mall.entity.mall.TbBuilddingCatalog;
import xjnu.edu.weixin.mall.entity.mall.TbBuilddingClass;
import xjnu.edu.weixin.mall.service.mall.TbBuilddingCatalogServiceI;

/**   
 * @Title: Controller
 * @Description: 建筑小类管理
 * @date 2014-08-07 22:03:09
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/tbBuilddingCatalogController")
public class TbBuilddingCatalogController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TbBuilddingCatalogController.class);

	@Autowired
	private TbBuilddingCatalogServiceI tbBuilddingCatalogService;
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
	 * 建筑小类管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "tbBuilddingCatalog")
	public ModelAndView tbBuilddingCatalog(HttpServletRequest request) {
		       // 给建筑大类外键查询条件中的下拉框准备数据
				List<TbBuilddingClass> tbBuilddingClassEntityList = systemService.getList(TbBuilddingClass.class);
				String className=RoletoJson.listToReplaceStr(tbBuilddingClassEntityList, "className", "id");
				if(!className.equals("")){
					request.setAttribute("classReplace", className);
				}else{
					request.setAttribute("classReplace", " _0");
				}
		return new ModelAndView("xjnu/edu/weixin/mall/mall/tbBuilddingCatalogList");
	}

	/**
	 * 返回所有小类JSON
	 *//*
	@RequestMapping(params = "datagrid1")
	public void datagrid1(TbBuilddingCatalog tbBuilddingCatalog,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		AjaxJson j = new AjaxJson();
		List<TbBuilddingCatalog> tbBuilddingclass = tbBuilddingCatalogService.loadAll(TbBuilddingCatalog.class);
		j.setObj(tbBuilddingclass);
//		System.out.println("------------j.getJsonStr()-------------->"+j.getJsonStr());
		response.setContentType("text/html;charset=UTF-8");
	    PrintWriter out;
		try {
			out = response.getWriter();
			out.print(j.getJsonStr());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(TbBuilddingCatalog tbBuilddingCatalog,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TbBuilddingCatalog.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tbBuilddingCatalog, request.getParameterMap());
		this.tbBuilddingCatalogService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除建筑小类管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TbBuilddingCatalog tbBuilddingCatalog, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		tbBuilddingCatalog = systemService.getEntity(TbBuilddingCatalog.class, tbBuilddingCatalog.getId());
		message = "建筑小类管理删除成功";
		tbBuilddingCatalogService.delete(tbBuilddingCatalog);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加建筑小类管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(TbBuilddingCatalog tbBuilddingCatalog, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(tbBuilddingCatalog.getId())) {
			message = "建筑小类管理更新成功";
			TbBuilddingCatalog t = tbBuilddingCatalogService.get(TbBuilddingCatalog.class, tbBuilddingCatalog.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(tbBuilddingCatalog, t);
				tbBuilddingCatalogService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "建筑小类管理更新失败";
			}
		} else {
			message = "建筑小类管理添加成功";
			tbBuilddingCatalogService.save(tbBuilddingCatalog);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 建筑小类管理列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TbBuilddingCatalog tbBuilddingCatalog, HttpServletRequest req) {
		 //传入小类建筑的信息
	    List<TbBuilddingClass> tbBuilddingClassEntityList = new ArrayList<TbBuilddingClass>();
	    tbBuilddingClassEntityList.addAll((List)systemService.getList(TbBuilddingClass.class));
	    req.setAttribute("tbBuilddingClassEntityList", tbBuilddingClassEntityList);
		if (StringUtil.isNotEmpty(tbBuilddingCatalog.getId())) {
			tbBuilddingCatalog = tbBuilddingCatalogService.getEntity(TbBuilddingCatalog.class, tbBuilddingCatalog.getId());
			req.setAttribute("tbBuilddingCatalogPage", tbBuilddingCatalog);
		}
		return new ModelAndView("xjnu/edu/weixin/mall/mall/tbBuilddingCatalog");
	}
}
