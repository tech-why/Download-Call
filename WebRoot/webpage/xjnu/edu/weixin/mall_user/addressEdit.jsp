<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/context/mytags.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<title>地址管理</title>

<meta
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;"
	name="viewport">
<meta content="telephone=no" name="format-detection">
<meta content="email=no" name="format-detection">

<link href="${webRoot }/plug-in/weixin_mall/static/bootstrap/bootstrap.min.css"
	rel="stylesheet">
<link href="${webRoot }/plug-in/weixin_mall/static/style/wxsc.css" rel="stylesheet">
<link href="${webRoot }/plug-in/weixin_mall/static/style/style.css" rel="stylesheet">
<link href="${webRoot }/plug-in/weixin_mall/static/style/address.css"
	rel="stylesheet">
<script src="${webRoot }/plug-in/weixin_mall/static/script/md5.js"></script>
<script src="${webRoot }/plug-in/weixin_mall/static/script/sha1.js"></script>
<script src="${webRoot }/plug-in/weixin_mall/static/jquery/jquery-2.0.3.min.js"
	type="text/javascript"></script>
<script src="${webRoot }/plug-in/weixin_mall/static/bootstrap/bootstrap.min.js"
	type="text/javascript"></script>
<style type="text/css">
.addradio {
	float: left;
	margin: 0px 5px 0 0
}

.addradio img {
	width: 18px;
}

.moren {
	background: url(${webRoot }/plug-in/static/images/radio1.png) no-repeat
		left top;
	background-size: 18px auto;
	padding-left: 25px;
	height: 20px;
	line-height: 20px;
	margin-top: 1px;
}

.moren_current {
	background: url(${webRoot }/plug-in/static/images/radio2.png) no-repeat
		left top;
	background-size: 18px auto;
	padding-left: 25px;
	height: 20px;
	line-height: 20px;
	margin-top: 1px;
}
</style>

