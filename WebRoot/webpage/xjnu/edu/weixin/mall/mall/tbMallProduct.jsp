<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery"></t:base>
<!DOCTYPE html>
<html>
 <head>
  <title>商品上架</title>
<t:base type="ckfinder,ckeditor,jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body  scroll="no">
  <div id="cc" class="easyui-layout" fit="true">
 <div region="north" style="height:230px;" >
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="tbMallProductController.do?save">
			<input id="id" name="id" type="hidden" value="${tbMallProductPage.id }">
			<table  cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					
					<td align="right">
						<label class="Validform_label">
							商品名称:
						</label>
					</td>
					<td class="value" id="ID">
						<input class="inputxt" id="productName" name="productName" 
							   value="${tbMallProductPage.productName}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
					
					
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
					
					<td align="right">
						<label class="Validform_label">
							商铺外键:
						</label>
					</td>
					 <%-- <td class="value">
						<input class="inputxt" id="shopId" name="shopId" ignore="ignore"
							   value="${name}">
						<span class="Validform_checktip"></span>
					</td> --%> 
					
					 <td class="value"><select id="shopId" name="shopId" datatype="*">
				  <c:forEach items="${tbMallShopEntityList}" var="tbShop">
					<option value="${tbShop.id }" <c:if test="${tbShop.id==tbMallProductPage.shopId}">selected="selected"</c:if>>${tbShop.shopName}</option>
				</c:forEach>
			    </select> <span class="Validform_checktip">请选择商铺</span></td> 
			    
			    
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
					<td align="right">
						<label class="Validform_label">
							微信价:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="productPrice" name="productPrice" ignore="ignore"
							   value="${tbMallProductPage.productPrice}" datatype="d">
						<span class="Validform_checktip"></span>
					</td>
					
					
					<td align="right">
						<label class="Validform_label">
							参考价:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="normalPrice" name="normalPrice" ignore="ignore"
							   value="${tbMallProductPage.normalPrice}" datatype="d">
						<span class="Validform_checktip"></span>
					</td>
					
					
					
					
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							商品图片:
						</label>
					</td>
					<%-- <td class="value">
						<input class="inputxt" id="productImageUrl" name="productImageUrl" ignore="ignore"
							   value="${tbMallProductPage.productImageUrl}">
						<span class="Validform_checktip"></span>
					</td> --%>
					
					<td class="value">
					    <t:ckfinder  name="productImageUrl" uploadType="Images" value="${tbMallProductPage.productImageUrl}" width="80" height="60" buttonClass="ui-button" buttonValue="上传图片"></t:ckfinder>
					</td>
					<td align="right">
						<label class="Validform_label">
							商品描述:
						</label>
					</td>
					<td class="value">
						<textarea class="inputxt" id="productDescription" name="productDescription" ignore="ignore"
							   value="${tbMallProductPage.productDescription}" rows="6" cols="20">${tbMallProductPage.productDescription}</textarea>
						<span class="Validform_checktip"></span>
					</td>
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
					<option  value="${tbcatalog.id }" <c:if test="${tbcatalog.id==tbMallProductPage.catalogId}">selected="selected"</c:if>>${tbcatalog.catalogName}</option>
				</c:forEach>
			    </select> <span class="Validform_checktip">请选择小类</span></td>
					<td></td>
					<td></td>
				</tr>
				
				
			</table>
		</t:formvalid>
		</div>
		

  <div region="center" style="padding:1px;">
  <t:datagrid name="tbMallProductTemplateList" queryMode="group" title="商品管理" actionUrl="tbMallProductTemplateController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="商品图片" field="productImageUrl" imageSize="90,90"></t:dgCol>
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="商品名称" field="productName" query="true"></t:dgCol>
   <t:dgCol title="显示顺序" field="displaySequence" ></t:dgCol>
  <t:dgCol title="商品描述" field="productDescription" ></t:dgCol>
   <t:dgCol title="单位" field="productUnit" ></t:dgCol>
   <t:dgCol title="微信价" field="productPrice" ></t:dgCol>
   <t:dgCol title="参考价" field="normalPrice" query="true"></t:dgCol>
   <t:dgCol title="单位量" field="unitAmount" ></t:dgCol>
   <t:dgCol title="积分系数" field="scoreCoefficient" ></t:dgCol>
   <t:dgCol title="小类外键" field="tbMallProductCatalogEntity_Id" ></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <%-- <t:dgDelOpt title="上架" url="" /> --%>
  <%--  <t:dgToolBar title="录入" icon="icon-add" url="tbMallProductTemplateController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="tbMallProductTemplateController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="tbMallProductTemplateController.do?addorupdate" funname="detail"></t:dgToolBar>  --%>
   <t:dgToolBar title="上架" icon="icon-add" funname="Product"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 </body>
 <script type="text/javascript">
 function Product(title,url,id){
	var rowData=$('#'+id).datagrid('getSelected');
	if(!rowData){tip('请选择条目');return;}
   productId='&productId='+rowData.id;
   console.log(productId);
   $.ajax({
	type:"post",
	url:"tbMallProductController.do?addproduct"+productId,
	dataType:"json",
	success:
	function(data){
	document.getElementById("productName").value=check(data.obj.productName);
	document.getElementById("displaySequence").value=check(data.obj.displaySequence);
	document.getElementById("productUnit").value=check(data.obj.productUnit);
	document.getElementById("productPrice").value=check(data.obj.productPrice);
	document.getElementById("unitAmount").value=check(data.obj.unitAmount);
	document.getElementById("scoreCoefficient").value=check(data.obj.scoreCoefficient);
	document.getElementById("normalPrice").value=check(data.obj.normalPrice);
	document.getElementById("catalogId").value=check(data.obj.catalogId); 
	document.getElementById("productDescription").innerText=check(data.obj.productDescription);
	/* alert("--------------------"+data.obj.catalogId); */ 
	document.getElementById("productImageUrl_input").value=check(data.obj.productImageUrl);
	$("#productImageUrl_herf").attr("src",check(data.obj.productImageUrl));
	 $("#productImageUrl_herf").removeAttr("hidden"); 
	},
	});
	function check(json,id){
	if(typeof(json)=='undefined'){
	   return '';
	}else{
	 return json;
	} 
   }
 }
 </script>		

 
 