<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单详情</title>

<meta charset="utf-8">
<meta
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;"
	name="viewport">
<script src="${webRoot }/plug-in/static/script/hm.js"></script>
<script src="${webRoot }/plug-in/static/script/md5.js"></script>
<script src="${webRoot }/plug-in/static/script/sha1.js"></script>

<meta content="telephone=no" name="format-detection">
<meta content="email=no" name="format-detection">

<script src="${webRoot }/plug-in/static/jquery/jquery-2.0.3.min.js"
	type="text/javascript"></script>
<link
	href="${webRoot }/plug-in/static/bootstrap/bootstrap.min.css"
	rel="stylesheet">
<link href="${webRoot }/plug-in/static/style/hys.css"
	rel="stylesheet">
<link href="${webRoot }/plug-in/static/style/wxsc.css"
	rel="stylesheet">
<script type="text/javascript"
	src="${webRoot }/plug-in/static/script/baidu.js"></script>
<script type="text/javascript">
document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
	WeixinJSBridge.call('hideOptionMenu');
	});
function cancelOrder() {
	var openid=document.getElementById("hiddenopenid").value;
	var tradeNo=document.getElementById("tradeNo").value;
	if (window.confirm("确认取消吗")) {
		$.ajax( {   
		     type : "POST",   
		     url : "/haoyao/wchatTrade.do?method=cancelTrade&tradeNo="+tradeNo,   
		     dataType: "json",   
		     success : function(data) {
		         if(data.update == "true"){
		        	 $("#btnPay").attr("disabled","disabled");
		        	 alert("取消成功！");
		        	 window.location="http://w.ehaoyao.com/haoyao/wchatTrade.do?method=myOrder&openid="+openid;
		         }   
		         else{  
		        	 $("#btnPay").removeAttr("disabled");
		             alert("取消失败！");   
		         }   
		     },   
		     error :function(){   
		         alert("网络连接出错！");   
		     }   
		 }); 
	}
}

function changeButton(){
	document.getElementById("cuiDan").innerHTML="已催办";
	$("#cuiDan").attr("disabled","disabled");
	
}

function scOeder(tradeNo) {
	var openid=document.getElementById("hiddenopenid").value;
	if (window.confirm("确认删除吗")) {
		$.ajax( {   
		     type : "POST",   
		     url : "/haoyao/wchatTrade.do?method=deleteTrade&tradeNo="+tradeNo,   
		     dataType: "json",   
		     success : function(data) { 
		         if(data.update == "true"){ 
		        	 $("#btnPay").attr("disabled","disabled");
		        	 alert("删除成功！");
		        	 window.location="http://w.ehaoyao.com/haoyao/wchatTrade.do?method=myOrder&openid="+openid;
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
		         }   
		         else{
		        	 $("#btnPay").removeAttr("disabled");
		             alert("删除失败！");   
		         }   
		     },   
		     error :function(){   
		         alert("网络连接出错！");   
		     }   
		 }); 
	}
}
</script>
<script type="text/javascript">
var oldPackageString;
var oldTimeStamp;
var oldNonceStr;

