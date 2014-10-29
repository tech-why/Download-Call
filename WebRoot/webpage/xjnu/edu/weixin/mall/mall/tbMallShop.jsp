<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>商铺选择</title>
<t:base type="ckfinder,ckeditor,jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="tbMallShopController.do?save">
			<input id="id" name="id" type="hidden" value="${tbMallShopPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
			
			<tr>
					<td align="right">
						<label class="Validform_label">
							商铺图片:
						</label>
					</td>
					<%-- <td class="value">
						<input class="inputxt" id="shopImageUrl" name="shopImageUrl" ignore="ignore"
							   value="${tbMallShopPage.shopImageUrl}">
						<span class="Validform_checktip"></span>
					</td> --%>
					
					
			       <td class="value"><t:ckfinder name="shopImageUrl" uploadType="Images" value="${tbMallShopPage.shopImageUrl}" width="80" height="60" buttonClass="ui-button" buttonValue="上传图片"></t:ckfinder></td>
					
					
				</tr>
				
				<tr>
					<td align="right">
						<label class="Validform_label">
							商铺名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="shopName" name="shopName" 
							   value="${tbMallShopPage.shopName}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				
				<tr>
					<td align="right">
						<label class="Validform_label">
							商铺描述:
						</label>
					</td>
					<td class="value">
						<textarea class="text" id="shopDescription" name="shopDescription" ignore="ignore"
							   value="${tbMallShopPage.shopDescription}" rows="6" cols="30">${tbMallShopPage.shopDescription}</textarea>
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
						<input class="inputxt" id="displaySequence" name="displaySequence" 
							   value="${tbMallShopPage.displaySequence}" datatype="n">
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
						<input class="inputxt" id="telephoneNumber" name="telephoneNumber" ignore="ignore"
							   value="${tbMallShopPage.telephoneNumber}">
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
							   value="${tbMallShopPage.jindu}" datatype="d">
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
							   value="${tbMallShopPage.weidu}" datatype="d">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							商铺类型:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="shopType" name="shopType" ignore="ignore"
							   value="${tbMallShopPage.shopType}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							邮费:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="transportFee" name="transportFee" ignore="ignore"
							   value="${tbMallShopPage.transportFee}" datatype="d">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							免邮费金额:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="freeTransportFeeAmount" name="freeTransportFeeAmount" ignore="ignore"
							   value="${tbMallShopPage.freeTransportFeeAmount}" datatype="d">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>