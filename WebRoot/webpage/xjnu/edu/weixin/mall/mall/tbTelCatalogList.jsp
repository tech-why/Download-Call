<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="tbTelCatalogList" title="号码小类" actionUrl="tbTelCatalogController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="号码小类名称" field="telCatalogName" query="true"></t:dgCol>
  <%--  <t:dgCol title="号码大类外键" field="classId" query="true"></t:dgCol> --%>
   <t:dgCol title="号码大类外键" field="tbTelClassEntity_Id" query="true" replace="${telClassNameReplace }"></t:dgCol>
   <t:dgCol title="显示顺序" field="displaySequence" query="true"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="tbTelCatalogController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="tbTelCatalogController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="tbTelCatalogController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="tbTelCatalogController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>