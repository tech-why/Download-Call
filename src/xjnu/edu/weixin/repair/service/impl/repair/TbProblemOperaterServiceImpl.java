package xjnu.edu.weixin.repair.service.impl.repair;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xjnu.edu.weixin.mall.entity.mall.TbSchoolEntity;
import xjnu.edu.weixin.mall.entity.mall.WeixinUserinfoEntity;
import xjnu.edu.weixin.repair.entity.repair.TbProblemOperateRecordEntity;
import xjnu.edu.weixin.repair.entity.repair.TbProblemOperaterEntity;
import xjnu.edu.weixin.repair.service.repair.TbProblemOperaterServiceI;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.web.system.pojo.base.TSUser;

@Service("tbProblemOperaterService")
@Transactional
public class TbProblemOperaterServiceImpl extends CommonServiceImpl implements TbProblemOperaterServiceI {
	public String getBindOperator() {
		// TODO Auto-generated method stub
		
		StringBuffer buffer = new StringBuffer();
		
		
		buffer.append("请按如下格式回复你的内容 如前台用户名：test后台用户名：test1则输入bdtest+test1）").append("\n");
		return buffer.toString();
	}

	public boolean BindOperater(String bindCode , String openUserId) {
		// TODO Auto-generated method stub
		if( !bindCode.startsWith("bd") )
		{
			return false ;
		}
		String bindkey = bindCode.substring(2);
		System.out.println(bindkey);
		String bindarray[]=bindkey.split("\\+");
		
		TSUser tsuser=this.findUniqueByProperty(TSUser.class,"userName", bindarray[1]);
		if(tsuser==null){
			return false;
		}
		Object[] params=new Object[]{
			bindarray[0],
			tsuser.getId()
		};
		System.out.println(tsuser.getId());
		System.out.println(params[0]);
		System.out.println(params[1]);
		try{
			
			String hql="from TbProblemOperaterEntity tb where userName=? and userId=?";
			List<TbProblemOperaterEntity> tbProblemOperaterEntitylist=this.findHql(hql, params);
			
			if(tbProblemOperaterEntitylist.size()<=0){
				return false;
			}
			if(tbProblemOperaterEntitylist.size()>1){
				return false;
			}
			tbProblemOperaterEntitylist.get(0).setWeixinUserId(openUserId);
			this.saveOrUpdate(tbProblemOperaterEntitylist.get(0));
			
		}
		catch(Exception ex)
		{
			return false ;
		}
		
		
		return true;
	}
	//是否绑定操作 如果绑定则返回你已经绑定
	public boolean isBindOperator(String openUserId) {
		// TODO Auto-generated method stub
		
		TbProblemOperaterEntity operatorentity = this.findUniqueByProperty(TbProblemOperaterEntity.class, "weixinUserId", openUserId) ;
		if( operatorentity == null )
		{
			return false;
		}
		else
		{
			return true;
		}
		
	}
	
	
	
}