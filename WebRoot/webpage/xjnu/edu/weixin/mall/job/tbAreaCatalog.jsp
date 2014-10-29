<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>区域小类表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="tbAreaCatalogController.do?save">
			<input id="id" name="id" type="hidden" value="${tbAreaCatalogPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							小类名:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="catalogName" name="catalogName" ignore="ignore"
							   value="${tbAreaCatalogPage.catalogName}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							显示顺序:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="displayIndex" name="displayIndex" ignore="ignore"
							   value="${tbAreaCatalogPage.displayIndex}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							大类外键:
						</label>
					</td>
					<%-- <td class="value">
						<input class="inputxt" id="classId" name="classId" ignore="ignore"
							   value="${tbAreaCatalogPage.classId}">
						<span class="Validform_checktip"></span>
					</td> --%>
					
					<td class="value"><select id="tbAreaClassEntity.Id" name="tbAreaClassEntity.Id" datatype="*">
				  <c:forEach items="${tbAreaClassEntityList}" var="tbClass">
					<option value="${tbClass.id }" <c:if test="${tbClass.id==tbAreaCatalogPage.tbAreaClassEntity.id}">selected="selected"</c:if>>${tbClass.className}</option>
				</c:forEach>
			    </select> <span class="Validform_checktip">请选择大类</span></td>
					
				</tr>
			</table>
		</t:formvalid>
 </body>