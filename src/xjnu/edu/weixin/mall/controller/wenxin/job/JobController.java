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
import org.springframework.web.servlet.ModelAndView;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.web.system.service.SystemService;

import xjnu.edu.weixin.mall.entity.job.TbJobCatalogEntity;
import xjnu.edu.weixin.mall.entity.job.TbJobEntity;
import xjnu.edu.weixin.mall.entity.job.TbParttimeJobEntity;
import xjnu.edu.weixin.mall.entity.mall.TbMallProductCatalogEntity;
import xjnu.edu.weixin.mall.entity.mall.TbMallProductEntity;
import xjnu.edu.weixin.mall.service.job.JobServiceI;
import xjnu.edu.weixin.mall.service.job.ParttimeJobServiceI;
import xjnu.edu.weixin.mall.service.mall.ProductServiceI;

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
@RequestMapping("/jobController")
public class JobController extends BaseController {
	private static int itemInfoPage=10;
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
	@RequestMapping(params = "job")
	public ModelAndView tbJob(HttpServletRequest request) {
		
		List<TbJobEntity> tbJobList=null;
		int total=jobService.getJobCount();
		request.setAttribute("total", total);
			int pageN=1;
			tbJobList=jobService.getJob(pageN);
		request.setAttribute("tbJobList", tbJobList);
		return new ModelAndView("xjnu/edu/weixin/mall_user/job/job_list");
	}
	
	
	@RequestMapping(params = "jobitem")
	public ModelAndView tbJobitem(HttpServletRequest request) {
		List<TbJobEntity> tbJobList=null;
		int total=jobService.getJobCount();
		request.setAttribute("total", total);
		String offset=request.getParameter("offset");
		if(offset==null){
			int pageN=1;
			tbJobList=jobService.getJob(pageN);
		}else{
			int offsetint=Integer.parseInt(offset);
			int pageN=offsetint/itemInfoPage;
			tbJobList=jobService.getJob(pageN);
		}
		request.setAttribute("tbJobList", tbJobList);
		return new ModelAndView("xjnu/edu/weixin/mall_user/job/Job_item");
	}
	
	
	@RequestMapping(params = "selectJob")
	public ModelAndView selectJob(TbJobEntity tbJob,HttpServletRequest request, HttpServletResponse response) {
		
		CriteriaQuery cq = new CriteriaQuery(TbJobEntity.class);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tbJob, request.getParameterMap());
		List<TbJobEntity> tbJobList = this.jobService.findByDetached(cq.getDetachedCriteria());
		request.setAttribute("tbJobList", tbJobList);
		return new ModelAndView("xjnu/edu/weixin/mall_user/job/job_list");
	}
	
}
