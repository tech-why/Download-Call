(function ( $ ) {

$.searchData = function ( dataUrl , methodType , requestData , replaceSelector , templateUrl , itemPlate ) {
		$.ajax({
			url: dataUrl,
			type: methodType,
			data: requestData,
			dataType: 'json'
		}).done(function( data ) {
			alert(data);
			$.getTemplate(  templateUrl , function( html ) {
				var source = html;

				$( replaceSelector ).html('');
				if( data.items.length > 0 ){
					$.each(data.items, function( i, item ) {
						//var newObject = jQuery.extend({}, item);
						var template = Handlebars.compile( source );
						var html = template( item );
						$( replaceSelector ).append(html);
					});
				}else{
					alert('没有找到你想要的商品，请重新输入！')
				};
			});
		});
	};
	
	
	
	$.ajaxLoadData = function ( dataUrl , methodType , requestData , replaceSelector , templateUrl , itemPlate ) {
		$.ajax({
			url: dataUrl,
			type: methodType,
			data: requestData,
			dataType: 'json'
		}).done(function( data ) {
			alert(data);
			$.getTemplate(  templateUrl , function( html ) {
				var source = html;

				$( replaceSelector ).html('');
				if( data.items.length > 0 ){
					$.each(data.items, function( i, item ) {
						//var newObject = jQuery.extend({}, item);
						var template = Handlebars.compile( source );
						var html = template( item );
						$( replaceSelector ).append(html);
					});
				}else{
					alert('没有找到你想要的商品，请重新输入！')
				};
			});
		});
	};
	
$.getTemplate = function ( templateUrl , fn ){
		$.get(templateUrl + '?'+Math.random(), function( html ){
			fn( html );
		});
	};


$.fn.searchPosition = function(  dataUrl , methodType , requestData , replaceSelector , templateUrl , itemPlate  ){
	//var settings = $.extend( {}, options );
	var elems = this;
	elems.click(function(e){
		e.preventDefault();
		alert("fdsadf");
		$.searchData(  dataUrl , methodType , requestData , replaceSelector , templateUrl , itemPlate  );
	});
};


$.fn.pagination = function( options ) {
        var settings = $.extend( {}, options );
		//为查询按钮设置查询处理函数
		var context = {
			itemId : '' ,
			itemText : '' 
		};
		$('#searchBtn').click(function(e){
		e.preventDefault();
		alert("fdsadf");
		//$.searchData(  dataUrl , methodType , requestData , replaceSelector , templateUrl , itemPlate  );
		});
        //$('#searchBtn').searchPosition( 'static/data/locatitonType.json' , "get" , "" , "#ulId" , 'static/template/locationItem.htm' , context);
}
}( jQuery ));