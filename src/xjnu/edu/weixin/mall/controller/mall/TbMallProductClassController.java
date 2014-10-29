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

import xjnu.edu.weixin.mall.entity.mall.TbImageEntity;
import xjnu.edu.weixin.mall.entity.mall.TbMallProductClassEntity;
import xjnu.edu.weixin.mall.service.mall.TbMallProductClassServiceI;

/**   
 * @Title: Controller
 * @Description: 大类管理
 * @date 2014-08-05 12:48:09
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/tbMallProductClassController")
public class TbMallProductClassController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TbMallProductClassController.class);

	@Autowired
	private TbMallProductClassServiceI tbMallProductClassService;
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
	 * 大类管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "tbMallProductClass")
	public ModelAndView tbMallProductClass(HttpServletRequest request) {
		 // 给大类外键查询条件中的下拉框准备数据
		List<TbImageEntity> tbImageEntityList = systemService.getList(TbImageEntity.class);
		String imageName=RoletoJson.listToReplaceStr(tbImageEntityList, "imageName", "id");
		if(!imageName.equals("")){
			request.setAttribute("imageNameReplace", imageName);
		}else{
			request.setAttribute("imageNameReplace", " _0");
		}
		return new ModelAndView("xjnu/edu/weixin/mall/mall/tbMallProductClassList");
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
	public void datagrid(TbMallProductClassEntity tbMallProductClass,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TbMallProductClassEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tbMallProductClass, request.getParameterMap());
		this.tbMallProductClassService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除大类管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TbMallProductClassEntity tbMallProductClass, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		tbMallProductClass = systemService.getEntity(TbMallProductClassEntity.class, tbMallProductClass.getId());
		message = "大类管理删除成功";
		tbMallProductClassService.delete(tbMallProductClass);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加大类管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(TbMallProductClassEntity tbMallProductClass, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(tbMallProductClass.getId())) {
			message = "大类管理更新成功";
			TbMallProductClassEntity t = tbMallProductClassService.get(TbMallProductClassEntity.class, tbMallProductClass.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(tbMallProductClass, t);
				tbMallProductClassService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "大类管理更新失败";
			}
		} else {
			message = "大类管理添加成功";
			tbMallProductClassService.save(tbMallProductClass);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 大类管理列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TbMallProductClassEntity tbMallProductClass, HttpServletRequest req) {
		//传入图片信息
		 List<TbImageEntity> tbImageEntityList = new ArrayList<TbImageEntity>();
		 tbImageEntityList.addAll((List)systemService.getList(TbImageEntity.class));
		    req.setAttribute("tbImageEntityList", tbImageEntityList);
		if (StringUtil.isNotEmpty(tbMallProductClass.getId())) {
			tbMallProductClass = tbMallProductClassService.getEntity(TbMallProductClassEntity.class, tbMallProductClass.getId());
			req.setAttribute("tbMallProductClassPage", tbMallProductClass);
		}
		return new ModelAndView("xjnu/edu/weixin/mall/mall/tbMallProductClass");
	}
}
