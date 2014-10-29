package xjnu.edu.weixin.mall.service.impl.mall;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import xjnu.edu.weixin.mall.service.mall.TbmallOrderServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.util.MyBeanUtils;

import xjnu.edu.weixin.mall.entity.mall.TbMallShopEntity;
import xjnu.edu.weixin.mall.entity.mall.TbMallShopSchoolEntity;
import xjnu.edu.weixin.mall.entity.mall.TbmallOrderEntity;
import xjnu.edu.weixin.mall.entity.mall.TbMallOrderItemEntity;
import xjnu.edu.weixin.mall.entity.mall.TbMallOrderOperateEntity;
import xjnu.edu.weixin.mall.json.mall.OrderJson;
import xjnu.edu.weixin.mall.json.mall.OrderItmJson;
@Service("tbmallOrderService")
@Transactional
public class TbmallOrderServiceImpl extends CommonServiceImpl implements TbmallOrderServiceI {

	
	public void addMain(TbmallOrderEntity tbmallOrder,
	        List<TbMallOrderItemEntity> tbMallOrderItemList,List<TbMallOrderOperateEntity> tbMallOrderOperateList){
			//保存主信息
			this.save(tbmallOrder);
		
			/**保存-订单明细*/
			for(TbMallOrderItemEntity tbMallOrderItem:tbMallOrderItemList){
				//外键设置
				tbMallOrderItem.setOrderId(tbmallOrder.getId());
				this.save(tbMallOrderItem);
			}
			/**保存-订单跟踪*/
			for(TbMallOrderOperateEntity tbMallOrderOperate:tbMallOrderOperateList){
				//外键设置
				tbMallOrderOperate.setOrderId(tbmallOrder.getId());
				this.save(tbMallOrderOperate);
			}
	}

	
	public void updateMain(TbmallOrderEntity tbmallOrder,
	        List<TbMallOrderItemEntity> tbMallOrderItemList,List<TbMallOrderOperateEntity> tbMallOrderOperateList) {
		//保存订单主信息
		this.saveOrUpdate(tbmallOrder);
		
		
		//===================================================================================
		//获取参数
		Object id0 = tbmallOrder.getId();
		Object id1 = tbmallOrder.getId();
		//===================================================================================
		//1.查询出数据库的明细数据-订单明细
	    String hql0 = "from TbMallOrderItemEntity where 1 = 1 AND orderId = ? ";
	    List<TbMallOrderItemEntity> tbMallOrderItemOldList = this.findHql(hql0,id0);
		//2.筛选更新明细数据-订单明细
		for(TbMallOrderItemEntity oldE:tbMallOrderItemOldList){
			boolean isUpdate = false;
				for(TbMallOrderItemEntity sendE:tbMallOrderItemList){
					//需要更新的明细数据-订单明细
					if(oldE.getId().equals(sendE.getId())){
		    			try {
							MyBeanUtils.copyBeanNotNull2Bean(sendE,oldE);
							this.saveOrUpdate(oldE);
						} catch (Exception e) {
							e.printStackTrace();
							throw new BusinessException(e.getMessage());
						}
						isUpdate= true;
		    			break;
		    		}
		    	}
	    		if(!isUpdate){
		    		//如果数据库存在的明细，前台没有传递过来则是删除-订单明细
		    		super.delete(oldE);
	    		}
	    		
			}
		//3.持久化新增的数据-订单明细
		for(TbMallOrderItemEntity tbMallOrderItem:tbMallOrderItemList){
			if(tbMallOrderItem.getId()==null){
				//外键设置
				tbMallOrderItem.setOrderId(tbmallOrder.getId());
				this.save(tbMallOrderItem);
			}
		}
		//===================================================================================
		//1.查询出数据库的明细数据-订单跟踪
	    String hql1 = "from TbMallOrderOperateEntity where 1 = 1 AND orderId = ? ";
	    List<TbMallOrderOperateEntity> tbMallOrderOperateOldList = this.findHql(hql1,id1);
		//2.筛选更新明细数据-订单跟踪
		for(TbMallOrderOperateEntity oldE:tbMallOrderOperateOldList){
			boolean isUpdate = false;
				for(TbMallOrderOperateEntity sendE:tbMallOrderOperateList){
					//需要更新的明细数据-订单跟踪
					if(oldE.getId().equals(sendE.getId())){
		    			try {
							MyBeanUtils.copyBeanNotNull2Bean(sendE,oldE);
							this.saveOrUpdate(oldE);
						} catch (Exception e) {
							e.printStackTrace();
							throw new BusinessException(e.getMessage());
						}
						isUpdate= true;
		    			break;
		    		}
		    	}
	    		if(!isUpdate){
		    		//如果数据库存在的明细，前台没有传递过来则是删除-订单跟踪
		    		super.delete(oldE);
	    		}
	    		
			}
		//3.持久化新增的数据-订单跟踪
		for(TbMallOrderOperateEntity tbMallOrderOperate:tbMallOrderOperateList){
			if(tbMallOrderOperate.getId()==null){
				//外键设置
				tbMallOrderOperate.setOrderId(tbmallOrder.getId());
				this.save(tbMallOrderOperate);
			}
		}
		
	}

	
	public void delMain(TbmallOrderEntity tbmallOrder) {
		//删除主表信息
		this.delete(tbmallOrder);
		
		//===================================================================================
		//获取参数
		Object id0 = tbmallOrder.getId();
		Object id1 = tbmallOrder.getId();
		//===================================================================================
		//删除-订单明细
	    String hql0 = "from TbMallOrderItemEntity where 1 = 1 AND orderId = ? ";
	    List<TbMallOrderItemEntity> tbMallOrderItemOldList = this.findHql(hql0,id0);
		this.deleteAllEntitie(tbMallOrderItemOldList);
		//===================================================================================
		//删除-订单跟踪
	    String hql1 = "from TbMallOrderOperateEntity where 1 = 1 AND orderId = ? ";
	    List<TbMallOrderOperateEntity> tbMallOrderOperateOldList = this.findHql(hql1,id1);
		this.deleteAllEntitie(tbMallOrderOperateOldList);
	}
	
	
	/*
	 * 根据学校得到商店的list
	 * */
	public List<TbMallShopEntity> getShopList(String schoolid){
		
		List<TbMallShopEntity> tbshoplist = new ArrayList<TbMallShopEntity>();
		List<TbMallShopSchoolEntity> shopidlist = this.findByProperty(
				TbMallShopSchoolEntity.class, "schoolId",
				schoolid);
		
		for (TbMallShopSchoolEntity tbse : shopidlist) {
			TbMallShopEntity tbshop = this.get(TbMallShopEntity.class,
					tbse.getShopId());

			tbshoplist.add(tbshop);

		}
		return  tbshoplist;
	}
	
