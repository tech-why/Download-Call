<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<%--  <script src="${webRoot}/plug-in/accordion/js/leftmenu.js"></script> --%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="tbMallShopList" title="商铺选择" queryMode="group" actionUrl="tbMallShopController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="商铺图片" field="shopImageUrl" imageSize="90,90"></t:dgCol>
   <t:dgCol title="商铺名称" field="shopName" query="true"></t:dgCol>
   <t:dgCol title="显示顺序" field="displaySequence" query="true"></t:dgCol>
   <t:dgCol title="联系电话" field="telephoneNumber" query="true"></t:dgCol>
   <t:dgCol title="经度" field="jindu" query="true"></t:dgCol>
   <t:dgCol title="纬度" field="weidu" query="true"></t:dgCol>
   <t:dgCol title="商铺描述" field="shopDescription" ></t:dgCol>
   <t:dgCol title="商铺类型" field="shopType" query="true"></t:dgCol>
   <t:dgCol title="邮费" field="transportFee" query="true"></t:dgCol>
   
   <t:dgCol title="免邮费金额" field="freeTransportFeeAmount" query="true"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="tbMallShopController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="tbMallShopController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="tbMallShopController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="tbMallShopController.do?addorupdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="商品录入" icon="icon-add" url="tbMallProductController.do?shangpinluru" funname="update"></t:dgToolBar>
  
  </t:datagrid>
  </div>
 </div>
 
 <!-- <script>
 function addTab(subtitle, url, icon) {
	var progress = $("div.messager-progress");
	if(progress.length){return;}
	rowid="";
	$.messager.progress({
		text : '页面加载中....',
		interval : 200
	});
	if (! mytabs.tabs('exists', subtitle)) {
		//判断是否进行href方式打开tab，默认为iframe方式
		if(url.indexOf('isHref') != -1){
			$('#maintabs').tabs('add', {
				title : subtitle,
				href : url,
				closable : true,
				icon : icon
			});	
		}else{
			
			$('#maintabs').tabs('add', {
				title : subtitle,
				content : '<iframe src="' + url + '" frameborder="0" style="border:0;width:100%;height:99.4%;"></iframe>',
				closable : true,
				icon : icon
			});		
			
		}

	} else {
		$('#maintabs').tabs('select', subtitle);
		$.messager.progress('close');
	} 

	 mytabs.tabs('select',subtitle);
	/* tabClose(); */

}
 
 </script> -->
 