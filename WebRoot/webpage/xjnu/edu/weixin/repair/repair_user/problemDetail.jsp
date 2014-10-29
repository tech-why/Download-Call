<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>保修单详情</title>

<meta
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;"
	name="viewport">
<script src="${webRoot }/plug-in/static/script/hm.js"></script>
<script src="${webRoot }/plug-in/static/script/md5.js"></script>
<script src="${webRoot }/plug-in/static/script/sha1.js"></script>

<meta content="telephone=no" name="format-detection">

    <link href="${webRoot}/plug-in/weixin_mall/static/bootstrap/bootstrap.min.css" rel="stylesheet"> 
	<link href="${webRoot}/plug-in/weixin_mall/static/style/hys.css" rel="stylesheet">
    <link href="${webRoot}/plug-in/weixin_mall/static/style/style.css" rel="stylesheet">
    
    <link href="${webRoot}/plug-in/weixin_mall/static/style/base.css" rel="stylesheet">
    <link href="${webRoot}/plug-in/weixin_mall/static/style/font-awesome.min.css" rel="stylesheet">
   <link href="${webRoot}/plug-in/weixin_mall/static/style/wxsc.css" rel="stylesheet">
 
     <link href="${webRoot}/plug-in/weixin_mall/static/style/address.css" rel="stylesheet">
    
    
     <script src="${webRoot}/plug-in/weixin_mall/static/jquery/jquery-2.0.3.min.js" type="text/javascript"></script>
     <script src="${webRoot}/plug-in/weixin_mall/static/bootstrap/bootstrap.min.js" type="text/javascript"></script>
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
	if(state=="已经完成"){
		 $("#btnPay").attr("disabled", "disabled");
	}
	
}

