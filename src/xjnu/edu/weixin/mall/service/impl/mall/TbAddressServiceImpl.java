package xjnu.edu.weixin.mall.service.impl.mall;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xjnu.edu.weixin.mall.entity.mall.TbAddressEntity;
import xjnu.edu.weixin.mall.entity.mall.TbBuilddingClass;
import xjnu.edu.weixin.mall.entity.mall.TbBuilddingEntity;
import xjnu.edu.weixin.mall.entity.mall.TbSchoolEntity;
import xjnu.edu.weixin.mall.entity.mall.WeixinUserinfoEntity;
import xjnu.edu.weixin.mall.json.mall.AddressJson;
import xjnu.edu.weixin.mall.service.mall.TbAddressServiceI;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("tbAddressService")
@Transactional
public class TbAddressServiceImpl extends CommonServiceImpl implements
		TbAddressServiceI {
	

	// 得到地址list
	public List<AddressJson> getAddressList(String openuserid) {
		WeixinUserinfoEntity weixinUserinfoEntity = this.findUniqueByProperty(
				WeixinUserinfoEntity.class, "openid", openuserid);
		if (weixinUserinfoEntity == null) {
			return null;
		}
		TbSchoolEntity tbschool = this.getEntity(TbSchoolEntity.class,
				weixinUserinfoEntity.getSchoolId());

		List<AddressJson> addresslist = new ArrayList<AddressJson>();
		String hql = "from TbAddressEntity where weixinUserId=? and isActive=? and tbBuilddingEntity.schoolId=? ";
		Object[] params = new Object[] { weixinUserinfoEntity.getId(), "是" , weixinUserinfoEntity.getSchoolId() };
		List<TbAddressEntity> tbaddresslist = this.findHql(hql, params);
		for (TbAddressEntity tb : tbaddresslist) {
			TbBuilddingEntity tbbuilding = this.getEntity(
					TbBuilddingEntity.class, tb.getTbBuilddingEntity().getId());
			AddressJson add = new AddressJson(tb.getId(),
					tbbuilding.getBuilddingName(), tb.getContact(),
					tb.getPhoneNumber(), tb.getMobileNumber(),
					tb.getHouseCode(), tb.getIsActive(), tb.getIsChoose(),
					tbschool.getSchoolName());
			addresslist.add(add);

		}
		return addresslist;

	}

	public List<TbBuilddingEntity> getTbBuildlist(String openuserid) {
		List<TbBuilddingEntity> tbbulidlist=new ArrayList<TbBuilddingEntity>();
		
		WeixinUserinfoEntity weixinUserinfoEntity = this.findUniqueByProperty(
				WeixinUserinfoEntity.class, "openid", openuserid);
		if (weixinUserinfoEntity == null) {
			return null;
		}
		tbbulidlist=this.findByProperty(TbBuilddingEntity.class,"schoolId",weixinUserinfoEntity.getSchoolId());
		
		return tbbulidlist;
	}
	
	public void saveAddress(String buidldingid,String contact,String housecode,String mobilenumber,String userid){
		WeixinUserinfoEntity weixinUserinfoEntity = this.findUniqueByProperty(
				WeixinUserinfoEntity.class, "openid", userid);
		//以防有多个选中的
		List<TbAddressEntity> tbchooseAddressEntity=this.findByProperty(TbAddressEntity.class, "isChoose","是");
		for(TbAddressEntity s:tbchooseAddressEntity){
			s.setIsChoose("否");
			this.saveOrUpdate(s);
		}
		TbAddressEntity tbAddressEntity=new TbAddressEntity();
		tbAddressEntity.setBuilddingId(buidldingid);
		tbAddressEntity.setContact(contact);
		tbAddressEntity.setHouseCode(housecode);
		tbAddressEntity.setIsActive("是");
		tbAddressEntity.setIsChoose("是");
		tbAddressEntity.setMobileNumber(mobilenumber);
		tbAddressEntity.setWeixinUserId(weixinUserinfoEntity.getId());
		this.save(tbAddressEntity);
		

	}
	public void updateAddress(String addressid,String buidldingid,String contact,String housecode,String mobilenumber,String userid){
		WeixinUserinfoEntity weixinUserinfoEntity = this.findUniqueByProperty(
				WeixinUserinfoEntity.class, "openid", userid);
		TbAddressEntity tbAddressEntity=this.getEntity(TbAddressEntity.class,addressid);
		tbAddressEntity.setBuilddingId(buidldingid);
		tbAddressEntity.setContact(contact);
		tbAddressEntity.setHouseCode(housecode);
		tbAddressEntity.setMobileNumber(mobilenumber);
		tbAddressEntity.setWeixinUserId(weixinUserinfoEntity.getId());
		this.saveOrUpdate(tbAddressEntity);
		
		
	}
	public void deleteAddress(String addressid){
		TbAddressEntity tbAddressEntity=this.getEntity(TbAddressEntity.class, addressid);
		tbAddressEntity.setIsActive("否");
		this.saveOrUpdate(tbAddressEntity);
	}
	public void chooseAddress(String addressid){
		//以防有多个选中的
		List<TbAddressEntity> tbchooseAddressEntity=this.findByProperty(TbAddressEntity.class, "isChoose","是");
		for(TbAddressEntity s:tbchooseAddressEntity){
			s.setIsChoose("否");
			this.saveOrUpdate(s);
		}
		TbAddressEntity tbAddressEntity=this.getEntity(TbAddressEntity.class,addressid);
		tbAddressEntity.setIsChoose("是");
	}

	@Override
	public TbAddressEntity getCurrentAddress(String openuserid) {
		// TODO Auto-generated method stub
		WeixinUserinfoEntity weixinUserinfoEntity = this.findUniqueByProperty(
				WeixinUserinfoEntity.class, "openid", openuserid);
		if (weixinUserinfoEntity == null) {
			return null;
		}
		String hql = "from TbAddressEntity where weixinUserId=? and isActive=? and tbBuilddingEntity.schoolId=? and isChoose='是'";
		Object[] params = new Object[] { weixinUserinfoEntity.getId(), "是" , weixinUserinfoEntity.getSchoolId() };
		List<TbAddressEntity> tbaddresslist = this.findHql(hql, params);
		if(tbaddresslist.size() == 0)
			return null;
		return tbaddresslist.get(0);
	}
	
}