function getIp(){
	
    var str_tmp = '10.10.10.71' ;
 
	return str_tmp;
	}
	
	
	function clearBr(key) {
		key = Trim(key, "g");
		key = key.replace(/<\/?.+?>/g, "");
		key = key.replace(/[\r\n]/g, "");
		return key;
	}
	function getItemShortDesc(){
		
		return document.form1.itemShort.value;
	}

	function getANumber() {
		var date = new Date();
		var times1970 = date.getTime();
		var times = date.getDate() + "" + date.getHours() + ""
				+ date.getMinutes() + "" + date.getSeconds();
		var encrypt = times * times1970;
		if (arguments.length == 1) {
			return arguments[0] + encrypt;
		} else {
			return encrypt;
		}

	}

	function getOutTradeNo() {
		return document.form1.outTradeNo.value;
	}
	
	function getPartnerId() {
		
		return document.form1.partnerId.value;
	
	}

	function getPartnerKey() {
		
		return document.form1.paternerKey.value;
		
	}
	function getFree(){
		
		 var money = document.form1.totalFee.value;
		 var mon = parseInt(money);
		 return mon;
	}

	function getPackage() {
		var banktype = "WX";
		var body =getItemShortDesc();
		var fee_type = "1";
		var input_charset = "UTF-8";
		var notify_url ="http://w.ehaoyao.com/haoyao/notifyToDeal.do?method=notifyToDeal";
		var out_trade_no =getOutTradeNo();
		var partner = getPartnerId();
		var spbill_create_ip = getIp();
		var total_fee =getFree();
		var partnerKey = getPartnerKey();
		var signString = "bank_type=" + banktype + "&body=" + body
				+ "&fee_type=" + fee_type + "&input_charset=" + input_charset
				+ "&notify_url=" + notify_url + "&out_trade_no=" + out_trade_no
				+ "&partner=" + partner + "&spbill_create_ip="
				+ spbill_create_ip + "&total_fee=" + total_fee + "&key="
				+ partnerKey;	
		
		var md5SignValue = ("" + CryptoJS.MD5(signString)).toUpperCase();
		
			
		banktype = encodeURIComponent(banktype);
		body = encodeURIComponent(body);
		fee_type = encodeURIComponent(fee_type);
		input_charset = encodeURIComponent(input_charset);
		notify_url = encodeURIComponent(notify_url);
		out_trade_no = encodeURIComponent(out_trade_no);
		partner = encodeURIComponent(partner);
		spbill_create_ip = encodeURIComponent(spbill_create_ip);
		total_fee = encodeURIComponent(total_fee);

	
		var completeString = "bank_type=" + banktype + "&body=" + body
				+ "&fee_type=" + fee_type + "&input_charset=" + input_charset
				+ "&notify_url=" + notify_url + "&out_trade_no=" + out_trade_no
				+ "&partner=" + partner + "&spbill_create_ip="
				+ spbill_create_ip + "&total_fee=" + total_fee;
		
		completeString = completeString + "&sign=" + md5SignValue;
		oldPackageString = completeString;
		
		return completeString;
	}	
	

	
	
	function getAppId(){
		return document.form1.appId.value;
	}
	
	function getAppKey() {
		
	return document.form1.appKey.value;
	
	}

	function getTimeStamp() {
		var timestamp = new Date().getTime();
		var timestampstring = timestamp.toString();
		oldTimeStamp = timestampstring;
		return timestampstring;
	}


	function getNonceStr() {
		var $chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
		var maxPos = $chars.length;
		var noceStr = "";
		for (i = 0; i < 32; i++) {
			noceStr += $chars.charAt(Math.floor(Math.random() * maxPos));
		}
		oldNonceStr = noceStr;
		return noceStr;
	}
	
	function getSignType() {
		
		 return "sha1";	
	}
	
    function getSign()
    {
        //var app_id = getAppId().toString();
       // var app_key = getAppKey().toString();
         var app_id = document.getElementById("appId").value;
        var app_key = document.getElementById("appKey").value;
        var nonce_str = oldNonceStr;
        var package_string = oldPackageString;
        var time_stamp = oldTimeStamp;
        var keyvaluestring = "appid="+app_id+"&appkey="+app_key+"&noncestr="+nonce_str+"&package="+package_string+"&timestamp="+time_stamp;
        sign = CryptoJS.SHA1(keyvaluestring).toString();
        return sign;
    }
  function getTradeId(){
	  return document.getElementById("tradeId").value;
  }
function bb(){
		$.ajax({  
			
		    url :"/haoyao/wchatTrade.do?method=goPay",  
		    type:"post",    //数据发送方式  
		    dataType:"json",   //接受数据格式  
		    data:"id="+getTradeId(),   //要传递的数据；就是上面序列化的值  
		    success: function(data) {
		    	document.getElementById("outTradeNo").value=data.outTradeNo;
		   
		    	document.getElementById("partnerId").value=data.partnerId;
		    
		    	document.getElementById("paternerKey").value=data.paternerKey;
	
		       document.getElementById("appId").value=data.appId;

		       document.getElementById("appKey").value=data.appKey;

		       document.getElementById("itemShort").value=data.itemShort;

		        document.getElementById("totalFee").value=data.totalFee;
				
		       var appid = data.appId+"";
				WeixinJSBridge.invoke('getBrandWCPayRequest', {
					"appId" : appid, //公众号名称，由商户传入
					"timeStamp" :getTimeStamp(), //时间戳
					"nonceStr" :getNonceStr(), //随机串
					"package" : getPackage(),//扩展包
					"signType" :"sha1", //微信签名方式:1.sha1
					"paySign" :getSign()
			//微信签名
			}, function(res) {
				
				if (res.err_msg == "get_brand_wcpay_request:ok") {
					
				}
				
				// 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回ok，但并不保证它绝对可靠。
				//因此微信团队建议，当收到ok返回时，向商户后台询问是否收到交易成功的通知，若收到通知，前端展示交易成功的界面；若此时未收到通知，商户后台主动调用查询订单接口，查询订单的当前状态，并反馈给前端展示相应的界面。
			});
		}
	});   
}
function checkstate(state){
	if(state=='已取消'){
		
		 $("#btnPay").attr("disabled", "disabled");
		 
	}
	
}

