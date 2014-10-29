<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>维修人员分配表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="tbOperaterBuilddingController.do?save">
			<input id="id" name="id" type="hidden" value="${tbOperaterBuilddingPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							维修人员外键:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="operateorId" name="operateorId" ignore="ignore"
							   value="${tbOperaterBuilddingPage.operateorId}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							建筑外键:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="builddingId" name="builddingId" ignore="ignore"
							   value="${tbOperaterBuilddingPage.builddingId}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>