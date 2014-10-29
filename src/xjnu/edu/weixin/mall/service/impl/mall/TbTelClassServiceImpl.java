package xjnu.edu.weixin.mall.service.impl.mall;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xjnu.edu.weixin.mall.entity.mall.TbTelCatalogEntity;
import xjnu.edu.weixin.mall.entity.mall.TbTelClassEntity;
import xjnu.edu.weixin.mall.json.mall.TbTelClassEntityJson;
import xjnu.edu.weixin.mall.service.mall.TbTelClassServiceI;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;

@Service("tbTelClassService")
@Transactional
public class TbTelClassServiceImpl extends CommonServiceImpl implements TbTelClassServiceI {
	@Override
	public List<TbTelClassEntity> getTbTelClassEntityBySchoolId(String schoolId) {
		DetachedCriteria dc = DetachedCriteria.forClass(TbTelClassEntity.class);
		 dc = dc.createCriteria("tbTelCatalogEntitys", "telcatalog");   
	     dc = dc.createCriteria("telcatalog.tbTelItemEntitys", "telitem");   
		 dc.add(Restrictions.eq("telitem.schoolId", schoolId));
		 dc.setResultTransformer(DetachedCriteria.DISTINCT_ROOT_ENTITY);
		 List<TbTelClassEntity> tbTelclass =findByDetached(dc);
		return tbTelclass;
	}
	@Override
	public List<TbTelCatalogEntity> getTbTelCatalogEntityBySchoolIdAndClassId(
			String schoolId, String classId) {
		DetachedCriteria dc1 = DetachedCriteria.forClass(TbTelCatalogEntity.class);
		 dc1.add(Restrictions.eq("tbTelClassEntity.id", classId));   
	     dc1 = dc1.createCriteria("tbTelItemEntitys", "telitem");   
		 dc1.add(Restrictions.eq("telitem.schoolId", schoolId));
		 dc1.setResultTransformer(DetachedCriteria.DISTINCT_ROOT_ENTITY);
		 List<TbTelCatalogEntity> tbTelcatalog =findByDetached(dc1);
		return tbTelcatalog;
	}

	@Override
	public List<TbTelClassEntityJson> getTbTelClassEntityJsonBySchoolId(
			String schoolId) {
		List<TbTelClassEntityJson> tbTelClassEntityJsonList=new ArrayList<TbTelClassEntityJson>();
		List<TbTelClassEntity> tbTelClassEntityList= getTbTelClassEntityBySchoolId(schoolId);
		for(TbTelClassEntity tb : tbTelClassEntityList){
			TbTelClassEntityJson tbtel=new TbTelClassEntityJson(tb);
			List<TbTelCatalogEntity> tbTelCatalogEntityList=getTbTelCatalogEntityBySchoolIdAndClassId(schoolId,tb.getId());
		    tbtel.setTbTelCatalogEntityJson(tbTelCatalogEntityList);
		    tbTelClassEntityJsonList.add(tbtel);
		}
		return tbTelClassEntityJsonList;
	}
}