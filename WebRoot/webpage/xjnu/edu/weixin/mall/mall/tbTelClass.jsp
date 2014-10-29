<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>号码大类</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="tbTelClassController.do?save">
			<input id="id" name="id" type="hidden" value="${tbTelClassPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							号码大类名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="telClassName" name="telClassName" ignore="ignore"
							   value="${tbTelClassPage.telClassName}">
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
						<input class="inputxt" id="displaySequence" name="displaySequence" ignore="ignore"
							   value="${tbTelClassPage.displaySequence}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				
			</table>
		</t:formvalid>
 </body>