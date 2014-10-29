package xjnu.edu.weixin.repair.service.impl.repair;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xjnu.edu.weixin.repair.service.repair.TbProblemTypeServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("tbProblemTypeService")
@Transactional
public class TbProblemTypeServiceImpl extends CommonServiceImpl implements TbProblemTypeServiceI {
	
}