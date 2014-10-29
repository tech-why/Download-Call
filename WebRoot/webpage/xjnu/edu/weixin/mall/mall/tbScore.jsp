<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>积分管理</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="tbScoreController.do?save">
			<input id="id" name="id" type="hidden" value="${tbScorePage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							用户外键:
						</label>
					</td>
					<%-- <td class="value">
						<input class="inputxt" id="weixinUserId" name="weixinUserId" ignore="ignore"
							   value="${tbScorePage.weixinUserId}">
						<span class="Validform_checktip"></span>
					</td> --%>
					
					
					<td class="value"><select id="weixinUserId" name="weixinUserId" datatype="*">
				  <c:forEach items="${weixinUserinforList}" var="tbweixinUserinfor">
					<option value="${tbweixinUserinfor.id }" <c:if test="${tbweixinUserinfor.id==tbScorePage.weixinUserId}">selected="selected"</c:if>>${tbweixinUserinfor.openid}</option>
				</c:forEach>
			    </select> <span class="Validform_checktip">请选择用户</span></td>
					
					
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							创建时间:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="createTime" name="createTime" ignore="ignore"
							     value="<fmt:formatDate value='${tbScorePage.createTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							积分:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="score" name="score" ignore="ignore"
							   value="${tbScorePage.score}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							积分原因:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="scoreReason" name="scoreReason" ignore="ignore"
							   value="${tbScorePage.scoreReason}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>