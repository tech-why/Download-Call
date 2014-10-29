package xjnu.edu.weixin.repair.service.impl.repair;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xjnu.edu.weixin.repair.service.repair.TbProblemOperateRecordServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("tbProblemOperateRecordService")
@Transactional
public class TbProblemOperateRecordServiceImpl extends CommonServiceImpl implements TbProblemOperateRecordServiceI {
	
}