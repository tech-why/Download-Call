package xjnu.edu.weixin.mall.controller.weixin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.RoletoJson;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import xjnu.edu.weixin.mall.entity.mall.TbAddressEntity;
import xjnu.edu.weixin.mall.entity.mall.TbBuilddingEntity;
import xjnu.edu.weixin.mall.entity.mall.TbMallShopEntity;
import xjnu.edu.weixin.mall.entity.mall.TbSchoolEntity;
import xjnu.edu.weixin.mall.entity.mall.TbmallOrderEntity;
import xjnu.edu.weixin.mall.entity.mall.WeixinUserinfoEntity;
import xjnu.edu.weixin.mall.json.mall.OperatorRecordJson;
import xjnu.edu.weixin.mall.json.mall.OrderJson;
import xjnu.edu.weixin.mall.json.mall.ProblemJson;
import xjnu.edu.weixin.mall.service.mall.TbAddressServiceI;
import xjnu.edu.weixin.repair.entity.repair.TbOperaterTypeEntity;
import xjnu.edu.weixin.repair.entity.repair.TbProblemOperateRecordEntity;
import xjnu.edu.weixin.repair.entity.repair.TbProblemOperaterEntity;
import xjnu.edu.weixin.repair.entity.repair.TbProblemTypeEntity;
import xjnu.edu.weixin.repair.entity.repair.TbSubmitProblemEntity;
import xjnu.edu.weixin.repair.service.repair.TbSubmitProblemServiceI;

