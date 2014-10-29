package xjnu.edu.weixin.mall.service.mall;

import java.util.List;

import org.jeecgframework.core.common.service.CommonService;

import xjnu.edu.weixin.mall.entity.mall.TbMallProductEntity;

public interface ProductServiceI extends CommonService{
   public List<TbMallProductEntity> getProduct(String shopId,String catalogId,int pageNo);
   public int getProductPageCount(String shopId,String catalogId);
}
