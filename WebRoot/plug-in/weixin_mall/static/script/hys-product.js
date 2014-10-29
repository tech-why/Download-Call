(function ( $ ) {
    var getCode = true;

    $.hysCookie = function( options ){
        var settings = $.extend( {}, options );

        if( settings.type == 'getCookie' ){
            console.log(getCookie( settings.cname ))
            return getCookie( settings.cname );
        };

        if( settings.type == 'setCookie' ){
            setCookie( settings.cname, settings.cvalue, settings.cdays );
        };

        function setCookie(cname, cvalue, exdays) {
            var d = new Date();

            console.log(cname +'/'+ cvalue +'/'+ exdays)
            d.setTime(d.getTime() + (exdays*24*60*60*1000));
            var expires = "expires=" + d.toGMTString();
            document.cookie = cname + "=" + cvalue + "; " + expires;
            console.log(document.cookie);
        }

        function getCookie(cname) {
            var name = cname + "=";
            var ca = document.cookie.split(';');
            for(var i=0; i<ca.length; i++) {
                var c = ca[i].trim();
                if (c.indexOf(name) == 0) return c.substring(name.length, c.length);
            }
            return "";
        }
    };

    $.errMsg = function( options ){
        var settings = $.extend( {}, options );

        var errDom = '<div id="errMsg" class="alert alert-danger" style="position:absolute; top:20%; left0; width:100%; text-align:center; z-index:2000;">'+ settings.errMsg +'</div>',
            errTimer = null;

        $( 'body' ).append( errDom );
        errTimer = setTimeout(function(){
            if( $( '#errMsg' ).length > 0 ){
                $( '#errMsg' ).detach();
                clearTimeout( errTimer );
            };
        }, 2000);
    };

    $.getOpenId = function( options ){
        var settings = $.extend( {
            onSuccess: function(){}
        }, options );

        var pageCode = $.productParam({'param': 'code'});

        if( pageCode && getCode == true ){
            $.ajax({
                url:'/haoyao/shopCarAction.do',
                type: 'GET',
                data: {
                    'method': 'gainOpenid',
                    'gainCode': pageCode
                },
                dataType: 'json'
            }).done(function( data ){
                getCode = false;
                settings.onSuccess.call( this );
            });
        }else{
            settings.onSuccess.call( this );
        }
    };

    $.productInit = function( options ){
        var pageTarget = $.productParam({'param': 'state'}),
            brandid = $.productParam({'param': 'brandid'});

        switch( pageTarget ){
            case 'jingpin':
                $( document ).attr( 'title', '药品商城' );
                break;
            case 'qixie':
                $( document ).attr( 'title', '健康器械' );
                break;
            case 'baojian':
                $( document ).attr( 'title', '养生保健' );
                break;
            case 'drug':
                $( document ).attr( 'title', '中药西药' );
                break;
            case 'food':
                $( document ).attr( 'title', '美食美客' );
                break;
            case 'meizhuang':
                $( document ).attr( 'title', '妆点部落' );
                break;
            case 'fuli':
                $( document ).attr( 'title', '北九福利' );
                break;
            case 'pinpai':
                $( document ).attr( 'title', '品牌专区/同仁堂' );
                break;
            case 'brandgoods':
                if( brandid != null ){
                    getTitle( brandid, function(t){
                        $( document ).attr( 'title', t );
                    });
                }else{
                    $( document ).attr( 'title', '品牌专区' );
                };
                break;
            default:
                $( document ).attr( 'title', '精品推荐' );
        };
        category( pageTarget );
        popular( pageTarget );

        function getTitle( brandid, fn ){
            $.ajax({
                url: '/haoyao/whchatIntegration.do',
                type: 'GET',
                data: {
                    method: 'brandnames',
                    brandid: brandid
                },
                dataType: 'json'
            }).done(function( data ){
                if( data.wxbrandnames ){
                    fn( data.wxbrandnames );
                }else{
                    fn( '品牌专区' );
                };
            });
        };

        function category( pageTarget ){
            if( pageTarget == 'xianshi' || pageTarget == 'fuli' || pageTarget == 'pinpai' ){
                $( '#category' ).hide();
            }else{
                $('#category').click(function(){
                    $('.category-items').slideToggle();
                });
            };
        }

        function popular( pageTarget ){
            if( pageTarget == 'xianshi' || pageTarget == 'fuli' ){
                $( '#pop, .js-pop' ).hide();
            }else{
                $( '#pop' ).popular({
                    url: '/mall/cartdata.html',
                    type: 'GET',
                    method: 'hotProduct',
                    template: 'static/template/product_pop_list.html',
                    onSuccess: function(){
                        $( "#pop" ).owlCarousel({
                            itemsCustom : [
                                [0, 3],
                                [450, 4],
                                [600, 6],
                                [700, 6],
                                [1000, 6],
                                [1200, 6],
                                [1400, 6],
                                [1600, 6]
                            ],
                            navigation: false
                        });
                    }
                });
            };
        };
    };

    $.productParam = function( options ){
        var settings = $.extend( {
            param: 'state'
        }, options );

        var _param = getParam( settings.param );
        return _param;

        function getParam( param ){
            var reg = new RegExp( "(^|&)"+ param +"=([^&]*)(&|$)" ),
                r = window.location.search.substr( 1 ).match( reg );

            if ( r!=null ){
                return unescape( r[2] );
            }else{
                return null;
            };
        };
    };

    $.fn.listChange = function( options ){
        var settings = $.extend( {}, options );

        return this.each(function() {
            var elems = this;

            $( elems ).click(function(){
                var status = $(this).attr('status');

                if( status == 1 ){
                    $( this ).attr('status', '2').find('span').
                            removeClass('hys-icon-double').
                            addClass('hys-icon-list');
                    $( '#product' ).removeClass('waterfall').addClass('table');
                }else if( status == 2 ){
                    $( this ).attr('status', '3').find('span').
                            removeClass('hys-icon-list').
                            addClass('hys-icon-single');
                    $( '#product' ).removeClass('table');
                }else{
                    $( this ).attr('status', '1').find('span').
                            removeClass('hys-icon-single').
                            addClass('hys-icon-double');
                    $( '#product' ).addClass('waterfall');
                };
            });
        });
    };

    $.fn.searchProduct = function( options ){
        var settings = $.extend( {}, options );

        var elems = this;

        $( elems ).click(function(){
            var searchText = $('#searchText').val();

            if( searchText != '' ){
                $('#pageBtn').hide();
                searchData( searchText );
            };
        });

        function searchData( text ) {
            $.ajax({
                url: '/haoyao/wchatGoodGroup.do',
                type: 'POST',
                data: {
                    'method': 'queryByItemName',
                    'itemName': text
                },
                dataType: 'json'
            }).done(function( data ) {
                getTemplate(function( html ) {
                    var source = html;

                    $( '#product' ).html('');
                    if( data.wchatGoodsGroup.length > 0 ){
                        $.each(data.wchatGoodsGroup, function( i, item ) {
                            var context = {
                                itemId: item.id,
                                itemName: item.itemName,
                                itemOrigPrice: item.itemOrigPrice,
                                itemSalePrice: item.itemSalePrice,
                                itemImageUrl: item.itemImageUrl
                            };
                            var template = Handlebars.compile( source );
                            var html = template( context );
                            $( '#product' ).append(html);
                        });
                        $( '#product' ).find('.js-shopping').cart({
                            type: 'addCart'
                        });
                    }else{
                        alert('没有找到你想要的商品，请重新输入！')
                    };
                });
            });
        };

        function getTemplate( fn ){
            $.get('static/template/product_list.html?'+Math.random(), function( html ){
                fn( html );
            });
        };
    };
 
    $.fn.pagination = function( options ) {
        var settings = $.extend( {}, options );

        return this.each(function() {
            var elems = this;

            $('#searchBtn').searchProduct();

            if( typeof($.productParam({'param': 'search'})) == 'string' ){
                console.log( decodeURIComponent($.productParam({'param': 'search'})) )
                $('#searchText').val( decodeURIComponent($.productParam({'param': 'search'})) );
                $('#searchBtn').trigger('click');
                return;
            };

            if( typeof( $( '#pageBtn' ).data( 'page' ) ) == 'undefined' ){
                getData( elems, 1 );
            };

            $( '#pageBtn' ).click(function(){
                var pageNo = $( this ).data( 'page' ),
                    totalPage = $( this ).data( 'totalPage' );

                if( pageNo == totalPage - 1 ){
                    $( this ).hide();
                };
                getData( elems, parseInt( pageNo ) + 1 );
            });
        });

        function getData( elems, pageNo ) {
            var category = $.productParam({'param': 'state'}),
                brandid = $.productParam({'param': 'brandid'}),
                ajaxParam = {
                    'method': $.productParam({'param': 'method'}),
                    'pageNo': pageNo
                };

            if( category != null ){
                ajaxParam.itemCategory = category;
            };
            if( brandid != null ){
                ajaxParam.brandid = brandid;
            };
            // $.getOpenId({
            //     onSuccess: function(){
                    // $( '#shoppingCart' ).cart({
                    //     type: 'getCount'
                    // });
                    $.ajax({
                        url: settings.url,
                        type: settings.type,
                        data: ajaxParam,
                        dataType: 'json'
                    }).done(function( data ) {
                        if( pageNo < data.totalPage && pageNo < 5 ){
                            $( '#pageBtn' ).show();
                        };
                        getTemplate(function( html ) {
                            var source = html;

                            $.each(data.wchatGoodsGroup, function( i, item ) {
                                var context = {
                                    itemId: item.id,
                                    itemName: item.itemName,
                                    itemOrigPrice: item.itemOrigPrice,
                                    itemSalePrice: item.itemSalePrice,
                                    itemImageUrl: item.itemImageUrl
                                };
                                var template = Handlebars.compile( source );
                                var html = template( context );
                                $( elems ).append(html);
                            });
                            $( '#pageBtn' ).data( 'page', pageNo );
                            if( data.totalPage > 5 ){
                                $( '#pageBtn' ).data( 'totalPage', 5 );
                            }else{
                                $( '#pageBtn' ).data( 'totalPage', data.totalPage );
                            };
                            if( settings.cart == true ){
                                $( elems ).find('.js-shopping').cart({
                                    type: 'addCart'
                                });
                            };
                        });
                    });
            //     }
            // });
        };

        function getTemplate( fn ){
            $.get(settings.template+'?'+Math.random(), function( html ){
                fn( html );
            });
        };
    };

    $.fn.popular = function( options ) {
        var settings = $.extend( {
            onSuccess: function(){}
        }, options );

        return this.each(function() {
            var elems = this;

            if( $( elems ).find('.item').length == 0 ){
                getData( elems );
            };
        });

        function getData( elems ) {
            $.ajax({
                url: settings.url,
                type: settings.type,
                data: { 'method': settings.method },
                dataType: 'json'
            }).done(function( data ) {
                console.log( data );
                getTemplate(function( html ) {
                    var source = html;

                    $.each(data.hotProduct, function( i, item ) {
                        console.log(item)
                        var context = {
                            itemId: item[0].id,
                            itemName: item[0].itemName,
                            itemImageUrl: item[0].itemImageUrlSmall
                        };
                        var template = Handlebars.compile( source );
                        var html = template( context );

                        $( elems ).append( html );
                    });
                    settings.onSuccess.call( this );
                });
            });
        };

        function getTemplate( fn ){
            $.get(settings.template + '?' + Math.random(), function( html ){
                fn( html );
            });
        };
    };

    $.fn.cart = function( options ){
        var settings = $.extend( {
            animate: true,
            addSuccess: function(){}
        }, options );

        return this.each(function() {
            var elems = this;

            switch( settings.type ){
                case 'addCart':
                    addCart( elems );
                    break;
                case 'updateCart':
                    updateCart( elems );
                    break;
                case 'initCart':
                    initCart( elems );
                    break;
                case 'getCount':
                    getCount( elems );
                    break;
                case 'checkOut':
                    checkOut( elems );
                    break;
            };
        });

        function initCart( elems ){
            getCart( elems );
        };

        function addCart( elems ){
            if( $( elems ).data('isAdd') == undefined ){
                $( elems ).one({
                    click: function(event){
                        var i = '<span class="glyphicon hys-icon hys-icon-cart js-effect" style="position:absolute; top:'+ parseInt(event.pageY-25) +'px; left:'+ parseInt(event.pageX-25) +'px; width:50px; height:50px; line-height:50px; background:#FFF; border:2px solid #197FEF; border-radius:50%; color:#197FEF; text-align:center; font-size:30px; z-index:1100;"></span>',
                            stop = {
                                x: $('#cartIcon').offset().left,
                                y: $('#cartIcon').offset().top
                            };
                            // count = parseInt( $('#shoppingCart').find('strong').html() );
                        // if( count < 20 ){
                            // $.ajax({
                            //     url: '/haoyao/shopCarAction.do',
                            //     type: 'GET',
                            //     data: {
                            //         method: 'addshopcart',
                            //         itemId: $( elems ).attr( 'itemid' )
                            //     },
                            //     dataType: 'json'
                            // }).done(function( data ){
                            //     settings.addSuccess.call( this );
                            // });
                            //count = count + 1;
                            //console.log($.cookie('cart'))
                            console.log($.hysCookie({'type':'getCookie','cname':'hyscart'}))
                            if( $.hysCookie({'type':'getCookie','cname':'hyscart'}) ){
                                var _cart = $.parseJSON( decodeURIComponent($.hysCookie({'type':'getCookie','cname':'hyscart'})) );
                                //console.log(_cart)
                                if( _cart.length > 20 ){
                                    $.errMsg({
                                        errMsg: '购物车已满！'
                                    });
                                    return false;
                                };
                            }else{
                                var _cart = [];
                            };
                            _cart.push( $(elems).attr('itemid') );
                            console.log(_cart)
                            $.hysCookie({
                                'type':'setCookie',
                                'cname':'hyscart',
                                'cvalue': encodeURIComponent( JSON.stringify(_cart) ),
                                'cdays': 30
                            });
                            console.log($.hysCookie({'type':'getCookie','cname':'hyscart'}))
                            settings.addSuccess.call( this );
                            if( settings.animate == true ){
                                $('body').append(i);
                                $('.js-effect').animate({
                                    'top': stop.y,
                                    'left': stop.x
                                },500,function(){
                                    $( this ).fadeOut(500,function(){
                                        $( this ).detach();
                                        // $('#shoppingCart').find('strong').html(count);
                                        // if( $('#shoppingCart').is(':visible') == false ){
                                        //     $('#shoppingCart').show();
                                        // };
                                    });
                                });
                            }else{
                                //$('#shoppingCart').find('strong').html(count);
                                // if( $('#shoppingCart').is(':visible') == false ){
                                //     $('#shoppingCart').show();
                                // };
                            };
                            $(this).data('isAdd', true);
                        // }else{
                        //     $.errMsg({
                        //         errMsg: '购物车已满！'
                        //     });
                        // };
                    }
                });
            };
        };

        function delCart( elems ){
            $.ajax({
                url: '/jeewx/cartController.do?delete',
                type: 'GET',
                data: {
                    'cartId': $(elems).attr('cartid')
                },
                dataType: 'json'
            }).done(function( data ){
                window.location.reload();
            });
        };

        function updateCart( fn ){
            var itemList = [];

            $( '#cartList' ).find( 'li' ).each(function( i ) {
                var _tempId = $( this ).attr( 'itemid' ),
                    _temp = {},
                    _tempShopState;

                if( $( this ).hasClass( 'select' ) ){
                    _tempShopState = 1;
                }else{
                    _tempShopState = 0;
                };
                _temp = {
                    'id': _tempId,
                    'count': $( this ).find( '.js-count' ).val(),
                    'shopState': _tempShopState
                };
                itemList.push( _temp );
            });

            $.ajax({
                url: '/jeewx/cartController.do?update',
                type: 'POST',
                data: {
                    'cartListStr': JSON.stringify( itemList )
                },
                dataType: 'json'
            }).done(function( data ) {
                console.log(data)
                if( !data.success ){
                    $.errMsg({
                        errMsg: data.message
                    });
                }else{
                    if( fn ){
                        fn();
                    }else{
                        window.location.reload();
                    };
                };
            });
        };

        function getCart( elems ){
            var productIdsStr = '[]';
            if( $.hysCookie({'type':'getCookie','cname':'hyscart'}) ){
            	productIdsStr = decodeURIComponent( $.hysCookie({'type':'getCookie','cname':'hyscart'}) );
            };
            $.ajax({
                url: '/jeewx/cartController.do?combine',
                type: 'POST',
                data: {
                	openUserId: $.productParam({'param': 'openUserId'}),
                	productIds: productIdsStr,
                	shopId: $.productParam({'param': 'shopId'})
                },
                dataType: 'json'
            }).done(function( data ){
                console.log( data.success )
                if( data.success == true ){
                    $.hysCookie({
                        'type':'setCookie',
                        'cname':'hyscart',
                        'cvalue': '',
                        'cdays': -1
                    });
                    $.ajax({
                        url: '/jeewx/cartController.do?getCarts',
                        type: 'GET',
                        data: {
                        	openUserId: $.productParam({'param': 'openUserId'}),
                        	shopId: $.productParam({'param': 'shopId'})
                        },
                        dataType: 'json'
                    }).done(function( data ) {
                        console.log(data)
                        getTemplate(function( html ) {
                            var source = html,
                                allPrice = 0;

                            $.each(data.obj, function( i, item ) {
                                var context = {
                                    cartId: item.id,
                                    itemId: item.id,
                                    itemName: item.productName,
                                    itemCount: item.count,
                                    itemImageUrl: item.productImageUrl,
                                    itemSalePrice: item.productPrice,
                                    isChange: item.isChange,
                                    itemDetailsUrl: item.detailsUrl
                                };
                                if( item.state == 1 ){
                                    context.status = 'select';
                                    allPrice += (item.productPrice * 100) * item.count;
                                };
                                Handlebars.registerHelper('singleAllprice', function() {
                                    var p = (this.itemSalePrice * 100) * this.itemCount / 100;
                                    return new Handlebars.SafeString(
                                        p.toFixed(2)
                                    );
                                });
                                var template = Handlebars.compile( source );
                                var html = template( context );

                                $( elems ).append( html );
                            });
                            getActivities();
                            $( '#countAll' ).html( $( '#cartList' ).find('.select').length );
                            $( '#priceAll' ).html( (allPrice/100).toFixed(2) );
                            $('#updateCart').click(function(){
                                updateCart();
                            });
                            $( elems ).find( 'li' ).on({
                                click: function(){
                                    var p = 0,
                                        priceAll = $('#priceAll').html() * 100,
                                        countAll = $('#countAll').html(),
                                        countInput = $(this).find('.js-count'),
                                        priceDom = $(this).find('.js-price'),
                                        price = priceDom.html() * 100;

                                    if( $( this ).hasClass( 'js-del' ) == false ){
                                        $( this ).toggleClass( 'select' );
                                        if( $( this ).hasClass( 'select' ) == true ){
                                            p = (parseInt(priceAll) + parseInt(price)) / 100;
                                            $('#countAll').html(parseInt(countAll)+1);
                                            $('#priceAll').html(p.toFixed(2));
                                        }else{
                                            p = (parseInt(priceAll) - parseInt(price)) / 100;
                                            $('#countAll').html(parseInt(countAll)-1);
                                            $('#priceAll').html(p.toFixed(2));
                                        };
                                    };
                                    changeActivities();
                                }
                            });
                            $('.cart-img').find('a').click(function(event){
                                event.stopPropagation();
                            });
                            $('.del-btn').click(function(event){
                                event.stopPropagation();
                                delCart( this );
                            });
                            calculate( elems );
                        });
                    });
                }else{
                    $.errMsg({
                        errMsg: '加载数据失败，请重试！'
                    });
                };
            });

            function getTemplate( fn ){
                $.get('/jeewx/plug-in/weixin_mall/static/template/cart_list.html?' + Math.random(), function( html ){
                    fn( html );
                });
            };
        };

        function getCount( elems ){
            $.ajax({
                url: '/haoyao/shopCarAction.do',
                type: 'GET',
                data: {
                    'method': 'shopnumber'
                },
                dataType: 'json'
            }).done(function( data ){
                console.log(data)
                if( parseInt(data.shopCarList) > 0 ){
                    $( elems ).show().find('strong').html(data.shopCarList);
                };
            });
        };

        function calculate( elems ){
            var addCount = $( elems ).find( '.js-countAdd' ),
                subCount = $( elems ).find( '.js-countSub' ),
                inputCount = $( elems ).find( '.js-count' );

            inputCount.each(function(i){
                if( $(this).parents('li').attr('isChange') == 'fail' ){
                    $(this).attr('disabled', 'disabled');
                };
            });
            addCount.on({
                click: function( event ){
                    event.stopPropagation();
                    if( $(this).parents('li').attr('isChange') == 'fail' ){
                        $.errMsg({
                            errMsg: '此商品只能购买1件！'
                        });
                        return false;
                    };
                    if( $(this).parents('li').hasClass('select') == true ){
                        var p = 0,
                            singlePrice = $(this).parents('li').attr('price') * 100,
                            priceAll = $('#priceAll').html() * 100,
                            countInput = $(this).parent().siblings('.js-count'),
                            priceDom = $(this).parents('li').find('.js-price'),
                            price = priceDom.html() * 100;

                        countInput.blur();
                        var count = parseInt(countInput.val());
                        if(count < 999){
                            countInput.val(count+1)
                            p = (parseInt(price) + parseInt(singlePrice)) / 100;
                            priceDom.html(p.toFixed(2));
                            p = (parseInt(priceAll) + parseInt(singlePrice)) / 100;
                            $('#priceAll').html(p.toFixed(2));
                        };
                        changeActivities();
                    };
                }
            });
            subCount.on({
                click: function( event ){
                    event.stopPropagation();
                    if( $(this).parents('li').attr('isChange') == 'fail' ){
                        $.errMsg({
                            errMsg: '此商品只能购买1件！'
                        });
                        return false;
                    };
                    if( $(this).parents('li').hasClass('select') == true ){
                        var p = 0,
                            singlePrice = $(this).parents('li').attr('price') * 100,
                            priceAll = $('#priceAll').html() * 100,
                            countInput = $(this).parent().siblings('.js-count'),
                            priceDom = $(this).parents('li').find('.js-price'),
                            price = priceDom.html() * 100;

                        countInput.blur();
                        var count = parseInt(countInput.val());
                        if(count > 1){
                            countInput.val(count-1);
                            p = (parseInt(price) - parseInt(singlePrice)) / 100;
                            priceDom.html(p.toFixed(2));
                            p = (parseInt(priceAll) - parseInt(singlePrice)) / 100;
                            $('#priceAll').html(p.toFixed(2));
                        };
                        changeActivities();
                    };
                }
            });
            inputCount.on({
                click: function( event ){
                    event.stopPropagation();
                    if( $(this).parents('li').hasClass('select') == true ){

                    }else{
                        return false;
                    };
                },
                blur: function(){
                    var p = 0,
                        all = 0,
                        singlePrice = $(this).parents('li').attr('price') * 100,
                        priceAll = $('#priceAll').html() * 100,
                        countInput = $(this).parent().siblings('.js-count'),
                        priceDom = $(this).parents('li').find('.js-price'),
                        price = priceDom.html() * 100;

                    if(isInt($(this).val()) == true){
                        if(parseInt($(this).val()) > 999){
                            $(this).val(999);
                            p = singlePrice * 999/100;
                            priceDom.html(p.toFixed(2));
                            all = (parseInt(priceAll)+singlePrice * 999-parseInt(price))/100;
                            $('#priceAll').html(all.toFixed(2));
                        }else if(parseInt($(this).val()) < 1 || $(this).val().length == 0){
                            $(this).val(1)
                            p = singlePrice/100;
                            priceDom.html(p.toFixed(2));
                            all = (parseInt(priceAll)+singlePrice-parseInt(price))/100;
                            $('#priceAll').html(all.toFixed(2));
                        }else{
                            p = singlePrice * parseInt($(this).val())/100;
                            priceDom.html(p.toFixed(2));
                            all = (parseInt(priceAll)+singlePrice*parseInt($(this).val())-parseInt(price))/100;
                            $('#priceAll').html(all.toFixed(2));
                        };
                        changeActivities();
                    }else{
                        $(this).val(1)
                        p = singlePrice /100;
                        priceDom.html(p.toFixed(2));
                        all = (parseInt(priceAll)+singlePrice-parseInt(price))/100;
                        $('#priceAll').html(all.toFixed(2));
                        changeActivities();
                    }
                    function isInt(str){
                        var reg = /^(-|\+)?\d+$/ ;
                        return reg.test(str);
                    }
                }
            });
        };

        function checkOut( elems ){
        	
        	var openUserId = $.productParam({'param': 'openUserId'}) ;
        	var shopId = $.productParam({'param': 'shopId'}) ;
        	
            $( elems ).click(function(){
                var checkOutUrl = '/jeewx/generateOrderController.do?display&open_user_id='+openUserId+'&shopId='+shopId,
                    stateParam = [],
                    selectItem = $('#cartList').find('li.select');

                if( selectItem.length > 0 ){
                    updateCart(function(){
                    	window.location.href = checkOutUrl;
                    });
                }else{
                    $.errMsg({
                        errMsg: '请选择需要购买的商品！'
                    });
                };
            });
        };

        function getActivities(){
            $.ajax({
                url: '/haoyao/wchatActivitiesInformationAction.do',
                type: 'GET',
                data: {
                    'method': 'getActivities'
                },
                dataType: 'json'
            }).done(function( data ){
                console.log(data)
                if( data.activities.length > 0 ){
                    var priceAll = $('#priceAll').html() * 100,
                        tempPriceAll = parseInt(priceAll);

                    $.each( data.activities, function( i, item) {
                        activitiesDesc( item.startPrices, item.reductionPrices, item.activitiesDesc, tempPriceAll, item.reductionPrices );
                        if( parseInt(item.startPrices * 100) - tempPriceAll <= 0 ){
                            tempPriceAll = tempPriceAll - parseInt(item.reductionPrices * 100);
                        };
                    });
                }else{
                    $( '#activities' ).hide();
                };

                function activitiesDesc( p1, p2, desc, price, temp ){
                    var startPrices = p1 * 100,
                        reductionPrices = p2 * 100;

                    if( parseInt(startPrices) - parseInt(price) > 0 ){
                        var html = "<p startPrices="+ p1 +" tempPrice="+ temp +"><span>再有<b>"+ (parseInt(startPrices) - parseInt(price)) / 100 +"</b>元,即可</span>享受"+ desc +"优惠!</p>";
                    }else{
                        var html = "<p startPrices="+ p1 +" tempPrice="+ temp +"><span>您可</span>享受"+ desc +"优惠!</p>";
                    };

                    $( '#activities' ).append( html );
                };
            });
        };

        function changeActivities(){
            var priceAll = $('#priceAll').html() * 100,
                tempPriceAll = parseInt(priceAll);

            if( $('#activities').find('p').length > 0 ){
                $('#activities').find('p').each(function( i ){
                    var startPrices = $(this).attr('startPrices') * 100,
                        temp = $(this).attr('tempPrice') * 100;

                    if( parseInt(startPrices) - tempPriceAll > 0 ){
                        $(this).find('span').html('再有<b>'+ (parseInt(startPrices) - tempPriceAll) / 100 +'</b>元,即可');
                    }else{
                        $(this).find('span').html('您可');
                        tempPriceAll = tempPriceAll - parseInt(temp);
                    };
                });
            };
        };
    };

    $.fn.productDetail = function( options ){
        var settings = $.extend( {
            onSuccess: function(){}
        }, options );

        return this.each(function() {
            var elems = this;

            detailInit( elems );
        });

        function detailInit( elems ){
            getInfo( elems );
        };

        function getInfo( elems ){
            var ajaxParam = {};

            ajaxParam.method = $.productParam({'param': 'method'});
            // if( $.productParam({'param': 'code'}) ){
            //     $.getOpenId({
            //         onSuccess: function(){
            //             ajaxParam.goodgid = $.productParam({'param': 'state'});
            //             getData( ajaxParam );
            //         }
            //     });
            // }else{
                ajaxParam.goodgid = $.productParam({'param': 'goodgid'});
                getData(ajaxParam);
            // };

            function getData( param ){
                // $( '#shoppingCart' ).cart({
                //     'type': 'getCount'
                // });
                $.ajax({
                    url: '/haoyao/wchatGoodGroup.do',
                    type: 'GET',
                    data: param,
                    dataType: 'json'
                }).done(function( data ){
                    $( document ).attr( 'title', data.goodGroup.itemName );
                    getTemplate(function( html ){
                        var source = html;
                        var imageUrl = [];
                        var imageUrlString = '';
                        var context = {
                            itemId: data.goodGroup.id,
                            itemName: data.goodGroup.itemName,
                            itemOrigPrice: data.goodGroup.itemOrigPrice,
                            itemSalePrice: data.goodGroup.itemSalePrice,
                            itemImageUrl: data.goodGroup.itemImageUrl,
                            description: data.goodGroup.drugSpecifications,
                            stockNum: data.stockNum
                        };
                        imageUrl.push(data.goodGroup.itemImageUrl);
                        if( data.wchatGoodDetails ){
                            context.itemImageUrlArray = [];
                            $.each(data.wchatGoodDetails, function( i, item ){
                                context.itemImageUrlArray.push(item.detailsImgUrl);
                                imageUrl.push(item.detailsImgUrl);
                            });
                        };
                        var template = Handlebars.compile( source );
                        var html = template( context );
                        $( elems ).append( html );
                        // $.each(imageUrl, function(i){
                        //     imageUrlString += '<div class="item"><img src="http://w.ehaoyao.com'+ this +'" class="img-responsive" alt=""></div>';
                        // });
                        imageUrlString += '<img src="http://img01.img.ehaoyao.com'+ data.goodGroup.itemImageUrl +'" class="img-responsive" alt="">';
                        $('#picture').html(imageUrlString);
                        // $('#picture').owlCarousel({
                        //     navigation : false,
                        //     slideSpeed : 300,
                        //     singleItem:true 
                        // });
                        // if( settings.cart == true ){
                        //     $( elems ).find('.js-shopping').cart({
                        //         type: 'addCart',
                        //         openId: $.productParam({'param': 'openid'})
                        //     });
                        // };
                        $('.js-buy').cart({
                            type: 'addCart',
                            animate: false,
                            addSuccess: function(){
                                window.location.href = $('#cartIcon').parent('a').attr('href');
                            }
                        });
                        settings.onSuccess.call( this );
                        $('#showImageInfo').click(function(){
                            if( $(this).find('i').hasClass('hys-icon-hide') ){
                                $(this).find('i').removeClass('hys-icon-hide').
                                        addClass('hys-icon-show');
                                $('#imageInfo').show();
                                getInfoPic(param.goodgid);
                            }else{
                                $(this).find('i').removeClass('hys-icon-show').
                                        addClass('hys-icon-hide');
                                $('#imageInfo').hide();
                            }
                        });
                    });
                });
            };
        };

        function getInfoPic( goodgid ){
            if( $('#imageInfo').find('img').length == 0 ){
                $.ajax({
                    url: '/haoyao/wchatGoodGroup.do',
                    type: 'GET',
                    data: {
                        'method': 'jsondetailsImage',
                        'goodgid': goodgid
                    },
                    dataType: 'json'
                }).done(function( data ){
                    if( data.wchatGoodDetailsImageModel && data.wchatGoodDetailsImageModel.length > 0 ){
                        var imageHtml = '';

                        $.each( data.wchatGoodDetailsImageModel, function( i, item ){
                            imageHtml += '<img src="http://img01.img.ehaoyao.com'+ item.goodDetailsImage +'" alt="" class="img-responsive">';
                        });
                        $('#imageInfo').html(imageHtml);
                    };
                });
            };
        };

        function getTemplate( fn ){
            $.get(settings.template +'?' + Math.random(), function( html ){
                fn( html );
            });
        };
    };
 
}( jQuery ));