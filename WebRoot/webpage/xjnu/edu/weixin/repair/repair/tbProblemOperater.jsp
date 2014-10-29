<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>维修人员表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="tbProblemOperaterController.do?save">
			<input id="id" name="id" type="hidden" value="${tbProblemOperaterPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							维修人员openid:
						</label>
					</td>
					<td class="value">
							<select id="weixinUserId" name="weixinUserId" >
				  				<c:forEach items="${weixinuserinfolist}" var="tbuser">
									<option value="${tbuser.id }" <c:if test="${tbuser.id==tbProblemOperaterPage.weixinUserId}">selected="selected"</c:if>>${tbuser.openid}</option>
								</c:forEach>
			    </select> 
			    <span class="Validform_checktip">请选择用户</span></td>
					
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							维修人员用户名:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="userName" name="userName" ignore="ignore"
							   value="${tbProblemOperaterPage.userName}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
							
					<td align="right">
						<label class="Validform_label">
							维修人员后台用户:
						</label>
					</td>
					<td class="value">
							<select id="userId" name=userId datatype="*">
				  				<c:forEach items="${tsuserlist}" var="tsuser">
									<option value="${tsuser.id }" <c:if test="${tsuser.id==tbProblemOperaterPage.userId}">selected="selected"</c:if>>${tsuser.userName}</option>
								</c:forEach>
			    </select> 
			    <span class="Validform_checktip">请选择用户</span></td>
				</tr>
			</table>
		</t:formvalid>
 </body>