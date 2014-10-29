<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>送货时间管理</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="tbMallWorkTimeTemplaneController.do?save">
			<input id="id" name="id" type="hidden" value="${tbMallWorkTimeTemplanePage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							标题:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="title" name="title" ignore="ignore"
							   value="${tbMallWorkTimeTemplanePage.title}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							开始时间:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="startTime" name="startTime" ignore="ignore"
							   value="${tbMallWorkTimeTemplanePage.startTime}">
						<span class="Validform_checktip"></span>
					</td> 
					<%-- <td class="value"><input name="startTime" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="width: 150px" value="<fmt:formatDate value='${tbMallWorkTimeTemplanePage.endTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>"
				errormsg="日期格式不正确!" ignore="ignore"><span class="Validform_checktip"></span></td> --%>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							结束时间:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="endTime" name="endTime" ignore="ignore"
							   value="${tbMallWorkTimeTemplanePage.endTime}">
						<span class="Validform_checktip"></span>
					</td> 
					<%-- <td class="value"><input name="endTime" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="width: 150px" value="<fmt:formatDate value='${tbMallWorkTimeTemplanePage.endTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>"
				errormsg="日期格式不正确!" ignore="ignore"><span class="Validform_checktip"></span></td> --%>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							预定截止时间:
						</label>
					</td>
					
					
					<%-- <td class="value"><input name="lastSubmitTime" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="width: 150px" value="<fmt:formatDate value='${tbMallWorkTimeTemplanePage.lastSubmitTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>"
				errormsg="日期格式不正确!" ignore="ignore"><span class="Validform_checktip"></span></td> --%>
		
					 <td class="value">
						<input class="inputxt" id="lastSubmitTime" name="lastSubmitTime" ignore="ignore"
							   value="${tbMallWorkTimeTemplanePage.lastSubmitTime}">
						<span class="Validform_checktip"></span>
					</td> 
					
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							每周休息日:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="notWorkDay" name="notWorkDay" ignore="ignore"
							   value="${tbMallWorkTimeTemplanePage.notWorkDay}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							每月休息日:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="notWorkDate" name="notWorkDate" ignore="ignore"
							   value="${tbMallWorkTimeTemplanePage.notWorkDate}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							每周休息提示:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="notWorkDateExplain" name="notWorkDateExplain" ignore="ignore"
							   value="${tbMallWorkTimeTemplanePage.notWorkDateExplain}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							每月休息提示:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="notWorkDayExplain" name="notWorkDayExplain" ignore="ignore"
							   value="${tbMallWorkTimeTemplanePage.notWorkDayExplain}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>