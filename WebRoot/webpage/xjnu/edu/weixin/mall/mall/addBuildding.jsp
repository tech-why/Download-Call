<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/context/mytags.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'addillegal.jsp' starting page</title>
    <t:base type="jquery,easyui,tools"></t:base>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
  </head>
  
  <body>
   <form action="http://218.195.213.187:8080/jeewx/tbBuilddingController.do?save" method="post" enctype="multipart/form-data">
  
		<input type="hidden" name="id" >
		<table>
		
		<tr>
   			<td>建筑小类外键</td>
   			<td>
   				<input type="text" name="catalogId">
   			</td>
		</tr> 
		
			<tr>
			<td> 建筑名：</td>
				<td><input type="text"  name="builddingName" ></td>
				</tr>
				
		     <tr>
			<td>学校外键</td>
				<td ><input type="text"  name="schoolId" >
				</tr>
					<tr>
			   <td> 电话：</td>
				<td ><input type="text"   name="phoneTel" ></td>
				</tr>
				<tr>
			   <td> 经度： </td>
				<td ><input type="text"  name="jindu" ></td>
				</tr>
				
				<tr>
			    <td>纬度： </td>
				<td ><input type="text"  name="weidu" ></td>
				</tr>
				
					<tr>
			    <td>描述： </td>
				<td ><input type="text"  name="builddingDescription" ></td>
				</tr>
				
				<tr>
			     <td></td>
				<td><input type="submit"></td>
				</tr> 
		</table>
		
    </form>


  </body>
</html>
