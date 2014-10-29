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

import xjnu.edu.weixin.mall.entity.mall.TbSchoolEntity;
import xjnu.edu.weixin.mall.entity.mall.TbTelCatalogEntity;
import xjnu.edu.weixin.mall.entity.mall.TbTelClassEntity;
import xjnu.edu.weixin.mall.entity.mall.TbTelItemEntity;
import xjnu.edu.weixin.mall.service.mall.TbTelItemServiceI;

/**   
 * @Title: Controller
 * @Description: 号码详情
 * @author zhangdaihao
 * @date 2014-08-25 18:11:19
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/tbTelItemController")
public class TbTelItemController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TbTelItemController.class);

	@Autowired
	private TbTelItemServiceI tbTelItemService;
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
	 * 号码详情列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "tbTelItem")
	public ModelAndView tbTelItem(HttpServletRequest request) {
		
		 // 给学校外键查询条件中的下拉框准备数据
		List<TbSchoolEntity> TbSchoolEntityList = systemService.getList(TbSchoolEntity.class);
		String schoolName = RoletoJson.listToReplaceStr(TbSchoolEntityList, "schoolName", "id");
		if(!schoolName.equals("")){
			request.setAttribute("schoolReplace", schoolName);	
		}else{
			request.setAttribute("schoolReplace", " _0");
		}
		
		 // 给小类外键查询条件中的下拉框准备数据
		List<TbTelCatalogEntity> TbTelCatalogEntityList = systemService.getList(TbTelCatalogEntity.class);
		String telCatalogName = RoletoJson.listToReplaceStr(TbTelCatalogEntityList, "telCatalogName", "id");
		if(!telCatalogName.equals("")){
			request.setAttribute("telCatalogNameReplace", telCatalogName);	
		}else{
			request.setAttribute("telCatalogNameReplace", " _0");
		}

		return new ModelAndView("xjnu/edu/weixin/mall/mall/tbTelItemList");
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
	public void datagrid(TbTelItemEntity tbTelItem,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TbTelItemEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tbTelItem, request.getParameterMap());
		this.tbTelItemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除号码详情
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TbTelItemEntity tbTelItem, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		tbTelItem = systemService.getEntity(TbTelItemEntity.class, tbTelItem.getId());
		message = "号码详情删除成功";
		tbTelItemService.delete(tbTelItem);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加号码详情
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(TbTelItemEntity tbTelItem, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(tbTelItem.getId())) {
			message = "号码详情更新成功";
			TbTelItemEntity t = tbTelItemService.get(TbTelItemEntity.class, tbTelItem.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(tbTelItem, t);
				tbTelItemService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "号码详情更新失败";
			}
		} else {
			message = "号码详情添加成功";
			tbTelItemService.save(tbTelItem);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 号码详情列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TbTelItemEntity tbTelItem, HttpServletRequest req) {
		//传入学校的信息
	    List<TbSchoolEntity> tbSchoolEntityList = new ArrayList<TbSchoolEntity>();
		tbSchoolEntityList.addAll((List)systemService.getList(TbSchoolEntity.class));
	    req.setAttribute("tbSchoolEntityList", tbSchoolEntityList);
		
		//传入小类的信息
	    List<TbTelCatalogEntity> tbTelCatalogEntityList = new ArrayList<TbTelCatalogEntity>();
	    tbTelCatalogEntityList.addAll((List)systemService.getList(TbTelCatalogEntity.class));
	    req.setAttribute("tbTelCatalogEntityList", tbTelCatalogEntityList);
		
		if (StringUtil.isNotEmpty(tbTelItem.getId())) {
			tbTelItem = tbTelItemService.getEntity(TbTelItemEntity.class, tbTelItem.getId());
			req.setAttribute("tbTelItemPage", tbTelItem);
		}
		return new ModelAndView("xjnu/edu/weixin/mall/mall/tbTelItem");
	}
}
