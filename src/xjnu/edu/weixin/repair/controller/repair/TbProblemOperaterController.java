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
import org.jeecgframework.core.common.model.json.ComboTree;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.RoletoJson;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import xjnu.edu.weixin.mall.entity.mall.TbBuilddingEntity;
import xjnu.edu.weixin.mall.entity.mall.WeixinUserinfoEntity;
import xjnu.edu.weixin.repair.entity.repair.TbProblemOperaterEntity;
import xjnu.edu.weixin.repair.service.repair.RepairServiceI;
import xjnu.edu.weixin.repair.service.repair.TbProblemOperaterServiceI;

/**   
 * @Title: Controller
 * @Description: 维修人员表
 * @author zhangdaihao
 * @date 2014-09-16 16:51:57
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/tbProblemOperaterController")
public class TbProblemOperaterController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TbProblemOperaterController.class);

	@Autowired
	private TbProblemOperaterServiceI tbProblemOperaterService;
	@Autowired
	private RepairServiceI repairServiceI;
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
	 * 维修人员表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "tbProblemOperater")
	public ModelAndView tbProblemOperater(HttpServletRequest request) {
		List<TSUser> tbUserEntityList = systemService.getList(TSUser.class);
		String username = RoletoJson.listToReplaceStr(tbUserEntityList, "userName", "id");
		if(!username.equals("")){
			request.setAttribute("userNameReplace", username);	
		}else{
			request.setAttribute("userNameReplace", " _0");
		}
		List<WeixinUserinfoEntity> weixinUserinfolist=systemService.getList(WeixinUserinfoEntity.class);
		
		String idreplace=RoletoJson.listToReplaceStr(weixinUserinfolist,"openid","id");
		if(!idreplace.equals("")){
			request.setAttribute("idreplace",idreplace);
		}else{
			request.setAttribute("idreplace","_0");
		}
		return new ModelAndView("xjnu/edu/weixin/repair/repair/tbProblemOperaterList");
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
	public void datagrid(TbProblemOperaterEntity tbProblemOperater,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TbProblemOperaterEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tbProblemOperater, request.getParameterMap());
		this.tbProblemOperaterService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除维修人员表
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TbProblemOperaterEntity tbProblemOperater, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		tbProblemOperater = systemService.getEntity(TbProblemOperaterEntity.class, tbProblemOperater.getId());
		message = "维修人员表删除成功";
		tbProblemOperaterService.delete(tbProblemOperater);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加维修人员表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(TbProblemOperaterEntity tbProblemOperater, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(tbProblemOperater.getId())) {
			message = "维修人员表更新成功";
			TbProblemOperaterEntity t = tbProblemOperaterService.get(TbProblemOperaterEntity.class, tbProblemOperater.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(tbProblemOperater, t);
				tbProblemOperaterService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "维修人员表更新失败";
			}
		} else {
			message = "维修人员表添加成功";
			tbProblemOperaterService.save(tbProblemOperater);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 维修人员表列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TbProblemOperaterEntity tbProblemOperater, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(tbProblemOperater.getId())) {
			tbProblemOperater = tbProblemOperaterService.getEntity(TbProblemOperaterEntity.class, tbProblemOperater.getId());
			
			req.setAttribute("tbProblemOperaterPage", tbProblemOperater);
			List<WeixinUserinfoEntity> weixinuserinfolist=systemService.getList(WeixinUserinfoEntity.class);
			req.setAttribute("weixinuserinfolist",weixinuserinfolist);
		}
		List<WeixinUserinfoEntity> weixinuserinfolist=systemService.getList(WeixinUserinfoEntity.class);
		List<TSUser> tsuserlist=systemService.getList(TSUser.class);
	
		req.setAttribute("tsuserlist",tsuserlist);
		return new ModelAndView("xjnu/edu/weixin/repair/repair/tbProblemOperater");
	}
	@RequestMapping(params = "fun")
	public ModelAndView fun(HttpServletRequest request) {
		String operaterId = request.getParameter("operaterId");
		request.setAttribute("operaterId", operaterId);
		return new ModelAndView("xjnu/edu/weixin/repair/repair/tbProblemOperaterSet");
	}
	@RequestMapping(params = "setAuthority")
	@ResponseBody
	public List<ComboTree> returnTreeJson(TbProblemOperaterEntity tbProblemOperater, HttpServletRequest req){
		String operaterId = req.getParameter("operaterId");
		List<ComboTree> list=repairServiceI.getSchoolComboTree(operaterId);
		
		return list;
	}
	@RequestMapping(params = "updateAuthority")
	@ResponseBody
	public AjaxJson saveTbOperaterAndBudding(TbProblemOperaterEntity tbProblemOperater, HttpServletRequest req){
		AjaxJson j = new AjaxJson();
		String buddingids=req.getParameter("builddingIds");
		String buddings[]=buddingids.split(",");
		String operaterId = req.getParameter("operaterId");
		repairServiceI.updateBuilddingAssignment(buddings, operaterId);
		j.setMsg("分配建筑成功！");
		
		return j;
	}
}
