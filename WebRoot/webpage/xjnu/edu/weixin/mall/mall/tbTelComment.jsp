<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>号码评论</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="tbTelCommentController.do?save">
			<input id="id" name="id" type="hidden" value="${tbTelCommentPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							评论内容:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="content" name="content" ignore="ignore"
							   value="${tbTelCommentPage.content}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							号码外键:
						</label>
					</td>
					<%-- <td class="value">
						<input class="inputxt" id="itemId" name="itemId" ignore="ignore"
							   value="${tbTelCommentPage.itemId}">
						<span class="Validform_checktip"></span>
					</td> --%>
					
					<td class="value"><select id="itemId" name="itemId" datatype="*">
				  <c:forEach items="${tbTelItemEntityList}" var="tbtelitem">
					<option value="${tbtelitem.id }" <c:if test="${tbtelitem.id==tbTelCommentPage.itemId}">selected="selected"</c:if>>${tbtelitem.telItemName}</option>
				</c:forEach>
			    </select> <span class="Validform_checktip">请选择号码</span></td>
					
					
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							显示顺序:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="displaySequence" name="displaySequence" ignore="ignore"
							   value="${tbTelCommentPage.displaySequence}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							评论时间:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="commentDate" name="commentDate" ignore="ignore"
							     value="<fmt:formatDate value='${tbTelCommentPage.commentDate}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							开放id:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="openuserid" name="openuserid" ignore="ignore"
							   value="${tbTelCommentPage.openuserid}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>