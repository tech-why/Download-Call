package xjnu.edu.weixin.repair.service.impl.repair;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xjnu.edu.weixin.mall.entity.mall.TbAddressEntity;
import xjnu.edu.weixin.mall.entity.mall.TbBuilddingEntity;
import xjnu.edu.weixin.mall.entity.mall.TbMallCart;
import xjnu.edu.weixin.mall.entity.mall.TbMallOrderItemEntity;
import xjnu.edu.weixin.mall.entity.mall.TbMallShopEntity;
import xjnu.edu.weixin.mall.entity.mall.TbSchoolEntity;
import xjnu.edu.weixin.mall.entity.mall.TbmallOrderEntity;
import xjnu.edu.weixin.mall.entity.mall.WeixinUserinfoEntity;
import xjnu.edu.weixin.mall.json.mall.OperatorRecordJson;
import xjnu.edu.weixin.mall.json.mall.ProblemJson;
import xjnu.edu.weixin.repair.entity.repair.TbOperaterBuilddingEntity;
import xjnu.edu.weixin.repair.entity.repair.TbOperaterTypeEntity;
import xjnu.edu.weixin.repair.entity.repair.TbProblemOperateRecordEntity;
import xjnu.edu.weixin.repair.entity.repair.TbProblemOperaterEntity;
import xjnu.edu.weixin.repair.entity.repair.TbProblemTypeEntity;
import xjnu.edu.weixin.repair.entity.repair.TbSubmitProblemEntity;
import xjnu.edu.weixin.repair.service.repair.TbSubmitProblemServiceI;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.StringUtil;

@Service("tbSubmitProblemService")
@Transactional
public class TbSubmitProblemServiceImpl extends CommonServiceImpl implements TbSubmitProblemServiceI {
	

	public void addMain(TbSubmitProblemEntity tbSubmitProblem,
	        List<TbProblemOperateRecordEntity> tbProblemOperateRecordList){
			tbSubmitProblem.setProblemCount(this.getProblemCountNumber());
			//保存主信息
			this.save(tbSubmitProblem);
		
			/**保存-维修记录*/
			for(TbProblemOperateRecordEntity tbProblemOperateRecord:tbProblemOperateRecordList){
				//外键设置
				tbProblemOperateRecord.setProblemId(tbSubmitProblem.getId());
				System.out.println(tbProblemOperateRecord.getOperateState());
				this.save(tbProblemOperateRecord);
			}
	}

	
	public void updateMain(TbSubmitProblemEntity tbSubmitProblem,
	        List<TbProblemOperateRecordEntity> tbProblemOperateRecordList) {
				this.saveOrUpdate(tbSubmitProblem);
		//===================================================================================
		//获取参数
		Object id0 = tbSubmitProblem.getId();
		//===================================================================================
		//1.查询出数据库的明细数据-维修记录
	    String hql0 = "from TbProblemOperateRecordEntity where 1 = 1 AND problemId = ? ";
	    List<TbProblemOperateRecordEntity> tbProblemOperateRecordOldList = this.findHql(hql0,id0);
		//2.筛选更新明细数据-维修记录
		for(TbProblemOperateRecordEntity oldE:tbProblemOperateRecordOldList){
			boolean isUpdate = false;
				for(TbProblemOperateRecordEntity sendE:tbProblemOperateRecordList){
					//需要更新的明细数据-维修记录
					if(oldE.getId().equals(sendE.getId())){
		    			try {
							MyBeanUtils.copyBeanNotNull2Bean(sendE,oldE);
							this.saveOrUpdate(oldE);
						} catch (Exception e) {
							e.printStackTrace();
							throw new BusinessException(e.getMessage());
						}
						isUpdate= true;
		    			break;
		    		}
		    	}
	    		if(!isUpdate){
		    		//如果数据库存在的明细，前台没有传递过来则是删除-维修记录
		    		super.delete(oldE);
	    		}
	    		
			}
		//3.持久化新增的数据-维修记录
		for(TbProblemOperateRecordEntity tbProblemOperateRecord:tbProblemOperateRecordList){
			if(tbProblemOperateRecord.getId()==null){
				//外键设置
				tbProblemOperateRecord.setProblemId(tbSubmitProblem.getId());
				this.save(tbProblemOperateRecord);
			}
		}
		
	}
	public void delMain(TbSubmitProblemEntity tbSubmitProblem) {
		//删除主表信息
		this.delete(tbSubmitProblem);
		//===================================================================================
		//获取参数
		Object id0 = tbSubmitProblem.getId();
		//===================================================================================
		//删除-维修记录
	    String hql0 = "from TbProblemOperateRecordEntity where 1 = 1 AND problemId = ? ";
	    List<TbProblemOperateRecordEntity> tbProblemOperateRecordOldList = this.findHql(hql0,id0);
		this.deleteAllEntitie(tbProblemOperateRecordOldList);
	}
	//让页面显示第二层js
	public  String listToReplaceStr(List<TbAddressEntity> Tbaddresslist){
		List<String> strList = new ArrayList<String>();
		for (TbAddressEntity TbAddressEntity : Tbaddresslist) {
			String perStr = null;
			String sufStr = null;
			try {
				String buddingid = TbAddressEntity.getBuilddingId();
				TbBuilddingEntity tbBuilddingEntity=this.getEntity(TbBuilddingEntity.class,buddingid);
				 perStr=tbBuilddingEntity.getBuilddingName()+TbAddressEntity.getHouseCode();
				sufStr = TbAddressEntity.getId();
			} catch (Exception e) {
				e.printStackTrace();
			}
			strList.add(perStr + "_" +sufStr);
		}
		return StringUtils.join(strList, ",");
	}

