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
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import xjnu.edu.weixin.mall.entity.mall.TbTelItemEntity;
import xjnu.edu.weixin.mall.entity.mall.TbTelMyEntity;
import xjnu.edu.weixin.mall.service.mall.TbTelMyServiceI;

/**   
 * @Title: Controller
 * @Description: 我的号码
 * @author zhangdaihao
 * @date 2014-08-25 18:13:10
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/tbTelMyController")
public class TbTelMyController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TbTelMyController.class);

	@Autowired
	private TbTelMyServiceI tbTelMyService;
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
	 * 我的号码列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "tbTelMy")
	public ModelAndView tbTelMy(HttpServletRequest request) {
		 // 给小类外键查询条件中的下拉框准备数据
		List<TbTelItemEntity> TbTelItemEntityList = systemService.getList(TbTelItemEntity.class);
		String telItemName = RoletoJson.listToReplaceStr(TbTelItemEntityList, "telItemName", "id");
		if(!telItemName.equals("")){
			request.setAttribute("telItemNameReplace", telItemName);	
		}else{
			request.setAttribute("telItemNameReplace", " _0");
		}
		
		return new ModelAndView("xjnu/edu/weixin/mall/mall/tbTelMyList");
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
	public void datagrid(TbTelMyEntity tbTelMy,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TbTelMyEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tbTelMy, request.getParameterMap());
		this.tbTelMyService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除我的号码
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TbTelMyEntity tbTelMy, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		tbTelMy = systemService.getEntity(TbTelMyEntity.class, tbTelMy.getId());
		message = "我的号码删除成功";
		tbTelMyService.delete(tbTelMy);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加我的号码
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(TbTelMyEntity tbTelMy, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(tbTelMy.getId())) {
			message = "我的号码更新成功";
			TbTelMyEntity t = tbTelMyService.get(TbTelMyEntity.class, tbTelMy.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(tbTelMy, t);
				tbTelMyService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "我的号码更新失败";
			}
		} else {
			message = "我的号码添加成功";
			tbTelMyService.save(tbTelMy);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 我的号码列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TbTelMyEntity tbTelMy, HttpServletRequest req) {
		
		//传入号码详情的信息
	    List<TbTelItemEntity> tbTelItemEntityList = new ArrayList<TbTelItemEntity>();
	    tbTelItemEntityList.addAll((List)systemService.getList(TbTelItemEntity.class));
	    req.setAttribute("tbTelItemEntityList", tbTelItemEntityList);
		
		if (StringUtil.isNotEmpty(tbTelMy.getId())) {
			tbTelMy = tbTelMyService.getEntity(TbTelMyEntity.class, tbTelMy.getId());
			req.setAttribute("tbTelMyPage", tbTelMy);
		}
		return new ModelAndView("xjnu/edu/weixin/mall/mall/tbTelMy");
	}
}
