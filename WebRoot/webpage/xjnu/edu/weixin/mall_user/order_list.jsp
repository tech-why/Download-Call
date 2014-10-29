<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的订单</title>
<meta charset="utf-8">
<meta
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;"
	name="viewport">
<meta content="telephone=no" name="format-detection">
<meta content="email=no" name="format-detection">

<link href="${webRoot }/plug-in/static/bootstrap/bootstrap.min.css"
	rel="stylesheet">
<link href="${webRoot }/plug-in/static/style/wxsc.css" rel="stylesheet">
<link href="${webRoot }/plug-in/static/style/style.css" rel="stylesheet">

<style type="text/css">
.menu {
	position: fixed;
	height: 40px;
	bottom: 25px;
	left: 10px;
}

.menu .nav ul {
	display: none;
	margin: 4px 0 0 0;
	padding: 10px 10px 6px 47px;
	float: left;
	background: #FFF;
	height: 42px;
	border-radius: 22px;
	box-shadow: 0px 0px 8px rgba(0, 0, 0, 0.03);
	overflow: hidden;
}

.menu .nav li {
	border-left: 1px solid #e7e7e7;
	padding: 0 8px;
	float: left;
	line-height: 22px;
	list-style: none;
	color: #34495e
}

.menu .nav li a:hover,a:visited {
	color: #34495e;
	text-decoration: none;
}

.menu .logo {
	width: 50px;
	height: 50px;
	border-radius: 50%;
	border: 2px solid #FFF;
	position: relative;
	background: url(static/images/07.jpg) no-repeat #41c281;
	position: absolute;
	box-shadow: 0px 0px 8px rgba(0, 0, 0, 0.3);
	background-size: 46px 46px;
}
</style>
<script>
	document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
		WeixinJSBridge.call('hideOptionMenu');
	});
	function qxOrder(tradeNo, event) {
		event.stopPropagation();
		var openid = document.getElementById("hiddenopenid").value;
		if (window.confirm("确认取消吗")) {
			$.ajax({
				type : "POST",
				url : "/haoyao/wchatTrade.do?method=cancelTrade&tradeNo="
						+ tradeNo,
				dataType : "json",
				success : function(data) {
					if (data.update == "true") {
						$("#btnPay").attr("disabled", "disabled");
						alert("取消成功！");
						window.location.reload();
					} else {
						$("#btnPay").removeAttr("disabled");
						alert("取消失败！");
					}
				},
				error : function() {
					alert("网络连接出错！");
				}
			});
		}
	}
	function cxOrder(tradeNo) {
		var openid = document.getElementById("hiddenopenid").value;
		if (window.confirm("确认取消吗")) {
			$.ajax({
				type : "POST",
				url : "/haoyao/wchatTrade.do?method=cancelTrade&tradeNo="
						+ tradeNo,
				dataType : "json",
				success : function(data) {
					if (data.update == "true") {
						$("#btnPay").attr("disabled", "disabled");
						alert("取消成功！");
						window.location.reload();
					} else {
						$("#btnPay").removeAttr("disabled");
						alert("取消失败！");
					}
				},
				error : function() {
					alert("网络连接出错！");
				}
			});
		}
	}
	function scOeder(tradeNo, event, buttonID) {
		event.stopPropagation();
		var openid = document.getElementById("hiddenopenid").value;

		var state = document.getElementById(buttonID + "a");
		if (window.confirm("确认取消吗")) {
			$.ajax({
				type : "POST",
				url : "/jeewx/orderController.do?disorder&orderid=" + tradeNo
						+ "&open_userid_id=" + openid,
				dataType : "json",
				success : function(data) {

					if (data.success == true) {
						$("#" + buttonID).attr("disabled", "disabled");
						state.innerHTML = "<p>已取消</p>"
						alert("取消成功！");
						/* window.location="http://w.ehaoyao.com/haoyao/wchatTrade.do?method=myOrder&openid="+openid; */
						/* window.location.href = "/haoyao/wchatTrade.do?method=myOrder&openid="
								+ openid; */
						/* window.location='orderTwo.jsp';*/
						/* releaseEvents(eventType) 
						  var tbRows = gel("teadelist").rows; //得到整个表格的行集合
						alert("11111111111111111");
						   for (var i = 0; i < tbRows.length; i++) {
						   if (tbRows[i].childNodes[1].innerHTML == txtArray[1]) {
						       gel("teadelist").deleteRow(i); //删除id相同的行
						       break;
						   } 
						}*/
					} else {
						$("#btnPay").removeAttr("disabled");
						alert("删除失败！");
					}
				},
				error : function() {
					alert("网络连接出错！");
				}
			});
		}
	}
