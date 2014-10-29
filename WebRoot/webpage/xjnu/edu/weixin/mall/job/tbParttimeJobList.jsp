<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="tbParttimeJobList" title="兼职" actionUrl="tbParttimeJobController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="图片" field="imagesUrl" imageSize="80,60"></t:dgCol>
   <t:dgCol title="工作名称" field="name" query="true"></t:dgCol>
   <t:dgCol title="工资外键" field="tbSalaryTypeEntity_Id" replace="${ salaryTypeNameReplace}" query="true"></t:dgCol>
   <t:dgCol title="工资" field="salary" query="true"></t:dgCol>
   <t:dgCol title="需要的人数" field="needConnt" query="true"></t:dgCol>
   <t:dgCol title="工作描述" field="jobDescription" query="true"></t:dgCol>
   <t:dgCol title="工作时间" field="workTime" query="true"></t:dgCol>
   <t:dgCol title="公司或家长外键" field="tbCompanyEntity_Id" replace="${ nameReplace}" query="true"></t:dgCol>
   <t:dgCol title="工作小类外键" field="tbJobCatalogEntity_Id" replace="${jobCatalogNameReplace }" query="true"></t:dgCol>
   <t:dgCol title="地址" field="address" query="true"></t:dgCol>
   <t:dgCol title="区域小类外键" field="tbAreaCatalogEntity_Id" replace="${catalogNameReplace }" query="true"></t:dgCol>
   <t:dgCol title="创建时间" field="creatTime" formatter="yyyy-MM-dd hh:mm:ss" query="true"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="tbParttimeJobController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="tbParttimeJobController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="tbParttimeJobController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="tbParttimeJobController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>