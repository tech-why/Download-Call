/**
 * Created by Ray on 14-4-10.
 */
var WealinkMobile = {'baseUrl':'http://m.wealink.com'};
jQuery('div[data-role=page]').live('pagehide', function(event, ui){
    var page = jQuery(event.target);
    if(page.attr('data-dom-cache') == 'false'){
        page.remove();
    }
});
// Cookie 操作；
WealinkMobile.Cookie = {
    'set': function (cookieName, cookieValue, nDays) {
        var today = new Date;
        var expire = new Date;
        if (nDays) {
            expire.setTime(today.getTime() + 3600000 * 24 * nDays);
        }
        document.cookie = cookieName + '=' + escape(cookieValue) +
            (nDays ? ('; expires=' + expire.toGMTString()) : '') + '; path=/; domain=wealink.com';
    },

    'get': function (name) {
        var nameEQ = name + '=';
        var ca = document.cookie.replace(/\s/g, '').split(';');
        for (var i = 0; i < ca.length; i++) {
            var c = ca[i];
            if (c.indexOf(nameEQ) == 0)
                return unescape(c.substring(nameEQ.length, c.length).replace(/\+/g, ' '));
        }
        return null;
    },

    del : function( name) {
        this.set( name , '' , -1 );
    }
};
// 列表信息异步加载；
WealinkMobile.ListWidget = {
    'init': function (options) {
        options = $.extend({
            'container': null,
            'noResult': '',
            'moreResult': '',
            'callback': function () {
            }
        }, options);

        options.moreResult = options.moreResult||'查看更多';
        options.container && options.container.each(function () {
            var $widget = $(this),
                $body = $(this).find('.widget-body'),
                $list = $body.find(".widget-list"),
                noResultHtml = '<p class="alignC widget-noResult" style="display: none;">' + options.noResult + '</p>',
                moreHtml = '<p class="showMore widget-more" style="display: none;"><span>展开更多</span></p>';


            if($widget.find('.widget-noResult').length ==0)
                $list.after(noResultHtml);
            if($widget.find('.widget-more').length ==0)
                $list.after(moreHtml);

            $widget.find(".widget-title, .widget-more").unbind('click').live( "click", function () {
                var bodyVisible = $body.is(':visible');
                var isLoading = $widget.jqmData('wlm-loading'),
                    url = $widget.jqmData('wlm-url'),
                    offset = $widget.jqmData('wlm-offset')||0,
                    perPage = $widget.jqmData('wlm-perpage')||3;

                // 点击title只有offset =0 时才会异步加载内容，否则只是打开body;
                if($(this).is('.widget-title')){
                    if(bodyVisible){
                        WealinkMobile.ListWidget.fold($widget);
                        return false;
                    }else{
                        if(offset > 0){
                            WealinkMobile.ListWidget.expand($widget);
                            return false;
                        }
                    }
                }

                if(!isLoading){
                    $.ajax({
                        'url': url + (url.indexOf('&') ? '&' : '') + 'offset=' + offset + '&perPage='+perPage,
                        'dataType': 'json',
                        'type': 'GET',
                        'async': true,
                        'cache': false,
                        'beforeSend':function(){
                            $widget.jqmData("wlm-loading", true);
                            $.mobile.loading('show');
                            return true;
                        },
                        'success':function(data){
                            $.mobile.loading('hide');
                            if (data.total == 0) {
                                $list.remove();
                                $body.find(".widget-noResult").show();
                            }else{
                                $body.find(".widget-more").show();
                            }

                            if(data.list && data.list.length > 0){
                                $list.find('>li').last().removeClass('noBd');
                                $(typeof(options.callback) == 'function' ? options.callback(data):'')
                                    .appendTo($list) && ($list.find('>li').last().addClass('noBd'));

                            }

                            if(offset + perPage >= data.total){
                                $body.find('.widget-more').remove();
                            }
                            if(!bodyVisible) WealinkMobile.ListWidget.expand($widget);

                            $widget.jqmData('wlm-offset', offset+perPage);
                            $widget.jqmRemoveData('wlm-loading');
                        }
                    });
                }

                return true;
            });
        });
    },
    'fold':function(widget){
        widget.find(".widget-body")
            .slideUp('normal', 'swing', function () {
                widget.find('.widget-title').removeClass('downArrow').addClass('rightArrow');
            });
    },
    'expand':function(widget){
        widget.find(".widget-body")
            .slideDown('normal', 'swing', function () {
                widget.find('.widget-title').removeClass('rightArrow').addClass('downArrow');
            });
    }
};

// 列表页滚动加载；
WealinkMobile.ListPage = {
    'scroll': function (options) {
        options = $.extend({
            container: null,
            scrollOffset:0,
            callback: function () {}
        }, options);

        if(!options.container) return false;

        var ul = options.container;
        var offset = ul.jqmData("wlm-offset") || 0,
            perpage = ul.jqmData("wlm-perpage") || 10,
            url = ul.jqmData("wlm-url"),
            total = ul.jqmData("wlm-total") || 0;
        var loading = ul.jqmData('wlm-loading');
        var $document = $(document), $window = $(window);
        var scrolledToBottom = ($document.scrollTop() + options.scrollOffset >= $document.height() - $window.height());
        if (!loading && scrolledToBottom && offset < total) {
        	/*alert(url + (url.indexOf('&') ? '&' : '') + 'offset=' + (offset + perpage));*/
            $.ajax({
                'url': url + (url.indexOf('&') ? '&' : '') + 'offset=' + (offset + perpage),
                'dataType': 'json',
                'type': 'GET',
                'async': true,
                'cache': false,
                'beforeSend': function () {
                    ul.jqmData("wlm-loading", true);
                    $.mobile.loading('show');
                    return true;
                },
                'success': function (data) {
                    $.mobile.loading("hide");

                    $(typeof(options.callback) == 'function' ? options.callback(data):'').appendTo(ul);
                    
                    if(ul.jqmData('role')=='listview') ul.listview('refresh');

                    ul.jqmData('wlm-offset', offset + perpage);
                    ul.jqmRemoveData('wlm-loading');
                }
            });
        }
        return true;
    }
};