	public TbSchoolEntity getschoolentity(String openuserid){
		// TODO Auto-generated method stub
		WeixinUserinfoEntity weixinUserinfoEntity = this.findUniqueByProperty(
				WeixinUserinfoEntity.class, "openid", openuserid);
		if (weixinUserinfoEntity == null) {
			return null;
		}
		TbSchoolEntity tbSchoolEntity=this.getEntity(TbSchoolEntity.class,weixinUserinfoEntity.getSchoolId());
		if(tbSchoolEntity==null){
			return null;
		}else{
			return tbSchoolEntity;
		}
	}
	public String submitproblem(String typeid,String userOpenId,String addressId, String message,TbSubmitProblemEntity tbsb) {
		// TODO Auto-generated method stub
		WeixinUserinfoEntity user = this.findUniqueByProperty(
				WeixinUserinfoEntity.class, "openid", userOpenId);
		TbAddressEntity tbAddressEntity=this.getEntity(TbAddressEntity.class, addressId);
		tbsb.setTbAddressEntity(tbAddressEntity);
		tbsb.setUserId(user.getId());
		tbsb.setState("待处理");
		tbsb.setProblemDescriptionf(message);
		tbsb.setProblemTypeId(typeid);
		tbsb.setSubmitTime(new Date());
		tbsb.setProblemCount(this.getProblemCountNumber());
		
		
		this.save(tbsb);

		return "保修成功";
	}
	
	public List<ProblemJson> getProblemList(String openuserid,String state){
		WeixinUserinfoEntity weixinUserinfoEntity = this.findUniqueByProperty(
				WeixinUserinfoEntity.class, "openid", openuserid);
		if (weixinUserinfoEntity == null) {
			return null;
		}
		List<TbSubmitProblemEntity> tbSubmitProblemlist;
		if(state.equals("isAll")){
			tbSubmitProblemlist=this.findByProperty(TbSubmitProblemEntity.class, "userId", weixinUserinfoEntity.getId());
		}else{
			String hql="from TbSubmitProblemEntity tb where tb.userId=? and state=?";
			Object[] params = new Object[] { 	weixinUserinfoEntity.getId(),state };
			tbSubmitProblemlist=this.findHql(hql, params);
		}
	    if(tbSubmitProblemlist==null){
	    	return null;
	    }else{
	    	List<ProblemJson> problemlist=new ArrayList<ProblemJson>();
	    	for(TbSubmitProblemEntity tbsp:tbSubmitProblemlist){
	    		TbProblemTypeEntity typeentity=this.getEntity(TbProblemTypeEntity.class,tbsp.getProblemTypeId());
	    		TbAddressEntity tbAddressEntity=tbsp.getTbAddressEntity();
	    		TbBuilddingEntity tbBuilddingEntity=this.getEntity(TbBuilddingEntity.class,tbAddressEntity.getBuilddingId());
	    		ProblemJson prj=new ProblemJson(tbsp.getId(), tbsp.getUserId(),tbAddressEntity,tbBuilddingEntity, typeentity, tbsp.getProblemDescriptionf(), tbsp.getState(), tbsp.getSubmitTime(), tbsp.getCompleteTime(), tbsp.getProblemCount());
	    		problemlist.add(prj);
	    	}
	    	return problemlist;
	    }
	}
	public List<ProblemJson> getProblemListByOperator(String operatorid,String state){
		String hql="from TbOperaterBuilddingEntity tb where tb.operateorId=?";
		List<TbOperaterBuilddingEntity> tbOperaterBuilddingList=this.findHql(hql, operatorid);
		if(tbOperaterBuilddingList==null){
			return null;
		}
		List<ProblemJson> problemJsonslist=new ArrayList<ProblemJson>();
		for(TbOperaterBuilddingEntity tbbe:tbOperaterBuilddingList){
			DetachedCriteria dc = DetachedCriteria.forClass(TbSubmitProblemEntity.class);
			if(state.equals("isAll")==false){
				dc.add(Restrictions.eq("state", state));
			}
			dc = dc.createCriteria("tbAddressEntity", "tbAddress");   
			dc = dc.createCriteria("tbAddress.tbBuilddingEntity", "tbbudding");   
			dc.add(Restrictions.eq("tbbudding.id", tbbe.getBuilddingId()));
			dc.setResultTransformer(DetachedCriteria.DISTINCT_ROOT_ENTITY);
			List<TbSubmitProblemEntity> tbSubmitProblelist = this.findByDetached(dc);
			for(TbSubmitProblemEntity tbsp:tbSubmitProblelist){
				
				TbProblemTypeEntity typeentity=this.getEntity(TbProblemTypeEntity.class,tbsp.getProblemTypeId());
	    		TbAddressEntity tbAddressEntity=tbsp.getTbAddressEntity();
	    		TbBuilddingEntity tbBuilddingEntity=this.getEntity(TbBuilddingEntity.class,tbAddressEntity.getBuilddingId());
	    		ProblemJson prj=new ProblemJson(tbsp.getId(), tbsp.getUserId(),tbAddressEntity,tbBuilddingEntity, typeentity, tbsp.getProblemDescriptionf(), tbsp.getState(), tbsp.getSubmitTime(), tbsp.getCompleteTime(), tbsp.getProblemCount());
				problemJsonslist.add(prj);
			
			}
			}
		return problemJsonslist;
		}
	  
