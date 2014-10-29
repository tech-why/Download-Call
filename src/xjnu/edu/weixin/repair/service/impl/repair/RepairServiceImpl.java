package xjnu.edu.weixin.repair.service.impl.repair;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xjnu.edu.weixin.mall.entity.mall.TbBuilddingCatalog;
import xjnu.edu.weixin.mall.entity.mall.TbBuilddingClass;
import xjnu.edu.weixin.mall.entity.mall.TbBuilddingEntity;
import xjnu.edu.weixin.mall.entity.mall.TbSchoolEntity;
import xjnu.edu.weixin.repair.entity.repair.TbOperaterBuilddingEntity;
import xjnu.edu.weixin.repair.entity.repair.TbSubmitProblemEntity;
import xjnu.edu.weixin.repair.service.repair.RepairServiceI;

import org.jeecgframework.core.common.model.json.ComboTree;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("repairServiceI")
@Transactional
public class RepairServiceImpl extends CommonServiceImpl implements RepairServiceI {
	
	private List<TbOperaterBuilddingEntity> allBuilddingList  ;
	
	@Override
	public List<ComboTree> getSchoolComboTree(String operateorId) {
		allBuilddingList =  this.findByProperty(TbOperaterBuilddingEntity.class, "operateorId", operateorId);
		List<TbSchoolEntity> schoolList = this.loadAll(TbSchoolEntity.class) ;
		return  getSchoolComboTree(  schoolList );
	}
	
	private List<ComboTree> getSchoolComboTree( List<TbSchoolEntity>  schoolList )
	{
		List<ComboTree> builddingClassComboTreeList = new ArrayList<ComboTree>();
		for( TbSchoolEntity school : schoolList)
		{
			ComboTree builddingClassComboTree = new ComboTree();
			builddingClassComboTree.setId(school.getId());
			builddingClassComboTree.setText(school.getSchoolName());
			builddingClassComboTree.setChildren( getBuilddingClassComboTree(school.getId() ) );
			builddingClassComboTreeList.add(builddingClassComboTree);
		}
		return builddingClassComboTreeList ;
		
	}
	
	private List<TbBuilddingClass> getBuilddingClassBySchoolId(String schoolId)
	{
		DetachedCriteria dc = DetachedCriteria.forClass(TbBuilddingClass.class); 
		dc = dc.createCriteria("tbBuilddingCatalogs", "catalog");   
		dc.createCriteria("catalog.builddingEntitySet", "buildding");   
		dc.add(Restrictions.eq("buildding.schoolId", schoolId));
		dc.setResultTransformer(DetachedCriteria.DISTINCT_ROOT_ENTITY);
		List<TbBuilddingClass> builddingClassList =  findByDetached(dc);
		return builddingClassList ;
	}
	
	private List<TbBuilddingCatalog> getBuilddingCatalogBySchoolId( String builddingClassId , String schoolId)
	{
		DetachedCriteria dc = DetachedCriteria.forClass(TbBuilddingCatalog.class);   
		dc.add(Restrictions.eq("tbBuilddingClass.id", builddingClassId));
		dc.createCriteria("builddingEntitySet", "buildding");   
		dc.add(Restrictions.eq("buildding.schoolId", schoolId));
		dc.setResultTransformer(DetachedCriteria.DISTINCT_ROOT_ENTITY);
		List<TbBuilddingCatalog> builddingCatalogList =  findByDetached(dc);
		return builddingCatalogList ;
	}
	
	private List<TbBuilddingEntity> getBuilddingBySchoolId( String builddingCatalogId , String schoolId)
	{
		DetachedCriteria dc = DetachedCriteria.forClass(TbBuilddingEntity.class);   
		dc.add(Restrictions.eq("catalogId", builddingCatalogId));  
		dc.add(Restrictions.eq("schoolId", schoolId));
		dc.setResultTransformer(DetachedCriteria.DISTINCT_ROOT_ENTITY);
		List<TbBuilddingEntity> builddingList =  findByDetached(dc);
		return builddingList ;
	}
	
	
	private List<ComboTree> getBuilddingClassComboTree(String schoolId)
	{
		List<TbBuilddingClass>  builddingClassList =  getBuilddingClassBySchoolId( schoolId) ;
		List<ComboTree> builddingClassComboTreeList = new ArrayList<ComboTree>();
		for( TbBuilddingClass builddingClass : builddingClassList)
		{
			ComboTree builddingClassComboTree = new ComboTree();
			builddingClassComboTree.setId(builddingClass.getId());
			builddingClassComboTree.setText(builddingClass.getClassName());
			builddingClassComboTree.setChildren( getBuilddingCatalogComboTree(builddingClass.getId() , schoolId) );
			builddingClassComboTreeList.add(builddingClassComboTree);
		}
		return builddingClassComboTreeList ;
		
	}

