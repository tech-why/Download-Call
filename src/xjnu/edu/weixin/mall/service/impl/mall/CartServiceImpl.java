package xjnu.edu.weixin.mall.service.impl.mall;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xjnu.edu.weixin.mall.entity.mall.TbMallCart;
import xjnu.edu.weixin.mall.entity.mall.TbMallProductEntity;
import xjnu.edu.weixin.mall.page.mall.CartPage;
import xjnu.edu.weixin.mall.service.mall.CartServiceI;
import xjnu.edu.weixin.mall.service.mall.TbBuilddingCatalogServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("cartService")
@Transactional
public class CartServiceImpl extends CommonServiceImpl implements CartServiceI {

	@Override
	public boolean combineCart(String shopId, String userOpenId,
			List<String> selectedProductIdList) {
		List<TbMallCart> newCartList = new ArrayList<TbMallCart>();
		
		List<TbMallCart> currentCartList = findHql("from TbMallCart where tbMallProduct.shopId=? " +
				"and isActive='是' and weixinUserId= ? ",
				new Object[] { shopId, userOpenId } ) ;
		for( String productId : selectedProductIdList )
		{
			boolean searchFlag = false ;
			for(  TbMallCart cart : currentCartList  )
			{
				if( cart.getTbMallProduct().getId().equals(productId) )
				{
					searchFlag = true ;
					break ;
				}
			}
			if( !searchFlag )
			{
				TbMallProductEntity tbMallProduct = this.getEntity(TbMallProductEntity.class, productId) ;
				TbMallCart newCart = new TbMallCart(tbMallProduct,
						userOpenId, 1 , new Timestamp((new Date()).getTime()), "是" , 1);
				newCartList.add(newCart);
			}
		}
		this.batchSave(newCartList) ;
		return true;
	}

	@Override
	public List<TbMallCart> getCart(String shopId, String userOpenId) {
		// TODO Auto-generated method stub
		
		List<TbMallCart> currentCartList = findHql("from TbMallCart where tbMallProduct.shopId=? " +
				"and isActive='是' and weixinUserId= ? and tbMallProduct.isActive = '是'  ",
				new Object[] { shopId, userOpenId } ) ;
		return currentCartList;
	}

	@Override
	public boolean updateCart(String shopId, String userOpenId,
			List<CartPage> cartParamList) {
		
		List<TbMallCart> cartList = new ArrayList<TbMallCart>();
		for(  CartPage cartParam : cartParamList  )
		{
			TbMallCart cart = this.getEntity( TbMallCart.class , cartParam.getId());
			cart.setCreatTime(new Timestamp((new Date()).getTime())) ;
			cart.setState( cartParam.getShopState());
			cart.setCount(cartParam.getCount());
		}
		this.batchSave(cartList) ;
		return false;
	}

	@Override
	public void deleteCart(String cartId) {
		// TODO Auto-generated method stub
		TbMallCart cart = this.getEntity(TbMallCart.class, cartId);
		cart.setIsActive("否");
		this.saveOrUpdate(cart);
	}

	@Override
	public List<TbMallCart> getCart(String shopId, String userOpenId, int state) {
		
		List<TbMallCart> currentCartList = findHql("from TbMallCart where tbMallProduct.shopId=? " +
				"and isActive='是' and weixinUserId= ? and tbMallProduct.isActive = '是' and state = ?  ",
				new Object[] { shopId, userOpenId , state } ) ;
		return currentCartList;
	}

	@Override
	public double getCartAmount(List<TbMallCart> currentCartList) {
		// TODO Auto-generated method stub
		double totalAmount = 0 ;		
		for(  TbMallCart cart : currentCartList  )
		{
			totalAmount += cart.getCount() * cart.getTbMallProduct().getProductPrice() ;
		}
		return totalAmount;
	}

	
	
	
}