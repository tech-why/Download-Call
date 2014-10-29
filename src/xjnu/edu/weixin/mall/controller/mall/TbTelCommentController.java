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

import xjnu.edu.weixin.mall.entity.mall.TbTelCatalogEntity;
import xjnu.edu.weixin.mall.entity.mall.TbTelCommentEntity;
import xjnu.edu.weixin.mall.entity.mall.TbTelItemEntity;
import xjnu.edu.weixin.mall.service.mall.TbTelCommentServiceI;

/**   
 * @Title: Controller
 * @Description: 号码评论
 * @author zhangdaihao
 * @date 2014-08-25 18:12:22
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/tbTelCommentController")
public class TbTelCommentController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TbTelCommentController.class);

	@Autowired
	private TbTelCommentServiceI tbTelCommentService;
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
	 * 号码评论列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "tbTelComment")
	public ModelAndView tbTelComment(HttpServletRequest req) {
		
		 // 给小类外键查询条件中的下拉框准备数据
		List<TbTelItemEntity> TbTelItemEntityList = systemService.getList(TbTelItemEntity.class);
		String telItemName = RoletoJson.listToReplaceStr(TbTelItemEntityList, "telItemName", "id");
		if(!telItemName.equals("")){
			req.setAttribute("telItemNameReplace", telItemName);	
		}else{
			req.setAttribute("telItemNameReplace", " _0");
		}
		
		return new ModelAndView("xjnu/edu/weixin/mall/mall/tbTelCommentList");
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
	public void datagrid(TbTelCommentEntity tbTelComment,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TbTelCommentEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tbTelComment, request.getParameterMap());
		this.tbTelCommentService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除号码评论
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TbTelCommentEntity tbTelComment, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		tbTelComment = systemService.getEntity(TbTelCommentEntity.class, tbTelComment.getId());
		message = "号码评论删除成功";
		tbTelCommentService.delete(tbTelComment);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加号码评论
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(TbTelCommentEntity tbTelComment, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(tbTelComment.getId())) {
			message = "号码评论更新成功";
			TbTelCommentEntity t = tbTelCommentService.get(TbTelCommentEntity.class, tbTelComment.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(tbTelComment, t);
				tbTelCommentService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "号码评论更新失败";
			}
		} else {
			message = "号码评论添加成功";
			tbTelCommentService.save(tbTelComment);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 号码评论列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TbTelCommentEntity tbTelComment, HttpServletRequest req) {
		//传入号码详情的信息
	    List<TbTelItemEntity> tbTelItemEntityList = new ArrayList<TbTelItemEntity>();
	    tbTelItemEntityList.addAll((List)systemService.getList(TbTelItemEntity.class));
	    req.setAttribute("tbTelItemEntityList", tbTelItemEntityList);
		
		if (StringUtil.isNotEmpty(tbTelComment.getId())) {
			tbTelComment = tbTelCommentService.getEntity(TbTelCommentEntity.class, tbTelComment.getId());
			req.setAttribute("tbTelCommentPage", tbTelComment);
		}
		return new ModelAndView("xjnu/edu/weixin/mall/mall/tbTelComment");
	}
}
