<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>商铺配送时间</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="tbMallWorkTimeController.do?save">
			<input id="id" name="id" type="hidden" value="${tbMallWorkTimePage.id }">
			<table  cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							标题:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="title" name="title" ignore="ignore"
							   value="${tbMallWorkTimePage.title}">
						<span class="Validform_checktip"></span>
					</td>
					
					<td align="right">
						<label class="Validform_label">
							开始时间:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="startTime" name="startTime" ignore="ignore"
							   value="${tbMallWorkTimePage.startTime}">
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							截止时间:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="endTime" name="endTime" ignore="ignore"
							   value="${tbMallWorkTimePage.endTime}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				
				
				
				<tr>
					<td align="right">
						<label class="Validform_label">
							订单截止时间:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="lastSubmitTime" name="lastSubmitTime" ignore="ignore"
							   value="${tbMallWorkTimePage.lastSubmitTime}">
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							商铺外键:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="shopId" name="shopId" ignore="ignore"
							   value="${tbMallWorkTimePage.shopId}">
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							每周休息时间:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="notWorkDay" name="notWorkDay" ignore="ignore"
							   value="${tbMallWorkTimePage.notWorkDay}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				
				
				
			
				
				<tr>
					<td align="right">
						<label class="Validform_label">
							每月休息日期:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="notWorkDate" name="notWorkDate" ignore="ignore"
							   value="${tbMallWorkTimePage.notWorkDate}">
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							每周休息时间提示:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="notWorkDateExplain" name="notWorkDateExplain" ignore="ignore"
							   value="${tbMallWorkTimePage.notWorkDateExplain}">
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							每月休息时间提示:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="notWorkDayExplain" name="notWorkDayExplain" ignore="ignore"
							   value="${tbMallWorkTimePage.notWorkDayExplain}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				
			</table>
		</t:formvalid>
		
		
		<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="tbMallWorkTimeTemplaneList"  title="送货时间管理" actionUrl="tbMallWorkTimeTemplaneController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="标题" field="title" query="true"></t:dgCol>
   <t:dgCol title="开始时间" field="startTime" ></t:dgCol>
   <t:dgCol title="结束时间" field="endTime" ></t:dgCol>
   <t:dgCol title="预定截止时间" field="lastSubmitTime" ></t:dgCol>
   <t:dgCol title="每周休息日" field="notWorkDay" ></t:dgCol>
   <t:dgCol title="每月休息日" field="notWorkDate" ></t:dgCol>
   <t:dgCol title="每周休息提示" field="notWorkDateExplain" ></t:dgCol>
   <t:dgCol title="每月休息提示" field="notWorkDayExplain" ></t:dgCol>
  <%--  <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="tbMallWorkTimeTemplaneController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="tbMallWorkTimeTemplaneController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="tbMallWorkTimeTemplaneController.do?addorupdate" funname="update"></t:dgToolBar> --%>
   <t:dgToolBar title="提取" icon="icon-add" funname="workTime"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script type="text/javascript">
   function workTime(title,url,id){
	var rowData=$('#'+id).datagrid('getSelected');
	if(!rowData){tip('请选择条目');return;}
   workTimeId='&workTimeId='+rowData.id;
   console.log(workTimeId);
   $.ajax({
	type:"post",
	url:"tbMallWorkTimeController.do?addWorkTime"+workTimeId,
	dataType:"json",
	success:
	function(data){
	document.getElementById("title").value=check(data.obj.title);
	document.getElementById("startTime").value=check(data.obj.startTime);
	document.getElementById("endTime").value=check(data.obj.endTime);
	document.getElementById("lastSubmitTime").value=check(data.obj.lastSubmitTime);
	document.getElementById("notWorkDay").value=check(data.obj.notWorkDay);
	document.getElementById("notWorkDate").value=check(data.obj.notWorkDate);
	document.getElementById("notWorkDateExplain").value=check(data.obj.notWorkDateExplain);
	document.getElementById("notWorkDayExplain").value=check(data.obj.notWorkDayExplain); 
	},
	});
	function check(json,id){
	if(typeof(json)=='undefined'){
	   return '';
	}else{
	 return json;
	} 
   }
 }
 
 
 </script>
		
 </body>