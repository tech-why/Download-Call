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

import xjnu.edu.weixin.mall.entity.mall.TbAddressEntity;
import xjnu.edu.weixin.mall.entity.mall.TbBuilddingEntity;
import xjnu.edu.weixin.mall.entity.mall.WeixinUserinfoEntity;
import xjnu.edu.weixin.mall.service.mall.TbAddressServiceI;

/**   
 * @Title: Controller
 * @Description: 地址管理
 * @author zhangdaihao
 * @date 2014-08-11 16:39:02
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/tbAddressController")
public class TbAddressController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TbAddressController.class);

	@Autowired
	private TbAddressServiceI tbAddressService;
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
	 * 地址管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "tbAddress")
	public ModelAndView tbAddress(HttpServletRequest request) {
		// 给建筑外键查询条件中的下拉框准备数据
		List<TbBuilddingEntity> tbBuilddingEntityList = systemService.getList(TbBuilddingEntity.class);
		String builddingName = RoletoJson.listToReplaceStr(tbBuilddingEntityList, "builddingName", "id");
		if(!builddingName.equals("")){
			request.setAttribute("builddingNameReplace", builddingName);	
		}else{
			request.setAttribute("builddingNameReplace", " _0");
		}
		// 给用户外键查询条件中的下拉框准备数据
		List<WeixinUserinfoEntity> weixinUserinfoEntityList = systemService.getList(WeixinUserinfoEntity.class);
		String openid = RoletoJson.listToReplaceStr(weixinUserinfoEntityList, "openid", "id");
		if(!openid.equals("")){
			request.setAttribute("openidReplace", openid);	
		}else{
			request.setAttribute("openidReplace", " _0");
		}
		
		return new ModelAndView("xjnu/edu/weixin/mall/mall/tbAddressList");
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
	public void datagrid(TbAddressEntity tbAddress,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TbAddressEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tbAddress, request.getParameterMap());
		this.tbAddressService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除地址管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TbAddressEntity tbAddress, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		tbAddress = systemService.getEntity(TbAddressEntity.class, tbAddress.getId());
		message = "地址管理删除成功";
		tbAddressService.delete(tbAddress);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加地址管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(TbAddressEntity tbAddress, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(tbAddress.getId())) {
			message = "地址管理更新成功";
			TbAddressEntity t = tbAddressService.get(TbAddressEntity.class, tbAddress.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(tbAddress, t);
				tbAddressService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "地址管理更新失败";
			}
		} else {
			message = "地址管理添加成功";
			tbAddressService.save(tbAddress);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 地址管理列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TbAddressEntity tbAddress, HttpServletRequest req) {
		//传入建筑的信息
	    List<TbBuilddingEntity> tbBuilddingEntityList = new ArrayList<TbBuilddingEntity>();
	    tbBuilddingEntityList.addAll((List)systemService.getList(TbBuilddingEntity.class));
	    req.setAttribute("tbBuilddingEntityList", tbBuilddingEntityList);
	    //传入用户的信息
	    List<WeixinUserinfoEntity> weixinUserinforList = new ArrayList<WeixinUserinfoEntity>();
	    weixinUserinforList.addAll((List)systemService.getList(WeixinUserinfoEntity.class));
	    req.setAttribute("weixinUserinforList", weixinUserinforList);
		
		
		if (StringUtil.isNotEmpty(tbAddress.getId())) {
			tbAddress = tbAddressService.getEntity(TbAddressEntity.class, tbAddress.getId());
			req.setAttribute("tbAddressPage", tbAddress);
		}
		return new ModelAndView("xjnu/edu/weixin/mall/mall/tbAddress");
	}
}