// 数据字典
WealinkMobile.dict = {
//	'part_time_job':[["5a43553b487daeb901487db0aebf0001","\u5bb6\u6559","5a43553b487daeb901487db0aebf0001",[["5a43553b487daeb901487db1706a0006","\u9ad8\u4e2d\u5bb6\u6559","5a43553b487daeb901487db1706a0006"]]],["5a43553b487daeb901487db0f17b0003","\u5355\u4f4d\u517c\u804c","5a43553b487daeb901487db0f17b0003",[["5a43553b487daeb901487db1d6460008","\u4e03\u5339\u72fc\u7ba1\u7406\u4eba\u5458","5a43553b487daeb901487db1d6460008"]]]],
	//'tbJobCatalogEntity_id':[["5a43553b487daeb901487db0aebf0001","\u5bb6\u6559","5a43553b487daeb901487db0aebf0001",[["5a43553b487daeb901487db1706a0006","\u9ad8\u4e2d\u5bb6\u6559","5a43553b487daeb901487db1706a0006"]]],["5a43553b487daeb901487db0f17b0003","\u5355\u4f4d\u517c\u804c","5a43553b487daeb901487db0f17b0003",[["5a43553b487daeb901487db1d6460008","\u4e03\u5339\u72fc\u7ba1\u7406\u4eba\u5458","5a43553b487daeb901487db1d6460008"]]]],
    'industry':[[31001,"\u8ba1\u7b97\u673a\/\u4e92\u8054\u7f51\/\u901a\u4fe1\/\u7535\u5b50","jisuanji",[[32001,"\u8ba1\u7b97\u673a\u8f6f\u4ef6","jisuanjiruanjian"],[32002,"\u8ba1\u7b97\u673a\u786c\u4ef6","jisuanjiyingjian"],[32003,"\u8ba1\u7b97\u673a\u670d\u52a1(\u7cfb\u7edf\u3001\u6570\u636e\u670d\u52a1\u3001\u7ef4\u4fee)","jisuanjifuwu"],[32004,"\u901a\u4fe1\/\u7535\u4fe1\/\u7f51\u7edc\u8bbe\u5907","tongxin"],[32005,"\u901a\u4fe1\/\u7535\u4fe1\u8fd0\u8425\u3001\u589e\u503c\u670d\u52a1","dianxin"],[32006,"\u4e92\u8054\u7f51\/\u7535\u5b50\u5546\u52a1","hulianwang"],[32007,"\u7f51\u7edc\u6e38\u620f","wangluoyouxi"],[32008,"\u7535\u5b50\u6280\u672f\/\u534a\u5bfc\u4f53\/\u96c6\u6210\u7535\u8def","dianzijishu"],[32009,"\u4eea\u5668\u4eea\u8868\/\u5de5\u4e1a\u81ea\u52a8\u5316","yiqiyibiao"]]],[31002,"\u4f1a\u8ba1\/\u91d1\u878d\/\u94f6\u884c\/\u4fdd\u9669","kuaiji",[[32010,"\u4f1a\u8ba1\/\u5ba1\u8ba1","shenji"],[32011,"\u91d1\u878d\/\u6295\u8d44\/\u8bc1\u5238","jinrongtouzi"],[32012,"\u94f6\u884c","yinhang"],[32013,"\u4fdd\u9669","baoxian"]]],[31003,"\u5e7f\u544a\/\u5a92\u4f53","meiti",[[32014,"\u5e7f\u544a","ad"],[32015,"\u516c\u5173\/\u5e02\u573a\u63a8\u5e7f\/\u4f1a\u5c55","gongguan"],[32016,"\u5f71\u89c6\/\u5a92\u4f53\/\u827a\u672f\/\u6587\u5316\u4f20\u64ad","yingshi"],[32017,"\u6587\u5b57\u5a92\u4f53\/\u51fa\u7248","wenzimeiti"],[32018,"\u5370\u5237\/\u5305\u88c5\/\u9020\u7eb8","yinshua"]]],[31004,"\u4e13\u4e1a\u670d\u52a1\/\u6559\u80b2\/\u57f9\u8bad","zhuanyefuwu",[[32019,"\u4e2d\u4ecb\u670d\u52a1","zhongjiefuwu"],[32020,"\u4e13\u4e1a\u670d\u52a1(\u54a8\u8be2\u3001\u4eba\u529b\u8d44\u6e90\u3001\u8d22\u4f1a)","zhuanyefuwu1"],[32021,"\u5916\u5305\u670d\u52a1","waibaofuwu"],[32022,"\u68c0\u6d4b\/\u8ba4\u8bc1","jiance"],[32023,"\u6cd5\u5f8b","falv"],[32024,"\u6559\u80b2\/\u57f9\u8bad\/\u9662\u6821","jiaoyu"],[32025,"\u5b66\u672f\/\u79d1\u7814","xueshu"]]],[31005,"\u5236\u836f\/\u533b\u7597","zhiyaoyiliao",[[32026,"\u5236\u836f\/\u751f\u7269\u5de5\u7a0b","zhiyao"],[32027,"\u533b\u7597\/\u62a4\u7406\/\u536b\u751f","yiliaohuli"],[32028,"\u533b\u7597\u8bbe\u5907\/\u5668\u68b0","yiliaoshebei"]]],[31006,"\u623f\u5730\u4ea7\/\u5efa\u7b51","dichan",[[32029,"\u623f\u5730\u4ea7\u5f00\u53d1","fangdichan"],[32030,"\u5efa\u7b51\/\u5efa\u6750\/\u5de5\u7a0b","jianzhujiancai"],[32031,"\u5bb6\u5c45\/\u5ba4\u5185\u8bbe\u8ba1\/\u88c5\u6f62","sheneisheji"],[32032,"\u7269\u4e1a\u7ba1\u7406\/\u5546\u4e1a\u4e2d\u5fc3","shangyezhongxin"]]],[31007,"\u8d38\u6613\/\u6d88\u8d39\/\u5236\u9020\/\u8425\u8fd0","zhizao",[[32033,"\u8d38\u6613\/\u8fdb\u51fa\u53e3","jinchukoumaoyi"],[32034,"\u6279\u53d1\/\u96f6\u552e","pifa"],[32035,"\u5feb\u901f\u6d88\u8d39\u54c1(\u98df\u54c1\u3001\u996e\u6599\u3001\u5316\u5986\u54c1)","xiaofeipin"],[32036,"\u670d\u88c5\/\u7eba\u7ec7\/\u76ae\u9769","fangzhi"],[32037,"\u5bb6\u5177\/\u5bb6\u7535\/\u5de5\u827a\u54c1\/\u73a9\u5177\/\u73e0\u5b9d","jiaju"],[32038,"\u5962\u4f88\u54c1\/\u6536\u85cf\u54c1","shechipin"],[32039,"\u529e\u516c\u7528\u54c1\u53ca\u8bbe\u5907","bangongyongpin"],[32040,"\u673a\u68b0\/\u8bbe\u5907\/\u91cd\u5de5","jixie"],[32041,"\u6c7d\u8f66\u53ca\u96f6\u914d\u4ef6","lingpeijian"]]],[31008,"\u670d\u52a1\u4e1a","fuwuye",[[32042,"\u9910\u996e\u4e1a","canyinye"],[32043,"\u9152\u5e97\/\u65c5\u6e38","jiudian"],[32044,"\u5a31\u4e50\/\u4f11\u95f2\/\u4f53\u80b2","yule"],[32045,"\u7f8e\u5bb9\/\u4fdd\u5065","meirongbaojian"],[32046,"\u751f\u6d3b\u670d\u52a1","shenghuofuwu"]]],[31009,"\u7269\u6d41\/\u8fd0\u8f93","wuliuyunshu",[[32047,"\u4ea4\u901a\/\u8fd0\u8f93\/\u7269\u6d41","jiaotong"],[32048,"\u822a\u5929\/\u822a\u7a7a","hangtian"]]],[31010,"\u80fd\u6e90\/\u539f\u6750\u6599","nengyuan",[[32049,"\u77f3\u6cb9\/\u5316\u5de5\/\u77ff\u4ea7\/\u5730\u8d28","shiyouhuagong"],[32050,"\u91c7\u6398\u4e1a\/\u51b6\u70bc","caijueye"],[32051,"\u7535\u529b\/\u6c34\u5229","dianli"],[32052,"\u65b0\u80fd\u6e90","xinnengyuan"],[32053,"\u539f\u6750\u6599\u548c\u52a0\u5de5","yuancailiao"]]],[31011,"\u653f\u5e9c\/\u975e\u8d62\u5229\u673a\u6784\/\u5176\u4ed6","zhengfu",[[32054,"\u653f\u5e9c\/\u516c\u5171\u4e8b\u4e1a","gongguanshiye"],[32055,"\u975e\u76c8\u5229\u673a\u6784","feiyingli"],[32056,"\u73af\u4fdd","huanbao"],[32057,"\u519c\/\u6797\/\u7267\/\u6e14","nonglin"],[32058,"\u591a\u5143\u5316\u4e1a\u52a1\u96c6\u56e2\u516c\u53f8","duoyuanhua"],[32059,"\u5176\u4ed6\u884c\u4e1a","qitahangye"]]]],
    'category':[[22002,"\u8ba1\u7b97\u673a\u786c\u4ef6","yingjian",[[23015,"\u9ad8\u7ea7\u786c\u4ef6\u5de5\u7a0b\u5e08","gaojiyingjian"],[23016,"\u786c\u4ef6\u5de5\u7a0b\u5e08","yingjiangongchengshi"],[23017,"\u5176\u4ed6","qitayj"]]],[22001,"\u8ba1\u7b97\u673a\u8f6f\u4ef6","ruanjian",[[23001,"\u9ad8\u7ea7\u8f6f\u4ef6\u5de5\u7a0b\u5e08","gaojiruanjian"],[23002,"\u8f6f\u4ef6\u5de5\u7a0b\u5e08","ruanjiangongchengshi"],[23003,"\u8f6f\u4ef6UI\u8bbe\u8ba1\u5e08\/\u5de5\u7a0b\u5e08","uisheji"],[23004,"\u4eff\u771f\u5e94\u7528\u5de5\u7a0b\u5e08","fangzhenyingyong"],[23005,"ERP\u5b9e\u65bd\u987e\u95ee","erpguwen"],[23006,"ERP\u6280\u672f\u5f00\u53d1","erpkaifa"],[23007,"\u9700\u6c42\u5de5\u7a0b\u5e08","xuqiugongchengshi"],[23008,"\u7cfb\u7edf\u96c6\u6210\u5de5\u7a0b\u5e08","xitongjicheng"],[23009,"\u7cfb\u7edf\u5206\u6790\u5458","xitongfenxi"],[23010,"\u7cfb\u7edf\u5de5\u7a0b\u5e08","xitonggongchengshi"],[23011,"\u7cfb\u7edf\u67b6\u6784\u8bbe\u8ba1\u5e08","xitongjiagoushi"],[23012,"\u6570\u636e\u5e93\u5de5\u7a0b\u5e08\/\u7ba1\u7406\u5458","shujuku"],[23013,"\u8ba1\u7b97\u673a\u8f85\u52a9\u8bbe\u8ba1\u5de5\u7a0b\u5e08","fuzhusheji"],[23014,"\u5176\u4ed6","qitarj"]]],[22003,"\u4e92\u8054\u7f51\/\u7535\u5b50\u5546\u52a1\/\u7f51\u6e38","wangyou",[[23734,"\u7f51\u7ad9\u8425\u8fd0\u603b\u76d1","wangzhanyingyunzongjian"],[23031,"\u7f51\u7ad9\u8425\u8fd0\u7ecf\u7406\/\u4e3b\u7ba1","wangzhanyunyingjingli"],[23020,"\u7f51\u7ad9\u8425\u8fd0\u4e13\u5458","wangzhanyun"],[23035,"\u7f51\u7ad9\u7f16\u8f91","wangzhanbianji"],[23735,"\u7535\u5b50\u5546\u52a1\u603b\u76d1","dianzishangwuzongjian"],[23726,"\u7535\u5b50\u5546\u52a1\u7ecf\u7406\/\u4e3b\u7ba1","dianzishangwujingli"],[23723,"\u7535\u5b50\u5546\u52a1\u4e13\u5458","dianzishangwu"],[23727,"\u7f51\u5e97\u5e97\u957f\/\u5ba2\u670d","wangdiandianzhang"],[23736,"\u4ea7\u54c1\u603b\u76d1","chanpinzongjian"],[23724,"\u4ea7\u54c1\u7ecf\u7406\/\u4e3b\u7ba1","chanpinjingli"],[23725,"\u4ea7\u54c1\u4e13\u5458","chanpinzhuanyuan"],[23023,"\u7f51\u7ad9\u7b56\u5212","wangzhancehua"],[23025,"\u6e38\u620f\u7b56\u5212\u5e08","youxicehuashi"],[23021,"UI\u8bbe\u8ba1\u5e08\/\u987e\u95ee","uiguwen"],[23737,"\u4ea4\u4e92\u8bbe\u8ba1\u5e08","jiaohushejishi"],[23024,"\u7f51\u9875\u8bbe\u8ba1\/\u5236\u4f5c\/\u7f8e\u5de5","wangyesheji"],[23027,"\u89c6\u89c9\u8bbe\u8ba1\u5e08","shijueshejishi"],[23026,"Flash\u8bbe\u8ba1\/\u5f00\u53d1","flashshejikaifa"],[23037,"\u6e38\u620f\u754c\u9762\u8bbe\u8ba1\u5e08","youxijiemian"],[23038,"\u7279\u6548\u8bbe\u8ba1\u5e08","texiaoshejishi"],[23039,"\u97f3\u6548\u8bbe\u8ba1\u5e08","yinxiaoshejishi"],[23018,"\u4e92\u8054\u7f51\u8f6f\u4ef6\u5f00\u53d1\u5de5\u7a0b\u5e08","kaifa"],[23033,"\u7f51\u7ad9\u67b6\u6784\u8bbe\u8ba1\u5e08","wangzhanjiagoushi"],[23019,"\u591a\u5a92\u4f53\/\u6e38\u620f\u5f00\u53d1\u5de5\u7a0b\u5e08","duomeiti"],[23030,"\u8bed\u97f3\/\u89c6\u9891\u5f00\u53d1\u5de5\u7a0b\u5e08","shipinkaifa"],[23036,"\u811a\u672c\u5f00\u53d1\u5de5\u7a0b\u5e08","jiaobenkaifa"],[23738,"\u7f51\u7edc\u63a8\u5e7f\u603b\u76d1","wangluotuiguangzongjian"],[23739,"\u7f51\u7edc\u63a8\u5e7f\u7ecf\u7406\/\u4e3b\u7ba1","wangluotuiguangjinglizhuguan"],[23740,"\u7f51\u7edc\u63a8\u5e7f\u4e13\u5458","wangluotuiguangzhuanyuan"],[23028,"SEO\u641c\u7d22\u5f15\u64ce\u4f18\u5316","seo"],[23022,"\u7f51\u7ad9\u7ef4\u62a4\u5de5\u7a0b\u5e08","wangzhanweihu"],[23032,"\u7f51\u7edc\u5de5\u7a0b\u5e08","wangluogongchengshi"],[23040,"\u7f51\u7edc\u4fe1\u606f\u5b89\u5168\u5de5\u7a0b\u5e08","wangluoanquan"],[23034,"\u7cfb\u7edf\u7ba1\u7406\u5458\/\u7f51\u7edc\u7ba1\u7406\u5458","xitongguanliyuan"],[23041,"\u5176\u4ed6","qitahlw"]]],[22004,"IT-\u7ba1\u7406","itguanli",[[23042,"\u9996\u5e2d\u6280\u672f\u6267\u884c\u5b98CTO\/\u9996\u5e2d\u4fe1\u606f\u5b98CIO","cto"],[23043,"\u6280\u672f\u603b\u76d1\/\u7ecf\u7406","jishuzongjian"],[23044,"\u4fe1\u606f\u6280\u672f\u7ecf\u7406\/\u4e3b\u7ba1","xinxijishujingli"],[23045,"\u4fe1\u606f\u6280\u672f\u4e13\u5458","xinxijishuzhuanyuan"],[23046,"\u9879\u76ee\u603b\u76d1","itxiangmuzongjian"],[23047,"\u9879\u76ee\u7ecf\u7406","itxiangmujingli"],[23048,"\u9879\u76ee\u4e3b\u7ba1","itxiangmuzhuguan"],[23049,"\u9879\u76ee\u6267\u884c\/\u534f\u8c03\u4eba\u5458","xiangmuzhixing"],[23050,"\u5176\u4ed6","qitait"]]],[22005,"IT-\u54c1\u7ba1\u3001\u6280\u672f\u652f\u6301\u53ca\u5176\u4ed6","itpinguan",[[23051,"\u6280\u672f\u652f\u6301\/\u7ef4\u62a4\u7ecf\u7406","weihujingli"],[23052,"\u6280\u672f\u652f\u6301\/\u7ef4\u62a4\u5de5\u7a0b\u5e08","weihugongchengshi"],[23053,"Helpdesk\u6280\u672f\u652f\u6301","helpdesk"],[23054,"\u8ba1\u91cf\u5de5\u7a0b\u5e08","jilianggongchengshi"],[23055,"\u6807\u51c6\u5316\u5de5\u7a0b\u5e08","biaozhunhua"],[23056,"\u54c1\u8d28\u7ecf\u7406","pinzhijingli"],[23057,"\u7cfb\u7edf\u6d4b\u8bd5","xitongceshi"],[23058,"\u8f6f\u4ef6\u6d4b\u8bd5","ruanjianceshi"],[23059,"\u786c\u4ef6\u6d4b\u8bd5","yingjianceshi"],[23060,"\u6d4b\u8bd5\u5458","ceshiyuan"],[23061,"\u6587\u6863\u5de5\u7a0b\u5e08","wendanggongchengshi"],[23062,"\u6280\u672f\u6587\u5458\/\u52a9\u7406","jishuzhuli"],[23063,"\u5176\u4ed6","qitapg"]]],[22006,"\u901a\u4fe1\u6280\u672f\u5f00\u53d1\u53ca\u5e94\u7528","tongxinyingyong",[[23064,"\u901a\u4fe1\u6280\u672f\u5de5\u7a0b\u5e08","tongxinjishu"],[23065,"\u6709\u7ebf\u4f20\u8f93\u5de5\u7a0b\u5e08","youxianchuanshu"],[23066,"\u65e0\u7ebf\u901a\u4fe1\u5de5\u7a0b\u5e08","wuxiantongxin"],[23067,"\u7535\u4fe1\u4ea4\u6362\u5de5\u7a0b\u5e08","dianxinjiaohuan"],[23068,"\u6570\u636e\u901a\u4fe1\u5de5\u7a0b\u5e08","shujutongxin"],[23069,"\u79fb\u52a8\u901a\u4fe1\u5de5\u7a0b\u5e08","yidongtongxin"],[23070,"\u7535\u4fe1\u7f51\u7edc\u5de5\u7a0b\u5e08","dianxinwangluo"],[23071,"\u901a\u4fe1\u7535\u6e90\u5de5\u7a0b\u5e08","tongxindianyuan"],[23072,"\u589e\u503c\u4ea7\u54c1\u5f00\u53d1\u5de5\u7a0b\u5e08","zengzhichanpin"],[23073,"\u624b\u673a\u8f6f\u4ef6\u5f00\u53d1\u5de5\u7a0b\u5e08","shoujiruanjian"],[23074,"\u624b\u673a\u5e94\u7528\u5f00\u53d1\u5de5\u7a0b\u5e08","shoujiyingyong"],[23075,"\u5176\u4ed6","qitatx"]]],[22007,"\u7535\u5b50\/\u7535\u5668\/\u534a\u5bfc\u4f53\/\u4eea\u5668\u4eea\u8868","dianzi",[[23076,"\u96c6\u6210\u7535\u8defIC\u8bbe\u8ba1\/\u5e94\u7528\u5de5\u7a0b\u5e08","jichengdianlu"],[23093,"\u6fc0\u5149\/\u5149\u7535\u5b50\u6280\u672f","jiguang"],[23097,"\u5de5\u827a\u5de5\u7a0b\u5e08","gongyi"],[23083,"\u7535\u5b50\u8f6f\u4ef6\u5f00\u53d1(ARM\/MCU...)","armmcu"],[23089,"\u7535\u5b50\u6280\u672f\u7814\u53d1\u5de5\u7a0b\u5e08","dianzijishuyanfa"],[23077,"\u7535\u5b50\u5de5\u7a0b\u5e08\/\u6280\u672f\u5458","dianzijishuyuan"],[23090,"\u7535\u5b50\/\u7535\u5668\u7ef4\u4fee\u5de5\u7a0b\u5e08\/\u6280\u5e08","dianziweixiu"],[23081,"\u7535\u58f0\/\u97f3\u54cd\u5de5\u7a0b\u5e08\/\u6280\u672f\u5458","diansheng"],[23080,"\u7535\u6c14\u5de5\u7a0b\u5e08\/\u6280\u672f\u5458","dianqijishuyuan"],[23092,"\u7535\u8def\u5de5\u7a0b\u5e08\/\u6280\u672f\u5458(\u6a21\u62df\/\u6570\u5b57)","dianlugongchengshi"],[23096,"\u7535\u6c60\/\u7535\u6e90\u5f00\u53d1","dianchi"],[23094,"\u81ea\u52a8\u63a7\u5236\u5de5\u7a0b\u5e08\/\u6280\u672f\u5458","zidongkongzhi"],[23098,"\u4eea\u5668\/\u4eea\u8868\/\u8ba1\u91cf\u5206\u6790\u5e08","yiqi"],[23078,"\u5c04\u9891\u5de5\u7a0b\u5e08","shepin"],[23084,"\u5d4c\u5165\u5f0f\u786c\u4ef6\u5f00\u53d1\uff08\u4e3b\u677f\u673a...\uff09","qianrushiyingjiankaifa"],[23095,"\u5d4c\u5165\u5f0f\u8f6f\u4ef6\u5f00\u53d1\uff08Linux\/\u5355\u7247\u673a\/DLC\/DSP...\uff09","qianrushiruanjiankaifa"],[23086,"\u5bb6\u7528\u7535\u5668\/\u6570\u7801\u4ea7\u54c1\u7814\u53d1","jiayongdianqi"],[23087,"\u6d4b\u8bd5\u5de5\u7a0b\u5e08","ceshigongchengshi"],[23079,"\u53d8\u538b\u5668\u4e0e\u78c1\u7535\u5de5\u7a0b\u5e08","bianyaqi"],[23082,"\u534a\u5bfc\u4f53\u6280\u672f","bandaotijishu"],[23091,"\u7248\u56fe\u8bbe\u8ba1\u5de5\u7a0b\u5e08","bantusheji"],[23088,"IC\u9a8c\u8bc1\u5de5\u7a0b\u5e08","icyanzheng"],[23085,"FAE\u73b0\u573a\u5e94\u7528\u5de5\u7a0b\u5e08","xianchangyingyong"],[23099,"\u5176\u4ed6","qitadz"]]],[22008,"\u9500\u552e\u7ba1\u7406","xiaoshouguanli",[[23100,"\u9500\u552e\u603b\u76d1","xiaoshouzongjian"],[23101,"\u9500\u552e\u7ecf\u7406","xiaoshoujingli"],[23102,"\u9500\u552e\u4e3b\u7ba1","xiaoshouzhuguan"],[23103,"\u4e1a\u52a1\u62d3\u5c55\u4e3b\u7ba1\/\u7ecf\u7406","yewutuozhanjingli"],[23104,"\u6e20\u9053\/\u5206\u9500\u603b\u76d1","qudaozongjian"],[23105,"\u6e20\u9053\/\u5206\u9500\u7ecf\u7406","qudaojingli"],[23106,"\u6e20\u9053\/\u5206\u9500\u4e3b\u7ba1","qudaozhuguan"],[23107,"\u5927\u5ba2\u6237\u9500\u552e\u7ba1\u7406","dakehuxiaoshouguanli"],[23108,"\u5ba2\u6237\u7ecf\u7406\/\u4e3b\u7ba1","kehujingli"],[23109,"\u533a\u57df\u9500\u552e\u603b\u76d1","quyuxiaoshouzongjian"],[23110,"\u533a\u57df\u9500\u552e\u7ecf\u7406","quyuxiaoshoujingli"],[23111,"\u56e2\u8d2d\u7ecf\u7406\/\u4e3b\u7ba1","tuangoujingli"],[23112,"\u5176\u4ed6","qitaxs"]]],[22009,"\u9500\u552e\u4eba\u5458","xiaoshourenyuan",[[23741,"\u5927\u5ba2\u6237\u9500\u552e","dakehuxiaoshou"],[23113,"\u9500\u552e\u4ee3\u8868","xiaoshoudaibiao"],[23114,"\u6e20\u9053\/\u5206\u9500\u4e13\u5458","qudaozhuanyuan"],[23115,"\u5ba2\u6237\u4ee3\u8868","kehudaibiao"],[23116,"\u9500\u552e\u5de5\u7a0b\u5e08","xiaoshougongchengshi"],[23117,"\u7535\u8bdd\u9500\u552e","dianhuaxiaoshou"],[23118,"\u56e2\u8d2d\u4e1a\u52a1\u5458","tuangouyewuyuan"],[23119,"\u7ecf\u9500\u5546","jingxiaoshang"],[23120,"\u5176\u4ed6","qitaxsry"]]],[22010,"\u9500\u552e\u884c\u653f\u53ca\u5546\u52a1","xiaoshouxingzheng",[[23121,"\u9500\u552e\u884c\u653f\u7ecf\u7406\/\u4e3b\u7ba1","xiaoshouxingzhengjingli"],[23122,"\u9500\u552e\u884c\u653f\u4e13\u5458\/\u52a9\u7406","xiaoshouxingzhengzhuli"],[23123,"\u4e1a\u52a1\u5206\u6790\u7ecf\u7406\/\u4e3b\u7ba1","yewufenxijingli"],[23124,"\u4e1a\u52a1\u5206\u6790\u4e13\u5458\/\u52a9\u7406","yewufenxizhuli"],[23125,"\u5546\u52a1\u7ecf\u7406","shangwujingli"],[23126,"\u5546\u52a1\u4e3b\u7ba1\/\u4e13\u5458","shangwuzhuguan"],[23127,"\u5546\u52a1\u52a9\u7406","shangwuzhuli"],[23128,"\u9500\u552e\u52a9\u7406","xiaoshouzhuli"],[23129,"\u5176\u4ed6","qitaxsxz"]]],[22011,"\u5ba2\u670d\u53ca\u6280\u672f\u652f\u6301","kefu",[[23130,"\u5ba2\u670d\u603b\u76d1(\u975e\u6280\u672f)","kefuzongjian"],[23131,"\u5ba2\u670d\u7ecf\u7406(\u975e\u6280\u672f)","kefujingli"],[23132,"\u5ba2\u670d\u4e3b\u7ba1(\u975e\u6280\u672f)","kefuzhuguan"],[23133,"\u5ba2\u670d\u4e13\u5458\/\u52a9\u7406(\u975e\u6280\u672f)","kefuzhuli"],[23134,"\u5ba2\u6237\u5173\u7cfb\u7ecf\u7406\/\u4e3b\u7ba1","kehuguanxijingli"],[23135,"\u6295\u8bc9\u4e13\u5458","tousuzhuanyuan"],[23136,"\u552e\u524d\/\u552e\u540e\u6280\u672f\u652f\u6301\u7ecf\u7406","shouqianjingli"],[23137,"\u552e\u524d\/\u552e\u540e\u6280\u672f\u652f\u6301\u4e3b\u7ba1","shouqianzhuguan"],[23138,"\u552e\u524d\/\u552e\u540e\u6280\u672f\u652f\u6301\u5de5\u7a0b\u5e08","shouqiangongchengshi"],[23139,"\u54a8\u8be2\u70ed\u7ebf\/\u547c\u53eb\u4e2d\u5fc3\u670d\u52a1\u4eba\u5458","zixunrexian"],[23140,"VIP\u4e13\u5458","vipzhuanyuan"],[23141,"\u5176\u4ed6","qitakf"]]],[22012,"\u8d22\u52a1\/\u5ba1\u8ba1\/\u7a0e\u52a1","caiwu",[[23142,"\u9996\u5e2d\u8d22\u52a1\u5b98CFO","cfo"],[23153,"\u8d22\u52a1\u603b\u76d1","caiwuzongjian"],[23158,"\u8d22\u52a1\u5206\u6790\u7ecf\u7406\/\u4e3b\u7ba1","caiwufenxijingli"],[23144,"\u8d22\u52a1\u4e3b\u7ba1\/\u603b\u5e10\u4e3b\u7ba1","caiwuzhuguan"],[23143,"\u8d22\u52a1\u7ecf\u7406","caiwujingli"],[23154,"\u8d22\u52a1\u987e\u95ee","caiwuguwen"],[23148,"\u8d22\u52a1\u5206\u6790\u5458","caiwufenxiyuan"],[23146,"\u8d22\u52a1\/\u4f1a\u8ba1\u52a9\u7406","caiwuzhuli"],[23159,"\u6210\u672c\u7ecf\u7406\/\u6210\u672c\u4e3b\u7ba1","chengbenjingli"],[23149,"\u6210\u672c\u7ba1\u7406\u5458","chengbenguanli"],[23155,"\u4f1a\u8ba1\u7ecf\u7406\/\u4f1a\u8ba1\u4e3b\u7ba1","kuaijijingli"],[23145,"\u4f1a\u8ba1","kuaiji1"],[23157,"\u4f1a\u8ba1\u6587\u5458","huijiwenyuan"],[23147,"\u56fa\u5b9a\u8d44\u4ea7\u4f1a\u8ba1","gudingzichanhuiji"],[23156,"\u51fa\u7eb3\u5458","chunayuan"],[23161,"\u5ba1\u8ba1\u7ecf\u7406\/\u4e3b\u7ba1","shenjijingli"],[23151,"\u5ba1\u8ba1\u4e13\u5458\/\u52a9\u7406","shenjizhuli"],[23162,"\u7a0e\u52a1\u7ecf\u7406\/\u7a0e\u52a1\u4e3b\u7ba1","shuiwujingli"],[23152,"\u7a0e\u52a1\u4e13\u5458\/\u52a9\u7406","shuiwuzhuli"],[23163,"\u7edf\u8ba1\u5458","tongjiyuan"],[23160,"\u8d44\u91d1\u7ecf\u7406\/\u4e3b\u7ba1","zijinjingli"],[23150,"\u8d44\u91d1\u4e13\u5458","zijinzhuanyuan"],[23164,"\u5176\u4ed6","qitacw"]]],[22013,"\u91d1\u878d\/\u8bc1\u5238\/\u671f\u8d27\/\u6295\u8d44","jinrongzhengquan",[[23165,"\u8bc1\u5238\/\u671f\u8d27\/\u5916\u6c47\u7ecf\u7eaa\u4eba","waihuijingjiren"],[23166,"\u8bc1\u5238\u5206\u6790\u5e08","zhengquanfenxishi"],[23167,"\u80a1\u7968\/\u671f\u8d27\u64cd\u76d8\u624b","gupiao"],[23742,"\u91d1\u878d\u4ea7\u54c1\u7ecf\u7406","jinrongchanpinjingli"],[23168,"\u91d1\u878d\/\u7ecf\u6d4e\u7814\u7a76\u5458","jingjiyanjiuyuan"],[23169,"\u6295\u8d44\/\u57fa\u91d1\u9879\u76ee\u7ecf\u7406","touzijingli"],[23170,"\u6295\u8d44\/\u7406\u8d22\u987e\u95ee","touziguwen"],[23171,"\u6295\u8d44\u94f6\u884c\u4e1a\u52a1","touziyinhang"],[23172,"\u878d\u8d44\u7ecf\u7406\/\u878d\u8d44\u4e3b\u7ba1","rongzijingli"],[23173,"\u878d\u8d44\u4e13\u5458","rongzizhuanyuan"],[23174,"\u62cd\u5356\/\u62c5\u4fdd\/\u5178\u5f53\u4e1a\u52a1","paimaidanbaodiandangyewu"],[23175,"\u5176\u4ed6","qitajr"]]],[22014,"\u94f6\u884c","yinhang1",[[23177,"\u884c\u957f\/\u526f\u884c\u957f","hangchang"],[23185,"\u8425\u4e1a\u90e8\u5927\u5802\u7ecf\u7406","yingyebu"],[23187,"\u4e2a\u4eba\u4e1a\u52a1\u90e8\u95e8\u7ecf\u7406\/\u4e3b\u7ba1","gerenyewujingli"],[23188,"\u516c\u53f8\u4e1a\u52a1\u90e8\u95e8\u7ecf\u7406\/\u4e3b\u7ba1","gongsiyewujingli"],[23189,"\u7efc\u5408\u4e1a\u52a1\u7ecf\u7406\/\u4e3b\u7ba1","zongheyewujingli"],[23191,"\u4fe1\u8d37\u7ba1\u7406","xindaiguanli"],[23181,"\u98ce\u9669\u63a7\u5236","fengxiankongzhi"],[23193,"\u6e05\u7b97\u4eba\u5458","qingsuanrenyuan"],[23183,"\u5916\u6c47\u4ea4\u6613","waihuijiaoyi"],[23182,"\u4fe1\u5ba1\u6838\u67e5","xinshenhecha"],[23190,"\u8d44\u4ea7\u8bc4\u4f30\/\u5206\u6790","zichanpinggu"],[23192,"\u8fdb\u51fa\u53e3\/\u4fe1\u7528\u8bc1\u7ed3\u7b97","jinchukou"],[23195,"\u94f6\u884c\u67dc\u5458","yinhangguiyuan"],[23180,"\u7efc\u5408\u4e1a\u52a1\u4e13\u5458","zongheyewu"],[23184,"\u9ad8\u7ea7\u5ba2\u6237\u7ecf\u7406\/\u5ba2\u6237\u7ecf\u7406","gaojikehu"],[23178,"\u4e2a\u4eba\u4e1a\u52a1\u5ba2\u6237\u7ecf\u7406","gerenyewu"],[23179,"\u516c\u53f8\u4e1a\u52a1\u5ba2\u6237\u7ecf\u7406","gongsiyewu"],[23194,"\u5ba2\u6237\u4e3b\u7ba1\/\u4e13\u5458","kehuzhuguan"],[23186,"\u94f6\u884c\u5361\u3001\u7535\u5b50\u94f6\u884c\u4e1a\u52a1\u63a8\u5e7f","yinxingka"],[23196,"\u5176\u4ed6","qitayh"]]],[22015,"\u4fdd\u9669","baoxian1",[[23200,"\u4fdd\u9669\u4ee3\u7406\/\u7ecf\u7eaa\u4eba\/\u5ba2\u6237\u7ecf\u7406","baoxiandaili"],[23203,"\u4fdd\u9669\u6838\u4fdd","baoxianhebao"],[23197,"\u4fdd\u9669\u7cbe\u7b97\u5e08","jingsuanshi"],[23205,"\u4fdd\u9669\u5ba2\u6237\u670d\u52a1\/\u7eed\u671f\u7ba1\u7406","baoxiankehu"],[23204,"\u4fdd\u9669\u7406\u8d54","baoxianlipei"],[23207,"\u4fdd\u9669\u5185\u52e4","baoxianneiqin"],[23206,"\u4fdd\u9669\u57f9\u8bad\u5e08","baoxianpeixun"],[23199,"\u4fdd\u9669\u4e1a\u52a1\u7ecf\u7406\/\u4e3b\u7ba1","baoxianyewu"],[23198,"\u4fdd\u9669\u4ea7\u54c1\u5f00\u53d1\/\u9879\u76ee\u7b56\u5212","baoxianchanpin"],[23202,"\u50a8\u5907\u7ecf\u7406\u4eba","chubeijingli"],[23201,"\u7406\u8d22\u987e\u95ee\/\u8d22\u52a1\u89c4\u5212\u5e08","licaiguwen"],[23208,"\u5951\u7ea6\u7ba1\u7406","qiyueguanli"],[23209,"\u5176\u4ed6","qitabx"]]],[22016,"\u4eba\u529b\u8d44\u6e90","renliziyuan",[[23210,"\u4eba\u4e8b\u603b\u76d1","renshizongjian"],[23211,"\u4eba\u4e8b\u7ecf\u7406","renshijingli"],[23212,"\u4eba\u4e8b\u4e3b\u7ba1","renshizhuguan"],[23213,"\u4eba\u4e8b\u4e13\u5458","renshizhuanyuan"],[23214,"\u4eba\u4e8b\u52a9\u7406","renshizhuli"],[23215,"\u62db\u8058\u7ecf\u7406\/\u4e3b\u7ba1","zhaopinjingli"],[23216,"\u62db\u8058\u4e13\u5458\/\u52a9\u7406","zhaopinzhuli"],[23217,"\u85aa\u8d44\u798f\u5229\u7ecf\u7406\/\u4e3b\u7ba1","xinzifulijingli"],[23218,"\u85aa\u8d44\u798f\u5229\u4e13\u5458\/\u52a9\u7406","xinzifulizhuli"],[23219,"\u7ee9\u6548\u8003\u6838\u7ecf\u7406\/\u4e3b\u7ba1","xiaojikaohejingli"],[23220,"\u7ee9\u6548\u8003\u6838\u4e13\u5458\/\u52a9\u7406","xiaojikaohezhuli"],[23221,"\u57f9\u8bad\u7ecf\u7406\/\u4e3b\u7ba1","peixunjingli"],[23222,"\u57f9\u8bad\u4e13\u5458\/\u52a9\u7406\/\u57f9\u8bad\u5e08","peixunzhuanyuan"],[23223,"\u4f01\u4e1a\u6587\u5316\/\u5458\u5de5\u5173\u7cfb\/\u5de5\u4f1a\u7ba1\u7406","qiyewenhua"],[23224,"\u4eba\u529b\u8d44\u6e90\u4fe1\u606f\u7cfb\u7edf\u4e13\u5458","renliziyuanxinxi"],[23225,"\u5176\u4ed6","qitarlzy"]]],[22017,"\u9ad8\u7ea7\u7ba1\u7406","gaojiguanli",[[23226,"\u9996\u5e2d\u6267\u884c\u5b98CEO\/\u603b\u88c1\/\u603b\u7ecf\u7406","ceo"],[23227,"\u9996\u5e2d\u8fd0\u8425\u5b98COO","coo"],[23228,"\u526f\u603b\u7ecf\u7406\/\u526f\u603b\u88c1","vp"],[23229,"\u5408\u4f19\u4eba","partner"],[23230,"\u603b\u76d1\/\u90e8\u95e8\u7ecf\u7406","zongjian"],[23231,"\u7b56\u7565\u53d1\u5c55\u603b\u76d1","celuefazhan"],[23232,"\u529e\u4e8b\u5904\u9996\u5e2d\u4ee3\u8868","banshichudaibiao"],[23233,"\u529e\u4e8b\u5904\/\u5206\u516c\u53f8\/\u5206\u652f\u673a\u6784\u7ecf\u7406","banshichu"],[23234,"\u603b\u88c1\u52a9\u7406\/\u603b\u7ecf\u7406\u52a9\u7406","zongcaizhuli"],[23752,"\u4f01\u4e1a\u79d8\u4e66\/\u8463\u4e8b\u4f1a\u79d8\u4e66","qiyemishudongshihuimishu"],[23753,"\u6295\u8d44\u8005\u5173\u7cfb","touzizheguanxi"],[23235,"\u5176\u4ed6","qitagj"]]],[22018,"\u884c\u653f\/\u540e\u52e4","xingzheng",[[23236,"\u884c\u653f\u603b\u76d1","xingzhengzongjian"],[23237,"\u884c\u653f\u7ecf\u7406\/\u4e3b\u7ba1\/\u529e\u516c\u5ba4\u4e3b\u4efb","xingzhengjingli"],[23238,"\u884c\u653f\u4e13\u5458\/\u52a9\u7406","xingzhengzhuli"],[23239,"\u7ecf\u7406\u52a9\u7406\/\u79d8\u4e66","jinglizhuli"],[23240,"\u524d\u53f0\u63a5\u5f85\/\u603b\u673a\/\u63a5\u5f85\u751f","qiantaijiedai"],[23241,"\u540e\u52e4","houqin"],[23242,"\u56fe\u4e66\u7ba1\u7406\u5458\/\u8d44\u6599\u7ba1\u7406\u5458","tushuguanliyuan"],[23243,"\u7535\u8111\u64cd\u4f5c\u5458\/\u6253\u5b57\u5458","daziyuan"],[23244,"\u5176\u4ed6","qitahq"]]],[22019,"\u5e7f\u544a","advertising",[[23245,"\u5e7f\u544a\u5ba2\u6237\u603b\u76d1\/\u526f\u603b\u76d1","guanggaokehuzongjian"],[23246,"\u5e7f\u544a\u5ba2\u6237\u7ecf\u7406","guanggaokehujingli"],[23247,"\u5e7f\u544a\u5ba2\u6237\u4e3b\u7ba1\/\u4e13\u5458","guanggaokehuzhuguan"],[23248,"\u5e7f\u544a\u521b\u610f\/\u8bbe\u8ba1\u7ecf\u7406","guanggaoshejijingli"],[23249,"\u5e7f\u544a\u521b\u610f\u603b\u76d1","guanggaochuangyizongjian"],[23250,"\u5e7f\u544a\u521b\u610f\/\u8bbe\u8ba1\u4e3b\u7ba1\/\u4e13\u5458","guanggaochuangyi"],[23251,"\u7f8e\u672f\u6307\u5bfc","meishuzhidao"],[23252,"\u6587\u6848\/\u7b56\u5212","wenancehua"],[23253,"\u4f01\u4e1a\/\u4e1a\u52a1\u53d1\u5c55\u7ecf\u7406","qiyefazhanjingli"],[23254,"\u4f01\u4e1a\u7b56\u5212\u4eba\u5458","qiyecehua"],[23255,"\u5176\u4ed6","qitagg"]]],[22020,"\u516c\u5173\/\u5a92\u4ecb","gongguanmeijie",[[23257,"\u516c\u5173\u4e3b\u7ba1","gongguanzhuguan"],[23256,"\u516c\u5173\u7ecf\u7406","gongguanjingli"],[23265,"\u516c\u5173\/\u5a92\u4ecb\u52a9\u7406","meijiezhuli"],[23258,"\u516c\u5173\u4e13\u5458","gongguanzhuanyuan"],[23259,"\u4f1a\u52a1\/\u4f1a\u5c55\u7ecf\u7406","huiwujingli"],[23260,"\u4f1a\u52a1\/\u4f1a\u5c55\u4e3b\u7ba1","huiwuzhuguan23260"],[23261,"\u4f1a\u52a1\/\u4f1a\u5c55\u4e13\u5458","huiwuzhuanyuan"],[23267,"\u6d3b\u52a8\u7b56\u5212","huodongcehua"],[23749,"\u6d3b\u52a8\u6267\u884c","huodongzhixing"],[23263,"\u5a92\u4ecb\u4e3b\u7ba1","meijiezhuguan"],[23262,"\u5a92\u4ecb\u7ecf\u7406","meijiejingli"],[23266,"\u5a92\u4ecb\u9500\u552e","meijiexiaoshou"],[23264,"\u5a92\u4ecb\u4e13\u5458","meijiezhuanyuan"],[23268,"\u5176\u4ed6","qitamj"]]],[22021,"\u5e02\u573a\/\u8425\u9500","shichang",[[23269,"\u5e02\u573a\/\u8425\u9500\/\u62d3\u5c55\u603b\u76d1","tuozhanzongjian"],[23278,"\u5e02\u573a\/\u8425\u9500\/\u62d3\u5c55\u7ecf\u7406","tuozhanjingli"],[23270,"\u5e02\u573a\/\u8425\u9500\/\u62d3\u5c55\u4e3b\u7ba1","tuozhanzhuguan"],[23279,"\u5e02\u573a\/\u8425\u9500\/\u62d3\u5c55\u4e13\u5458","tuozhanzhuanyuan"],[23271,"\u5e02\u573a\u52a9\u7406","shichangzhuli"],[23280,"\u5e02\u573a\u5206\u6790\/\u8c03\u7814\u4eba\u5458","shichangfenxi"],[23272,"\u4ea7\u54c1\/\u54c1\u724c\u7ecf\u7406","pinpaijingli"],[23281,"\u4ea7\u54c1\/\u54c1\u724c\u4e3b\u7ba1","paizhuguan"],[23273,"\u4ea7\u54c1\/\u54c1\u724c\u4e13\u5458","pinpaizhuanyuan"],[23282,"\u5e02\u573a\u901a\u8def\u7ecf\u7406\/\u4e3b\u7ba1","shichangtonglujingli"],[23274,"\u5e02\u573a\u901a\u8def\u4e13\u5458","shichangtonglu"],[23283,"\u5e02\u573a\u4f01\u5212\u7ecf\u7406\/\u4e3b\u7ba1","shichangqihuajingli"],[23275,"\u5e02\u573a\u4f01\u5212\u4e13\u5458","shichangqihua"],[23284,"\u4fc3\u9500\u7ecf\u7406","cuxiaojingli"],[23276,"\u4fc3\u9500\u4e3b\u7ba1\/\u7763\u5bfc","cuxiaodudao"],[23285,"\u4fc3\u9500\u5458\/\u5bfc\u8d2d","cuxiaoyuan"],[23277,"\u9009\u5740\u62d3\u5c55\/\u65b0\u5e97\u5f00\u53d1","xindiankaifa"],[23286,"\u5176\u4ed6","qitascyx"]]],[22022,"\u5f71\u89c6\/\u5a92\u4f53","yingshimeiti",[[23288,"\u5bfc\u6f14\/\u7f16\u5bfc","daoyan"],[23296,"\u653e\u6620\u7ecf\u7406\/\u4e3b\u7ba1","fangyingjingli"],[23297,"\u653e\u6620\u5458","fangyingyuan"],[23293,"\u540e\u671f\u5236\u4f5c","houqizhizuo"],[23298,"\u5316\u5986\u5e08\/\u9020\u578b\u5e08","zaoxingshi"],[23290,"\u7ecf\u7eaa\u4eba\/\u661f\u63a2","jingjiren"],[23295,"\u914d\u97f3\u5458","peiyinyuan"],[23292,"\u6444\u5f71\u5e08\/\u6444\u50cf\u5e08","sheyingshi"],[23291,"\u6f14\u5458\/\u6a21\u7279\/\u4e3b\u6301\u4eba","yanyuan"],[23289,"\u827a\u672f\/\u8bbe\u8ba1\u603b\u76d1","yishuzongjian"],[23294,"\u97f3\u6548\u5e08","yinxiaoshi"],[23287,"\u5f71\u89c6\u7b56\u5212\/\u5236\u4f5c\u4eba\u5458","yingshicehua"],[23299,"\u5176\u4ed6","qitays"]]],[22023,"\u5199\u4f5c\/\u51fa\u7248\/\u5370\u5237","xiezuo",[[23300,"\u603b\u7f16\/\u526f\u603b\u7f16","zongbian"],[23308,"\u7f16\u8f91","bianji"],[23301,"\u4f5c\u5bb6\/\u64b0\u7a3f\u4eba","zuojia"],[23309,"\u8bb0\u8005","jizhe"],[23302,"\u7535\u8bdd\u91c7\u7f16","dianhuacaibian"],[23310,"\u7f8e\u672f\u7f16\u8f91","meishubianji"],[23303,"\u6392\u7248\u8bbe\u8ba1","paibansheji"],[23311,"\u6821\u5bf9\/\u5f55\u5165","xiaodui"],[23304,"\u51fa\u7248\/\u53d1\u884c","chuban"],[23312,"\u7535\u5206\u64cd\u4f5c\u5458","dianfencaozuo"],[23305,"\u5370\u5237\u6392\u7248\/\u5236\u7248","yinshuapaiban"],[23313,"\u6570\u7801\u76f4\u5370\/\u83f2\u6797\u8f93\u51fa","shumazhiyin"],[23306,"\u6253\u7a3f\u673a\u64cd\u4f5c\u5458","dagaoji"],[23314,"\u8c03\u58a8\u6280\u5e08","diaomojishi"],[23307,"\u5370\u5237\u673a\u68b0\u673a\u957f","yinshuaji"],[23315,"\u6652\u7248\/\u62fc\u7248\/\u88c5\u8ba2\/\u70eb\u91d1\u6280\u5de5","shaiban"],[23316,"\u5176\u4ed6","qitaxz"]]],[22024,"\u827a\u672f\/\u8bbe\u8ba1","yishu",[[23318,"\u5e73\u9762\u8bbe\u8ba1\u603b\u76d1","pingmiansheji"],[23320,"\u5e73\u9762\u8bbe\u8ba1\u5e08","pingmianshejishi"],[23319,"\u5e73\u9762\u8bbe\u8ba1\u7ecf\u7406\/\u4e3b\u7ba1","pingmianshejijingli"],[23329,"\u5bb6\u5177\/\u5bb6\u5c45\u7528\u54c1\u8bbe\u8ba1","jiajusheji"],[23328,"\u5de5\u827a\u54c1\/\u73e0\u5b9d\u8bbe\u8ba1\u9274\u5b9a","gongyipin"],[23327,"\u5de5\u4e1a\/\u4ea7\u54c1\u8bbe\u8ba1","gongyesheji"],[23325,"\u591a\u5a92\u4f53\u8bbe\u8ba1","duomeitisheji"],[23322,"\u52a8\u753b\/3D\u8bbe\u8ba1","donghua"],[23326,"\u5305\u88c5\u8bbe\u8ba1","baozhuangsheji"],[23324,"\u5c55\u89c8\/\u5c55\u793a\/\u5e97\u9762\u8bbe\u8ba1","zhanlansheji"],[23323,"\u539f\u753b\u5e08","yuanhuashi"],[23330,"\u73a9\u5177\u8bbe\u8ba1","wanjusheji"],[23321,"\u7ed8\u753b","huihua"],[23331,"\u5176\u4ed6","qitayssj"]]],[22025,"\u54a8\u8be2\/\u987e\u95ee","zixun",[[23332,"\u4e13\u4e1a\u987e\u95ee","zhuanyeguwen"],[23333,"\u54a8\u8be2\u603b\u76d1","zixunzongjian"],[23334,"\u54a8\u8be2\u7ecf\u7406","zixunjingli"],[23335,"\u4e13\u4e1a\u57f9\u8bad\u5e08","peixunshi"],[23336,"\u54a8\u8be2\u5458","zixunyuan"],[23337,"\u8c03\u7814\u5458","diaoyanyuan"],[23338,"\u730e\u5934\/\u4eba\u624d\u4e2d\u4ecb","lietou"],[23339,"\u60c5\u62a5\u4fe1\u606f\u5206\u6790\u4eba\u5458","qingbaoxinxi"],[23340,"\u5176\u4ed6","qitazxgw"]]],[22026,"\u5f8b\u5e08\/\u6cd5\u52a1\/\u5408\u89c4","lvshi",[[23341,"\u5f8b\u5e08\/\u6cd5\u5f8b\u987e\u95ee","falvguwen"],[23342,"\u5f8b\u5e08\u52a9\u7406","lvshizhuli"],[23343,"\u6cd5\u52a1\u7ecf\u7406","fawujingli"],[23344,"\u6cd5\u52a1\u4e3b\u7ba1\/\u4e13\u5458","fawuzhuguan"],[23345,"\u6cd5\u52a1\u52a9\u7406","fawuzhuli"],[23346,"\u5408\u89c4\u7ecf\u7406","heguijingli"],[23347,"\u5408\u89c4\u4e3b\u7ba1\/\u4e13\u5458","heguizhuguan"],[23348,"\u77e5\u8bc6\u4ea7\u6743\/\u4e13\u5229\/\u5546\u6807","zhishichanquan"],[23349,"\u5176\u4ed6","qitafl"]]],[22027,"\u6559\u5e08","jiaoshi",[[23350,"\u6821\u957f","xiaochang"],[23351,"\u5927\u5b66\u6559\u6388","daxuejiaoshou"],[23352,"\u8bb2\u5e08\/\u52a9\u6559","jiangshi2"],[23356,"\u9662\u6821\u6559\u52a1\u7ba1\u7406\u4eba\u5458","jiaowu"],[23353,"\u4e2d\u5b66\u6559\u5e08","zhongxuejiaoshi"],[23359,"\u804c\u4e1a\u6280\u672f\u6559\u5e08","jishujiaoshi"],[23354,"\u5c0f\u5b66\u6559\u5e08","xiaoxuejiaoshi"],[23732,"\u97f3\u4e50\/\u7f8e\u672f\u6559\u5e08","yinyuemeishujiaoshi"],[23355,"\u5e7c\u6559","youjiao"],[23358,"\u5bb6\u6559","jiajiao"],[23357,"\u517c\u804c\u6559\u5e08","jianzhijiaoshi"],[23754,"\u5916\u8bed\u57f9\u8bad\u5e08","waiyupeixunshi"],[23360,"\u5176\u4ed6","qitajs"]]],[22028,"\u57f9\u8bad","peixun",[[23361,"\u57f9\u8bad\u7763\u5bfc","peixundudao"],[23362,"\u57f9\u8bad\u8bb2\u5e08","peixunjiangshi"],[23363,"\u57f9\u8bad\u7b56\u5212","peixuncehua"],[23364,"\u57f9\u8bad\u4ea7\u54c1\u5f00\u53d1","peixunchanpin"],[23365,"\u57f9\u8bad\/\u8bfe\u7a0b\u987e\u95ee","kechengguwen"],[23366,"\u57f9\u8bad\u52a9\u7406","peixunzhuli"],[23367,"\u5176\u4ed6","qitapx"]]],[22029,"\u79d1\u7814\u4eba\u5458","keyanrenyuan",[[23368,"\u79d1\u7814\u7ba1\u7406\u4eba\u5458","keyanguanli"],[23369,"\u79d1\u7814\u4eba\u5458","keyanren"],[23370,"\u5176\u4ed6","qitaky"]]],[22030,"\u751f\u7269\/\u5236\u836f\/\u533b\u7597\u5668\u68b0","shengwu",[[23382,"\u5316\u5b66\u5206\u6790\u6d4b\u8bd5\u5458","huaxuefenxi"],[23374,"\u4e34\u5e8a\u6570\u636e\u5206\u6790\u5458","linchuangshuju"],[23384,"\u4e34\u5e8a\u534f\u8c03\u5458","linchuang"],[23373,"\u4e34\u5e8a\u7814\u7a76\u5458","linchuangyanjiu"],[23392,"\u5176\u4ed6","qitasw"],[23371,"\u751f\u7269\u5de5\u7a0b\/\u751f\u7269\u5236\u836f","shengwugongcheng"],[23375,"\u836f\u54c1\u751f\u4ea7\/\u8d28\u91cf\u7ba1\u7406","yaopinshengchan"],[23386,"\u836f\u54c1\u5e02\u573a\u63a8\u5e7f\u7ecf\u7406","yaopintuiguang"],[23376,"\u836f\u54c1\u5e02\u573a\u63a8\u5e7f\u4e3b\u7ba1\/\u4e13\u5458","yaopinshichang"],[23385,"\u836f\u54c1\u6ce8\u518c","yaopinzhuce"],[23390,"\u533b\u7597\u5668\u68b0\u751f\u4ea7\/\u8d28\u91cf\u7ba1\u7406","yiliaoqixieshengchan"],[23380,"\u533b\u7597\u5668\u68b0\u5e02\u573a\u63a8\u5e7f","yiliaotuiguang"],[23381,"\u533b\u7597\u5668\u68b0\u7ef4\u4fee\u4eba\u5458","yiliaoweixiu"],[23391,"\u533b\u7597\u5668\u68b0\u9500\u552e\u4ee3\u8868","yiliaoqixiexiaoshoudaibiao"],[23747,"\u533b\u7597\u5668\u68b0\u9500\u552e\u7ecf\u7406\/\u4e3b\u7ba1","yiliaoqixiexiaoshoujinglizhuguan"],[23731,"\u533b\u7597\u5668\u68b0\u7814\u53d1","yiliaoqixieyanfa"],[23379,"\u533b\u7597\u5668\u68b0\u6ce8\u518c","yiliaoqixiezhuce"],[23372,"\u533b\u836f\u6280\u672f\u7814\u53d1\u7ba1\u7406\u4eba\u5458","yiyaoyanfa"],[23383,"\u533b\u836f\u6280\u672f\u7814\u53d1\u4eba\u5458","yiyaojishu"],[23389,"\u533b\u836f\u9500\u552e\u4ee3\u8868","yiyaoxiaoshou"],[23378,"\u533b\u836f\u9500\u552e\u7ecf\u7406\/\u4e3b\u7ba1","yiyaoxiaoshoujingli"],[23387,"\u533b\u836f\u62db\u5546","yiyaozhaoshang"],[23388,"\u62db\u6295\u6807\u7ba1\u7406","zhaotoubiao"],[23377,"\u653f\u5e9c\u4e8b\u52a1\u7ba1\u7406","zhengfushiwu"]]],[22031,"\u5316\u5de5","huagong",[[23393,"\u5316\u5de5\u6280\u672f\u5e94\u7528\/\u5316\u5de5\u5de5\u7a0b\u5e08","huagongyingyong"],[23394,"\u5316\u5de5\u5b9e\u9a8c\u5ba4\u7814\u7a76\u5458\/\u6280\u672f\u5458","huagongshiyan"],[23398,"\u5316\u5986\u54c1\u7814\u53d1","huazhuangpin"],[23396,"\u914d\u8272\u6280\u672f\u5458","peise"],[23399,"\u98df\u54c1\/\u996e\u6599\u7814\u53d1","shipinyanfa"],[23397,"\u5851\u6599\u5de5\u7a0b\u5e08","suliao"],[23395,"\u6d82\u6599\u7814\u53d1\u5de5\u7a0b\u5e08","tuliaoyanfa"],[23400,"\u9020\u7eb8\u7814\u53d1","zaozhiyanfa"],[23401,"\u5176\u4ed6","qitahg"]]],[22032,"\u533b\u9662\/\u533b\u7597\/\u62a4\u7406","yiyuan",[[23402,"\u533b\u9662\u7ba1\u7406\u4eba\u5458","yiyuanguanli"],[23410,"\u62a4\u7406\u4e3b\u4efb\/\u62a4\u58eb\u957f","hushichang"],[23420,"\u62a4\u58eb\/\u62a4\u7406\u4eba\u5458","hushi"],[23407,"\u513f\u79d1\u533b\u751f","erkeyisheng"],[23748,"\u653e\u5c04\u79d1\u533b\u5e08","fangshekeyishi"],[23415,"\u7406\u7597\u5e08","liliaoshi"],[23414,"\u9ebb\u9189\u533b\u751f","mazuiyisheng"],[23405,"\u7f8e\u5bb9\u6574\u5f62\u5e08","meirongshi"],[23412,"\u5185\u79d1\u533b\u751f","neikeyisheng"],[23411,"\u517d\u533b","shouyi"],[23403,"\u5916\u79d1\u533b\u751f","waikeyisheng"],[23417,"\u5fc3\u7406\u533b\u751f","xinliyisheng"],[23404,"\u7259\u79d1\u533b\u751f","yakeyisheng"],[23421,"\u9a8c\u5149\u5e08","yanguangshi"],[23418,"\u836f\u5e93\u4e3b\u4efb\/\u836f\u5242\u5e08","yaojishi"],[23409,"\u533b\u836f\u5b66\u68c0\u9a8c","yiyaojianyan"],[23406,"\u4e2d\u533b\u79d1\u533b\u751f","zhongyi"],[23413,"\u4e13\u79d1\u533b\u751f","zhuankeyisheng"],[23408,"\u8425\u517b\u5e08","yingyangshi"],[23416,"\u9488\u7078\u3001\u63a8\u62ff","zhenjiu"],[23419,"\u516c\u5171\u536b\u751f\/\u75be\u75c5\u63a7\u5236","jibingkongzhi"],[23422,"\u5176\u4ed6","qitayy"]]],[22033,"\u5efa\u7b51\u88c5\u6f62\/\u5e02\u653f\u5efa\u8bbe","jianzhuzhuanghuang",[[23433,"\u5efa\u7b51\u5de5\u7a0b\u7ba1\u7406\/\u9879\u76ee\u7ecf\u7406","jianzhuxiangmujingli"],[23437,"\u5efa\u7b51\u5de5\u7a0b\u5e08","jianzhugongchengshi"],[23447,"\u5efa\u7b51\u5de5\u7a0b\u9a8c\u6536","gongchengyanshou"],[23427,"\u5efa\u7b51\u673a\u7535\u5de5\u7a0b\u5e08","jianzhujidian"],[23424,"\u5efa\u7b51\u8bbe\u8ba1\u5e08","jianzhushejishi"],[23431,"\u5efa\u7b51\u5236\u56fe\/\u6a21\u578b\/\u6e32\u67d3","jianzhuzhitumoxingxuanran"],[23425,"\u7ed3\u6784\/\u571f\u6728\/\u571f\u5efa\u5de5\u7a0b\u5e08","jiegou"],[23445,"\u5f00\u53d1\u62a5\u5efa","kaifabaojian"],[23435,"\u5b89\u5168\u5458","anquanyuan"],[23444,"\u6d4b\u7ed8\/\u6d4b\u91cf","cehui"],[23423,"\u9ad8\u7ea7\u5efa\u7b51\u5de5\u7a0b\u5e08\/\u603b\u5de5","zonggong"],[23428,"\u7ed9\u6392\u6c34\/\u6696\u901a\u5de5\u7a0b","jipaishui"],[23434,"\u5de5\u7a0b\u76d1\u7406","gongchengjianli"],[23432,"\u5de5\u7a0b\u9020\u4ef7\u5e08\/\u9884\u7ed3\u7b97\u7ecf\u7406","zaojiashi"],[23439,"\u516c\u8def\/\u6865\u6881\/\u6e2f\u53e3\/\u96a7\u9053\u5de5\u7a0b","suidaogongcheng"],[23429,"\u89c4\u5212\u4e0e\u8bbe\u8ba1","guihua"],[23448,"\u5408\u540c\u7ba1\u7406","hetongguanli"],[23440,"\u697c\u5b87\u81ea\u52a8\u5316","louyu"],[23442,"\u5e55\u5899\u5de5\u7a0b\u5e08","muqiang"],[23436,"\u65bd\u5de5\u5458","shigongyuan"],[23438,"\u5e02\u653f\u5de5\u7a0b\u5e08","shizheng"],[23443,"\u5ba4\u5185\u8bbe\u8ba1","shineisheji"],[23426,"\u5ca9\u571f\u5de5\u7a0b","yantu"],[23446,"\u9884\u7ed3\u7b97\u5458","yujiesuanyuan"],[23430,"\u56ed\u827a\/\u56ed\u6797\/\u666f\u89c2\u8bbe\u8ba1","yuanyisheji"],[23441,"\u667a\u80fd\u5927\u53a6\/\u7efc\u5408\u5e03\u7ebf\/\u5b89\u9632\/\u5f31\u7535","zhinengdashazonghebuxian"],[23449,"\u8d44\u6599\u5458","ziliaoyuan"],[23450,"\u5176\u4ed6","qitajz"]]],[22034,"\u623f\u5730\u4ea7","fangdichanzn",[[23451,"\u623f\u5730\u4ea7\u9879\u76ee\/\u5f00\u53d1\/\u7b56\u5212\u7ecf\u7406","fangdichanxiangmujingli"],[23452,"\u623f\u5730\u4ea7\u9879\u76ee\/\u5f00\u53d1\/\u7b56\u5212\u4e3b\u7ba1\/\u4e13\u5458","fangdichanxiangmuzhuanyuan"],[23454,"\u623f\u5730\u4ea7\u9879\u76ee\u62db\u6295\u6807","fangdichanxiangmuzhaobiao"],[23457,"\u623f\u5730\u4ea7\u9500\u552e\u7ecf\u7406\/\u4e3b\u7ba1","fangdichanxiaoshoujingli"],[23458,"\u623f\u5730\u4ea7\u9500\u552e\u4eba\u5458","fangdichanxiaoshou"],[23456,"\u623f\u5730\u4ea7\u4e2d\u4ecb\/\u4ea4\u6613","fangdichanjiaoyi"],[23750,"\u623f\u5730\u4ea7\u8d44\u4ea7\u7ba1\u7406","fangdichanzichanguanli"],[23453,"\u623f\u4ea7\u9879\u76ee\u914d\u5957\u5de5\u7a0b\u5e08","fangchanxiangmupeitao"],[23455,"\u623f\u5730\u4ea7\u8bc4\u4f30","fangdichanpinggu"],[23751,"\u623f\u5730\u4ea7\u6295\u8d44\u5206\u6790","fangdichantouzifenxi"],[23459,"\u5176\u4ed6","qitafdc"]]],[22035,"\u7269\u4e1a\u7ba1\u7406","wuyeguanli",[[23460,"\u9ad8\u7ea7\u7269\u4e1a\u987e\u95ee\/\u7269\u4e1a\u987e\u95ee","wuyeguwen"],[23461,"\u7269\u4e1a\u7ba1\u7406\u7ecf\u7406\/\u4e3b\u7ba1","wuyeguanlijingli"],[23462,"\u7269\u4e1a\u7ba1\u7406\u4e13\u5458\/\u52a9\u7406","wuyeguanlizhuli"],[23463,"\u7269\u4e1a\u62db\u5546\/\u79df\u8d41\/\u79df\u552e","wuyezulin"],[23464,"\u7269\u4e1a\u8bbe\u65bd\u7ba1\u7406\u4eba\u5458","wuyesheshi"],[23465,"\u7269\u4e1a\u673a\u7535\u5de5\u7a0b\u5e08","wuyejidian"],[23466,"\u7269\u4e1a\u7ef4\u4fee\u4eba\u5458","wuyeweixiu"],[23467,"\u5176\u4ed6","qitawy"]]],[22036,"\u9910\u996e\/\u5a31\u4e50","canyin",[[23468,"\u9910\u996e\/\u5a31\u4e50\u7ba1\u7406","yuleguanli"],[23469,"\u9910\u996e\/\u5a31\u4e50\u9886\u73ed\/\u90e8\u957f","yulelingban"],[23470,"\u9910\u996e\/\u5a31\u4e50\u670d\u52a1\u5458","yulefuwuyuan"],[23474,"\u884c\u653f\u4e3b\u53a8\/\u53a8\u5e08\u957f","chushichang"],[23733,"\u53a8\u5e08\u52a9\u7406\/\u5b66\u5f92","chushizhuli"],[23475,"\u53a8\u5e08\/\u9762\u70b9\u5e08","chushi"],[23476,"\u8c03\u9152\u5e08\/\u4f8d\u9152\u5e08\/\u5427\u53f0\u5458","tiaojiushishijiushibataiyuan"],[23471,"\u4f20\u83dc\u4e3b\u7ba1\/\u4f20\u83dc\u5458","chuancaiyuan"],[23472,"\u793c\u4eea\/\u8fce\u5bbe","liyi"],[23473,"\u53f8\u4eea","siyi"],[23477,"\u8336\u827a\u5e08","chayishi"],[23478,"\u5176\u4ed6","qitacy"]]],[22037,"\u9152\u5e97\/\u65c5\u6e38","jiudianlvyou",[[23479,"\u9152\u5e97\/\u5bbe\u9986\u7ecf\u7406","jiudianjingli"],[23489,"\u9152\u5e97\/\u5bbe\u9986\u8425\u9500","jiudianyingxiao"],[23493,"\u5ba2\u623f\u670d\u52a1\u5458\/\u697c\u9762\u670d\u52a1\u5458","kefangfuwuyuan"],[23481,"\u5bbe\u5ba2\u670d\u52a1\u7ecf\u7406","binkefuwu"],[23490,"\u5927\u5802\u7ecf\u7406","datangjingli"],[23485,"\u7ba1\u5bb6\u90e8\u7ecf\u7406\/\u4e3b\u7ba1","guanjiabujingli"],[23491,"\u697c\u9762\u7ecf\u7406","loumianjingli"],[23480,"\u5bb4\u4f1a\u7ba1\u7406","yanhuiguanli"],[23492,"\u9884\u8ba2\u90e8\u4e3b\u7ba1","yudingbuzhuguan"],[23483,"\u9884\u8ba2\u5458","yudingyuan"],[23497,"\u884c\u7a0b\u7ba1\u7406\/\u8ba1\u8c03","xingchengguanli"],[23488,"\u7968\u52a1\/\u8ba2\u623f\u670d\u52a1","piaowu"],[23482,"\u524d\u5385\u63a5\u5f85","qiantingjiedai"],[23495,"\u6e05\u6d01\u670d\u52a1\u4eba\u5458","qingjiefuwu"],[23494,"\u884c\u674e\u5458","xingliyuan"],[23486,"\u5065\u8eab\u623f\u670d\u52a1","jianshenfang"],[23487,"\u5bfc\u6e38\/\u65c5\u884c\u987e\u95ee","daoyou"],[23496,"\u65c5\u6e38\u4ea7\u54c1\u9500\u552e","lvyouchanpin"],[23484,"\u673a\u573a\u4ee3\u8868","jichangdaibiao"],[23498,"\u7b7e\u8bc1\u4e13\u5458","qianzhengzhuanyuan"],[23499,"\u5176\u4ed6","qitajd"]]],[22038,"\u7f8e\u5bb9\/\u5065\u8eab\/\u4f53\u80b2","meirongjianshen",[[23755,"\u7f8e\u5bb9\u5bfc\u5e08","meirongdaoshi"],[23500,"\u7f8e\u5bb9\u987e\u95ee\/\u5316\u5986","meirongguwen"],[23503,"\u7f8e\u5bb9\u52a9\u7406\/\u89c1\u5e2d\u7f8e\u5bb9\u5e08","meirongzhuli"],[23501,"\u5f69\u5986\u57f9\u8bad\u5e08","caizhuangpeixun"],[23507,"\u7f8e\u7532\u5e08","meijiashi"],[23502,"\u4e13\u67dc\u5f69\u5986\u987e\u95ee(BA)","caizhuangguwen"],[23513,"\u5ba0\u7269\u62a4\u7406\/\u7f8e\u5bb9","chongwuhuli"],[23505,"\u53d1\u578b\u5e08","faxingshi"],[23506,"\u53d1\u578b\u52a9\u7406\/\u5b66\u5f92","faxingzhuli"],[23504,"\u7626\u8eab\u987e\u95ee","shoushenguwen"],[23510,"\u4f53\u80b2\u8fd0\u52a8\u6559\u7ec3","tiyujiaolian"],[23512,"\u745c\u4f3d\/\u821e\u8e48\u8001\u5e08","yujia"],[23509,"\u5065\u8eab\u987e\u95ee\/\u6559\u7ec3","jiaolian"],[23511,"\u6551\u751f\u5458","jiushengyuan"],[23508,"\u6309\u6469\/\u8db3\u7597","anmozuliao"],[23514,"\u5176\u4ed6","qitamr"]]],[22039,"\u767e\u8d27\/\u8fde\u9501\/\u96f6\u552e\u670d\u52a1","baihuo",[[23515,"\u5e97\u957f\/\u5356\u573a\u7ecf\u7406\/\u697c\u9762\u7ba1\u7406","dianchang"],[23516,"\u54c1\u7c7b\u7ecf\u7406","pinleijingli"],[23517,"\u5e97\u5458\/\u8425\u4e1a\u5458","dianyuan"],[23520,"\u6536\u94f6\u4e3b\u7ba1\/\u6536\u94f6\u5458","shouyinyuan"],[23521,"\u7406\u8d27\u5458\/\u9648\u5217\u5458\/\u6536\u8d27\u5458","shouhuoyuan"],[23526,"\u517c\u804c\u5e97\u5458","jianzhidianyuan"],[23519,"\u9632\u635f\u5458\/\u5185\u4fdd","fangsunyuan"],[23522,"\u5bfc\u8d2d\u5458","daogouyuan"],[23518,"\u5b89\u9632\u4e3b\u7ba1","anfangzhuguan"],[23523,"\u897f\u70b9\u5e08\/\u9762\u5305\u7cd5\u70b9\u52a0\u5de5","xidianshi"],[23525,"\u719f\u98df\u52a0\u5de5","shushijiagong"],[23524,"\u751f\u9c9c\u98df\u54c1\u52a0\u5de5\/\u5904\u7406","shengxianshipin"],[23527,"\u5176\u4ed6","qitabh"]]],[22040,"\u4ea4\u901a\u8fd0\u8f93\u670d\u52a1","jiaotongyunshu",[[23528,"\u98de\u673a\u673a\u957f\/\u526f\u673a\u957f","feijijichang"],[23529,"\u7a7a\u4e58\u4eba\u5458","kongchengrenyuan"],[23530,"\u5730\u52e4\u4eba\u5458","diqinrenyuan"],[23531,"\u5217\u8f66\/\u5730\u94c1\u8f66\u957f","liechechang"],[23532,"\u5217\u8f66\/\u5730\u94c1\u53f8\u673a","liechesiji"],[23533,"\u8239\u957f\/\u526f\u8239\u957f","chuanchang"],[23535,"\u4e58\u52a1\u5458","chengwuyuan"],[23536,"\u53f8\u673a","siji"],[23534,"\u8239\u5458","chuanyuan"],[23537,"\u5176\u4ed6","qitajt"]]],[22041,"\u4fdd\u5b89\/\u5bb6\u653f\/\u5176\u4ed6\u670d\u52a1","baoan",[[23538,"\u4fdd\u5b89\u7ecf\u7406","baoanjingli"],[23539,"\u4fdd\u5b89\u4eba\u5458","baoanrenyuan"],[23540,"\u4fdd\u9556","baobiao"],[23541,"\u5bfb\u547c\u5458\/\u8bdd\u52a1\u5458","huawuyuan"],[23542,"\u642c\u8fd0\u5de5","banyungong"],[23543,"\u6e05\u6d01\u5de5","qingjiegong"],[23544,"\u5bb6\u653f\u670d\u52a1\/\u4fdd\u59c6","baomu"],[23545,"\u5176\u4ed6","qitaba"]]],[22042,"\u751f\u4ea7\/\u8425\u8fd0","yingyun",[[23546,"\u5de5\u5382\u7ecf\u7406\/\u5382\u957f","gongchangjingli"],[23547,"\u603b\u5de5\u7a0b\u5e08\/\u526f\u603b\u5de5\u7a0b\u5e08","zonggongchengshi"],[23548,"\u9879\u76ee\u603b\u76d1","xiangmuzongjian"],[23549,"\u9879\u76ee\u7ecf\u7406\/\u4e3b\u7ba1","xiangmuzhuguan"],[23550,"\u9879\u76ee\u5de5\u7a0b\u5e08","xiangmugongchengshi"],[23551,"\u8425\u8fd0\u7ecf\u7406","yingyunjingli"],[23552,"\u8425\u8fd0\u4e3b\u7ba1","yingyunzhuguan"],[23553,"\u751f\u4ea7\u603b\u76d1","shengchanzongjian"],[23554,"\u751f\u4ea7\u7ecf\u7406\/\u8f66\u95f4\u4e3b\u4efb","shengchanjingli"],[23555,"\u751f\u4ea7\u8ba1\u5212\/\u7269\u6599\u7ba1\u7406(PMC)","pmc"],[23556,"\u751f\u4ea7\u4e3b\u7ba1\/\u7763\u5bfc\/\u9886\u73ed\/\u7ec4\u957f","shengchandudao"],[23557,"\u751f\u4ea7\u6587\u5458","shengchanwenyuan"],[23558,"\u5316\u9a8c\u5458","huayanyuan"],[23559,"\u5176\u4ed6","qitasc"]]],[22043,"\u8d28\u91cf\u7ba1\u7406\/\u5b89\u5168\u9632\u62a4","zhiliang",[[23560,"\u8d28\u91cf\u7ba1\u7406\/\u6d4b\u8bd5\u7ecf\u7406(QA\/QC\u7ecf\u7406)","zhiliangceshijingli"],[23561,"\u8d28\u91cf\u7ba1\u7406\/\u6d4b\u8bd5\u4e3b\u7ba1(QA\/QC\u4e3b\u7ba1)","zhiliangceshizhuguan"],[23562,"\u8d28\u91cf\u7ba1\u7406\/\u6d4b\u8bd5\u5de5\u7a0b\u5e08(QA\/QC\u5de5\u7a0b\u5e08)","zhiliangceshigongchengshi"],[23563,"\u8d28\u91cf\u68c0\u9a8c\u5458\/\u6d4b\u8bd5\u5458","zhiliangceshiyuan"],[23564,"\u53ef\u9760\u5ea6\u5de5\u7a0b\u5e08","kekaodugongchengshi"],[23565,"\u6545\u969c\u5206\u6790\u5de5\u7a0b\u5e08","guzhangfenxi"],[23566,"\u8ba4\u8bc1\u5de5\u7a0b\u5e08\/\u5ba1\u6838\u5458","renzhengshenheyuan"],[23567,"\u4f53\u7cfb\u5de5\u7a0b\u5e08\/\u5ba1\u6838\u5458","tixishenheyuan"],[23568,"\u73af\u5883\/\u5065\u5eb7\/\u5b89\u5168\u7ecf\u7406\/\u4e3b\u7ba1(EHS)","huanjingjingli"],[23569,"\u73af\u5883\/\u5065\u5eb7\/\u5b89\u5168\u5de5\u7a0b\u5e08(EHS)","huanjinggongchengshi"],[23728,"\u5b89\u5168\u9632\u62a4","anquanfanghu"],[23570,"\u4f9b\u5e94\u5546\u7ba1\u7406","gongyingshangguanli"],[23571,"\u91c7\u8d2d\u6750\u6599\u3001\u8bbe\u5907\u8d28\u91cf\u7ba1\u7406","caigoucailiao"],[23572,"\u5176\u4ed6","qitazl"]]],[22044,"\u5de5\u7a0b\/\u673a\u68b0\/\u80fd\u6e90","gongcheng",[[23576,"\u5de5\u7a0b\/\u8bbe\u5907\u4e3b\u7ba1","shebeizhuguan"],[23593,"\u5de5\u7a0b\/\u8bbe\u5907\u7ecf\u7406","shebeijingli"],[23594,"\u5de5\u7a0b\/\u8bbe\u5907\u5de5\u7a0b\u5e08","shebeigongchengshi"],[23577,"\u5de5\u7a0b\/\u673a\u68b0\u7ed8\u56fe\u5458","jixiehuitu"],[23595,"\u5de5\u4e1a\u5de5\u7a0b\u5e08","gongyegongchengshi"],[23573,"\u6280\u672f\u7814\u53d1\u7ecf\u7406\/\u4e3b\u7ba1","jishuyanfajingli"],[23591,"\u6280\u672f\u7814\u53d1\u5de5\u7a0b\u5e08","jishuyanfa"],[23584,"CNC\u5de5\u7a0b\u5e08","cnc"],[23578,"\u6750\u6599\u5de5\u7a0b\u5e08","cailiaogongchengshi"],[23574,"\u4ea7\u54c1\u5de5\u827a\/\u5236\u7a0b\u5de5\u7a0b\u5e08","chanpingongyi"],[23592,"\u4ea7\u54c1\u89c4\u5212\u5de5\u7a0b\u5e08","chanpinguihua"],[23602,"\u51b2\u538b\u5de5\u7a0b\u5e08\/\u6280\u5e08","chongyajishi"],[23587,"\u8239\u8236\u5de5\u7a0b\u5e08","chuanbo"],[23603,"\u7535\u529b\u5de5\u7a0b\u5e08\/\u6280\u672f\u5458","dianlijishuyuan"],[23729,"\u5149\u4f0f\u7cfb\u7edf\u5de5\u7a0b\u5e08","guangfuxitonggongchengshi"],[23605,"\u8f68\u9053\u4ea4\u901a\u5de5\u7a0b\u5e08\/\u6280\u672f\u5458","guidaojiaotong"],[23585,"\u9505\u7089\u5de5\u7a0b\u5e08\/\u6280\u5e08","guolujishi"],[23583,"\u710a\u63a5\u5de5\u7a0b\u5e08\/\u6280\u5e08","hanjiejishi"],[23580,"\u673a\u7535\u5de5\u7a0b\u5e08","jidiangongchengshi"],[23596,"\u673a\u68b0\u5de5\u7a0b\u5e08","jixiegongchengshi"],[23588,"\u98de\u673a\u7ef4\u4fee\u673a\u68b0\u5e08","feijiweixiu"],[23606,"\u98de\u884c\u5668\u8bbe\u8ba1\u4e0e\u5236\u9020","feixingqi"],[23601,"\u5939\u5177\u5de5\u7a0b\u5e08\/\u6280\u5e08","jiajujishi"],[23579,"\u7ed3\u6784\u5de5\u7a0b\u5e08","jiegougongchengshi"],[23590,"\u77ff\u4ea7\u52d8\u63a2\/\u5730\u8d28\u52d8\u6d4b\u5de5\u7a0b\u5e08","kuangchankantan"],[23597,"\u6a21\u5177\u5de5\u7a0b\u5e08","mojugongchengshi"],[23604,"\u6c7d\u8f66\/\u6469\u6258\u8f66\u5de5\u7a0b\u5e08","qichegongchengshi"],[23607,"\u77f3\u6cb9\u5929\u7136\u6c14\u6280\u672f\u4eba\u5458","shiyoutianranqi"],[23575,"\u5b9e\u9a8c\u5ba4\u8d1f\u8d23\u4eba\/\u5de5\u7a0b\u5e08","shiyanshifuzeren"],[23589,"\u6c34\u5229\/\u6c34\u7535\u5de5\u7a0b\u5e08","shuili"],[23581,"\u7ef4\u4fee\u5de5\u7a0b\u5e08","weixiugongchengshi"],[23598,"\u7ef4\u4fee\u7ecf\u7406\/\u4e3b\u7ba1","weixiujingli"],[23743,"\u9879\u76ee\u7ba1\u7406","xiangmuguanli"],[23600,"\u6ce8\u5851\u5de5\u7a0b\u5e08\/\u6280\u5e08","zhusujishi"],[23582,"\u94f8\u9020\/\u953b\u9020\u5de5\u7a0b\u5e08\/\u6280\u5e08","duanzaojishi"],[23599,"\u88c5\u914d\u5de5\u7a0b\u5e08\/\u6280\u5e08","zhuangpeijishi"],[23586,"\u5149\u6e90\u4e0e\u7167\u660e\u5de5\u7a0b","zhaoming"],[23608,"\u5176\u4ed6","qitagc"]]],[22045,"\u6c7d\u8f66","qiche",[[23609,"\u6c7d\u8f66\u673a\u6784\u5de5\u7a0b\u5e08","qichejigou"],[23610,"\u6c7d\u8f66\u8bbe\u8ba1\u5de5\u7a0b\u5e08","qichesheji"],[23611,"\u6c7d\u8f66\u7535\u5b50\u5de5\u7a0b\u5e08","qichedianzi"],[23612,"\u6c7d\u8f66\u8d28\u91cf\u7ba1\u7406","qichezhiliang"],[23613,"\u6c7d\u8f66\u5b89\u5168\u6027\u80fd\u5de5\u7a0b\u5e08","qicheanquan"],[23614,"\u6c7d\u8f66\u88c5\u914d\u5de5\u827a\u5de5\u7a0b\u5e08","qichezhuangpei"],[23615,"\u6c7d\u8f66\u4fee\u7406\u4eba\u5458","qichexiuli"],[23744,"\u6c7d\u8f66\u9879\u76ee\u7ba1\u7406","qichexiangmuguanli"],[23617,"\u6c7d\u8f66\u9500\u552e\/\u7ecf\u7eaa\u4eba","qichexiaoshou"],[23616,"4S\u5e97\u7ecf\u7406\/\u7ef4\u4fee\u7ad9\u7ecf\u7406","4sdianjingli"],[23745,"\u552e\u540e\u670d\u52a1\/\u5ba2\u6237\u670d\u52a1","shouhoufuwukehufuwu"],[23618,"\u4e8c\u624b\u8f66\u8bc4\u4f30\u5e08","ershouchepinggushi"],[23619,"\u5176\u4ed6","qitaqc"]]],[22046,"\u6280\u5de5","jigong",[[23630,"\u88c1\u7f1d\u5370\u7eba\u71a8\u70eb","caifeng"],[23626,"\u53c9\u8f66\u5de5","chachegong"],[23622,"\u8f66\u5de5\/\u78e8\u5de5\/\u94e3\u5de5\/\u51b2\u538b\u5de5\/\u9523\u5de5","chegong"],[23625,"\u7535\u5de5","diangong"],[23746,"\u7535\u710a\u5de5\/\u94c6\u710a\u5de5","dianhangongmaohangong"],[23620,"\u6280\u5de5","jigong1"],[23627,"\u7a7a\u8c03\u5de5\/\u7535\u68af\u5de5\/\u9505\u7089\u5de5","kongdiaogong"],[23624,"\u6a21\u5177\u5de5","mojugong"],[23629,"\u666e\u5de5\/\u64cd\u4f5c\u5de5","pugong"],[23631,"\u6c7d\u8f66\u4fee\u7406\u5de5","qichexiuligong"],[23621,"\u94b3\u5de5\/\u673a\u4fee\u5de5\/\u94a3\u91d1\u5de5","qiangong"],[23623,"\u5207\u5272\u6280\u5de5","qiegejigong"],[23628,"\u6c34\u5de5\/\u6728\u5de5\/\u6f06\u5de5","shuigong"],[23632,"\u5176\u4ed6","qitajg"]]],[22047,"\u670d\u88c5\/\u7eba\u7ec7\/\u76ae\u9769","fuzhuang",[[23633,"\u670d\u88c5\/\u7eba\u7ec7\u8bbe\u8ba1\u603b\u76d1","fuzhuangshejizongjian"],[23634,"\u670d\u88c5\/\u7eba\u7ec7\u8bbe\u8ba1","fuzhuangsheji"],[23730,"\u670d\u88c5\/\u7eba\u7ec7\/\u76ae\u9769\u5de5\u827a\u5e08","fuzhuanggongchengshi"],[23635,"\u9762\u6599\u8f85\u6599\u5f00\u53d1","mianliaokaifa"],[23636,"\u9762\u6599\u8f85\u6599\u91c7\u8d2d","mianliaocaigou"],[23637,"\u670d\u88c5\/\u7eba\u7ec7\/\u76ae\u9769\u8ddf\u5355","pigegendan"],[23638,"\u8d28\u91cf\u7ba1\u7406\/\u9a8c\u8d27\u5458(QA\/QC)","yanhuoyuan"],[23639,"\u677f\u623f\/\u6966\u5934\/\u5e95\u683c\u51fa\u683c\u5e08","banfang"],[23640,"\u6253\u6837\/\u5236\u7248","dayang"],[23641,"\u7535\u8111\u653e\u7801\u5458","fangmayuan"],[23642,"\u7eb8\u6837\u5e08\/\u8f66\u677f\u5de5","zhiyangshi"],[23643,"\u88c1\u5e8a","caichuang"],[23644,"\u5176\u4ed6","qitafz"]]],[22048,"\u91c7\u8d2d","caigou",[[23645,"\u91c7\u8d2d\u603b\u76d1","caigouzongjian"],[23646,"\u91c7\u8d2d\u7ecf\u7406","caigoujingli"],[23647,"\u91c7\u8d2d\u4e3b\u7ba1","caigouzhuguan"],[23648,"\u91c7\u8d2d\u5458","caigouyuan"],[23649,"\u91c7\u8d2d\u52a9\u7406","caigouzhuli"],[23650,"\u4e70\u624b","maishou"],[23651,"\u4f9b\u5e94\u5546\u5f00\u53d1","gongyingshangkaifa"],[23652,"\u5176\u4ed6","qitacg"]]],[22049,"\u8d38\u6613","maoyi",[[23653,"\u8d38\u6613\/\u5916\u8d38\u7ecf\u7406\/\u4e3b\u7ba1","maoyiwaimaojinglizhuguan"],[23654,"\u8d38\u6613\/\u5916\u8d38\u4e13\u5458\/\u52a9\u7406","maoyiwaimaozhuanyuanzhuli"],[23655,"\u56fd\u5185\u8d38\u6613\u4eba\u5458","guoneimaoyi"],[23656,"\u4e1a\u52a1\u8ddf\u5355\u7ecf\u7406","yewugendanjingli"],[23657,"\u9ad8\u7ea7\u4e1a\u52a1\u8ddf\u5355","gaojiyewugendan"],[23658,"\u4e1a\u52a1\u8ddf\u5355","yewugendan"],[23659,"\u52a9\u7406\u4e1a\u52a1\u8ddf\u5355","zhuliyewu"],[23660,"\u5176\u4ed6","qitamy"]]],[22050,"\u7269\u6d41\/\u4ed3\u50a8","wuliu",[[23661,"\u7269\u6d41\u603b\u76d1","wuliuzongjian"],[23672,"\u7269\u6d41\u7ecf\u7406","wuliujingli"],[23662,"\u7269\u6d41\u4e3b\u7ba1","wuliuzhuguan"],[23673,"\u7269\u6d41\u4e13\u5458\/\u52a9\u7406","wuliuzhuli"],[23663,"\u4f9b\u5e94\u94fe\u603b\u76d1","gongyinglianzongjian"],[23674,"\u4f9b\u5e94\u94fe\u7ecf\u7406","gongyinglianjingli"],[23664,"\u4f9b\u5e94\u94fe\u4e3b\u7ba1\/\u4e13\u5458","gongyinglianzhuguan"],[23675,"\u7269\u6599\u7ecf\u7406","wuliaojingli"],[23665,"\u7269\u6599\u4e3b\u7ba1\/\u4e13\u5458","wuliaozhuguan"],[23676,"\u4ed3\u5e93\u7ecf\u7406\/\u4e3b\u7ba1","cangkujingli"],[23666,"\u4ed3\u5e93\u7ba1\u7406\u5458","guanliyuan"],[23677,"\u8fd0\u8f93\u7ecf\u7406\/\u4e3b\u7ba1","yunshujingli"],[23667,"\u9879\u76ee\u7ecf\u7406\/\u4e3b\u7ba1","xiangmujingli"],[23678,"\u8d27\u8fd0\u4ee3\u7406","huoyundaili"],[23668,"\u96c6\u88c5\u7bb1\u4e1a\u52a1","jizhuangxiang"],[23679,"\u6d77\u5173\u4e8b\u52a1\u7ba1\u7406","haiguanshiwu"],[23669,"\u62a5\u5173\u4e0e\u62a5\u68c0","baoguanyuan"],[23680,"\u5355\u8bc1\u5458","danzhengyuan"],[23670,"\u8239\u52a1\/\u7a7a\u8fd0\u9646\u8fd0\u64cd\u4f5c","chuanwu"],[23681,"\u5feb\u9012\u5458","kuaidiyuan"],[23671,"\u8c03\u5ea6\u5458","diaoduyuan"],[23682,"\u7406\u8d27\u5458","lihuoyuan"],[23683,"\u5176\u4ed6","qitawl"]]],[22051,"\u516c\u52a1\u5458","gongwuyuan",[[23684,"\u516c\u52a1\u5458","gongwuyuan1"]]],[22052,"\u7ffb\u8bd1","fanyi",[[23685,"\u82f1\u8bed\u7ffb\u8bd1","yingyufanyi"],[23686,"\u65e5\u8bed\u7ffb\u8bd1","riyufanyi"],[23687,"\u5fb7\u8bed\u7ffb\u8bd1","deyufanyi"],[23688,"\u6cd5\u8bed\u7ffb\u8bd1","fayufanyi"],[23689,"\u4fc4\u8bed\u7ffb\u8bd1","eyufanyi"],[23691,"\u897f\u73ed\u7259\u8bed\u7ffb\u8bd1","xibanyayufanyi"],[23694,"\u97e9\u8bed\/\u671d\u9c9c\u8bed\u7ffb\u8bd1","hanyufanyi"],[23693,"\u963f\u62c9\u4f2f\u8bed\u7ffb\u8bd1","alaboyufanyi"],[23690,"\u610f\u5927\u5229\u8bed\u7ffb\u8bd1","yidaliyufanyi"],[23692,"\u8461\u8404\u7259\u8bed\u7ffb\u8bd1","putaoyayufanyi"],[23695,"\u6cf0\u8bed\u7ffb\u8bd1","taiyufanyi"],[23696,"\u4e2d\u56fd\u65b9\u8a00\u7ffb\u8bd1","fangyanfanyi"],[23697,"\u5176\u4ed6\u8bed\u79cd\u7ffb\u8bd1","qitayz"]]],[22053,"\u5728\u6821\u5b66\u751f","zaixiaoxuesheng",[[23700,"\u4e2d\u4e13\/\u804c\u6821\u751f","zhongzhuan"],[23699,"\u5927\u5b66\/\u5927\u4e13\u5e94\u5c4a\u6bd5\u4e1a\u751f","daxue"],[23701,"\u5176\u4ed6","qitazxs"],[23698,"\u7814\u7a76\u751f","yanjiusheng"]]],[22054,"\u50a8\u5907\u5e72\u90e8\/\u57f9\u8bad\u751f\/\u5b9e\u4e60\u751f","chubeiganbu",[[23703,"\u57f9\u8bad\u751f","peixunsheng"],[23702,"\u50a8\u5907\u5e72\u90e8","chubeiganbu1"],[23704,"\u5b9e\u4e60\u751f","shixisheng"]]],[22055,"\u517c\u804c","jianzhi",[[23705,"\u517c\u804c","jianzhi1"]]],[22057,"\u73af\u4fdd","huanbaolei",[[23709,"\u73af\u4fdd\u5de5\u7a0b\u5e08","huanbaogongchengshi"],[23713,"\u6c34\u5904\u7406\u5de5\u7a0b\u5e08","shuichuligongchengshi"],[23712,"\u6c34\u8d28\u68c0\u6d4b\u5458","shuizhijiance"],[23710,"\u73af\u5883\u5f71\u54cd\u8bc4\u4ef7\u5de5\u7a0b\u5e08","huanjingyingxiang"],[23711,"\u73af\u4fdd\u68c0\u6d4b","huanbaojiance"],[23714,"\u56fa\u5e9f\u5de5\u7a0b\u5e08","gufei"],[23756,"\u5e9f\u6c14\u5904\u7406\u5de5\u7a0b\u5e08","feiqichuligongchengshi"],[23715,"\u5176\u4ed6","qitahb"]]],[22058,"\u519c\/\u6797\/\u7267\/\u6e14","muyu",[[23716,"\u517b\u6b96\u90e8\u4e3b\u7ba1","yangzhizhuguan"],[23717,"\u573a\u957f(\u519c\/\u6797\/\u7267\/\u6e14\u4e1a)","changchang"],[23718,"\u519c\u827a\u5e08","nongyishi"],[23719,"\u755c\u7267\u5e08","xumushi"],[23720,"\u9972\u517b\u5458","siyangyuan"],[23721,"\u52a8\u7269\u8425\u517b\/\u9972\u6599\u7814\u53d1","dongwuyingyang"],[23722,"\u5176\u4ed6","qitanl"]]],[22056,"\u5176\u4ed6","qitalei",[[23708,"\u5176\u4ed6","qitaqt"],[23706,"\u9a6f\u517d\u5e08\/\u52a9\u7406\u9a6f\u517d\u5e08","xunshoushi"],[23707,"\u5fd7\u613f\u8005\/\u793e\u4f1a\u5de5\u4f5c\u8005","zhiyuanzhe"]]]],
    'experience':[[1,"\u5728\u8bfb\u5b66\u751f"],[2,"\u5e94\u5c4a\u6bd5\u4e1a\u751f"],[3,"1\u5e74\u4ee5\u4e0a"],[4,"2\u5e74\u4ee5\u4e0a"],[5,"3\u5e74\u4ee5\u4e0a"],[6,"5\u5e74\u4ee5\u4e0a"],[7,"8\u5e74\u4ee5\u4e0a"],[8,"10\u5e74\u4ee5\u4e0a"]],
    'company_type':[[42001,"\u5916\u8d44(\u6b27\u7f8e)"],[42002,"\u5916\u8d44(\u975e\u6b27\u7f8e)"],[42003,"\u5408\u8d44(\u6b27\u7f8e)"],[42004,"\u5408\u8d44(\u975e\u6b27\u7f8e)"],[42005,"\u56fd\u4f01"],[42006,"\u6c11\u8425\u516c\u53f8"],[42007,"\u5916\u4f01\u4ee3\u8868\u5904"],[42008,"\u653f\u5e9c\u673a\u5173"],[42009,"\u4e8b\u4e1a\u5355\u4f4d"],[42010,"\u975e\u76c8\u5229\u673a\u6784"],[42011,"\u5176\u5b83\u6027\u8d28"]],
    'month_salary':[[10,"\u9762\u8bae"],[1,"2000\u4ee5\u4e0b"],[2,"2000-3000"],[3,"3000-5000"],[4,"5000-7000"],[5,"7000-10000"],[6,"10000-15000"],[7,"15000-20000"],[8,"20000-30000"],[9,"30000\u4ee5\u4e0a"]],
    'identity':[[1,"\u730e\u5934"],[2,"HR"],[3,"\u9ad8\u7ba1"]],
    'readytime':[[0,"\u968f\u65f6"],[1,"\u4e00\u5468\u5185"],[2,"\u4e24\u5468\u5185"],[3,"\u4e00\u4e2a\u6708\u5185"],[4,"\u4e09\u4e2a\u6708\u5185"]],
    'contact_visi':[[1,"\u5bf9\u5173\u6ce8\u7684\u4f2f\u4e50\u516c\u5f00"],[2,"\u5b8c\u5168\u4fdd\u5bc6"]],
    'job_status_visi':[[0,"\u5bf9\u6240\u6709\u4f2f\u4e50\u516c\u5f00"],[1,"\u5bf9\u5173\u6ce8\u7684\u4f2f\u4e50\u516c\u5f00"],[2,"\u5b8c\u5168\u4fdd\u5bc6"]],
    'degree':[[2360,"\u521d\u4e2d"],[2359,"\u9ad8\u4e2d"],[2358,"\u4e2d\u6280"],[2357,"\u4e2d\u4e13"],[2356,"\u5927\u4e13"],[2355,"\u672c\u79d1"],[2354,"\u7855\u58eb"],[2353,"MBA"],[2352,"\u535a\u58eb"]],
    'relation':[[1,"\u6211\u5f53\u524d\u5728\u804c"],[2,"\u6211\u5df2\u79bb\u804c"],[3,"\u6211\u662f\u9762\u8bd5\u8005"],[4,"\u6211\u4eec\u6709\u8fc7\u5546\u52a1\u5408\u4f5c\u8005"]],
    'iExpertStatus':[[1,"\u6b63\u5728\u62db\u8058\u4e2d"],[2,"\u6709\u597d\u7684\u4eba\u624d\u53ef\u4ee5\u8003\u8651"],[3,"\u6682\u4e0d\u62db\u8058"]],
    'work_years':[[1,"1\u5e74\u4ee5\u5185"],[2,"1\u5e74\u4ee5\u4e0a"],[3,"2\u5e74\u4ee5\u4e0a"],[4,"3\u5e74\u4ee5\u4e0a"],[5,"4\u5e74\u4ee5\u4e0a"],[6,"5\u5e74\u4ee5\u4e0a"],[7,"8\u5e74\u4ee5\u4e0a"],[8,"10\u5e74\u4ee5\u4e0a"]],
    'work_years_new':[[0,"\u5728\u8bfb\u5b66\u751f"],[1,"\u5e94\u5c4a\u751f"],[2014,2014],[2013,2013],[2012,2012],[2011,2011],[2010,2010],[2009,2009],[2008,2008],[2007,2007],[2006,2006],[2005,2005],[2004,2004],[2003,2003],[2002,2002],[2001,2001],[2000,2000],[1999,1999],[1998,1998],[1997,1997],[1996,1996],[1995,1995],[1994,1994],[1993,1993],[1992,1992],[1991,1991],[1990,1990],[1989,1989],[1988,1988],[1987,1987],[1986,1986],[1985,1985],[1984,1984],[1983,1983],[1982,1982],[1981,1981],[1980,1980],[1979,1979],[1978,1978],[1977,1977],[1976,1976],[1975,1975],[1974,1974],[1973,1973],[1972,1972],[1971,1971],[1970,1970],[1969,1969],[1968,1968],[1967,1967],[1966,1966],[1965,1965],[1964,1964],[1963,1963],[1962,1962],[1961,1961],[1960,1960],[1959,1959],[1958,1958],[1957,1957],[1956,1956],[1955,1955],[1954,1954],[1953,1953],[1952,1952],[1951,1951],[1950,1950]],
    'intention':[[1,"\u6b63\u5728\u627e\u5de5\u4f5c"],[2,"\u6709\u597d\u7684\u673a\u4f1a\u6211\u4f1a\u8003\u8651"],[3,"\u6682\u65f6\u4e0d\u60f3\u627e\u5de5\u4f5c"]],
    'get':function(fieldName, id){
    	 /* $.ajax({
         	type:"post",  
            url:"http://localhost:8080/jeewx/selectInfoController.do?selectInfoJson",  
            dataType : "json",
            async: false,
             success:function(data){  
            	 WealinkMobile.dict[fieldName]=data.obj;  
             }  
         }); */
        var data = [];
        var dict = dictJson;
        
        if(fieldName in dict && dict[fieldName].length > 0){
            if(!id) return dict[fieldName];

            for(var i= 0; i<dict[fieldName].length; i++){
                if(id == dict[fieldName][i][0]){
                    data = dict[fieldName][i];
                    break;
                }
            }
        }
        return data;
    },

    'getSub': function (fieldName, id) {
        return WealinkMobile.dict.get(fieldName, id)[3];
    }
};

