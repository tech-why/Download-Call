<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/context/mytags.jsp"%>
<%-- ${webRoot}/plug-in/static/tel_item_files --%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <title>无标题文档</title>
    
<meta name="viewport" content="width=device-width,minimum-scale=1,maximum-scale=1,initial-scale=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <meta content="false" id="twcClient" name="twcClient">
    <script type="text/javascript">
	var hmt = hmt || {};

	hmt.sid =  49;
	
    
    </script>
   
    <script src="${webRoot}/plug-in/static/tel_files/jquery.js" type="text/javascript"></script>
    <script src="${webRoot}/plug-in/static/tel_files/template.js" type="text/javascript"></script>
    <script src="${webRoot}/plug-in/static/tel_files/jquery.cookie.js" type="text/javascript"></script>
    <link rel="stylesheet" href="${webRoot}/plug-in/static/tel_files/style.css">
    <script src="${webRoot}/plug-in/static/tel_files/jquery.iDialog.js" dialog-theme="default" type="text/javascript"></script>
    <script src="${webRoot}/plug-in/static/tel_files/main.js" dialog-theme="default" type="text/javascript"></script>



    <link href="${webRoot}/plug-in/static/tel_files/base.css" rel="stylesheet" type="text/css">
    <script>

    </script>
</head>

<body>
<div id="ajaxLoading" style="display: none;">
    <div class="icon-ok" id="ajaxLoadingIcon"></div>
    <div id="ajaxLoadingText">Success</div>
</div>






<!------- 号码详细页 ------------------------------------------------------------------------------------------------------->
<div id="page_ShopDetail"><div class="header">
<div class="fl"><span class="btn-back" onclick="history.go(-1);"><span class="icon"></span>返回</span></div>
<div class="fr"><span class="btn-HR" onclick="waitbuild();">...</span></div>
<h2>号码详情</h2>

</div>

<div class="mainBody">
  <div class="hmtShopArt">
  <div class="shopInfo">


        	<a href="tel:${tb.tel }" class="btn-tel-big" target="_blank" onclick=" this.stopPropagation();"></a> 
           <c:forEach items="${tbTelItemEntityList}" var="tb" >  
            <div class="box">
                <div class="name">${tb.officeName }</div>
                 <div class="note"> <i class="fontmfy-note"></i>${tb.telItemName }</div>
                <div class="tel"><i class="fontmfy-tel"></i>${tb.tel }</div>
                <div class="address"> <i class="fontmfy-address"></i>${tb.officeAddress }</div>
                
            </div>
            </c:forEach>
            <div class="notice clearfix">
            	<div class="fl"><span class="is_v">
				
				<i class="fontmfy-v v "></i>官方认证
				
				</span></div>
                <div class="fr"><span class="toComment" id="btnToShopComment"><i class="icon-comment icon"></i>评论</span></div>
            </div>
  </div>
  <div class="shopCommentList">
  	<ul>
    	

    </ul>
    <div class="btnListMore" data-sid="49" lock="true" pagenum="1">--还没有评论--</div>

  </div>
  
        
        
        
        
        


  </div>
</div></div>

<!--  <script id="temp-ShopDetail" type="text/html">
<div class="header">
<div class="fl"><span class="btn-back" onclick = 'history.go(-1);'><span class="icon"></span>返回</span></div>
<div class="fr"><span class="btn-HR"  onclick="waitbuild();" >...</span></div>
<h2>号码详情</h2>

</div>

