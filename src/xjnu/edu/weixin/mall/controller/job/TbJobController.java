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
import org.jeecgframework.web.system.pojo.base.TSTerritory;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import xjnu.edu.weixin.mall.entity.job.TbCompanyEntity;
import xjnu.edu.weixin.mall.entity.job.TbJobEntity;
import xjnu.edu.weixin.mall.service.job.TbJobServiceI;

/**   
 * @Title: Controller
 * @Description: 工作表
 * @author zhangdaihao
 * @date 2014-09-16 12:34:03
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/tbJobController")
public class TbJobController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TbJobController.class);

	@Autowired
	private TbJobServiceI tbJobService;
	@Autowired
	private SystemService systemService;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	Short level=4;

	/**
	 * 工作表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "tbJob")
	public ModelAndView tbJob(HttpServletRequest request) {
		// 给公司外键查询条件中的下拉框准备数据
		List<TbCompanyEntity> tbCompanyEntityList = systemService.getList(TbCompanyEntity.class);
		String name=RoletoJson.listToReplaceStr(tbCompanyEntityList, "name", "id");
		if(!name.equals("")){
			request.setAttribute("nameReplace", name);
		}else{
			request.setAttribute("nameReplace", " _0");
		}
		
		// 给区域外键查询条件中的下拉框准备数据
		List<TSTerritory> tSTerritoryList = systemService.findByProperty(TSTerritory.class, "territoryLevel", level);
		String territoryName=RoletoJson.listToReplaceStr(tSTerritoryList, "territoryName", "id");
		if(!territoryName.equals("")){
			request.setAttribute("territoryNameReplace", territoryName);
		}else{
			request.setAttribute("territoryNameReplace", " _0");
		}
		return new ModelAndView("xjnu/edu/weixin/mall/job/tbJobList");
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
	public void datagrid(TbJobEntity tbJob,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TbJobEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tbJob, request.getParameterMap());
		this.tbJobService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除工作表
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TbJobEntity tbJob, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		tbJob = systemService.getEntity(TbJobEntity.class, tbJob.getId());
		message = "工作表删除成功";
		tbJobService.delete(tbJob);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加工作表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(TbJobEntity tbJob, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(tbJob.getId())) {
			message = "工作表更新成功";
			TbJobEntity t = tbJobService.get(TbJobEntity.class, tbJob.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(tbJob, t);
				tbJobService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "工作表更新失败";
			}
		} else {
			message = "工作表添加成功";
			tbJobService.save(tbJob);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 工作表列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TbJobEntity tbJob, HttpServletRequest req) {
		//传入公司信息
	    List<TbCompanyEntity> tbCompanyEntityList = new ArrayList<TbCompanyEntity>();
	    tbCompanyEntityList.addAll((List)systemService.getList(TbCompanyEntity.class));
	    req.setAttribute("tbCompanyEntityList", tbCompanyEntityList);
	    
	  //传入区域信息
	    List<TSTerritory> tsTerritoryList = new ArrayList<TSTerritory>();
	    tsTerritoryList.addAll((List)systemService.findByProperty(TSTerritory.class, "territoryLevel", level));
	    req.setAttribute("tsTerritoryList", tsTerritoryList);
	   /* for(TSTerritory ts : tsTerritoryList){
	    	System.out.println("---------------------------------"+ts.getTSTerritory().getTerritoryName());
	    }*/
		if (StringUtil.isNotEmpty(tbJob.getId())) {
			tbJob = tbJobService.getEntity(TbJobEntity.class, tbJob.getId());
			req.setAttribute("tbJobPage", tbJob);
		}
		return new ModelAndView("xjnu/edu/weixin/mall/job/tbJob");
	}
}