/**
 * 筛选器；
 * @type {{doubleLevel: 'doubleLevel', singleLevel: 'singleLevel', loadSub: 'loadSub'}}
 */
WealinkMobile.filter = {
    /**
     * 两层的选择；
     *
     * @param options
     */
    'double': function (options) {
        options.selected = Number($("input[name=" + options.fieldName + "]").val());
        options.selected = isNaN(options.selected) ? 0 : options.selected;
        options.selected = options.selected || 0;

        var DICT = WealinkMobile.dict;
        var mainData = DICT.get(options.fieldName, null);
        var mainHtml = "<li data-wlm-filter-selectable='true' data-wlm-filter-value='0' data-wlm-filter-item='" + options.fieldName + "-main-0'>不限</li>";
        var mainSelected= 0, subSelected = 0;

        if(options.trigger.jqmData('wlm-filter-required')) mainHtml = '';

        options.filter
            .css({width:$(window).width()*0.8})
            .html('<ul class="slip1"></ul><ul class="slip2"></ul>'); // 两级菜单的容器

        for (var i = 0; i < mainData.length; i++) {
            if(options.selected == mainData[i][0])
                mainSelected = mainData[i][0];

            if (mainSelected == 0 && options.selected > 0 && mainData[i][3].length > 0) {
                for (var j = 0; j < mainData[i][3].length; j++) {
                    if (options.selected == mainData[i][3][j][0]){
                        mainSelected = mainData[i][0];
                        subSelected  = options.selected;
                    }
                }
            }
            options.mainSelected = mainSelected;
            mainHtml += "<li "+(mainData[i][3].length==0?'data-wlm-filter-selectable=\"true\"':'')+" data-wlm-filter-value='" + mainData[i][0] + "' data-wlm-filter-item='" + options.fieldName + "-main-" + mainData[i][0] + "'>" + mainData[i][1] + "</li>";
        }

        // 第一层事件绑定；
        options.filter
            .find('.slip1').html(mainHtml)
            .find('li').on('click', function () {
                options.parent_id = $(this).jqmData('wlm-filter-value');
                options.parent_label = $(this).text();
                options.loadSub = true;
                WealinkMobile.filter.select(options, $(this));
            });

        var selectedItem = options.filter.find('li[data-wlm-filter-item='+options.fieldName+'-main-'+mainSelected+']');
        selectedItem.addClass('current');

        if(mainSelected) selectedItem.click();

        WealinkMobile.filter.show(options);
    },
    
    
    /**
     * 两层的选择；
     *
     * @param options
     */
    'double_multiple': function (options) {
        options.selected = $("input[name=" + options.fieldName + "]").val().split(",");

        var DICT = WealinkMobile.dict;
        var mainData = DICT.get(options.fieldName, null);
        var mainHtml = "<li data-wlm-filter-selectable='true' data-wlm-filter-value='0' data-wlm-filter-item='" + options.fieldName + "-main-0'>不限</li>";
        var mainSelected= 0, subSelected = 0;

        if(options.trigger.jqmData('wlm-filter-required'))
            mainHtml = "";
        options.filter
            .css({width:$(window).width()*0.75})
            .html('<ul class="slip1"></ul><ul class="slipSquare"></ul>'); // 两级菜单的容器

        for (var i = 0; i < mainData.length; i++) {
        	if(mainSelected == 0){
	        	for(var k=0; k<options.selected.length;k++){
		            if(options.selected[k] == mainData[i][0])
		                mainSelected = mainData[i][0];
				}
        	}
			

            if (mainSelected == 0 && options.selected.length > 0 && mainData[i][3].length > 0) {
                for (var j = 0; j < mainData[i][3].length; j++) {
                	if(mainSelected == 0){
	                	for(var l=0; l<options.selected.length;l++){
		                    if (options.selected[l] == mainData[i][3][j][0]){
		                        mainSelected = mainData[i][0];
		                    }
	                	}
                	}
                }
            }
            mainHtml += "<li data-wlm-filter-value='" + mainData[i][0] + "' data-wlm-filter-item='" + options.fieldName + "-main-" + mainData[i][0] + "'>" + mainData[i][1] + "</li>";
        }
        // 第一层事件绑定；
        options.filter
            .find('.slip1').html(mainHtml)
            .find('li').on('click', function () {
                options.parent_id = $(this).jqmData('wlm-filter-value');
                options.parent_label = $(this).text();
                options.loadSub_multiple = true;
                WealinkMobile.filter.select(options, $(this));
            });

        var selectedItem = options.filter.find('li[data-wlm-filter-item='+options.fieldName+'-main-'+mainSelected+']');
        selectedItem.addClass('current');
        if(mainSelected) selectedItem.click();

        WealinkMobile.filter.show(options);
    },
    
    /**
     * 两层的选择；
     *
     * @param options
     */
    'double_for_time': function (options) {
        options.selected = $("input[name=" + options.fieldName + "]").val().split("-");
        var mainHtml = "<li id='zhijinLi'>至今</li>";
        var mainSelected= 'y';

        options.filter
            .css({width:$(window).width()*0.75})
            .html('<ul class="slip1"></ul><ul class="slipRound"></ul>'); // 两级菜单的容器

        if(options.year)
        	mainHtml += "<li data-wlm-filter-value='y' data-wlm-filter-item='" + options.fieldName + "-main-y'>选择年</li>";
        if(options.month)
        	mainHtml += "<li data-wlm-filter-value='m' data-wlm-filter-item='" + options.fieldName + "-main-m'>选择月</li>";
        if(options.day)
        	mainHtml += "<li data-wlm-filter-value='d' data-wlm-filter-item='" + options.fieldName + "-main-d'>选择日</li>";

        // 第一层事件绑定；
        options.filter
            .find('.slip1').html(mainHtml)
            .find('li').on('click', function () {
                options.parent_id = $(this).jqmData('wlm-filter-value');
                options.loadSub_for_time = true;
                WealinkMobile.filter.select(options, $(this));
            });
        $('#zhijinLi').on('click', function () {
		        var val = ''
        		if(options.year)
		        	val += "0000";
		        if(options.month)
		        	val += "-00";
		        if(options.day)
		        	val += "-00";
		    	$('input[name=' + options.fieldName + ']').val(val);
		    	$('#label-' + options.fieldName).text('至今');
		        return true;
            });
        var selectedItem = options.filter.find('li[data-wlm-filter-item='+options.fieldName+'-main-'+mainSelected+']');
        selectedItem.addClass('current');

        if(mainSelected) selectedItem.click();

        WealinkMobile.filter.show(options);
    },
    
    'loadSub_for_time': function (options) {
        var subHtml = "";
        if (options.parent_id) {
        	subSelected_y = 0;
        	subSelected_m = 0;
        	subSelected_d = 0;
			var mydate = new Date();
            if(options.parent_id == 'y'){
                if (options.selected[0]){
                    subSelected_y = options.selected[0];	
                }
               for(var y=1950;y<=mydate.getFullYear();y++){
		           if (subSelected_y == y)
	            		subHtml += "<li data-wlm-filter-selectable='true' data-wlm-filter-type='y' data-wlm-filter-value='" + y + "' data-wlm-filter-item='" + options.fieldName + "-sub-y'><span class='current'></span>" + y + "</li>";
		           else
	                	subHtml += "<li data-wlm-filter-selectable='true' data-wlm-filter-type='y' data-wlm-filter-value='" + y + "' data-wlm-filter-item='" + options.fieldName + "-sub-y'><span></span>" + y + "</li>";
	        	}
            }else if(options.parent_id == 'm'){
                if (options.selected[1]){
                    subSelected_m = options.selected[1];	
                }
               for(var m=1;m<=12;m++){
               	   var show = m;
               	   if(m < 10)
               	      show = '0'+m;
		           if (subSelected_m == show)
	            		subHtml += "<li data-wlm-filter-selectable='true' data-wlm-filter-type='m' data-wlm-filter-value='" + show + "' data-wlm-filter-item='" + options.fieldName + "-sub-m'><span class='current'></span>" + show + "</li>";
		           else
	                	subHtml += "<li data-wlm-filter-selectable='true' data-wlm-filter-type='m' data-wlm-filter-value='" + show + "' data-wlm-filter-item='" + options.fieldName + "-sub-m'><span></span>" + show + "</li>";
	        	}
        	}else if(options.parent_id == 'd'){
                if (options.selected[2]){
                    subSelected_d = options.selected[2];	
                }
               for(var d=1;d<=31;d++){
               	   var show = d;
               	   if(d < 10)
               	      show = '0'+d;
		           if (subSelected_d == show)
	            		subHtml += "<li data-wlm-filter-selectable='true' data-wlm-filter-type='d' data-wlm-filter-value='" + show + "' data-wlm-filter-item='" + options.fieldName + "-sub-m'><span class='current'></span>" + show + "</li>";
		           else
	                	subHtml += "<li data-wlm-filter-selectable='true' data-wlm-filter-type='d' data-wlm-filter-value='" + show + "' data-wlm-filter-item='" + options.fieldName + "-sub-m'><span></span>" + show + "</li>";
	        	}
        	}
        }
		options.filter.find('.slipRound').html(subHtml).find('li').on('click', function () {
                WealinkMobile.filter.select_for_time(options, $(this));
            });
        if($('.slipBtn').html() == undefined){
	        var buttonHtml = '<div class="slipBtn"><input type="button" id="reset_multiple" value="清空" data-role="none" class="leftBtn"><input type="button" id="submit_multiple" value="确认" data-role="none" class="rightBtn"></div>'
	        $(options.filter).append(buttonHtml);
        }
        $('#reset_multiple').on('click', function () {
		    	$('.slipRound').find('span').removeClass('current');
		    	$('input[name=' + options.fieldName + ']').val('');
		    	$('#label-' + options.fieldName).text('');
            });
        $('#submit_multiple').on('click', function () {
		    	WealinkMobile.filter.hide(options);
            });
            
        $(document).scrollTo(0, 800, {queue: true});
    },
    
    'loadSub_multiple': function (options) {
        var subData = WealinkMobile.dict.getSub(options.fieldName, options.parent_id);
        var subHtml = "";
        var subSelected = 0;
        var parentFixed = false;
        if(subData.length == 0){
            subData = [];
            parentFixed = true;
            subData.push(WealinkMobile.dict.get(options.fieldName, options.parent_id));
        }

        options.selected = $("input[name=" + options.fieldName + "]").val().split(",");
        if (options.parent_id > 0 && subData.length > 0) {
            if(options.trigger.jqmData('wlm-filter-selectparent') && !parentFixed){
                subHtml += "<li data-wlm-filter-selectable='true' data-wlm-filter-parent_label='"
                    + options.parent_label + "' data-wlm-filter-parent_id='" + options.parent_id
                    + "' data-wlm-filter-value='" + (options.parent_id) + "' data-wlm-filter-item='"
                    + options.fieldName + "-sub-0'><span"+($.inArray(options.parent_id+'', options.selected)>=0?" class='current'":"")+"></span><i>"+options.parent_label+"</i></li>";
            }
            for (var i = 0; i < subData.length; i++) {
            	subSelected = 0;
            	for(var m=0; m<options.selected.length;m++){
	                if (subData[i][0] == options.selected[m]){
	                    subSelected = options.selected[m];	
	                }
            	}

	           if (subSelected)
            		subHtml += "<li data-wlm-filter-selectable='true' data-wlm-filter-value='" + subData[i][0] + "' data-wlm-filter-item='" + options.fieldName + "-sub-" + subData[i][0] + "'><span class='current'></span><i>" + subData[i][1] + "</i></li>";
	           else
                	subHtml += "<li data-wlm-filter-selectable='true' data-wlm-filter-value='" + subData[i][0] + "' data-wlm-filter-item='" + options.fieldName + "-sub-" + subData[i][0] + "'><span></span><i>" + subData[i][1] + "</i></li>";
        	}
        }
		options.filter.find('.slipSquare').html(subHtml).find('li').on('click', function () {
                WealinkMobile.filter.select_multiple(options, $(this));
            });
        if($('.slipBtn').html() == undefined){
	        var buttonHtml = '<div class="slipBtn"><input type="button" id="reset_multiple" value="清空" data-role="none" class="leftBtn"><input type="button" id="submit_multiple" value="确认" data-role="none" class="rightBtn"></div>'
	        $(options.filter).append(buttonHtml);
        }
        $('#reset_multiple').on('click', function () {
		    	$('.slipSquare').find('span').removeClass('current');
		    	$('input[name=' + options.fieldName + ']').val('');
		    	$('#label-' + options.fieldName).text('');
            });
        $('#submit_multiple').on('click', function () {
		    	WealinkMobile.filter.hide(options);
            });

        WealinkMobile.filter.sizeFix(options);
        if($(document).scrollTop() > options.filter.position().top)
            $(document).scrollTo(options.filter.position().top, 800, {axis:'y',queue: true});

    },
    'select_for_time': function (options, selectedObj) {
    	selectedObj.siblings().find('span').removeClass('current');
        selectedObj.find('span').addClass('current');
        
        var org_itemvalue = $('input[name=' + options.fieldName + ']').val().split("-");
        var itemValue = selectedObj.jqmData('wlm-filter-value');
        var itemType = selectedObj.jqmData('wlm-filter-type');
        if(itemType == 'y'){
        	var val = itemValue;
        	for(var i=1;i<org_itemvalue.length;i++){
				if(i==1){
					if(org_itemvalue[i] == '00')
						org_itemvalue[i] = '01';
					val += '-'+org_itemvalue[i];
				}else if(i==2){
					if(org_itemvalue[i] == '00')
						org_itemvalue[i] = '01';
					val += '-'+org_itemvalue[i];
				}
        	}
        }else if(itemType == 'm'){
        	var val = '';
        	for(var i=0;i<org_itemvalue.length;i++){
				if(i==0){
					if(org_itemvalue[i] == '' || org_itemvalue[i] == '0000'){
			        	alert("请先选择年");
			        	return false;
					}
					val += org_itemvalue[i]+'-'+itemValue;
				}else if(i==2){
					if(org_itemvalue[i] == '00')
						org_itemvalue[i] = '01';
					val += '-'+org_itemvalue[i];
				}
        	}
        }else if(itemType == 'd'){
        	var val = '';
        	for(var i=0;i<org_itemvalue.length;i++){
				if(i==0){
					if(org_itemvalue[i] == '' || org_itemvalue[i] == '0000'){
			        	alert("请先选择年");
			        	return false;
					}
					val += org_itemvalue[i];
				}else if(i==1){
					if(org_itemvalue[i] == '' || org_itemvalue[i] == '00'){
			        	alert("请先选择月");
			        	return false;
					}
					val += '-'+org_itemvalue[i]+'-'+itemValue;
				}
        	}
        }
    	$('input[name=' + options.fieldName + ']').val(val);
    	$('#label-' + options.fieldName).text(val);
        return true;
    },
    'select_multiple': function (options, selectedObj) {
        var org_itemvalue = $('input[name=' + options.fieldName + ']').val();
        if(selectedObj.find('span').attr('class') == 'current'){
	        var org_itemlabel = $('#label-' + options.fieldName).text();
	        
	        var itemValue = selectedObj.jqmData('wlm-filter-value');
            var itemLabel = selectedObj.text();

        	var val = org_itemvalue.replace(itemValue+',','');
        	val = val.replace(','+itemValue,'');
            val = val.replace(itemValue,'');
        	var lable = org_itemlabel.replace(itemLabel+',','');
        	lable = lable.replace(','+itemLabel,'');
            lable = lable.replace(itemLabel,'');

        	$('input[name=' + options.fieldName + ']').val(val);
        	$('#label-' + options.fieldName).text(lable);
        	selectedObj.find('span').removeClass('current');
        	return false;
        }
        if(org_itemvalue.split(',').length >=options.num){
        	alert('最多可以选'+options.num+"项");
        	return false;
        }
        selectedObj.find('span').addClass('current');
        var org_itemlabel = $('#label-' + options.fieldName).text();
        var itemValue = selectedObj.jqmData('wlm-filter-value');
        var itemLabel = selectedObj.text();
		if(org_itemvalue == ''||org_itemvalue==0){
        	$('input[name=' + options.fieldName + ']').val(itemValue);
        	$('#label-' + options.fieldName).text(itemLabel);
		}else{
        	$('input[name=' + options.fieldName + ']').val(org_itemvalue+','+itemValue);
        	$('#label-' + options.fieldName).text(org_itemlabel+','+itemLabel);
		}
        return true;
    },

    /**
     * 单层的选择；
     *
     * @param options
     */
    'single': function (options) {
        options.selected = Number($("input[name=" + options.fieldName + "]").val());
        options.selected = isNaN(options.selected) ? 0 : options.selected;
        options.selected = options.selected || 0;

        var DICT = WealinkMobile.dict;
        var mainData = DICT.get(options.fieldName, null);
        var mainHtml = '';

        if(options.dict) mainData = options.dict;

        if(options.showbuxian != false){
	        mainHtml += "<li data-wlm-filter-selectable='true' data-wlm-filter-value='0' data-wlm-filter-item='" + options.fieldName + "-main-0'>不限</li>";
        }
        var mainSelected= 0;

        options.filter.html("<ul class='slip3'></ul>");
        for(var i=0; i< mainData.length; i++){
            if(options.selected == mainData[i][0])
                mainSelected = options.selected;

            mainHtml += "<li data-wlm-filter-selectable='true' data-wlm-filter-value='" + mainData[i][0] + "' data-wlm-filter-item='" + options.fieldName + "-main-" + mainData[i][0] + "'>" + mainData[i][1] + "</li>";
        }

        options.filter
            .find('.slip3').html(mainHtml)
            .find('li').on('click', function () {
                WealinkMobile.filter.select(options, $(this));
            }).end()
            .find('li[data-wlm-filter-item='+options.fieldName+'-main-'+mainSelected+']').addClass('current');

        WealinkMobile.filter.show(options);
    },

    /**
     * 加载下一级选项；
     * @param options
     */
    'loadSub': function (options) {
        var subData = WealinkMobile.dict.getSub(options.fieldName, options.parent_id);
        var subHtml = "";
        var subSelected = 0;
        options.selected = options.selected || 0;

        if (options.parent_id != '' && subData.length > 0) {
            var selectparent = options.trigger.jqmData('wlm-selectparent');
			
            if(false!==selectparent)
                subHtml += "<li data-wlm-filter-selectable='true' data-wlm-filter-parent_label='" + options.parent_label + "' data-wlm-filter-parent_id='" + options.parent_id + "' data-wlm-filter-value='" + (options.parent_id) + "' data-wlm-filter-item='" + options.fieldName + "-sub-0'>"+options.parent_label+"</li>";
            for (var i = 0; i < subData.length; i++) {
                if (subData[i][0] == options.selected)
                    subSelected = options.selected;
                subHtml += "<li data-wlm-filter-selectable='true' data-wlm-filter-value='" + subData[i][0] + "' data-wlm-filter-item='" + options.fieldName + "-sub-" + subData[i][0] + "'>" + subData[i][1] + "</li>";
            }
        }

        options.filter.find('.slip2').html(subHtml)
            .find('li[data-wlm-filter-item=' + options.fieldName + '-sub-' + subSelected + ']').addClass(options.mainSelected==options.parent_id||subSelected!=0?'current':'')
            .end().find('li').on('click', function () {
                WealinkMobile.filter.select(options, $(this));
            });//.last().css({'border-bottom':'none'});

        WealinkMobile.filter.sizeFix(options);
        if($(document).scrollTop() > options.filter.position().top)
            $(document).scrollTo(options.filter.position().top, 800, {axis:'y',queue: true});
    },

    /**
     * 选中；
     *
     * @param options
     * @param selectedObj
     */
    'select': function (options, selectedObj) {
        selectedObj.siblings().removeClass('current').end().addClass('current');
        var itemValue = selectedObj.jqmData('wlm-filter-value');
        var itemLabel = selectedObj.text();

        if (selectedObj.jqmData('wlm-filter-selectable')) {
            WealinkMobile.filter.hide(options);
            var parentName = selectedObj.jqmData('wlm-filter-parent_label');
            if (parentName) 
            {
            	itemLabel = parentName;
            	$('input[id=' + options.parentFieldName + ']').val(itemValue);
            	$('input[id=' + options.fieldName + ']').val('');
            }
            else
            {
            	$('input[id=' + options.parentFieldName + ']').val('');
            	$('input[id=' + options.fieldName + ']').val(itemValue);
            }

            $('#label-' + options.fieldName).text(itemLabel);
            

            return true;
        }

        if(options.loadSub){
            WealinkMobile.filter.loadSub(options);
        }
        if(options.loadSub_multiple){
            WealinkMobile.filter.loadSub_multiple(options);
        }
        if(options.loadSub_for_time){
            WealinkMobile.filter.loadSub_for_time(options);
        }
    },

    /**
     * 显示菜单；
     *
     * @param options
     */
    'show':function(options){
        var maskObj = $("#wlm-filter-mask");
        var headerHeight = $('div[data-role=header]').height();
        var scrollTop = $(document).scrollTop();
        var filterTop = Math.max(headerHeight, scrollTop);

        maskObj.unbind().click(function(){
            WealinkMobile.filter.hide(options);
        });

        WealinkMobile.filter.sizeFix(options);
        options.filter.css({left:$(window).width(), top:filterTop}).show(function(){
            maskObj.css({
                'background': '#000',
                'width': $(window).width(),
                "opacity": 0,
                'left': 0
            }).show().animate({"opacity": 0.5}, 200, null, function () {
                options.filter.animate({left: $(window).width()*0.25}, 300).show();
            });
        });
    },

    /**
     * 收起菜单；
     * @param options
     */
    'hide':function(options){
        var maskObj = $("#wlm-filter-mask");
        options.filter.animate({left: $(window).width()}, 300,null, function(){
            maskObj.animate({"opacity": 0}, 200, null, function(){
                $(this).hide();
                $(options.filter).hide();
            });
        });
    },

    /**
     * 菜单高度、大小调整；
     * @param options
     */
    'sizeFix':function(options){
        var maskObj = $("#wlm-filter-mask");
        var headerHeight = $('div[data-role=header]').height();
        var docHeight = $(document).height();
        var maskHeight= docHeight - headerHeight;
        var filterHeight = Math.max.apply(null, options.filter.find('.slip1, .slip2, .slip3, .slipSquare, .slipRound').map(function () {
            var items = $(this).find('li');
            return items.eq(0).outerHeight(true) * items.length;
        }).get());

        if(filterHeight < maskHeight){
                filterHeight = maskHeight;
        }

        if(filterHeight > maskHeight)
            maskHeight = filterHeight;

        if($('.slipBtn').length) maskHeight +=60; // 清除、确定浮层遮罩高度修正

        if($('.slip3').length){
            options.filter.css({'background':'#FFF','z-index':2999});// 单层的要加背景色;
        }

        options.filter.find('.slip1,.slip2, .slip3, .slipSquare, .slipRound').height(filterHeight);
        maskObj.css({
            'height': maskHeight,
            'top': headerHeight+'px'
        });
    }
};

