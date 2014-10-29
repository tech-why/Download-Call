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
	<link href="${webRoot}/plug-in/weixin_mall/static/style/hys.css" rel="stylesheet">
    <link href="${webRoot}/plug-in/weixin_mall/static/style/style.css" rel="stylesheet">
    
    <link href="${webRoot}/plug-in/weixin_mall/static/style/base.css" rel="stylesheet">
    <link href="${webRoot}/plug-in/weixin_mall/static/style/font-awesome.min.css" rel="stylesheet">
   <link href="${webRoot}/plug-in/weixin_mall/static/style/wxsc.css" rel="stylesheet">
 
     <link href="${webRoot}/plug-in/weixin_mall/static/style/address.css" rel="stylesheet">
    
    
     <script src="${webRoot}/plug-in/weixin_mall/static/jquery/jquery-2.0.3.min.js" type="text/javascript"></script>
     <script src="${webRoot}/plug-in/weixin_mall/static/bootstrap/bootstrap.min.js" type="text/javascript"></script>
     
     <style>
	     .bp-switch {position:relative; width:54px; height:35px; background:url(${webRoot}/plug-in/weixin_mall/static/images/bp_switch_bg.png) center no-repeat;  background-size:54px auto; overflow:hidden;}
	.bp-switch .bp-switch-text {margin:4px auto; width:68px; height:32px;}
	.bp-switch .bp-switch-text span {display:block; float:left; width:50%; height:32px; line-height:32px; font-size:14px; color:#797067;}
	.bp-switch .bp-switch-bar {position:absolute; top:5px;  width:22px; height:22px; background:url(${webRoot}/plug-in/weixin_mall/static/images/bp_switch_bar.png) center no-repeat;background-size:20px auto;}
	.bp-switch.current {background:url(${webRoot}/plug-in/weixin_mall/static/images/bp_switch_bg_current.png) center no-repeat;background-size:54px auto;}
	.bp-switch.current .bp-switch-text span {color:#09b173;}
	.bp-switch.current .bp-switch-bar {left:32px; background:url(${webRoot}/plug-in/weixin_mall/static/images/bp_switch_bar_current.png) center no-repeat;background-size:20px auto;}
	     
     </style>
	<script type="text/javascript">
	
function aa(biaoshi){
	window.location.href="submitSuccess.jsp";
	
}
	
   

function zhifu(){
	$.ajax({  
	    url :"/jeewx/generateOrderController.do?generateOrder",
	    type:"post",    //数据发送方式  
	    dataType:"json",   //接受数据格式  
	    data:{
		    openUserId : $('#openUserId').val(),
			shopId : $('#shopId').val(),
			addressId : $('#addressId').val(),
			timeId : $('#timeId').val(),
			todayOrTomorow : $('#todayOrTomorrow').val(),
			userMessage : $('#userMessage').val()
	    },   //要传递的数据；就是上面序列化的值  
	    success: function(data) {
	    	window.location.href="/jeewx/generateOrderController.do?submitSuccess&open_user_id=${open_user_id}&orderId="+data.obj;
		} 
	});
}
function div(){
    document.getElementById("errorDiv").style.display = "";
	//$("#errorDiv").style.display="";
}
</script>
	
</head>
<script type="text/javascript">
	function oderDetail(){
		var outTradeNo = document.getElementById("outTradeNo").value;
		window.location.href="submitProblemController.do?display&open_user_id=${open_user_id}";
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
<h4 class="text-center"  style="color:#09b173; margin:30px 0"><i class="icon-uniE627" style="font-size:26px; vertical-align:-4px;"></i>&nbsp;报修单提交成功</h4>
  <ul class="list-group">
		    <li class="list-group-item">报修单号：${problem.problemCount }</li>
		    <li class="list-group-item">工作人员会尽快和您联系</li>
		    <li class="list-group-item">在微信发送wdwxd查询维修进度</li>
		</ul>


          <button type="button" class="btn btn-o2o btn-block" data-toggle="modal" data-target="#address"  style="margin-top:30px" onclick="oderDetail()">返回</button>
       <div class="text-center" style="margin:35px 0 15px 0"><%-- <img src="${webRoot}/plug-in/weixin_mall/static/images/pay_footer.png" alt="" width="150" > --%></div>
</div>

	

</body>
</html>