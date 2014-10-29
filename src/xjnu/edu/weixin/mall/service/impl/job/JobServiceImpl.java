package xjnu.edu.weixin.mall.service.impl.job;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xjnu.edu.weixin.mall.entity.job.TbJobEntity;
import xjnu.edu.weixin.mall.entity.job.TbParttimeJobEntity;
import xjnu.edu.weixin.mall.service.job.JobServiceI;
import xjnu.edu.weixin.mall.service.job.ParttimeJobServiceI;
import xjnu.edu.weixin.mall.service.job.TbParttimeJobServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("jobService")
@Transactional
public class JobServiceImpl extends CommonServiceImpl implements JobServiceI {
	private static int itemInfoPage=10;
	/**获取相应页数的数据*/
	@Override
	public List<TbJobEntity> getJob(int pageNo) {
		List<TbJobEntity> tbJobList = loadAll(TbJobEntity.class);
		int itemSize=tbJobList.size();
		List<TbJobEntity> tbJobEntityList = null ;
		int end=pageNo*itemInfoPage;
		int start=(pageNo-1)*itemInfoPage;
		if(start < itemSize){
			if(itemSize < end){
				end=itemSize;
			}
			tbJobEntityList=tbJobList.subList(start, end);
		}
		return tbJobEntityList;
	}
	/**获取总数*/
	@Override
	public int getJobCount() {
		List<TbJobEntity> tbJobList = loadAll(TbJobEntity.class);
		int size=tbJobList.size();
		return size;
	}
	
}