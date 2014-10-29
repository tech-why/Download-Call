<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>商品上架</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="tbMallProductController.do?save">
			<input id="id" name="id" type="hidden" value="${tbMallProductPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							商品名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="productName" name="productName" 
							   value="${tbMallProductPage.productName}" datatype="*">
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
							   value="${tbMallProductPage.displaySequence}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							商品描述:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="productDescription" name="productDescription" ignore="ignore"
							   value="${tbMallProductPage.productDescription}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							商品图片:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="productImageUrl" name="productImageUrl" ignore="ignore"
							   value="${tbMallProductPage.productImageUrl}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							商铺外键:
						</label>
					</td>
					 <td class="value">
						<input class="inputxt" id="shopId" name="shopId" ignore="ignore"
							   value="${name}">
						<span class="Validform_checktip"></span>
					</td> 
					
					<%-- <td class="value"><select id="shopId" name="shopId" datatype="*">
				  <c:forEach items="${tbMallShopEntityList}" var="tbShop">
					<option value="${tbShop.id }" <c:if test="${tbShop.id==tbMallProductPage.shopId}">selected="selected"</c:if>>${tbShop.shopName}</option>
				</c:forEach>
			    </select> <span class="Validform_checktip">请选择商铺</span></td> --%>
					
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							销量:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="saleCount" name="saleCount" ignore="ignore"
							   value="${tbMallProductPage.saleCount}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							是否活动:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="isActive" name="isActive" ignore="ignore"
							   value="${tbMallProductPage.isActive}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							是否显示:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="isDisplay" name="isDisplay" ignore="ignore"
							   value="${tbMallProductPage.isDisplay}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							剩余数量:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="remainAmount" name="remainAmount" ignore="ignore"
							   value="${tbMallProductPage.remainAmount}" datatype="d">
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
							   value="${tbMallProductPage.unitAmount}" datatype="d">
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
							   value="${tbMallProductPage.scoreCoefficient}" datatype="d">
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
							   value="${tbMallProductPage.productUnit}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							单价:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="productPrice" name="productPrice" ignore="ignore"
							   value="${tbMallProductPage.productPrice}" datatype="d">
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
							   value="${tbMallProductPage.catalogId}">
						<span class="Validform_checktip"></span>
					</td> --%>
					
					<td class="value"><select id="catalogId" name="catalogId" datatype="*">
				  <c:forEach items="${tbMallProductCatalogEntityList}" var="tbcatalog">
					<option value="${tbcatalog.id }" <c:if test="${tbcatalog.id==tbMallProductPage.catalogId}">selected="selected"</c:if>>${tbcatalog.catalogName}</option>
				</c:forEach>
			    </select> <span class="Validform_checktip">请选择小类</span></td>
					
				</tr>
			</table>
		</t:formvalid>
 </body>