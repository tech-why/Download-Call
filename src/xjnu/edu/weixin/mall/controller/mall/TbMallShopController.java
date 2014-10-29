package xjnu.edu.weixin.mall.controller.mall;
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

import xjnu.edu.weixin.mall.entity.mall.TbMallShopEntity;
import xjnu.edu.weixin.mall.service.mall.TbMallShopServiceI;

/**   
 * @Title: Controller
 * @Description: 商铺基本信息
 * @date 2014-08-05 12:56:25
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/tbMallShopController")
public class TbMallShopController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TbMallShopController.class);

	@Autowired
	private TbMallShopServiceI tbMallShopService;
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
	 * 商铺选择列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "tbMallShop")
	public ModelAndView tbMallShop(HttpServletRequest request) {
		return new ModelAndView("xjnu/edu/weixin/mall/mall/tbMallShopList");
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
	public void datagrid(TbMallShopEntity tbMallShop,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TbMallShopEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tbMallShop, request.getParameterMap());
		this.tbMallShopService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除商铺选择
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TbMallShopEntity tbMallShop, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		tbMallShop = systemService.getEntity(TbMallShopEntity.class, tbMallShop.getId());
//		System.out.println("-------------------->"+tbMallShop.getShopName());
		message = "商铺选择删除成功";
		tbMallShopService.delete(tbMallShop);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加商铺选择
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(TbMallShopEntity tbMallShop, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(tbMallShop.getId())) {
			message = "商铺选择更新成功";
			TbMallShopEntity t = tbMallShopService.get(TbMallShopEntity.class, tbMallShop.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(tbMallShop, t);
				tbMallShopService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "商铺选择更新失败";
			}
		} else {
			message = "商铺选择添加成功";
			tbMallShopService.save(tbMallShop);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 商铺选择列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TbMallShopEntity tbMallShop, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(tbMallShop.getId())) {
			tbMallShop = tbMallShopService.getEntity(TbMallShopEntity.class, tbMallShop.getId());
			req.setAttribute("tbMallShopPage", tbMallShop);
		}
		return new ModelAndView("xjnu/edu/weixin/mall/mall/tbMallShop");
	}
	
	/**
	 * 商铺选择列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "shangpinluru")
	public ModelAndView shangpinluru(TbMallShopEntity tbMallShop, HttpServletRequest request) {
		if (StringUtil.isNotEmpty(tbMallShop.getId())) {
			tbMallShop = tbMallShopService.getEntity(TbMallShopEntity.class, tbMallShop.getId());
			request.setAttribute("tbMallShopPage", tbMallShop);
		}
		return new ModelAndView("xjnu/edu/weixin/mall/mall/tbMallShop");
	}
	
}
