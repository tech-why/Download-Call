package xjnu.edu.weixin.mall.service.impl.mall;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xjnu.edu.weixin.mall.entity.mall.TbSchoolEntity;
import xjnu.edu.weixin.mall.entity.mall.WeixinUserinfoEntity;
import xjnu.edu.weixin.mall.service.mall.TbSchoolServiceI;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("tbSchoolService")
@Transactional
public class TbSchoolServiceImpl extends CommonServiceImpl implements TbSchoolServiceI {

	@Override
	public String getChangeSchoolString() {
		// TODO Auto-generated method stub
		
		List<TbSchoolEntity> schoolList = this.findByQueryString("from TbSchoolEntity order by displaySequence asc");
		StringBuffer buffer = new StringBuffer();
		
		
		buffer.append("请先回复所在学校代码，点击左下角切换输入模式，请直接回复【 】里的数字，并发送。（例：新疆师范大学 ，请直接回复：sch1 即可）").append("\n");
		for( TbSchoolEntity school : schoolList )
		{
			buffer.append( "【sch" + school.getDisplaySequence() +"】" + school.getSchoolName() ).append("\n") ;
		}
		return buffer.toString();
	}

	@Override
	public boolean changeSchool(String schoolCode , String openUserId) {
		// TODO Auto-generated method stub
		if( !schoolCode.startsWith("sch") )
		{
			return false ;
		}
		String schoolIndex = schoolCode.substring(3) ;
		TbSchoolEntity school = null ;
		WeixinUserinfoEntity userInfo = null ;
		int nSchoolIndex = 0;
		try{
			nSchoolIndex = Integer.parseInt(schoolIndex);
			school = this.findUniqueByProperty(TbSchoolEntity.class, "displaySequence", nSchoolIndex) ;
			userInfo = this.findUniqueByProperty(WeixinUserinfoEntity.class, "openid", openUserId) ;
			userInfo.setSchoolId(school.getId());
			this.updateEntitie(userInfo);
		}
		catch(Exception ex)
		{
			return false ;
		}
		
		
		return true;
	}

	@Override
	public boolean isSetSchool(String openUserId) {
		// TODO Auto-generated method stub
		
		WeixinUserinfoEntity userInfo = this.findUniqueByProperty(WeixinUserinfoEntity.class, "openid", openUserId) ;
		if( userInfo.getSchoolId() == null || userInfo.getSchoolId().equals(""))
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
}