	  public ProblemJson getproblemJson(String problemid){
		  ProblemJson proj;
		  TbSubmitProblemEntity tbsp=this.getEntity(TbSubmitProblemEntity.class,problemid);
		  if(tbsp==null){
		   return null;
		  }else{
			  TbProblemTypeEntity typeentity=this.getEntity(TbProblemTypeEntity.class,tbsp.getProblemTypeId());
	    		TbAddressEntity tbAddressEntity=tbsp.getTbAddressEntity();
	    		TbBuilddingEntity tbBuilddingEntity=this.getEntity(TbBuilddingEntity.class,tbAddressEntity.getBuilddingId());
			  proj=new ProblemJson(tbsp.getId(), tbsp.getUserId(),tbAddressEntity,tbBuilddingEntity, typeentity, tbsp.getProblemDescriptionf(), tbsp.getState(), tbsp.getSubmitTime(), tbsp.getCompleteTime(), tbsp.getProblemCount());
			  return proj;
		  }
	   }
	  
	  public List<OperatorRecordJson> getoprecordList(String problemid){
		  
		  List<TbProblemOperateRecordEntity> tbrecordlist=this.findByProperty(TbProblemOperateRecordEntity.class, "problemId", problemid);
		  if(tbrecordlist==null){
			  return null;
		  }else{
			  List<OperatorRecordJson> tbrecordjsonlist=new ArrayList<OperatorRecordJson>();
			  for(TbProblemOperateRecordEntity tbrs:tbrecordlist){
				  WeixinUserinfoEntity weixinUserinfoEntity=this.getEntity(WeixinUserinfoEntity.class,tbrs.getOperateId());
				  OperatorRecordJson rps=new OperatorRecordJson(tbrs.getId(),tbrs.getProblemId(), tbrs.getOperateState(), weixinUserinfoEntity, tbrs.getOperateTime());
				  tbrecordjsonlist.add(rps);
			  }
			  return tbrecordjsonlist;
		  }
		 
	  }
	  
	public int getProblemCountNumber(){
		Integer maxCount = this.singleResult("select max(problemCount) from TbSubmitProblemEntity problem");
		if( maxCount == null)
			maxCount  = 1 ;
		return maxCount + 1 ;
	}
	public TbProblemOperaterEntity getoperater(String openuserid){
		WeixinUserinfoEntity weixinUserinfoEntity = this.findUniqueByProperty(
				WeixinUserinfoEntity.class, "openid", openuserid);
		if (weixinUserinfoEntity == null) {
			return null;
		}
		TbProblemOperaterEntity tbProblemOperaterEntity=this.findUniqueByProperty(TbProblemOperaterEntity.class, "weixinUserId", weixinUserinfoEntity.getId());
		if(tbProblemOperaterEntity==null){
			return null;
		}else{
			return tbProblemOperaterEntity;
		}
	}
	public boolean Weixiu(String problemid,String openuserid,String state){
		TbProblemOperaterEntity tboperater=this.getoperater(openuserid);
		if(tboperater==null){
			return false;
		}
		TbSubmitProblemEntity tbsp=this.getEntity(TbSubmitProblemEntity.class,problemid);
		if(tbsp==null){
			return false;
		}
		TbProblemOperateRecordEntity tbrecord=new TbProblemOperateRecordEntity();
		tbrecord.setOperateId(tboperater.getId());
		tbrecord.setOperateState(state);
		tbrecord.setOperateTime(new Date());
		tbrecord.setProblemId(problemid);
		this.save(tbrecord);
		if(state.equals("已经完成")){
			tbsp.setCompleteTime(tbrecord.getOperateTime());
		}
		
		tbsp.setState(state);
		this.saveOrUpdate(tbsp);
		return true;
	}
	public boolean disproblem(String problemid,String openuserid){
		WeixinUserinfoEntity weixinUserinfoEntity=this.findUniqueByProperty(WeixinUserinfoEntity.class, "openid", openuserid);
		if(weixinUserinfoEntity==null){
			return false;
		}
		TbSubmitProblemEntity tbsp=this.getEntity(TbSubmitProblemEntity.class,problemid);
		if(tbsp==null){
			return false;
		}
		tbsp.setState("已取消");
		return true;
		
	}
}
