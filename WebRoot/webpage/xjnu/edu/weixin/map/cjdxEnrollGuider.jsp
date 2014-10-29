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
    var center = new qq.maps.LatLng(43.833025,87.592632);
    var text1 = '智善楼';
    var point1 = new qq.maps.LatLng(43.884040,87.551100);
    var text2 = '大门入口';
    var point2 = new qq.maps.LatLng(43.884443,87.552157);
    var text3 = '侧门入口';
    var point3 = new qq.maps.LatLng(43.883182,87.553048);
    var text4 = '服务点';
    var point4 = new qq.maps.LatLng(43.88397,87.546906);
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
					1、新生持《新生报到须知》中第三条的材料到所在学院报到并领取校园通内存好学费、教材费的新生直接到所在学院领取相关票据<br>
					2、持现金缴费的新生到至善楼办理相关手续<br>
					3、最后将《新生报到通知单》交迎新现场学生工作处服务点进行识别
					
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