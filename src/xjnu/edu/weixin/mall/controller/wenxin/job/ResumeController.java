package xjnu.edu.weixin.mall.controller.wenxin.job;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.web.system.service.SystemService;
import xjnu.edu.weixin.mall.entity.job.TbResumeEntity;
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
@RequestMapping("/resumeController")
public class ResumeController extends BaseController {
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
	 *个人简历列表 页面跳转
	 * 
	 * @return
	 */
	
	@RequestMapping(params = "resume")
	public ModelAndView resume(HttpServletRequest request) {
		List<TbSchoolEntity> tbSchoolEntityList = systemService.loadAll(TbSchoolEntity.class);
        JsArrayCreator jac1 = new JsArrayCreator();
        JsArrayItemModal parrentItemModal1 = new JsArrayItemModal( "id" , "schoolName" ,"" ,  "id" );
        String school = jac1.getJsArray("schoolId", parrentItemModal1, tbSchoolEntityList) ;
        request.setAttribute("school", school);
		
		return new ModelAndView("xjnu/edu/weixin/mall_user/job/resume");
	}
	@RequestMapping(params = "saveResume")
	public ModelAndView saveResume(TbResumeEntity tbResumeEntity,HttpServletRequest request) {
      systemService.save(tbResumeEntity);
      message = "学生信息添加成功";
		return new ModelAndView("xjnu/edu/weixin/mall_user/job/resume");
	}
	
	
	
}
