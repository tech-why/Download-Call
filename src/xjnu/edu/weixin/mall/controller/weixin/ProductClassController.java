package xjnu.edu.weixin.mall.controller.weixin;
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
import xjnu.edu.weixin.mall.entity.mall.TbMallProductClassEntity;

/**   
 * @Title: Controller
 * @Description: 微信前端  大类管理
 * @date 2014-08-05 12:48:09
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/productClassController")
public class ProductClassController extends BaseController {
	
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
	 * 根据商铺Id返回所有大类信息(微信前端)
	 */

	@RequestMapping(params = "select")
	public String select(TbMallProductClassEntity TbMallProductClass,HttpServletRequest request) {
		String shopId=request.getParameter("shopId");
		DetachedCriteria dc = DetachedCriteria.forClass(TbMallProductClassEntity.class); 
		dc = dc.createCriteria("tbMallProductCatalogEntitys", "catalog");   
		dc.createCriteria("catalog.tbMallProductEntitys", "product");   
		dc.add(Restrictions.eq("product.shopId", shopId));
		dc.setResultTransformer(DetachedCriteria.DISTINCT_ROOT_ENTITY);
		List<TbMallProductClassEntity> classList =  systemService.findByDetached(dc);
		request.setAttribute("tbMallProductClass", classList);
		request.setAttribute("shopId", shopId);
		String openuserid =request.getParameter("open_user_id");
		request.setAttribute("openuserid", openuserid);
		/*for( TbMallProductClassEntity productClass : classList)
		{
			System.out.println("-----------------drygrth---------------->"+productClass.getClassName());
			
		}*/
		return "xjnu/edu/weixin/mall_user/class_select";
	}
}