<script type="text/javascript">
	document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
		WeixinJSBridge.call('hideOptionMenu');
	});
	function updateaddress(addressid) {
		/* 	alert("1--"+addressid); */
		var hiddenname = document.getElementById("hiddenname" + addressid).value;
		/*alert("2--"+hiddenname);  */
		var address = document.getElementById("hiddenaddress" + addressid).value;
		/*alert("3--"+address);*/
		var hiddenphone = document.getElementById("hiddenphone" + addressid).value;
		var hiddendef = document.getElementById("hiddenaddressdef" + addressid).value;
		/*alert("5--"+hiddenphone);*/
		var hiddenuserid = document.getElementById("hiddenuserid").value;
		/*alert("6--"+hiddenuserid);*/
		document.getElementById("sname").value = hiddenname;
		document.getElementById("saddress").value = address;
		document.getElementById("sphone").value = hiddenphone;
		document.getElementById("addressid").value = addressid;
	
	

	}
	function addsite() {
		document.getElementById("sname").value = "";
		document.getElementById("titlename").innerHTML = "添加新地址";
		document.getElementById("addressid").value = "";
		document.getElementById("saddress").value = "";
		document.getElementById("sphone").value = "";
	}
	function saveaddress() {
		//
	
		var openid=document.getElementById("hiddenopenid").value;
		var buildingid=document.getElementById("buiddingid").value;
		var saddressid = document.getElementById("addressid").value;
		var sname = document.getElementById("sname").value;
		var sphone = document.getElementById("sphone").value;
		var selectbuilding=document.getElementById("openSelectdrugs").value;
		var housecode = document.getElementById("saddress").value;
		if ($.trim(sname) == "") {
			//$("#btnSave").attr("disabled","disabled");
			$("#tipSname").html("姓名不能为空");
			return;
		} else if ($.trim(sname) != "") {
			//$("#btnSave").removeAttr("disabled");
			$("#tipSname").html("");
		}
		if ($.trim(sphone) == "") {
			//$("#btnSave").attr("disabled","disabled");
			$("#tipSphone").html("电话不能为空,且请正确填写电话号");
			return;
		} else if ($.trim(sphone) != "") {
			// $("#btnSave").removeAttr("disabled");
			$("#tipSphone").html("");
		}
		if ($.trim(selectbuilding) == "") {
			//$("#btnSave").attr("disabled","disabled");
			$("#tipsbuild").html("请选择建筑");
			return;
		}
		if ($.trim(housecode) == "") {
			//$("#btnSave").attr("disabled","disabled");
			$("#tipSaddress").html("地址不能为空");
		} else if ($.trim(saddress) != "") {
			// $("#btnSave").removeAttr("disabled");
			$("#tipSaddress").html("");
		}
		$.ajax({
			type : "POST",
			url : "/jeewx/addressController.do?saveaddress",
			dataType : "json",
			data : "addressid=" +saddressid + "&builddingid=" +buildingid
					+ "&contact=" + sname + "&mobilenumber=" + sphone
					+ "&housecode="+housecode+"&open_user_id=" + openid ,
			success : function(data) {
				var addresssave = data.success;
				if(addresssave==true){
					if($.trim(saddressid)==""){
						alert("保存成功！");
					}else{
					alert("修改地址信息成功");
					}
					window.location.href='addressController.do?display&open_user_id=${open_user_id }&app_code=${app_code}&shopId=${shopId }';
					
				}
			},
			error : function() {
				alert("网络连接出错！");
			}
		});
		
		
	}
	
	function deladdress() {
		var saddressid = document.getElementById("addressid").value;
		if (confirm("确定要删除吗？")) {
			$
					.ajax({
						type : "POST",
						url : "/haoyao/wchatUserBinding.do?method=deladdress&saddressid="
								+ saddressid + "",
						dataType : "json",
						success : function(data) {
							if (data.update == "true") {
								$("#btnPay").attr("disabled", "disabled");
								alert("删除成功！");
								$("#addressliid" + saddressid + "").remove();
								$('#editAddresss').modal('hide');
								$('.modal-backdrop').detach();
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
	function deladd(addid){
		var saddressid=addid;
		var openid=document.getElementById("hiddenopenid").value;
		if(saddressid==""){
			alert("当前为添加状态不可删除");
			return;
		}

		if(confirm("确定要删除吗？")){
		$.ajax( {   
		     type : "POST",   
		     url : "/jeewx/addressController.do?del&addressid="+saddressid+"&addressid="+openid,   
		     dataType: "json",   
		     success : function(data) { 
		         if(data.success==true){ 
		        	 $("#btnPay").attr("disabled","disabled");
		        	 alert("删除成功！");
		        	 window.location.href='addressController.do?display&open_user_id=${open_user_id }&app_code=${app_code}&shopId=${shopId }';
		        
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
	
	function cancelEdit(){
		
		if(confirm("确定要取消吗？")){
		 
			window.location.href='addressController.do?display&open_user_id=${open_user_id }&app_code=${app_code}&shopId=${shopId }';
		}
	}
	
</script>
<script type="text/javascript">
	function getAddressSign() {
		var app_id = "wxab144e9dd399684a";
		var url = "http://w.ehaoyao.com/haoyao/wchatUserBinding.do?method=addressManage";
		var time_stamp = "1384841012";
		var nonce_str = "123456";
		var accesstoken = document.getElementById("accesstoken").value;
		var state = document.getElementById("state").value;
		var code = document.getElementById("code").value;
		url = url + "&code=" + code + "&state=" + state;
		//第一步，对所有需要传入的参数加上appkey作一次key＝value字典序的排序
		var keyvaluestring = "accesstoken=" + accesstoken + "&appid=" + app_id
				+ "&noncestr=" + nonce_str + "&timestamp=" + time_stamp
				+ "&url=" + url;
		var sign = CryptoJS.SHA1(keyvaluestring).toString();
		return sign;
	}
	document
			.addEventListener(
					'WeixinJSBridgeReady',
					function onBridgeReady() {
						$("#changeAddress")
								.click(
										function() {
											WeixinJSBridge
													.invoke(
															'editAddress',
															{
																"appId" : "wxab144e9dd399684a",
																"scope" : "jsapi_address",
																"signType" : "sha1",
																"addrSign" : getAddressSign()
																		+ "",
																"timeStamp" : "1384841012",
																"nonceStr" : "123456"
															},
															function(res) {
																/* alert(res.err_msg); */
																if (res.err_msg == "edit_address:ok") {

																	//若res 中所带的返回值不为空，则表示用户选择该返回值作为收货地
																	//址。否则若返回空，则表示用户取消了这一次编辑收货地址。

																	var proviceFirstStageName = res.proviceFirstStageName;
																	var addressCitySecondStageName = res.addressCitySecondStageName;
																	var addressCountiesThirdStageName = res.addressCountiesThirdStageName;
																	var addressDetailInfo = res.addressDetailInfo;
																	var telNumber = res.telNumber;
																	var userName = res.userName;
																	var addressPostalCode = res.addressPostalCode;

																	var suserid = document
																			.getElementById("hiddenuserid").value;
																	var openid = document
																			.getElementById("hiddenopenid").value;
																	$
																			.ajax({
																				type : "POST",
																				url : "/haoyao/wchatUserBinding.do?method=wxaddress&suserid="
																						+ suserid
																						+ "&openid="
																						+ openid
																						+ "",
																				data : "suserid="
																						+ suserid
																						+ "&openid="
																						+ openid
																						+ "&proviceFirstStageName="
																						+ proviceFirstStageName
																						+ "&addressCitySecondStageName="
																						+ addressCitySecondStageName
																						+ "&addressCountiesThirdStageName="
																						+ addressCountiesThirdStageName
																						+ "&addressDetailInfo="
																						+ addressDetailInfo
																						+ "&telNumber="
																						+ telNumber
																						+ "&userName="
																						+ userName
																						+ "&addressPostalCode="
																						+ addressPostalCode,
																				dataType : "json",
																				success : function(
																						data) {
																					/* alert(data.update); */
																					if (data.update != null) {
																						$(
																								"#addressUl")
																								.append(
																										data.update);
																						$(
																								"#btnPay")
																								.attr(
																										"disabled",
																										"disabled");

																					} else if (data.error == "addressnull") {
																						$(
																								"#btnPay")
																								.removeAttr(
																										"disabled");
																						alert("获取不到您的微信地址，请添加新地址。");
																					} else {
																						$(
																								"#btnPay")
																								.removeAttr(
																										"disabled");
																						alert("获取不到您的微信地址，请添加新地址。");
																					}
																				},
																				error : function() {
																					alert("网络连接出错！");
																				}
																			});
																}
															});
										});

					});
</script>

</head>
<body style="color: rgb(52, 73, 94); background: rgb(236, 240, 241);"
	class="">

	<input type="hidden" id="state" value="">
	<!-- state -->
	<input type="hidden" id="code" value="">
	<!-- code -->
	<input type="hidden" id="hiddenopenid" name="message" value="${open_user_id }">
	<input type="hidden" id="buiddingid" name="buiddingid" value="${tbaddress.getBuilddingId() }">
	<input type="hidden" id="hiddenuserid" name="hiddenuserid" value="">
	<div class="container">
		<div class="container">
			<div class="modal-body">
				<div class="form-group">
					<input type="hidden" id="addressid" name="addressid" value="${tbaddress.getId() }">
					<input type="hidden" id="dizhixuhao" name="dizhixuhao" value="">
					<input type="hidden" id="userid" name="userid" value=""> <input
						type="hidden" id="addressdef" name="addressdef" value="">
					<label>收货人姓名</label> <input type="text" id="sname" name="sname" value="${tbaddress.getContact() }"
						class="form-control"> <span id="tipSname"
						class="requiredSpan" style="color: red;"></span>
				</div>
				<div class="form-group">
					<label>收货人电话</label> <input type="text" id="sphone" name="sphone" value="${tbaddress.getMobileNumber() }"
						class="form-control"> <span id="tipSphone"
						class="requiredSpan" style="color: red;"></span>
				</div>
				<div class="form-group">
					<label>楼号</label> <input type="text" id="openSelectdrugs"
						name="openSelectdrugs" class="form-control" value="${tbbuilding.getBuilddingName() }"> <span
						id="tipsbuild" class="requiredSpan" style="color: red;"></span>
				</div>
				<div class="form-group">
					<label>地址</label>
					<textarea class="form-control" rows="3" style="text-align: left;"
						id="saddress" name="saddress" >${tbaddress.getHouseCode() }</textarea>
					<span id="tipSaddress" class="requiredSpan" style="color: red;"></span>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal"
					style="border: 1px solid #29b572; color: #29b572" onclick="cancelEdit()">取消</button>
				<button id="btndelete"type="button" class="btn btn-o2o2" onclick="deladd('${tbaddress.getId() }')">删除此地址</button>
				<button type="button" class="btn btn-o2o" onclick="saveaddress()"
					id="btnSave">保存</button>
			</div>
		</div>


		<!--选择药品-->
		<div id="Selectdrugs" class="modal fade" tabindex="-1" role="dialog"
			aria-hidden="true" style="display: none;">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header" style="border-bottom:2px solid #09b173">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
						<h4 class="modal-title">
							<i class="icon-uniE614"
								style="color:#41c27f; padding-right:5px; vertical-align:-2px; font-size:20px;"></i>选择建筑
						</h4>
					</div>
					<div class="modal-body" style="padding:10px 0 0 0">




						<div id="tabsContent" class="panel-group">

							<div class="panel panel-default panelA">

								<div class="js-collapsePanel "
									style="overflow: auto; height: 200px;">
									<c:forEach var="building" items="${buildinglist }">
										<div class="panel-heading js-collapse"
											style="background:#FFF; border-bottom:1px solid #e6e5e5;">
											<h4 class="panel-title">
												<a style="text-decoration:none">
													<p drugid="${building.getId()}">
														<span class="select pull-right"><img
															src="${webRoot }/plug-in/weixin_mall/static/images/selectno.png"></span><span
															class="js-drugName">${building.getBuilddingName() }</span>
													</p>
												</a>
											</h4>
										</div>
									</c:forEach>

								</div>

							</div>

						</div>

						<div class="modal-footer" style="margin-top:0">
							<button id="selectDrugSubmit" type="button"
								class="btn btn-o2o btn-block">确定</button>
						</div>
					</div>
				</div>
			</div>



			<script>
				$('.js-setDefault').click(
						function(e) {
							e.stopPropagation();

							$(this).addClass('moren_current').parents('.panel')
									.siblings().find('.js-setDefault')
									.removeClass('moren_current');
						});
				function change_pic(id) {
					$
							.ajax({
								type : "POST",
								url : "/haoyao/wchatUserBinding.do?method=updatedefault&addressid="
										+ id + "",
								dataType : "json",
								success : function(data) {
									if (data.update == "false") {
										alert("设置失败！");
									}
								},
								error : function() {
									alert("网络连接出错！");
								}
							});
				}

				selectDrugs();

				$('#openSelectdrugs').click(function(e) {
					e.stopPropagation();

					$('#Selectdrugs').modal('show');
				});

				function selectDrugs() {

					drugDirContent = $('.js-collapsePanel'),
							selectDrugs = $('#tagSelect');

					$('#tabsContent')
							.find('[drugid]')
							.click(
									function(e) {
										e.stopPropagation();
										var selectItem = this;
										$
												.each(
														$('#tabsContent').find(
																'[drugid]'),
														function(i, item) {
															if (selectItem == item) {
																$(this)
																		.addClass(
																				'current');
																$(this)
																		.find(
																				'img')
																		.attr(
																				'src',
																				'${webRoot }/plug-in/weixin_mall/static/images/select.png');
															} else {
																$(this)
																		.removeClass(
																				'current');
																$(this)
																		.find(
																				'img')
																		.attr(
																				'src',
																				'${webRoot }/plug-in/weixin_mall/static/images/selectno.png');
															}
															;
														});

									});
					$('#selectDrugSubmit').click(
							function(e) {
								e.stopPropagation();

								var selectHtml = '';
								var selectId = '';

								drugDirContent.find('[drugid]').each(
										function(i) {
											if ($(this).hasClass('current')) {
												selectId = $(this).attr(
														'drugid');
									
												selectHtml = $(this).find(
														'.js-drugName').html();
											}
											;
										});
								$('#openSelectdrugs').val(selectHtml);
								$('#buiddingid').val(selectId);
								$('#Selectdrugs').modal('hide');
							});
				};
			</script>
</body>
</html>