package xjnu.edu.weixin.mall.service.mall;

import java.util.List;

import org.jeecgframework.core.common.service.CommonService;
import xjnu.edu.weixin.mall.entity.mall.TbTelCatalogEntity;
import xjnu.edu.weixin.mall.entity.mall.TbTelClassEntity;
import xjnu.edu.weixin.mall.json.mall.TbTelClassEntityJson;
public interface TbTelClassServiceI extends CommonService{
	public List<TbTelClassEntity> getTbTelClassEntityBySchoolId(String schoolId);
	public List<TbTelCatalogEntity> getTbTelCatalogEntityBySchoolIdAndClassId(String schoolId,String classId);
	public List<TbTelClassEntityJson> getTbTelClassEntityJsonBySchoolId(String schoolId);
}
