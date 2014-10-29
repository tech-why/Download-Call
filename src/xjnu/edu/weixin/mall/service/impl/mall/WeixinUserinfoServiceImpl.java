package xjnu.edu.weixin.mall.service.impl.mall;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xjnu.edu.weixin.mall.entity.mall.WeixinUserinfoEntity;
import xjnu.edu.weixin.mall.service.mall.WeixinUserinfoServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("weixinUserinfoService")
@Transactional
public class WeixinUserinfoServiceImpl extends CommonServiceImpl implements WeixinUserinfoServiceI {

	@Override
	public WeixinUserinfoEntity getWeixinUserInfoById(String userOpenid) {
		// TODO Auto-generated method stub
		List<WeixinUserinfoEntity> userInfoList = findByProperty(WeixinUserinfoEntity.class, "openid", userOpenid);
		if( userInfoList.size() == 0)
			return null;
		else
			return userInfoList.get(0);
	}

	@Override
	public void saveWinxinUserInfo(String userOpenid , String sys_accountId)
	{
		
		WeixinUserinfoEntity weixinUser =getWeixinUserInfoById(userOpenid);
		if(weixinUser == null)
		{
			weixinUser = new WeixinUserinfoEntity();
			weixinUser.setOpenid(userOpenid);
		}	
		else
		{
			weixinUser.setOpenid(userOpenid);
			weixinUser.setAddtime(new Timestamp(new Date().getTime()));
		}
		weixinUser.setAccountid(sys_accountId);
		weixinUser.setSubscribe("关注");
		saveOrUpdate(weixinUser);
		//weixinUser.setSubscribeTime(subscribeTime);
	}
	
}