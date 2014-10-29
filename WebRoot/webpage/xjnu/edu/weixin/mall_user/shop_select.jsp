<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@include file="/context/mytags.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="format-detection" content="telephone=no">
    <meta name="format-detection" content="email=no">
    <meta http-equiv="pragma" content="no-cache" />
	<title></title>
	<link rel="stylesheet" type="text/css" href="${webRoot}/plug-in/static/style/base.css?1407174187">
    <link rel="stylesheet" type="text/css" href="${webRoot}/plug-in/static/style/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${webRoot}/plug-in/static/style/font-awesome.min.css">
    <script type="text/javascript" src="${webRoot}/plug-in/static/script/jquery.min.js"></script>
    <script type="text/javascript" src="${webRoot}/plug-in/static/script/bootstrap.min.js"></script>
    <script type="text/javascript" src="${webRoot}/plug-in/static/script/common.js?1407174187"></script>
    <script>
    	function onBridgeReady(){
		 	WeixinJSBridge.call('hideOptionMenu');
		}
		
		if (typeof WeixinJSBridge == "undefined"){
			if( document.addEventListener ){
				document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
			}else if (document.attachEvent){
				document.attachEvent('WeixinJSBridgeReady', onBridgeReady); 
				document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
			}
		}else{
			onBridgeReady();
		}
    </script>
</head>
<body class="bg">
   
	<div class="cartfooter navbar-fixed-bottom">
        <div class="panel-body panel-default">
            <div class="col-xs-8">在此学校共有<span>${shopJson.size()}</span>家商铺</div>
              <div class="col-xs-4 text-center"><a href="${webRoot}/schoolController.do?select&app_code=shop&open_user_id=${openuserid}">更换学校</a></div>
        </div>
    </div>
  
   <div class="mtop container">
        <ul class="media-list businesslist">
     <c:if test="${shopJson.size()>=1}"> 
         <c:forEach items="${shopJson}" var="tb">
            <li class="media panel panel-body">
                   <div class="media-body">
                    <h5 class="media-heading">${tb.shopName}</h5>
                    <h5 class="text-muted">营业时间：07:30 - 02:00                    	
                    </h5>
                </div>
                <div class="row" style="border:0"></div>
                <div class="row">
                 <div class="col-xs-6"><a class="btn-block link text-center" href="tel:${tb.telephoneNumber} }"><i class="fa fa-phone-square"></i> 电话购物</a></div>
                   <div class="col-xs-6"><a class="btn-block link text-center bl" href="${webRoot}/productClassController.do?select&shopId=${tb.id }&open_user_id=${openuserid}"><i class="fa fa-cube"></i> 在线购物</a></div>
               </div>
            </li>
             </c:forEach>
            </c:if><c:if test="${shopJson.size()<1}">
               <h5 class="media-heading">此学校目前暂未开通服务!请联系工作人员此学校开通服务</h5>
             </c:if>  
		</ul> 
		 
	</div>
	
	
	
	
    <script>
    	function image(url){
			WeixinJSBridge.invoke('imagePreview',{ 'current':'http://'+window.location.host+url, 'urls':['http://'+window.location.host+url]} );   
		}
    </script>
</body>
</html>