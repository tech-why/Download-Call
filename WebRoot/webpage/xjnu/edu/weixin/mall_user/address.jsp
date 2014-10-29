<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/context/mytags.jsp"%>
<html><head><meta charset="utf-8">
<title>地址管理</title>

<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport">
<meta content="telephone=no" name="format-detection">
<meta content="email=no" name="format-detection">

<link href="${webRoot }/plug-in/static/style/bootstrap.css" rel="stylesheet">
<link href="${webRoot }/plug-in/static/style/wxsc.css" rel="stylesheet">
<link href="${webRoot }/plug-in/static/style/style.css" rel="stylesheet">
	 <script src="${webRoot }/plug-in/static/script/md5.js"></script>
	<script src="${webRoot }/plug-in/static/script/sha1.js"></script>
	<script src="${webRoot }/plug-in/static/jquery/jquery-2.0.3.min.js" type="text/javascript"></script>	
	<script src="${webRoot }/plug-in/static/bootstrap/bootstrap.min.js" type="text/javascript"></script>
<style type="text/css">
.addradio { float:left; margin:0px 5px 0 0}
.addradio img{ width:18px; }
.moren{ background:url(${webRoot }/plug-in/static/images/radio1.png) no-repeat left top; background-size:18px auto; padding-left:25px; height:20px; line-height:20px; margin-top:1px;}
.moren_current{ background:url(${webRoot }/plug-in/static/images/radio2.png) no-repeat left top; background-size:18px auto;padding-left:25px; height:20px; line-height:20px; margin-top:1px; }
</style>

<script type="text/javascript">
document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
	WeixinJSBridge.call('hideOptionMenu');
	});
