package xjnu.edu.weixin.mall.service.impl.mall;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xjnu.edu.weixin.mall.entity.mall.TbAddressEntity;
import xjnu.edu.weixin.mall.entity.mall.TbMallCart;
import xjnu.edu.weixin.mall.entity.mall.TbMallOrderItemEntity;
import xjnu.edu.weixin.mall.entity.mall.TbMallOrderOperateEntity;
import xjnu.edu.weixin.mall.entity.mall.TbMallProductEntity;
import xjnu.edu.weixin.mall.entity.mall.TbMallShopEntity;
import xjnu.edu.weixin.mall.entity.mall.TbMallWorkTimeEntity;
import xjnu.edu.weixin.mall.entity.mall.TbSchoolEntity;
import xjnu.edu.weixin.mall.entity.mall.TbmallOrderEntity;
import xjnu.edu.weixin.mall.entity.mall.WeixinUserinfoEntity;
import xjnu.edu.weixin.mall.service.mall.CartServiceI;
import xjnu.edu.weixin.mall.service.mall.OrderServiceI;
import xjnu.edu.weixin.mall.service.mall.TbBuilddingCatalogServiceI;
import xjnu.edu.weixin.mall.service.mall.WeixinUserinfoServiceI;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("orderService")
@Transactional
public class OrderServiceImpl extends CommonServiceImpl implements OrderServiceI {
	
	private double orderTotalCount = 0 ;
	private double orderTotalAmount = 0 ;
	private List<TbMallOrderItemEntity> orderItemList = new ArrayList<TbMallOrderItemEntity>();
	private List<TbMallProductEntity> productList = new ArrayList<TbMallProductEntity>();

	
	
	

	@Override
	public String validateOrder(String shopId, String userOpenId) {
		String validateString = ""; 
		TbMallProductEntity underCarriageProduct = null ;
		TbMallProductEntity notEnoughProduct = null ;
		List<TbMallCart> currentCartList = findHql("from TbMallCart where tbMallProduct.shopId=? " +
				"and isActive='是' and weixinUserId= ?  ",
				new Object[] { shopId, userOpenId } ) ;
		for(  TbMallCart cart : currentCartList  )
		{
			if(cart.getTbMallProduct().getIsActive().equals("否") || cart.getTbMallProduct().getIsDisplay().equals("否") )
			{
				underCarriageProduct = cart.getTbMallProduct() ;
				break;
			}
			else if(cart.getCount() > cart.getTbMallProduct().getRemainAmount() )
			{
				notEnoughProduct = cart.getTbMallProduct() ;
				break ;
			}
		}
		if( underCarriageProduct != null )
		{
			validateString = underCarriageProduct.getProductName() + "已下架";
		}
		else if( notEnoughProduct != null )
		{
			validateString = notEnoughProduct.getProductName() + "库存不足";
		}
		return validateString;
	}

	@Override
	public String generateOrder(String shopId, String userOpenId,
			String addressId, String message, String timeId , String todayOrTomorow ,  TbmallOrderEntity order ) {
		// TODO Auto-generated method stub
		
		
		//WeixinUserinfoEntity user = this.getEntity(WeixinUserinfoEntity.class, userOpenId) ; 
		/*WeixinUserinfoServiceI weixinUserinfoService = new WeixinUserinfoServiceImpl();
		WeixinUserinfoEntity user =  weixinUserinfoService.getWeixinUserInfoById(userOpenId);*/
		WeixinUserinfoEntity user = this.findUniqueByProperty(
				WeixinUserinfoEntity.class, "openid", userOpenId);
		order.setTbAddressEntity( (TbAddressEntity) getEntity(TbAddressEntity.class, addressId));
		order.setUserMessage(message);
		order.setOrderState("已提交") ;
		order.setUnchargeAmount(0.0f) ;
		order.setTotalCount(0.0f);
		order.setTotalPrice(0.0f);
		order.setWeixinUserinfoEntity((WeixinUserinfoEntity) this.findUniqueByProperty(WeixinUserinfoEntity.class, "openid",userOpenId)) ;
		order.setTbMallShopEntity((TbMallShopEntity) getEntity(TbMallShopEntity.class, shopId))  ;
		order.setTbSchoolEntity((TbSchoolEntity) getEntity(TbSchoolEntity.class, user.getSchoolId()));
		setOrderTime( order , timeId , todayOrTomorow ) ;
		
		List<TbMallCart> currentCartList = findHql("from TbMallCart where tbMallProduct.shopId=? " +
				"and isActive='是' and weixinUserId= ?  ", new Object[] { shopId, userOpenId } ) ;
		
		String validateString = generateOrderItem( order , currentCartList) ;
		if( !validateString.equals("") )
		{
			return validateString ;
		}
		order.setOrderCount(getOrderSearialNumber());
		this.save(order);
		
		for(  TbMallOrderItemEntity orderItem : orderItemList  )
		{
			orderItem.setOrderId(order.getId()) ;
		}
		this.batchSave(orderItemList) ;
		this.batchUpdate(productList) ;
		this.batchUpdate(currentCartList) ;
		order.setTotalCount( (float)orderTotalCount) ;
		order.setTotalPrice((float)orderTotalAmount) ;
		insertOperateRecord(order.getId() , "提交订单");

		this.saveOrUpdate(order);
		return validateString;
	}
	
	
	private void insertOperateRecord(String orderId , String state)
	{
		TbMallOrderOperateEntity tbop = new TbMallOrderOperateEntity();
		tbop.setOrderId(orderId);
		tbop.setOperateTime(new Date());
		tbop.setOperateType(state);
		this.save(tbop);
	}
	
