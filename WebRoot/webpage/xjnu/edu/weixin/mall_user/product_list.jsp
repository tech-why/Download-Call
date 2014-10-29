<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta charset="utf-8">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport" />
	<meta content="telephone=no" name="format-detection" />
	<meta content="email=no" name="format-detection" />
	<title></title>

	<link href="${webRoot}/plug-in/static/bootstrap/bootstrap.min.css" rel="stylesheet">
	<link href="${webRoot}/plug-in/static/style/hys.css" rel="stylesheet">
    <link href="${webRoot}/plug-in/static/style/style.css" rel="stylesheet">
	<link href="${webRoot}/plug-in/static/owl-carousel/owl.carousel.css" rel="stylesheet">
	<link href="${webRoot}/plug-in/static/owl-carousel/owl.theme.css" rel="stylesheet">
	<style type="text/css">
		#listChange {color:#FFF;}
		.pop a {display:block; text-align:center;}
		.item {
			position:relative;
			margin-right:10px;
			border: 1px solid transparent;
			border-radius: 4px;
			-webkit-box-shadow: 0 1px 1px rgba(0, 0, 0, 0.05);
			box-shadow: 0 1px 1px rgba(0, 0, 0, 0.05);
		}
		.item .item-info {position:absolute; left:0; bottom:0; margin:0; width:100%; height:22px; line-height:22px;}
		.item .item-info .mask {position:absolute; top:0; left:0; width:100%; height:100%; background:#000; opacity:.5; z-index:1;}
		.item .item-info .text {position:relative; color:#FFF; font-size:12px; z-index:2;}
		.list {margin-left:-5px; margin-right:-5px; overflow:hidden;}
		.list-items {
			float:left;
			margin-bottom:10px;
			padding:0 5px;
			width:100%;
			box-sizing:border-box;
			-moz-box-sizing:border-box;
			-webkit-box-sizing:border-box;
			overflow:hidden;
		}
		.list-items .list-items-pic {text-align:center;}
		.list-items .list-items-content {padding:10px 5px 5px 5px; background:#FFF; overflow:hidden;}
		.list-items .list-items-content h5 { position:relative; margin:0 0 5px 0; padding:0; height:32px; font-size:16px;}
		.waterfall .list-items {width:50%}
		.waterfall .hys-btn-cart{padding:0; border:0 none;}
		.waterfall .hys-btn-cart span{display:none;}
		.table .list-items {background:#FFF;}
		.table .list-items .list-items-pic {float:left; width:30%;}
		.table .list-items .list-items-content {float:left; width:70%; padding:10px;}
		.table .list-items .list-items-content h5 {display:block;}
		.table .hys-btn-cart span em{display:none;}
		.owl-theme .owl-controls {margin:0;}
		.owl-theme .owl-controls .owl-page span{background:#FFF; border:1px solid #197fef;}
		.owl-theme .owl-controls .owl-page.active span {background:#197fef;}
		.category-items{
			display:none;
			position:absolute;
			left:0;
			top:50px;
			width:100%;
			padding:5px 5px 0 5px;
			background:#ecf0f1;
			border-top:0px solid #676767;
			border-bottom:0px solid #676767;
			box-sizing:border-box;
			-moz-box-sizing:border-box;
			-webkit-box-sizing:border-box;box-shadow: 3px 3px 5px rgba(0, 0, 0, 0.07);
		}
		.category-items .category-items-box{margin-left:-5px; margin-right:-5px; overflow:hidden;}
		.category-items .category-items-box p{
			float:left;
			margin:0 0 5px 0;
			padding:0 5px;
			width:33.33333333333%;
			box-sizing:border-box;
			-moz-box-sizing:border-box;
			-webkit-box-sizing:border-box;border-raduis:4px;
		}
		.category-items .category-items-box p a{display:block; padding:5px 0; background:#fff; text-align:center; border:1px solid #e7e6e6; color:#29b572; border-raduis:4px;}
		.pop-title{margin-bottom:5px; background:#FFF; border-top:3px solid #197fef;}
		.pop-title p{padding:0 10px; margin:0; height:30px; line-height:30px; font-size:12px; color:#c50226;}
		.pop-title span{margin-right:10px; font-size:20px; color:#197fef;}
		.pop-title strong{margin-right:10px; font-size:18px; color:#197fef;}
		
		
		
		/* .menu {position:fixed; height:40px; bottom:25px; left:10px;}
	.menu .nav ul {display:none; margin:4px 0 0 0; padding: 10px 10px 6px 47px; float:left; background:#FFF; height:42px; border-radius:22px; box-shadow:0px 0px 8px rgba(0, 0, 0, 0.3);overflow:hidden;}
	.menu .nav li {border-left:1px solid #e7e7e7; padding:0 8px; float:left; line-height:22px; list-style:none;color:#34495e}
	.menu .nav li a:hover,a:visited{color:#34495e;text-decoration:none;}
	.menu .logo {width:50px; height:50px;border-radius:50%; border:2px solid #FFF; position:relative; background:url(static/images/07.jpg) no-repeat #41c281;position:absolute;box-shadow:0px 0px 8px rgba(0, 0, 0, 0.3); background-size:46px 46px;}
	 */
	 </style>
	<script type="text/javascript" src="${webRoot}/plug-in/static/script/baidu.js"></script> 
</head>
<body style="background:#ecf0f1; padding-top:70px; color:#34495e">
	<nav class="navbar navbar-default navbar-fixed-top hys-bg-nav" role="navigation" style="border:0 none; border-radius:0; border-bottom:0px solid #FFF;">
		<!-- <div class="text-center" style="position:absolute; top:0; left:0; width:100%; height:50px; background:url(static/images/logo.png) center no-repeat; z-index:1;"></div> -->
		<!-- <div id="pageBack" class="pull-left" style="position:relative; width:40px; height:50px; text-align:center; z-index:2;">
			<span class="glyphicon hys-icon hys-icon-arrow-left" style="width:40px; height:50px; line-height:50px; color:#FFF; font-size:20px;"></span>
		</div> -->
		<div class="pull-left" id="listChange" status="1" style="position:relative; width:50px; height:50px; text-align:center; z-index:2;">
			<span class="glyphicon hys-icon hys-icon-double" style="width:40px; height:50px; line-height:50px; font-size:20px;"></span>
		</div>
		<div class="pull-left" style="margin-top:10px; width:55%;">
			<div class="input-group input-group-sm">
		        <input id="searchText" type="text" class="form-control" placeholder="搜索..." style="border-radius:0; border:0px solid #fff; ">
		        <span class="input-group-btn">
		        	<button id="searchBtn" class="btn btn-default" type="button"style="border-radius:0;border-top:0px; border-bottom:0px; border-right:0px; background:#f4f4f4"><span class="icon-uniE617" style="font-size:16px;"></span></button>
		        </span>
		    </div>
		</div>
		<div id="category" class="pull-right" style="position:relative; width:40px; height:50px; text-align:center; z-index:2;">
			<span class="icon-uniE633" style="width:40px; height:50px; line-height:50px; color:#FFF; font-size:20px;"></span>
		</div>
		<div id = "showCart" class="pull-right" style="position:relative; width:40px; height:50px; text-align:center; z-index:2;">
				<div id="shoppingCart" style="display:none; position:absolute; top:5px; right:5px; padding:2px 5px; line-height:12px; background:red; border-radius:10px; font-size:12px; text-align:center; color:#FFF; border:2px solid #FFF; z-index:1;"><strong>0</strong></div>
				<span id="cartIcon" class="icon-uniE61E" style="width:40px; height:50px; line-height:50px; color:#FFF; font-size:24px;"></span>
			
		</div>
		<div class="category-items">
			<div id="categoryContent" class="category-items-box">
				<!-- <p><a href="/haoyao/index_menu/shopping.html?method=xianshi&state=xianshi">限时购物</a></p> -->
				<!-- <p><a href="/haoyao/index_menu/shopping.html?method=xianshi&state=jingpin">精品购物</a></p>
				<p><a href="/haoyao/index_menu/shopping.html?method=xianshi&state=qixie">健康器械</a></p>
				<p><a href="/haoyao/index_menu/shopping.html?method=xianshi&state=baojian">养生保健</a></p>
				<p><a href="/haoyao/index_menu/shopping.html?method=xianshi&state=drug">中药西药</a></p>
				<p><a href="/haoyao/index_menu/shopping.html?method=xianshi&state=food">美食美客</a></p>
				<p><a href="/haoyao/index_menu/shopping.html?method=xianshi&state=meizhuang">妆点部落</a></p> -->
			</div>
		</div>
	</nav>
	<div class="container" style="padding-left:10px; padding-right:10px;">
		<div id="product" class="list waterfall"></div>
		<button id="pageBtn" type="button" class="btn btn-default btn-block" style="display:none;">更多</button><br />
	</div>
	
	<%@ include  file="menu.jsp"%>
	
	
	<script src="${webRoot}/plug-in/static/jquery/jquery-2.0.3.min.js" type="text/javascript"></script>
	<script src="${webRoot}/plug-in/static/handlebars/handlebars-v1.3.0.js" type="text/javascript"></script>
	<script src="${webRoot}/plug-in/static/owl-carousel/owl.carousel.min.js" type="text/javascript"></script>
	<script src="${webRoot}/plug-in/static/script/hys-base.js" type="text/javascript"></script>
	<script src="${webRoot}/plug-in/static/script/hys-product.js" type="text/javascript"></script>
	<script type="text/javascript">
		/* $.productInit(); */
		
		$('#category').click(function(){
                    $('.category-items').slideToggle();
                });
                
                $('#showCart').click(function(){
                	window.location.href = '${webRoot}/cartController.do?display&openUserId=${open_user_id}&shopId=${shopId}';
                });

		$('#listChange').listChange();

		$( '#product' ).pagination({
			url: 'productController.do?getdata',
			type: 'GET',
			template: '${webRoot}/plug-in/static/template/product_list.html',
			cart: true,
			userOpenid:'${open_user_id}',
			shopId:'${shopId}'
		});

		$('#searchBtn').searchProduct();

		getMenu();
		
		function getMenu() {
			$('#menuBtn').click(function() {
				$('#menuContent').toggle();
			});
		};
		
		
	</script>

</body>
</html>