<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>大类管理</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="tbMallProductClassController.do?save">
			<input id="id" name="id" type="hidden" value="${tbMallProductClassPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							大类名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="className" name="className" 
							   value="${tbMallProductClassPage.className}" datatype="*">
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
							   value="${tbMallProductClassPage.displaySequence}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							大类图片:
						</label>
					</td>
					<%-- <td class="value">
						<input class="inputxt" id="classImageUrl" name="classImageUrl" 
							   value="${tbMallProductClassPage.classImageUrl}" datatype="*">
						<span class="Validform_checktip"></span>
					</td> --%>
					
					<td class="value"><select id="classImageUrl" name="classImageUrl" datatype="*">
				  <c:forEach items="${tbImageEntityList}" var="tbImage">
					<option value="${tbImage.id }" <c:if test="${tbImage.id ==tbMallProductClassPage.classImageUrl}">selected="selected"</c:if>>${tbImage.imageName}</option>
				</c:forEach>
			    </select> <span class="Validform_checktip">请选择大类</span></td>
					
					
				</tr>
			</table>
		</t:formvalid>
 </body>