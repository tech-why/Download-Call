<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/context/mytags.jsp"%>
<%-- ${webRoot}/plug-in/static/tel_item_list_files --%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <title>无标题文档</title> 
<meta name="viewport" content="width=device-width,minimum-scale=1,maximum-scale=1,initial-scale=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <meta content="false" id="twcClient" name="twcClient">
    <script type="text/javascript">
	var hmt = hmt || {};
	hmt.cateid = 1;
	hmt.areaid = 103;
	hmt.area = {"cateid":"1","catename":"\u5b66\u6821","rid":"0","sortid":"1","isshow":"1"};
	
    
    </script>
   
    <script src="${webRoot}/plug-in/static/tel_files/jquery.js" type="text/javascript"></script>
    <script src="${webRoot}/plug-in/static/tel_files/template.js" type="text/javascript"></script>
    <script src="${webRoot}/plug-in/static/tel_files/jquery.cookie.js" type="text/javascript"></script>
    <link rel="stylesheet" href="${webRoot}/plug-in/static/tel_files/style.css">
    <script src="${webRoot}/plug-in/static/tel_files/jquery.iDialog.js" dialog-theme="default" type="text/javascript"></script>
    <script src="${webRoot}/plug-in/static/tel_files/main.js" dialog-theme="default" type="text/javascript"></script>



    <link href="${webRoot}/plug-in/static/tel_files/base.css" rel="stylesheet" type="text/css">
    <script>
    $(document).ready(function() {
		//page.pageIndex();
	});
    </script>
</head>

<body>
<div id="ajaxLoading" style="display: none;">
    <div class="icon-ok" id="ajaxLoadingIcon"></div>
    <div id="ajaxLoadingText">Success</div>
</div>






<!------ 分类列表页 -------------------------------------------------------------------------------------------------------->
<div id="page_CateList"><div class="header">
<div class="fl"><span class="btn-back" onclick="history.go(-1);"><span class="icon"></span>返回</span></div>
<div class="fr"><!--<span class="btn-HR"  onclick="waitbuild();" >添加</span>--></div>
<h2>${tbtitle}</h2>

</div>

<div class="mainBody">
  <div class="hmtShopList">
  	<ul>
  	 <c:forEach items="${tbTelItemEntity}" var="tb" >
    	<li onclick="location='${webRoot}/telItemController.do?display&id=${tb.id}&open_user_id=${open_user_id}'">
        	<%-- <a href="tel:${tb.tel }" class="btn-tel-big" target="_blank" onclick=" this.stopPropagation();"></a> --%>
          <a href="tel:${tb.tel}" class="btn-tel-big"> </a>
            <div class="box" data-sid="63">
                <div class="name"> <i class="fontmfy-v"></i>  ${tb.officeName }</div>
                <div class="note"> <i class="fontmfy-note"></i>${tb.telItemName }</div>
                <div class="tel"> <i class="fontmfy-tel"></i> ${tb.tel }</div>
                <div class="address"> <i class="fontmfy-address"></i> ${tb.officeAddress }</div>
            </div>
            
        </li>
    </c:forEach>    
     </ul>
   <!--  <div class="btnListMore" cateid="1" lock="false" pagenum="1">更多...</div> -->


  </div>
</div></div>
<!-- <script id="temp-CateList" type="text/html">
<div class="header">
<div class="fl"><span class="btn-back" onclick = 'history.go(-1);'><span class="icon"></span>返回</span></div>
<div class="fr"><!--<span class="btn-HR"  onclick="waitbuild();" >添加</span>--></div>
<!--<h2>{{catename}}</h2>

</div>

<div class="mainBody">
  <div class="hmtShopList">
  	<ul>

  
        
        
    </ul>
    <div class="btnListMore" cateid={{cateid}} >更多...</div>


  </div>
</div>
</script>

