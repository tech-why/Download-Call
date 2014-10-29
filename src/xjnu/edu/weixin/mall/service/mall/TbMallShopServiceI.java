package xjnu.edu.weixin.mall.service.mall;

import org.jeecgframework.core.common.service.CommonService;

import xjnu.edu.weixin.mall.entity.mall.TbMallShopEntity;

public interface TbMallShopServiceI extends CommonService{
	
	public double getTrasportFee( TbMallShopEntity shop, double cartTotalAmount);
}
