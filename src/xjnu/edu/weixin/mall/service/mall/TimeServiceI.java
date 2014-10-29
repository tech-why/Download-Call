package xjnu.edu.weixin.mall.service.mall;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.jeecgframework.core.common.service.CommonService;

import xjnu.edu.weixin.mall.entity.mall.TbMallCart;
import xjnu.edu.weixin.mall.entity.mall.TbMallWorkTimeEntity;


public interface TimeServiceI extends CommonService{

	public List<TbMallWorkTimeEntity> getTbMallWorkTimeList(String shopId);

	public String checkTime(String shopId, TbMallWorkTimeEntity selectWorkTime);

	public Calendar getRequestTime(TbMallWorkTimeEntity selectWorkTime);

	public Calendar getEndTime(TbMallWorkTimeEntity selectWorkTime);
	
	
}
