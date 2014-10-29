package xjnu.edu.weixin.mall.controller.weixin;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.RoletoJson;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import xjnu.edu.weixin.mall.entity.mall.TbSchoolEntity;
import xjnu.edu.weixin.mall.entity.mall.TbTelCatalogEntity;
import xjnu.edu.weixin.mall.entity.mall.TbTelClassEntity;
import xjnu.edu.weixin.mall.entity.mall.TbTelItemEntity;
import xjnu.edu.weixin.mall.entity.mall.WeixinUserinfoEntity;
import xjnu.edu.weixin.mall.json.mall.TbTelClassEntityJson;
import xjnu.edu.weixin.mall.service.mall.TbTelItemServiceI;

/**   
 * @Title: Controller
 * @Description: 号码详情
 * @author zhangdaihao
 * @date 2014-08-25 18:11:19
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/telItemListController")
public class TelItemListController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TelItemListController.class);


	@Autowired
	private SystemService systemService;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	private List<TbTelItemEntity> tbTelItemEntity;
	@RequestMapping(params = "display")
	public String display(HttpServletRequest request) {

		AjaxJson j = new AjaxJson();
		String openuserid =request.getParameter("open_user_id");
		String catalogId =request.getParameter("catalogId");
		String schoolId =request.getParameter("schoolId");
		String classId =request.getParameter("classId");
		if(catalogId!=null){
		 DetachedCriteria dc = DetachedCriteria.forClass(TbTelItemEntity.class);
		 dc.add(Restrictions.eq("catalogId", catalogId));     
		 dc.add(Restrictions.eq("schoolId", schoolId));
		 dc.setResultTransformer(DetachedCriteria.DISTINCT_ROOT_ENTITY);
		 tbTelItemEntity =systemService.findByDetached(dc);
		 TbTelCatalogEntity tbTelCatalogt=systemService.findUniqueByProperty(TbTelCatalogEntity.class, "id", catalogId);
		 request.setAttribute("tbtitle", tbTelCatalogt.getTelCatalogName());
		}else{
		 DetachedCriteria dc = DetachedCriteria.forClass(TbTelItemEntity.class);
		 dc.createCriteria("tbTelCatalogEntity", "catalog");
		 dc.createCriteria("catalog.tbTelClassEntity", "class");
		 dc.add(Restrictions.eq("class.id", classId)); 
		 dc.add(Restrictions.eq("schoolId", schoolId));
		 dc.setResultTransformer(DetachedCriteria.DISTINCT_ROOT_ENTITY);
		 tbTelItemEntity =systemService.findByDetached(dc);
		 TbTelClassEntity tbTelClass=systemService.findUniqueByProperty(TbTelClassEntity.class, "id", classId);
		 request.setAttribute("tbtitle", tbTelClass.getTelClassName());
		}
		 request.setAttribute("tbTelItemEntity", tbTelItemEntity);
//		 System.out.println("--------------------------"+openuserid);
		 request.setAttribute("open_user_id", openuserid);
		return "xjnu/edu/weixin/mall_user/tel_item_list";
	}
	
}
