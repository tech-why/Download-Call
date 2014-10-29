<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <div region="center" style="padding:1px;">
  <t:datagrid name="tbMallProductList" title="商品上架"  actionUrl="tbMallProductController.do?datagrid&shopId=${tbMallShopname }" idField="id" fit="true" queryMode="group">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="商品图片" field="productImageUrl" imageSize="90,90"></t:dgCol>
   <t:dgCol title="商品名称" field="productName"></t:dgCol>
   <t:dgCol title="显示顺序" field="displaySequence" query="true"></t:dgCol>
   <%-- <t:dgCol title="商品描述" field="productDescription" ></t:dgCol> --%>
   <t:dgCol title="商品描述" field="productDescription" ></t:dgCol>
   <t:dgCol title="商铺外键" field="tbMallShopEntity_Id" replace="${shopReplace }" query="true"></t:dgCol>
   <t:dgCol title="销量" field="saleCount" query="true"></t:dgCol>
   <t:dgCol title="是否活动" field="isActive" query="true"></t:dgCol>
   <t:dgCol title="是否显示" field="isDisplay" query="true"></t:dgCol>
   <t:dgCol title="剩余数量" field="remainAmount" query="true"></t:dgCol>
   <t:dgCol title="单位量" field="unitAmount" query="true"></t:dgCol>
   <t:dgCol title="积分系数" field="scoreCoefficient" query="true"></t:dgCol>
   <t:dgCol title="单位" field="productUnit" query="true"></t:dgCol>
   <t:dgCol title="微信价" field="productPrice" query="true"></t:dgCol>
    <t:dgCol title="参考价" field="normalPrice" query="true"></t:dgCol>
    
    <t:dgCol title="大类外键" field="tbmallProductCatalogEntity_tbMallProductClassEntity_Id" replace="${classNameReplace }" query="true"></t:dgCol>
   <t:dgCol title="小类外键" field="tbmallProductCatalogEntity_Id"  replace="${catalogNameReplace }" query="true"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="tbMallProductController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="tbMallProductController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="tbMallProductController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="tbMallProductController.do?addorupdate" funname="detail"></t:dgToolBar>
   
  </t:datagrid>
  </div>
 </div>
 
 
 <script>
  function bindCascade( dataUrl,parentSelector , childSelector)
{
	$(parentSelector).change(function(){
		$(childSelector).empty();
		var s =$(parentSelector).val() ;
		$.ajax({
			url: dataUrl ,
			type: 'post' ,
			 data: { parentId : $(parentSelector).val()} ,
			dataType: 'json'
		}).done(function( data ) {
			var option = '<option>---请选择---</option>';
			if( data.obj.length > 0 ){
				$.each(data.obj, function( i, item ) {
					option += '<option value = '+ item.id +' > '+item.catalogName+'</option>';
				});
			}
		  $(childSelector).append(option);
	});
  });
}


 bindCascade( 'tbMallProductTemplateController.do?getCatalog' , "[name='tbmallProductCatalogEntity.tbMallProductClassEntity.Id']" , "[name='tbmallProductCatalogEntity.Id']" );
 
 $(childSelector).empty();
 
 </script>