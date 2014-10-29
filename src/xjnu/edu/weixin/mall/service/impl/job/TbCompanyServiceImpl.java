package xjnu.edu.weixin.mall.service.impl.job;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xjnu.edu.weixin.mall.service.job.TbCompanyServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("tbCompanyService")
@Transactional
public class TbCompanyServiceImpl extends CommonServiceImpl implements TbCompanyServiceI {
	
}