// if( window.history.length == 1 ){
// 	$('#pageBack').hide();
// };

if (window.WeixinJSBridge) {
	WeixinJSBridge.call('hideToolbar');
} else {
	var _timeout_WX_regist = setTimeout(function() {
		if (window.WeixinJSBridge) {
			WeixinJSBridge.call('hideToolbar');
		}
	}, 1000);
	document.addEventListener("WeixinJSBridgeReady", function() {
		clearTimeout(_timeout_WX_regist);
		WeixinJSBridge.call('hideToolbar');
	});
}