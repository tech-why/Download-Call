<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	$('#addTbProblemOperateItemBtn').linkbutton({   
	    iconCls: 'icon-add'  
	});  
	$('#delTbProblemOrderItemBtn').linkbutton({   
	    iconCls: 'icon-remove'  
	}); 
	$('#addTbProblemOperateItemBtn').bind('click', function(){   
 		 var tr =  $("#add_tbProblemOperateRecorditem_table_template tr").clone();
	 	 $("#add_tbProblemOperateRecorditem_table").append(tr);
	 	 resetTrNum('add_tbProblemOperateRecorditem_table');
    });  
	$('#delTbProblemOrderItemBtn').bind('click', function(){   
      	$("#add_tbProblemOperateRecorditem_table").find("input:checked").parent().parent().remove();   
        resetTrNum('add_tbProblemOperateRecorditem_table'); 
    }); 
    $(document).ready(function(){
    	$(".datagrid-toolbar").parent().css("width","auto");
    });
</script>
<div style="padding: 3px; height: 25px;width:auto;" class="datagrid-toolbar">
	<a id="addTbProblemOperateItemBtn" href="#">添加</a> <a id="delTbProblemOrderItemBtn" href="#">删除</a> 
</div>
<div style="width: auto;height: 300px;overflow-y:auto;overflow-x:scroll;">
<table border="0" cellpadding="2" cellspacing="0" id="tbProblemOperateRecorditem_table">
	<tr bgcolor="#E6E6E6">
		<td align="center" bgcolor="#EEEEEE">序号</td>
		<td align="center" bgcolor="#EEEEEE">操作</td>
				  <td align="left" bgcolor="#EEEEEE">维修状态:</td>
				  <td align="left" bgcolor="#EEEEEE">维修人员外键:</td>
				  <td align="left" bgcolor="#EEEEEE">处理时间:</td>
	</tr>
	<tbody id="add_tbProblemOperateRecorditem_table">	
	<c:if test="${fn:length(tbProblemOperateRecorditemList)  <= 0 }">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">1</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
				  <td align="left"><input name="tbProblemOperateRecorditemList[0].operateState" maxlength="50" type="text" style="width:120px;" ></td>
				  <td align="left"><select  name="tbProblemOperateRecorditemList[0].operateId" datatype="*" style="width:120px;">
				  			<c:forEach items="${tbProblemOperaterList}" var="tbproblemoperater">
									<option value="${tbproblemoperater.id}" >${tbproblemoperater.userName }</option>
							</c:forEach>
			    		</select></td>
				  <td align="left"><input class="Wdate" name="tbProblemOperateRecorditemList[0].operateTime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  maxlength="250" type="text" style="width:120px;" 
							     ></td>
						
				 	
   			</tr>
	</c:if>
	<c:if test="${fn:length(tbProblemOperateRecorditemList)  > 0 }">
		<c:forEach items="${tbProblemOperateRecorditemList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">${stuts.index+1 }</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
				<input name="tbProblemOperateRecorditemList[${stuts.index }].id"  value="${poVal.id }" type="hidden" >
				   <td align="left"><input name="tbProblemOperateRecorditemList[${stuts.index }].operateState" maxlength="50" value="${poVal.operateState }" type="text" style="width:120px;"></td>
				   <td align="left"><select  name="tbProblemOperateRecorditemList[${stuts.index }].operateId" datatype="*">
				  			<c:forEach items="${tbProblemOperaterList}" var="tbproblemoperater">
									<option value="${tbproblemoperater.id}" <c:if test="${tbproblemoperater.id==poVal.operateId }">selected="selected"</c:if>>${tbproblemoperater.userName }</option>
							</c:forEach>
			    		</select></td>
				   <td align="left"> <input name="tbProblemOperateRecorditemList[${stuts.index }].operateTime" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 120px" 
							     value="<fmt:formatDate value="${poVal.operateTime }"  type="date" pattern="yyyy-MM-dd hh:mm:ss"/>"></td>
				  
						
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>
</div>