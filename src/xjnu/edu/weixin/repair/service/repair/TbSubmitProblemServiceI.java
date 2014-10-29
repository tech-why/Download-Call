package xjnu.edu.weixin.repair.service.repair;

import java.util.List;

import org.jeecgframework.core.common.service.CommonService;

import xjnu.edu.weixin.mall.entity.mall.TbAddressEntity;
import xjnu.edu.weixin.mall.entity.mall.TbSchoolEntity;
import xjnu.edu.weixin.mall.json.mall.OperatorRecordJson;
import xjnu.edu.weixin.mall.json.mall.ProblemJson;
import xjnu.edu.weixin.repair.entity.repair.TbProblemOperateRecordEntity;
import xjnu.edu.weixin.repair.entity.repair.TbProblemOperaterEntity;
import xjnu.edu.weixin.repair.entity.repair.TbSubmitProblemEntity;

public interface TbSubmitProblemServiceI extends CommonService{
	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(TbSubmitProblemEntity tbSubmitProblem,
	        List<TbProblemOperateRecordEntity> tbProblemOperateRecordList) ;
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(TbSubmitProblemEntity tbSubmitProblem,
	        List<TbProblemOperateRecordEntity> tbProblemOperateRecordList);
	public void delMain (TbSubmitProblemEntity tbSubmitProblem);
	public  String listToReplaceStr(List<TbAddressEntity> Tbaddresslist);
	public TbSchoolEntity getschoolentity(String openuserid);
	public String submitproblem(String typeid,String userOpenId,String addressId, String message,TbSubmitProblemEntity tbsb);
	public int getProblemCountNumber();
	public List<ProblemJson> getProblemList(String openuserid,String state);
	public ProblemJson getproblemJson(String problemid);
	public List<OperatorRecordJson> getoprecordList(String problemid);
	public TbProblemOperaterEntity getoperater(String openuserid);
	public List<ProblemJson> getProblemListByOperator(String operatorid,String state);
	public boolean Weixiu(String problemid,String openuserid,String state);
	public boolean disproblem(String problemid,String openuserid);
}
