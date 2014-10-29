package xjnu.edu.weixin.mall.service.impl.mall;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xjnu.edu.weixin.mall.entity.mall.TbBuilddingClass;
import xjnu.edu.weixin.mall.entity.mall.TbMallShopEntity;
import xjnu.edu.weixin.mall.service.mall.BuilddingServiceI;
import xjnu.edu.weixin.mall.service.mall.ShopServiceI;
import xjnu.edu.weixin.mall.service.mall.TbBuilddingServiceI;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("shopService")
@Transactional
public class ShopServiceImpl extends CommonServiceImpl implements ShopServiceI {

	@Override
	public List<TbMallShopEntity> getShopBySchoolId(String schoolId) {
		// TODO Auto-generated method stub
		DetachedCriteria dc = DetachedCriteria.forClass(TbMallShopEntity.class);
		dc = dc.createCriteria("tbMallShopSchoolEntitys", "shop");   
		dc.createCriteria("shop.tbSchoolEntity", "school");
		dc.add(Restrictions.eq("school.id", schoolId));
		List<TbMallShopEntity> list =  findByDetached(dc);
		return list;
	}
	
	
	
}