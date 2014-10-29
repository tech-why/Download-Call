<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>故障处理表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="tbProblemOperateRecordController.do?save">
			<input id="id" name="id" type="hidden" value="${tbProblemOperateRecordPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							报修单外键:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="problemId" name="problemId" ignore="ignore"
							   value="${tbProblemOperateRecordPage.problemId}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							维修状态:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="operateState" name="operateState" ignore="ignore"
							   value="${tbProblemOperateRecordPage.operateState}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							维修人员外键:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="operateId" name="operateId" ignore="ignore"
							   value="${tbProblemOperateRecordPage.operateId}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							处理时间:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="operateTime" name="operateTime" ignore="ignore"
							     value="<fmt:formatDate value='${tbProblemOperateRecordPage.operateTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>