// 职位操作
WealinkMobile.Position = {
    /**
     * 申请职位；
     *
     * @param pid
     * @param obj
     * @param callback
     * @returns {boolean}
     */
    'apply': function (pid, obj, callback) {
        if(obj.jqmData('wlm-loading')) return false;
        _gaq.push(['_trackEvent', 'zhiwei', 'wap_Apply']);

        $.ajax({
            dataType: 'json',
            data: {pid: pid},
            type: 'POST',
            async: false,
            cache: false,
            url: WealinkMobile.baseUrl + "/zhiwei/doApply/",
            beforeSend:function(){
                obj. jqmData('wlm-loading', true);
                return true;
            },
            
            complete:function(){
                obj.jqmRemoveData('wlm-loading');
            },
            success: function (data) {
                if (data.flag == true) {
                    if (typeof(callback) === 'function') {
                        callback(obj, data);
                    } else {
                        WealinkMobile.Popup.alert(data.msg);
                    }
                } else {
                    switch (data.code) {
                        case 'INVALID_PARAMS':
                            WealinkMobile.Popup.alert(data.msg, 'warn', 3);
                            break;
                        case 'POSITION_APPLIED':
                            WealinkMobile.Popup.alert(data.msg, 'warn', 3);
                            break;
                        case 'LOGIN_FIRST':
                            window.location = WealinkMobile.baseUrl+"/denglu/";
                            break;
                        case 'PROFILE_INCOMPLETE':
                        case 'INVALID_INTENTION_SETTING':
                            var actionName = 'INVALID_INTENTION_SETTING'==data.code?'修改':'完善';
                            var html =
                                '<div class="popTitle"><h2>提示</h2></div>' +
                                    '<div class="popContent">' +
                                    '<p>'+data.msg+'</p>' +
                                    '<p class="btn"><button data-role="none" class="leftBtn  wlm-popClose">暂不'+actionName+'</button><button data-role="none" class="wlm-popPositive">立即'+actionName+'</button></p>' +
                                    '</div>';
                            WealinkMobile.Popup.window({'html':html, 'positiveCallback':function(){
                                $('.wlm-popClose').click();
                                $.mobile.changePage(WealinkMobile.baseUrl+'/dangan/editqiuzhi/',{transition:'slide','changeHash':true});
                            }});
                            break;
                        default:
                            WealinkMobile.Popup.alert(data.msg, 'warn', 3);
                            break;
                    }
                }
            },
            error: function () {
                /*WealinkMobile.Popup.alert('连接服务器失败，请稍候再试！', 'warn', 3);*/
            }
        });
        return true;
    },

    /**
     * 收藏职位；
     *
     * @param pid
     * @param obj
     * @param callback
     * @returns {boolean}
     */
    'favorite':function(pid, obj, callback){
        if(obj.jqmData('wlm-loading')) return false;
        _gaq.push(['_trackEvent', 'zhiwei', 'wap_collect']);
        $.ajax({
            dataType: 'json',
            data: {pid: pid},
            type: 'POST',
            async: false,
            cache: false,
            url: WealinkMobile.baseUrl + "/zhiwei/favorite/",
            beforeSend:function(){
                obj.jqmData('wlm-loading', true);
                return true;
            },
            complete:function(){
                obj.jqmRemoveData('wlm-loading');
            },
            success: function (data) {
                if (data.flag == true) {
                    if (typeof(callback) === 'function') {
                        callback(obj, data);
                    } else {
                        WealinkMobile.Popup.alert(data.msg);
                    }
                } else {
                    switch (data.code) {
                        case 'INVALID_PARAMS':
                            WealinkMobile.Popup.alert(data.msg, 'warn', 3);
                            break;
                        case 'POSITION_COLLECTED':
                            WealinkMobile.Popup.alert(data.msg, 'warn', 3);
                            break;
                        case 'LOGIN_FIRST':
                            window.location = WealinkMobile.baseUrl+"/denglu/";
                            break;
                        default:
                            WealinkMobile.Popup.alert(data.msg, 'warn', 3);
                            break;
                    }
                }
            },
            error: function () {
                WealinkMobile.Popup.alert('连接服务器失败，请稍候再试！', 'warn', 3);
            }
        });
        return true;
    }
};

