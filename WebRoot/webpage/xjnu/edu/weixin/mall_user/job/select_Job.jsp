<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/context/mytags.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html class="ui-mobile">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--<base href="http://m.wealink.com/zhiwei/filter/">-->
<base href=".">
<meta charset="utf-8">
<title>筛选</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0,maximum-scale=1.0, minimum-scale=1.0,user-scalable=no">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta content="telephone=no" name="format-detection">
<meta content="email=no" name="format-detection">

<script type="text/javascript">
var dictJson = {
   ${location},${company}
};
</script>

<!-- <link rel="apple-touch-icon"
	href="http://m.wealink.com/images/apple-touch-icon-iphone.png">
<link rel="apple-touch-icon" sizes="72x72"
	href="http://m.wealink.com/images/apple-touch-icon-ipad.png">
<link rel="apple-touch-icon" sizes="114x114"
	href="http://m.wealink.com/images/apple-touch-icon-iphone4.png"> -->
<link
	href="${webRoot}/plug-in/weixin_mall/job/jquery.mobile-1.4.2.min.css"
	rel="stylesheet" type="text/css">
<link href="${webRoot}/plug-in/weixin_mall/job/font.css"
	rel="stylesheet" type="text/css">
<link href="${webRoot}/plug-in/weixin_mall/job/wap.css" rel="stylesheet"
	type="text/css">
<script type="text/javascript" async=""
	src="${webRoot}/plug-in/weixin_mall/job/ga.js">
	
</script>
<script type="text/javascript"
	src="${webRoot}/plug-in/weixin_mall/job/jquery-1.8.2-min.js"></script>
<script type="text/javascript"
	src="${webRoot}/plug-in/weixin_mall/job/WealinkMobile.js"></script>
<script type="text/javascript"
	src="${webRoot}/plug-in/weixin_mall/job/jquery.mobile-1.4.2.js"></script>
</head>
<body class="ui-mobile-viewport ui-overlay-a">


	<div class="ui-loader ui-corner-all ui-body-a ui-loader-default">
		<span class="ui-icon-loading"></span>
		<h1>loading</h1>
	</div>
	<div data-role="page" id="search" data-dom-cache="false"data-url="selectInfo" data-external-page="true" tabindex="0"
		class="ui-page ui-page-theme-a ui-page-active"
		style="min-height: 953px;">
		<div data-role="header" data-ajax="false" role="banner"
			class="ui-header ui-bar-inherit">
			<!-- <div class="top">
				<a class="logo ui-link" href="http://m.wealink.com/"> <img
					src="./筛选·手机若邻网_files/wap-LOGO-126-40.png" width="63" height="20">
				</a> <span class="info"><a data-ajax="false"
					href="http://m.wealink.com/denglu/" class="ui-link">登录</a> <span
					class="colhui pd">|</span> <a data-ajax="false"
					href="http://m.wealink.com/zhuce/geren/" class="ui-link">注册</a> <span>
				</span>
				</span> 
			</div>-->

			<div class="nav">
				<div class="navBar">
					<!-- <a data-ajax="false" href="${webRoot}/partTimeJobController.do?partTimeJob"
						class="navQiuzhi current ui-link">招聘</a><a data-ajax="false"
						href="http://m.wealink.com/renmai/" class="navShejiao ui-link">人脉</a><a
						data-ajax="false" href="http://m.wealink.com/dianping/"
						class="navZixun ui-link">发现</a><a data-ajax="false"
						href="http://m.wealink.com/wode/" class="navWode ui-link">我的</a> -->
				</div>
			</div>
			<div class="wtitleNew">
				<a href="#" data-rel="back"
					class="back f22 ui-link" data-direction="reverse"><img
					src="${webRoot}/plug-in/weixin_mall/job/images/new_back.png" width="5" height="10"
					data-rel="back">&nbsp;返回</a> <span class="alginC">搜索</span>
			</div>
		</div>
		<div data-role="content" class="ui-content" role="main">
			<div class="searchContent">
				<form id="formSearch" method="post"
					action="${webRoot}/jobController.do?selectJob" data-ajax="false">
					<input name="fromFilter" value="1" type="hidden">
					<div class="search">
						<input data-role="none" name="kw" value="" type="text"
							placeholder="职位、公司名称关键字" class="inputContent bggrey"><span
							class="big"><img src="${webRoot}/plug-in/weixin_mall/job/images/search-icon.png"
							width="17" height="17">
						</span>
					</div>
					<ul class="choose f24">
					<li class="colb" data-wlm-filter-field="tbCompanyEntity_id" data-wlm-filter-type="single">选择公司
							<input type="hidden" name="tbCompanyEntity.id" id="tbCompanyEntity_id" value="0">
							<a href="javascript:void(0);" id="label-tbCompanyEntity_id" class="ui-link">不限</a>
						</li>
						
						<li class="colb" data-wlm-filter-field="tSTerritory_id" data-filter-parent-field = "tSTerritory_TSTerritory_id" >选择城市
						<input type="hidden" name="tSTerritory.id" id = "tSTerritory_id" value="">
						 <input type="hidden" name="tSTerritory.TSTerritory.id" id = "tSTerritory_TSTerritory_id" value="">
						<a href="javascript:void(0);" id="label-tSTerritory_id" class="ui-link">不限</a>
						</li>
						
						<!-- <li class="colb" data-wlm-filter-field="industry">选择城市<input
							type="hidden" name="industry" value="0"><a
							href="javascript:void(0);" id="label-industry" class="ui-link">不限</a>
						</li> -->
						<!-- <li class="colb" data-wlm-filter-field="category">选择职能<input
							type="hidden" name="category" value="0"><a
							href="javascript:void(0);" id="label-category" class="ui-link">不限</a>
						</li>
						<li class="colb" data-wlm-filter-field="month_salary"
							data-wlm-filter-type="single">选择月薪范围<input type="hidden"
							name="month_salary" value="0"><a
							href="javascript:void(0);" id="label-month_salary"
							class="ui-link">不限</a>
						</li>
					</ul>
					<ul class="choose f24" id="fields-more" style="display:none">
						<li class="colb" data-wlm-filter-field="experience"
							data-wlm-filter-type="single">选择经验要求<input type="hidden"
							name="experience" value="0"><a id="label-experience"
							href="javascript:void(0);" class="ui-link">不限</a>
						</li>
						<li class="colb" data-wlm-filter-field="company_type"
							data-wlm-filter-type="single">选择公司性质<input type="hidden"
							name="company_type" value="0"><a
							href="javascript:void(0);" id="label-company_type"
							class="ui-link">不限</a>
						</li>
						<li class="colb" data-wlm-filter-field="identity"
							data-wlm-filter-type="single">选择发布来源<input type="hidden"
							name="identity" value="0"> <a href="javascript:void(0);"
							id="label-identity" class="ui-link">不限</a>
						</li> -->
					</ul>
					<p class="showMore" id="moreFilterToggle">
						<span>更多筛选</span>
					</p>
					<div class="btn ">
						<input type="submit" class="greenbtn" value="立即查找"
							data-role="none">
					</div>
				</form>
				<div class="footer">
					<div class="alphaBg" id="wlm-filter-mask"></div>
					<div class="filter" id="wlm-filter"></div>
				</div>
			</div>
			<div class="searchHistroy">
				<p class="bar f26">
					历史搜索记录<a href="javascript:void(0);" id="clean-search-history"
						data-wlm-cookie="wlm_zw" class="ui-link"><span
						class="colg2 f26">清空</span>
					</a>
				</p>
				<div></div>
			</div>
		</div>
	</div>
</body>
</html>