function updateaddress(addressid){
/* 	alert("1--"+addressid); */
 	var hiddenname=document.getElementById("hiddenname"+addressid).value;
 	/*alert("2--"+hiddenname);  */
	var address=document.getElementById("hiddenaddress"+addressid).value;
	/*alert("3--"+address);*/
	var hiddenphone=document.getElementById("hiddenphone"+addressid).value;
	var hiddendef=document.getElementById("hiddenaddressdef"+addressid).value;
	/*alert("5--"+hiddenphone);*/
	var hiddenuserid=document.getElementById("hiddenuserid").value;
	/*alert("6--"+hiddenuserid);*/
	document.getElementById("sname").value=hiddenname;
	document.getElementById("saddress").value=address;
	document.getElementById("sphone").value=hiddenphone;
	document.getElementById("addressid").value=addressid;
	document.getElementById("userid").value=hiddenuserid;
	document.getElementById("addressdef").value=hiddendef;
	
}
function addsite(){
	document.getElementById("sname").value="";
	document.getElementById("titlename").innerHTML="添加新地址";
	document.getElementById("addressid").value="";
	document.getElementById("saddress").value="";
	document.getElementById("sphone").value="";
}

	function saveaddress(){
	var sname=document.getElementById("sname").value;
	var saddress=document.getElementById("saddress").value;
	var sphone=document.getElementById("sphone").value;
	var saddressid=document.getElementById("addressid").value;
	var suserid=document.getElementById("hiddenuserid").value;
	var dizhiss=document.getElementById("dizhixuhao").value; 
	var addressdef=document.getElementById("addressdef").value;
	if ($.trim(sname) == "") {
		$("#btnSave").attr("disabled","disabled");
		$("#tipSname").html("姓名不能为空");
		
	}else if($.trim(sname) != ""){
		 $("#btnSave").removeAttr("disabled");
		$("#tipSname").html("");
	
	}
	if ($.trim(sphone) == "") {
		$("#btnSave").attr("disabled","disabled");
		$("#tipSphone").html("电话不能为空,且请正确填写电话号");

	}else if($.trim(sphone) != ""){
		 $("#btnSave").removeAttr("disabled");
		$("#tipSphone").html("");
		
	}
	if ($.trim(saddress) == "") {
		$("#btnSave").attr("disabled","disabled");
		$("#tipSaddress").html("地址不能为空");
	
	}else if($.trim(saddress) != ""){
		 $("#btnSave").removeAttr("disabled");
		$("#tipSaddress").html("");
	}
	
		$.ajax( {   
		     type : "POST",   
		     url : "/haoyao/wchatUserBinding.do?method=addAddress",   
		     dataType: "json", 
		     data:"sname="+sname+"&saddress="+saddress+"&sphone="+sphone+"&saddressid="+saddressid+"&suserid="+suserid+"",
		     success : function(data) {
		    	  var addresssave=data.updates;
		    	  var aa=saddressid;
		    	  if(aa==null || aa==''){
		    		  $("#btnPay").attr("disabled","disabled");
		 	        	 $("#addressUl").append(addresssave);
			        	 alert("保存成功！");
			        	 $('#editAddresss').modal('hide');
			        	 $('.modal-backdrop').detach();
			       		}else if(data.update=="shibai"){
					        	 $("#btnPay").removeAttr("disabled");
					             alert("保存失败！");   
					         }else if(data.update=="xiugai"){
					        	$("#addressliid"+saddressid+"").html("");
						        /* $("#addressliid"+saddressid+"").html("<div class=\"panel-body\"style=\"padding-bottom: 0px; border-bottom: 1px solid #e6e5e5;\"><div class=\"media\" style=\"padding-bottom: 5px; margin-top: 0px\"><div class=\"media\"  style=\" padding-bottom:5px; margin-top:0px\"><p>收货人姓名："+sname+"</p> <p>手&nbsp;&nbsp;机&nbsp;&nbsp;号&nbsp;&nbsp;&nbsp;："+sphone+"</p><p>送货地址&nbsp;&nbsp;&nbsp;："+saddress+"</p><input type=\"hidden\" id=\"hiddenname"+saddressid+"\" name=\"hiddenname"+saddressid+"\" value=\""+sname+"\"><input type=\"hidden\" id=\"hiddenphone"+saddressid+"\" name=\"hiddenphone"+saddressid+"\" value=\""+sphone+"\"><input type=\"hidden\" id=\"hiddenaddress"+saddressid+"\" name=\"hiddenaddress"+saddressid+"\" value=\""+saddress+"\"></div></div><div class=\"panel-body\"style=\"padding-bottom: 15px; padding-top: 10\"><div class=\"media\" ><span class=\"pull-right\"><i class=\"icon-uniE602 green\"style=\"font-size: 16px; vertical-align: -2px; padding-right: 5px;\" onclick=\"deladd("+saddressid+")\"></i>删除</span><span onClick=\"updateaddress("+saddressid+")\" class=\"pull-right\" data-toggle=\"modal\" data-target=\"#edit\"><i class=\"icon-uniE623 green\" style=\"font-size:16px;vertical-align:-2px; padding-right:5px;\"></i>编辑</span> </div></div>"); */
								 $("#addressliid"+saddressid+"").append("<div id=\"updateaddressid"+saddressid+"\"><div class=\"panel-body\"style=\"padding-bottom: 0px; border-bottom: 1px solid #e6e5e5;\"><div class=\"media\" style=\"padding-bottom: 5px; margin-top: 0px\"><p>收货人姓名："+sname+"</p> <p>手&nbsp;&nbsp;机&nbsp;&nbsp;号&nbsp;&nbsp;&nbsp;："+sphone+"</p><p>送货地址&nbsp;&nbsp;&nbsp;："+saddress+"</p><input type=\"hidden\" id=\"hiddenname"+saddressid+"\" name=\"hiddenname"+saddressid+"\"value=\""+sname+"\"><input type=\"hidden\" id=\"hiddenphone"+saddressid+"\" name=\"hiddenphone"+saddressid+"\"value=\""+sphone+"\"><input type=\"hidden\" id=\"hiddenaddress"+saddressid+"\" name=\"hiddenaddress"+saddressid+"\"value=\""+saddress+"\"></div></div><div class=\"panel-body\"style=\"padding-bottom: 15px; padding-top: 10\"><div class=\"media\"><span class=\"pull-right\"><i class=\"icon-uniE602 green\"style=\"font-size: 16px; vertical-align: -2px; padding-right: 5px;\"onclick=\"deladd("+saddressid+")\"></i>删除</span><span onclick=\"updateaddress("+saddressid+")\" class=\"pull-right\" data-toggle=\"modal\" data-target=\"#editAddresss\"><i class=\"icon-uniE623 green\" style=\"font-size: 16px; vertical-align: -2px; padding-right: 5px;\"></i>编辑</span></div></div></div>");
 								alert("保存成功！");
			        	 		$('#editAddresss').modal('hide');
			        	 		$('.modal-backdrop').detach();
		        		 }
		         
		    	 },   
		     		error :function(){
		        	 alert("网络连接出错！");
		     }   
		 }); 
}
function deladdress(){
	var saddressid=document.getElementById("addressid").value;
	 if(confirm("确定要删除吗？")){
	$.ajax( {   
	     type : "POST",   
	     url : "/haoyao/wchatUserBinding.do?method=deladdress&saddressid="+saddressid+"",   
	     dataType: "json",   
	     success : function(data) { 
	         if(data.update == "true"){ 
	        	 $("#btnPay").attr("disabled","disabled");
	        	 alert("删除成功！");
	        	$("#addressliid"+saddressid+"").remove();
	        	$('#editAddresss').modal('hide');
	        	$('.modal-backdrop').detach();
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
function deladd(addid){
	var saddressid=addid;
	var openid=document.getElementById("hiddenopenid").value;

	if(confirm("确定要删除吗？")){
	$.ajax( {   
	     type : "POST",   
	     url : "/jeewx/addressController.do?del&addressid="+saddressid+"&addressid="+openid,   
	     dataType: "json",   
	     success : function(data) { 
	         if(data.success==true){ 
	        	 $("#btnPay").attr("disabled","disabled");
	        	 alert("删除成功！");
	        	$("#"+saddressid).remove();
	        
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
function chooseaddress(addid){
	var saddressid=addid;
	var openid=document.getElementById("hiddenopenid").value;
	var chooseaddressid=document.getElementById("chooseaddressid").value;
	var oldchoose=document.getElementById(chooseaddressid);
	var newchoose=document.getElementById(saddressid);
	

	/* if(confirm("确定要选择吗？")){ */
	$.ajax( {   
	     type : "POST",   
	     url : "/jeewx/addressController.do?chooseaddress&addressid="+saddressid+"&addressid="+openid,   
	     dataType: "json",   
	     success : function(data) { 
	         if(data.success==true){ 
	        	 if(oldchoose!=null){
	        		 oldchoose .style.cssText='border:hidden;margin-top:15px';
	        	 }
	        	
	        	 newchoose.style.cssText='border:#F00 3px solid;margin-top:15px';
	        	 $("#chooseaddressid").val(saddressid);
	        	 
	        	 $("#btnPay").attr("disabled","disabled");
	     
	 /*        	$("#"+saddressid).remove(); */
	        
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
	/* } */
}



</script>
<script type="text/javascript">
function getAddressSign()
{
    var app_id = "wxab144e9dd399684a";
    var url = "http://w.ehaoyao.com/haoyao/wchatUserBinding.do?method=addressManage";
	var time_stamp = "1384841012";
    var nonce_str = "123456";
	var accesstoken=document.getElementById("accesstoken").value;
	var state=document.getElementById("state").value;
	var code=document.getElementById("code").value;
	url = url+"&code="+code+"&state="+state;
    //第一步，对所有需要传入的参数加上appkey作一次key＝value字典序的排序
   var keyvaluestring = "accesstoken=" + accesstoken + "&appid=" + app_id + "&noncestr=" + nonce_str+ "&timestamp=" + time_stamp + "&url=" + url;
    var sign = CryptoJS.SHA1(keyvaluestring).toString();
    return sign;
}
document.addEventListener('WeixinJSBridgeReady', function onBridgeReady(){
	$("#changeAddress").click(function(){
		WeixinJSBridge.invoke('editAddress',{
			"appId" : "wxab144e9dd399684a",
			"scope" : "jsapi_address",
			"signType" : "sha1",
			"addrSign" : getAddressSign()+"",
			"timeStamp" : "1384841012",
			"nonceStr" : "123456"
			},function(res){
				/* alert(res.err_msg); */
				if(res.err_msg=="edit_address:ok"){
							
					//若res 中所带的返回值不为空，则表示用户选择该返回值作为收货地
					//址。否则若返回空，则表示用户取消了这一次编辑收货地址。
					
					var proviceFirstStageName =res.proviceFirstStageName;
					var addressCitySecondStageName=res.addressCitySecondStageName;
					var addressCountiesThirdStageName=res.addressCountiesThirdStageName;
					var addressDetailInfo=res.addressDetailInfo;
					var telNumber=res.telNumber;
					var userName=res.userName;
					var addressPostalCode =res.addressPostalCode;

					var suserid=document.getElementById("hiddenuserid").value;
					var openid=document.getElementById("hiddenopenid").value;
					$.ajax( {   
					     type : "POST",
					     url : "/haoyao/wchatUserBinding.do?method=wxaddress&suserid="+suserid+"&openid="+openid+"",   
					     data:"suserid="+suserid+"&openid="+openid+"&proviceFirstStageName="+proviceFirstStageName+"&addressCitySecondStageName="+addressCitySecondStageName+"&addressCountiesThirdStageName="+addressCountiesThirdStageName+"&addressDetailInfo="+addressDetailInfo+"&telNumber="+telNumber+"&userName="+userName+"&addressPostalCode="+addressPostalCode,
					   	 dataType: "json",   
					     success : function(data) { 
					    	 /* alert(data.update); */
					         if(data.update != null){ 
					        	 $("#addressUl").append(data.update);
					        	 $("#btnPay").attr("disabled","disabled");
					        	 
							}   
					         else
					         if(data.error=="addressnull")
					         {
					        	 $("#btnPay").removeAttr("disabled");
					             alert("获取不到您的微信地址，请添加新地址。");
					         }else{
					        	 $("#btnPay").removeAttr("disabled");
					             alert("获取不到您的微信地址，请添加新地址。");
					         }
					     },   
					     error :function(){
					         alert("网络连接出错！");
					     }   
					 });
				}
			});
	});
	
	});
</script>
</head>
<body style="color: rgb(52, 73, 94); background: rgb(236, 240, 241);" class="">
<input type="hidden" id="accesstoken" value="">
		<input type="hidden" id="state" value=""><!-- state -->
		<input type="hidden" id="code" value=""><!-- code -->

	<input type="hidden" id="hiddenopenid" name="hiddenopenid" value="${open_user_id }">
	<input type="hidden" id="returnUrl" name="returnUrl" value="${returnUrl }">
	<input type="hidden" id="chooseaddressid" name="chooseaddressid" value="${chooseTbEntity.getId() }">
	<div class="container">
	<div id="addressUl">
		
<input type="hidden" id="hiddenaddressdef2408" name="hiddenaddressdef2408" value="2">
<c:forEach var="address" items="${addresslist }">
<c:if test="${address.getIsChoose()=='是' }">
<div id="${address.getId() }" class="panel" style="margin-top:15px;border:#F00 3px solid;" >
	
		<div id="updateaddress${address.getId()}">
			<div class="panel-body" style="padding-bottom:0px;border-bottom: 1px solid #e6e5e5;">
				<div class="media"  style=" padding-bottom:5px; margin-top:0px">
					<p>收货人姓名：${address.getContact() }</p>
					<p>手&nbsp;&nbsp;机&nbsp;&nbsp;号&nbsp;&nbsp;&nbsp;：${address.getMobileNumber() }</p>
					<p>收货地址&nbsp;&nbsp;&nbsp;：${address.getSchoolname() }${address.getBuilddingname() }${address.getHouseCode() }</p>
					<input type="hidden" id="hiddenname${address.getId() }" name="hiddenname2408" value="：${address.getContact() }">
					<input type="hidden" id="hiddenphone${address.getId() }" name="hiddenphone2408" value="${address.getMobileNumber() }">
					<input type="hidden" id="hiddenaddress${address.getId() }" name="hiddenaddress2408" value="${address.getMobileNumber() }">
				</div>
			</div>
			<div class="panel-body" style="padding-bottom:15px; padding-top:10">
				<div class="media" >
					<span class="pull-right">
						<i class="icon-uniE602 green" style="font-size:16px;vertical-align:-2px; padding-right:5px;" onclick="deladd('${address.getId()}')">
						</i>删除
					</span>
					<span onclick="window.location.href='addressController.do?addorupdate&open_user_id=${open_user_id }&addressid=${address.getId()}&app_code=${app_code}&shopId=${shopId }'" class="pull-right" data-toggle="modal" data-target="#editAddresss">
						<i class="icon-uniE623 green" style="font-size:16px;vertical-align:-2px; padding-right:5px;"></i>编辑
					</span>
					<span onclick="chooseaddress('${address.getId()}')" class="pull-right" data-toggle="modal" data-target="#editAddresss">
						<i class="icon-uniE623 green" style="font-size:16px;vertical-align:-2px; padding-right:5px;"></i>选择地址
					</span>
				</div>
			</div>
		</div>
	</div>
</c:if>
<c:if test="${address.getIsChoose()=='否' }">
	<div id="${address.getId() }" class="panel" style="margin-top:15px;">
	
		<div id="updateaddress${address.getId()}">
			<div class="panel-body" style="padding-bottom:0px;border-bottom: 1px solid #e6e5e5;">
				<div class="media"  style=" padding-bottom:5px; margin-top:0px">
					<p>收货人姓名：${address.getContact() }</p>
					<p>手&nbsp;&nbsp;机&nbsp;&nbsp;号&nbsp;&nbsp;&nbsp;：${address.getMobileNumber() }</p>
					<p>收货地址&nbsp;&nbsp;&nbsp;：${address.getSchoolname() }${address.getBuilddingname() }${address.getHouseCode() }</p>
					<input type="hidden" id="hiddenname${address.getId() }" name="hiddenname2408" value="：${address.getContact() }">
					<input type="hidden" id="hiddenphone${address.getId() }" name="hiddenphone2408" value="${address.getMobileNumber() }">
					<input type="hidden" id="hiddenaddress${address.getId() }" name="hiddenaddress2408" value="${address.getMobileNumber() }">
				</div>
			</div>
			<div class="panel-body" style="padding-bottom:15px; padding-top:10">
				<div class="media" >
					<span class="pull-right">
						<i class="icon-uniE602 green" style="font-size:16px;vertical-align:-2px; padding-right:5px;" onclick="deladd('${address.getId()}')">
						</i>删除
					</span>
					<span onclick="window.location.href='addressController.do?addorupdate&open_user_id=${open_user_id }&addressid=${address.getId()}&app_code=${app_code}&shopId=${shopId }'" class="pull-right" data-toggle="modal" data-target="#editAddresss">
						<i class="icon-uniE623 green" style="font-size:16px;vertical-align:-2px; padding-right:5px;"></i>编辑
					</span>
					<span onclick="chooseaddress('${address.getId()}')" class="pull-right" data-toggle="modal" data-target="#editAddresss">
						<i class="icon-uniE623 green" style="font-size:16px;vertical-align:-2px; padding-right:5px;"></i>选择地址
					</span>
				</div>
			</div>
		</div>
	</div>
	</c:if>
	</c:forEach>
		</div>
		<ul class="money-btngroup" style="margin: 40px 0 50px 0;">
			
			<li style="padding-left: 3px;">
				<button type="button" class="btn btn-o2o btn-block" data-toggle="modal" data-target="#editAddresss" onclick="window.location.href='addressController.do?addorupdate&open_user_id=${open_user_id }&app_code=${app_code}&shopId=${shopId }'">添加新地址</button>
			</li>
			<li style="padding-left: 3px;">
				<button type="button" class="btn btn-o2o btn-block" data-toggle="modal" data-target="#editAddresss" onclick="window.location.href='${returnUrl }'">返回</button>
			</li>
		</ul>
	</div>




	
	<script>
	$('.js-setDefault').click(function(e){
		e.stopPropagation();

		$(this).addClass('moren_current').parents('.panel').siblings().
			find('.js-setDefault').removeClass('moren_current');
	});
	function change_pic(id){
		$.ajax( {   
		    type : "POST",   
		    url : "/haoyao/wchatUserBinding.do?method=updatedefault&addressid="+id+"",   
		    dataType: "json",   
		    success : function(data) { 
		        if(data.update == "false"){ 
		             alert("设置失败！");
		        }
		    },   
		    error :function(){
		        alert("网络连接出错！");
		    }   
		});
	}
	</script>

</body></html>