package xjnu.edu.weixin.mall.service.impl.job;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xjnu.edu.weixin.mall.entity.job.TbParttimeJobEntity;
import xjnu.edu.weixin.mall.service.job.ParttimeJobServiceI;
import xjnu.edu.weixin.mall.service.job.TbParttimeJobServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("parttimeJobService")
@Transactional
public class ParttimeJobServiceImpl extends CommonServiceImpl implements ParttimeJobServiceI {
	private static int itemInfoPage=10;
	/**获取相应页数的数据*/
	@Override
	public List<TbParttimeJobEntity> getParttimeJob(int pageNo) {
		List<TbParttimeJobEntity> tbbParttimeJobList = loadAll(TbParttimeJobEntity.class);
		int itemSize=tbbParttimeJobList.size();
		List<TbParttimeJobEntity> pTbParttimeJobEntityList = null ;
		int end=pageNo*itemInfoPage;
		int start=(pageNo-1)*itemInfoPage;
		if(start < itemSize){
			if(itemSize < end){
				end=itemSize;
			}
			pTbParttimeJobEntityList=tbbParttimeJobList.subList(start, end);
		}
		return pTbParttimeJobEntityList;
	}
	/**获取总数*/
	@Override
	public int getParttimeJobCount() {
		List<TbParttimeJobEntity> tbbParttimeJobList = loadAll(TbParttimeJobEntity.class);
		int size=tbbParttimeJobList.size();
		return size;
	}
	
}