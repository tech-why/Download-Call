<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>地址管理</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="tbAddressController.do?save">
			<input id="id" name="id" type="hidden" value="${tbAddressPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							建筑外键:
						</label>
					</td>
					<%-- <td class="value">
						<input class="inputxt" id="builddingId" name="builddingId" 
							   value="${tbAddressPage.builddingId}" datatype="*">
						<span class="Validform_checktip"></span>
					</td> --%>
					
					<td class="value"><select id="builddingId" name="builddingId" datatype="*">
				  <c:forEach items="${tbBuilddingEntityList}" var="tbBuildding">
					<option value="${tbBuildding.id }" <c:if test="${tbBuildding.id==tbAddressPage.builddingId}">selected="selected"</c:if>>${tbBuildding.builddingName}</option>
				</c:forEach>
			    </select> <span class="Validform_checktip">请选择建筑</span></td>
					
					
					
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							联系人:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="contact" name="contact" ignore="ignore"
							   value="${tbAddressPage.contact}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							电话号码:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="phoneNumber" name="phoneNumber" ignore="ignore"
							   value="${tbAddressPage.phoneNumber}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							移动电话:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="mobileNumber" name="mobileNumber" ignore="ignore"
							   value="${tbAddressPage.mobileNumber}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							详细地址:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="houseCode" name="houseCode" ignore="ignore"
							   value="${tbAddressPage.houseCode}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							用户名外键:
						</label>
					</td>
					<%-- <td class="value">
						<input class="inputxt" id="weixinUserId" name="weixinUserId" ignore="ignore"
							   value="${tbAddressPage.weixinUserId}">
						<span class="Validform_checktip"></span>
					</td> --%>
					
					
					<td class="value"><select id="weixinUserId" name="weixinUserId" datatype="*">
				  <c:forEach items="${weixinUserinforList}" var="tbweixinUserinfor">
					<option value="${tbweixinUserinfor.id }" <c:if test="${tbweixinUserinfor.id==tbAddressPage.weixinUserId}">selected="selected"</c:if>>${tbweixinUserinfor.openid}</option>
				</c:forEach>
			    </select> <span class="Validform_checktip">请选择用户</span></td>
					
					
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							是否活动:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="isActive" name="isActive" ignore="ignore"
							   value="${tbAddressPage.isActive}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				
				<tr>
					<td align="right">
						<label class="Validform_label">
							是否选中:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="isActive" name="isChoose" ignore="ignore"
							   value="${tbAddressPage.isChoose }">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				
			</table>
		</t:formvalid>
 </body>