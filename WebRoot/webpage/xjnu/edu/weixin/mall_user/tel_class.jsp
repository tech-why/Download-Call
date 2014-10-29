<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/context/mytags.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <title>号码通</title>
    
<meta name="viewport" content="width=device-width,minimum-scale=1,maximum-scale=1,initial-scale=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <meta content="false" id="twcClient" name="twcClient">
    <script type="text/javascript">
	var hmt = hmt || {};
	hmt.area = {"areaid":"103","areaname":"\u6c88\u9633\u822a\u7a7a\u822a\u5929\u5927\u5b66","areashortname":"\u6c88\u822a"};
	hmt.user = {"from_user":"o80rDtyAsw5OllsfGCuCVaYbJdyw","univid":"103"};
	console.log(hmt);
    
    </script>
   
    <script src="${webRoot}/plug-in/static/tel_files/jquery.js" type="text/javascript"></script>
    <script src="${webRoot}/plug-in/static/tel_files/template.js" type="text/javascript"></script>
    <script src="${webRoot}/plug-in/static/tel_files/jquery.cookie.js" type="text/javascript"></script>
    <link rel="stylesheet" href="${webRoot}/plug-in/static/tel_files/style.css">
    <script src="${webRoot}/plug-in/static/tel_files/jquery.iDialog.js" dialog-theme="default" type="text/javascript"></script>
    <script src="${webRoot}/plug-in/static/tel_files/main.js" dialog-theme="default" type="text/javascript"></script>



    <link href="${webRoot}/plug-in/static/tel_files/base.css" rel="stylesheet" type="text/css">

</head>

<body>
<div id="ajaxLoading" style="display: none;">
    <div class="icon-ok" id="ajaxLoadingIcon"></div>
    <div id="ajaxLoadingText">Success</div>
</div>





<div id="msg"></div>
<!----号码通首页---------------------------------------------------------------------------------------------------------->
<div id="page_Index"><div class="header">
    <div class="fl"> </div>
    <div class="fr"> </div>
    <h2>号码通</h2>
</div>

<div class="mainBody">
	<div class="hmtIndex">
    	<div class="M1" onclick="waitbuild();"><span class="iocn"></span> <strong>我的号码</strong> </div>
	 <c:forEach items="${tbTelclassList}" var="tb" >
        <a style = "text-decoration:none ; color:#000" href="${webRoot}/telItemListController.do?display&schoolId=${schoolId}&classId=${tb.getId()}&open_user_id=${openuserid}" class="panel-title">
        <div class="M1 -hover" data-cateid="1"><span class="iocn"></span>
         ${tb.telClassName }
         </div>
        </a>
        <div class="M2box">
        	<ul>
        	 <c:forEach items="${tb.getTbTelCatalogEntityJson()}" var="tbc" varStatus="status">
               <a style = "text-decoration:none ; color:#000" href="${webRoot}/telItemListController.do?display&schoolId=${schoolId}&catalogId=${tbc.getId()}&open_user_id=${openuserid}" class="panel-title">
               <li class="M2 -hover" data-cateid="[status.index]">
               ${tbc.getTelCatalogName()}
               </li>
               </a> 
            </c:forEach>
            </ul>
           <!-- <br clear="all"/>-->
            
        </div>
		</c:forEach>
        
    </div>
</div></div>
<!--<script id="temp-Index" type="text/html">


<div class="header">
    <div class="fl"> </div>
    <div class="fr"> </div>
    <h2>号码通</h2>
</div>

<div class="mainBody">
	<div class="hmtIndex">
    	<div class="M1" onclick="waitbuild();" ><span class="iocn"></span> <strong>我的号码</strong> </div>
		
{{if tree }}
	{{each tree as v i }}
        <div class="M1 -hover" data-cateid = '{{v.cateid}}'><span class="iocn"></span> {{v.catename}}</div>
        
		{{if v.son }}
        <div class="M2box">
        	<ul>
			{{each v.son as vv ii }}
            <li class="M2 -hover" data-cateid = '{{vv.cateid}}'>{{vv.catename}}</li>
			{{/each }}
            </ul>
           <!-- <br clear="all"/>-->
            
      <!--   </div>
		{{/if}}
	{{/each}}


{{ /if  }}
        
        
    </div>
</div>
</script> -->

<!-------------------------------------------------------------------------------------------------------------->







<script>
/* 	var page = page || {};

page.pageIndex= function(){
	
	var areaid = hmt.area.areaid ;
	//把hmt.user 保存到 cookic 里 方便后边其他页面 需要用户信息时候 调用
	$.cookie('hmt.user',JSON.stringify(hmt.user));// 读取的时候用这个 xxx = JSON.parse($.cookie('hmt.user'));
	//$.cookie('hmt.user',null);
	
	

	
	$.ajax({
		url: 'ajax.php?dopost=pageIndex&r='+Math.random(),
		type: 'POST',
		data: hmt.area ,
		dataType: 'json',
		timeout: 6e3,
		//error: function(){alert('Error loading PHP document');},
		success: function(d){
			
			if( 0 != d.error ){
				M.alert(d.msg);
			}else{
				hmt.cate= hmt.cate || {};
				hmt.cate.tree = d.tree;
				hmt.cate.list = d.list;
				makePage( d );
				M.LoadSuccess();
			}
			
		}
	
	});
	
	function makePage( data ){
		console.log(data);
		//var data = ?( 'Object' == typeof(data) )  data : {};
		data = data ||{};
		var html = template('temp-Index', data);
		$('#page_Index').html(html);
		
		
	}
	 */
	$('.hmtIndex [data-cateid]').live({
		'mouseover':function(){
			$(this).addClass('hover');
		},
		'mouseout':function(){
			$(this).removeClass('hover');
		},
		
		/* 'click':function(){ 
			var $that = $(this);
			var cateid = $(this).data('cateid');
			//跳转到 分类列表页 带参数 cateid
			//alert(cateid);
			M.LoadShow ();
			window.location.href="cateList.php?cateid="+cateid+"&areaid="+areaid;
		} */
		
	});
	
/* };	
    $(document).ready(function() {
		page.pageIndex();
		M.guanzhu
	}); */
</script></body></html>