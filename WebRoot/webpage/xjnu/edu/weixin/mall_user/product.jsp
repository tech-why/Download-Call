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
    
	<!-- <link href="static/owl-carousel/owl.carousel.css" rel="stylesheet">
	<link href="static/owl-carousel/owl.theme.css" rel="stylesheet"> -->
	<style type="text/css">
	
	.menu {position:fixed; height:40px; bottom:25px; left:10px;}
	.menu .nav ul {display:none; margin:4px 0 0 0; padding: 10px 10px 6px 47px; float:left; background:#FFF; height:42px; border-radius:22px; box-shadow:0px 0px 8px rgba(0, 0, 0, 0.3);overflow:hidden;}
	.menu .nav li {border-left:1px solid #e7e7e7; padding:0 8px; float:left; line-height:22px; list-style:none;color:#34495e}
	.menu .nav li a:hover,a:visited{color:#34495e;text-decoration:none;}
	.menu .logo {width:50px; height:50px;border-radius:50%; border:2px solid #FFF; position:relative; background:url(static/images/07.jpg) no-repeat #41c281;position:absolute;box-shadow:0px 0px 8px rgba(0, 0, 0, 0.3); background-size:46px 46px;}
		#picture .item img{ display: block; width: 100%;}
		#picture .owl-controls{margin-top:0;}
		#picture .owl-pagination{overflow:hidden;}
		#picture .owl-page{display:block; float:left; width:25%;}
		#picture .owl-page span{margin:0; width:100%; height:4px; border-radius:0; -webkit-border-radius:0;}
		.product-info h4{margin-top:0; padding-bottom:10px; border-bottom:2px solid #29b572;}
		.product-info h4 span i{font-size:22px; line-height:18px; color:#29b572;}
		.product-info h4 span.pull-right{color:#29b572;}
	</style>
	<script type="text/javascript" src="${webRoot}/plug-in/static/script/baidu.js"></script> 
</head>
<body style="background:#ecf0f1; padding-top:70px; color:#34495e">
	<nav class="navbar navbar-default navbar-fixed-top hys-bg-nav" role="navigation" style="border:0 none; border-radius:0;">
		<div id="backHome" class="pull-left" style="position:relative; width:40px; height:50px; text-align:center;  z-index:2;">
			<span class="glyphicon hys-icon hys-icon-home" style="width:40px; height:50px; line-height:50px; color:#FFF; font-size:20px;"></span>
		</div>
		<div class="text-center" style="position:absolute; top:0; left:0; width:100%; height:50px; line-height:50px; font-size:16px; color:#FFF; z-index:1;"><strong>商品明细</strong></div>
		<div id = "showCart" class="pull-right" style="position:relative; width:40px; height:50px; text-align:center; z-index:2;">
				<div id="shoppingCart" style="display:none; position:absolute; top:5px; right:5px; padding:2px 5px; line-height:12px; background:red; border-radius:10px; font-size:12px; text-align:center; color:#FFF; border:2px solid #FFF; z-index:1;"><strong>0</strong></div>
				<span id="cartIcon" class="icon-uniE61E" style="width:40px; height:50px; line-height:50px; color:#FFF; font-size:24px;"></span>
			
		</div>
	</nav>
	<div class="container" style="padding-left:10px; padding-right:10px;">
		<div id="productDetail"></div>
		<!-- <div class="text-center"><img src="static/images/pay_footer.png" width="150" alt=""></div> -->
	</div>
	
	<%@ include  file="menu.jsp"%>
	
	
	<script src="${webRoot}/plug-in/static/jquery/jquery-2.0.3.min.js" type="text/javascript"></script>
	<script src="${webRoot}/plug-in/static/handlebars/handlebars-v1.3.0.js" type="text/javascript"></script>
	<script src="${webRoot}/plug-in/static/owl-carousel/owl.carousel.min.js" type="text/javascript"></script>
	<script src="${webRoot}/plug-in/static/script/hys-base.js" type="text/javascript"></script>
	<script src="${webRoot}/plug-in/static/script/hys-product.js" type="text/javascript"></script>
	<script type="text/javascript">
		$('#backHome').click(function(){
			window.location.href = "/jeewx/schoolController.do?display&open_user_id=${open_user_id }&app_code=shop";
		});
		$('#productDetail').productDetail({
		    /* url: 'productItemController.do?productItem',
			type: 'GET', */
			template: '${webRoot}/plug-in/static/template/product_detail.html',
			cart: true
		});
		
		$('#showCart').click(function(){
        	window.location.href = '${webRoot}/cartController.do?display&openUserId=${open_user_id}&shopId=${shopId}';
        });
		getMenu();

		
		function getMenu() {
			$('#menuBtn').click(function() {
				$('#menuContent').toggle();
			});
		};
		var cpsParam = $.productParam({'param': 'csp'}) || 0;
		var customer_data = {'snid':'0','f1':'2','f2':'0','f3':'0','f4':cpsParam,'f5':'0'};
	</script>
		<!-- <script type="text/javascript" src="http://img01.img.ehaoyao.com/images/trace.js"></script> -->
		
		
</body>
</html>