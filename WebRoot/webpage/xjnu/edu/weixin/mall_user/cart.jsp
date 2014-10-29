<%@ page language="java" import="java.util.*"  pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!DOCTYPE html>
<html>
<head>
<title>购物车</title>
	<meta charset="utf-8">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport" />
	<meta content="telephone=no" name="format-detection" />
	<meta content="email=no" name="format-detection" />

	<link href="${webRoot}/plug-in/weixin_mall/static/bootstrap/bootstrap.min.css" rel="stylesheet">
	<link href="${webRoot}/plug-in/weixin_mall/static/style/hys.css" rel="stylesheet">
    <link href="${webRoot}/plug-in/weixin_mall/static/style/wxsc.css" rel="stylesheet">
    <link href="${webRoot}/plug-in/weixin_mall/static/style/style.css" rel="stylesheet">
	<link href="${webRoot}/plug-in/weixin_mall/static/owl-carousel/owl.carousel.css" rel="stylesheet">
	<link href="${webRoot}/plug-in/weixin_mall/static/owl-carousel/owl.theme.css" rel="stylesheet">
	<link href="${webRoot}/plug-in/weixin_mall/static/style/cart.css" rel="stylesheet">
	
</head>
<body style="background:#ecf0f1; padding-top:10px; color:#34495e">

	<div class="container" style="padding-left:10px; padding-right:10px;">
		<p style="height:34px; line-height:34px; font-size:18px;">
			<span class="glyphicon hys-icon hys-icon-ok"></span>
			选<strong id="countAll" style="padding:0 2px; color:#f16c10;">0</strong>件商品，共计<strong id="priceAll" style="padding:0 2px; color:#f16c10;">0.00</strong>元
	  </p>
		<ul id="cartList" class="list-group"></ul>
		<div id="activities" class="alert alert-success" style="background:#FFF; border:1px solid #e6e5e5; color:#34495e"></div>
		<p><button type="button" class="btn btn-o2o btn-block js-checkOut" >去结算</button></p>

		<!-- <div id="pop" class="pop owl-carousel owl-theme"></div> -->
	</div>
	
	
	<script src="${webRoot}/plug-in/weixin_mall/static/jquery/jquery-2.0.3.min.js" type="text/javascript"></script>
	<script src="${webRoot}/plug-in/weixin_mall/static/handlebars/handlebars-v1.3.0.js" type="text/javascript"></script>
	<script src="${webRoot}/plug-in/weixin_mall/static/owl-carousel/owl.carousel.min.js" type="text/javascript"></script>
	<script src="${webRoot}/plug-in/weixin_mall/static/script/hys-base.js" type="text/javascript"></script>
	<script src="${webRoot}/plug-in/weixin_mall/static/script/hys-product.js" type="text/javascript"></script>
	<script type="text/javascript">
		$('#cartList').cart({
			type: 'initCart'
		});

		$('.js-checkOut').cart({
			type: 'checkOut'
		});
		
	</script>
	
</body>
</html>