</script>
<script>
function scproblem(event,problemid) {
		event.stopPropagation();
		var openid = document.getElementById("hiddenopenid").value;
		var state=document.getElementById("state");
		if (window.confirm("确认取消吗")) {
			$.ajax({
				type : "POST",
				url : "/jeewx/submitProblemController.do?disproblem&problem_id=" + problemid
						+ "&open_user_id=" + openid,
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

        window.location.href = '/jeewx/submitProblemController.do?list&open_user_id=${open_user_id }';

	}
</script>

</head>




<body onload="javascript:checkstate('${problem.state }');"
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
				name="tradeNo" value=""> 
				<input
				type="hidden" id="hiddenopenid" name="hiddenopenid"
				value="${open_user_id}">
				<input type="hidden" id="hiddenproblemid" value="${problem.id }"/>
				<div class="pull-left">
					维修单号：<br> <span style="font-size:16px;">${problem.problemCount } </span>
				</div>

				<div class="pull-right" style="color:#f15a28" id="state">${problem.state }</div>

				<div class="clearfix"></div></li>
		</ul>
		<ul class="list-group">
			<li class="list-group-item">地址：${problem.building.builddingName }${problem.address.houseCode }<strong style="color:#f15a28;">${order.getTotalPrice() }</strong></li>
			
			<li class="list-group-item">联系人：${problem.address.contact }</li>
			<li class="list-group-item">联系电话：${problem.address.mobileNumber }</li>
			<li class="list-group-item">故障类型：${problem.problemType.typeName }</li>
				<li class="list-group-item">故障描述：${problem.problemDescriptionf }</li>
					<c:if test="${problem.state=='已经完成'}">
						<li class="list-group-item">完成时间：${problem.completeTime }</li>
							
							</c:if>
		</ul>
		<!-- 根据订单状态，按钮不同 -->
		<!-- 订单状态：代付款 -->
		<div class="row show-grid">

			<div class="col-xs-5 col-sm-5">
			<c:if test="${isoperator==true }">
		<c:if test="${problem.state!='已取消'&&problem.state!='已经完成' }">
         <button id="openSelectdrugs" type="button" class="btn btn-o2o2  btn-block"
					>添加维修记录</button></br>
					</c:if>
					</c:if>
					<c:if test="${isoperator==false }">
					<button id="btnPay" type="button" class="btn btn-o2o2  btn-block"
					onclick="scproblem(event,'${problem.id}')">取消</button>
					
					</c:if>
				
			</div>
			<div class="col-xs-7 col-sm-7">
				<button id="btnPay1" type="button" class="btn btn-o2o  btn-block"
					onclick="returnOrderList()">返回维修单列表</button>
			</div>
		</div>


		<br>
		


		
						
         
		<h3>维修记录</h3>
		
		<ul class="list-group">
			<li class="list-group-item">
				<p style="font-size:16px;"></p>
				<table id="xiahuxian">
					<c:forEach var="recorditems" items="${recordlist }" begin="0" step="1">
						<tbody>
							<tr>
								<td>${recorditems.operateState }</td>
							</tr>
							<tr>
								<td>${recorditems.operateTime }</td>
							</tr>
						</tbody>
					</c:forEach>
				</table>
			</li>
		</ul>



	</div>
 <div id="Selectdrugs" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" style="display: none;">
	    <div class="modal-dialog">
		    <div class="modal-content">
		        <div class="modal-header" style="border-bottom:2px solid #09b173">
		        	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		        	<h4 class="modal-title"><i class="icon-uniE614" style="color:#41c27f; padding-right:5px; vertical-align:-2px; font-size:20px;"></i>选择操作类型</h4>
		        </div>
	          <div class="modal-body" style="padding:10px 0 0 0">
		        	

               <!-- Tab panes -->
        <div class="tabs-content">
					<ul id="tabsContent">
					 	<li style="padding:20px;" class="current" data-date = "today">
                 <div class="panel-group">
                <div class="panel panel-default panelA">
                  <div class="js-collapsePanel ">
                  
             	<c:forEach var="type" items="${operatertypelist }">
						  <div  class="panel-heading js-collapse" style="background:#FFF; border-bottom:1px solid #e6e5e5;">
							<h4 class="panel-title">
							  <a style="text-decoration:none">
								<p  id="${type.id }"drugid="${type.id }" >
									<span class="select pull-right">
										<img src="${webRoot}/plug-in/weixin_mall/static/images/selectno.png">
									</span>
									
									<span class="js-drugName">${type.operateType }</span>
									
								</p>
							  </a>
							</h4>
						  </div>
						  </c:forEach>
						
                  </div>
                </div>
              </div>
            </li>
           
          </ul>
        </div>
                  
                     <div class="modal-footer" style="margin-top:0">	        	
		        	<button id="selectDrugSubmit" type="button" class="btn btn-o2o btn-block">提交</button>
		        </div>
		    </div>
	    </div>
</div>
	
    <script type="text/javascript">
     	$('#selectPerson').click(function(){
			var _img = $(this).find('img');

			_img.attr('src', 'static/images/radio2.png');
			$('#selectCompany').find('img').attr('src', 'static/images/radio1.png');
			$('#title').hide();
		});
		$('#selectCompany').click(function(){
			var _img = $(this).find('img');

			_img.attr('src', 'static/images/radio2.png');
			$('#selectPerson').find('img').attr('src', 'static/images/radio1.png');
			$('#title').show();
		});
     	$('.js-select').click(function(){
			var _c = $(this).hasClass('current'),
				_cImg = $(this).find('img');

			if( _c ){
				$(this).removeClass('current');
				_cImg.attr('src', 'static/images/selectno.png');
			}else{
				$(this).addClass('current');
				_cImg.attr('src', 'static/images/select.png');
			};
		});

		bpSwitch();

		function bpSwitch(){
			var _b = $('.bp-switch'),
				_invoice = $('#invoicePanel');

			_b.click(function(){
				var _this = this;

				if( $(_this).hasClass('current') ){
					$(_this).find('.bp-switch-bar').animate({
						'left': '1px'
					}, 200, function(){
						$(_this).removeClass('current');
						_invoice.hide();
					});
				}else{
					$(_this).find('.bp-switch-bar').animate({
						'left': '32px'
					}, 200, function(){
						$(_this).addClass('current');
						_invoice.show();
					});
				};
			});
		};
		
		
		selectDrugs();

    $('#openSelectdrugs').click(function(e){
      e.stopPropagation();

      $('#Selectdrugs').modal('show');
    });

    

   

    

	$(document).on({
      click: function(){
        $('#tabsContent').find('[drugid = '+ $(this).attr('drugid') +']').removeClass('current').find('img').attr('src', 'static/images/selectno.png');
        $(this).detach();
      }
    }, '#tagSelect li');

    
    $('#selectDrugsTabs').find('li').click(function(e){
      e.stopPropagation();

      var i = $(this).index();

      $(this).addClass('current').siblings().removeClass('current');
      $('#tabsContent').find('li').eq(i).addClass('current').siblings().removeClass('current');
    });

    function vilidateform()
    {
    	if ($.trim($("#cartList").attr("data-count") ) == "0") {
			alert("购物车为空，请重新选择商品！");
			return false;
		} 
		else if ($.trim($("#deliverDate").html() ) == ""  || $.trim($("#deliverTime").html() ) == "" ) {
			alert("送货时间为空，请重新选择送货时间！");
			return false;
		} 
		else if ($.trim($("#shopAddress").attr("data-id") ) == "") {
			alert("送货地址为空，请重新选择送货地址！");
			return false;
		}
		return true ;
		
    }
    
    
function selectDrugs(){

	drugDirContent = $('.js-collapsePanel'),
	selectDrugs = $('#tagSelect');

    
    $('#tabsContent').find('[drugid]').click(function(e){
    	e.stopPropagation();
		var selectItem = this ;
		$.each( $('#tabsContent').find('[drugid]') , function(i,item)
		{
			if( selectItem == item ){
				$(this).addClass('current');
				$(this).find('img').attr('src', '${webRoot}/plug-in/weixin_mall/static/images/select.png');
			}else{
				$(this).removeClass('current');
				$(this).find('img').attr('src', '${webRoot}/plug-in/weixin_mall/static/images/selectno.png');
			};
		});

    });
    
    $('#selectDrugSubmit').click(function(e){
		e.stopPropagation();
		var selectTime = '';
		var selectTimeId = '';
		var selectDate = '';
		var openid=document.getElementById("hiddenopenid").value;
		var problemid=document.getElementById("hiddenproblemid").value;
		//处理时间段选择
		drugDirContent.find('[drugid]').each(function(i){
	        if( $(this).hasClass('current') ){
	        	var selectedItem = $(this).find('.js-drugName');
	        	selectTime = selectedItem.html();
	        	selectTimeId = $(this).attr('id');
	        };
        });
      	//处理日期选择
 		$('#tabsContent').find('li').each(function(i){
		    if( $(this).hasClass('current') ){
		      selectDate = $(this).attr('data-date');
		    };
		});
		if(selectTime==''){
			alert("请选择或者点击X退出!");
			return;
		}
		$.ajax({
			type : "POST",
			url : "/jeewx/submitProblemController.do?operator",
			data:{
				    statename : selectTime,
					open_user_id : openid,
					problem_id : problemid
					
			    },
			dataType : "json",
			
			success : function(data) {
				if (data.success == true) {
					window.location.href="/jeewx/submitProblemController.do?detail&problem_id=" + problemid
					+ "&open_user_id=" + openid+"&isoperator="+${isoperator};
				/* dd */
				} else {
					$("#btnPay").removeAttr("disabled");
					alert("添加失败！");
				}
			},
			error : function() {
				alert("网络连接出错！");
			}
		});	
		$('#Selectdrugs').modal('hide');
});
};
	 </script>

</body>
</html>
