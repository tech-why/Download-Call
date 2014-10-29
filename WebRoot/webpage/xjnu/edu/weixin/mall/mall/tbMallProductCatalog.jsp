<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>小类管理</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="tbMallProductCatalogController.do?save">
			<input id="id" name="id" type="hidden" value="${tbMallProductCatalogPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							小类名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="catalogName" name="catalogName" 
							   value="${tbMallProductCatalogPage.catalogName}" datatype="*">
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
						<input class="inputxt" id="displaySequence" name="displaySequence" 
							   value="${tbMallProductCatalogPage.displaySequence}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							类别图片:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="catalogImageUrl" name="catalogImageUrl" ignore="ignore"
							   value="${tbMallProductCatalogPage.catalogImageUrl}">
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
							   value="${tbMallProductCatalogPage.classId}">
						<span class="Validform_checktip"></span>
					</td> --%>
					
					
					<td class="value"><select id="classId" name="classId" datatype="*">
				  <c:forEach items="${tbMallProductClassEntityList}" var="tbClass">
					<option value="${tbClass.id }" <c:if test="${tbClass.id==tbMallProductCatalogPage.classId}">selected="selected"</c:if>>${tbClass.className}</option>
				</c:forEach>
			    </select> <span class="Validform_checktip">请选择大类</span></td>
					
				</tr>
			</table>
		</t:formvalid>
 </body>