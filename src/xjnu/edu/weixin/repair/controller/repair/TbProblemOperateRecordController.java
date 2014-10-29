package xjnu.edu.weixin.repair.controller.repair;
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

import xjnu.edu.weixin.repair.entity.repair.TbProblemOperateRecordEntity;
import xjnu.edu.weixin.repair.service.repair.TbProblemOperateRecordServiceI;

/**   
 * @Title: Controller
 * @Description: 故障处理表
 * @author zhangdaihao
 * @date 2014-09-16 16:51:04
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/tbProblemOperateRecordController")
public class TbProblemOperateRecordController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TbProblemOperateRecordController.class);

	@Autowired
	private TbProblemOperateRecordServiceI tbProblemOperateRecordService;
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
	 * 故障处理表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "tbProblemOperateRecord")
	public ModelAndView tbProblemOperateRecord(HttpServletRequest request) {
		
		return new ModelAndView("xjnu/edu/weixin/repair/repair/tbProblemOperateRecordList");
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
	public void datagrid(TbProblemOperateRecordEntity tbProblemOperateRecord,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TbProblemOperateRecordEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tbProblemOperateRecord, request.getParameterMap());
		this.tbProblemOperateRecordService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除故障处理表
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TbProblemOperateRecordEntity tbProblemOperateRecord, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		tbProblemOperateRecord = systemService.getEntity(TbProblemOperateRecordEntity.class, tbProblemOperateRecord.getId());
		message = "故障处理表删除成功";
		tbProblemOperateRecordService.delete(tbProblemOperateRecord);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加故障处理表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(TbProblemOperateRecordEntity tbProblemOperateRecord, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(tbProblemOperateRecord.getId())) {
			message = "故障处理表更新成功";
			TbProblemOperateRecordEntity t = tbProblemOperateRecordService.get(TbProblemOperateRecordEntity.class, tbProblemOperateRecord.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(tbProblemOperateRecord, t);
				tbProblemOperateRecordService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "故障处理表更新失败";
			}
		} else {
			message = "故障处理表添加成功";
			tbProblemOperateRecordService.save(tbProblemOperateRecord);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 故障处理表列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TbProblemOperateRecordEntity tbProblemOperateRecord, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(tbProblemOperateRecord.getId())) {
			tbProblemOperateRecord = tbProblemOperateRecordService.getEntity(TbProblemOperateRecordEntity.class, tbProblemOperateRecord.getId());
			req.setAttribute("tbProblemOperateRecordPage", tbProblemOperateRecord);
		}
		return new ModelAndView("xjnu/edu/weixin/repair/repair/tbProblemOperateRecord");
	}
}
