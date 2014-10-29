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

import xjnu.edu.weixin.mall.entity.mall.TbmallOrderEntity;
import xjnu.edu.weixin.mall.page.mall.TbmallOrderPage;
import xjnu.edu.weixin.mall.service.mall.TbmallOrderServiceI;
import xjnu.edu.weixin.mall.entity.mall.TbAddressEntity;
import xjnu.edu.weixin.mall.entity.mall.TbMallOrderItemEntity;
import xjnu.edu.weixin.mall.entity.mall.TbMallOrderOperateEntity;
import xjnu.edu.weixin.mall.entity.mall.TbMallShopEntity;
import xjnu.edu.weixin.mall.entity.mall.TbSchoolEntity;
import xjnu.edu.weixin.mall.entity.mall.WeixinUserinfoEntity;
/**   
 * @Title: Controller
 * @Description: 订单管理
 * @date 2014-08-05 13:07:22
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/tbmallOrderController")
public class TbmallOrderController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TbmallOrderController.class);

	@Autowired
	private TbmallOrderServiceI tbmallOrderService;
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
	 * 订单管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "tbmallOrder")
	public ModelAndView tbmallOrder(HttpServletRequest request) {
		       // 给学校外键查询条件中的下拉框准备数据
				List<TbSchoolEntity> TbSchoolEntityList = systemService.getList(TbSchoolEntity.class);
				String schoolName = RoletoJson.listToReplaceStr(TbSchoolEntityList, "schoolName", "id");
				List<TbMallShopEntity> TbMallShopEntityList = systemService.getList(TbMallShopEntity.class);
				String shopName=RoletoJson.listToReplaceStr(TbMallShopEntityList, "shopName", "id");
				if(!schoolName.equals("")){
					request.setAttribute("schoolReplace", schoolName);	
				}else{
					request.setAttribute("schoolReplace", " _0");
				}
				// 给商铺外键查询条件中的下拉框准备数据
				if(!shopName.equals("")){
					request.setAttribute("shopReplace",shopName );
				}else{
					request.setAttribute("shopReplace", " _0");
				}
				 // 给地址外键查询条件中的下拉框准备数据
				List<TbAddressEntity> tbAddressEntityList = systemService.getList(TbAddressEntity.class);
				String houseCode = RoletoJson.listToReplaceStr(tbAddressEntityList, "houseCode", "id");
				if(!houseCode.equals("")){
					request.setAttribute("houseCodeReplace", houseCode);	
				}else{
					request.setAttribute("houseCodeReplace", " _0");
				}
				
				// 给用户外键查询条件中的下拉框准备数据
				List<WeixinUserinfoEntity> weixinUserinfoEntityList = systemService.getList(WeixinUserinfoEntity.class);
				String openid = RoletoJson.listToReplaceStr(weixinUserinfoEntityList, "openid", "id");
				if(!openid.equals("")){
					request.setAttribute("openidReplace", openid);	
				}else{
					request.setAttribute("openidReplace", " _0");
				}
				
				
		return new ModelAndView("xjnu/edu/weixin/mall/mall/tbmallOrderList");
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
	public void datagrid(TbmallOrderEntity tbmallOrder,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TbmallOrderEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tbmallOrder);
		this.tbmallOrderService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除订单管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TbmallOrderEntity tbmallOrder, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		tbmallOrder = systemService.getEntity(TbmallOrderEntity.class, tbmallOrder.getId());
		message = "删除成功";
		tbmallOrderService.delete(tbmallOrder);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加订单管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(TbmallOrderEntity tbmallOrder,TbmallOrderPage tbmallOrderPage, HttpServletRequest request) {
		List<TbMallOrderItemEntity> tbMallOrderItemList =  tbmallOrderPage.getTbMallOrderItemList();
		List<TbMallOrderOperateEntity> tbMallOrderOperateList =  tbmallOrderPage.getTbMallOrderOperateList();
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(tbmallOrder.getId())) {
			message = "更新成功";
			tbmallOrderService.updateMain(tbmallOrder, tbMallOrderItemList,tbMallOrderOperateList);
//			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} else {
			message = "添加成功";
			tbmallOrderService.addMain(tbmallOrder, tbMallOrderItemList,tbMallOrderOperateList);
//			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 订单管理列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TbmallOrderEntity tbmallOrder, HttpServletRequest req) {
		 //传入学校的信息
	    List<TbSchoolEntity> tbSchoolEntityList = new ArrayList<TbSchoolEntity>();
		tbSchoolEntityList.addAll((List)systemService.getList(TbSchoolEntity.class));
	    req.setAttribute("tbSchoolEntityList", tbSchoolEntityList);
//	    for(TbSchoolEntity tb : tbSchoolEntityList){
//	    	System.out.println("------getSchoolName()-------"+tb.getSchoolName());
//	    }
	    //传入商铺的信息
	    List<TbMallShopEntity> tbMallShopEntityList = new ArrayList<TbMallShopEntity>();
	    tbMallShopEntityList.addAll((List)systemService.getList(TbMallShopEntity.class));
	    req.setAttribute("tbMallShopEntityList", tbMallShopEntityList);
	    
	    
	    //传入地址的信息
	    List<TbAddressEntity> tbAddressEntityList = new ArrayList<TbAddressEntity>();
	    tbAddressEntityList.addAll((List)systemService.getList(TbAddressEntity.class));
	    req.setAttribute("tbAddressEntityList", tbAddressEntityList);
	    
	  //传入用户的信息
	    List<WeixinUserinfoEntity> weixinUserinforList = new ArrayList<WeixinUserinfoEntity>();
	    weixinUserinforList.addAll((List)systemService.getList(WeixinUserinfoEntity.class));
	    req.setAttribute("weixinUserinforList", weixinUserinforList);
	    
	    
		if (StringUtil.isNotEmpty(tbmallOrder.getId())) {
			tbmallOrder = tbmallOrderService.getEntity(TbmallOrderEntity.class, tbmallOrder.getId());
			req.setAttribute("tbmallOrderPage", tbmallOrder);
		}
//		System.out.println("----------------------------"+tbmallOrder.getTbMallShopEntity().getId());
		return new ModelAndView("xjnu/edu/weixin/mall/mall/tbmallOrder");
	}
	
	
	/**
	 * 加载明细列表[订单明细]
	 * 
	 * @return
	 */
	@RequestMapping(params = "tbMallOrderItemList")
	public ModelAndView tbMallOrderItemList(TbmallOrderEntity tbmallOrder, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object id0 = tbmallOrder.getId();
		//===================================================================================
		//查询-订单明细
	    String hql0 = "from TbMallOrderItemEntity where 1 = 1 AND orderId = ? ";
		try{
		    List<TbMallOrderItemEntity> tbMallOrderItemEntityList = systemService.findHql(hql0,id0);
			req.setAttribute("tbMallOrderItemList", tbMallOrderItemEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("xjnu/edu/weixin/mall/mall/tbMallOrderItemList");
	}
	/**
	 * 加载明细列表[订单跟踪]
	 * 
	 * @return
	 */
	@RequestMapping(params = "tbMallOrderOperateList")
	public ModelAndView tbMallOrderOperateList(TbmallOrderEntity tbmallOrder, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object id1 = tbmallOrder.getId();
		//===================================================================================
		//查询-订单跟踪
	    String hql1 = "from TbMallOrderOperateEntity where 1 = 1 AND orderId = ? ";
		try{
		    List<TbMallOrderOperateEntity> tbMallOrderOperateEntityList = systemService.findHql(hql1,id1);
			req.setAttribute("tbMallOrderOperateList", tbMallOrderOperateEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("xjnu/edu/weixin/mall/mall/tbMallOrderOperateList");
	}
	
	
}
