<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="tbResumeList" title="简历" actionUrl="tbResumeController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
  <t:dgCol title="图片" field="picture" imageSize="80,60"></t:dgCol>
   <t:dgCol title="姓名" field="name" query="true"></t:dgCol>
   <t:dgCol title="性别" field="gender" query="true"></t:dgCol>
   <t:dgCol title="生日" field="brithday" formatter="yyyy-MM-dd hh:mm:ss" query="true"></t:dgCol>
   <t:dgCol title="区域" field="area" query="true"></t:dgCol>
   <%-- <t:dgCol title="学校" field="schoolId" query="true"></t:dgCol> --%>
   <t:dgCol title="学校外键" field="tbSchoolEntity_Id" query="true" replace="${schoolReplace }"></t:dgCol>
   <t:dgCol title="期望职位" field="expirePosition" query="true"></t:dgCol>
   <t:dgCol title="电话" field="phoneNumber" query="true"></t:dgCol>
   <t:dgCol title="自我介绍" field="selfDiscription" query="true"></t:dgCol>
   
   <t:dgCol title="QQ" field="qq" query="true"></t:dgCol>
   <t:dgCol title="电子邮件" field="email" query="true"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="tbResumeController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="tbResumeController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="tbResumeController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="tbResumeController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>