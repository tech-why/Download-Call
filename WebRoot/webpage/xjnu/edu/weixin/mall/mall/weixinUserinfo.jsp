<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>微信用户信息管理</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="weixinUserinfoController.do?save">
			<input id="id" name="id" type="hidden" value="${weixinUserinfoPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							添加时间:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="addtime" name="addtime" ignore="ignore"
							   value="${weixinUserinfoPage.addtime}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							联系电话:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="mobile" name="mobile" ignore="ignore"
							   value="${weixinUserinfoPage.mobile}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							开放id:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="openid" name="openid" ignore="ignore"
							   value="${weixinUserinfoPage.openid}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							密码:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="password" name="password" ignore="ignore"
							   value="${weixinUserinfoPage.password}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							学校外键:
						</label>
					</td>
					<%-- <td class="value">
						<input class="inputxt" id="schoolId" name="schoolId" ignore="ignore"
							   value="${weixinUserinfoPage.schoolId}">
						<span class="Validform_checktip"></span>
					</td> --%>
					
					
					<td class="value"><select id="schoolId" name="schoolId" datatype="*">
				  <c:forEach items="${tbSchoolEntityList}" var="tbSchool">
					<option value="${tbSchool.id }" <c:if test="${tbSchool.id==weixinUserinfoPage.schoolId}">selected="selected"</c:if>>${tbSchool.schoolName}</option>
				</c:forEach>
			    </select> <span class="Validform_checktip">请选择学校</span></td>
					
					
				</tr>
			</table>
		</t:formvalid>
 </body>