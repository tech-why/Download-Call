<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="tbMallWorkTimeTemplaneList"  title="送货时间管理" actionUrl="tbMallWorkTimeTemplaneController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="标题" field="title" query="true"></t:dgCol>
   <t:dgCol title="开始时间" field="startTime" query="true"></t:dgCol>
   <t:dgCol title="结束时间" field="endTime" query="true"></t:dgCol>
   <t:dgCol title="预定截止时间" field="lastSubmitTime" query="true"></t:dgCol>
   <t:dgCol title="每周休息日" field="notWorkDay" query="true"></t:dgCol>
   <t:dgCol title="每月休息日" field="notWorkDate" query="true"></t:dgCol>
   <t:dgCol title="每周休息提示" field="notWorkDateExplain" query="true"></t:dgCol>
   <t:dgCol title="每月休息提示" field="notWorkDayExplain" query="true"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="tbMallWorkTimeTemplaneController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="tbMallWorkTimeTemplaneController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="tbMallWorkTimeTemplaneController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="tbMallWorkTimeTemplaneController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 