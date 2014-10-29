package xjnu.edu.weixin.mall.controller.weixin;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.web.system.service.SystemService;
import xjnu.edu.weixin.mall.entity.mall.TbMallProductCatalogEntity;

/**   
 * @Title: Controller
 * @Description:微信前端   小类管理
 * @date 2014-08-05 12:48:40
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/productCatalogController")
public class ProductCatalogController extends BaseController {
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
	 * 根据选择的大类返回所有小类  (微信前端)
	 */

	@RequestMapping(params = "select")
	public String select(HttpServletRequest request) {
		String classId=request.getParameter("classId");
		String shopId=request.getParameter("shopId");
		String openuserid =request.getParameter("open_user_id");
//		System.out.println("----------openuserid-------------"+openuserid);
		DetachedCriteria dc = DetachedCriteria.forClass(TbMallProductCatalogEntity.class);
		dc.add(Restrictions.eq("tbMallProductClassEntity.id", classId));
		dc = dc.createCriteria("tbMallProductEntitys", "product");   
		dc.add(Restrictions.eq("product.shopId", shopId));
		
		dc.setResultTransformer(DetachedCriteria.DISTINCT_ROOT_ENTITY);
		List<TbMallProductCatalogEntity> productCatalog = systemService.findByDetached(dc);
		List<Integer> productCounts = new ArrayList<Integer>(); 
		for(TbMallProductCatalogEntity pc : productCatalog){
			productCounts.add(systemService.findHql("from TbMallProductEntity where shopId=? and catalogId=?", new Object[]{shopId,pc.getId()}).size());
		}
		request.setAttribute("productCatalogList", productCatalog);
		request.setAttribute("productCounts", productCounts);
		request.setAttribute("open_user_id", openuserid);
		request.setAttribute("shopId", shopId);
		return "xjnu/edu/weixin/mall_user/catalog_select";
	}

}