</script>
<script>
function scOeder(event,tradeNo) {
		event.stopPropagation();
		var openid = document.getElementById("hiddenopenid").value;
		var state=document.getElementById("state");
		if (window.confirm("确认取消吗")) {
			$.ajax({
				type : "POST",
				url : "/jeewx/orderController.do?disorder&orderid=" + tradeNo
						+ "&open_userid_id=" + openid,
				dataType : "json",
				success : function(data) {
					if (data.success == true) {
						$("#btnPay").attr("disabled", "disabled");
						state.innerHTML="<p>已取消</p>"
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
	
	function returnOrderList()
	{

        window.location.href = '/jeewx/orderController.do?list&open_user_id=${open_user_id }';

	}
</script>

</head>




<body onload="javascript:checkstate('${order.getOrderState()}');"
	style="color: rgb(52, 73, 94); padding-top: 15px; background: rgb(236, 240, 241);">

	<form action="" id="form1" name="form1">
		<input type="hidden" id="tradeId" value="13718"> <input
			type="hidden" id="appId"> <input type="hidden"
			id="outTradeNo"> <input type="hidden" id="totalFee">
		<input type="hidden" id="partnerId"> <input type="hidden"
			id="appKey"> <input type="hidden" id="paternerKey"> <input
			type="hidden" id="itemShort">

	</form>
	<div class="container">
		<ul class="list-group">
			<li class="list-group-item"><input type="hidden" id="tradeNo"
				name="tradeNo" value="${order.getOrderCount() }"> <input
				type="hidden" id="hiddenopenid" name="hiddenopenid"
				value="${open_user_id}">


				<div class="pull-left">
					订单号：<br> <span style="font-size:16px;">${orderNo}</span>
				</div>

				<div class="pull-right" style="color:#f15a28" id="state">${order.getOrderState() }</div>

				<div class="clearfix"></div></li>
		</ul>
		<ul class="list-group">
			<li class="list-group-item">总金额：<strong style="color:#f15a28;">${order.getTotalPrice() }</strong></li>
			<li class="list-group-item">收货地址：${address }</li>
			<li class="list-group-item">收货人：${contract }</li>
		</ul>
		<!-- 根据订单状态，按钮不同 -->
		<!-- 订单状态：代付款 -->
		<div class="row show-grid">

			<div class="col-xs-5 col-sm-5">
				<button id="btnPay" type="button" class="btn btn-o2o  btn-block"
					onclick="scOeder(event,'${order.getId()}')">取消</button>
			</div>
			<div class="col-xs-7 col-sm-7">
				<button id="btnPay" type="button" class="btn btn-o2o  btn-block"
					onclick="returnOrderList()">返回订单列表</button>
			</div>
		</div>



		<!-- 订单状态 付款前十分钟 -->






		<!-- 订单状态：已付款 -->



		<!-- 订单状态：已出货 -->

		<!-- 订单状态：已付款  点击存单后 -->
		<!-- 		<div class="row show-grid">
		    <div class="col-xs-7 col-sm-7"><button type="button" class="btn btn-default btn-lg btn-block" disabled="disabled">催单已提交</button></div>
		    <div class="col-xs-5 col-sm-5"><button type="button" class="btn btn-default btn-lg btn-block">取消订单</button></div>
		</div> -->
		<!-- 订单状态：已出货  点击存单后 -->
		<!-- 		<div class="row show-grid">
		    <div class="col-xs-12 col-sm-12"><button type="button" class="btn btn-default btn-lg btn-block" disabled="disabled">催单已提交</button></div>
		</div> -->
		<!-- 订单状态：已完成 无按钮 -->
		<!-- 订单状态：交易取消 无按钮 -->
		<br>
		<div style="margin-bottom:5px;">
			<div class="pull-left">
				<h4>商品信息</h4>
			</div>
			<div class="pull-right" style=" padding-top:10px;">
				共<strong>${order.getTotalCount() }</strong>件
			</div>
			<div class="clearfix"></div>
		</div>
		<c:forEach var="items" items="${orderitemslist }" begin="0" step="1">
			<div class="panel">
				<div class="panel-body">
					<div class="media">
						<a class="pull-left"
							href="#"
							style="width:80px;"> <!--  /haoyao-->
							<p>
								<img class="media-object img-responsive"
									src="${items.getProductImageUrl() }" alt=""
									onclick="window.location.href=#;">
							</p> <!-- <span>X</span> -->
						</a>
						<div class="media-body">
							<h4 class="media-heading">${items.getProductName() }</h4>
							<div style="margin-top:5px;">
								<p class="pull-left" style="font-size:20px;">X
									${items.getCount() }</p>
								<p class="pull-right" style="color:#f15a28;">
									微信价<strong class="text-danger"
										style="font-size:20px; color:#f15a28">￥<em id="price"
										style="font-style:normal;">${items.getProductPrice() }</em></strong>
								</p>
								<p class="clearfix"></p>
							</div>
						</div>
					</div>
				</div>
			</div>

		</c:forEach>

		<div class="alert alert-danger"
			style="background:#fff; border-color:#e6e5e5; color:#34495e;">


			<p>
				总计价格:<span style="color:#f15a28">￥${order.getTotalPrice() }</span>
			</p>
			<p>满29.0包邮￥0.0</p>

		</div>
		<h4>订单跟踪</h4>

		<ul class="list-group">
			<li class="list-group-item">
				<p style="font-size:16px;"></p>
				<table id="xiahuxian">

					<c:forEach var="operitems" items="${orderoperatelist }" begin="0" step="1">
						<tbody>
							<tr>
								<td>${operitems.getOperateType() }</td>
							</tr>
							<tr>
								<td>${operitems.getOperateTime() }</td>
							</tr>




						</tbody>
					</c:forEach>
				</table>
			</li>
		</ul>



	</div>
	
	

	<script src="${webRoot }/plug-in/static/jquery/jquery-2.0.3.min.js"
		type="text/javascript"></script>

</body>
</html>
