package xjnu.edu.weixin.mall.service.mall;

import java.util.List;

import org.jeecgframework.core.common.service.CommonService;

import xjnu.edu.weixin.mall.entity.mall.TbAddressEntity;
import xjnu.edu.weixin.mall.entity.mall.TbBuilddingEntity;
import xjnu.edu.weixin.mall.json.mall.AddressJson;

public interface TbAddressServiceI extends CommonService{
	
	public TbAddressEntity getCurrentAddress(String openuserid);
	//得到地址list
	public List<AddressJson> getAddressList(String openuserid);
	//得到学校的建筑物list
	public List<TbBuilddingEntity> getTbBuildlist(String openuserid);
	public void saveAddress(String buidldingid,String contact,String housecode,String mobilenumber,String userid);
	public void updateAddress(String addressid,String buidldingid,String contact,String housecode,String mobilenumber,String userid);
	public void deleteAddress(String addressid);
	public void chooseAddress(String addressid);
}