WealinkMobile.User = {
    // 档案评价；
    'comment':function(commentObj, params){
        if(!params.relation_id) WealinkMobile.Popup.alert('请选择你们的关系!','warn', 3);
        if(!params.content) WealinkMobile.Popup.alert('请填写评价内容!','warn', 3);

        if(!params.relation_id || !params.content || !params.uid) return false;

        this._request({
            'data':params,
            'url':WealinkMobile.baseUrl+'/renmai/addComment',
            'type':'POST'
        }, commentObj, function(data){
            if(data.flag==true){
                WealinkMobile.Popup.alert(data.msg,'correct',1, function(){
                    window.location.href=WealinkMobile.baseUrl+'/zpdangan/'+params.uid+'/';
                });
            }else{
                if(data.code == 'LOGIN_FIRST')
                    $.mobile.change('/denglu/',{transition:'slide', changeHash:true, domCache:false});
                $('.wlm-popClose').click();
                if('INVALID_HR_PROFILE'==data.code||'INVALID_PROFILE'==data.code){
                    var html =
                        '<div class="popTitle"><h2>提示</h2></div>' +
                            '<div class="popContent">' +
                            '<p>'+data.msg+'</p>' +
                            '<p class="btn"><button data-role="none" class="leftBtn wlm-popClose">暂不完善</button><button data-role="none" class="wlm-popPositive">立即完善</button></p>' +
                            '</div>';
                    WealinkMobile.Popup.window({'html':html, 'positiveCallback':function(){
                        $('.wlm-popClose').click();
                        $.mobile.changePage(WealinkMobile.baseUrl+'/dangan/'+(data.code=='INVALID_HR_PROFILE'?'editzhaopin':'editqiuzhi')+'/',{transition:'slide','changeHash':true});
                    }});
                }else{
                    WealinkMobile.Popup.alert(data.msg, 'warn', 3);
                }
            }
        } );
    },
    // 加关注；
    'concern':function(concernObj){
        var uid = concernObj.jqmData('wlm-uid');
        this._request({
            'data': {'uid': uid},
            'url': WealinkMobile.baseUrl + "/renmai/addConcern"
        }, concernObj, function (data) {
            if(data.result == 1){
                WealinkMobile.Popup.alert(data.msg, 'correct', 1, function(){
                    concernObj.text('已关注');
                    concernObj.jqmData('wlm-unconcern') && concernObj.jqmData('wlm-operation','unConcern');
                    !concernObj.jqmData('wlm-unconcern') && concernObj.jqmRemoveData('wlm-operation').removeAttr('data-wlm-operation').addClass('used');
                });
            } else if(data.result == -100){
            	window.location.href = '/denglu/';
                //$.mobile.changePage('/denglu/',{'transition':'slide','changeHash':'true'});
            }else {
                if(data.result == -4 || data.result ==-5){
                    var html =
                        '<div class="popTitle"><h2>提示</h2></div>' +
                        '<div class="popContent">' +
                            '<p>'+data.msg+'</p>' +
                            '<p class="btn"><button data-role="none" class="leftBtn  wlm-popClose">暂不完善</button><button data-role="none" class="wlm-popPositive">立即完善</button></p>' +
                        '</div>';
                    WealinkMobile.Popup.window({'html':html, 'positiveCallback':function(){
                        $('.wlm-popClose').click();
                        $.mobile.changePage(WealinkMobile.baseUrl+'/dangan/'+(data.result==-4?'editzhaopin':'editqiuzhi')+'/',{transition:'slide','changeHash':true});
                    }});
                }else{
                    WealinkMobile.Popup.alert(data.msg, 'warn', 3);
                }
            }
        });
    },

    /**
     * 发私信；
     * @param sendMessageObj
     */
    'sendMessage':function(sendMessageObj){
        var toUid = sendMessageObj.jqmData('wlm-uid');
        var isReload = sendMessageObj.jqmData('wlm-reload');
        var html =
            '<div class="popContent">' +
            '<div class="popTxt">' +
                '<textarea id="messageContent" name="content" data-role="none" rows="4" cols="50" autofocus=""></textarea>' +
            '</div>' +
                '<div class="popGreenBtn">' +
                '<button class="leftBtn wlm-popClose">取消</button>' +
                '<button class="rightBtn wlm-popPositive" onclick="_gaq.push([\'_trackEvent\', \'messageinput\', \'wap_messagesend\']);">发送</button>' +
                '</div>' +
            '</div>';
        WealinkMobile.Popup.window({'html':html, 'positiveCallback':function(){
              WealinkMobile.User._request({
                  'url':WealinkMobile.baseUrl+'/message/send/',
                  'data':{'iToUid':toUid,'ajax':1,'action':'doSend','tContent':$('#messageContent').val()}
              }, sendMessageObj, function(data){
                  if(data.flag == true){
                      $('.wlm-popClose').click();
                      WealinkMobile.Popup.alert(data.msg, 'correct', 1, function(){
                          if(isReload) window.location.reload();
                      });
                  }else{
                      if(data.code=='LOGIN_FIRST')
                      	  window.location.href = '/denglu/';
                          //$.mobile.changePage('/denglu/',{transition:'slide',changeHash:true});
                      $('.wlm-popClose').click();
                      if('INVALID_HR_PROFILE'==data.code||'INVALID_PROFILE'==data.code){
                          var html =
                              '<div class="popTitle"><h2>提示</h2></div>' +
                                  '<div class="popContent">' +
                                  '<p>'+data.msg+'</p>' +
                                  '<p class="btn"><button data-role="none" class="leftBtn  wlm-popClose">暂不完善</button><button data-role="none" class="wlm-popPositive">立即完善</button></p>' +
                                  '</div>';
                              WealinkMobile.Popup.window({'html':html, 'positiveCallback':function(){
                              $('.wlm-popClose').click();
                              $.mobile.changePage(WealinkMobile.baseUrl+'/dangan/'+(data.code=='INVALID_HR_PROFILE'?'editzhaopin':'editqiuzhi')+'/',{transition:'slide','changeHash':true});
                          }});
                      }else{
                              WealinkMobile.Popup.alert(data.msg, 'warn', 3);
                      }

                  }
              });
        }});
    },

    // 档案委托；
    'entrust':function(entrustObj){
        var uid = entrustObj.jqmData('wlm-uid');
        this._request({
            'data': {'hrUid': uid},
            'url': WealinkMobile.baseUrl + "/bole/entrustResume"
        }, entrustObj, function (data) {
            if(data.flag == true){
                WealinkMobile.Popup.alert(data.msg||'档案委托成功！', 'correct', 1, function(){
                    entrustObj.text('已委托');
                    entrustObj.jqmData('wlm-unentrust') && entrustObj.jqmData('wlm-operation','unEntrust');
                    !entrustObj.jqmData('wlm-unentrust') && entrustObj.jqmRemoveData('wlm-operation').removeAttr('data-wlm-operation').addClass('used');
                });
            }else {
                var errorMessage = '';
                switch(data.code){
                    case 'LOGIN_FIRST':
                        errorMessage = '请登录后再操作！';
                        break;
                    case 'ENTRUSTED_BEFORE':
                        errorMessage = '你曾经将简历委托给该猎头，无需重复委托';
                        break;
                    case 'INTENTION_ERROR':
                        errorMessage = '您的求职状态为<b>“暂时不想找工作”</b>，不能进行委托操作，请修改！';
                        break;
                    case 'INVALID_PROFILE':
                        errorMessage = data.msg;
                        break;
                    case 'ENTRUST_LIMIT':
                        errorMessage = '您的委托已经超过上限，不能进行委托操作！';
                        break;
                    default:
                        errorMessage = data.msg||'委托失败，请稍后再试';
                }

                $('.wlm-popClose').click();
                if('INVALID_PROFILE'==data.code || 'INTENTION_ERROR'==data.code){
                    var labelNegative=('INTENTION_ERROR'==data.code)?'暂不修改':'暂不完善';
                    var labelPositive=('INTENTION_ERROR'==data.code)?'立即修改':'立即完善';
                    var html =
                        '<div class="popTitle"><h2>提示</h2></div>' +
                            '<div class="popContent">' +
                            '<p>'+errorMessage+'</p>' +
                            '<p class="btn"><button data-role="none" class="leftBtn  wlm-popClose">'+labelNegative+'</button><button data-role="none" class="wlm-popPositive">'+labelPositive+'</button></p>' +
                            '</div>';
                    WealinkMobile.Popup.window({'html':html, 'positiveCallback':function(){
                        $('.wlm-popClose').click();
                        $.mobile.changePage(WealinkMobile.baseUrl+'/dangan/'+(data.code=='INVALID_HR_PROFILE'?'editzhaopin':'editqiuzhi')+'/',{transition:'slide','changeHash':true});
                    }});

                    return false;
                }
                WealinkMobile.Popup.alert(errorMessage, 'warn', 3);
            }
        });
    },

    // 查看联系方式；
    'getContact':function(downloadObj){
        var params = {
            'url': WealinkMobile.baseUrl + '/dangan/download/',
            'type': 'GET',
            'data': {uid: downloadObj.jqmData('wlm-uid'), 'confirmDownload': downloadObj.jqmData('wlm-confirmed')}
        };
        this._request(params, downloadObj, function (response) {
            $(".wlm-popClose").click();
            if (response.flag == true) {
                var email = '';
                var mobile = '';
                if (response.data && response.data.email) email = response.data.email;
                if (response.data && response.data.mobile) mobile = response.data.mobile;

                downloadObj.after('<li>邮箱：' + email + '</li><li>手机：' + mobile + '</li>').hide();
                if('SUCCESS'==response.code){
                    WealinkMobile.Popup.alert(response.msg, 'correct', 2);
                }
            }else{
                if(response.code=='LOGIN_FIRST'){
                	window.location.href = '/denglu/';
                    //$.mobile.changePage('/denglu/',{'transition':'slide','changeHash':'true'});
                    return false;
                }

                if('INVALID_HR_PROFILE'==response.code){
                    var html =
                        '<div class="popTitle"><h2>提示</h2></div>' +
                            '<div class="popContent">' +
                            '<p>'+response.msg+'</p>' +
                            '<p class="btn"><button data-role="none" class="leftBtn  wlm-popClose">暂不完善</button><button data-role="none" class="wlm-popPositive">立即完善</button></p>' +
                            '</div>';
                    WealinkMobile.Popup.window({'html':html, 'positiveCallback':function(){
                        $('.wlm-popClose').click();
                        $.mobile.changePage(WealinkMobile.baseUrl+'/dangan/'+(response.code=='INVALID_HR_PROFILE'?'editzhaopin':'editqiuzhi')+'/',{transition:'slide','changeHash':true});
                    }});

                    return false;
                }

                if('NO_CREDIT'==response.code || 'INVALID_IDENTITY'==response.code){
                    var html =
                        '<div class="popTitle"><h2>提示</h2></div>' +
                            '<div class="popContent">' +
                            '<p>'+response.msg+'</p>' +
                            '<p class="btn"><button data-role="none" class="leftBtn  wlm-popClose">取消</button><button data-role="none" class="wlm-popPositive">确定</button></p>' +
                            '</div>';
                    WealinkMobile.Popup.window({'html':html, 'positiveCallback':function(){
                        $('.wlm-popClose').click();
                    }});

                    return false;
                }

                if(response.code = 'PLEASE_CONFIRM'){
                    var html = '<div class="popTitle"><h2>提示</h2></div>' +
                        '<div class="popContent">' +
                        '<p>'+response.msg+'</p>' +
                        '<p class="btn"><button data-role="none" class="leftBtn  wlm-popClose">取消</button><button data-role="none" class="wlm-popPositive">确定</button></p>' +
                        '</div>';
                    WealinkMobile.Popup.window({html:html, positiveCallback: function(){
                        downloadObj.jqmData('wlm-confirmed', 1);
                        WealinkMobile.User.getContact(downloadObj);
                    }});

                    return false;
                }

                WealinkMobile.Popup.alert(response.msg, 'warn', 3);
            }
        });
    },

    // 回复公司点评；
    'replyCompanyComment':function(replyObj){
        var cid = replyObj.jqmData('wlm-cid');
        var comment_id = replyObj.jqmData('wlm-commentid');

        $(document).scrollTo(0, 0, {axis:'y',onAfter:function(){setTimeout(function(){}, 10)}});

        var html =
            '<div class="popContent">' +
                '<div class="popTxt">' +
                '<textarea id="replyContent" name="content" data-role="none" rows="4" cols="50" autofocus=""></textarea>' +
                '</div>' +
                '<div class="popGreenBtn">' +
                '<button class="leftBtn wlm-popClose">取消</button>' +
                '<button class="rightBtn  wlm-popPositive">发送</button>' +
                '</div>' +
                '</div>';
        WealinkMobile.Popup.window({'html':html, 'positiveCallback':function(){
            WealinkMobile.User._request({
                'url':WealinkMobile.baseUrl+'/gongsi/addReply/',
                'data':{'cid':cid,'commentId':comment_id,'replyContent':$('#replyContent').val()}
            }, replyObj, function(data){
                if(data.flag == true){
                    $('.wlm-popClose').click();
                    WealinkMobile.Popup.alert(data.msg, 'correct', 1, function(){
                        var widget = $('#repliesWidget');
                        if(widget.length > 0){
                            $('<li class="addBorderNoH">' +
                                '<p>'+data.reply+'</p><p class="f18">'+data.replyDate+'&nbsp;回复</p>' +
                              '</li>'
                            ).prependTo('#repliesWidget ul.widget-list');

                            if(widget.find('.widget-list li').length > 3){
                                widget.find('.widget-list li').last().remove();
                                widget.find('.widget-list li').last().addClass('noBd');
                            }

                            return true;
                        }
                        window.location.reload();
                    });
                }else{
                    if(data.code=='LOGIN_FIRST')
                    	window.location.href = '/denglu/';
                        //$.mobile.changePage('/denglu/',{transition:'slide',changeHash:true});
                    $('.wlm-popClose').click();
                        WealinkMobile.Popup.alert(data.msg, 'warn', 3);
                }
            });
        }});
    },

    'replyMianjing': function (replyObj) {
        var interviewId = replyObj.jqmData('wlm-interview_id');

        var html =
            '<div class="popContent">' +
                '<div class="popTxt">' +
                '<textarea id="replyContent" name="content" data-role="none" rows="4" cols="50" autofocus=""></textarea>' +
                '</div>' +
                '<div class="popGreenBtn">' +
                '<button class="leftBtn wlm-popClose">取消</button>' +
                '<button class="rightBtn  wlm-popPositive">发送</button>' +
                '</div>' +
                '</div>';
        WealinkMobile.Popup.window({'html': html, 'positiveCallback': function () {
            WealinkMobile.User._request({
                'url': WealinkMobile.baseUrl + '/mianjing/saveComment/',
                'data': {'iInterviewId': interviewId, 'sComment': $('#replyContent').val()}
            }, replyObj, function (data) {
                if (data.flag == true) {
                    $('.wlm-popClose').click();
                    WealinkMobile.Popup.alert(data.msg, 'correct', 1, function () {
                        var widget = $('#repliesWidget');
                        if(widget.length > 0){
                            $('<li class="addBorderNoH">' +
                                '<p>'+data.reply+'</p><p class="f18">'+data.replyDate+'&nbsp;回复</p>' +
                                '</li>'
                            ).prependTo('#repliesWidget ul.widget-list');

                            widget.find('.widget-list li').last().remove();
                            widget.find('.widget-list li').last().addClass('noBd');

                            return true;
                        }
                        window.location.reload();
                    });
                } else {
                    if (data.code == 'LOGIN_FIRST')
                    	window.location.href = '/denglu/';
                        //$.mobile.changePage('/denglu/', {transition: 'slide', changeHash: true});
                    $('.wlm-popClose').click();
                    WealinkMobile.Popup.alert(data.msg, 'warn', 3);
                }
            });
        }});
    },

    // ajax请求的统一调用；
    '_request': function (params, obj, callback) {
        params = $.extend(
            {
                'type': 'POST',
                'cache': false,
                'dataType': 'json',
                'beforeSend': function () {
                    if (obj.jqmData('wlm-loading')) return false;
                    obj.jqmData('wlm-loading', true);
                    $.mobile.loading('show');
                    return true;
                },
                'complete': function () {
                    obj.jqmRemoveData('wlm-loading');
                    $.mobile.loading('hide');

                },
                'success': function (data) {
                    callback(data);
                },
                'error':function(){
                    obj.jqmRemoveData('wlm-loading');
                    WealinkMobile.Popup.alert('网络异常，请稍候再试！','warn', 3);
                }
            },
            params
        );

        $.ajax(params);
    }
};

