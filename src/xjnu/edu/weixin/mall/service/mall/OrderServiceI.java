package xjnu.edu.weixin.mall.service.mall;

import java.util.List;

import org.jeecgframework.core.common.service.CommonService;

import xjnu.edu.weixin.mall.entity.mall.TbMallCart;
import xjnu.edu.weixin.mall.entity.mall.TbmallOrderEntity;


public interface OrderServiceI extends CommonService{
	
	

	public String validateOrder(String shopId , String userOpenId);

	
	
	public void cancelOrder(String orderId);

	public String generateOrder(String shopId, String userOpenId, String addressId,
			String message, String timeId, String todayOrTomorow,
			TbmallOrderEntity order);
	
}
