<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="format-detection" content="telephone=no">
    <meta name="format-detection" content="email=no">
    <meta http-equiv="pragma" content="no-cache" />
	<title>选择学校</title>
	<link rel="stylesheet" type="text/css" href="${webRoot}/plug-in/static/style/base.css?1407174187">
    <link rel="stylesheet" type="text/css" href="${webRoot}/plug-in/static/style/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${webRoot}/plug-in/static/style/font-awesome.min.css">
    <script type="text/javascript" src="${webRoot}/plug-in/static/script/jquery.min.js"></script>
    <script type="text/javascript" src="${webRoot}/plug-in/static/script/bootstrap.min.js"></script>
    <script  type="text/javascript" src="${webRoot}/plug-in/static/script/common.js?1407174187"></script>
    <script>
    	function onBridgeReady(){
		 	WeixinJSBridge.call('hideOptionMenu');
		}
		
		if (typeof WeixinJSBridge == "undefined"){
			if( document.addEventListener ){
				document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
			}else if (document.attachEvent){
				document.attachEvent('WeixinJSBridgeReady', onBridgeReady); 
				document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
			}
		}else{
			onBridgeReady();
		}
    </script>
</head>
<body class="bg" style="padding-bottom:10px;">
	<div class="tip"></div>
	<div class="container mtop10">
        <div class="tab">
        	<div class="tabs">
                <a href="javascript:;" data-id="clubsearch" class="cur">搜索学校</a>
                <a href="javascript:;" data-id="clubrecord">选择记录</a>
            </div>
            <div id="clubsearch">
            	<div class="input-group">
                <form class="formicon" onSubmit="return form();">
                    <input type="search" class="form-control" placeholder="请输入学校名字以查找" name="title" id="keytitle">
                    <i class="fa fa-times-circle" onClick="$('#keytitle').val('');$(this).hide()"></i>
                </form>
                </div>
        		<ul class="list-group clublist" id="clublist"></ul>
            </div>
            <%-- <div id="clubrecord" class="hide">
				<ul class="list-group clublist">
            		<c:forEach items="${defautsCatalogList}" var="defautsCatalogItem">
		                <li class="list-group-item" id="li-1082">
							<a href="/index.php?s=/wechat/index/${defautsCatalogItem.id}" data-id="1082" class="panel-title">嘉和园小区<i class="fa fa-angle-right"></i></a>
	                        <span class="fr delspan" data-id="1082">删除</span>
						</li>
					</c:foreach>
				</ul> 
			</div> --%>
			<div id="clubrecord" class="hide">
            	<ul class="list-group clublist">
                	<li class="list-group-item" id="li-1082">
						<a href="#" data-id="1082" class="panel-title">暂无记录<i class="fa fa-angle-right"></i></a>
					</li>
	      		</ul>            </div>
			
        </div>
    </div>
    <script type="text/javascript" src="${webRoot}/plug-in/static/script/jquery.touchy.js"></script>
    <script>
	var classlist =${schoolJson};
		var nohtml = '';
		/* alert(classlist); */
		function searchKey(key)
		{
			var html ='';
			var flag = true ;
			if(key)
				flag = false ;
			var keytext = new RegExp(key);
			$.each(classlist,function(i) {
				var title = classlist[i].schoolName;
				if( flag || keytext.test(title)){
					html += '<li class="list-group-item">';
					html += '	<a href="shopController.do?select&schoolId='+classlist[i]['id']+'&open_user_id=${openuserid}" class="panel-title">'+classlist[i]['schoolName']+'<i class="fa fa-angle-right"></i></a>';
					html += '</li>';
				}
			})
			$('#clublist').html(html);
		}
		
	$(document).ready(function(){
		searchKey();
	});
	
	$('input[name="title"]').on("input propertychange",function(){
		$('#addclub').hide();
		var key = $(this).val();
		searchKey(key);
	})
	var options = {
		enableHighAccuracy: true,
	};
	/*window.navigator.geolocation.getCurrentPosition(handleSuccess, handleError,{timeout:10000});
			function handleSuccess(position){
		var lng = position.coords.longitude;
		var lat = position.coords.latitude;
		var html='';
		$('.tip').html('');
		$.each(classlist,function(i) {
			var xy = GetDistance(lat,lng,classlist[i].lat,classlist[i].lng);
			if(xy*1000<1000){
				html += '<li class="list-group-item">';
				html += '	<a href="/wechat/index/clubrecord/clubid/'+classlist[i]['id']+'" class="panel-title">'+classlist[i]['title']+'<i class="fa fa-angle-right"></i></a>';
				html += '</li>';
			}
		})
		nohtml = html;
		if(html)
		$('.loading').hide();
		else
		error('未找到你周边的小区,请手动搜索');
		$('#clublist').html(html);
	}*/
	function form(){
		if(!$('#clublist li').text()){
			$('#addclub strong').text($('input[name="title"]').val());
			$('input[name="clubname"]').val($('input[name="title"]').val())
			$('#addclub').show();
		}
		return false;
	}
	function handleError(s){
		error('无法获取你的位置,请手动搜索');
	}
	function Rad(d){
       return d * Math.PI / 180.0;
    }
	function GetDistance(lat1,lng1,lat2,lng2){
        var radLat1 = Rad(lat1);
        var radLat2 = Rad(lat2);
        var a = radLat1 - radLat2;
        var  b = Rad(lng1) - Rad(lng2);
        var s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +
        Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
        s = s *6378.137 ;
        s = Math.round(s * 10000) / 10000;
        //s=s.toFixed(4);
        return s;
    }
	$('.btn-submit').click(function(){
		loading();
		var form = $('#form');
		var target = form.attr('action');
		var query = form.serialize();
		$.post(target,query).success(function(data){
			if(data){
				if(data.status){
					error(data.info,'alert-warning');
					$('.btn-submit').attr("disabled", true).removeClass('btn-success').addClass('btn-gray');
				}else{
					error(data.info,'alert-danger',1);
				}
			}else{
				error('保存地址失败!','alert-danger');
			}
		});
		return false;
	})
	$('.tabs a').click(function(){
		var id = $(this).attr('data-id');
		$('#clubsearch,#clubrecord').hide();
		$('.tabs a').removeClass('cur')
		$('#'+id).show();
		$(this).addClass('cur');
	})
	$('.delspan').click(function(){
		var id = $(this).attr('data-id');
		var target = '/wechat/index/delclub/id/'+id;
		$.get(target).success(function(data){
			if(data.status){
				$('#li-'+id).remove();
				error(data.info,'alert-danger');
				if(!$('#clubrecord li').html()){
					$('#clubrecord').html('<h5 style="margin-top:10px; line-height:20px;" class="alert alert-warning alert-warning-border">您还没有小区选择记录!</h5>')
				}
			}
			else{
				error(data.info,'alert-danger');
			}
		})
	})
	$(function(){
		var handleTouchySwipe = function (e, $target, data) {
			var id = $(this).attr('data-id');
			if(data.direction=='left'){
				event.preventDefault();
				$(this).stop().animate({"margin-left":"-59px"},400);
				$('#li-'+id).find('span').stop().animate({"right":"0"},400);
			}
			else if(data.direction=='right'){
				event.preventDefault();
				$(this).stop().animate({"margin-left":"0"},400);
				$('#li-'+id).find('span').stop().animate({"right":"-59px"},400);
			}
		}   
		$('.list-group-item a').bind('touchy-swipe', handleTouchySwipe);
	});
    </script>
</body>
</html>
