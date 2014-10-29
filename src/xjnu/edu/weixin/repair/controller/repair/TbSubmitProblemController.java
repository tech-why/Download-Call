package xjnu.edu.weixin.repair.controller.repair;
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
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import xjnu.edu.weixin.mall.entity.mall.TbAddressEntity;
import xjnu.edu.weixin.mall.entity.mall.TbMallOrderItemEntity;
import xjnu.edu.weixin.mall.entity.mall.TbmallOrderEntity;
import xjnu.edu.weixin.mall.entity.mall.WeixinUserinfoEntity;
import xjnu.edu.weixin.repair.entity.repair.TbProblemOperateRecordEntity;
import xjnu.edu.weixin.repair.entity.repair.TbProblemOperaterEntity;
import xjnu.edu.weixin.repair.entity.repair.TbProblemTypeEntity;
import xjnu.edu.weixin.repair.entity.repair.TbSubmitProblemEntity;
import xjnu.edu.weixin.repair.page.repair.TbSubmitProblemPage;
import xjnu.edu.weixin.repair.service.repair.TbSubmitProblemServiceI;

/**   
 * @Title: Controller
 * @Description: 保修单表
 * @author zhangdaihao
 * @date 2014-09-16 16:49:56
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/tbSubmitProblemController")
public class TbSubmitProblemController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TbSubmitProblemController.class);

	@Autowired
	private TbSubmitProblemServiceI tbSubmitProblemService;
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
	 * 保修单表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "tbSubmitProblem")
	public ModelAndView tbSubmitProblem(HttpServletRequest request) {
		List<TbAddressEntity> tbAddressEntityList = systemService.getList(TbAddressEntity.class);
		
		String addressname = tbSubmitProblemService.listToReplaceStr(tbAddressEntityList);
		if(!addressname.equals("")){
			request.setAttribute("AddressNameReplace", addressname);	
		}else{
			request.setAttribute("AddressNameReplace", " _0");
		}
		List<TbProblemTypeEntity> tbProblemTypeEntitiesList=systemService.getList(TbProblemTypeEntity.class);
		String typename=RoletoJson.listToReplaceStr(tbProblemTypeEntitiesList,"typeName","id");
		if(!typename.equals("")){
			request.setAttribute("TypeNameReplace", typename);	
	
		}else{
			request.setAttribute("TypeNameReplace", " _0");
		}
		return new ModelAndView("xjnu/edu/weixin/repair/repair/tbSubmitProblemList");
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
	public void datagrid(TbSubmitProblemEntity tbSubmitProblem,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TbSubmitProblemEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tbSubmitProblem, request.getParameterMap());
		this.tbSubmitProblemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除保修单表
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TbSubmitProblemEntity tbSubmitProblem, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		tbSubmitProblem = systemService.getEntity(TbSubmitProblemEntity.class, tbSubmitProblem.getId());
		message = "删除成功";
		tbSubmitProblemService.delete(tbSubmitProblem);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}



	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(TbSubmitProblemEntity tbSubmitProblem,TbSubmitProblemPage tbSubmitProblemPage, HttpServletRequest request) {
		List<TbProblemOperateRecordEntity> tbProblemOperateRecordList = tbSubmitProblemPage.getTbProblemOperateRecordList();
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(tbSubmitProblem.getId())) {
			message = "更新成功";
			tbSubmitProblemService.updateMain(tbSubmitProblem, tbProblemOperateRecordList);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} else {
			message = "添加成功";
			tbSubmitProblemService.addMain(tbSubmitProblem, tbProblemOperateRecordList);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * 保修单表列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TbSubmitProblemEntity tbSubmitProblem, HttpServletRequest req) {
		List<TbProblemTypeEntity> tbproblemtypelist=systemService.getList(TbProblemTypeEntity.class);
		req.setAttribute("tbproblemtypelist", tbproblemtypelist);
		List<TbAddressEntity> tbAddressEntityList=systemService.getList(TbAddressEntity.class);
	    req.setAttribute("tbAddressEntityList", tbAddressEntityList);
	    List<WeixinUserinfoEntity> weixinuserinfoList=systemService.getList(WeixinUserinfoEntity.class);
	    req.setAttribute("weixinuserinfoList", weixinuserinfoList);
	    List<TbProblemOperaterEntity> tbProblemOperaterList=systemService.getList(TbProblemOperaterEntity.class);
		req.setAttribute("tbProblemOperaterList", tbProblemOperaterList);
		if (StringUtil.isNotEmpty(tbSubmitProblem.getId())) {
			tbSubmitProblem = tbSubmitProblemService.getEntity(TbSubmitProblemEntity.class, tbSubmitProblem.getId());
			req.setAttribute("tbSubmitProblemPage", tbSubmitProblem);
		}
		return new ModelAndView("xjnu/edu/weixin/repair/repair/tbSubmitProblem");
	}
	
	
	/**
	 * 加载明细列表[维修记录]
	 * 
	 * @return
	 */
	@RequestMapping(params = "tbProblemOperateRecordList")
	public ModelAndView tbProblemOperateRecordList(TbSubmitProblemEntity tbSubmitProblem, HttpServletRequest req) {
		 List<TbProblemOperaterEntity> tbProblemOperaterList=systemService.getList(TbProblemOperaterEntity.class);
			req.setAttribute("tbProblemOperaterList", tbProblemOperaterList);
		//===================================================================================
		//获取参数
		Object id0 = tbSubmitProblem.getId();
		//===================================================================================
		//查询-维修记录
	    String hql0 = "from TbProblemOperateRecordEntity where 1 = 1 AND problemId = ? ";
		try{
		    List<TbProblemOperateRecordEntity> tbProblemOperateRecordEntityList = systemService.findHql(hql0,id0);
			req.setAttribute("tbProblemOperateRecordList", tbProblemOperateRecordEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("xjnu/edu/weixin/repair/repair/tbProblemOperateRecordList");
	}
}
