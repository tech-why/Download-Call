<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>学校管理</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="tbSchoolController.do?save">
			<input id="id" name="id" type="hidden" value="${tbSchoolPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							学校名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="schoolName" name="schoolName" ignore="ignore"
							validType="tb_school,school_name,id"   value="${tbSchoolPage.schoolName}" datatype="s2-10">
						<span class="Validform_checktip">学校名称范围在2~10位字符</span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							学校描述:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="schoolDescription" name="schoolDescription" ignore="ignore"
							   value="${tbSchoolPage.schoolDescription}">
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
							   value="${tbSchoolPage.displaySequence}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>