<div class="mainBody">
  <div class="hmtShopArt">
  <div class="shopInfo">


        	<a href="tel:{{tel}}" class="btn-tel-big" target="_blank" onClick=" this.stopPropagation();"></a>
            
            <div class="box" >
                <div class="name">{{name}}</div>
                <div class="tel"><i class="fontmfy-tel"></i> {{tel}} </div>
                <div class="address"> <i class="fontmfy-address"></i>{{address}} </div>
                <div class="note"> <i class="fontmfy-note"></i> {{info}} </div>
            </div>
            <div class="notice clearfix">
            	<div class="fl"><span class="is_v">
				{{if v}}
				<i class="fontmfy-v v "></i>官方认证
				{{else}}
				<i class="fontmfy-v  "></i>未认证
				{{/if}}
				</span></div>
                <div class="fr"><span class='toComment' id='btnToShopComment'><i class="icon-comment icon"></i>评论</span></div>
            </div>
  </div>
  <div class="shopCommentList">
  	<ul>
    	

    </ul>
    <div class="btnListMore" data-sid = "{{sid}}" >{{sid}}更多评论...</div>

  </div>
  
        
        
        
        
        


  </div>
</div>
</script>
<script id="temp-ShopDetail-CommentList-li" type="text/html">
{{each list as v i}}
	<li data-comid = "{{v.comid}}">
        	<div class="head"><img class="head-pic" src="{{v.avatar}}"/></div>
            <span class="user-name">{{v.nickname}}</span>
            <span class="user-area">{{v.areaname}}</span>
            <div class="comment-msg">{{v.body}}</div>
            <span class="comment-time">{{v.ptime}}</span>
        </li>
{{/each}}
</script>
<!-------------------------------------------------------------------------------------------------------------->
<!-- <script id="temp-ShopCommentForm" type="text/html">
<div id="ShopComment">
	
    <div class="mainBody">
        <div class="commentEditW">
    <div class="commentEdit" style="overflow-x:hidden;" contentEditable = "true" placeholder="Search W3School" id="shopCommentConent"></div>
    <div class="commentBtnSubmit" id="shopCommentSubmint"> 提交评论 </div>
        </div>
    </div>

</div>
</script>
<!-------------------------------------------------------------------------------------------------------------->







<!-- <script>
var page = page || {};

