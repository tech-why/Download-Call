<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>工作表</title>
<t:base type="ckfinder,ckeditor,jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="tbJobController.do?save">
			<input id="id" name="id" type="hidden" value="${tbJobPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
			<tr>
				<td align="right">
						<label class="Validform_label">
							图片:
						</label>
					</td>
					<td class="value">
					    <t:ckfinder  name="imagesUrl" uploadType="Images" value="${tbJobPage.imagesUrl}" width="80" height="60" buttonClass="ui-button" buttonValue="上传图片"></t:ckfinder>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							职位名:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="positionname" name="positionname" ignore="ignore"
							   value="${tbJobPage.positionname}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							工资:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="salary" name="salary" ignore="ignore"
							   value="${tbJobPage.salary}" >
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							需要的人数:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="needConnt" name="needConnt" ignore="ignore"
							   value="${tbJobPage.needConnt}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							公司外键:
						</label>
					</td>
					<%-- <td class="value">
						<input class="inputxt" id="companyId" name="companyId" ignore="ignore"
							   value="${tbJobPage.companyId}">
						<span class="Validform_checktip"></span>
					</td> --%>
					
					
					<td class="value"><select id="tbCompanyEntity.id" name="tbCompanyEntity.id" datatype="*">
				  <c:forEach items="${tbCompanyEntityList}" var="tbCompany">
					<option value="${tbCompany.id }" <c:if test="${tbCompany.id==tbJobPage.tbCompanyEntity.id}">selected="selected"</c:if>>${tbCompany.name}</option>
				</c:forEach>
			    </select> <span class="Validform_checktip">请选择公司</span></td>
					
					
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							区域外键:
						</label>
					</td>
					<td class="value"><select id="tSTerritory.Id" name="tSTerritory.Id" datatype="*">
				  <c:forEach items="${tsTerritoryList}" var="territory">
					<option value="${territory.id }" <c:if test="${territory.id==tbJobPage.tSTerritory.id}">selected="selected"</c:if>>${territory.territoryName}</option>
				</c:forEach>
			    </select> <span class="Validform_checktip">请选择区域</span></td>
					
					
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							工作描述:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="jobDescription" name="jobDescription" ignore="ignore"
							   value="${tbJobPage.jobDescription}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							创建时间:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="creatTime" name="creatTime" ignore="ignore"
							     value="<fmt:formatDate value='${tbJobPage.creatTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							工作场所:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="workPlace" name="workPlace" ignore="ignore"
							   value="${tbJobPage.workPlace}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>