WealinkMobile.Company = {

};

// 弹出消息浮层
WealinkMobile.Popup = {
    'alert': function (content, style, interval, callback) {
        style = style || 'correct';
        interval = interval || 1;

        var html = '<div class="popWindow" style="width:86%">' +
            '<div class="popContent"><p class="'+style+'">'+content+'</p></div>'+
            '</div>'+
            '<div class="popMask"></div>';

        $(html).appendTo('body');
        this._adjust();
        this._show();

        setTimeout(function(){
            WealinkMobile.Popup._hide();
            if(typeof callback == 'function'){
                callback.call();
            }
        }, interval * 1000);
    },

    // 普通浮层；
    'window': function(options){
        options = $.extend({
            width: '86%',
            endOpacity:0.5,
            url:'',
            html:''
        },options);

        options.url!=''  && $.get(options.url, {}, function(data){
            options.html = data;
        });

        options.html = '<div class="popWindow">'+options.html+'</div><div class="popMask"></div>';
        $(options.html).css({width:options.width}).appendTo('body');

        this._adjust(options) && this._show();
    },

    '_show': function(){$('.popWindow, .popMask').fadeIn(200); return true;},
    '_hide':function(){$('.popWindow, .popMask').fadeOut(200).remove(); return true;},
    '_adjust':function(options){
        var screenWidth = $(window).width(),
            alertWindow = $('.popWindow'),
            alertWidth  = alertWindow.outerWidth();

        alertWindow.css({
            top:$(window).height() / 2 + $(window).scrollTop() - alertWindow.outerHeight()/2,
            left:(screenWidth/2 - alertWidth/2)+'px'
        });

        $('.popMask').height($(document).height()).width($(window).width()).click(function(){
            WealinkMobile.Popup._hide();
        });

        $('.wlm-popClose').click(function(){
            WealinkMobile.Popup._hide();
            if(options && options.closeCallback){
                options.closeCallback.call();
            }
        });

        $('.wlm-popPositive').click(function(){
            if(options && options.positiveCallback){
                options.positiveCallback.call();
            }
        });

        return true;
    }
};

