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

import xjnu.edu.weixin.mall.entity.mall.TbScoreEntity;
import xjnu.edu.weixin.mall.entity.mall.WeixinUserinfoEntity;
import xjnu.edu.weixin.mall.service.mall.TbScoreServiceI;

/**   
 * @Title: Controller
 * @Description: 积分管理
 * @author zhangdaihao
 * @date 2014-08-11 16:47:06
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/tbScoreController")
public class TbScoreController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TbScoreController.class);

	@Autowired
	private TbScoreServiceI tbScoreService;
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
	 * 积分管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "tbScore")
	public ModelAndView tbScore(HttpServletRequest request) {
		// 给用户外键查询条件中的下拉框准备数据
		List<WeixinUserinfoEntity> weixinUserinfoEntityList = systemService.getList(WeixinUserinfoEntity.class);
		String openid = RoletoJson.listToReplaceStr(weixinUserinfoEntityList, "openid", "id");
		if(!openid.equals("")){
			request.setAttribute("openidReplace", openid);	
		}else{
			request.setAttribute("openidReplace", " _0");
		}
		return new ModelAndView("xjnu/edu/weixin/mall/mall/tbScoreList");
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
	public void datagrid(TbScoreEntity tbScore,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TbScoreEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tbScore, request.getParameterMap());
		this.tbScoreService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除积分管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TbScoreEntity tbScore, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		tbScore = systemService.getEntity(TbScoreEntity.class, tbScore.getId());
		message = "积分管理删除成功";
		tbScoreService.delete(tbScore);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加积分管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(TbScoreEntity tbScore, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(tbScore.getId())) {
			message = "积分管理更新成功";
			TbScoreEntity t = tbScoreService.get(TbScoreEntity.class, tbScore.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(tbScore, t);
				tbScoreService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "积分管理更新失败";
			}
		} else {
			message = "积分管理添加成功";
			tbScoreService.save(tbScore);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 积分管理列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TbScoreEntity tbScore, HttpServletRequest req) {
		 //传入用户的信息
	    List<WeixinUserinfoEntity> weixinUserinforList = new ArrayList<WeixinUserinfoEntity>();
	    weixinUserinforList.addAll((List)systemService.getList(WeixinUserinfoEntity.class));
	    req.setAttribute("weixinUserinforList", weixinUserinforList);
		
		if (StringUtil.isNotEmpty(tbScore.getId())) {
			tbScore = tbScoreService.getEntity(TbScoreEntity.class, tbScore.getId());
			req.setAttribute("tbScorePage", tbScore);
		}
		return new ModelAndView("xjnu/edu/weixin/mall/mall/tbScore");
	}
}