	/*
	 * 根据选中的商店得到剩余的商店list
	 */
public List<TbMallShopEntity> getOtherShopList(String schoolid,String shopid){
	List<TbMallShopEntity> tbshoplist = new ArrayList<TbMallShopEntity>();
	List<TbMallShopSchoolEntity> shopidlist = this.findByProperty(
			TbMallShopSchoolEntity.class, "schoolId",
			schoolid);

	for (TbMallShopSchoolEntity tbse : shopidlist) {
		if (tbse.getShopId().equals(shopid)) {
			continue;
		}
		TbMallShopEntity tbshop = this.get(TbMallShopEntity.class,
				tbse.getShopId());

		tbshoplist.add(tbshop);

	}
	
	
		return  tbshoplist;
	}
	
	
	/*根据Hql和Params得到对应的ORderlist*/
	public List<OrderJson> getOrderListByHql(String hql,Object[] params){
		
		List<OrderJson> orderlist = new ArrayList<OrderJson>();
		List<TbmallOrderEntity> orderlist1 = this.findHql(hql,
				params);
		System.out.println(orderlist1.size() + "大下坡");

		for (int i = 0; i < orderlist1.size(); i++) {
			TbMallShopEntity tbshop = this.get(TbMallShopEntity.class,
					orderlist1.get(i).getTbMallShopEntity().getId());

			List<TbMallOrderItemEntity> orderitemlist = this
					.findByProperty(TbMallOrderItemEntity.class, "orderId",
							orderlist1.get(i).getId());
			List<OrderItmJson> oitemlist = new ArrayList<OrderItmJson>();
			for (TbMallOrderItemEntity tbmallitem : orderitemlist) {
				int a = (int) Math.floor(tbmallitem.getCount());
				OrderItmJson orderitem = new OrderItmJson(tbmallitem.getId(),
						tbmallitem.getOrderId(), tbmallitem.getProductName(),
						tbmallitem.getProductDescription(),
						tbmallitem.getProductImageUrl(),
						tbmallitem.getProductUnit(),
						tbmallitem.getProductPrice(),
						tbmallitem.getUnitAmount(),
						tbmallitem.getScoreCoefficient(), a);
				oitemlist.add(orderitem);
			}

			OrderJson or = new OrderJson(orderlist1.get(i).getId(), orderlist1.get(i)
					.getOrderCount(), tbshop.getShopName(), orderlist1.get(i)
					.getOrderState(), orderlist1.get(i).getOrderCreateTime(),
					oitemlist, orderlist1.get(i).getTotalCount(), orderlist1
							.get(i).getTotalPrice());
			orderlist.add(or);

		}
		
		return orderlist;
		
	}
	
	public List<String> getStateList(String state){
		List<String> statelist = new ArrayList<String>();
		statelist.add("已取消");
		statelist.add("已完成");
		statelist.add("待发货");
		statelist.add("已发货");

		for (int i = 0; i < statelist.size(); i++) {
			if (state.equals(statelist.get(i))) {
				statelist.remove(i);
			}
			
		}
		return statelist;
	}
	
	
	
}