<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/context/mytags.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html class="ui-mobile">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--<base href="http://m.wealink.com/qiuzhi/basic/">-->
<base href=".">
<meta charset="utf-8">
<title>我的简历-基本信息</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0,maximum-scale=1.0, minimum-scale=1.0,user-scalable=no" />
<meta content="yes" name="apple-mobile-web-app-capable" />
<meta content="black" name="apple-mobile-web-app-status-bar-style" />
<meta content="telephone=no" name="format-detection" />
<meta content="email=no" name="format-detection" />
<script type="text/javascript">
var dictJson = {
   ${school}
};
</script>
<!-- <link rel="apple-touch-icon" href="http://m.wealink.com/images/apple-touch-icon-iphone.png">
<link rel="apple-touch-icon" sizes="72x72" href="http://m.wealink.com/images/apple-touch-icon-ipad.png">
<link rel="apple-touch-icon" sizes="114x114" href="http://m.wealink.com/images/apple-touch-icon-iphone4.png"> -->
<link
	href="${webRoot}/plug-in/weixin_mall/job/jquery.mobile-1.4.2.min.css"
	rel="stylesheet" type="text/css">
<link
	href="${webRoot}/plug-in/weixin_mall/job/mobiscroll.custom-2.6.2.min.css"
	rel="stylesheet" type="text/css">
<link href="${webRoot}/plug-in/weixin_mall/job/font.css"
	rel="stylesheet" type="text/css">
<link href="${webRoot}/plug-in/weixin_mall/job/wap.css" rel="stylesheet"
	type="text/css">
<script type="text/javascript" async=""
	src="${webRoot}/plug-in/weixin_mall/job/ga.js">
	
</script>
<script type="text/javascript"
	src="${webRoot}/plug-in/weixin_mall/job/jquery-1.8.2-min.js"></script>
<script type="text/javascript"
	src="${webRoot}/plug-in/weixin_mall/job/WealinkMobile.js"></script>
<script type="text/javascript"
	src="${webRoot}/plug-in/weixin_mall/job/jquery.mobile-1.4.2.min.js"></script>
<script type="text/javascript"
	src="${webRoot}/plug-in/weixin_mall/job/check_qiuzhi.js"></script>
<script type="text/javascript"
	src="${webRoot}/plug-in/weixin_mall/job/mobiscroll.custom-2.6.2.min.js"></script>
</head>

