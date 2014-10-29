<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="tbSubmitProblemList" title="保修单表" actionUrl="tbSubmitProblemController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="报修单序列号" field="problemCount" query="true"></t:dgCol>
   <t:dgCol title="用户外键" field="userId" query="true"></t:dgCol>
   <t:dgCol title="地址外键" field="tbAddressEntity_Id" replace="${AddressNameReplace }" query="true"></t:dgCol>
   <t:dgCol title="故障类型外键" field="problemTypeId" replace="${TypeNameReplace }" query="true"></t:dgCol>
   <t:dgCol title="问题描述" field="problemDescriptionf" query="true"></t:dgCol>
   <t:dgCol title="当前状态" field="state" query="true" ></t:dgCol>
   <t:dgCol title="保修时间" field="submitTime" formatter="yyyy-MM-dd hh:mm:ss" query="true"></t:dgCol>
   <t:dgCol title="完成时间" field="completeTime" formatter="yyyy-MM-dd hh:mm:ss" query="true"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="tbSubmitProblemController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="tbSubmitProblemController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="tbSubmitProblemController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="tbSubmitProblemController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script type="text/javascript">

	$(document).ready(function(){
		$("input[name='submitTime']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});});
		$("input[name='completeTime']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});});
		
	});
</script>
 