<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<!-- 	<script src="http://crypto-js.googlecode.com/svn/tags/3.1.2/build/rollups/md5.js"></script>
	<script src="http://crypto-js.googlecode.com/svn/tags/3.1.2/build/rollups/sha1.js"></script> -->
	<script src="static/script/md5.js"></script>
	<script src="static/script/sha1.js"></script>
	<meta charset="utf-8">
		<title style="font:30px #ffffff">故障保修</title>
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport" />
	<meta content="telephone=no" name="format-detection" />
	<meta content="email=no" name="format-detection" />


    <link href="${webRoot}/plug-in/weixin_mall/static/bootstrap/bootstrap.min.css" rel="stylesheet"> 
    <link href="${webRoot}/plug-in/weixin_mall/static/style/style.css" rel="stylesheet">
    
   <link href="${webRoot}/plug-in/weixin_mall/static/style/wxsc.css" rel="stylesheet">
 
    
    
     
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
				url : "/jeewx/submitProblemController.do?disorder&orderid=" + tradeNo
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
	
	
	
	function ckxx(problemid,isoperator, event) {
		event.stopPropagation();
		var openid = document.getElementById("hiddenopenid").value;
		
		window.location.href="/jeewx/submitProblemController.do?detail&open_user_id="+openid+"&problem_id="+problemid+"&isoperator="+${isoperator };
		
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
							href="submitProblemController.do?list&open_user_id=${open_user_id }"><span
								class="pull-right icon-uniE629"
								style="color:#57c6d7; font-size:16px"></span>全部</a></li>
						<li class="divider"></li>
						<c:forEach var="operatertype" items="${operatertypelist }">
							<li><a
								href="submitProblemController.do?showlist&open_user_id=${open_user_id }&type_id=${operatertype.id }">${operatertype.operateType }</a></li>
							<li class="divider"></li>
						</c:forEach>
			
					</ul>
				</c:if>
				<c:if test="${isAll==false}">
					<button type="button" class="btn btn-o2o3 btn-sm"
						style="width:110px;">${choosetype.operateType }</button>
					<button type="button" class="btn btn-o2o3 btn-sm dropdown-toggle"
						data-toggle="dropdown" style="height:30px;">
						<span class="caret"></span> <span class="sr-only">Toggle
							Dropdown</span>
					</button>
					<ul class="dropdown-menu" role="menu">
						<li><a
							href="submitProblemController.do?showlist&open_user_id=${open_user_id }&type_id=${choosetype.id }"><span
								class="pull-right icon-uniE629"
								style="color:#57c6d7; font-size:16px"></span>${choosetype.operateType }</a></li>
						<li class="divider"></li>
						<c:forEach var="operatertype" items="${operatertypelist }">
							<li><a
								href="submitProblemController.do?showlist&open_user_id=${open_user_id }&type_id=${operatertype.id }">${operatertype.operateType }</a></li>
							<li class="divider"></li>
						</c:forEach>
						<li><a
							href="submitProblemController.do?list&open_user_id=${open_user_id }"><span
								style="color:#57c6d7; font-size:16px"></span>全部</a></li>
						<li class="divider"></li>


					</ul>



				</c:if>
			</div>

		</div>
		<ul class="pay-list" style="list-style:none; margin:0; padding:0">

			<c:forEach var="problem" items="${problemlist }" begin="0" step="1">

				<li id="image"
					onclick="window.location.href='submitProblemController.do?detail&open_user_id=${open_user_id }&problem_id=${problem.id }&isoperator=${isoperator }'">
					<div class="panel" style="margin-top:15px;">
						<div class="panel-heading" style="border-bottom:1px solid #e6e5e5">
							<span class="pull-right red">
								<p id="${problem.id }a" class="pull-right"
									style="color:#f15a28;">${problem.state }</p>
							</span>
							<p>编号：${problem.problemCount } &nbsp;&nbsp;提交日期:${problem.submitTime }</p>
						</div>
						
						<div class="panel-body" style="padding-bottom:5px;">
						地址：${problem.building.builddingName }${problem.address.houseCode }</br>
							联系人:${problem.address.contact }</br>
							电话:${problem.address.mobileNumber }</br><p>
							故障类型:${problem.problemType.typeName }</br>
							故障描述：${problem.problemDescriptionf }
							<c:if test="${problem.state=='已经完成'}">
							</br>完成时间：${problem.completeTime }
							
							</c:if>
							</p>
						<div class="row" style="padding:5px 0 5px 15px;">
						</div>
							<div class="media">
									<%-- <button id="${problem.id }" type="button"
										class="btn btn-o2o2 pull-right"
										onclick="scOeder('${problem.id }',event ,'${orderitem.getOrderid() }')"
										style=" margin-top:10px;">取消报修</button> --%>
								<button id="${problem.id }ck" type="button"
										class="btn btn-o2o pull-right"
										onclick="ckxx('${problem.id }','${isoperator }',event)"
										style=" margin-top:10px;">查看详细</button>
							</div>
						</div>
					</div>
				</li>
			</c:forEach>
			<input type="hidden" id="hiddenopenid" name="hiddenopenid" value="${open_user_id }">
		</ul>
	</div>
	<script src="${webRoot }/plug-in/static/jquery/jquery-2.0.3.min.js"
		type="text/javascript"></script>
	<script src="${webRoot }/plug-in/static/bootstrap/bootstrap.min.js"
		type="text/javascript"></script>

	<script
		src="${webRoot }/plug-in/static/owl-carousel/owl.carousel.min.js"
		type="text/javascript"></script>
	
	

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
     