<body >
	<div data-role="page" id="dangan" data-dom-cache="false"
		data-url="dangan" tabindex="0"
		class="ui-page ui-page-theme-a ui-page-active"
		style="min-height: 524px;">
		<div data-role="header" role="banner" class="ui-header ui-bar-inherit">
			<!-- <div class="top">
     <a class="logo ui-link" href="http://m.wealink.com/"><img src="./我的简历-基本信息_files/wap-LOGO-126-40.png" width="63" height="20"></a> -->

			<!--  <span class="info"><a data-ajax="false" href="http://m.wealink.com/wode/" class="ui-link"></a><span class="colhui pd">|</span><a href="http://m.wealink.com/message/" class="ui-link">消息</a><span>
    </span></span> </div>-->

			<div class="wtitle f34 colw ui-content" data-role="content"
				role="main">
				<a href="javascript:history.back();" class="back f22 ui-link"><img
					src="http://localhost:8080/jeewx/plug-in/weixin_mall/job/images/back.png" width="5"
					height="10">&nbsp;返回</a> <span class="alginC">基本信息</span>
			</div>
		</div>
		<div data-role="content">
        	<div class="mgT18LR10">
            <form action="jeewx/resumeController.do?saveResum" method="post" name="danganForm" id="danganForm" data-ajax='false'>
               <input type="hidden" name="id" id="id" />
                <ul>
                    <li class="H40"><span>姓　　名：</span><input data-role="none" type="text" value="" id="name" name="name"></li>
                    <li class="H40"><span>性　　别：</span><span class="radiobox">
                    <i class="left current" onclick="choose(this,'M','gender');">男</i>
                    <i class="right" onclick="choose(this,'F','gender');">女</i>
                    <input type="hidden" id="gender" name="gender" value="M">
                    </span></li>
                    
                    <li class="H40"><span>期望职位：</span><input data-role="none" type="text" value="" id="expirePosition" name="expirePosition"></li>
                    <li class="H40"><span>电子邮件：</span><input data-role="none" type="text" value="" id="email" name="email"></li>
                    <!-- <li class="H40"><span>期望工作城市：</span><input data-role="none" type="text" value="" id="area" name="area"></li> -->
                    <li class="H40"><span>Q　　  Q：</span><input data-role="none" type="text" value="" id="qq" name="qq"></li>
                    
                    <li class="H40"><span>出生年月:</span>
					<input data-role="none" type="text" value="" id="brithday" name="brithday" style="ime-mode: disabled;" readonly>
                    </li>
                    <li class="H40"><span>手机号码:</span><input data-role="none" id="phoneNumber" name="phoneNumber" type="text"  value=""></li>
                    
                    <!-- <li class="H40"><span>参加工作:</span>
						<label for="work_years_new" class="ui-input-text" style="display:none;">请选择参加工作年份</label>
						<select id="work_years_new" class="demos" data-role="none" style="display: none;">
							 <option value="0" >在读学生</option>
						     <option value="1" >应届生</option>
						    						    <option value="2014" >2014</option>
						    						    <option value="2013" >2013</option>
						    						    <option value="2012" >2012</option>
						    						    <option value="2011" >2011</option>
						    						    <option value="2010" >2010</option>
						    						    <option value="2009" >2009</option>
						    						    <option value="2008" >2008</option>
						    						    <option value="2007" >2007</option>
						    						    <option value="2006" >2006</option>
						    						    <option value="2005" >2005</option>
						    						    <option value="2004" >2004</option>
						    						    <option value="2003" >2003</option>
						    						    <option value="2002" >2002</option>
						    						    <option value="2001" >2001</option>
						    						    <option value="2000" >2000</option>
						    						    <option value="1999" >1999</option>
						    						    <option value="1998" >1998</option>
						    						    <option value="1997" >1997</option>
						    						    <option value="1996" >1996</option>
						    						    <option value="1995" >1995</option>
						    						    <option value="1994" >1994</option>
						    						    <option value="1993" >1993</option>
						    						    <option value="1992" >1992</option>
						    						    <option value="1991" >1991</option>
						    						    <option value="1990" >1990</option>
						    						    <option value="1989" >1989</option>
						    						    <option value="1988" >1988</option>
						    						    <option value="1987" >1987</option>
						    						    <option value="1986" >1986</option>
						    						    <option value="1985" >1985</option>
						    						    <option value="1984" >1984</option>
						    						    <option value="1983" >1983</option>
						    						    <option value="1982" >1982</option>
						    						    <option value="1981" >1981</option>
						    						    <option value="1980" >1980</option>
						    						    <option value="1979" >1979</option>
						    						    <option value="1978" >1978</option>
						    						    <option value="1977" >1977</option>
						    						    <option value="1976" >1976</option>
						    						    <option value="1975" >1975</option>
						    						    <option value="1974" >1974</option>
						    						    <option value="1973" >1973</option>
						    						    <option value="1972" >1972</option>
						    						    <option value="1971" >1971</option>
						    						    <option value="1970" >1970</option>
						    						    <option value="1969" >1969</option>
						    						    <option value="1968" >1968</option>
						    						    <option value="1967" >1967</option>
						    						    <option value="1966" >1966</option>
						    						    <option value="1965" >1965</option>
						    						    <option value="1964" >1964</option>
						    						    <option value="1963" >1963</option>
						    						    <option value="1962" >1962</option>
						    						    <option value="1961" >1961</option>
						    						    <option value="1960" >1960</option>
						    						    <option value="1959" >1959</option>
						    						    <option value="1958" >1958</option>
						    						    <option value="1957" >1957</option>
						    						    <option value="1956" >1956</option>
						    						    <option value="1955" >1955</option>
						    						    <option value="1954" >1954</option>
						    						    <option value="1953" >1953</option>
						    						    <option value="1952" >1952</option>
						    						    <option value="1951" >1951</option>
						    						    <option value="1950" >1950</option>
						    						</select>  
                    </li>
                    <li class="H40"><span>婚姻状况:</span><span class="radiobox1">
                    <i onclick="choose(this,'S','marriage');" class="left current">保密</i>
                    <i onclick="choose(this,'N','marriage');" class="center">未婚</i>
                    <i onclick="choose(this,'Y','marriage');" class="right">已婚</i>
                    <input type="hidden" id="marriage" name="marriage" value="S">
                    </span></li>
                    <li class="colb" data-wlm-filter-field="company" data-wlm-filter-type="single">选择公司
							<input type="hidden" name="company" id="company" value="0">
							<a href="javascript:void(0);" id="label-company" class="ui-link">不限</a>
						</li>
                     -->
                    <li class="H40" data-wlm-filter-required="true" data-wlm-filter-field="schoolId" data-wlm-filter-type="single">
                    <span>所在学校:</span>
                    <span class="pChoose" id="label-schoolId"></span>
                    <input id="schoolId" type="hidden" value="" name="schoolId">
                    </li>
                    <li class="H40"><span>期望城市：</span><input data-role="none" type="text" value="" id="area" name="area"></li>
                    <li class="noHeight"><span>自我评价:</span>
                    <textarea data-role="none" rows="5" cols="5" name="selfDiscription" id="selfDiscription"></textarea>
                    </li>
                </ul>
	            <p class="jlPdTB30"><button id="id_save_hrinformation" type="button" onclick="save_information();" data-role="none">保存</button></p>
            </form>
        </div>

    </div>
</div>
    <div class="alphaBg" id="wlm-filter-mask"></div>
    <div class="filter" id="wlm-filter" ></div>
<script type="text/javascript">

function choose(obj,value,name){
	$(obj).addClass("current").siblings().removeClass("current");
	$('#'+name).val(value);
}
$(function () {
    $("#brithday").scroller('destroy').scroller($.extend(  
        {  
            preset :"date",//日期形式 date|datetime|time  
            //minDate: new Date(1950,1),//最小日期  
            //maxDate: new Date(2014,12) //最大日期  
        },  
        { 
        	theme: 'default', //风格配置 jqm|default 默认default  
        	mode: 'scroller', //滚动模式 scroller|clickpick|mixed 默认scroller  
        	display: 'bottom', //显示模式 modal|inline|bubble|top|bottom 默认modal  
        	lang: 'zh', //默认en-US  
        	dateOrder: "yymmdd", //弹出样子
        	dateFormat:'yy-mm-dd', //显示格式
        }
    ));  
    
    $('#work_years_new').scroller('destroy').scroller($.extend(
    		{preset : 'select'},
    		{theme: 'default', //风格配置 jqm|default 默认default  
        	 mode: 'scroller', //滚动模式 scroller|clickpick|mixed 默认scroller  
        	 display: 'bottom', //显示模式 modal|inline|bubble|top|bottom 默认modal  
        	 lang: 'zh', //默认en-US  
        	}
      ));
});

</script>
</body>
</html>