<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="tbmallOrderList" title="订单管理" actionUrl="tbmallOrderController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="用户名外键" field="weixinUserinfoEntity_Id" query="true" replace="${openidReplace }"></t:dgCol>
   <t:dgCol title="地址外键" field="tbAddressEntity_Id" query="true" replace="${houseCodeReplace }"></t:dgCol>
   <t:dgCol title="订单状态" field="orderState" query="true"></t:dgCol>
   <t:dgCol title="预约时间" field="orderRequireTime" formatter="yyyy-MM-dd hh:mm:ss" query="true"></t:dgCol>
   <t:dgCol title="完成时间" field="orderCompleteTime" formatter="yyyy-MM-dd hh:mm:ss" query="true"></t:dgCol>
   <t:dgCol title="用户留言" field="userMessage" ></t:dgCol>
   <t:dgCol title="订单积分" field="orderScore" query="true"></t:dgCol>
   
   <t:dgCol title="商品总数" field="totalCount" query="true"></t:dgCol>
   <t:dgCol title="积分外键" field="scoreId" query="true"></t:dgCol>
   <t:dgCol title="总价格" field="totalPrice" query="true"></t:dgCol>
   <t:dgCol title="订单创建时间" field="orderCreateTime" formatter="yyyy-MM-dd hh:mm:ss" query="true"></t:dgCol>
   <t:dgCol title="订单完成时间" field="orderLastTime" formatter="yyyy-MM-dd hh:mm:ss" query="true"></t:dgCol>
   <t:dgCol title="折扣" field="unchargeAmount" query="true"></t:dgCol>
   <t:dgCol title="订单明细字符串" field="orderItemString" ></t:dgCol>
   <t:dgCol title="赠品字符串" field="giftString" ></t:dgCol>
   <t:dgCol title="订单序列号" field="orderCount" query="true"></t:dgCol>
   <t:dgCol title="商铺外键" field="tbMallShopEntity_Id" query="true" replace="${shopReplace }"></t:dgCol>
   <t:dgCol title="学校外键" field="tbSchoolEntity_Id" query="true" replace="${schoolReplace }"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="tbmallOrderController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="tbmallOrderController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="tbmallOrderController.do?addorupdate" funname="update"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 
 
 <script type="text/javascript">

	$(document).ready(function(){
		$("input[name='createDate_begin']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});});
		$("input[name='createDate_end']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});});
		$("input[name='orderRequireTime']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});});
		$("input[name='orderCompleteTime']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});});
		$("input[name='orderCreateTime']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});});
		$("input[name='orderLastTime']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});});
	});
</script>
 