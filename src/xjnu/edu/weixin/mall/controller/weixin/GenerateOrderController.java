package xjnu.edu.weixin.mall.controller.weixin;


import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import xjnu.edu.weixin.mall.entity.mall.TbAddressEntity;
import xjnu.edu.weixin.mall.entity.mall.TbBuilddingCatalog;
import xjnu.edu.weixin.mall.entity.mall.TbBuilddingClass;
import xjnu.edu.weixin.mall.entity.mall.TbBuilddingEntity;
import xjnu.edu.weixin.mall.entity.mall.TbMallCart;
import xjnu.edu.weixin.mall.entity.mall.TbMallProductEntity;
import xjnu.edu.weixin.mall.entity.mall.TbMallShopEntity;
import xjnu.edu.weixin.mall.entity.mall.TbMallWorkTimeEntity;
import xjnu.edu.weixin.mall.entity.mall.TbSchoolEntity;
import xjnu.edu.weixin.mall.entity.mall.TbmallOrderEntity;
import xjnu.edu.weixin.mall.entity.mall.WeixinUserinfoEntity;
import xjnu.edu.weixin.mall.json.mall.TbBuilddingEntityJson;
import xjnu.edu.weixin.mall.json.mall.TbMallCartJson;
import xjnu.edu.weixin.mall.service.mall.CartServiceI;
import xjnu.edu.weixin.mall.service.mall.OrderServiceI;
import xjnu.edu.weixin.mall.service.mall.TbAddressServiceI;
import xjnu.edu.weixin.mall.service.mall.TbBuilddingClassServiceI;
import xjnu.edu.weixin.mall.service.mall.TbBuilddingServiceI;
import xjnu.edu.weixin.mall.service.mall.TbMallShopServiceI;
import xjnu.edu.weixin.mall.service.mall.TimeServiceI;
import xjnu.edu.weixin.mall.service.mall.WeixinUserinfoServiceI;

