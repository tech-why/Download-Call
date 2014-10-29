<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>我的号码</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="tbTelMyController.do?save">
			<input id="id" name="id" type="hidden" value="${tbTelMyPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							号码外键:
						</label>
					</td>
					<%-- <td class="value">
						<input class="inputxt" id="itemId" name="itemId" ignore="ignore"
							   value="${tbTelMyPage.itemId}">
						<span class="Validform_checktip"></span>
					</td> --%>
					
					<td class="value"><select id="itemId" name="itemId" datatype="*">
				  <c:forEach items="${tbTelItemEntityList}" var="tbtelitem">
					<option value="${tbtelitem.id }" <c:if test="${tbtelitem.id==tbTelCommentPage.itemId}">selected="selected"</c:if>>${tbtelitem.telItemName}</option>
				</c:forEach>
			    </select> <span class="Validform_checktip">请选择号码</span></td>
					
				</tr>
			</table>
		</t:formvalid>
 </body>