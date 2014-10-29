// JavaScript Document
var M = M || {};
/*----------------------
Loading  Success
------------------------*/
M.LoadShow = function( m ){
	var m = m || 'Loading';
	var w = $('#ajaxLoading');
	var t = $('#ajaxLoadingText');
	var i = $('#ajaxLoadingIcon');
	i.attr("class", "icon-spin2 animate-spin");
	t.text(m);
	w.show();
};
M.LoadHide = function( m ){
	var m = m || 'Loading';
	var w = $('#ajaxLoading');
	var t = $('#ajaxLoadingText');
	var i = $('#ajaxLoadingIcon');
	i.attr("class", "icon-spin2 animate-spin");
	t.text(m);
	w.hide();
};
M.LoadSuccess = function( m ){
	var m = m || 'Success';
	var w = $('#ajaxLoading');
	var t = $('#ajaxLoadingText');
	var i = $('#ajaxLoadingIcon');
	i.attr("class", "icon-ok");
	t.text(m);
	w.show();
	setTimeout(function(){w.hide();},1000);
	
};
M.LoadError = function( m ){
	var m = m || 'Error';
	var w = $('#ajaxLoading');
	var t = $('#ajaxLoadingText');
	var i = $('#ajaxLoadingIcon');
	i.attr("class", "icon-cancel");
	t.text(m);
	w.show();
	setTimeout(function(){w.hide();},1000);
	
};

M.guanzhu = function(){
	var html= '<div id="foot-guanzhubox" style="position:fixed; left:0px ; bottom:0px; width:100%; height:70px; z-index:9999; background:rgba(0,0,0,.6); color:#FFFFFF;"><div style=" max-width:640px; margin:0px auto; position:relative;"><span guanzhu=“ztx” style="padding:10px; margin-right:60px;; display:inline-block; font-size:1.1em"><img src="static/img/guanzhu-logo-50x50.png" style="float:left; margin-right:5px;">点击关注张童鞋，<br/>关注更精彩...</span><span class="foot-guanzhubox-btn-close" style="position:absolute; right:17px; top:17px; height:35px; line-height:35px; width:35px; text-align:center; border-radius:10px; font-size:18px; background:#000000;">X</span></div></div>';

	

	setTimeout(function(){
		$( html ).appendTo('body').css({'bottom':'-100px'}).animate({bottom:'0px'},500);
	},2000);
	
	
	$('[guanzhu]').live({
		'click':function(){
			//关注页面
			window.location.href = 'http://mp.weixin.qq.com/s?__biz=MzA4MzAyOTIzNQ==&mid=201600850&idx=1&sn=cdf28f92f45c1ed18bd8d430356db3c4#rd';
		}
	});
	
	$('.foot-guanzhubox-btn-close').live({
		'click':function(){
			$('#foot-guanzhubox').animate({bottom:'-100px'},500,function(){$(this).remove();});
		}
	});
	
}	

/*----------------

----------------------*/

/*----------------

----------------------*/
$(document).ready(function() {
	//

	
	//page.init();
});
function msg(m){
	$('#msg').html( JSON.stringify( m ) );
}

function waitbuild(){
	M.alert('等待开发！');
}

