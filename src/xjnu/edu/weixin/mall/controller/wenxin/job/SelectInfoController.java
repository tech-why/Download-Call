package xjnu.edu.weixin.mall.controller.wenxin.job;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.hibernate.mapping.Array;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.web.system.pojo.base.TSTerritory;
import org.jeecgframework.web.system.service.SystemService;

import xjnu.edu.weixin.mall.entity.job.TbAreaCatalogEntity;
import xjnu.edu.weixin.mall.entity.job.TbAreaClassEntity;
import xjnu.edu.weixin.mall.entity.job.TbCompanyEntity;
import xjnu.edu.weixin.mall.entity.job.TbJobClassEntity;
import xjnu.edu.weixin.mall.entity.job.TbParttimeJobEntity;
import xjnu.edu.weixin.mall.entity.mall.TbSchoolEntity;
import xjnu.edu.weixin.mall.utils.JsArrayCreator;
import xjnu.edu.weixin.mall.utils.JsArrayItemModal;

/**   
 * @Title: Controller
 * @Description: 工作表
 * @author zhangdaihao
 * @date 2014-09-16 12:34:03
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/selectInfoController")
public class SelectInfoController extends BaseController {
	private JsArrayCreator jac = new JsArrayCreator();
	@Autowired
	private SystemService systemService;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * 工作表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "selectJob")
	public ModelAndView selectJob(HttpServletRequest request) {
		short no1=3;
		
		List<TSTerritory> tSTerritoryList = systemService.findByProperty(TSTerritory.class, "territoryLevel", no1);
		JsArrayItemModal parrentItemModal = new JsArrayItemModal( "id" , "territoryName" ,"TSTerritorys" ,  "id" );
		JsArrayItemModal childItemModal = new JsArrayItemModal( "id" , "territoryName" ,  "", "id" );
		String location = jac.getJsArray("tSTerritory_id", parrentItemModal, childItemModal, tSTerritoryList) ;
        request.setAttribute("location", location);
       
        List<TbCompanyEntity> tbCompanyEntityList = systemService.loadAll(TbCompanyEntity.class);
        JsArrayItemModal parrentItemModal1 = new JsArrayItemModal( "id" , "name" ,"" ,  "id" );
        String company = jac.getJsArray("tbCompanyEntity_id", parrentItemModal1, tbCompanyEntityList) ;
        request.setAttribute("company", company);
		return new ModelAndView("xjnu/edu/weixin/mall_user/job/select_Job");
	}

	
	@RequestMapping(params = "selectPartTimeJob")
	public ModelAndView selectPartTimeJob(HttpServletRequest request) {
		List<TbJobClassEntity> tbJobClassList = systemService.loadAll(TbJobClassEntity.class);
		JsArrayItemModal parrentItemModal = new JsArrayItemModal( "id" , "jobClassName" ,"tbJobCatalogEntitys" ,  "id" );
		JsArrayItemModal childItemModal = new JsArrayItemModal( "id" , "jobCatalogName" ,  "", "id" );
		String PartTimeJob = jac.getJsArray("tbJobCatalogEntity_id", parrentItemModal, childItemModal, tbJobClassList) ;
        request.setAttribute("partTimeJob", PartTimeJob);
        
        List<TbAreaClassEntity> tbAreaClassList = systemService.loadAll(TbAreaClassEntity.class);
		JsArrayItemModal parrentItemModal1 = new JsArrayItemModal( "id" , "className" ,"tbAreaCatalogEntitys" ,  "id" );
		JsArrayItemModal childItemModal1= new JsArrayItemModal( "id" , "catalogName" ,  "", "id" );
		String tbArea = jac.getJsArray("tbAreaCatalogEntity_id", parrentItemModal1, childItemModal1, tbAreaClassList) ;
        request.setAttribute("tbArea", tbArea);  
		return new ModelAndView("xjnu/edu/weixin/mall_user/job/select_partTimeJob");
	}
	
}
