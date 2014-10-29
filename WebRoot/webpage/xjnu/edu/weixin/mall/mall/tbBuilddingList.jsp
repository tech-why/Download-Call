<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="tbBuilddingList" title="建筑管理" actionUrl="tbBuilddingController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="建筑名称" field="builddingName"  query="true"></t:dgCol>
   <t:dgCol title="学校外键" field="tbSchoolEntity_Id" replace="${schoolReplace}" query="true"> </t:dgCol>
   <t:dgCol title="显示顺序" field="displaySequence"  query="true"></t:dgCol>
   
   <t:dgCol title="描述" field="builddingDescription" query="true"></t:dgCol>
   <t:dgCol title="建筑小类外键" field="tbBuilddingCatalogEntity_Id" replace="${catalogReplace }" query="true"></t:dgCol>
   <t:dgCol title="联系方式" field="phoneTel" query="true"></t:dgCol>
   <t:dgCol title="建筑类型" field="builddingType" query="true"></t:dgCol>
   <t:dgCol title="经度" field="jindu" query="true"></t:dgCol>
   <t:dgCol title="纬度" field="weidu" query="true"></t:dgCol>
   
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="tbBuilddingController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="tbBuilddingController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="tbBuilddingController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="tbBuilddingController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>