<script id="temp-hmtShopList-li" type="text/html">
{{if list }}
	{{each list as v i }}
    	<li>
        	<a href="tel:{{v.tel}}" class="btn-tel-big" target="_blank" onClick=" this.stopPropagation();"></a>
            
      <div class="box" data-sid = {{v.sid}}  >
                <div class="name">{{if v}} <i class="fontmfy-v"></i> {{/if}} {{v.name}}</div>
                <div class="tel"> <i class="fontmfy-tel"></i> {{v.tel}} </div>
                <div class="address"> <i class="fontmfy-address"></i> {{v.address}}</div>
                <div class="note"> <i class="fontmfy-note"></i> {{v.info}} </div>
            </div>
            
        </li>
	{{/each}}
{{/if}}
</script>







<script>
var page = page || {};

page.pageCateList = function( ){
	//alert(cateid);
	var cateid = hmt.cateid || 0;
	var areaid = hmt.areaid || 0;
	
	//先添加进去 后显示
	var data = hmt.area;
	console.log(data);
	var html = template('temp-CateList', data );
	$('#page_CateList').html(html);
	
	
	var btnMore = $('#page_CateList .btnListMore');


	btnMore.live({
		'click':function(){
			//lock
			
			var $that = $(this);
			if( $that.attr('lock') == 'true' ){ return false;}else{ $that.html('<i class="icon-spin2 animate-spin"></i>数据加载中...'); $that.attr('lock','true') ;}
			//
			var pageNum = $(this).attr('pageNum') || 0;
			var _pageNum = parseInt(pageNum)+1;
			//alert(cateid);//为什么这里的cateid 就变了呢？
			$.ajax({
				url: 'ajax.php?dopost=getCateList&r='+Math.random(),
				type: 'POST',
				data: {'cateid':cateid ,'areaid':areaid , 'pagenum':_pageNum } ,
				dataType: 'json',
				timeout: 6e3,
				//error: function(){alert('Error loading PHP document');},
				success: function(d){
					console.log(d);
					if( 0 != d.error ){
						M.alert(d.msg);
					}else{
						
						makePage( d );
						
						if( d.last == 0){
							$that.attr('pageNum',_pageNum );
							$that.html('更多...');
							$that.attr('lock','false');
							
						}else{
							$that.html('--没有更多--');
							$that.attr('lock','true');
							$that.attr('pageNum',_pageNum );
						}
					
						
						
					}
					
				}
			
			});
			
			
			
		}			 
	});
	/* 局部函数 */
	function makePage( d ){
		var data = {};
		data.list = d.list;
		
		var html = template('temp-hmtShopList-li', data );
		$('#page_CateList .hmtShopList ul').append(html);
	};
	
	$('.hmtShopList .box').live({
		'click':function(){
			window.location.href = "ShopDetail.php?sid="+$(this).data('sid');
		}
	});
	
	btnMore.click();//先运行一次
	
}
$(document).ready(function() {
	//
	page.pageCateList();
	M.LoadSuccess();
	M.guanzhu();

});
</script>
-->

<div id="foot-guanzhubox" style="position: fixed; left: 0px; bottom: 0px; width: 100%; height: 70px; z-index: 9999; background-color: rgba(0, 0, 0, 0.6); color: rgb(255, 255, 255); background-position: initial initial; background-repeat: initial initial;"><div style=" max-width:640px; margin:0px auto; position:relative;"><span guanzhu="“ztx”" style="padding:10px; margin-right:60px;; display:inline-block; font-size:1.1em"><img src="${webRoot}/plug-in/static/tel_files/guanzhu-logo-50x50.png" style="float:left; margin-right:5px;">点击关注王童鞋，<br>关注更精彩...</span><span class="foot-guanzhubox-btn-close" style="position:absolute; right:17px; top:17px; height:35px; line-height:35px; width:35px; text-align:center; border-radius:10px; font-size:18px; background:#000000;">X</span></div></div></body></html>
