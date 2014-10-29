<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="tbAddressList" title="地址管理" actionUrl="tbAddressController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
  
   <t:dgCol title="建筑外键" field="tbBuilddingEntity_Id" query="true" replace="${builddingNameReplace }"></t:dgCol>
   <t:dgCol title="联系人" field="contact" query="true"></t:dgCol>
   <t:dgCol title="电话号码" field="phoneNumber" query="true"></t:dgCol>
   <t:dgCol title="移动电话" field="mobileNumber" query="true"></t:dgCol>
   <t:dgCol title="详细地址" field="houseCode" query="true"></t:dgCol>
   <t:dgCol title="是否选中" field="isChoose" query="true"></t:dgCol>
   <t:dgCol title="用户名外键" field="weixinUserinfoEntity_Id" query="true" replace="${openidReplace }"></t:dgCol>
   <t:dgCol title="是否活动" field="isActive" query="true"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="tbAddressController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="tbAddressController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="tbAddressController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="tbAddressController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>