@Scope("prototype")
@Controller
@RequestMapping("/submitProblemController")
public class submitProblemController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(submitProblemController.class);

	@Autowired
	private TbSubmitProblemServiceI tbSubmitProblemService;
	@Autowired
	private TbAddressServiceI tbAddressServiceI;
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
	 * 保修单表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "display")
	public ModelAndView tbSubmitProblem(HttpServletRequest req) {
		String openuserid = req.getParameter("open_user_id");
		
		if (openuserid.isEmpty() == true) {
			req.setAttribute("success", "请使用微信访问");
			return new ModelAndView("xjnu/edu/weixin/mall_user/success");
		}
		
		TbAddressEntity tbAddressEntity=tbAddressServiceI.getCurrentAddress(openuserid);
		req.setAttribute("app_code", "problem");
		req.setAttribute("open_user_id",openuserid);
		//如果没有选择地址去选择地址或者添加
		if(tbAddressEntity==null){
			req.setAttribute("returnUrl", "submitProblemController.do?display&open_user_id="+openuserid);
			return new ModelAndView("xjnu/edu/weixin/mall_user/address");
		}else{
			TbSchoolEntity tbSchoolEntity=tbSubmitProblemService.getschoolentity(openuserid);
			req.setAttribute("school",tbSchoolEntity);
			req.setAttribute("address", tbAddressEntity);
			TbBuilddingEntity tbBuilddingEntity=tbAddressServiceI.getEntity(TbBuilddingEntity.class,tbAddressEntity.getBuilddingId());
			req.setAttribute("tbbudding",tbBuilddingEntity);
		}
		List<TbProblemTypeEntity> tbProblemTypeEntitiesList=systemService.getList(TbProblemTypeEntity.class);
		req.setAttribute("typeproblemlist",tbProblemTypeEntitiesList);
		
		return new ModelAndView("xjnu/edu/weixin/repair/repair_user/repair");
	}

	@RequestMapping(params = "submitproblem")
	@ResponseBody
	public AjaxJson submitproblem( HttpServletRequest request) {

		AjaxJson j = new AjaxJson();
		String openUserId = request.getParameter("openUserId");
		String addressId = request.getParameter("addressId");
		String typeId = request.getParameter("typeId");
		String userMessage = request.getParameter("message");
		String problemMessage = request.getParameter("problemMessage");	
		TbSubmitProblemEntity tbsp=new TbSubmitProblemEntity();
		String message = tbSubmitProblemService.submitproblem(typeId, openUserId, addressId, problemMessage, tbsp);
		j.setObj(tbsp.getId());
		j.setMsg(message);
		return j;
	}
	@RequestMapping(params = "submitsuccess")
	public ModelAndView submitscuccess(HttpServletRequest req) {
		String open_user_id=req.getParameter("open_user_id");
		String problemid=req.getParameter("problemid");
		TbSubmitProblemEntity tbsp=tbSubmitProblemService.getEntity(TbSubmitProblemEntity.class,problemid);
		req.setAttribute("open_user_id",open_user_id);
		req.setAttribute("problem",tbsp);
		return new ModelAndView("xjnu/edu/weixin/repair/repair_user/submitsuccess");
	}
	
	@RequestMapping(params = "list")
	public ModelAndView showProblemList(TbmallOrderEntity tbmallOrder,
			WeixinUserinfoEntity weixinUserinfoEntity, HttpServletRequest req) {
		
		String state="isAll";
		List<ProblemJson> problemlist;
		String openuserid = req.getParameter("open_user_id");
		if (openuserid.isEmpty() == true) {
			req.setAttribute("success", "请使用微信访问");
			return new ModelAndView("xjnu/edu/weixin/mall_user/success");
		}
		TbProblemOperaterEntity tboperator=tbSubmitProblemService.getoperater(openuserid);
		if(tboperator==null){
			problemlist=tbSubmitProblemService.getProblemList(openuserid,state);
			req.setAttribute("isoperator",false);
		}else{
			problemlist=tbSubmitProblemService.getProblemListByOperator(tboperator.getId(),state);
			req.setAttribute("isoperator",true);
		}
		List<TbOperaterTypeEntity> tbOperaterTypelist=systemService.getList(TbOperaterTypeEntity.class);
		System.out.println(tbOperaterTypelist.size());
			req.setAttribute("operatertypelist", tbOperaterTypelist);
		  	req.setAttribute("isAll",true);
			req.setAttribute("open_user_id",openuserid);
			req.setAttribute("problemlist",problemlist);
		return new ModelAndView("xjnu/edu/weixin/repair/repair_user/problemlist");

	}
	@RequestMapping(params = "showlist")
	public ModelAndView showproblemlistbyguanjian(TbmallOrderEntity tbmallOrder,
			WeixinUserinfoEntity weixinUserinfoEntity, HttpServletRequest req) {
		// 获取用户所有订单的所有店铺
		// 校验用户是否为空
		String typeid=req.getParameter("type_id");
		List<ProblemJson> problemlist;
		String openuserid = req.getParameter("open_user_id");
		if (openuserid.isEmpty() == true) {
			req.setAttribute("success", "请使用微信访问");
			return new ModelAndView("xjnu/edu/weixin/mall_user/success");
		}
		TbOperaterTypeEntity chooseType=systemService.getEntity(TbOperaterTypeEntity.class,typeid);
		TbProblemOperaterEntity tboperator=tbSubmitProblemService.getoperater(openuserid);
		if(tboperator==null){
			problemlist=tbSubmitProblemService.getProblemList(openuserid,chooseType.getOperateType());
			req.setAttribute("isoperator",false);
		}else{
			problemlist=tbSubmitProblemService.getProblemListByOperator(tboperator.getId(),chooseType.getOperateType());
			req.setAttribute("isoperator",true);
		}
		
		List<TbOperaterTypeEntity> tbOperaterTypelist=systemService.getList(TbOperaterTypeEntity.class);
		for(int i=0;i<tbOperaterTypelist.size();i++){
			if(tbOperaterTypelist.get(i).getOperateType().equals(chooseType.getOperateType())){
				tbOperaterTypelist.remove(i);
				i--;
			}
		}
		req.setAttribute("operatertypelist", tbOperaterTypelist);
			req.setAttribute("choosetype",chooseType);
		  	req.setAttribute("isAll",false);
			req.setAttribute("open_user_id",openuserid);
			req.setAttribute("problemlist",problemlist);
		return new ModelAndView("xjnu/edu/weixin/repair/repair_user/problemlist");

	}
	
	
	
	
	@RequestMapping(params = "detail")
	public ModelAndView showProblemListDetail(TbmallOrderEntity tbmallOrder,
			WeixinUserinfoEntity weixinUserinfoEntity, HttpServletRequest req) {
		// 获取用户所有订单的所有店铺
		// 校验用户是否为空
		boolean isoperator;
		String isoperator1=req.getParameter("isoperator");
		
		if(isoperator1.equals("true")){
			isoperator=true;
		}else{
			isoperator=false;
		}
		
		System.out.println(isoperator);
		String openuserid = req.getParameter("open_user_id");
		String problemid=req.getParameter("problem_id");
		if (openuserid.isEmpty() == true) {
			req.setAttribute("success", "请使用微信访问");
			return new ModelAndView("xjnu/edu/weixin/mall_user/success");
		}
		ProblemJson pro=tbSubmitProblemService.getproblemJson(problemid);
		List<OperatorRecordJson> tbrecordlist=tbSubmitProblemService.getoprecordList(problemid);
		List<TbOperaterTypeEntity> tbOperaterTypeEntities=systemService.getList(TbOperaterTypeEntity.class);
		req.setAttribute("isoperator",isoperator);
		req.setAttribute("operatertypelist",tbOperaterTypeEntities);
		req.setAttribute("recordlist", tbrecordlist);
		req.setAttribute("problem",pro);
		req.setAttribute("open_user_id",openuserid);
		return new ModelAndView("xjnu/edu/weixin/repair/repair_user/problemDetail");

	}
	@RequestMapping(params = "operator")
	@ResponseBody
	public AjaxJson weixiu(TbSubmitProblemEntity tbSubmitProblem, HttpServletRequest req) {
		AjaxJson j = new AjaxJson();
		String openuserid = req.getParameter("open_user_id");
		String problemid=req.getParameter("problem_id");
		String state=req.getParameter("statename");
		boolean b=tbSubmitProblemService.Weixiu(problemid, openuserid, state);
		j.setSuccess(b);
		return j;
	}
	@RequestMapping(params = "disproblem")
	@ResponseBody
	public AjaxJson disProblem(TbSubmitProblemEntity tbSubmitProblemEntity,HttpServletRequest req){
		AjaxJson j=new AjaxJson();
		String openuserid = req.getParameter("open_user_id");
		String problemid=req.getParameter("problem_id");
		boolean isscuccess;
		if(openuserid==null){
			isscuccess=false;
		}
		if(problemid==null){
			isscuccess= false;
		}
		isscuccess=tbSubmitProblemService.disproblem(problemid, openuserid);
		j.setSuccess(isscuccess);
		return j;
		
	}
	

}