// --------- 几个列表页，滚动加载：首页 、伯乐、人脉、工资、点评、面经--------；
var scrollLoadPages = "#index, #dianping, #bole, #gongzi,#renmai, #mianjing";
$(document)
    .delegate(scrollLoadPages,'pagebeforeshow', function(){
        var listPage = $('#'+$(this).jqmData('wlm-listpage'));
        var navOffset = $('.top').eq(0).height() + $('.nav').find('>div').eq(0).height();

        if(listPage){
            $(document).on("scrollstop", function () {
                var scrollTop = $(document).scrollTop();
                if (scrollTop > navOffset) {
                    var heightFix = $('.nav').height();

                    if(!$('.navBar').hasClass('fixedTop')){
                        $('.navBar').addClass('fixedTop').siblings().hide();
                        $("div[data-role=page]").css('padding-top', heightFix+'px');
                    }
                }else{
                    $('.navBar').removeClass('fixedTop').siblings().show();
                    $("div[data-role=page]").css('padding-top', '0');
                }

                WealinkMobile.ListPage.scroll({
                    container: listPage,
                    scrollOffset: 60,
                    callback: function (data) {return data.html;}
                });
            });
        }
    })
    .delegate(scrollLoadPages,'pagebeforehide', function(){
        $(document).unbind('scrollstop');
    })
// --------- 求职职位列表 --------；
    .delegate("#qiuzhi-zhiwei",'pagebeforeshow', function(){
        $(document).on("scrollstop", function () {
            WealinkMobile.ListPage.scroll({
                container: $('#zhiwei-list'),
                scrollOffset: 60,
                callback: function (data) {
                    return data.html;
                }
            });
        });
    })
    .delegate('#qiuzhi-zhiwei','pagebeforehide', function(){
        $(document).unbind('scrollstop');
    })
// --------- 招聘简历列表 --------；
    .delegate("#zhaopin-jianli",'pagebeforeshow', function(){
        $(document).on("scrollstop", function () {
            WealinkMobile.ListPage.scroll({
                container: $('#jianli-list'),
                scrollOffset: 60,
                callback: function (data) {
                    return data.html;
                }
            });
        });
    })
    .delegate('#zhaopin-jianli','pagebeforehide', function(){
        $(document).unbind('scrollstop');
    })
    
// --------- 职位管理 --------；
    .delegate("#zhaopin-zhiwei",'pagebeforeshow', function(){
        $(document).on("scrollstop", function () {
            WealinkMobile.ListPage.scroll({
                container: $('#zhiwei-manage'),
                scrollOffset: 60,
                callback: function (data) {
                    return data.html;
                }
            });
        });
    })
    .delegate('#zhaopin-zhiwei','pagebeforehide', function(){
        $(document).unbind('scrollstop');
    })
// --------- 求职人脉列表 --------；
    .delegate("#qiuzhi-renmai",'pagebeforeshow', function(){
        $(document).on("scrollstop", function () {
            WealinkMobile.ListPage.scroll({
                container: $('#renmai-list'),
                scrollOffset: 60,
                callback: function (data) {
                    return data.html;
                }
            });
        });
    })
    .delegate('#qiuzhi-renmai','pagebeforehide', function(){
        $(document).unbind('scrollstop');
    })
// --------- 私信列表 --------；
    .delegate("#thread-index",'pagebeforeshow', function(){
        $(document).on("scrollstop", function () {
            WealinkMobile.ListPage.scroll({
                container: $('#thread-list'),
                scrollOffset: 60,
                callback: function (data) {
                    return data.html;
                }
            });
        });
    })
    .delegate('#thread-index','pagebeforehide', function(){
        $(document).unbind('scrollstop');
    })
    
    .delegate("#message-index",'pagebeforeshow', function(){
        $(document).on("scrollstop", function () {
            WealinkMobile.ListPage.scroll({
                container: $('#message-list'),
                scrollOffset: 60,
                callback: function (data) {
                    return data.html;
                }
            });
        });
        $('a.wlm-operation').unbind('click').live('click', function () {
            var operation = $(this).jqmData('wlm-operation');
            if (operation in WealinkMobile.User) WealinkMobile.User[operation]($(this));
        });
    })
    .delegate('#message-index','pagebeforehide', function(){
        $(document).unbind('scrollstop');
    })
// --------- 通过伯乐求职列表 --------；
    .delegate("#qiuzhi-bole",'pagebeforeshow', function(){
        $(document).on("scrollstop", function () {
            WealinkMobile.ListPage.scroll({
                container: $('#bole-list'),
                scrollOffset: 60,
                callback: function (data) {
                    return data.html;
                }
            });
        });
    })
    .delegate('#qiuzhi-bole','pagebeforehide', function(){
        $(document).unbind('scrollstop');
    })
// --------- 我的发布列表 --------；
    .delegate("#wode-fabu",'pagebeforeshow', function(){
        $(document).on("scrollstop", function () {
            WealinkMobile.ListPage.scroll({
                container: $('#fabu-list'),
                scrollOffset: 60,
                callback: function (data) {
                    return data.html;
                }
            });
        });
    })
    .delegate('#wode-fabu','pagebeforehide', function(){
        $(document).unbind('scrollstop');
    })
// --------- 点评详情页 --------；
    .delegate('#dianping-detail', 'pagebeforeshow', function(){
        $('.widget-list').each(function(){
            $(this).find('>li').last().addClass('noBd');
        });
        // 加载公司点评回复；
        WealinkMobile.ListWidget.init({
            'container':$('#repliesWidget'),
            'noResult':'还没有该点评的回复哦！',
            'callback':function(data){
                var count = data.list.length;
                var html = "";
                for(var i=0; i<count; i++){
                    row = data.list[i];
                    html += '<li class="addBorderNoH">' +
                        '<p>'+row.content+'</p><p class="f18">'+row.add_time+'&nbsp;回复</p></li>';
                }

                return html;
            }
        });
        // 该公司的其他点评；
        WealinkMobile.ListWidget.init({
            'container':$('#dianpingWidget'),
            'noResult':'没有更多关于该公司的点评了！',
            'callback':function(data){
                var count = data.list.length;
                var html = "";
                for(var i=0; i<count; i++){
                    row = data.list[i];
                    html +=
                        '<li class="addBorderNoH">' +
                        '<a data-ajax="false" href="'+WealinkMobile.baseUrl+'/dianping/view/'+row.cid+'/'+row.id+'/"><div>'+
                        '<h3 class="star">'+row.relation+'点评：<span class="star'+row.star+'"></span></h3>' +
                        '<p>'+row.content+'</p>' +
                        '</div></a></li>';
                }

                return html;
            }
        });

        $('#btnAddReply').unbind('click').click(function(){
                WealinkMobile.User.replyCompanyComment($(this));
        });
    })
// --------- 工资详情页 --------；
    .delegate('#gongzi-detail', 'pagebeforeshow', function(){
        $('.widget-list').each(function(){
            $(this).find('>li').last().addClass('noBd');
        });
        // 加载公司其他职位工资；
        WealinkMobile.ListWidget.init({
            'container':$('#gongziWidget'),
            'noResult':'还没有该公司的工资记录哦！',
            'callback':function(data){
                var count = data.list.length;
                var html = "";
                for(var i=0; i<count; i++){
                    row = data.list[i];
                    html += '<li class="addBorder"><a data-ajax="false" href="http://m.wealink.com/gongzi/zhiwei/'+row.id+'/"><div><ul><li><h3>'+row.position_name+'<i class="salary">￥'+row.salary_avg+'</i></h3></li><li><h4>'+row.location+'<span class="data">平均月薪</span></h4></li></ul></div></a></li>';
                }

                return html;
            }
        });

        $('.orangerBar,.greyBar').find('>span').width(0).end().hide();
    })
    .delegate('#gongzi-detail', 'pageshow', function(){
        var showGongzi = function (){
            var $avgObj = $('#salary-avg'),
                $minObj = $('#salary-min'),
                $maxObj = $('#salary-max'),
                salary_avg = $avgObj.jqmData('wlm-salary'),
                salary_min = $minObj.jqmData('wlm-salary'),
                salary_max = $maxObj.jqmData('wlm-salary');

            var segments = 4; // 按4等分之后进行长度校准；
            var backgroundLength = $avgObj.find('>p').width();
            var difference   = salary_max - salary_min;
            var segmentValue = Math.ceil ( difference / ( segments - 1 ) );

            var min = salary_min - Math.ceil ( segmentValue / 2 );
            var max = salary_max + Math.ceil ( segmentValue / 2 );

            var adjustment = 0;
            if ( min < 0 ) {
                adjustment = Math.abs ( min );
                max        = max + Math.abs ( min );
                min        = 0;
            }

            // 校准到整百;
            adjustment += Math.ceil(
                (
                    ( min % 100 ) +                     // 标尺左移的偏移量
                        ( 100 - max % 100 ) // 标尺右移的偏移量
                    )/segments
            );
            min          = min - ( min % 100 );
            max          = max + ( 100 - max % 100 );
            segmentValue = ( max - min ) / segments;
            var valueLength = (backgroundLength/segments) / segmentValue;

            var minWidth = (salary_min - min + adjustment) * valueLength;
            var avgWidth = (salary_avg - min + adjustment) * valueLength;
            var maxWidth = (salary_max - min + adjustment) * valueLength;

            setTimeout(function(){
                $avgObj.find('>p').fadeIn(200).find('>span').animate({width: avgWidth+'px'}, 500);
                $minObj.find('>p').fadeIn(200).find('>span').animate({width: minWidth+'px'}, 300);
                $maxObj.find('>p').fadeIn(200).find('>span').animate({width: maxWidth+'px'}, 800);
            }, 100);
         }

        showGongzi();
    })
// --------- 职位详情页 --------；
    .delegate('#zhiwei-detail','pageinit',function(){
        $(this).delegate('#btnAddFavorite', 'click', function(){
            var pid = $(this).jqmData('wlm-pid');
            if (!pid) return false;
            WealinkMobile.Position.favorite(pid, $(this), function (O, response) {
                if (response.flag == true) {
                    WealinkMobile.Popup.alert('收藏成功！', 'correct', 2, function(){
                        O.text('已收藏').unbind();
                    });
                }
            });
        }).delegate('#companyDescriptionToggle', 'click', function(){
            var $description = $('#companyDescription');
            if($description.hasClass('t5over')){
                $(this).find('span').text('收起简介').addClass('close');
                $description.removeClass('t5over').css({'min-height':'100px','line-height':1.7, 'word-break':'break-all'});
            }else{
                $(this).find('span').text('展开更多').removeClass('close');
                $description.addClass('t5over').removeAttr('style');
            }
        }).delegate('#btnApplyPosition', 'click', function(){
            var pid = $(this).jqmData('wlm-pid');
            if(!pid) return false;
            WealinkMobile.Position.apply(pid, $(this), function(O, response){
                if(response.flag == true){
                    WealinkMobile.Popup.alert('申请成功！', 'correct', 2, function(){
                        O.find('span').text('已申请').unbind().parent().addClass('applyBtnDown');
                    });
                }
            })
        });

        var baseUrl = WealinkMobile.baseUrl;
        // 加载公司点评；
        WealinkMobile.ListWidget.init({
            'container':$('#dianpingWidget'),
            'noResult':'该公司还没有点评哦！',
            'callback':function(data){
                var count = data.list.length;
                var html = "";
                for(var i=0; i<count; i++){
                    row = data.list[i];
                    html +=
                        '<li class="addBorderNoH">' +
                            '<a data-ajax="false" href="'+WealinkMobile.baseUrl+'/dianping/view/'+row.cid+'/'+row.id+'/"><div>'+
                            '<h3 class="star">'+row.relation+'点评：<span class="star'+row.star+'"></span></h3>' +
                            '<p>'+row.content+'</p>' +
                            '</div></a></li>';
                }

                return html;
            }
        });

        // 加载公司面经；
        WealinkMobile.ListWidget.init({
            'container':$('#mianjingWidget'),
            'noResult':'该公司还没有面经哦！',
            'callback':function(data){
                var count = data.list.length;
                var html = "";
                for(var i=0; i<count; i++){
                    row = data.list[i];
                    html +=
                        '<li class="addBorderNoH"><a href="http://m.wealink.com/mianjing/view/'+row.id+'/" class="ui-link"><div><p class="f30BT">'+row.sPositionName+' - '+row.sCompanyName+'</p><p><span class="colB18">面试结果：'+row.result+'</span>'+row.sDescription+'</p></div></a></li>';
                }
                return html;
            }
        });

        // 加载公司工资信息；
        WealinkMobile.ListWidget.init({
            'container':$('#gongziWidget'),
            'noResult':'该公司还没有工资信息哦！',
            'callback':function(data){
                var count = data.list.length;
                var html = "";
                for(var i=0; i<count; i++){
                    row = data.list[i];
                    html += '<li class="addBorder"><a data-ajax="false" href="http://m.wealink.com/gongzi/zhiwei/'+row.id+'/"><div><ul><li><h3>'+row.position_name+'<i class="salary">￥'+row.salary_avg+'</i></h3></li><li><h4>'+row.location+'<span class="data">平均月薪</span></h4></li></ul></div></a></li>';
                }

                return html;
            }
        });

        // 加载公司其他职位；
        WealinkMobile.ListWidget.init({
            'container':$('#zhiweiWidget'),
            'noResult':'该公司没有在招职位哦！',
            'callback':function(data){
                var count = data.list.length;
                var html = "";
                for(var i=0; i<count; i++){
                    row = data.list[i];
                    html +=
                        '<li class="addBorderNoH">' +
                            '<ul>' +
                            '<li><h3 class="jobName"><a data-ajax="false" style="color:black;" href="'+baseUrl+'/zhiwei/view/'+row.pid+'/">' +row.title +'</a><i class="salary">'+row.salary+'</i></h3></li>' +
                            '<li><p class="oneLine">'+row.company_name+'</p></li>' +
                            '<li>'+row.location+'<span class="data">'+row.publishDate+'</span></li>' +
                            '</ul>' +
                            '</li>';
                }

                return html;
            }
        });
    })
