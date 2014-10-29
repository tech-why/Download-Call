<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>建筑小类管理</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="tbBuilddingCatalogController.do?save">
			<input id="id" name="id" type="hidden" value="${tbBuilddingCatalogPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							建筑小类名:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="catalogName" name="catalogName" ignore="ignore"
							   value="${tbBuilddingCatalogPage.catalogName}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							建筑大类外键:
						</label>
					</td>
					<%-- <td class="value">
						<input class="inputxt" id="classId" name="classId" ignore="ignore"
							   value="${tbBuilddingCatalogPage.classId}">
						<span class="Validform_checktip"></span>
					</td> --%>
					
					<td class="value"><select id="tbBuilddingClass.Id" name="tbBuilddingClass.Id" datatype="*">
				  <c:forEach items="${tbBuilddingClassEntityList}" var="tbClass">
					<option value="${tbClass.id }" <c:if test="${tbClass.id==tbBuilddingCatalogPage.tbBuilddingClass.Id}">selected="selected"</c:if>>${tbClass.className}</option>
				</c:forEach>
			    </select> <span class="Validform_checktip">请选择大类建筑</span></td>
					
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							显示顺序:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="displaySequence" name="displaySequence" ignore="ignore"
							   value="${tbBuilddingCatalogPage.displaySequence}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>