	private List<ComboTree> getBuilddingCatalogComboTree(String builddingClassId , String schoolId )
	{
		List<TbBuilddingCatalog>  builddingCatalogList = getBuilddingCatalogBySchoolId(builddingClassId , schoolId);
		List<ComboTree> builddingCatalogComboTreeList = new ArrayList<ComboTree>();
		for( TbBuilddingCatalog builddingCatalog : builddingCatalogList)
		{
			ComboTree builddingCatalogComboTree = new ComboTree();
			builddingCatalogComboTree.setId(builddingCatalog.getId());
			builddingCatalogComboTree.setText(builddingCatalog.getCatalogName());
			builddingCatalogComboTree.setChildren( getBuilddingComboTree(builddingCatalog.getId() , schoolId) );
			builddingCatalogComboTreeList.add(builddingCatalogComboTree);
		}
		return builddingCatalogComboTreeList ;
	}
	
	private List<ComboTree> getBuilddingComboTree( String builddingCatalogId , String schoolId )
	{
		List<TbBuilddingEntity>  builddingList =  getBuilddingBySchoolId(builddingCatalogId ,schoolId);
		List<ComboTree> builddingComboTreeList = new ArrayList<ComboTree>();
		for( TbBuilddingEntity buildding : builddingList)
		{
			ComboTree builddingComboTree = new ComboTree();
			builddingComboTree.setId(buildding.getId());
			builddingComboTree.setText(buildding.getBuilddingName());
			builddingComboTree.setChecked(false) ;
			if( isBuilddingSelected( buildding ) )
			{
				builddingComboTree.setChecked(true) ;
			}
			builddingComboTreeList.add(builddingComboTree);
		}
		return builddingComboTreeList ;
	}
	
	private boolean isBuilddingSelected( TbBuilddingEntity checkBuildding )
	{
		for( TbOperaterBuilddingEntity buildding : allBuilddingList)
		{
			if( buildding.getBuilddingId().equals(checkBuildding.getId()))
			{
				return true ;
			}
		}
		return false ;
	}

	@Override
	public void updateBuilddingAssignment(String[] builddingIds , String operaterId)
	{
		List<TbOperaterBuilddingEntity> deleteBuilddingList = new ArrayList<TbOperaterBuilddingEntity>();
		List<TbOperaterBuilddingEntity> addBuilddingList = new ArrayList<TbOperaterBuilddingEntity>();
		
		List<TbOperaterBuilddingEntity> checkedBuilddingList = this.findByProperty(TbOperaterBuilddingEntity.class , "operateorId", operaterId) ;
		for( TbOperaterBuilddingEntity operatorBuildding : checkedBuilddingList )
		{
			boolean deleteflag = true ;
			for(String updateBuilddingId : builddingIds)
			{
				if( operatorBuildding.getId().equals(updateBuilddingId))
				{
					deleteflag = false ;
					break;
				}
				
			}
			if(deleteflag)
				deleteBuilddingList.add(operatorBuildding);
		}
		for(String updateBuilddingId : builddingIds)
		{
			boolean addflag = true ;
			for( TbOperaterBuilddingEntity operatorBuildding : checkedBuilddingList )
			{
				if( operatorBuildding.getId().equals(updateBuilddingId))
				{
					addflag = false;
					break;
				}
			}
			if(addflag)
			{
				TbOperaterBuilddingEntity newOperatorBuildding = new TbOperaterBuilddingEntity();
				newOperatorBuildding.setBuilddingId(updateBuilddingId);
				newOperatorBuildding.setOperateorId(operaterId) ;
				addBuilddingList.add(newOperatorBuildding);
			}
		}
		this.deleteAllEntitie(deleteBuilddingList) ;
		this.batchSave(addBuilddingList);
		
	}
	
	
	/*public List<TbSubmitProblemEntity> getSubmitProblemByOperatorId( String operatorId)
	{
		DetachedCriteria dc = DetachedCriteria.forClass(TbSubmitProblemEntity.class); 
		dc.add(Restrictions.eq("tbAddressEntity", "address"));
		
		
		//dc = dc.createCriteria("tbAddressEntity", "address");   
		dc.createCriteria("catalog.builddingEntitySet", "buildding");   
		dc.add(Restrictions.eq("buildding.schoolId", schoolId));
		dc.setResultTransformer(DetachedCriteria.DISTINCT_ROOT_ENTITY);
		List<TbBuilddingClass> builddingClassList =  findByDetached(dc);
		return builddingClassList ;
		
		
	}*/
	
	
	
}