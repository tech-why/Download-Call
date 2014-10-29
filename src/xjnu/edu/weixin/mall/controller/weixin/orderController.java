package xjnu.edu.weixin.mall.controller.weixin;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import xjnu.edu.weixin.mall.entity.mall.TbAddressEntity;
import xjnu.edu.weixin.mall.entity.mall.TbBuilddingEntity;
import xjnu.edu.weixin.mall.entity.mall.TbMallOrderItemEntity;
import xjnu.edu.weixin.mall.entity.mall.TbMallOrderOperateEntity;
import xjnu.edu.weixin.mall.entity.mall.TbMallShopEntity;
import xjnu.edu.weixin.mall.entity.mall.TbmallOrderEntity;
import xjnu.edu.weixin.mall.entity.mall.WeixinUserinfoEntity;
import xjnu.edu.weixin.mall.json.mall.OrderJson;
import xjnu.edu.weixin.mall.service.mall.TbMallShopServiceI;
import xjnu.edu.weixin.mall.service.mall.TbmallOrderServiceI;
import xjnu.edu.weixin.mall.service.mall.WeixinUserinfoServiceI;

@Scope("prototype")
@Controller
@RequestMapping("/orderController")
public class orderController extends BaseController {
	private static final Logger logger = Logger
			.getLogger(orderController.class);
	@Autowired
	private WeixinUserinfoServiceI weixinuserservice;
	@Autowired
	private TbmallOrderServiceI tbmallOrderService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private TbMallShopServiceI tbMallShopServiceI;
	@RequestMapping(params = "list")
	public ModelAndView showSchoolOrder(TbmallOrderEntity tbmallOrder,
			WeixinUserinfoEntity weixinUserinfoEntity, HttpServletRequest req) {
		// 获取用户所有订单的所有店铺
		// 校验用户是否为空
		String openuserid = req.getParameter("open_user_id");
		if (openuserid.isEmpty() == true) {
			req.setAttribute("success", "请使用微信访问");
			return new ModelAndView("xjnu/edu/weixin/mall_user/success");
		}
		weixinUserinfoEntity = weixinuserservice.findUniqueByProperty(
				WeixinUserinfoEntity.class, "openid", openuserid);
		if (weixinUserinfoEntity == null) {
			req.setAttribute("success", "出错！没有该用户 ");
			return new ModelAndView("xjnu/edu/weixin/mall_user/success");
		}
		// 得到商店的list
		List<TbMallShopEntity> tbshoplist = tbmallOrderService
				.getShopList(weixinUserinfoEntity.getSchoolId());
		//得到订单的list
		List<OrderJson> orderlist = new ArrayList<OrderJson>();
		String hql = "from TbmallOrderEntity tborder where tborder.tbSchoolEntity.id=? and tborder.weixinUserinfoEntity.id=?";
		
		Object[] params = new Object[] { weixinUserinfoEntity.getSchoolId(),
				weixinUserinfoEntity.getId()  };
		orderlist = tbmallOrderService.getOrderListByHql(hql, params);
		req.setAttribute("tbshoplist", tbshoplist);
		req.setAttribute("orderlist", orderlist);
		req.setAttribute("open_user_id", openuserid);
		req.setAttribute("isall", true);
		req.setAttribute("isAll", true);
		return new ModelAndView("xjnu/edu/weixin/mall_user/order_list");

	}

	// 由商店筛选
	@RequestMapping(params = "showlist")
	public ModelAndView showOrderlist(HttpServletRequest req,
			WeixinUserinfoEntity weixinUserinfoEntity) {
		// 实用时需要加验证方式
		String openuserid = req.getParameter("open_user_id");
		String shopid = req.getParameter("shopid");
		TbMallShopEntity chooseshop = systemService.getEntity(
		TbMallShopEntity.class, shopid);
		weixinUserinfoEntity = weixinuserservice.findUniqueByProperty(WeixinUserinfoEntity.class, "openid", openuserid);
		List<TbMallShopEntity> tbshoplist = tbmallOrderService
				.getOtherShopList(weixinUserinfoEntity.getSchoolId(), shopid);
		List<OrderJson> orderlist = new ArrayList<OrderJson>();
		String hql = "from TbmallOrderEntity tb where tb.tbMallShopEntity.id=? and tb.tbSchoolEntity.id=? and tb.weixinUserinfoEntity.id=?";
		Object[] params = new Object[] { shopid,
				weixinUserinfoEntity.getSchoolId(),
				weixinUserinfoEntity.getId() };
		orderlist = tbmallOrderService.getOrderListByHql(hql, params);
		req.setAttribute("isall", true);
		req.setAttribute("isAll", false);
		req.setAttribute("tbshoplist", tbshoplist);
		req.setAttribute("orderlist", orderlist);
		req.setAttribute("open_user_id", openuserid);
		req.setAttribute("chooseshop", chooseshop);
		return new ModelAndView("xjnu/edu/weixin/mall_user/order_list");
	}

