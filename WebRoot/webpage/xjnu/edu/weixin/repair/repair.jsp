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
		<title style="font:30px #ffffff">故障报修</title>
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

<body style="background:#ecf0f1; padding-top:10px; color:#34495e">



		<input type="hidden" id="openUserId"  value="${open_user_id}">
		<input type="hidden" id="shopId"  value="${shopId}">
		<input type="hidden" id="addressId" value="${address.id}"/>
		<input type="hidden" id="timeId" value=""/>
		<input type="hidden" id="todayOrTomorrow" value=""/>
		
		
	<nav>
		<!-- <div id="pageBack" class="pull-left" style="width:50px; height:50px; text-align:center;">
			<span class="glyphicon glyphicon-circle-arrow-left" style="width:50px; height:50px; line-height:50px; color:#FFF; font-size:24px;" onclick="javascript:history.go(-1);"></span>
		</div>  -->
		<div class="pull-right" style="position:relative; width:50px; height:50px; text-align:center; z-index:2;">
			<a href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxab144e9dd399684a&redirect_uri=http://w.ehaoyao.com/haoyao/index_menu/cart.html?response_type=code&scope=snsapi_base&state=1#wechat_redirect">
			<div id="shoppingCart" style="display:none; position:absolute; top:5px; right:5px; width:20px; height:20px; line-height:20px; background:red; border-radius:20px; text-align:center; color:#FFF;" onClick="javascript:history.go(-1);"><strong>0</strong></div>
			<span id="cartIcon" class="glyphicon hys-icon hys-icon-cart" style="width:50px; height:50px; line-height:50px; color:#FFF; font-size:24px;"></span>
			</a>
		</div>
	</nav>
	
	<div id = "cartList" data-count="${cartListLength}" class="container" style="padding-left:0px; padding-right:0px;">	

		 <div class="panel listaddr panel-default" id="li-283">
		 
		 	<div class="panel-heading">
		 	
                	故障地址
                    
            </div>
                <div class="panel-body">
                    <address id="shopAddress" data-id = "address.id">
					
                      <h5><span class="text-muted">地 址：</span><input type= "text" ></input> </h5>
                      <h5><span class="text-muted">姓 名：</span><input type= "text" ></input> </h5>
                      <h5><span class="text-muted">电 话：</span><input type= "text" ></input> </h5>
                    </address>            
                </div>
           </div>    
		<div  id="errorDiv" style="display:none" align="center">
			<p>网络故障，请点击<a href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxab144e9dd399684a&redirect_uri=http://w.ehaoyao.com/haoyao/wchatTrade.do?method=goPay&response_type=code&scope=snsapi_base&state=+14239+#wechat_redirect">重新加载</a>地址</p>
		</div>
		
		<div class="panel listaddr panel-default" id="li-283">
		 
		 	<div class="panel-heading">
		 	
                	故障类型
                    <a class="edit-addr" href="#" id = "openSelectdrugs"><i class="fa fa-check-square-o"> 选择</i></a> 
            </div>
			<div class="panel-body">
				<address>
				
				  <h5><span class="text-muted">类型</span> <span id= "deliverTime"> </span> </h5>
				</address>            
			</div>
	   </div>    
        
        
        <div class="panel">
          <div class="panel-heading">
            <div class="bp-switch current pull-right" id="kaiguan" >
				 <div class="bp-switch-bar"></div>
		    </div>
		    <span style="font-size:16px">故障描述</span>
          </div>
          <div id="invoicePanel" class="panel-body" style="padding-top:15px; position:relative">
						<textarea class="form-control" rows="3" style="text-align: left;" id="userMessage" name="userMessage"></textarea>
						<span id="tipSaddress" class="requiredSpan" style="color: red;"></span>
          </div>
          
        </div>
      
       <p style="padding:0 15px;">   <button id="submitOrderButton" type="button" class="btn btn-o2o  btn-block"  id="zhifu1" onClick="aa(1)" style="margin-bottom:15px;"  >报修故障</button></p>
		
	</div>
	
<!--选择药品-->
  <div id="Selectdrugs" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" style="display: none;">
	    <div class="modal-dialog">
		    <div class="modal-content">
		        <div class="modal-header" style="border-bottom:2px solid #09b173">
		        	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		        	<h4 class="modal-title"><i class="icon-uniE614" style="color:#41c27f; padding-right:5px; vertical-align:-2px; font-size:20px;"></i>选择故障类型</h4>
		        </div>
	          <div class="modal-body" style="padding:10px 0 0 0">
		        	

               <!-- Tab panes -->
        <div class="tabs-content">
					<ul id="tabsContent">
					 	<li style="padding:20px;" class="current" data-date = "today">
                <div class="panel-group">
                <div class="panel panel-default panelA">
                  
                  <div class="js-collapsePanel ">
					   
					   
						   <div  class="panel-heading js-collapse" style="background:#FFF; border-bottom:1px solid #e6e5e5;">
							<h4 class="panel-title">
							  <a style="text-decoration:none">
								<p id="${workTime.id}" drugid="21">
									<span class="select pull-right">
										<img src="${webRoot}/plug-in/weixin_mall/static/images/selectno.png">
									</span>
									
									<span class="js-drugName">有线故障</span>
									
								</p>
							  </a>
							</h4>
						  </div>
						  
						  <div  class="panel-heading js-collapse" style="background:#FFF; border-bottom:1px solid #e6e5e5;">
							<h4 class="panel-title">
							  <a style="text-decoration:none">
								<p id="${workTime.id}" drugid="21">
									<span class="select pull-right">
										<img src="${webRoot}/plug-in/weixin_mall/static/images/selectno.png">
									</span>
									
									<span class="js-drugName">无线故障</span>
									
								</p>
							  </a>
							</h4>
						  </div>
						  
						  <div  class="panel-heading js-collapse" style="background:#FFF; border-bottom:1px solid #e6e5e5;">
							<h4 class="panel-title">
							  <a style="text-decoration:none">
								<p id="${workTime.id}" drugid="21">
									<span class="select pull-right">
										<img src="${webRoot}/plug-in/weixin_mall/static/images/selectno.png">
									</span>
									
									<span class="js-drugName">硬件故障</span>
									
								</p>
							  </a>
							</h4>
						  </div>
						  
						  <div  class="panel-heading js-collapse" style="background:#FFF; border-bottom:1px solid #e6e5e5;">
							<h4 class="panel-title">
							  <a style="text-decoration:none">
								<p id="${workTime.id}" drugid="21">
									<span class="select pull-right">
										<img src="${webRoot}/plug-in/weixin_mall/static/images/selectno.png">
									</span>
									
									<span class="js-drugName">软件故障</span>
									
								</p>
							  </a>
							</h4>
						  </div>
						 
                  </div>
                </div>
              </div>
            </li>
           
          </ul>
        </div>
                  
                     <div class="modal-footer" style="margin-top:0">	        	
		        	<button id="selectDrugSubmit" type="button" class="btn btn-o2o btn-block">保存</button>
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
		
		$('#timeId').val(selectTimeId);
		$('#deliverTime').html(selectTime);
		if( selectDate == "today")
		{
			$('#deliverDate').html("今天");
		}
		else if( selectDate == "tomorrow")
		{
			$('#deliverDate').html("明天");
		}
		$('#todayOrTomorrow').val(selectDate);
		
		$('#Selectdrugs').modal('hide');
});
};
	 </script>
</body>
</html>