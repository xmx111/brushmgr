$(function() {
    $('.order-search-div').find('.hidden-inline-xs').click(function(){
        $('.order-search-div').find('#order_search_engineeringCode').select2('val', '');
    });
    $('.order-search-div').find('#order_search_engineeringCode').select2({
        openOnEnter: false,
//        placeholder: '请选择工程编号',
        width: '169px',
        selectOnBlur: true,
        formatSearching: function(){
            return '搜索中...';
        },
        ajax: {
            url: WEB_ROOT + '/project/contract/selectByCode.json',
            dataType: 'json',
            quietMillis: 250,
            data: function (term, page) { // page is the one-based page number tracked by Select2
                return {
                    engineeringCode: term, //search term
                    page: page // page number
                };
            },
            results: function (data, page) {
                if (!data.content){
                    return {results : {}};
                }
                var results = $.map(data.content, function(n) {
                    return {
                        id : n.engineeringCode,
                        name : n.engineeringCode + '-' + n.engineeringName
                    };
                });
                return {
                    results : results
                };
            }
        },
        formatResult: function (item) {
            return item.name;
        }, // 选择结果中的显示
        formatSelection: function (item) { return item.name; }//,  // 搜索列表中的显示
    });

    //$('.order-search-div').find('.date').datetimepicker({
    //    language: 'zh-cn',
    //    pickTime: false,
    //    format: "YYYY-MM-DD"
    //});

    $(".grid-engineering-order-order-index").data("gridOptions", {
        url : WEB_ROOT + '/engineering/order/findByPage.json',
        colModel : [ {
            label : '流水号',
            name : 'id',
            hidden : true                          
        }, {
            label : '工程编号',
            name : 'contract.engineeringCode',
            width : 30,
            editable: true,
            align : 'left'
        }, {
            label : '工程名称',
            name : 'contract.engineeringName',
            width : 30,
            editable: true,
            align : 'left'
        }, {
            label : '订单编号',
            name : 'code',
            width : 20,
            editable: true,
            align : 'left'
        }, {
            label : '订单名称',
            name : 'name',
            width : 40,
            editable: true,
            align : 'left'
        }, {
            label : '订单时间',
            name : 'time',
            width : 40,
            editable: true,
            align : 'left'
        }, {
            label : '订单状态',
            name : 'status',
            formatter : 'select',
            searchoptions : {
                value : Util.getCacheEnumsByType('OrderStatusEnum')
            },
            width : 30,
            editable: true,
            align : 'center'
        }, {
            label : '供货商名称',
            name : 'supplier.name',
            width : 40,
            editable: true,
            align : 'left'
        }, {
            label : '供货商联系电话',
            name : 'supplier.tel',
            width : 30,
            editable: true,
            align : 'left'
        }, {
            label : '操作',
            name : 'opt',
            formatter : function(cellValue, options, rowdata, action) {
                var urldetail = WEB_ROOT + '/engineering/order/view.htm?id='+rowdata.id;
                var result = '<a data-toggle="dynamic-tab" data-url="'+urldetail+'"  href="javascript:;" title="订单详情"><button type="button" class="btn btn-primary">订单详情</button></a>&nbsp;';
                return result;
            },
            width : 30,
            editable: false,
            sortable: false,
            align : 'left'
        } ],
        editurl : WEB_ROOT + '/engineering/order/save.json',
        delurl : WEB_ROOT + '/engineering/order/delete.json',
        fullediturl : WEB_ROOT + '/engineering/order/inputTabs.htm'
    });
});
