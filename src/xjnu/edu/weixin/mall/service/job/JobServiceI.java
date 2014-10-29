package xjnu.edu.weixin.mall.service.job;

import java.util.List;
import org.jeecgframework.core.common.service.CommonService;
import xjnu.edu.weixin.mall.entity.job.TbJobEntity;

public interface JobServiceI extends CommonService{
	   public List<TbJobEntity> getJob(int pageNo);
	   public int getJobCount();
}
