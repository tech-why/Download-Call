package xjnu.edu.weixin.mall.service.job;

import java.util.List;

import org.jeecgframework.core.common.service.CommonService;

import xjnu.edu.weixin.mall.entity.job.TbParttimeJobEntity;

public interface ParttimeJobServiceI extends CommonService{
	   public List<TbParttimeJobEntity> getParttimeJob(int pageNo);
	   public int getParttimeJobCount();
}
