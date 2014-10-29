<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="tbProblemOperaterList" title="维修人员表" actionUrl="tbProblemOperaterController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="编号" field="id" hidden="false" ></t:dgCol>
   <t:dgCol title="维修人员openid" field="weixinUserId"  replace="${idreplace }" query="true" ></t:dgCol>
   <t:dgCol title="维修人员用户名" field="userName" query="true"></t:dgCol>
   <t:dgCol title="维修人员后台用户名" field="userId" replace="${userNameReplace }" query="true"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
 <t:dgDelOpt title="删除" url="tbProblemOperaterController.do?del&id={id}" /> 
  <t:dgFunOpt funname="setfunbyrole(id,userName)" title="地址分配"></t:dgFunOpt>
   <t:dgToolBar title="录入" icon="icon-add" url="tbProblemOperaterController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="tbProblemOperaterController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="tbProblemOperaterController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <div region="east" style="width: 400px;" split="true">
<div tools="#tt" class="easyui-panel" title="地址分配" style="padding: 10px;" fit="true" border="false" id="function-panel"></div>
</div>
<script type="text/javascript">
function setfunbyrole(id,userName) {
	$("#function-panel").panel(
		{
			title :userName+":当前地址",
			href:"tbProblemOperaterController.do?fun&operaterId=" + id
		}
	);
	$('#function-panel').panel("refresh" );
	
}
//删除角色
function delRole(id){
	var tabName= 'roleList';
	var url= 'roleController.do?delRole&id='+id;
	$.dialog.confirm('确定删除该记录吗', function(){
		doSubmit(url,tabName);
		rowid = '';
		$("#function-panel").html("");//删除角色后，清空对应的权限
	}, function(){
	});
}
</script>
<div id="tt"></div>
 
 