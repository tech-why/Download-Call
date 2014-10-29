<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>简历</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="tbResumeController.do?save">
			<input id="id" name="id" type="hidden" value="${tbResumePage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							姓名:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="name" name="name" ignore="ignore"
							   value="${tbResumePage.name}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							性别:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="gender" name="gender" ignore="ignore"
							   value="${tbResumePage.gender}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							生日:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="brithday" name="brithday" ignore="ignore"
							     value="<fmt:formatDate value='${tbResumePage.brithday}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							区域:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="area" name="area" ignore="ignore"
							   value="${tbResumePage.area}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							学校:
						</label>
					</td>
					<%-- <td class="value">
						<input class="inputxt" id="schoolId" name="schoolId" ignore="ignore"
							   value="${tbResumePage.schoolId}">
						<span class="Validform_checktip"></span>
					</td> --%>
					
					<td class="value"><select id="tbSchoolEntity.Id" name="tbSchoolEntity.id" datatype="*">
				  <c:forEach items="${tbSchoolEntityList}" var="tbSchool">
					<option value="${tbSchool.id }" <c:if test="${tbSchool.id==tbResumePage.tbSchoolEntity.id}">selected="selected"</c:if>>${tbSchool.schoolName}</option>
				</c:forEach>
			    </select> <span class="Validform_checktip">请选择学校</span></td>
					
					
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							期望职位:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="expirePosition" name="expirePosition" ignore="ignore"
							   value="${tbResumePage.expirePosition}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							电话:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="phoneNumber" name="phoneNumber" ignore="ignore"
							   value="${tbResumePage.phoneNumber}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							自我介绍:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="selfDiscription" name="selfDiscription" ignore="ignore"
							   value="${tbResumePage.selfDiscription}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							图片:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="picture" name="picture" ignore="ignore"
							   value="${tbResumePage.picture}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							QQ:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="qq" name="qq" ignore="ignore"
							   value="${tbResumePage.qq}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							电子邮件:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="email" name="email" ignore="ignore"
							   value="${tbResumePage.email}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>