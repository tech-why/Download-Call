package xjnu.edu.weixin.mall.service.impl.job;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xjnu.edu.weixin.mall.service.job.TbJobCatalogServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("tbJobCatalogService")
@Transactional
public class TbJobCatalogServiceImpl extends CommonServiceImpl implements TbJobCatalogServiceI {
	
}