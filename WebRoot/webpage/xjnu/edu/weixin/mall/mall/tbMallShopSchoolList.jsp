<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="tbMallShopSchoolList" queryMode="group" title="分配学校" actionUrl="tbMallShopSchoolController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="商铺外键" field="tbMallShopEntity_Id" query="true" replace="${shopReplace }"></t:dgCol>
   <t:dgCol title="学校外键" field="tbSchoolEntity_Id" query="true" replace="${schoolReplace }"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="tbMallShopSchoolController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="tbMallShopSchoolController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="tbMallShopSchoolController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="tbMallShopSchoolController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>