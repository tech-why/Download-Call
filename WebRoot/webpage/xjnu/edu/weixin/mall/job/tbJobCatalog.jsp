<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>工作小类</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="tbJobCatalogController.do?save">
			<input id="id" name="id" type="hidden" value="${tbJobCatalogPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							小类名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="jobCatalogName" name="jobCatalogName" ignore="ignore"
							   value="${tbJobCatalogPage.jobCatalogName}">
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
							   value="${tbJobCatalogPage.displayIndex}" datatype="n">
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
						<input class="inputxt" id="jobClassId" name="jobClassId" ignore="ignore"
							   value="${tbJobCatalogPage.jobClassId}">
						<span class="Validform_checktip"></span>
					</td> --%>
					
					<td class="value"><select id="tbJobClassEntity.Id" name="tbJobClassEntity.Id" datatype="*">
				  <c:forEach items="${tbJobClassEntityList}" var="tbClass">
					<option value="${tbClass.id }" <c:if test="${tbClass.id==tbJobCatalogPage.tbJobClassEntity.id}">selected="selected"</c:if>>${tbClass.jobClassName}</option>
				</c:forEach>
			    </select> <span class="Validform_checktip">请选择大类</span></td>
					
				</tr>
			</table>
		</t:formvalid>
 </body>