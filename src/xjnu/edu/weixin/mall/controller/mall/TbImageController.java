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

import xjnu.edu.weixin.mall.entity.mall.TbImageEntity;
import xjnu.edu.weixin.mall.service.mall.TbImageServiceI;

/**   
 * @Title: Controller
 * @Description: 图片管理
 * @author zhangdaihao
 * @date 2014-08-11 20:58:43
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/tbImageController")
public class TbImageController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TbImageController.class);

	@Autowired
	private TbImageServiceI tbImageService;
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
	 * 图片管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "tbImage")
	public ModelAndView tbImage(HttpServletRequest request) {
		return new ModelAndView("xjnu/edu/weixin/mall/mall/tbImageList");
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
	public void datagrid(TbImageEntity tbImage,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TbImageEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tbImage, request.getParameterMap());
		this.tbImageService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除图片管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TbImageEntity tbImage, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		tbImage = systemService.getEntity(TbImageEntity.class, tbImage.getId());
		message = "图片管理删除成功";
		tbImageService.delete(tbImage);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加图片管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(TbImageEntity tbImage, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(tbImage.getId())) {
			message = "图片管理更新成功";
			TbImageEntity t = tbImageService.get(TbImageEntity.class, tbImage.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(tbImage, t);
				tbImageService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "图片管理更新失败";
			}
		} else {
			message = "图片管理添加成功";
			tbImageService.save(tbImage);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 图片管理列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TbImageEntity tbImage, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(tbImage.getId())) {
			tbImage = tbImageService.getEntity(TbImageEntity.class, tbImage.getId());
			req.setAttribute("tbImagePage", tbImage);
		}
		return new ModelAndView("xjnu/edu/weixin/mall/mall/tbImage");
	}
}
