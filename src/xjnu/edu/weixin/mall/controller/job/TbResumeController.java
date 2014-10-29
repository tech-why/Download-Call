package xjnu.edu.weixin.mall.controller.job;
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

import xjnu.edu.weixin.mall.entity.job.TbResumeEntity;
import xjnu.edu.weixin.mall.entity.mall.TbMallShopEntity;
import xjnu.edu.weixin.mall.entity.mall.TbSchoolEntity;
import xjnu.edu.weixin.mall.service.job.TbResumeServiceI;

/**   
 * @Title: Controller
 * @Description: 简历
 * @author zhangdaihao
 * @date 2014-09-16 12:33:34
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/tbResumeController")
public class TbResumeController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TbResumeController.class);

	@Autowired
	private TbResumeServiceI tbResumeService;
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
	 * 简历列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "tbResume")
	public ModelAndView tbResume(HttpServletRequest request) {
		// 给学校外键查询条件中的下拉框准备数据
				List<TbSchoolEntity> TbSchoolEntityList = systemService.getList(TbSchoolEntity.class);
				String schoolName = RoletoJson.listToReplaceStr(TbSchoolEntityList, "schoolName", "id");
				
				if(!schoolName.equals("")){
					request.setAttribute("schoolReplace", schoolName);	
				}else{
					request.setAttribute("schoolReplace", " _0");
				}
		return new ModelAndView("xjnu/edu/weixin/mall/job/tbResumeList");
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
	public void datagrid(TbResumeEntity tbResume,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TbResumeEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tbResume, request.getParameterMap());
		this.tbResumeService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除简历
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TbResumeEntity tbResume, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		tbResume = systemService.getEntity(TbResumeEntity.class, tbResume.getId());
		message = "简历删除成功";
		tbResumeService.delete(tbResume);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加简历
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(TbResumeEntity tbResume, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(tbResume.getId())) {
			message = "简历更新成功";
			TbResumeEntity t = tbResumeService.get(TbResumeEntity.class, tbResume.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(tbResume, t);
				tbResumeService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "简历更新失败";
			}
		} else {
			message = "简历添加成功";
			tbResumeService.save(tbResume);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 简历列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TbResumeEntity tbResume, HttpServletRequest req) {
		//传入学校的信息
	    List<TbSchoolEntity> tbSchoolEntityList = new ArrayList<TbSchoolEntity>();
		tbSchoolEntityList.addAll((List)systemService.getList(TbSchoolEntity.class));
	    req.setAttribute("tbSchoolEntityList", tbSchoolEntityList);
		if (StringUtil.isNotEmpty(tbResume.getId())) {
			tbResume = tbResumeService.getEntity(TbResumeEntity.class, tbResume.getId());
			req.setAttribute("tbResumePage", tbResume);
		}
		return new ModelAndView("xjnu/edu/weixin/mall/job/tbResume");
	}
}
