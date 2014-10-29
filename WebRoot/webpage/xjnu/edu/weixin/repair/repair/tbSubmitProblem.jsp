<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>维修人员信息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //初始化下标
	function resetTrNum(tableId) {
		$tbody = $("#"+tableId+"");
		$tbody.find('>tr').each(function(i){
			$(':input, select', this).each(function(){
				var $this = $(this), name = $this.attr('name'), val = $this.val();
				if(name!=null){
					if (name.indexOf("#index#") >= 0){
						$this.attr("name",name.replace('#index#',i));
					}else{
						var s = name.indexOf("[");
						var e = name.indexOf("]");
						var new_name = name.substring(s+1,e);
						$this.attr("name",name.replace(new_name,i));
					}
				}
			});
			$(this).find('div[name=\'xh\']').html(i+1);
		});
	}
 </script>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="tbSubmitProblemController.do?save">
			<input id="id" name="id" type="hidden" value="${tbSubmitProblemPage.id }">
			<input id="problemCount" name="problemCount" type="hidden" value="${tbSubmitProblemPage.problemCount}">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
			
			
				<tr>
					<td align="right">
						<label class="Validform_label">
							用户外键:
						</label>
					</td>
					<td class="value">
				
				<select id="userId" name="userId" datatype="*">
				  <c:forEach items="${weixinuserinfoList}" var="tbuser">
					<option value="${tbuser.id }" <c:if test="${tbuser.id==tbSubmitProblemPage.userId}">selected="selected"</c:if>>${tbuser.id}</option>
				</c:forEach>
			    </select> 
			    <span class="Validform_checktip">请选择用户</span></td>
					</td>
				</tr>
				<tr>
					<td align="right">
				<label class="Validform_label">
							地址外键:
						</label>
					</td>
					<td class="value">
					<select id="tbAddressEntity.Id" name="tbAddressEntity.Id" datatype="*">
				  <c:forEach items="${tbAddressEntityList}" var="tbAddress">
					<option value="${tbAddress.id }" <c:if test="${tbAddress.id==tbSubmitProblemPage.tbAddressEntity.id}">selected="selected"</c:if>>${tbAddress.houseCode}</option>
				</c:forEach>
			    </select> <span class="Validform_checktip">请选择地址:</span></td>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							故障类型外键:
						</label>
					</td>
					<td class="value">
						<select id="problemTypeId" name="problemTypeId" datatype="*">
				  			<c:forEach items="${tbproblemtypelist}" var="tbproblemtype">
									<option value="${tbproblemtype.id}" <c:if test="${tbSubmitProblemPage.problemTypeId==tbproblemtype.id}">selected="selected"</c:if>>${tbproblemtype.typeName }</option>
							</c:forEach>
			    		</select> 
			    		<span class="Validform_checktip">请选择故障类型</span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							问题描述:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="problemDescriptionf" name="problemDescriptionf" ignore="ignore"
							   value="${tbSubmitProblemPage.problemDescriptionf}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							当前状态:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="state" name="state" ignore="ignore"
							   value="${tbSubmitProblemPage.state}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							保修时间:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="submitTime" name="submitTime" ignore="ignore"
							     value="<fmt:formatDate value='${tbSubmitProblemPage.submitTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							完成时间:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="completeTime" name="completeTime" ignore="ignore"
							     value="<fmt:formatDate value='${tbSubmitProblemPage.completeTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		<div style="width: auto;height: 200px;">
				<%-- 增加一个div，用于调节页面大小，否则默认太小 --%>
				<div style="width:690px;height:1px;"></div>
				<t:tabs id="tt" iframe="false" tabPosition="top" fit="false">
				 <t:tab href="tbSubmitProblemController.do?tbProblemOperateRecordList&id=${tbSubmitProblemPage.id}" icon="icon-search" title="维修记录" id="tbProblemOperateRecorditem"></t:tab>
				
				</t:tabs>
			</div>
			</t:formvalid>
			<!--维修记录模版 -->
		<table style="display:none">
		<tbody id="add_tbProblemOperateRecord_table_template">
			<tr>
			 <td align="center"><div style="width: 25px;" name="xh"></div></td>
			 <td align="center"><input style="width:20px;" type="checkbox" name="ck"/></td>
				  <td align="left"><input name="tbProblemOperateRecordList[#index#].operateState" maxlength="50" type="text" style="width:120px;"></td>
				  <td align="left"><select name="tbProblemOperateRecordList[#index#].operateId" maxlength="50" type="text" style="width:120px;">
				  				<c:forEach items="${tbProblemOperaterList}" var="tbproblemoperater">
									<option value="${tbproblemoperater.id}" >${tbproblemoperater.userName }</option>
							</c:forEach></select></td>
				  <td align="left"><input  class="Wdate" name="tbProblemOperateRecordList[#index#].operateTime" maxlength="250" type="text" style="width:120px;" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" ></td>
				 
			</tr>
		 </tbody>
		
		</table>
 </body>