<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>兼职</title>
<t:base type="ckfinder,ckeditor,jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="tbParttimeJobController.do?save">
			<input id="id" name="id" type="hidden" value="${tbParttimeJobPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
			<tr>
				<td align="right">
						<label class="Validform_label">
							图片:
						</label>
					</td>
					<td class="value">
					    <t:ckfinder  name="imagesUrl" uploadType="Images" value="${tbParttimeJobPage.imagesUrl}" width="80" height="60" buttonClass="ui-button" buttonValue="上传图片"></t:ckfinder>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							工作名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="name" name="name" ignore="ignore"
							   value="${tbParttimeJobPage.name}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				
				<tr>
					<td align="right">
						<label class="Validform_label">
							工资外键:
						</label>
					</td>
					<%-- <td class="value">
						<input class="inputxt" id="salaryTypeId" name="salaryTypeId" ignore="ignore"
							   value="${tbParttimeJobPage.salaryTypeId}">
						<span class="Validform_checktip"></span>
					</td> --%>
					
					<td class="value"><select id="tbSalaryTypeEntity.id" name="tbSalaryTypeEntity.id" datatype="*">
				  <c:forEach items="${tbSalaryTypeEntityList}" var="tbSalaryType">
					<option value="${tbSalaryType.id }" <c:if test="${tbSalaryType.id==tbParttimeJobPage.tbSalaryTypeEntity.id}">selected="selected"</c:if>>${tbSalaryType.salaryTypeName}</option>
				</c:forEach>
			    </select> <span class="Validform_checktip">请选择工资类别</span></td>
					
					
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							工资:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="salary" name="salary" ignore="ignore"
							   value="${tbParttimeJobPage.salary}" >
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							需要的人数:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="needConnt" name="needConnt" ignore="ignore"
							   value="${tbParttimeJobPage.needConnt}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							工作描述:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="jobDescription" name="jobDescription" ignore="ignore"
							   value="${tbParttimeJobPage.jobDescription}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							工作时间:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="workTime" name="workTime" ignore="ignore"
							   value="${tbParttimeJobPage.workTime}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							公司或家长外键:
						</label>
					</td>
					<%-- <td class="value">
						<input class="inputxt" id="companyId" name="companyId" ignore="ignore"
							   value="${tbParttimeJobPage.companyId}">
						<span class="Validform_checktip"></span>
					</td> --%>
					
					
					<td class="value"><select id="tbCompanyEntity.id" name="tbCompanyEntity.id" datatype="*">
				  <c:forEach items="${tbCompanyEntityList}" var="tbCompany">
					<option value="${tbCompany.id }" <c:if test="${tbCompany.id==tbParttimeJobPage.tbCompanyEntity.id}">selected="selected"</c:if>>${tbCompany.name}</option>
				</c:forEach>
			    </select> <span class="Validform_checktip">请选择公司</span></td>
					
					
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							工作小类外键:
						</label>
					</td>
					<%-- <td class="value">
						<input class="inputxt" id="jobCatalogId" name="jobCatalogId" ignore="ignore"
							   value="${tbParttimeJobPage.jobCatalogId}">
						<span class="Validform_checktip"></span>
					</td> --%>
					
					
					<td class="value"><select id="tbJobCatalogEntity.id" name="tbJobCatalogEntity.id" datatype="*">
				  <c:forEach items="${tbJobCatalogEntityList}" var="tbJobCatalog">
					<option value="${tbJobCatalog.id }" <c:if test="${tbJobCatalog.id==tbParttimeJobPage.tbJobCatalogEntity.id}">selected="selected"</c:if>>${tbJobCatalog.jobCatalogName}</option>
				</c:forEach>
			    </select> <span class="Validform_checktip">请选择工作小类</span></td>
					
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							地址:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="address" name="address" ignore="ignore"
							   value="${tbParttimeJobPage.address}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							区域小类外键:
						</label>
					</td>
					<%-- <td class="value">
						<input class="inputxt" id="areaCatalogId" name="areaCatalogId" ignore="ignore"
							   value="${tbParttimeJobPage.areaCatalogId}">
						<span class="Validform_checktip"></span>
					</td> --%>
					
					<td class="value"><select id="tbAreaCatalogEntity.id" name="tbAreaCatalogEntity.id" datatype="*">
				  <c:forEach items="${tbAreaCatalogEntityList}" var="tbAreaCatalog">
					<option value="${tbAreaCatalog.id }" <c:if test="${tbAreaCatalog.id==tbParttimeJobPage.tbAreaCatalogEntity.id}">selected="selected"</c:if>>${tbAreaCatalog.catalogName}</option>
				</c:forEach>
			    </select> <span class="Validform_checktip">请选择工作小类</span></td>
					
					
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							创建时间:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="creatTime" name="creatTime" ignore="ignore"
							     value="<fmt:formatDate value='${tbParttimeJobPage.creatTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>