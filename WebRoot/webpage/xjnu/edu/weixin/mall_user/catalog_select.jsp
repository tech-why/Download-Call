<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
	<link rel="stylesheet"  href="${webRoot}/plug-in/static/style/base.css?1407172124">
    <link rel="stylesheet" href="${webRoot}/plug-in/static/style/bootstrap.css">
    <link rel="stylesheet" href="${webRoot}/plug-in/static/style/font-awesome.min.css">
    <script src="${webRoot}/plug-in/static/script/jquery.min.js"></script>
    <script src="${webRoot}/plug-in/static/script/bootstrap.min.js"></script>
    <script src="${webRoot}/plug-in/static/script/common.js?1407172124"></script>
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
    <style type="text/css">
	.menu {position:fixed; height:40px; bottom:25px; left:10px;}
	.menu .nav ul {display:none; margin:4px 0 0 0; padding: 10px 10px 6px 47px; float:left; background:#FFF; height:42px; border-radius:22px; box-shadow:0px 0px 8px rgba(0, 0, 0, 0.3);overflow:hidden;}
	.menu .nav li {border-left:1px solid #e7e7e7; padding:0 8px; float:left; line-height:22px; list-style:none;color:#34495e}
	.menu .nav li a:hover,a:visited{color:#34495e;text-decoration:none;}
	.menu .logo {width:50px; height:50px;border-radius:50%; border:2px solid #FFF; position:relative; background:url(static/images/07.jpg) no-repeat #41c281;position:absolute;box-shadow:0px 0px 8px rgba(0, 0, 0, 0.3); background-size:46px 46px;}
</style>
</head>
<body class="bg">
    <!-- <div class="cartfooter navbar-fixed-bottom">
        <div class="panel-body panel-default">
        	            <div class="col-xs-8">购物车商品总价 ¥ <span id="total">0.00</span></div>
            <div class="col-xs-4 text-center btn-carts">购物车</div>        </div> -->
    </div>
    <div class="tip"></div>
    <div class="mtop container">
        <div class="input-group">
            <form method="post" action="/index.php?s=/wechat/index/search.htm" onSubmit="return scannerInput();" class="formicon">
                <input type="search" class="form-control" placeholder="请输入商品关键字" name="title" id="keytitle">
                <i class="fa fa-times-circle" onClick="$('#keytitle').val('');$(this).hide()"></i>
            </form>
        </div>
         <c:forEach items="${productCatalogList}" var="pc" varStatus="status">
           <ul class="list-group clublist">
        	   <li class="list-group-item">
                    <a href="${webRoot}/productController.do?display&catalogId=${pc.id }&shopId=${shopId}&open_user_id=${open_user_id}" class="panel-title">
                    	${pc.catalogName }<i class="fa fa-angle-right"></i>
                        <em>${productCounts[status.index]}</em>
                    </a>
                </li>
            </ul>
         </c:forEach>
         </div>
       <%@ include  file="menu.jsp"%>
    <script>
		sessionStorage.shoplistpage = '';
    	function scannerInput(){
			var title = $('input[name="title"]').val();
			if(!title){
				error('请输入商品关键字');
				return false;
			}
		}
		
		getMenu();

		function getMenu() {
			$('#menuBtn').click(function() {
				$('#menuContent').toggle();
			});
		};
    </script>
</body>
</html>