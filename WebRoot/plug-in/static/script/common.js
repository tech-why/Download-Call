/*生成提示窗口
* s是提示类型
* 
*/
function error(html,s,o){
	$('.tip').html('');
	$('.tip').html('<div class="alert alert-bg fade alert-tip in" id="alert">'+html+'</div>');
	setTimeout(function(){
		$('.tip').html('');
	},3000);
}
function loading(){
	$('.tip').html('<div class="alert alert-bg fade alert-tip in" id="alert"><i class="fa fa-loader"></i></div>');
}
/*
*	添加到购物车
*/
function addcart(id,price,shopid){
	loading();
	var target = '/wechat/index/addcart/id/'+id+'/price/'+price;
	$.get(target).success(function(data){
		if(data.status){
			$('.tip').html('');
			var z = $('#total').text();
			z = Number(parseFloat(z)+parseFloat(price));
			$('#li-'+shopid+' .btn').text('已加入购物车').attr("disabled", true).removeClass('btn-success').addClass('btn-gray');
			$('#total').text(z.toFixed(2));
			//error(data.info,'alert-warning');
		}else{
			error(data.info,'alert-danger');
		}
	})
}
$(function(){
	$("#keytitle").on("input propertychange",function(){
		var data = $(this).val();
		if(data){
			$('.formicon .fa-times-circle').show();
		}else{
			$('.formicon .fa-times-circle').hide();
		}
	})
	$("#keytitle").focus(function(){
		if($(this).val()){
			$('.formicon .fa-times-circle').show();
		}
	})
	$(".btn-carts").click(function(){
		var z = $('#total').text();
		if(parseFloat(z)<1){
			error('购物车里还没有商品呢!');
		}else{
			sessionStorage.total = $('#total').text();
			sessionStorage.bodyhtml = $('.container').html();
			window.location = '/wechat/index/cart/';
		}
	})
})