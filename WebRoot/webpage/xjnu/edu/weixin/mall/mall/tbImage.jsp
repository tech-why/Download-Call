<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>图片管理</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="tbImageController.do?save">
			<input id="id" name="id" type="hidden" value="${tbImagePage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							图片关键字:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="imageKey" name="imageKey" ignore="ignore"
							   value="${tbImagePage.imageKey}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							图片名字:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="imageName" name="imageName" ignore="ignore"
							   value="${tbImagePage.imageName}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>