<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>分配学校</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="tbMallShopSchoolController.do?save">
			<input id="id" name="id" type="hidden" value="${tbMallShopSchoolPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							商铺外键:
						</label>
					</td>
					<%-- <td class="value">
						<input class="inputxt" id="shopId" name="shopId" 
							   value="${tbMallShopSchoolPage.shopId}" datatype="*">
						<span class="Validform_checktip"></span>
					</td> --%>
					
					
					
					<td class="value"><select id="shopId" name="shopId" datatype="*">
				  <c:forEach items="${tbMallShopEntityList}" var="tbShop">
					<option value="${tbShop.id }" <c:if test="${tbShop.id==tbMallShopSchoolPage.shopId}">selected="selected"</c:if>>${tbShop.shopName}</option>
				</c:forEach>
			    </select> <span class="Validform_checktip">请选择商铺</span></td>
					
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							学校外键:
						</label>
					</td>
					<%-- <td class="value">
						<input class="inputxt" id="schoolId" name="schoolId" 
							   value="${tbMallShopSchoolPage.schoolId}" datatype="*">
						<span class="Validform_checktip"></span>
					</td> --%>
					
					
					<td class="value"><select id="schoolId" name="schoolId" datatype="*">
				  <c:forEach items="${tbSchoolEntityList}" var="tbSchool">
					<option value="${tbSchool.id }" <c:if test="${tbSchool.id==tbMallShopSchoolPage.schoolId}">selected="selected"</c:if>>${tbSchool.schoolName}</option>
				</c:forEach>
			    </select> <span class="Validform_checktip">请选择学校</span></td>
					
					
				</tr>
			</table>
		</t:formvalid>
 </body>