package xjnu.edu.weixin.mall.service.impl.job;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xjnu.edu.weixin.mall.service.job.TbJobServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("tbJobService")
@Transactional
public class TbJobServiceImpl extends CommonServiceImpl implements TbJobServiceI {
	
}