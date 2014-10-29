package xjnu.edu.weixin.mall.service.mall;

import java.util.List;
import org.jeecgframework.core.common.service.CommonService;

import xjnu.edu.weixin.mall.entity.mall.TbMallShopEntity;
import xjnu.edu.weixin.mall.entity.mall.TbmallOrderEntity;
import xjnu.edu.weixin.mall.entity.mall.TbMallOrderItemEntity;
import xjnu.edu.weixin.mall.entity.mall.TbMallOrderOperateEntity;
import xjnu.edu.weixin.mall.json.mall.OrderJson;

public interface TbmallOrderServiceI extends CommonService{
	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(TbmallOrderEntity tbmallOrder,
	        List<TbMallOrderItemEntity> tbMallOrderItemList,List<TbMallOrderOperateEntity> tbMallOrderOperateList) ;
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(TbmallOrderEntity tbmallOrder,
	        List<TbMallOrderItemEntity> tbMallOrderItemList,List<TbMallOrderOperateEntity> tbMallOrderOperateList);
	public void delMain (TbmallOrderEntity tbmallOrder);
	
	/*
	 * 根据学校得到商店的list
	 * */
	public  List<TbMallShopEntity> getShopList(String schoolid);
	/*
	 * 根据hql和Params得到订单的list
	 * */
	public List<OrderJson> getOrderListByHql(String hql,Object[] params);
	public List<TbMallShopEntity> getOtherShopList(String schoolid,String shopid);
	//得到剩下的状态列表
	public List<String> getStateList(String state);

}
