package xjnu.edu.weixin.mall.service.impl.mall;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xjnu.edu.weixin.mall.service.mall.TbMallProductServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("tbMallProductService")
@Transactional
public class TbMallProductServiceImpl extends CommonServiceImpl implements TbMallProductServiceI {
	
}