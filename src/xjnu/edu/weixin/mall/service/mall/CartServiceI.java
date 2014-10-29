package xjnu.edu.weixin.mall.service.mall;

import java.util.List;

import org.jeecgframework.core.common.service.CommonService;

import xjnu.edu.weixin.mall.entity.mall.TbMallCart;
import xjnu.edu.weixin.mall.page.mall.CartPage;


public interface CartServiceI extends CommonService{
	public boolean combineCart(String shopId , String userOpenId , List<String> selectedProductIdList);
	
	public  List<TbMallCart> getCart(String shopId , String userOpenId);
	
	public  List<TbMallCart> getCart(String shopId , String userOpenId , int state);
	
	
	public void  deleteCart(String cartId);

	public boolean updateCart(String shopId, String userOpenId,
			List<CartPage> cartParamList);

	public double getCartAmount(List<TbMallCart> currentCartList);
	
	
}
