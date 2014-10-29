package xjnu.edu.weixin.mall.service.impl.mall;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xjnu.edu.weixin.mall.entity.mall.TbMallProductEntity;
import xjnu.edu.weixin.mall.service.mall.ProductServiceI;
import xjnu.edu.weixin.mall.service.mall.TbMallProductServiceI;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("productService")
@Transactional
public class ProductServiceImpl extends CommonServiceImpl implements ProductServiceI {
	private static int itemCountSinglePage=10;
	/**获取相应页数的数据*/
	@Override
	public List<TbMallProductEntity> getProduct(String shopId,
			String catalogId, int pageNo) {
		List<TbMallProductEntity> tbMallProductEntityList=findHql("from TbMallProductEntity where shopId=? and catalogId=?", new Object[]{shopId,catalogId});
		List<TbMallProductEntity> productList = null ;
		int size= tbMallProductEntityList.size();
		int end=pageNo*itemCountSinglePage;
		int start=(pageNo-1)*itemCountSinglePage;
		if(start < size){
			if(size < end){
				end=size;
			}
			productList=tbMallProductEntityList.subList(start, end);
		}
		return productList;
	}
   /**获取总页数*/
	@Override
	public int getProductPageCount(String shopId, String catalogId) {
		List<TbMallProductEntity> tbMallProductEntityList=findHql("from TbMallProductEntity where shopId=? and catalogId=?", new Object[]{shopId,catalogId});
		int itemSize=tbMallProductEntityList.size();
		
		int result=itemSize % itemCountSinglePage;
		int pageNo;
		if(result==0){
			pageNo=itemSize / itemCountSinglePage;
		}else{
			pageNo=itemSize / itemCountSinglePage+1;
		}
		return pageNo;
	}
	
}