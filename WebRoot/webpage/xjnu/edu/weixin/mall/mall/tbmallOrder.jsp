<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>订单管理</title>
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
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" tiptype="1" action="tbmallOrderController.do?save">
			<input id="id" name="id" type="hidden" value="${tbmallOrderPage.id }">
			<table cellpadding="0" cellspacing="1" class="formtable">
			<tr>
			<td align="right"><label class="Validform_label">用户外键:</label></td>
			<%-- <td class="value">
				<input nullmsg="请填写用户外键" errormsg="用户外键格式不对" class="inputxt" id="weixinUserId" name="weixinUserId" ignore="ignore"
									   value="${tbmallOrderPage.weixinUserId}">
								<span class="Validform_checktip"></span>
			</td> --%>
			
			<td class="value"><select id="weixinUserinfoEntity.id" name="weixinUserinfoEntity.id" datatype="*">
				  <c:forEach items="${weixinUserinforList}" var="tbweixin">
					<option value="${tbweixin.id }" <c:if test="${tbweixin.id==tbmallOrderPage.weixinUserinfoEntity.id}">selected="selected"</c:if>>${tbweixin.openid}</option>
				</c:forEach>
			    </select> <span class="Validform_checktip">请选择用户</span></td>
			    
			    
			    
			<td align="right"><label class="Validform_label">地址外键:</label></td>
			<%-- <td class="value">
				<input nullmsg="请填写地址外键" errormsg="地址外键格式不对" class="inputxt" id="addressId" name="addressId" ignore="ignore"
									   value="${tbmallOrderPage.addressId}">
								<span class="Validform_checktip"></span>
			</td> --%>
			
			
			<td class="value"><select id="tbAddressEntity.Id" name="tbAddressEntity.Id" datatype="*">
				  <c:forEach items="${tbAddressEntityList}" var="tbAddress">
					<option value="${tbAddress.id }" <c:if test="${tbAddress.id==tbmallOrderPage.tbAddressEntity.id}">selected="selected"</c:if>>${tbAddress.houseCode}</option>
				</c:forEach>
			    </select> <span class="Validform_checktip">请选择地址</span></td>
			
			
			
			</tr>
			<tr>
			<td align="right"><label class="Validform_label">订单状态:</label></td>
			<td class="value">
				<input nullmsg="请填写订单状态" errormsg="订单状态格式不对" class="inputxt" id="orderState" name="orderState" ignore="ignore"
									   value="${tbmallOrderPage.orderState}">
								<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">预约时间:</label></td>
			<td class="value">
				<input nullmsg="请填写预约时间" errormsg="预约时间格式不对" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="orderRequireTime" name="orderRequireTime" ignore="ignore"
									     value="<fmt:formatDate value='${tbmallOrderPage.orderRequireTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">
								<span class="Validform_checktip"></span>
			</td>
			</tr>
			<tr>
			<td align="right"><label class="Validform_label">完成时间:</label></td>
			<td class="value">
				<input nullmsg="请填写完成时间" errormsg="完成时间格式不对" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="orderCompleteTime" name="orderCompleteTime" ignore="ignore"
									     value="<fmt:formatDate value='${tbmallOrderPage.orderCompleteTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">
								<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">用户留言:</label></td>
			<td class="value">
				<input nullmsg="请填写用户留言" errormsg="用户留言格式不对" class="inputxt" id="userMessage" name="userMessage" ignore="ignore"
									   value="${tbmallOrderPage.userMessage}">
								<span class="Validform_checktip"></span>
			</td>
			</tr>
			<tr>
			<td align="right"><label class="Validform_label">订单积分:</label></td>
			<td class="value">
				<input nullmsg="请填写订单积分" errormsg="订单积分格式不对" class="inputxt" id="orderScore" name="orderScore" ignore="ignore"
									   value="${tbmallOrderPage.orderScore}" datatype="n">
								<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">积分外键:</label></td>
			<td class="value">
				<input nullmsg="请填写积分外键" errormsg="积分外键格式不对" class="inputxt" id="scoreId" name="scoreId" ignore="ignore"
									   value="${tbmallOrderPage.scoreId}">
								<span class="Validform_checktip"></span>
			</td>
			</tr>
			<tr>
			<td align="right"><label class="Validform_label">总价格:</label></td>
			<td class="value">
				<input nullmsg="请填写总价格" errormsg="总价格格式不对" class="inputxt" id="totalPrice" name="totalPrice" ignore="ignore"
									   value="${tbmallOrderPage.totalPrice}" datatype="d">
								<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">订单创建时间:</label></td>
			<td class="value">
				<input nullmsg="请填写订单创建时间" errormsg="订单创建时间格式不对" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="orderCreateTime" name="orderCreateTime" ignore="ignore"
									     value="<fmt:formatDate value='${tbmallOrderPage.orderCreateTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">
								<span class="Validform_checktip"></span>
			</td>
			</tr>
			<tr>
			<td align="right"><label class="Validform_label">订单完成时间:</label></td>
			<td class="value">
				<input nullmsg="请填写订单完成时间" errormsg="订单完成时间格式不对" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="orderLastTime" name="orderLastTime" ignore="ignore"
									     value="<fmt:formatDate value='${tbmallOrderPage.orderLastTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">
								<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">折扣:</label></td>
			<td class="value">
				<input nullmsg="请填写折扣" errormsg="折扣格式不对" class="inputxt" id="unchargeAmount" name="unchargeAmount" ignore="ignore"
									   value="${tbmallOrderPage.unchargeAmount}" datatype="d">
								<span class="Validform_checktip"></span>
			</td>
			</tr>
			<tr>
			<td align="right"><label class="Validform_label">订单明细字符串:</label></td>
			<td class="value">
				<input nullmsg="请填写订单明细字符串" errormsg="订单明细字符串格式不对" class="inputxt" id="orderItemString" name="orderItemString" ignore="ignore"
									   value="${tbmallOrderPage.orderItemString}">
								<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">赠品字符串:</label></td>
			<td class="value">
				<input nullmsg="请填写赠品字符串" errormsg="赠品字符串格式不对" class="inputxt" id="giftString" name="giftString" ignore="ignore"
									   value="${tbmallOrderPage.giftString}">
								<span class="Validform_checktip"></span>
			</td>
			</tr>
			<tr>
			<td align="right"><label class="Validform_label">订单序列号:</label></td>
			<td class="value">
				<input nullmsg="请填写订单序列号" errormsg="订单序列号格式不对" class="inputxt" id="orderCount" name="orderCount" 
									   value="${tbmallOrderPage.orderCount}" datatype="n">
								<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">商铺外键:</label></td>
			<%-- <td class="value">
				<input nullmsg="请填写商铺外键" errormsg="商铺外键格式不对" class="inputxt" id="shopId" name="shopId" ignore="ignore"
									   value="${tbmallOrderPage.shopId}">
								<span class="Validform_checktip"></span>
			</td> --%>
			
			<td class="value"><select id="tbMallShopEntity.Id" name="tbMallShopEntity.Id" datatype="*">
				  <c:forEach items="${tbMallShopEntityList}" var="tbShop">
					<option value="${tbShop.id }" <c:if test="${tbShop.id==tbmallOrderPage.tbMallShopEntity.id}">selected="selected"</c:if>>${tbShop.shopName}</option>
				</c:forEach>
			    </select> <span class="Validform_checktip">请选择商铺</span></td>
			
			
			</tr>
			<tr>
			<td align="right"><label class="Validform_label">学校外键:</label></td>
			<%-- <td class="value">
				<input nullmsg="请填写学校外键" errormsg="学校外键格式不对" class="inputxt" id="schoolId" name="schoolId" ignore="ignore"
									   value="${tbmallOrderPage.schoolId}">
								<span class="Validform_checktip"></span>
			</td> --%>
			
			
			<td class="value"><select id="tbSchoolEntity.Id" name="tbSchoolEntity.Id" datatype="*">
				  <c:forEach items="${tbSchoolEntityList}" var="tbSchool">
					<option value="${tbSchool.id }" <c:if test="${tbSchool.id==tbmallOrderPage.tbSchoolEntity.id}">selected="selected"</c:if>>${tbSchool.schoolName}</option>
				</c:forEach>
			    </select> <span class="Validform_checktip">请选择学校</span></td>
			
					<td align="right">
						<label class="Validform_label">
							商品总数:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="totalCount" name="totalCount" ignore="ignore"
							   value="${tbmallOrderPage.totalCount}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				
			
			
			</tr>
			</table>
			<div style="width: auto;height: 200px;">
				<%-- 增加一个div，用于调节页面大小，否则默认太小 --%>
				<div style="width:690px;height:1px;"></div>
				<t:tabs id="tt" iframe="false" tabPosition="top" fit="false">
				 <t:tab href="tbmallOrderController.do?tbMallOrderItemList&id=${tbmallOrderPage.id}" icon="icon-search" title="订单明细" id="tbMallOrderItem"></t:tab>
				 <t:tab href="tbmallOrderController.do?tbMallOrderOperateList&id=${tbmallOrderPage.id}" icon="icon-search" title="订单跟踪" id="tbMallOrderOperate"></t:tab>
				</t:tabs>
			</div>
			</t:formvalid>
			<!-- 添加 明细 模版 -->
		<table style="display:none">
		<tbody id="add_tbMallOrderItem_table_template">
			<tr>
			 <td align="center"><div style="width: 25px;" name="xh"></div></td>
			 <td align="center"><input style="width:20px;" type="checkbox" name="ck"/></td>
				  <td align="left"><input name="tbMallOrderItemList[#index#].productName" maxlength="50" type="text" style="width:120px;"></td>
				  <td align="left"><input name="tbMallOrderItemList[#index#].productDescription" maxlength="50" type="text" style="width:120px;"></td>
				  <td align="left"><input name="tbMallOrderItemList[#index#].productImageUrl" maxlength="250" type="text" style="width:120px;"></td>
				  <td align="left"><input name="tbMallOrderItemList[#index#].productUnit" maxlength="50" type="text" style="width:120px;"></td>
				  <td align="left"><input name="tbMallOrderItemList[#index#].productPrice" maxlength="" type="text" style="width:120px;"></td>
				  <td align="left"><input name="tbMallOrderItemList[#index#].unitAmount" maxlength="" type="text" style="width:120px;"></td>
				  <td align="left"><input name="tbMallOrderItemList[#index#].scoreCoefficient" maxlength="" type="text" style="width:120px;"></td>
				  <td align="left"><input name="tbMallOrderItemList[#index#].count" maxlength="" type="text" style="width:120px;"></td>
			</tr>
		 </tbody>
		<tbody id="add_tbMallOrderOperate_table_template">
			<tr>
			 <td align="center"><div style="width: 25px;" name="xh"></div></td>
			 <td align="center"><input style="width:20px;" type="checkbox" name="ck"/></td>
				  <td align="left"><input name="tbMallOrderOperateList[#index#].operateType" maxlength="20" type="text" style="width:120px;"></td>
				  <td align="left"><input name="tbMallOrderOperateList[#index#].operateTime" maxlength="" type="text" style="width:120px;"></td>
			</tr>
		 </tbody>
		</table>
 </body>