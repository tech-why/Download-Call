package xjnu.edu.weixin.mall.controller.mall;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
import xjnu.edu.weixin.mall.entity.mall.TbBuilddingEntity;
import xjnu.edu.weixin.mall.entity.mall.TbSchoolEntity;
import xjnu.edu.weixin.mall.json.mall.TbBuilddingEntityJson;
import xjnu.edu.weixin.mall.service.mall.TbBuilddingServiceI;

/**   
 * @Title: Controller
 * @Description: 建筑管理
 * @date 2014-08-05 12:47:42
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/tbBuilddingController")
public class TbBuilddingController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TbBuilddingController.class);

	@Autowired
	private TbBuilddingServiceI tbBuilddingService;
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
	 * 建筑管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "tbBuildding")
	public ModelAndView tbBuildding(HttpServletRequest request) {
		// 给学校外键查询条件中的下拉框准备数据
		List<TbSchoolEntity> TbSchoolEntityList = systemService.getList(TbSchoolEntity.class);
		String schoolName=RoletoJson.listToReplaceStr(TbSchoolEntityList, "schoolName", "id");
		if(!schoolName.equals("")){
			request.setAttribute("schoolReplace", schoolName);
		}else{
			request.setAttribute("schoolReplace", " _0");
		}
		
		// 给建筑小类外键查询条件中的下拉框准备数据
		List<TbBuilddingCatalog> tbBuilddingCatalogEntityList = systemService.getList(TbBuilddingCatalog.class);
		String catalogName=RoletoJson.listToReplaceStr(tbBuilddingCatalogEntityList, "catalogName", "id");
		if(!catalogName.equals("")){
			request.setAttribute("catalogReplace", catalogName);
		}else{
			request.setAttribute("catalogReplace", " _0");
		}
		return new ModelAndView("xjnu/edu/weixin/mall/mall/tbBuilddingList");
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
	public void datagrid(TbBuilddingEntity tbBuildding,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TbBuilddingEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tbBuildding, request.getParameterMap());
		this.tbBuilddingService.getDataGridReturn(cq, true);
	   TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除建筑管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TbBuilddingEntity tbBuildding, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		tbBuildding = systemService.getEntity(TbBuilddingEntity.class, tbBuildding.getId());
		message = "建筑管理删除成功";
		tbBuilddingService.delete(tbBuildding);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}

	@RequestMapping(params = "addBuildding")
	public ModelAndView addillegal(TbBuilddingEntity tbBuildding, 
			HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("xjnu/edu/weixin/mall/mall/addBuildding");
	}
	
	/**
	 * 根据学校外键（schoolId）,小类外键（catalogId）返回建筑管理相应json
	 * 
	 * @return
	 */
	@RequestMapping(params = "select")
	public void select(TbBuilddingEntity tbBuildding, HttpServletRequest request,
			HttpServletResponse response,DataGrid dataGrid) throws UnsupportedEncodingException {
		   AjaxJson j = new AjaxJson();
			String schoolId = request.getParameter("schoolId");
			String catalogId = request.getParameter("catalogId");
		List<TbBuilddingEntity> sId = tbBuilddingService.findHql("from TbBuilddingEntity where tbSchoolEntity.id=? and tbBuilddingCatalogEntity.id=?", new Object [] {schoolId,catalogId});
		//临时实体类处理处理JSON数据
		List<TbBuilddingEntityJson> list=new ArrayList<TbBuilddingEntityJson>();
		for(TbBuilddingEntity s:sId){
			TbBuilddingEntityJson lstbej=new TbBuilddingEntityJson();
			lstbej.setBuilddingName(s.getBuilddingName());
			lstbej.setBuilddingDescription(s.getBuilddingDescription());
			lstbej.setId(s.getId());
			lstbej.setJindu(s.getJindu());
			lstbej.setPhoneTel(s.getPhoneTel());
			lstbej.setWeidu(s.getWeidu());
			list.add(lstbej);
			  }
		if(list!=null){
		   j.setObj(list);
		}
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
	 * 添加建筑管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(TbBuilddingEntity tbBuildding, HttpServletRequest request) {
		System.out.println("----------save------------->"+123);
		AjaxJson j = new AjaxJson();
		
		if (StringUtil.isNotEmpty(tbBuildding.getId())) {
			message = "建筑管理更新成功";
			TbBuilddingEntity t = tbBuilddingService.get(TbBuilddingEntity.class, tbBuildding.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(tbBuildding, t);
				tbBuilddingService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "建筑管理更新失败";
			}
		} else {
			message = "建筑管理添加成功";
			tbBuilddingService.save(tbBuildding);
//			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 建筑管理列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TbBuilddingEntity tbBuildding, HttpServletRequest req) {
		    //传入学校的信息
		    List<TbSchoolEntity> tbSchoolEntityList = new ArrayList<TbSchoolEntity>();
			tbSchoolEntityList.addAll((List)systemService.getList(TbSchoolEntity.class));
		    req.setAttribute("tbSchoolEntityList", tbSchoolEntityList);
		  //传入小类建筑的信息
		    List<TbBuilddingCatalog> tbBuilddingCatalogEntityList = new ArrayList<TbBuilddingCatalog>();
		    tbBuilddingCatalogEntityList.addAll((List)systemService.getList(TbBuilddingCatalog.class));
		    req.setAttribute("tbBuilddingCatalogEntityList", tbBuilddingCatalogEntityList);

		if (StringUtil.isNotEmpty(tbBuildding.getId())) {
			tbBuildding = tbBuilddingService.getEntity(TbBuilddingEntity.class, tbBuildding.getId());
			req.setAttribute("tbBuilddingPage", tbBuildding);
		}
		return new ModelAndView("xjnu/edu/weixin/mall/mall/tbBuildding");
	}
}