/**   
 * @Title: Controller
 * @Description: 建筑管理
 * @author zhangdaihao
 * @date 2014-08-05 12:47:42
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/generateOrderController")
public class GenerateOrderController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(GenerateOrderController.class);

	@Autowired
	private SystemService systemService;
	
	@Autowired
	private WeixinUserinfoServiceI weixinUserinfoService;
	
	@Autowired
	private TbMallShopServiceI tbMallShopService;
	
	@Autowired
	private TbAddressServiceI tbAddressService;
	
	@Autowired
	private OrderServiceI orderService;
	
	@Autowired
	private CartServiceI cartService;
	
	@Autowired
	private TimeServiceI timeService;
	
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	@RequestMapping(params = "submitSuccess")
	public ModelAndView submitSuccess( HttpServletRequest request) {
		String orderId = request.getParameter("orderId");
		String openUserId = request.getParameter("open_user_id");
		TbmallOrderEntity order = systemService.getEntity(TbmallOrderEntity.class, orderId) ; 
		String orderNo = "dj" + String.format("%06d", order.getOrderCount()) ; 
		request.setAttribute("order", order);
		request.setAttribute("orderNo", orderNo);
		request.setAttribute("open_user_id", openUserId) ;
		return new ModelAndView("xjnu/edu/weixin/mall_user/submitSuccess");
	}

	
	@RequestMapping(params = "display")
	public ModelAndView display( HttpServletRequest request) {
		
		String openUserId = request.getParameter("open_user_id");
		String shopId = request.getParameter("shopId");
		WeixinUserinfoEntity userInfo = weixinUserinfoService.getWeixinUserInfoById(openUserId);
		TbMallShopEntity shop = tbMallShopService.getEntity(TbMallShopEntity.class, shopId);
		
		TbSchoolEntity schoolInfo = systemService.getEntity(TbSchoolEntity.class, userInfo.getSchoolId());
		
		List<TbMallCart> cartList = cartService.getCart(shopId, openUserId , 1) ;
		double cartTotalAmount = cartService.getCartAmount(cartList) ;
		double transportFee = tbMallShopService.getTrasportFee(shop, cartTotalAmount);
		double freeTranseportAmount = shop.getFreeTransportFeeAmount();
		double totalAmount = cartTotalAmount + transportFee ;
		List<TbMallWorkTimeEntity> workTimeList = timeService.getTbMallWorkTimeList(shopId);
		TbAddressEntity address = tbAddressService.getCurrentAddress(openUserId);
		String addressDetail = "";
		if(address != null )
		{
			addressDetail =  schoolInfo.getSchoolName() + address.getTbBuilddingEntity().getBuilddingName() + address.getHouseCode() ;
		}
		
		request.setAttribute("cartList", cartList) ;
		request.setAttribute("cartListLength", cartList.size()) ;
		request.setAttribute("shopId", shopId) ;
		request.setAttribute("app_code", "order") ;
		request.setAttribute("open_user_id", openUserId) ;
		request.setAttribute("workTimeList", workTimeList) ;
		request.setAttribute("cartTotalAmount", cartTotalAmount) ;
		request.setAttribute("transportFee", transportFee) ;
		request.setAttribute("totalAmount", totalAmount) ;
		request.setAttribute("address", address) ;
		request.setAttribute("addressDetail", addressDetail) ;
		request.setAttribute("freeTranseportAmount", freeTranseportAmount) ;
		
		return new ModelAndView("xjnu/edu/weixin/mall_user/generateOrder");
	}

	
	
	
	
	/**
	 * 添加建筑管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "generateOrder")
	@ResponseBody
	public AjaxJson generateOrder( HttpServletRequest request) {

		AjaxJson j = new AjaxJson();
		String openUserId = request.getParameter("openUserId");
		String shopId = request.getParameter("shopId");
		String addressId = request.getParameter("addressId");
		String timeId = request.getParameter("timeId");
		String userMessage = request.getParameter("message");
		String todayOrTomorow = request.getParameter("todayOrTomorow");	
		TbmallOrderEntity order = new TbmallOrderEntity();
		message = orderService.generateOrder(shopId, openUserId, addressId, userMessage, timeId, todayOrTomorow , order);
		j.setObj(order.getId());
		j.setMsg(message);
		return j;
	}
	
	
	@RequestMapping(params = "validateOrder")
	@ResponseBody
	public AjaxJson validateOrder( HttpServletRequest request) {

		String validateString = "";
		AjaxJson j = new AjaxJson();
		String openUserId = request.getParameter("openUserId");
		String shopId = request.getParameter("shopId");
		String timeId = request.getParameter("timeId");
		String todayOrTomorow = request.getParameter("todayOrTomorow");	
		TbMallWorkTimeEntity selectWorkTime = timeService.getEntity(TbMallWorkTimeEntity.class, timeId);
		selectWorkTime.setTodayOrTomorow(todayOrTomorow) ;

		validateString = orderService.validateOrder(shopId, openUserId);
		if(!validateString.equals(""))
		{
			message = validateString;
			j.setSuccess(false);
			j.setMsg(message);
			return j ;
		}
		validateString = timeService.checkTime(shopId, selectWorkTime) ;
		if(!validateString.equals(""))
		{
			message = validateString;
			j.setSuccess(false);
			j.setMsg(message);
			return j;
		}
		j.setSuccess(true);
		return j;
	}
	
	
	
	@RequestMapping(params = "cancelOrder")
	@ResponseBody
	public AjaxJson cancelOrder( HttpServletRequest request) {
		
		AjaxJson j = new AjaxJson();
		String openUserId = request.getParameter("openUserId");
		String shopId = request.getParameter("shopId");
		String orderId =  request.getParameter("orderId");
		
		orderService.cancelOrder(orderId);
		j.setMsg(message);
		return j;
	}
	
	
	
	
	
	
	
}