page.pageShopDetail = function( sid ){
	//var sid =  sid || 'undefined';
	hmt.user = JSON.parse($.cookie('hmt.user'));	
	console.log(hmt.user);
			
			
			
	if('undefined' == typeof(sid)) { console.log('sid错误',sid );;return false;}
	
	//初始化页面 ajax 获取 详细页 页面信息
	$.ajax({
		url: 'ajax.php?dopost=getShopDetail&r='+Math.random(),
		type: 'POST',
		data: {'sid':sid } ,
		dataType: 'json',
		timeout: 6e3,
		//error: function(){alert('Error loading PHP document');},
		success: function(d){
			console.log(d);
			makePage( d );
			
			
			//进来先加载第一页评论
			$('#page_ShopDetail .btnListMore').click();//这里不用 btnMore 为什么？好像还没定义呢
		}
	});
	function makePage( d ){
		var data = {};
		data = d.data;
		console.log(data);
		var html = template('temp-ShopDetail', data );
		$('#page_ShopDetail').html(html);	
		
	}
	
	
	//更多评论
	var btnMore = $('#page_ShopDetail .btnListMore');
	
	btnMore.die();
	btnMore.live({
		'click':function(){
			//lock
			var $that = $(this);
			var sid = sid;
			sid = $that.data('sid');
			
			if( $that.attr('lock') == 'true' ){ return false;}else{ $that.html('<i class="icon-spin2 animate-spin"></i>数据加载中...'); $that.attr('lock','true') ;}
			
			//
			var pageNum = $(this).attr('pageNum') || 0;
			var _pageNum = parseInt(pageNum)+1;
			
			$.ajax({
				url: 'ajax.php?dopost=getCommentList&r='+Math.random(),
				type: 'POST',
				data: {'sid':sid ,'pagenum':_pageNum } ,
				dataType: 'json',
				timeout: 6e3,
				//error: function(){alert('Error loading PHP document');},
				success: function(d){
					//console.log(d);
					if( 0 != d.error ){
						M.alert(d.msg);
					}else{
						
						makeCommentList( d );
						$that.attr('pageNum',_pageNum );
						if( d.last == 0){
							
							$that.html('更多评论...');
							$that.attr('lock','false');
							
						}else{
							if(1 == _pageNum){
								$that.html('--还没有评论--');
							}else{
								$that.html('--没有评论了--');
							}
							
							$that.attr('lock','true');
							
						}
					
						
						
					}
					
				}
			
			});
			
			
			
		}			 
	});
	function makeCommentList(d){
		var data = {};
		data.list = d.list;
		console.log(data);
		var html = template('temp-ShopDetail-CommentList-li', data );
		
		$('#page_ShopDetail .shopCommentList ul').append(html);
		
	}
	
	
	//给评论btn 添加 click事件
	$('#btnToShopComment').live('click',function(){
		if(hmt.user ){
			showCommentDialog(sid);	
		}else{
			M.alert('您还没有登录<br/>或者<br/>还没有关注[张童鞋]!!!');
		}
												 
	})
	function showCommentDialog(){
		var html = template('temp-ShopCommentForm', {} );
		//alert(html);
		var w = (document.body.scrollWidth > 600 )  ?600 :document.body.scrollWidth-40;
		$.dialog({
			  id: 'dialog-ShopComment',
			  fixed:true,
			  lock:true,
			  title:'评论',
			  width:w,
			  padding:'0px',
			  content:html,
			  show:function(){
				var h =  document.body.offsetHeight ;
				this.$lock.css('height',h+'px');
			  }
		});
	};
	//给评论提交按钮写 提交事件
	var b = $('#shopCommentSubmint');
	
	b.live({
		'click':function(){
			//判断是否登录
					
			
			var $that = $(this);
			var c = $('#shopCommentConent');
			if( 0 == c.text().length ){ M.alert(' 评论内容不能为空！！！');return false; }
			
			
			//lock
			if( $that.attr('lock') == 'true' ){ return false;}else{ $that.html('<i class="icon-spin2 animate-spin"></i>评论提交中...'); $that.attr('lock','true') ;}
			$.ajax({
				url: 'ajax.php?dopost=shopCommentSubmint&r='+Math.random(),
				type: 'POST',
				data: {'sid':sid ,'from_user':hmt.user.from_user ,'body': c.text() } ,
				dataType: 'json',
				timeout: 6e3,
				//error: function(){alert('Error loading PHP document');},
				success: function(d){
					console.log(d);
					if( 0 != d.error ){
						M.alert(d.msg);
					}else{
						M.LoadSuccess();
						setTimeout(function(){
							page.pageShopDetail(sid);	//成功后重新加载 详细页 查看评论			
						},1500);
					}
				}
			});
		}
	});

	
	
	
	
	
	
};

page.pageShopComment = function (sid){

	
	
};



$(document).ready(function() {
	//
	page.pageShopDetail(49);
	M.LoadSuccess();
	M.guanzhu();
	

});
</script>
-->
<div id="foot-guanzhubox" style="position: fixed; left: 0px; bottom: 0px; width: 100%; height: 70px; z-index: 9999; background-color: rgba(0, 0, 0, 0.6); color: rgb(255, 255, 255); background-position: initial initial; background-repeat: initial initial;"><div style=" max-width:640px; margin:0px auto; position:relative;"><span guanzhu="“ztx”" style="padding:10px; margin-right:60px;; display:inline-block; font-size:1.1em"><img src="${webRoot}/plug-in/static/tel_files/guanzhu-logo-50x50.png" style="float:left; margin-right:5px;">点击关注王童鞋，<br>关注更精彩...</span><span class="foot-guanzhubox-btn-close" style="position:absolute; right:17px; top:17px; height:35px; line-height:35px; width:35px; text-align:center; border-radius:10px; font-size:18px; background:#000000;">X</span></div></div></body></html>

