<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>建筑管理</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="tbBuilddingController.do?save">
			<input id="id" name="id" type="hidden" value="${tbBuilddingPage.id }">
			
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
			
				<tr>
					<td align="right">
						<label class="Validform_label">
							建筑名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="builddingName" name="builddingName" ignore="ignore"
							   value="${tbBuilddingPage.builddingName}">
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
							   value="${tbBuilddingPage.schoolId}">
						<span class="Validform_checktip"></span>
					</td> --%>
					
					
					<td class="value"><select id="schoolId" name="schoolId" datatype="*">
				  <c:forEach items="${tbSchoolEntityList}" var="tbSchool">
					<option value="${tbSchool.id }" <c:if test="${tbSchool.id==tbBuilddingPage.schoolId}">selected="selected"</c:if>>${tbSchool.schoolName}</option>
				</c:forEach>
			    </select> <span class="Validform_checktip">请选择学校</span></td>
					
					
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							显示顺序:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="displaySequence" name="displaySequence" ignore="ignore"
							   value="${tbBuilddingPage.displaySequence}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				
				
				<tr>
					<td align="right">
						<label class="Validform_label">
							描述:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="builddingDescription" name="builddingDescription" ignore="ignore"
							   value="${tbBuilddingPage.builddingDescription}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							建筑小类外键:
						</label>
					</td>
					<%-- <td class="value">
						<input class="inputxt" id="catalogId" name="catalogId" ignore="ignore"
							   value="${tbBuilddingPage.catalogId}">
						<span class="Validform_checktip"></span>
					</td> --%>
					
					<td class="value"><select id="catalogId" name="catalogId" datatype="*">
				  <c:forEach items="${tbBuilddingCatalogEntityList}" var="tbCatalog">
					<option value="${tbCatalog.id }" <c:if test="${tbCatalog.id==tbBuilddingPage.catalogId}">selected="selected"</c:if>>${tbCatalog.catalogName}</option>
				</c:forEach>
			    </select> <span class="Validform_checktip">请选择小类建筑</span></td>
					
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							联系方式:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="phoneTel" name="phoneTel" ignore="ignore"
							   value="${tbBuilddingPage.phoneTel}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							建筑类型:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="builddingType" name="builddingType" ignore="ignore"
							   value="${tbBuilddingPage.builddingType}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							经度:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="jindu" name="jindu" ignore="ignore"
							   value="${tbBuilddingPage.jindu}" datatype="d">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							纬度:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="weidu" name="weidu" ignore="ignore"
							   value="${tbBuilddingPage.weidu}" datatype="d">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				
				
			</table>
		</t:formvalid>
 </body>