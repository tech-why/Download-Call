package xjnu.edu.weixin.mall.controller.weixin;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import xjnu.edu.weixin.mall.entity.mall.TbAddressEntity;
import xjnu.edu.weixin.mall.entity.mall.TbBuilddingEntity;
import xjnu.edu.weixin.mall.entity.mall.WeixinUserinfoEntity;
import xjnu.edu.weixin.mall.json.mall.AddressJson;
import xjnu.edu.weixin.mall.service.mall.TbAddressServiceI;
import xjnu.edu.weixin.mall.service.mall.TbBuilddingServiceI;
import xjnu.edu.weixin.mall.service.mall.WeixinUserinfoServiceI;

@Scope("prototype")
@Controller
@RequestMapping("/addressController")
public class AddressController extends BaseController {
	@Autowired
	private TbAddressServiceI tbaddressservice;
	@Autowired
	private WeixinUserinfoServiceI weixinUserinfoServiceI;
	@Autowired
	private TbBuilddingServiceI tbBuilddingServiceI;
	
	
	
	@RequestMapping(params = "display")
	public ModelAndView showAddress(HttpServletRequest req,
			WeixinUserinfoEntity weixinUserinfoEntity) {
		String openuserid = req.getParameter("open_user_id");
		String appCode = req.getParameter("app_code");
		if (openuserid.isEmpty() == true) {
			req.setAttribute("success", "请使用微信访问");
			return new ModelAndView("xjnu/edu/weixin/mall_user/success");
		}
		List<AddressJson> addresslist = new ArrayList<AddressJson>();
		addresslist = tbaddressservice.getAddressList(openuserid);
		TbAddressEntity chooseTbAEntity=tbaddressservice.findUniqueByProperty(TbAddressEntity.class, "isChoose","是");
		//
		req.setAttribute("chooseTbEntity", chooseTbAEntity);
		req.setAttribute("addresslist", addresslist);
		req.setAttribute("open_user_id", openuserid);
		//如果是从个人中心进入地址栏的
		if(appCode.equals("account"))
		{
			req.setAttribute("returnUrl", "accountController.do?display&open_user_id="+openuserid);
		}
		//如果是从订单进入地址栏的
		else if(appCode.equals("order"))
		{
			String shopId = req.getParameter("shopId");
			req.setAttribute("shopId", shopId);
			req.setAttribute("returnUrl", "generateOrderController.do?display&open_user_id="+openuserid+"&shopId="+shopId);
		}
		//如果是从报修进入地址栏的
		else if(appCode.equals("problem")){
			req.setAttribute("returnUrl", "submitProblemController.do?display&open_user_id="+openuserid);
		}
		req.setAttribute("app_code", appCode);

		return new ModelAndView("xjnu/edu/weixin/mall_user/address");
	}

	@RequestMapping(params = "addorupdate")
	public ModelAndView AddAddress(HttpServletRequest req,
			TbAddressEntity tbAddressEntity) {
		String openuserid = req.getParameter("open_user_id");
		String addressid = req.getParameter("addressid");
		String appCode = req.getParameter("app_code");
		String shopId = req.getParameter("shopId");
		System.out.println(addressid + "dd");
		if (addressid != null) {
			tbAddressEntity = tbaddressservice.getEntity(TbAddressEntity.class,
					addressid);
	
			TbBuilddingEntity tbBuilddingEntity = tbBuilddingServiceI.getEntity(
					TbBuilddingEntity.class, tbAddressEntity.getBuilddingId());
			req.setAttribute("tbaddress", tbAddressEntity);
			req.setAttribute("tbbuilding", tbBuilddingEntity);
		}
		List<TbBuilddingEntity> tbbulidlist = tbaddressservice
				.getTbBuildlist(openuserid);
		req.setAttribute("buildinglist", tbbulidlist);
		req.setAttribute("open_user_id", openuserid);
		req.setAttribute("app_code", appCode);
		req.setAttribute("shopId", shopId);
		return new ModelAndView("xjnu/edu/weixin/mall_user/addressEdit");

	}

	@RequestMapping(params = "saveaddress")
	public void saveAddress(HttpServletRequest req, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		
		String addressid = req.getParameter("addressid");
		String builddingid = req.getParameter("builddingid");
		String contact = req.getParameter("contact");
		String housecode = req.getParameter("housecode");
		String openuserid = req.getParameter("open_user_id");
		String mobilephone=req.getParameter("mobilenumber");
		System.out.println(addressid);
		System.out.println(mobilephone);
		
		try {
			if (addressid.isEmpty() == false) {
				tbaddressservice.updateAddress(addressid, builddingid, contact,
						housecode, mobilephone, openuserid);
				j.setSuccess(true);
			} else {
				tbaddressservice.saveAddress(builddingid, contact, housecode,
						mobilephone, openuserid);
				j.setSuccess(true);
			}
		} catch (Exception e) {
			// TODO: handle exception
			j.setSuccess(false);
			e.printStackTrace();
		}
		
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

	@RequestMapping(params = "del")
	public void del(HttpServletRequest req,HttpServletResponse response) {
		String addressid=req.getParameter("addressid");
		AjaxJson j=new AjaxJson();
	
			try {
				tbaddressservice.deleteAddress(addressid);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				j.setSuccess(false);
				e1.printStackTrace();
			}
			j.setSuccess(true);
		
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
	@RequestMapping(params = "chooseaddress")
	public void chooseAddress(HttpServletRequest req,HttpServletResponse response){
		
		String addressid=req.getParameter("addressid");
		AjaxJson j=new AjaxJson();
	
			try {
				tbaddressservice.chooseAddress(addressid);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				j.setSuccess(false);
				e1.printStackTrace();
			}
			j.setSuccess(true);
		
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