	private int getOrderSearialNumber()
	{
		Integer maxCount = this.singleResult("select max(orderCount) from TbmallOrderEntity order");
		if( maxCount == null)
			maxCount  = 1 ;
		return maxCount + 1 ;
	}
	
	public void cancelOrder(String orderId)
	{
		
		TbmallOrderEntity order = this.getEntity(TbmallOrderEntity.class, orderId);
		if( !order.getOrderState().equals("已取消") || !order.getOrderState().equals("已完成"))
		{
			List<TbMallOrderItemEntity> orderItemList = this.findByProperty(TbMallOrderItemEntity.class, "orderId", orderId);
			List<TbMallProductEntity> orderProductList = new ArrayList<TbMallProductEntity>();
			
			
			for(  TbMallOrderItemEntity orderItem : orderItemList  )
			{
				String productId = orderItem.getProductId() ;
				TbMallProductEntity product = this.getEntity(TbMallProductEntity.class, productId) ;
				product.setSaleCount( (int) (product.getSaleCount() - orderItem.getCount()) ) ;
				product.setRemainAmount(product.getRemainAmount() + orderItem.getCount());
				orderProductList.add(product);
			}
			
			this.batchUpdate(orderProductList);
			order.setOrderState("已取消") ;
			this.saveOrUpdate(order);
			insertOperateRecord(order.getId() , "订单已取消");
		}
		
	}
	
	private void setOrderTime( TbmallOrderEntity order , String timeId , String todayOrTomorow )
	{
		TbMallWorkTimeEntity selectWorkTime = this.getEntity(TbMallWorkTimeEntity.class, timeId) ;
		selectWorkTime.setTodayOrTomorow(todayOrTomorow) ;
		TimeServiceImpl timeService = new TimeServiceImpl();
		order.setOrderRequireTime(timeService.getRequestTime(selectWorkTime).getTime());
		order.setOrderLastTime(timeService.getEndTime(selectWorkTime).getTime());
		order.setOrderCreateTime(new Date());
	}
	
	
	private String generateOrderItem( TbmallOrderEntity order , List<TbMallCart> currentCartList)
	{
		orderTotalAmount = 0 ;
		orderTotalCount = 0 ;
		orderItemList.clear() ;
		productList.clear() ;
		
		String validateString = "" ;
		for(  TbMallCart cart : currentCartList  )
		{
			TbMallProductEntity product =  cart.getTbMallProduct() ;
			if( product.getIsActive().equals("是") && product.getIsDisplay().equals("是") )
			{
				if( cart.getCount() <= product.getRemainAmount() )
				{
					
					TbMallOrderItemEntity orderItem = new TbMallOrderItemEntity();
					orderItem.setCount( (double) cart.getCount());
					orderItem.setProductImageUrl(product.getProductImageUrl());
					orderItem.setProductDescription(product.getProductDescription());
					orderItem.setProductName(product.getProductName()) ;
					orderItem.setProductUnit(product.getProductUnit());
					orderItem.setProductId(cart.getTbMallProduct().getId());
					orderItem.setProductPrice(  product.getProductPrice().floatValue() );
					orderItem.setScoreCoefficient(product.getScoreCoefficient());
					orderItem.setUnitAmount(product.getUnitAmount());
					//订单总数量
					orderTotalAmount += orderItem.getCount() * orderItem.getProductPrice() ;
					//订单总金额
					orderTotalCount += orderItem.getCount() ;
					product.setRemainAmount( product.getRemainAmount() - cart.getCount());
					if( product.getSaleCount() != null)
						product.setSaleCount(product.getSaleCount() + cart.getCount() );
					else
						product.setSaleCount(cart.getCount() );
					//this.saveOrUpdate(product) ;
					orderItemList.add(orderItem) ;
					productList.add(product) ;
					cart.setIsActive("否");
				}
				else
				{
					validateString = product.getProductName() + "库存不足";
				}
			}
			else
			{
				validateString = product.getProductName() + "已下架";
			}
		}
		return validateString ;
	}

	
	
	
}