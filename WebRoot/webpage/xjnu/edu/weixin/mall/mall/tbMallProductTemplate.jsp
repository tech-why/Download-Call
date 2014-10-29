<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>商品管理</title>
<t:base type="ckfinder,ckeditor,jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="tbMallProductTemplateController.do?save">
			<input id="id" name="id" type="hidden" value="${tbMallProductTemplatePage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
			   <tr>
					<td align="right">
						<label class="Validform_label">
							商品图片:
						</label>
					</td>
					<%-- <td class="value">
						<input class="inputxt" id="productImageUrl" name="productImageUrl" ignore="ignore"
							   value="${tbMallProductTemplatePage.productImageUrl}">
						<span class="Validform_checktip"></span>
					</td> --%>
					
					<td class="value">
					    <t:ckfinder name="productImageUrl" uploadType="Images" value="${tbMallProductTemplatePage.productImageUrl}" width="80" height="60" buttonClass="ui-button" buttonValue="上传图片"></t:ckfinder>
					</td>
					
				</tr>
			
				<tr>
					<td align="right">
						<label class="Validform_label">
							商品名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="productName" name="productName" 
							   value="${tbMallProductTemplatePage.productName}" datatype="*">
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
							   value="${tbMallProductTemplatePage.displaySequence}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				
				<tr>
					<td align="right">
						<label class="Validform_label">
							单位:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="productUnit" name="productUnit" ignore="ignore"
							   value="${tbMallProductTemplatePage.productUnit}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							微信价:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="productPrice" name="productPrice" ignore="ignore"
							   value="${tbMallProductTemplatePage.productPrice}" datatype="d">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				
				<tr>
					<td align="right">
						<label class="Validform_label">
							参考价:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="normalPrice" name="normalPrice" ignore="ignore"
							   value="${tbMallProductTemplatePage.normalPrice}" datatype="d">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				
				
				<tr>
					<td align="right">
						<label class="Validform_label">
							单位量:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="unitAmount" name="unitAmount" ignore="ignore"
							   value="${tbMallProductTemplatePage.unitAmount}" datatype="d">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							积分系数:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="scoreCoefficient" name="scoreCoefficient" ignore="ignore"
							   value="${tbMallProductTemplatePage.scoreCoefficient}" datatype="d">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							小类外键:
						</label>
					</td>
					<%-- <td class="value">tbMallProductCatalogEntityList
						<input class="inputxt" id="catalogId" name="catalogId" ignore="ignore"
							   value="${tbMallProductTemplatePage.catalogId}">
						<span class="Validform_checktip"></span>
					</td> --%>
					
					<td class="value"><select id="catalogId" name="catalogId" datatype="*">
				  <c:forEach items="${tbMallProductCatalogEntityList}" var="tbcatalog">
					<option value="${tbcatalog.id }" <c:if test="${tbcatalog.id==tbMallProductTemplatePage.catalogId}">selected="selected"</c:if>>${tbcatalog.catalogName}</option>
				</c:forEach>
			    </select> <span class="Validform_checktip">请选择小类</span></td>
				</tr>
				
				<tr>
					<td align="right">
						<label class="Validform_label">
							商品描述:
						</label>
					</td>
					<td class="value">
						<textarea class="text" id="productDescription" name="productDescription" ignore="ignore"
							   value="${tbMallProductTemplatePage.productDescription}" rows="6" cols="30">${tbMallProductTemplatePage.productDescription}</textarea>
						<span class="Validform_checktip"></span>
					</td>
					
				</tr>
				
			</table>
		</t:formvalid>
 </body>