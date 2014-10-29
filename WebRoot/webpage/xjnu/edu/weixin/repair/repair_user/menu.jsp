 
 <%@ page contentType="text/html;charset=utf-8" %>
 <style>
 	.menu {position:fixed; height:40px; bottom:25px; left:10px;}
	.menu .nav ul {display:none; margin:4px 0 0 0; padding: 10px 10px 6px 47px; float:left; background:#FFF; height:42px; border-radius:22px; box-shadow:0px 0px 8px rgba(0, 0, 0, 0.3);overflow:hidden;}
	.menu .nav li {border-left:1px solid #e7e7e7; padding:0 8px; float:left; line-height:22px; list-style:none;color:#34495e}
	.menu .nav li a:hover,a:visited{color:#34495e;text-decoration:none;}
	.menu .logo {width:50px; height:50px;border-radius:50%; border:2px solid #FFF; position:relative; background:url(${webRoot}/plug-in/static/images/07.jpg) no-repeat #41c281;position:absolute;box-shadow:0px 0px 8px rgba(0, 0, 0, 0.3); background-size:46px 46px;}
</style>
 <div class="menu">
		<div id="menuBtn" class="logo"></div>
		<div class="nav">
			<ul id="menuContent">
				<li><a href="/jeewx/schoolController.do?display&open_user_id=${open_user_id }&app_code=shop"
					style="color:#34495e;text-decoration:none;">首页</a></li>
				<li><a
					href="/jeewx/orderController.do?list&open_user_id=${open_user_id }"
					style="color:#34495e;text-decoration:none;">我的订单</a></li>
				<li><a
					href="/jeewx/accountController.do?display&open_user_id=${open_user_id }"
					style="color:#34495e;text-decoration:none;">个人中心</a></li>
				<li><a
					href="/jeewx/cartController.do?display&openUserId=${open_user_id }&shopId=${shopId }"
					style="color:#34495e;text-decoration:none;">购物车</a></li>
			</ul>
		</div>
	</div>
	