</script>
<script type="text/javascript">
	function hidebutton(hideshopid) {

		for ( var p in hideshopid) {
			$("#" + p).attr("disabled", "disabled");
		}
	}
</script>
</head>
<body style="color: rgb(52, 73, 94); background: rgb(236, 240, 241);">
	<div class="container">

		<div class="paixu">
			<div class="btn-group pull-right">
				<c:if test="${isAll==true}">
					<button type="button" class="btn btn-o2o3 btn-sm"
						style="width:110px;">全&nbsp;&nbsp;部</button>
					<button type="button" class="btn btn-o2o3 btn-sm dropdown-toggle"
						data-toggle="dropdown" style="height:30px;">
						<span class="caret"></span> <span class="sr-only">Toggle
							Dropdown</span>
					</button>
					<ul class="dropdown-menu" role="menu">
						<li><a
							href="orderController.do?list&open_user_id=${open_user_id }"><span
								class="pull-right icon-uniE629"
								style="color:#57c6d7; font-size:16px"></span>全部</a></li>
						<li class="divider"></li>
						<c:forEach var="shopitems" items="${tbshoplist }">
							<li><a
								href="orderController.do?showlist&open_user_id=${open_user_id }&shopid=${shopitems.getId() }">${shopitems.getShopName() }</a></li>
							<li class="divider"></li>
						</c:forEach>


					</ul>
				</c:if>
				<c:if test="${isAll==false}">
					<button type="button" class="btn btn-o2o3 btn-sm"
						style="width:110px;">${chooseshop.getShopName() }</button>
					<button type="button" class="btn btn-o2o3 btn-sm dropdown-toggle"
						data-toggle="dropdown" style="height:30px;">
						<span class="caret"></span> <span class="sr-only">Toggle
							Dropdown</span>
					</button>
					<ul class="dropdown-menu" role="menu">
						<li><a
							href="orderController.do?showlist&open_user_id=${open_user_id }&shopid=${chooseshop.getId() }"><span
								class="pull-right icon-uniE629"
								style="color:#57c6d7; font-size:16px"></span>${chooseshop.getShopName() }</a></li>
						<li class="divider"></li>
						<c:forEach var="shopitems" items="${tbshoplist }">
							<li><a
								href="orderController.do?showlist&open_user_id=${open_user_id }&shopid=${shopitems.getId() }">${shopitems.getShopName() }</a></li>
							<li class="divider"></li>
						</c:forEach>
						<li><a
							href="orderController.do?list&open_user_id=${open_user_id }">全部</a>
						<li class="divider"></li>


					</ul>



				</c:if>
			</div>

			<div class="btn-group pull-right" style="margin-right:10px;">
				<c:if test="${isall==true }">
					<button type="button" class="btn btn-o2o btn-sm"
						style="width:110px;" id="ssname">全&nbsp;&nbsp;部</button>
					<button type="button" class="btn btn-o2o  btn-sm dropdown-toggle"
						data-toggle="dropdown" style="height:30px;">
						<span class="caret"></span> <span class="sr-only">Toggle
							Dropdown</span>
					</button>
					<ul class="dropdown-menu" role="menu" id="ulId">
						<li><a
							href="/jeewx/orderController.do?list&open_user_id=${open_user_id}"><span
								class="pull-right icon-uniE629"
								style="color:#29b572;font-size:16px"></span>全部</a></li>
						<li class="divider"></li>

						<li><a
							href="/jeewx/orderController.do?showstate&open_user_id=${open_user_id }&state=待发货">待发货</a></li>
						<li class="divider"></li>
						<li><a
							href="/jeewx/orderController.do?showstate&open_user_id=${open_user_id }&state=已发货">已发货</a></li>
						<li class="divider"></li>
						<li><a
							href="/jeewx/orderController.do?showstate&open_user_id=${open_user_id }&state=已取消">已取消</a></li>
						<li class="divider"></li>
						<li><a
							href="/jeewx/orderController.do?showstate&open_user_id=${open_user_id }&state=已完成">已完成</a></li>

					</ul>
				</c:if>
				<c:if test="${isall==false }">
					
					<button type="button" class="btn btn-o2o btn-sm"
						style="width:110px;" id="ssname">${choosestate }</button>
					<button type="button" class="btn btn-o2o  btn-sm dropdown-toggle"
						data-toggle="dropdown" style="height:30px;">
						<span class="caret"></span> <span class="sr-only">Toggle
							Dropdown</span>
					</button>
					<ul class="dropdown-menu" role="menu" id="ulId">
						<li><a
							href="/jeewx/orderController.do?showstate&open_user_id=${open_user_id }&state=${choosestate }">
								<span class="pull-right icon-uniE629"
								style="color:#29b572;font-size:16px"></span>${choosestate }</a></li>
						<li class="divider"></li>
						<c:forEach items="${liststate }" var="stateitems">
							<li><a
								href="/jeewx/orderController.do?showstate&open_user_id=${open_user_id }&state=${stateitems}">${stateitems}</a></li>
							<li class="divider"></li>
						</c:forEach>
						<li><a
							href="/jeewx/orderController.do?list&open_user_id=${open_user_id}">全部</a></li>

					</ul>
				</c:if>

			</div>
		</div>
		<ul class="pay-list" style="list-style:none; margin:0; padding:0">

			<c:forEach var="orderitem" items="${orderlist }" begin="0" step="1">

				<li id="image"
					onclick="window.location.href='orderController.do?display&open_user_id=${open_user_id }&order_id=${orderitem.getOrderid() }'">
					<div class="panel" style="margin-top:15px;">
						<div class="panel-heading" style="border-bottom:1px solid #e6e5e5">
							<span class="pull-right red">

								<p id="${orderitem.getOrderid() }a" class="pull-right"
									style="color:#f15a28;">${orderitem.getOrderstate() }</p>


							</span>${orderitem.getShopname() } ${orderitem.getCreatetime() }
						</div>
						<div class="panel-body" style="padding-bottom:5px;">
							<div class="row" style="padding:5px 0 5px 15px;">
								<c:forEach var="items" items="${orderitem.getOrteritem() }"
									begin="0" step="1">
									<div class="col-xs-4"
										style=" padding-right:15px; margin-bottom:15px;">

										<div class="num">${items.getCount()}</div>
										<a href="http://w.ehaoyao.com/haoyao/orderTwo.jsp"></a><img
											src="${items.getProductImageUrl() }" width="100%">
									</div>
								</c:forEach>
							</div>
							<div class="media">
								<c:if test="${orderitem.getOrderstate()!='已取消'}">
									<button id="${orderitem.getOrderid() }" type="button"
										class="btn btn-o2o2 pull-right"
										onclick="scOeder('${orderitem.getOrderid() }',event ,'${orderitem.getOrderid() }')"
										style=" margin-top:15px;">取消订单</button>
								</c:if>
								<p>
									总计：<span class="red" style="font-size:16px; padding-right:5px;">
										${orderitem.getTotalcount() } </span>件商品
								</p>
								<p>
									总价： <span class="red">￥ ${orderitem.getTotalprice() }</span>
								</p>

							</div>
						</div>
					</div>
				</li>
			</c:forEach>
			<input type="hidden" id="hiddenopenid" name="hiddenopenid"
				value="${open_user_id }">
			</inpute>
			</inpute>
			</inpute>
		</ul>
	</div>
	<script src="${webRoot }/plug-in/static/jquery/jquery-2.0.3.min.js"
		type="text/javascript"></script>
	<script src="${webRoot }/plug-in/static/bootstrap/bootstrap.min.js"
		type="text/javascript"></script>

	<script
		src="${webRoot }/plug-in/static/owl-carousel/owl.carousel.min.js"
		type="text/javascript"></script>
	
	<%@ include  file="menu.jsp"%>

	<script type="text/javascript">
		getMenu();

		function getMenu() {
			$('#menuBtn').click(function() {
				$('#menuContent').toggle();
			});
		};
	</script>
</body>
</html>
