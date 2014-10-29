package xjnu.edu.weixin.mall.controller.weixin;

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
import org.jeecgframework.core.common.model.json.AjaxJson;

import org.jeecgframework.web.system.service.SystemService;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import xjnu.edu.weixin.mall.entity.mall.TbMallCart;
import xjnu.edu.weixin.mall.json.mall.TbMallCartJson;
import xjnu.edu.weixin.mall.page.mall.CartPage;
import xjnu.edu.weixin.mall.service.mall.CartServiceI;


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
@RequestMapping("/cartController")
public class CartController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(CartController.class);

	@Autowired
	private SystemService systemService;
	
	@Autowired
	private CartServiceI cartService;
	
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	
	
	
	
	/**
	 * 添加建筑管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "combine")
	@ResponseBody
	public AjaxJson combine( HttpServletRequest request) {
		
		AjaxJson j = new AjaxJson();
		String openUserId = request.getParameter("openUserId");
		String shopId = request.getParameter("shopId");
		String productIdsStr =  request.getParameter("productIds");
		
		Gson gson = new Gson();
		List<String> productIdList = gson.fromJson(productIdsStr, new TypeToken<List<String>>() {}.getType());
		
		cartService.combineCart(shopId, openUserId, productIdList);
		j.setMsg(message);
		return j;
	}
	
	
	@RequestMapping(params = "delete")
	@ResponseBody
	public AjaxJson delete( HttpServletRequest request) {
		
		AjaxJson j = new AjaxJson();
		String cartId = request.getParameter("cartId");
		cartService.deleteCart(cartId);
		j.setMsg(message);
		return j;
	}
	
	
	
	@RequestMapping(params = "display")
	public ModelAndView display(HttpServletRequest request, HttpServletResponse response) {
		String openUserId = request.getParameter("openUserId");
		String shopId = request.getParameter("shopId");
		request.setAttribute("open_user_id", openUserId) ;
		request.setAttribute("shopId", shopId) ;
		return new ModelAndView("xjnu/edu/weixin/mall_user/cart" );
		
	}
	
	@RequestMapping(params = "update")
	@ResponseBody
	public AjaxJson update( HttpServletRequest request) {
		
		AjaxJson j = new AjaxJson();
		String openUserId = request.getParameter("openUserId");
		String shopId = request.getParameter("shopId");
		String cartListStr =  request.getParameter("cartListStr");
		
		Gson gson = new Gson();
		List<CartPage> cartList = gson.fromJson(cartListStr, new TypeToken<List<CartPage>>() {}.getType());
		
		cartService.updateCart(shopId, openUserId, cartList);
		j.setSuccess(true);
		j.setMsg(message);
		return j;
	}
	
	
	@RequestMapping(params = "getCarts")
	@ResponseBody
	public AjaxJson getCarts( HttpServletRequest request) {
		
		AjaxJson j = new AjaxJson();
		String openUserId = request.getParameter("openUserId");
		String shopId = request.getParameter("shopId");
		
		List<TbMallCart> cartList = cartService.getCart(shopId, openUserId) ;
		List<TbMallCartJson> cartJsonList = new ArrayList<TbMallCartJson>() ;
		for( TbMallCart cart : cartList)
		{
			TbMallCartJson cartjson = new TbMallCartJson(cart);
			cartJsonList.add(cartjson);
		}
		j.setObj(cartJsonList) ;
		j.setMsg(message);
		return j;
	}
	
	
	
	
	
	
}
