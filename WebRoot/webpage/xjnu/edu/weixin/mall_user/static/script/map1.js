(function ( $ ) {


$.renderComponent= function ( options ) {
	var settings = $.extend( {}, options );
	$.getTemplate(  settings.templateUrl , function( html ) {
		var source = html;
		$( settings.componentSelector ).html('');
		
		if( settings.data.length > 0 ){
			$.each( settings.data, function( i, item ) {
				var template = Handlebars.compile( source );
				var itemTemplate = settings.convertFun( item );
				var html = template( itemTemplate );
				$( settings.componentSelector ).append(html);
			});
		}else{
			alert('没有找到你想要的商品，请重新输入！');
		}
	});
};

$.getParam = function( param ){
    var reg = new RegExp( "(^|&)"+ param +"=([^&]*)(&|$)" ),
        r = window.location.search.substr( 1 ).match( reg );

    if ( r!=null ){
        return unescape( r[2] );
    }else{
        return null;
    };
};



$.renderComponentByAjax = function ( options ) {
	var settings = $.extend( {}, options );
	$.ajax({
		url: settings.dataUrl,
		type: settings.methodType,
		data: settings.requestData,
		dataType: 'json'
	}).done(function( data ) {
		$.getTemplate(  settings.templateUrl , function( html ) {
			var source = html;
			if( data.obj.length > 0 ){
				$.each(data.obj, function( i, item ) {
					settings.renderFun(item , i , source ) ;
				});
			}else{
				alert('没有找到你想要的商品，请重新输入！')
			};
			
			settings.completeFun();
		});
	});
};


	
$.getTemplate = function ( templateUrl , fn ){
		$.get(templateUrl + '?'+Math.random(), function( html ){
			fn( html );
		});
	};

$.processOnClick = function ( elem , settings ){

	settings.process(elem);
	$.renderComponent(settings);
};

$.fn.refreshChildDropdowns = function( options ){
	var settings = $.extend( {}, options );
	var elems = this;
	$.each( elems , function( i, elem ) {
		$( elem ).click(function(){
			$.processOnClick( elem , settings );
		});
	});
};


}( jQuery ));