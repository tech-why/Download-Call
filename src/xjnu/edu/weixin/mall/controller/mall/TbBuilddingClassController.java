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
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;
import xjnu.edu.weixin.mall.entity.mall.TbBuilddingClass;
import xjnu.edu.weixin.mall.service.mall.TbBuilddingClassServiceI;

/**   
 * @Title: Controller
 * @Description: 建筑大类管理
 * @date 2014-08-07 22:02:33
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/tbBuilddingClassController")
public class TbBuilddingClassController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TbBuilddingClassController.class);

	@Autowired
	private TbBuilddingClassServiceI tbBuilddingClassService;
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
	 * 建筑大类管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "tbBuilddingClass")
	public ModelAndView tbBuilddingClass(HttpServletRequest request) {
		return new ModelAndView("xjnu/edu/weixin/mall/mall/tbBuilddingClassList");
	}
	
	/**
	 * 返回所有大类小类JSON
	 */
	@RequestMapping(params = "datagrid1")
	public void datagrid1(TbBuilddingClass tbBuilddingClass,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		AjaxJson j=new AjaxJson();
		List<TbBuilddingClass> tbclass = tbBuilddingClassService.loadAll(TbBuilddingClass.class);
		j.setObj(tbclass);
		response.setContentType("text/html;charset=UTF-8");
	    PrintWriter out;
		try {
			out = response.getWriter();
			out.print(j.getJsonStr());
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	public void datagrid(TbBuilddingClass tbBuilddingClass,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TbBuilddingClass.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tbBuilddingClass, request.getParameterMap());
		this.tbBuilddingClassService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除建筑大类管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TbBuilddingClass tbBuilddingClass, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		tbBuilddingClass = systemService.getEntity(TbBuilddingClass.class, tbBuilddingClass.getId());
		message = "建筑大类管理删除成功";
		tbBuilddingClassService.delete(tbBuilddingClass);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加建筑大类管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(TbBuilddingClass tbBuilddingClass, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(tbBuilddingClass.getId())) {
			message = "建筑大类管理更新成功";
			TbBuilddingClass t = tbBuilddingClassService.get(TbBuilddingClass.class, tbBuilddingClass.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(tbBuilddingClass, t);
				tbBuilddingClassService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "建筑大类管理更新失败";
			}
		} else {
			message = "建筑大类管理添加成功";
			tbBuilddingClassService.save(tbBuilddingClass);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 建筑大类管理列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TbBuilddingClass tbBuilddingClass, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(tbBuilddingClass.getId())) {
			tbBuilddingClass = tbBuilddingClassService.getEntity(TbBuilddingClass.class, tbBuilddingClass.getId());
			req.setAttribute("tbBuilddingClassPage", tbBuilddingClass);
		}
		return new ModelAndView("xjnu/edu/weixin/mall/mall/tbBuilddingClass");
	}
}
