<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/context/mytags.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<META content="IE=11.0000" http-equiv="X-UA-Compatible">

<META charset="utf-8">
<TITLE>找兼职</TITLE>
<META name="viewport"
	content="width=device-width, initial-scale=1.0,maximum-scale=1.0, minimum-scale=1.0,user-scalable=no">
<META name="apple-mobile-web-app-capable" content="yes">
<META name="apple-mobile-web-app-status-bar-style" content="black">
<META name="format-detection" content="telephone=no">
<META name="format-detection" content="email=no">
<!-- <LINK href="/images/apple-touch-icon-iphone.png" rel="apple-touch-icon">
<LINK href="/images/apple-touch-icon-ipad.png" rel="apple-touch-icon"
	sizes="72x72">
<LINK href="/images/apple-touch-icon-iphone4.png" rel="apple-touch-icon"
	sizes="114x114"> -->
<LINK
	href="${webRoot}/plug-in/weixin_mall/job/jquery.mobile-1.4.2.min.css"
	rel="stylesheet" type="text/css">
<LINK href="${webRoot}/plug-in/weixin_mall/job/font.css"
	rel="stylesheet" type="text/css">
<LINK href="${webRoot}/plug-in/weixin_mall/job/wap.css" rel="stylesheet"
	type="text/css">

<SCRIPT src="${webRoot}/plug-in/weixin_mall/job/jquery-1.8.2-min.js"
	type="text/javascript"></SCRIPT>

<SCRIPT src="${webRoot}/plug-in/weixin_mall/job/WealinkMobile.js"
	type="text/javascript"></SCRIPT>

<SCRIPT src="${webRoot}/plug-in/weixin_mall/job/jquery.mobile-1.4.2.js"
	type="text/javascript"></SCRIPT>

<META name="GENERATOR" content="MSHTML 11.00.9600.17239">
</HEAD>
<BODY>
	<DIV id="index" data-role="page" data-wlm-listpage="zhiwei-list"
		data-dom-cache="false">
		<DIV data-role="header">

			<DIV class="nav">
				<DIV class="navBar">
					<!-- <A class="navQiuzhi current" href="http://m.wealink.com/zhiwei/"
						data-ajax="false">招聘</A> -->
				</DIV>
				<DIV class="barBoth bar1">
					<A class="zhiweiZhao current" href="${webRoot}/partTimeJobController.do?partTimeJob"
						data-ajax="false">找兼职</A><A class="boleZhao colgrey addUnderline"
						href="${webRoot}/jobController.do?job" data-ajax="false">找工作</A>
				</DIV>
			</DIV>
		</DIV>
		<DIV class="Qiuzhi" data-role="content">
			<DIV class="searchBar">
				<P>
					 <A href="${webRoot}/selectInfoController.do?selectPartTimeJob" data-ajax="false"> <SPAN
						class="searchInput"> <SPAN class="searchIcon"
							onclick="_gaq.push(['_trackEvent', 'zhiweilist', 'wap_search_inputbox']);">
								<IMG width="18" height="18"
								src="${webRoot}/plug-in/weixin_mall/job/images/search-icon.png">  </SPAN> <I
							class="colgrey">职位、公司名称关键字</I> </SPAN> <SPAN class="searchBtn"
						onclick="_gaq.push(['_trackEvent', 'zhiweilist', 'wap_filter']);">筛选</SPAN>
					</A>
				</P>
			</DIV>
			<DIV class="clearFix"></DIV>
			<DIV class="content zhiwei">


				<UL class="kind1"
				 id="zhiwei-list"
				 data-role="listview"
				 data-wlm-perpage="10"
				 data-wlm-total="${total}"
				 data-wlm-url="${webRoot}/partTimeJobController.do?tbJobitem"
				 data-wlm-offset="10"
				 data-icon="false">

					<c:forEach items="${tbbParttimeJobList}" var="tbj">
						<LI class="line"></LI>
						<LI class="item"><A
							href="${webRoot}/jobDetailController.do?partTimeJobDetail&partTimeJobId=${tbj.id}"
							data-transition="slide"> <IMG width="74" height="55"
								class="pic" src="${tbj.imagesUrl }"> 
								<UL>
									<LI>
										<H3>
											${tbj.name }<SPAN >${tbj.salary }</SPAN>
										</H3></LI>
									<LI>
										<H4>
											${tbj.getTbCompanyEntity().name }<SPAN class="data">需人数：${tbj.needConnt}</SPAN>
										</H4></LI>
									<LI>
										<H4>
											${tbj.getTbAreaCatalogEntity().getTbAreaClassEntity().className}-${tbj.getTbAreaCatalogEntity().catalogName}<SPAN
												class="data">${tbj.creatTime }</SPAN>
										</H4></LI>
								</UL> </A></LI>
					</c:forEach>

				</UL>

			</DIV>
		</DIV>
		<DIV data-role="footer">
			<DIV class="footer">
				<P>
					<A href="#" data-ajax="false">意见反馈</A>
				</P>
				<P class="colgrey2"></P>
			</DIV>
			<SCRIPT type="text/javascript">
				if (!window.getCookie) {
					function setCookie(cookieName, cookieValue, nDays) {
						var today = new Date;
						var expire = new Date;
						if (nDays) {
							expire.setTime(today.getTime() + 3600000 * 24
									* nDays);
						}
						document.cookie = cookieName
								+ '='
								+ escape(cookieValue)
								+ (nDays ? ('; expires=' + expire.toGMTString())
										: '') + '; path=/; domain=wealink.com';
					}

					function getCookie(name) {
						var nameEQ = name + '=';
						var ca = document.cookie.replace(/\s/g, '').split(';');
						for ( var i = 0; i < ca.length; i++) {
							var c = ca[i];
							if (c.indexOf(nameEQ) == 0)
								return unescape(c.substring(nameEQ.length,
										c.length).replace(/\+/g, ' '));
						}
						return null;
					}
				}
				$(function() {
					if (getCookie("_fmsg")) {
						try {
							var flash_msg = getCookie("_fmsg");
							setCookie("_fmsg", "", -1);
							var flash_msg_obj = eval("(" + flash_msg + ")");
							var jsonObj = eval(flash_msg_obj) || {};
							var error_code = jsonObj.error_code || 'warn';
							var error_msg = jsonObj.error_msg || "";
							var redirect_url = jsonObj.redirect_url || "";
							var delay_time = jsonObj.delay_time || 3
							if (jsonObj && window.jQuery && WealinkMobile.Popup) {
								jQuery(function() {
									WealinkMobile.Popup.alert(error_msg,
											error_code, delay_time);
								});
							}
						} catch (e) {
						}
					}
				});
			</SCRIPT>
		</DIV>
	</DIV>
</BODY>
</HTML>
