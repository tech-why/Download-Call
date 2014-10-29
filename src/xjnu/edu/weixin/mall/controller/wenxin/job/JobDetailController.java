package xjnu.edu.weixin.mall.controller.wenxin.job;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.web.system.service.SystemService;

import xjnu.edu.weixin.mall.entity.job.TbJobClassEntity;
import xjnu.edu.weixin.mall.entity.job.TbJobEntity;
import xjnu.edu.weixin.mall.entity.job.TbParttimeJobEntity;
import xjnu.edu.weixin.mall.service.job.JobServiceI;

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
@RequestMapping("/jobDetailController")
public class JobDetailController extends BaseController {
	@Autowired
	private SystemService systemService;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	@Autowired
	private JobServiceI jobService;

	/**
	 * 工作表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "jobDetail")
	public ModelAndView tbJob(HttpServletRequest request) {
		String jobId=request.getParameter("jobId");
		TbJobEntity tbJobDetail = systemService.findUniqueByProperty(TbJobEntity.class, "id", jobId);
		request.setAttribute("tbJobDetail", tbJobDetail);
		return new ModelAndView("xjnu/edu/weixin/mall_user/job/job_detail");
	}

	@RequestMapping(params = "partTimeJobDetail")
	public ModelAndView partTimeJob(HttpServletRequest request) {
		String partTimeJobId=request.getParameter("partTimeJobId");
		TbParttimeJobEntity tbPartTimeJobDetail = systemService.findUniqueByProperty(TbParttimeJobEntity.class, "id", partTimeJobId);
		request.setAttribute("tbPartTimeJobDetail", tbPartTimeJobDetail);
		return new ModelAndView("xjnu/edu/weixin/mall_user/job/partTimeJob_detail");
	}
}
