<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="tbMallWorkTimeList" queryMode="group" title="商铺配送时间" actionUrl="tbMallWorkTimeController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="标题" field="title" query="true"></t:dgCol>
   <t:dgCol title="开始时间" field="startTime" query="true"></t:dgCol>
   <t:dgCol title="截止时间" field="endTime" query="true"></t:dgCol>
   <t:dgCol title="订单截止时间" field="lastSubmitTime" query="true"></t:dgCol>
   <t:dgCol title="商铺外键" field="shopId" query="true"></t:dgCol>
   <t:dgCol title="每周休息时间" field="notWorkDay" query="true"></t:dgCol>
   <t:dgCol title="每月休息日期" field="notWorkDate" query="true"></t:dgCol>
   <t:dgCol title="每周休息时间提示" field="notWorkDateExplain" query="true"></t:dgCol>
   <t:dgCol title="每月休息时间提示" field="notWorkDayExplain" query="true"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="tbMallWorkTimeController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="tbMallWorkTimeController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="tbMallWorkTimeController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="tbMallWorkTimeController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>