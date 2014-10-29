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
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import xjnu.edu.weixin.mall.entity.mall.TbBuilddingEntity;
import xjnu.edu.weixin.mall.entity.mall.TbSchoolEntity;
import xjnu.edu.weixin.mall.service.mall.TbSchoolServiceI;

/**   
 * @Title: Controller
 * @Description: 学校管理
 * @date 2014-08-05 12:46:04
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/tbSchoolController")
public class TbSchoolController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TbSchoolController.class);

	@Autowired
	private TbSchoolServiceI tbSchoolService;
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
	 * 学校管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "tbSchool")
	public ModelAndView tbSchool(HttpServletRequest request) {
		return new ModelAndView("xjnu/edu/weixin/mall/mall/tbSchoolList");
	}
	
	/**
	 * 返回所有学校JSON
	 */

	@RequestMapping(params = "datagrid1")
	public void datagrid1(TbSchoolEntity tbSchool,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		AjaxJson j = new AjaxJson();
		List<TbSchoolEntity> tbBuild = systemService.loadAll(TbSchoolEntity.class);
		j.setObj(tbBuild);
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

	}
	
	/**
	 * 返回所有学校JSON(微信前端)
	 *//*

	@RequestMapping(params = "datagridweixin")
	public String datagridweixin(TbSchoolEntity tbSchool,HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		List<TbSchoolEntity> tbBuild = systemService.loadAll(TbSchoolEntity.class);
		j.setObj(tbBuild);
//		System.out.println("----------------------------->"+j.getJsonStr().substring(20, j.getJsonStr().length()-16));
		request.setAttribute("schoolJson", j.getJsonStr().substring(20, j.getJsonStr().length()-16));
		return "xjnu/edu/weixin/mall_user/school";
	}
*/

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(TbSchoolEntity tbSchool,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TbSchoolEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tbSchool, request.getParameterMap());
		this.tbSchoolService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除学校管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TbSchoolEntity tbSchool, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		tbSchool = systemService.getEntity(TbSchoolEntity.class, tbSchool.getId());
		message = "学校管理删除成功";
		tbSchoolService.delete(tbSchool);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加学校管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(TbSchoolEntity tbSchool, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		
		if (StringUtil.isNotEmpty(tbSchool.getId())) {
			message = "学校管理更新成功";
			TbSchoolEntity t = tbSchoolService.get(TbSchoolEntity.class, tbSchool.getId());
			try {
				 MyBeanUtils.copyBeanNotNull2Bean(tbSchool, t);
				 tbSchoolService.saveOrUpdate(t);
				 systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "学校管理更新失败";
			}
		} else {
			TbSchoolEntity schoolName = systemService.findUniqueByProperty(TbSchoolEntity.class, "schoolName",tbSchool.getSchoolName());
			if(schoolName!=null){
				message = "用户: " + tbSchool.getSchoolName() + "已经存在";
			}else{
				message = "学校管理添加成功";
				tbSchoolService.save(tbSchool);
				systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			}
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 学校管理列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TbSchoolEntity tbSchool, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(tbSchool.getId())) {
			tbSchool = tbSchoolService.getEntity(TbSchoolEntity.class, tbSchool.getId());
			req.setAttribute("tbSchoolPage", tbSchool);
		}
		return new ModelAndView("xjnu/edu/weixin/mall/mall/tbSchool");
	}
}
