<%@ page language="java" import="java.util.*"  pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport" />
	<meta content="telephone=no" name="format-detection" />
	<meta content="email=no" name="format-detection" />
	<title></title>

	<link href="${webRoot}/plug-in/weixin_mall/static/bootstrap/bootstrap.min.css" rel="stylesheet">
	<link href="${webRoot}/plug-in/weixin_mall/static/style/hys.css" rel="stylesheet">
    <link href="${webRoot}/plug-in/weixin_mall/static/style/style.css" rel="stylesheet">
	
	<script charset="utf-8" src="http://map.qq.com/api/js?v=2.exp"></script>
<script>
function showSingleTextMarker(map , latLng , contentText)
{
	var label = new qq.maps.Label({
        position: latLng,
        map: map,
        content:contentText
    });
	var cssStyF=true;
    var cssSty=document.getElementById("cssSty");
    var cssC={color:"#f00",fontSize:"16px",fontWeight:"bold"};
    var cssP={color:"#000",fontSize:"14px",fontWeight:"normal"};
	label.setMap(map);
	label.setStyle(cssC);
}

function showLocationMarker(map , latLng )
{
	var anchor = new qq.maps.Point(10, 30);
    var size = new qq.maps.Size(32, 30);
    var origin = new qq.maps.Point(0, 0);
   
    size = new qq.maps.Size(52, 30);
    var originShadow = new qq.maps.Point(32, 0);
    var shadow =new qq.maps.MarkerImage(
        size, 
        originShadow,
        anchor 
    );
	var marker = new qq.maps.Marker({
        shadow: shadow,
        map: map,
        position:latLng,
        animation: qq.maps.MarkerAnimation.BOUNCE
    });
	
	marker.setMap(map);
}

var minLatLng = null ;
var maxLatLng = null ;

function getMin(value1 , value2)
{
	if( value1 > value2 )
	{
		return value2;
	}
	else
	{
		return value1;
	}
}

function getMax(value1 , value2)
{
	if( value1 > value2 )
	{
		return value1;
	}
	else
	{
		return value2;
	}
}

function buildBounds(newLatLng)
{
	if(minLatLng == null)
	{
		minLatLng = newLatLng ;
		maxLatLng = newLatLng ;
	}
	else
	{
		var minLat = getMin(newLatLng.getLat() , minLatLng.getLat() ) ;
		var minLng = getMin(newLatLng.getLng() , minLatLng.getLng() ) ;
		var maxLat = getMax(newLatLng.getLat() , maxLatLng.getLat() ) ;
		var maxLng = getMax(newLatLng.getLng() , maxLatLng.getLng() ) ;
		minLatLng = new qq.maps.LatLng(minLat, minLng) ;
		maxLatLng = new qq.maps.LatLng(maxLat, maxLng) ;
	}
}




var init = function() {

    var center = new qq.maps.LatLng(43.83867,87.57981);
    var text1 = '篮球场';
    var point1 = new qq.maps.LatLng(43.83867,87.57981);
    var text2 = '图书馆';
    var point2 = new qq.maps.LatLng(43.8373,87.57879);
    var text3 = '3号公寓';
    var point3 = new qq.maps.LatLng(43.83715,87.580246);
    var text4 = '大门入口';
    var point4 = new qq.maps.LatLng(43.835044,87.580269);
   buildBounds(point1);
    buildBounds(point2);
    buildBounds(point3);
    buildBounds(point4);
        
    var map = new qq.maps.Map(document.getElementById('map_container'),{
        center: center,
        zoom: 13
    });
    
    showSingleTextMarker(map , point1 , text1);
     showSingleTextMarker(map , point2 , text2);
      showSingleTextMarker(map , point3, text3);
       showSingleTextMarker(map , point4, text4);
      <c:if test="${flag == 'true'}">
      	 	var myLocation = new qq.maps.LatLng(${weidu},${jindu});
      	 	showLocationMarker(map , myLocation ) ;
      	 	buildBounds(myLocation);
      </c:if>
      var latlngBounds = new qq.maps.LatLngBounds(minLatLng,maxLatLng );
      
      map.fitBounds(latlngBounds);
	
    
}
</script>

    
	<!-- <link href="static/owl-carousel/owl.carousel.css" rel="stylesheet">
	<link href="static/owl-carousel/owl.theme.css" rel="stylesheet"> -->
	<style type="text/css">
		#picture .item img{ display: block; width: 100%;}
		#picture .owl-controls{margin-top:0;}
		#picture .owl-pagination{overflow:hidden;}
		#picture .owl-page{display:block; float:left; width:25%;}
		#picture .owl-page span{margin:0; width:100%; height:4px; border-radius:0; -webkit-border-radius:0;}
		.product-info h4{margin-top:0; padding-bottom:10px; border-bottom:2px solid #29b572;}
		.product-info h4 span i{font-size:22px; line-height:18px; color:#29b572;}
		.product-info h4 span.pull-right{color:#29b572;}
	</style>
</head>
<body style="background:#ecf0f1; color:#34495e"  onload="init()">
	<div class="container" style="padding-left:10px; padding-right:10px;">
		<div id="productDetail">
			<div class="panel product-info">
				<div class="panel-body" style="padding:10px;">
					<h4><span><i class="glyphicon hys-icon hys-icon-info-text"></i>&nbsp;新生地图</span></h4>
					<div class="panel-body" style="padding:0 0 10px 0;">
						<div style="width:100%;height:400px" id="map_container"></div>
					</div>
				</div>
			</div>
			
			
			<div class="panel product-info">
				<div class="panel-body" style="padding:10px;">
					<h4><span><i class="glyphicon hys-icon hys-icon-info-text"></i>&nbsp;报道须知</span></h4>
					<div class="detail-info">
					1、进入校门后由指示牌，如行李众多，可有家长照看或放在寄存处（篮球场旁边）<br>

					2、进入篮球场后，领取新生注册表，将表上每栏信息依次盖章，建议先将学生处一系列盖章改完，再找到学院接待处盖学院章，之后出篮球场去图书馆缴费<br>
					
					3、图书馆内缴纳学费、宿舍费（可刷卡、必须有注册章子包括确定给分宿舍的章子，才可以交费），办理一卡通，购买白大褂、耳机（这些需现金），在3号公寓领取校服校鞋（需交费凭单）<br>
					
					4、拿着单子去分到的宿舍楼，再根据专业分宿舍，领钥匙，入住<br>
					
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="${webRoot}/plug-in/weixin_mall/static/jquery/jquery-2.0.3.min.js" type="text/javascript"></script>
	<script src="${webRoot}/plug-in/weixin_mall/static/handlebars/handlebars-v1.3.0.js" type="text/javascript"></script>
	<script src="${webRoot}/plug-in/weixin_mall/static/owl-carousel/owl.carousel.min.js" type="text/javascript"></script>
	<script src="${webRoot}/plug-in/weixin_mall/static/script/hys-base.js" type="text/javascript"></script>
	<script src="${webRoot}/plug-in/weixin_mall/static/script/hys-product.js" type="text/javascript"></script>

</body>
</html>