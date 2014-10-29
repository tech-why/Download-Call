<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	$('#addTbMallOrderItemBtn').linkbutton({   
	    iconCls: 'icon-add'  
	});  
	$('#delTbMallOrderItemBtn').linkbutton({   
	    iconCls: 'icon-remove'  
	}); 
	$('#addTbMallOrderItemBtn').bind('click', function(){   
 		 var tr =  $("#add_tbMallOrderItem_table_template tr").clone();
	 	 $("#add_tbMallOrderItem_table").append(tr);
	 	 resetTrNum('add_tbMallOrderItem_table');
    });  
	$('#delTbMallOrderItemBtn').bind('click', function(){   
      	$("#add_tbMallOrderItem_table").find("input:checked").parent().parent().remove();   
        resetTrNum('add_tbMallOrderItem_table'); 
    }); 
    $(document).ready(function(){
    	$(".datagrid-toolbar").parent().css("width","auto");
    });
</script>
<div style="padding: 3px; height: 25px;width:auto;" class="datagrid-toolbar">
	<a id="addTbMallOrderItemBtn" href="#">添加</a> <a id="delTbMallOrderItemBtn" href="#">删除</a> 
</div>
<div style="width: auto;height: 300px;overflow-y:auto;overflow-x:scroll;">
<table border="0" cellpadding="2" cellspacing="0" id="tbMallOrderItem_table">
	<tr bgcolor="#E6E6E6">
		<td align="center" bgcolor="#EEEEEE">序号</td>
		<td align="center" bgcolor="#EEEEEE">操作</td>
				  <td align="left" bgcolor="#EEEEEE">商品名称</td>
				  <td align="left" bgcolor="#EEEEEE">商品描述</td>
				  <td align="left" bgcolor="#EEEEEE">商品图片</td>
				  <td align="left" bgcolor="#EEEEEE">单位</td>
				  <td align="left" bgcolor="#EEEEEE">价格</td>
				  <td align="left" bgcolor="#EEEEEE">单位量</td>
				  <td align="left" bgcolor="#EEEEEE">积分系数</td>
				  <td align="left" bgcolor="#EEEEEE">数量</td>
	</tr>
	<tbody id="add_tbMallOrderItem_table">	
	<c:if test="${fn:length(tbMallOrderItemList)  <= 0 }">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">1</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck"/></td>
				  <td align="left"><input name="tbMallOrderItemList[0].productName" maxlength="50" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="tbMallOrderItemList[0].productDescription" maxlength="50" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="tbMallOrderItemList[0].productImageUrl" maxlength="250" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="tbMallOrderItemList[0].productUnit" maxlength="50" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="tbMallOrderItemList[0].productPrice" maxlength="" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="tbMallOrderItemList[0].unitAmount" maxlength="" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="tbMallOrderItemList[0].scoreCoefficient" maxlength="" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="tbMallOrderItemList[0].count" maxlength="" type="text" style="width:120px;" ></td>
   			</tr>
	</c:if>
	<c:if test="${fn:length(tbMallOrderItemList)  > 0 }">
		<c:forEach items="${tbMallOrderItemList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">${stuts.index+1 }</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
				<input name="tbMallOrderItemList[${stuts.index }].id"  value="${poVal.id }" type="hidden" >
				   <td align="left"><input name="tbMallOrderItemList[${stuts.index }].productName" maxlength="50" value="${poVal.productName }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="tbMallOrderItemList[${stuts.index }].productDescription" maxlength="50" value="${poVal.productDescription }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="tbMallOrderItemList[${stuts.index }].productImageUrl" maxlength="250" value="${poVal.productImageUrl }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="tbMallOrderItemList[${stuts.index }].productUnit" maxlength="50" value="${poVal.productUnit }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="tbMallOrderItemList[${stuts.index }].productPrice" maxlength="" value="${poVal.productPrice }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="tbMallOrderItemList[${stuts.index }].unitAmount" maxlength="" value="${poVal.unitAmount }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="tbMallOrderItemList[${stuts.index }].scoreCoefficient" maxlength="" value="${poVal.scoreCoefficient }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="tbMallOrderItemList[${stuts.index }].count" maxlength="" value="${poVal.count }" type="text" style="width:120px;"></td>
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>
</div>