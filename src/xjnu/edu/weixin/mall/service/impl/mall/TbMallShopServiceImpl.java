package xjnu.edu.weixin.mall.service.impl.mall;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xjnu.edu.weixin.mall.entity.mall.TbMallShopEntity;
import xjnu.edu.weixin.mall.service.mall.TbMallShopServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("tbMallShopService")
@Transactional
public class TbMallShopServiceImpl extends CommonServiceImpl implements TbMallShopServiceI {

	@Override
	public double getTrasportFee(TbMallShopEntity shop, double cartTotalAmount) {
		// TODO Auto-generated method stub
		if( shop.getFreeTransportFeeAmount() <=  cartTotalAmount)
		{
			return 0 ;
		}
		return shop.getTransportFee();
	}
	
}