// -------- 修改工作 教育经历--------；
	.delegate('#dangan', 'pageinit', function () {
        $('#danganForm').find('li[data-wlm-filter-field]').each(function () {
            $(this).on('click', function () {
                var fieldName = $(this).jqmData('wlm-filter-field');
                var menuType = $(this).jqmData('wlm-filter-type');
                    menuType = menuType||'double';
                var day_type = $(this).jqmData('wlm-filter-day-type')||false;
                var showbuxian = $(this).jqmData('wlm-filter-showbuxian');
                var options = {'fieldName': fieldName, 'filter': $('#wlm-filter'),'trigger':$(this),'year':true,'month':true,'day':day_type,'showbuxian':showbuxian};

                WealinkMobile.filter[menuType].call(this, options);
            });
        });
     })

// --------- 面经详情页 --------；
    .delegate('#mianjing-detail', 'pagebeforeshow', function(){
        $('.widget-list').each(function(){
            $(this).find('>li').last().addClass('noBd');
        });
        $('#btnAddReply').unbind('click').click(function(){
            WealinkMobile.User.replyMianjing($(this));
        });
        // 加载面经回复；
        WealinkMobile.ListWidget.init({
            'container':$('#repliesWidget'),
            'noResult':'还没有该面经的回复哦！',
            'callback':function(data){
                var count = data.list.length;
                var html = "";
                for(var i=0; i<count; i++){
                    row = data.list[i];
                    html += '<li class="addBorderNoH">' +
                        '<p>'+row.sContent+'</p><p class="f18">'+row.dCreateTime+'&nbsp;回复</p></li>';
                }

                return html;
            }
        });
        // 更多同名职位面经；
        WealinkMobile.ListWidget.init({
            'container':$('#mianjingWidget'),
            'noResult':'没有更多关于该职位的面经了！',
            'callback':function(data){
                var count = data.list.length;
                var html = "";
                for(var i=0; i<count; i++){
                    row = data.list[i];
                    html +=
                        '<li class="addBorderNoH"><a data-ajax="false" href="http://m.wealink.com/mianjing/view/'+row.id+'/" class="ui-link"><div><p class="f30BT">'+row.sPositionName+' - '+row.companyName+'</p><p><span class="colB18">'+row.result+'</span>'+row.sDescription+'</p></div></a></li>';
                }
                return html;
            }
        });
    })
// -------- 搜索页 --------；
    .delegate('#search', 'pageinit', function () {
        $('#formSearch').find('li[data-wlm-filter-field]').each(function () {
            $(this).on('click', function () {
                var fieldName = $(this).jqmData('wlm-filter-field');
                var parentFieldName = $(this).jqmData('filter-parent-field');
                var menuType = $(this).jqmData('wlm-filter-type');
                    menuType = menuType||'double';
                var options = {'fieldName': fieldName,'parentFieldName': parentFieldName, 'filter': $('#wlm-filter'),'trigger':$(this)};

                WealinkMobile.filter[menuType].call(this, options);
            });
        });

        $('#moreFilterToggle').unbind('click').click(function(){
            var fieldsMore = $('#fields-more');
            var isVisible = fieldsMore.is(":visible");
            if(isVisible){
                $(this).find("span").removeClass().text('更多筛选');
                fieldsMore.slideUp();
            }else{
                $(this).find("span").addClass('close').text('收起筛选');
                fieldsMore.slideDown();
            }
        });

        $('#clean-search-history').click(function(){
            WealinkMobile.Cookie.del($(this).jqmData('wlm-cookie'));
            window.location.reload();
        });
    })
    
    .delegate('#edit-qiuzhi', 'pageinit', function () {
        $('#private').find('li[data-wlm-filter-field]').each(function () {
            $(this).on('click', function () {
                var fieldName = $(this).jqmData('wlm-filter-field');
                var options = {'fieldName': fieldName, 'filter': $('#wlm-filter'), 'method':function(){setPrivate(fieldName);}};

                WealinkMobile.operate['single'].call(this, options);
            });
        });
    })
    .delegate('#zhaopin-zhiwei', 'pageinit', function () {
        $('#zhiwei-manage').find('li[data-wlm-pid]').live('click',function () {
            var pid = $(this).jqmData('wlm-pid');
            var type = $(this).jqmData('wlm-type');
            var options = {'pid': pid, 'filter': $('#wlm-filter'),'type':type};
            WealinkMobile.operate['single_for_zhiwei'].call(this, options);
        });
    })
    //add by tony.lu 选择多选
    .delegate('#zhaopin-gaiyao', 'pageinit', function () {
        $('#gaiyao-manage').find('li[data-wlm-filter-field]').each(function () {
            $(this).on('click', function () {
                var fieldName = $(this).jqmData('wlm-filter-field');
                var menuType = $(this).jqmData('wlm-filter-type');
                    menuType = menuType||'double';
                var num = Number($(this).jqmData('wlm-filter-num'));
		        num = isNaN(num) ? 5 : num;
		        var showbuxian = $(this).jqmData('wlm-filter-showbuxian');
                var options = {'fieldName': fieldName, 'num':num, 'filter': $('#wlm-filter'),'trigger':$(this),'showbuxian':showbuxian};

                WealinkMobile.filter[menuType].call(this, options);
            });
        });
    })
// -------- 求职档案页 --------；
    .delegate('#dangan-detail', 'pagebeforeshow', function(){
        $('.expandable-widget').each(function(){
            $(this).find('.widget-title').unbind('click').bind('click', function(){
                var widgetTitle = $(this);

                var widgetBody = widgetTitle.parent().siblings('.widget-body');
                if(widgetBody.is(":visible")){
                    widgetBody.slideUp(300, function(){widgetTitle.removeClass('downArrow').addClass('rightArrow')});
                }else{
                    widgetBody.slideDown(300, function(){widgetTitle.removeClass('rightArrow').addClass('downArrow')});
                }
            });
        });

        $('#btnDownloadResume').unbind('click').bind('click', function(){
            WealinkMobile.User.getContact($(this));
        });
    })
// -------- 招聘档案页 --------；
    .delegate("#bole-detail", 'pagebeforeshow', function(){
        // 加载伯乐其他职位；
        var baseUrl = WealinkMobile.baseUrl;
        WealinkMobile.ListWidget.init({
            'container':$('#zhiweiWidget'),
            'noResult':'Ta还没有在招职位哦！',
            'callback':function(data){
                var count = data.list.length;
                var html = "";
                for(var i=0; i<count; i++){
                    row = data.list[i];

                    html +='<li class="addBorder">' +
                        '<a data-ajax="false" href="'+baseUrl+'/zhiwei/view/'+row.pid+'/">' +
                        '<ul>' +
                        '<li><h3 class="jobName">' +row.title +'<i class="salary">'+row.salary+'</i></h3></li>' +
                        '<li><p class="tover">'+row.company_name+'</p></li>' +
                        '<li><h4>'+row.location+'<span class="data">'+row.publishDate+'</span></h4></li>' +
                        '</ul></a></li>';
                }

                return html;
            }
        });
    })
// -------- 求职、招聘档案页社交信息异步加载 --------；
    .delegate("#bole-detail, #dangan-detail, #zhiwei-detail", 'pagebeforeshow', function(){
        var baseUrl = WealinkMobile.baseUrl;
        WealinkMobile.ListWidget.init({
            'container':$('#concernsWidget'),
            'noResult':'Ta还没有关注的人哦！',
            'callback':function(data){
                var count = data.list.length;
                var html = "";
                for(var i=0; i<count; i++){
                    var row = data.list[i];
                    row = data.list[i];
                    var profileUrl = row.isDeleted ? 'javascript:void(0);':baseUrl+'/zpdangan/'+row.uid+'/';

                    html +=
                        '<li class="addBorder">' +
                            '<a data-ajax="false" href="'+profileUrl+'">' +
                            '<div>' +
                            '<img src="'+row.avatar+'" width="55" height="55" class="pic" />' +
                            '<ul style="height:60px">' +
                            '<li><h3>'+row.name_html+(row.last_position==''?'':'<span class="job">'+row.last_position+'</span>')+'</h3></li>' +
                            '<li><p class="oneLine">'+row.last_company+'</p></li>' +
                            '<li><p class="oneLine">'+row.region_name+'</p></li>' +
                            '</ul>' +
                            '</div>' +
                            '</a></li>';
                }

                return html;
            }
        });

        WealinkMobile.ListWidget.init({
            'container':$('#fansWidget'),
            'noResult':'Ta还没有粉丝哦！',
            'callback':function(data){
                var count = data.list.length;
                var html = "";
                for(var i=0; i<count; i++){
                    var row = data.list[i];
                    var profileUrl = row.isDeleted ? 'javascript:void(0);':baseUrl+'/zpdangan/'+row.uid+'/';
                    html +=
                        '<li class="addBorder">' +
                            '<a data-ajax="false" href="'+profileUrl+'">' +
                            '<div>' +
                            '<img src="'+row.avatar+'" width="55" height="55" class="pic" />' +
                            '<ul style="height:70px">' +
                            '<li><h3>'+row.name_html+(row.last_position==''?'':'<span class="job">'+row.last_position+'</span>')+'</h3></li>' +
                            '<li><p class="oneLine">'+row.last_company+'</p></li>' +
                            '<li><p class="oneLine">'+row.region_name+'</p></li>' +
                            '</ul>' +
                            '</div>' +
                            '</a></li>';
                }

                return html;
            }
        });

        WealinkMobile.ListWidget.init({
            'container':$('#commentsWidget'),
            'noResult':'Ta还没有收到评价哦！',
            'callback':function(data){
                var count = data.list.length;
                var html = "";
                for(var i=0; i<count; i++){
                    row = data.list[i];
                    html +=
                        '<li class="addBorder" style="min-height:70px;">' +
                            '<img src="'+row.avatar+'" width="55" height="55" class="pic" />' +
                            '<ul>' +
                            '<li><h3>'+row.name_html+'<span class="job">对Ta的评价</span></h3></li>' +
                            '<li><p class="showAll">'+row.content+'</p></li>' +
                            '</ul>' +
                            '</li>';
                }

                return html;
            }
        });

        $('a.wlm-operation').unbind().bind('click', function () {
            var operation = $(this).jqmData('wlm-operation');
            if (operation in WealinkMobile.User) WealinkMobile.User[operation]($(this));
        });
    })
    .delegate('#woyao', 'pageinit', function () {
        $('#createdianpingForm').find('li[data-wlm-filter-field]').each(function () {
            $(this).on('click', function () {
                var fieldName = $(this).jqmData('wlm-filter-field');
                var menuType = $(this).jqmData('wlm-filter-type');
                    menuType = menuType||'double';
                var options = {'fieldName': fieldName, 'filter': $('#wlm-filter'),'trigger':$(this),'showbuxian':false};
                WealinkMobile.filter[menuType].call(this, options);
            });
        });
        $('#createmianjingForm').find('li[data-wlm-filter-field]').each(function () {
            $(this).on('click', function () {
                var fieldName = $(this).jqmData('wlm-filter-field');
                var menuType = $(this).jqmData('wlm-filter-type');
                    menuType = menuType||'double';
                var options = {'fieldName': fieldName, 'filter': $('#wlm-filter'),'trigger':$(this),'year':true,'month':true};
                WealinkMobile.filter[menuType].call(this, options);
            });
        });
        
        $('.starRate').find('i').each(function () {
            $(this).on('vclick', function () {
                var star = $(this).jqmData('wlm-star-val');
				$('#starSpan').attr('class','star'+star);
				$('#rateStar').val(star);
            });
        });
    })
    .delegate('#dangan-dianping','pageinit', function(){
        $('#dianpingForm').find('li[data-wlm-filter-field]').each(function () {
            $(this).on('click', function () {
                var fieldName = $(this).jqmData('wlm-filter-field');
                var menuType = $(this).jqmData('wlm-filter-type');
                menuType = menuType||'double';
                var options = {'fieldName': fieldName, 'filter': $('#wlm-filter'),'trigger':$(this),'showbuxian':false};
                var customerDict = $(this).jqmData('wlm-dict');
                if(customerDict) {
                    var tmpDict = [];
                    var dictRows = customerDict.split(";");
                    for(var i =0; i<dictRows.length; i++){
                        tmpDict.push(dictRows[i].split('|'));
                    }

                    options.dict = tmpDict;
                }
                WealinkMobile.filter[menuType].call(this, options);
            });
        });

        $('#btnAddComment').unbind('click').click(function(){
            var relation_id = $('input[name=relation]').val();
            var content = $('textarea[name=content]').val();
            var uid = $('input[name=uid]').val();

            WealinkMobile.User.comment($(this),{'relation_id':relation_id,'content':content, 'uid':uid});
        });
    })
;

WealinkMobile.operate = {
    /**
     * 单层的选择；
     *
     * @param options
     */
    'single': function (options) {
        options.selected = Number($("input[name=" + options.fieldName + "]").val());
        options.selected = isNaN(options.selected) ? 0 : options.selected;
        options.selected = options.selected || 0;

        var DICT = WealinkMobile.dict;
        var mainData = DICT.get(options.fieldName, null);
        
        var mainHtml = '';
        var mainSelected= 0;

        options.filter.html("<ul class='slip3'></ul>");
        for(var i=0; i< mainData.length; i++){
            if(options.selected == mainData[i][0])
                mainSelected = options.selected;

            mainHtml += "<li data-wlm-filter-selectable='true' data-wlm-filter-value='" + mainData[i][0] + "' data-wlm-filter-item='" + options.fieldName + "-main-" + mainData[i][0] + "'>" + mainData[i][1] + "</li>";
        }
        options.filter
            .find('.slip3').html(mainHtml)
            .find('li').on('click', function () {
                WealinkMobile.operate.select(options, $(this));
            }).end()
            .find('li[data-wlm-filter-item='+options.fieldName+'-main-'+mainSelected+']').addClass('current');

        WealinkMobile.filter.show(options);
    },
    
    'single_for_zhiwei': function (options) {
        var mainHtml = '';
        var pid = options.pid;
        var type = options.type;
        if(type == 1)
        	return false;
        options.filter.html("<ul class='slip3'></ul>");
        if(type == 0){
        	mainHtml += "<li onclick=\"doOperation('"+pid+"','recommend')\">推荐(推荐一周)</li><li onclick=\"doOperation('"+pid+"','refresh')\">刷新</li><li onclick=\"doOperation('"+pid+"','close')\">停止</li><li onclick=\"window.location.href='http://m.wealink.com/zhiwei/view/"+pid+"/'\">查看详情</li>";
        }else if(type == 2){
        	mainHtml += "<li onclick=\"doOperation('"+pid+"','republish')\">重新发布</li><li onclick=\"doOperation('"+pid+"','delete')\">删除</li>";
        }
        options.filter.find('.slip3').html(mainHtml);
        WealinkMobile.filter.show(options);
    },
    
    /**
     * 选中；
     *
     * @param options
     * @param selectedObj
     */
    'select': function (options, selectedObj) {
        selectedObj.siblings().removeClass('current').end().addClass('current');
        var itemValue = selectedObj.jqmData('wlm-filter-value');
        var itemLabel = selectedObj.text();

        if (selectedObj.jqmData('wlm-filter-selectable')) {
            WealinkMobile.filter.hide(options);
            
            $('#label-' + options.fieldName).text(itemLabel);
            $('input[name=' + options.fieldName + ']').val(itemValue);
            options.method();
            return true;
        }
    }
};

function setPrivate(type){
	if(type == 'contact_visi'){
		var contact_visi = $('#contact_visi').val();
		dataobj = {'contact_visi':contact_visi,'type':type};
	}else if(type == 'job_status_visi'){
		var job_status_visi = $('#job_status_visi').val();
		dataobj = {'job_status_visi':job_status_visi,'type':type};
	}
	$.ajax({
		'url':'http://m.wealink.com/dangan/setprivateAjax/',
		'type':'post',
        'cache': false,
        'dataType': 'json',
		'data':dataobj,
		'success':function(data){
			if(data.flag == true){
				WealinkMobile.Popup.alert('保存成功','correct',2);
			}else{
				WealinkMobile.Popup.alert(data.msg,'warn',3);
				location.reload();
			}
		}
	})
}
function doOperation(pid,type){
	if(type == 'recommend'){
		_gaq.push(['_trackEvent', 'jobonline', 'wap_jobrecommend']);
	}else if(type == 'refresh'){
		_gaq.push(['_trackEvent', 'jobonline', 'wap_jobrefresh']);
	}else if(type == 'close'){
		_gaq.push(['_trackEvent', 'jobonline', 'wap_jobstop']);
	}
	var options = {'filter': $('#wlm-filter')};
	WealinkMobile.filter.hide(options);
    var dataObj = {'pid': pid, 'type': type};
    $.ajax({
        type: "GET",
        url: "http://www.wealink.com/user/zhiwei/doOperation/",
        data: dataObj,
        dataType: "jsonp",
        jsonp:'callback',
        cache:false,
        success: function (data) {
            if(data.flag == true){
            	if(type == 'republish' || type == 'close' || type == 'refresh'){
        			WealinkMobile.Popup.alert('操作成功','correct',2);
        			location.reload();
            	}else if(type == 'recommend'){
            		WealinkMobile.Popup.alert('推荐成功','correct',2);
            	}
            }else{
                WealinkMobile.Popup.alert(data.msg,'warn',3);
            }
        }
    });
}

function getTextLength(text){
    var len = 0;
    for (i = 0; i < text.length; i++) {
        if (text.charCodeAt(i) > 256) {
            len += 2;
        }
        else {
            len++;
        }
    }
    return len;
}

var imageReady = (function () {
    var list = [], intervalId = null,

    // 用来执行队列
        tick = function () {
            var i = 0;
            for (; i < list.length; i++) {
                list[i].end ? list.splice(i--, 1) : list[i]();
            }
            !list.length && stop();
        },

    // 停止所有定时器队列
        stop = function () {
            clearInterval(intervalId);
            intervalId = null;
        };

    return function (url, ready, load, error) {
        var onready, width, height, newWidth, newHeight,
            img = new Image();

        img.src = url;

        // 如果图片被缓存，则直接返回缓存数据
        if (img.complete) {
            ready.call(img);
            load && load.call(img);
            return;
        }

        width = img.width;
        height = img.height;

        // 加载错误后的事件
        img.onerror = function () {
            error && error.call(img);
            onready.end = true;
            img = img.onload = img.onerror = null;
        };

        // 图片尺寸就绪
        onready = function () {
            newWidth = img.width;
            newHeight = img.height;
            if (newWidth !== width || newHeight !== height ||
                // 如果图片已经在其他地方加载可使用面积检测
                newWidth * newHeight > 1024
                ) {
                ready.call(img);
                onready.end = true;
            }
        };
        onready();

        // 完全加载完毕的事件
        img.onload = function () {
            // onload在定时器时间差范围内可能比onready快
            // 这里进行检查并保证onready优先执行
            !onready.end && onready();

            load && load.call(img);

            // IE gif动画会循环执行onload，置空onload即可
            img = img.onload = img.onerror = null;
        };

        // 加入队列中定期执行
        if (!onready.end) {
            list.push(onready);
            // 无论何时只允许出现一个定时器，减少浏览器性能损耗
            if (intervalId === null) intervalId = setInterval(tick, 40);
        }
    };
})();
/**
 * Copyright (c) 2007-2014 Ariel Flesler - aflesler<a>gmail<d>com | http://flesler.blogspot.com
 * Licensed under MIT
 * @author Ariel Flesler
 * @version 1.4.11
 */
;(function(a){if(typeof define==='function'&&define.amd){define(['jquery'],a)}else{a(jQuery)}}(function($){var j=$.scrollTo=function(a,b,c){return $(window).scrollTo(a,b,c)};j.defaults={axis:'xy',duration:parseFloat($.fn.jquery)>=1.3?0:1,limit:true};j.window=function(a){return $(window)._scrollable()};$.fn._scrollable=function(){return this.map(function(){var a=this,isWin=!a.nodeName||$.inArray(a.nodeName.toLowerCase(),['iframe','#document','html','body'])!=-1;if(!isWin)return a;var b=(a.contentWindow||a).document||a.ownerDocument||a;return/webkit/i.test(navigator.userAgent)||b.compatMode=='BackCompat'?b.body:b.documentElement})};$.fn.scrollTo=function(f,g,h){if(typeof g=='object'){h=g;g=0}if(typeof h=='function')h={onAfter:h};if(f=='max')f=9e9;h=$.extend({},j.defaults,h);g=g||h.duration;h.queue=h.queue&&h.axis.length>1;if(h.queue)g/=2;h.offset=both(h.offset);h.over=both(h.over);return this._scrollable().each(function(){if(f==null)return;var d=this,$elem=$(d),targ=f,toff,attr={},win=$elem.is('html,body');switch(typeof targ){case'number':case'string':if(/^([+-]=?)?\d+(\.\d+)?(px|%)?$/.test(targ)){targ=both(targ);break}targ=$(targ,this);if(!targ.length)return;case'object':if(targ.is||targ.style)toff=(targ=$(targ)).offset()}var e=$.isFunction(h.offset)&&h.offset(d,targ)||h.offset;$.each(h.axis.split(''),function(i,a){var b=a=='x'?'Left':'Top',pos=b.toLowerCase(),key='scroll'+b,old=d[key],max=j.max(d,a);if(toff){attr[key]=toff[pos]+(win?0:old-$elem.offset()[pos]);if(h.margin){attr[key]-=parseInt(targ.css('margin'+b))||0;attr[key]-=parseInt(targ.css('border'+b+'Width'))||0}attr[key]+=e[pos]||0;if(h.over[pos])attr[key]+=targ[a=='x'?'width':'height']()*h.over[pos]}else{var c=targ[pos];attr[key]=c.slice&&c.slice(-1)=='%'?parseFloat(c)/100*max:c}if(h.limit&&/^\d+$/.test(attr[key]))attr[key]=attr[key]<=0?0:Math.min(attr[key],max);if(!i&&h.queue){if(old!=attr[key])animate(h.onAfterFirst);delete attr[key]}});animate(h.onAfter);function animate(a){$elem.animate(attr,g,h.easing,a&&function(){a.call(this,targ,h)})}}).end()};j.max=function(a,b){var c=b=='x'?'Width':'Height',scroll='scroll'+c;if(!$(a).is('html,body'))return a[scroll]-$(a)[c.toLowerCase()]();var d='client'+c,html=a.ownerDocument.documentElement,body=a.ownerDocument.body;return Math.max(html[scroll],body[scroll])-Math.min(html[d],body[d])};function both(a){return $.isFunction(a)||typeof a=='object'?a:{top:a,left:a}};return j}));
/**
 * add by GA
 */
var _gaq = _gaq || [];
_gaq.push(['_setAccount', 'UA-101112-13']);
_gaq.push(['_trackPageview']);
(function() {
var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
})();