	// 由类型筛选
	@RequestMapping(params = "showstate")
	public ModelAndView showstate(HttpServletRequest req) {
		String openuserid = req.getParameter("open_user_id");
		try {
			String state = new String(req.getParameter("state").getBytes(
					"ISO-8859-1"), "UTF-8");
			System.out.println(state);
			List<String> statelist = tbmallOrderService.getStateList(state);
			WeixinUserinfoEntity weixinUserinfoEntity = weixinuserservice
					.findUniqueByProperty(WeixinUserinfoEntity.class, "openid",
							openuserid);
			String hql = "from TbmallOrderEntity tb where tb.orderState=? and tb.tbSchoolEntity.id=? and tb.weixinUserinfoEntity.id=?";
			Object[] params = new Object[] { state,
					weixinUserinfoEntity.getSchoolId(),
					weixinUserinfoEntity.getId() };
			List<OrderJson> orderlist = new ArrayList<OrderJson>();
			orderlist = tbmallOrderService.getOrderListByHql(hql, params);
			req.setAttribute("isAll", true);
			req.setAttribute("orderlist", orderlist);
			req.setAttribute("choosestate", state);
			req.setAttribute("isall", false);
			req.setAttribute("open_user_id", openuserid);
			req.setAttribute("liststate", statelist);

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ModelAndView("xjnu/edu/weixin/mall_user/order_list");
	}

	@RequestMapping(params = "display")
	public ModelAndView showOrderItem(HttpServletRequest req) {

		String orderid = req.getParameter("order_id");
		String openuserid = req.getParameter("open_user_id");

		TbmallOrderEntity tbmallorder = tbmallOrderService.get(
				TbmallOrderEntity.class, orderid);
		List<TbMallOrderItemEntity> orderitemlist = tbmallOrderService
				.findByProperty(TbMallOrderItemEntity.class, "orderId", orderid);
		String hql = "from TbMallOrderOperateEntity tope where tope.orderId=? order by tope.operateTime asc";
		List<TbMallOrderOperateEntity> orderOperatelist = tbmallOrderService
				.findHql(hql, orderid);
		TbAddressEntity tbaddress = systemService.getEntity(
				TbAddressEntity.class, tbmallorder.getTbAddressEntity().getId());
		TbBuilddingEntity tbuBuilddingEntity = systemService.getEntity(
				TbBuilddingEntity.class, tbaddress.getBuilddingId());
		String orderNo = "dj" + String.format("%06d", tbmallorder.getOrderCount()) ; 
		req.setAttribute("address", tbuBuilddingEntity.getBuilddingName());
		req.setAttribute("contract", tbaddress.getContact());
		req.setAttribute("orderNo", orderNo);
		req.setAttribute("open_user_id", openuserid);
		req.setAttribute("order", tbmallorder);
		req.setAttribute("orderitemslist", orderitemlist);
		req.setAttribute("orderoperatelist", orderOperatelist);

		return new ModelAndView("xjnu/edu/weixin/mall_user/orderdetail");
	}

	@RequestMapping(params = "disorder")
	public void disorder(HttpServletRequest req, HttpServletResponse resp) {

		String openuserid = req.getParameter("open_user_id");
		String orderid = req.getParameter("orderid");
		TbmallOrderEntity tb = tbmallOrderService.getEntity(
				TbmallOrderEntity.class, orderid);
		tb.setOrderState("已取消");
		tbmallOrderService.updateEntitie(tb);
		TbMallOrderOperateEntity tbop = new TbMallOrderOperateEntity();
		tbop.setOrderId(orderid);
		tbop.setOperateTime(new Date());
		tbop.setOperateType("已经取消订单");
		systemService.saveOrUpdate(tbop);
		AjaxJson j = new AjaxJson();
		j.setSuccess(true);
		resp.setContentType("text/html;charset=UTF-8");
		System.out.println("sddddd" + j.getJsonStr());
		PrintWriter out;
		try {
			out = resp.getWriter();
			out.print(j.getJsonStr());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
