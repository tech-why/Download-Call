package xjnu.edu.weixin.mall.controller.mall;
import java.io.IOException;
import java.io.PrintWriter;
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

import xjnu.edu.weixin.mall.entity.mall.TbMallProductCatalogEntity;
import xjnu.edu.weixin.mall.entity.mall.TbMallProductClassEntity;
import xjnu.edu.weixin.mall.entity.mall.TbMallProductEntity;
import xjnu.edu.weixin.mall.entity.mall.TbMallProductTemplateEntity;
import xjnu.edu.weixin.mall.entity.mall.TbMallShopEntity;
import xjnu.edu.weixin.mall.json.mall.TbMallProductCatalogEntityJson;
import xjnu.edu.weixin.mall.service.mall.TbMallProductServiceI;

/**   
 * @Title: Controller
 * @Description: 商品上架
 * @date 2014-08-05 12:53:53
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/tbMallProductController")
public class TbMallProductController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TbMallProductController.class);

	@Autowired
	private TbMallProductServiceI tbMallProductService;
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
	 * 商品上架列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "tbMallProduct")
	public ModelAndView tbMallProduct(HttpServletRequest request) {
		
		// 给商铺外键查询条件中的下拉框准备数据
		List<TbMallShopEntity> TbMallShopEntityList = systemService.getList(TbMallShopEntity.class);
		String shopName=RoletoJson.listToReplaceStr(TbMallShopEntityList, "shopName", "id");
		if(!shopName.equals("")){
			request.setAttribute("shopReplace",shopName );
		}else{
			request.setAttribute("shopReplace", " _0");
		}
		 // 给小类外键查询条件中的下拉框准备数据
		List<TbMallProductCatalogEntity> tbMallProductCatalogEntityList = systemService.getList(TbMallProductCatalogEntity.class);
		String catalogName=RoletoJson.listToReplaceStr(tbMallProductCatalogEntityList, "catalogName", "id");
		if(!catalogName.equals("")){
			request.setAttribute("catalogNameReplace", catalogName);
		}else{
			request.setAttribute("catalogNameReplace", " _0");
		}
		
		
		List<TbMallProductClassEntity> tbMallProductClassEntityList = systemService.getList(TbMallProductClassEntity.class);
		String className=RoletoJson.listToReplaceStr(tbMallProductClassEntityList, "className", "id");
		if(!className.equals("")){
			request.setAttribute("classNameReplace", className);
		}else{
			request.setAttribute("classNameReplace", " _0");
		}
		return new ModelAndView("xjnu/edu/weixin/mall/mall/tbMallProductList");
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
	public void datagrid(TbMallProductEntity tbMallProduct,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TbMallProductEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tbMallProduct, request.getParameterMap());
		this.tbMallProductService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	/**
	 * 商品选择列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TbMallProductEntity tbMallProduct, HttpServletRequest req) {
			//传入小类信息
			 List<TbMallProductCatalogEntity> tbMallProductCatalogEntityList = new ArrayList<TbMallProductCatalogEntity>();
			 tbMallProductCatalogEntityList.addAll((List)systemService.getList(TbMallProductCatalogEntity.class));
		    req.setAttribute("tbMallProductCatalogEntityList", tbMallProductCatalogEntityList);
		    //传入商铺的信息
		    List<TbMallShopEntity> tbMallShopEntityList = new ArrayList<TbMallShopEntity>();
		    tbMallShopEntityList.addAll((List)systemService.getList(TbMallShopEntity.class));
		    req.setAttribute("tbMallShopEntityList", tbMallShopEntityList);
		    
		if (StringUtil.isNotEmpty(tbMallProduct.getId())) {
			tbMallProduct = tbMallProductService.getEntity(TbMallProductEntity.class, tbMallProduct.getId());
			req.setAttribute("tbMallProductPage", tbMallProduct);
		}
		return new ModelAndView("xjnu/edu/weixin/mall/mall/tbMallProduct");
	}
	
	/**
	 * 商铺-->商品选择列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate1")
	public ModelAndView addorupdate(TbMallShopEntity tbMallShop,TbMallProductEntity tbMallProduct, HttpServletRequest req) {
			//传入小类信息
			 List<TbMallProductCatalogEntity> tbMallProductCatalogEntityList = new ArrayList<TbMallProductCatalogEntity>();
			 tbMallProductCatalogEntityList.addAll((List)systemService.getList(TbMallProductCatalogEntity.class));
		    req.setAttribute("tbMallProductCatalogEntityList", tbMallProductCatalogEntityList);
		    //传入商铺的信息
		   Object name = req.getSession().getAttribute("name");
//		   System.out.println("----------name----------------->"+name);
		   req.setAttribute("name", name);
		if (StringUtil.isNotEmpty(tbMallProduct.getId())) {
			tbMallProduct = tbMallProductService.getEntity(TbMallProductEntity.class, tbMallProduct.getId());
			req.setAttribute("tbMallProductPage", tbMallProduct);
		}
		return new ModelAndView("xjnu/edu/weixin/mall/mall/tbMallProductSon");
	}
	
	
	/**
	 * 删除商品上架
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TbMallProductEntity tbMallProduct, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		tbMallProduct = systemService.getEntity(TbMallProductEntity.class, tbMallProduct.getId());
		message = "商品上架删除成功";
		tbMallProductService.delete(tbMallProduct);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加商品上架
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(TbMallProductEntity tbMallProduct, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(tbMallProduct.getId())) {
			message = "商品上架更新成功";
			TbMallProductEntity t = tbMallProductService.get(TbMallProductEntity.class, tbMallProduct.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(tbMallProduct, t);
				tbMallProductService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "商品上架更新失败";
			}
		} else {
			message = "商品上架添加成功";
			tbMallProductService.save(tbMallProduct);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 从商品选择到商品上架列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "shangpinluru")
	public ModelAndView tbMallProduct(TbMallShopEntity tbMallShop,HttpServletRequest request) {
		
		// 给商铺外键查询条件中的下拉框准备数据
		List<TbMallShopEntity> TbMallShopEntityList = systemService.getList(TbMallShopEntity.class);
		String shopName=RoletoJson.listToReplaceStr(TbMallShopEntityList, "shopName", "id");
		if(!shopName.equals("")){
			request.setAttribute("shopReplace",shopName );
		}else{
			request.setAttribute("shopReplace", " _0");
		}
		 // 给小类外键查询条件中的下拉框准备数据
		List<TbMallProductCatalogEntity> tbMallProductCatalogEntityList = systemService.getList(TbMallProductCatalogEntity.class);
		String catalogName=RoletoJson.listToReplaceStr(tbMallProductCatalogEntityList, "catalogName", "id");
		if(!catalogName.equals("")){
			request.setAttribute("catalogNameReplace", catalogName);
		}else{
			request.setAttribute("catalogNameReplace", " _0");
		}
		tbMallShop = systemService.getEntity(TbMallShopEntity.class, tbMallShop.getId());
		request.setAttribute("tbMallShopname",tbMallShop.getId());
		TbMallShopEntity tbMallShop1=new TbMallShopEntity();
		 request.getSession().setAttribute("name",tbMallShop.getId());
		 
		return new ModelAndView("xjnu/edu/weixin/mall/mall/tbMallProductListSon");
	}
	
	
	/**
	 * 上架模板信息
	 * 5a43553a47d267ba0147d29bf2fe0025
	 * @return 
	 * @return
	 */
	@RequestMapping(params = "addproduct")
	public void addproduct(TbMallProductTemplateEntity tbMallProductTemplate, HttpServletRequest request,HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		String productId=request.getParameter("productId");
		if (StringUtil.isNotEmpty(productId)) {
			tbMallProductTemplate = tbMallProductService.getEntity(TbMallProductTemplateEntity.class, productId);
			request.setAttribute("tbMallProductPage", tbMallProductTemplate);
		}
		j.setObj(tbMallProductTemplate);
//		System.out.println("------------------asd--------------------"+j.getJsonStr());
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
	
}
