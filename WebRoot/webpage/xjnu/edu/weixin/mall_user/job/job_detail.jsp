<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/context/mytags.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<META content="IE=11.0000" http-equiv="X-UA-Compatible">

<META charset="utf-8">
<TITLE>找工作</TITLE>
<META name="viewport"
	content="width=device-width, initial-scale=1.0,maximum-scale=1.0, minimum-scale=1.0,user-scalable=no">
<META name="apple-mobile-web-app-capable" content="yes">
<META name="apple-mobile-web-app-status-bar-style" content="black">
<META name="format-detection" content="telephone=no">
<META name="format-detection" content="email=no">
<!-- <LINK href="/images/apple-touch-icon-iphone.png" rel="apple-touch-icon">
<LINK href="/images/apple-touch-icon-ipad.png" rel="apple-touch-icon"
	sizes="72x72">
<LINK href="/images/apple-touch-icon-iphone4.png" rel="apple-touch-icon"
	sizes="114x114"> -->
<LINK
	href="${webRoot}/plug-in/weixin_mall/job/jquery.mobile-1.4.2.min.css"
	rel="stylesheet" type="text/css">
<LINK href="${webRoot}/plug-in/weixin_mall/job/font.css"
	rel="stylesheet" type="text/css">
<LINK href="${webRoot}/plug-in/weixin_mall/job/wap.css" rel="stylesheet"
	type="text/css">

<SCRIPT src="${webRoot}/plug-in/weixin_mall/job/jquery-1.8.2-min.js"
	type="text/javascript"></SCRIPT>

<SCRIPT src="${webRoot}/plug-in/weixin_mall/job/WealinkMobile.js"
	type="text/javascript"></SCRIPT>

<SCRIPT src="${webRoot}/plug-in/weixin_mall/job/jquery.mobile-1.4.2.js"
	type="text/javascript"></SCRIPT>

<META name="GENERATOR" content="MSHTML 11.00.9600.17239">
</HEAD>
<BODY>
	<div id="zhiwei-detail" data-role="page" data-dom-cache="false">
   <div data-role="header" data-icon="false">
        <div class="nav">
        <div class="navBar">
<!--             <a data-ajax='false' href='http://m.wealink.com/zhiwei/' class='navQiuzhi current'>招聘</a><a data-ajax='false' href='http://m.wealink.com/renmai/' class='navShejiao'>人脉</a><a data-ajax='false' href='http://m.wealink.com/dianping/' class='navZixun'>发现</a><a data-ajax='false' href='http://m.wealink.com/wode/' class='navWode'>我的</a>        </div>
 -->            </div>
    <div class="wtitleNew">
        <a href="${webRoot}/jobController.do?job" data-ajax="false" data-rel="back" class="back"><img src="${webRoot}/plug-in/weixin_mall/job/images/new_back.png" width="5" height="10">&nbsp;返回</a>
        <span class="alginC">职位详情</span>
       <!--  <a id="btnAddFavorite" data-wlm-pid="24620858" href="javascript:void(0);" class="wtR whiteLink">收藏</a> -->
    </div>
</div>
<div data-role="content">
<div class="listItem">
    
    <div class="listDetail">
        <ul>
            <li class="f30B">${tbJobDetail.positionname}</li>
            <li class="salary1">${tbJobDetail.salary }</li> 
            <li class="colgrey1"><p class="tover"><span class="colb">公司名称：</span>${tbJobDetail.getTbCompanyEntity().name }</p></li>
            <li class="colgrey1"><p class="tover"><span class="colb">工作地点：</span>
            ${tbJobDetail.gettSTerritory().getTSTerritory().getTSTerritory().getTerritoryName()}
            -${tbJobDetail.gettSTerritory().getTSTerritory().getTerritoryName()}
            -${tbJobDetail.gettSTerritory().getTerritoryName()}
            -${tbJobDetail.workPlace}</p></li>
            <!-- <li class="colgrey1"><p class="tover"><span class="colb">学历要求：</span>中专</p></li> -->
                    </ul>
    </div>
</div>
<div class="listItem" >
    <div class="listTitle">
        职位详情
    </div>
    <div class="colgrey1  pdT18">
        <div class="f24 pdB3 lH17">
            <div class="pd10">
               ${tbJobDetail.jobDescription }
            </div>
        </div>
    </div>
</div>

    <div class = "listItem">
        <div class = "listTitle">
              公司信息
        </div>
        <div class = "listDetail f24 colgrey1">
            <p class = "f30B tover">${tbJobDetail.getTbCompanyEntity().name }</p>
            <table>
                  <tr>
                        <td class = "colb ll">公司地点：</td>
                        <td>${tbJobDetail.gettSTerritory().getTSTerritory().getTSTerritory().getTerritoryName()}
            -${tbJobDetail.gettSTerritory().getTSTerritory().getTerritoryName()}
            -${tbJobDetail.gettSTerritory().getTerritoryName()}
            -${tbJobDetail.workPlace}</td>
                    </tr>
                <tr>
                    <td class = "colb ll">联系人：</td>
                    <td>${tbJobDetail.getTbCompanyEntity().contact}</td>
                </tr>
                <tr></tr>
                    <tr>
                        <td class = "colb ll">座机：</td>
                        <td>${tbJobDetail.getTbCompanyEntity().phoneNumber}</td>
                    </tr>
                    <tr>
                        <td class = "colb ll">手机：</td>
                        <td>${tbJobDetail.getTbCompanyEntity().mobile}</td>
                    </tr>
                     <tr>
                        <td class = "colb ll">电子邮件：</td>
                        <td>${tbJobDetail.getTbCompanyEntity().email}</td>
                    </tr>
                     <tr>
                        <td class = "colb ll">QQ：</td>
                        <td>${tbJobDetail.getTbCompanyEntity().qq}</td>
                    </tr>
                    <tr>
                        <td class = "colb ll">公司简介：</td>
                        <td>
                            <p id = "companyDescription" class="t5over">
                            ${tbJobDetail.getTbCompanyEntity().discription}
                            </p>
                        </td>
                    </tr>
                            </table>
                        <p class = "showMore" id = "companyDescriptionToggle"><span>展开更多</span></p>
                    </div>
    </div>
    <p class="pdT52"></p>
    <div class="bottomBtn">
        <p class="colgrey3 border1"></p>
        <p class="border1 colgrey4 "></p>
        <div class="greybox"><%-- href="${webRoot}/resumeController.do?resume" --%>
            <a data-wlm-pid="24620858" id="btnApplyPosition" href="${webRoot}/resumeController.do?resume" data-ajax="false"  class="applyBtn whiteLink "><span>个人简历</span></a>
        </div>
    </div>
</div>

</BODY>
</HTML>
