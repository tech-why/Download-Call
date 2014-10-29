<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/context/mytags.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta charset="utf-8">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport" />
	<meta content="telephone=no" name="format-detection" />
	<meta content="email=no" name="format-detection" />

	 <link href="${webRoot}/plug-in/weixin_mall/static/bootstrap/bootstrap.min.css" rel="stylesheet">
     <link href="${webRoot}/plug-in/weixin_mall/static/style/wxsc.css" rel="stylesheet">
     <link href="${webRoot}/plug-in/weixin_mall/static/style/style.css"  rel="stylesheet">
     <script src="${webRoot}/plug-in/weixin_mall/static/jquery/jquery-2.0.3.min.js" type="text/javascript"></script>
	<script src="${webRoot}/plug-in/weixin_mall/static/bootstrap/bootstrap.min.js" type="text/javascript"></script>

<script type="text/javascript">
	function oderDetail(){
		var outTradeNo = document.getElementById("outTradeNo").value;
		window.location.href="orderController.do?display&open_user_id=${open_user_id}&order_id=${order.id}";
	}
	
	function change_pic(){
		var imgObj = document.getElementById("caocao_pic");
		var Flag=(imgObj.getAttribute("src",2)=="static/images/radio2.png")
		imgObj.src=Flag?"static/images/radio1.png":"static/images/radio2.png";
	}
</script>


</head>

<body style="background:#ecf0f1; color:#34495e ;" >
<input type="hidden" value="14624" id="outTradeNo"/>
<div class="container" >
<h4 class="text-center"  style="color:#09b173; margin:30px 0"><i class="icon-uniE627" style="font-size:26px; vertical-align:-4px;"></i>&nbsp;提交订单成功</h4>
  <ul class="list-group">
		    <li class="list-group-item">订单号：${orderNo}</li>
		    <li class="list-group-item">订单金额：<strong style="color:#f15a28;">￥${order.totalPrice }</strong></li>
		    <li class="list-group-item">支付方式：货到付款</li>
		</ul>


          <button type="button" class="btn btn-o2o btn-block" data-toggle="modal" data-target="#address"  style="margin-top:30px" onclick="oderDetail()">查看订单</button>
       <div class="text-center" style="margin:35px 0 15px 0"><img src="static/images/pay_footer.png" alt="" width="150" ></div>
</div>

	

</body>
</html>