<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> 
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="format-detection" content="telephone=no">
    <meta name="format-detection" content="email=no">
    <meta http-equiv="pragma" content="no-cache" />
	<title></title>
	<link rel="stylesheet" href="${webRoot}/plug-in/static/style/base.css?1407170834">
    <link rel="stylesheet" href="${webRoot}/plug-in/static/style/bootstrap.css">
    <link rel="stylesheet" href="${webRoot}/plug-in/static/style/font-awesome.min.css">
    <script src="${webRoot}/plug-in/static/script/jquery.min.js"></script>
    <script src="${webRoot}/plug-in/static/script/bootstrap.min.js"></script>
    <script src="${webRoot}/plug-in/static/script/common.js?1407170834"></script>
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
        	<div class="col-xs-8">欢迎您对本店提出意见!</div>
            <a href="#" class="col-xs-4 text-center">意见投诉</a>
                    </div>
    </div>
    <div class="tip"></div>
    <div class="mtop container">
         <div class="input-group">
            <form method="post" action="/index.php?s=/wechat/index/search.htm" onSubmit="return scannerInput();" class="formicon">
                <input type="search" class="form-control" placeholder="请输入商品关键字" name="title" id="keytitle">
                <i class="fa fa-times-circle" onClick="$('#keytitle').val('');$(this).hide()"></i>
            </form>
        </div> 
        <ul class="classlist">
            <c:forEach items="${tbMallProductClass}" var="tb"> 
        	<li id="li78">
                    <a href="${webRoot}/productCatalogController.do?select&classId=${tb.id }&shopId=${shopId}&open_user_id=${openuserid}"><i class="${tb.tbImageEntity.imageKey }"></i> <h5>${tb.className}</h5></a>
                </li>
                </c:forEach> 
                           <li class="shoprecord">
                <a href="#" class="panel-title">
                    <i class="fa fa-th-large"></i>
                    <h5>购买记录</h5>
                </a>
            </li>
        </ul>
            </div>
     <script>
		sessionStorage.shoplistpage = '';
    	function scannerInput(){
			var title = $('input[name="title"]').${tb.classImageUrl};
			if(!title){
				error('请输入商品关键字');
				return false;
			}
		}
    </script> 
</body>
</html>