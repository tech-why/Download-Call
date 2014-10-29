<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>号码小类</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="tbTelCatalogController.do?save">
			<input id="id" name="id" type="hidden" value="${tbTelCatalogPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							号码小类名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="telCatalogName" name="telCatalogName" ignore="ignore"
							   value="${tbTelCatalogPage.telCatalogName}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							号码大类外键:
						</label>
					</td>
					<%-- <td class="value">
						<input class="inputxt" id="classId" name="classId" ignore="ignore"
							   value="${tbTelCatalogPage.classId}">
						<span class="Validform_checktip"></span>
					</td> --%>
					
					
					<td class="value"><select id="classId" name="classId" datatype="*">
				  <c:forEach items="${tbTelClassEntityList}" var="tbtelclass">
					<option value="${tbtelclass.id }" <c:if test="${tbtelclass.id==tbTelCatalogPage.classId}">selected="selected"</c:if>>${tbtelclass.telClassName}</option>
				</c:forEach>
			    </select> <span class="Validform_checktip">请选择大类外键</span></td>
					
					
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							显示顺序:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="displaySequence" name="displaySequence" ignore="ignore"
							   value="${tbTelCatalogPage.displaySequence}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>