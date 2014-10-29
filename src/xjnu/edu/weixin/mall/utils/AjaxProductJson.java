package xjnu.edu.weixin.mall.utils;

import org.jeecgframework.core.common.model.json.AjaxJson;

public class AjaxProductJson extends AjaxJson {
	private int totalPage;

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

}
