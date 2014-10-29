package xjnu.edu.weixin.repair.service.impl.repair;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xjnu.edu.weixin.repair.service.repair.TbOperaterTypeServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("tbOperaterTypeService")
@Transactional
public class TbOperaterTypeServiceImpl extends CommonServiceImpl implements TbOperaterTypeServiceI {
	
}