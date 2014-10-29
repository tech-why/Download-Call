<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>号码详情</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="tbTelItemController.do?save">
			<input id="id" name="id" type="hidden" value="${tbTelItemPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							号码联系人名:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="telItemName" name="telItemName" ignore="ignore"
							   value="${tbTelItemPage.telItemName}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							小类外键:
						</label>
					</td>
					<%-- <td class="value">
						<input class="inputxt" id="catalogId" name="catalogId" ignore="ignore"
							   value="${tbTelItemPage.catalogId}">
						<span class="Validform_checktip"></span>
					</td> --%>
					
					<td class="value"><select id="catalogId" name="catalogId" datatype="*">
				  <c:forEach items="${tbTelCatalogEntityList}" var="tbtelcatalog">
					<option value="${tbtelcatalog.id }" <c:if test="${tbtelcatalog.id==tbTelItemPage.catalogId}">selected="selected"</c:if>>${tbtelcatalog.telCatalogName}</option>
				</c:forEach>
			    </select> <span class="Validform_checktip">请选择小类</span></td>
					
					
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							学校外键:
						</label>
					</td>
					<%-- <td class="value">
						<input class="inputxt" id="schoolId" name="schoolId" ignore="ignore"
							   value="${tbTelClassPage.schoolId}">
						<span class="Validform_checktip"></span>
					</td> --%>
					
					
					<td class="value"><select id="schoolId" name="schoolId" datatype="*">
				  <c:forEach items="${tbSchoolEntityList}" var="tbSchool">
					<option value="${tbSchool.id }" <c:if test="${tbSchool.id==tbMallShopSchoolPage.schoolId}">selected="selected"</c:if>>${tbSchool.schoolName}</option>
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
							   value="${tbTelItemPage.displaySequence}" datatype="n">
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
						<input class="inputxt" id="tel" name="tel" ignore="ignore"
							   value="${tbTelItemPage.tel}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							办公地址:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="officeAddress" name="officeAddress" ignore="ignore"
							   value="${tbTelItemPage.officeAddress}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							办公室名:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="officeName" name="officeName" ignore="ignore"
							   value="${tbTelItemPage.officeName}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>