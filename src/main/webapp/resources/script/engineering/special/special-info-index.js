$(function() {
    $('.special-search-div').find('.hidden-inline-xs').click(function(){
        $('.special-search-div').find('#special_search_engineeringCode').select2('val', '');
    });
    $('.special-search-div').find('#special_search_engineeringCode').select2({
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

    $(".grid-engineering-special-special-info-index").data("gridOptions", {
        url : WEB_ROOT + '/engineering/special/findByPage.json',
        colModel : [ {
            label : '流水号',
            name : 'id',
            hidden : true                          
        }, {
            label : '工程编号',
            name : 'contract.engineeringCode',
            width : 40,
            editable: true,
            align : 'left'
        }, {
            label : '工程名称',
            name : 'contract.engineeringName',
            width : 50,
            editable: true,
            align : 'left'
        }, {
            label : '采集时间',
            name : 'createTime',
            width : 50,
            editable: true,
            align : 'left'
        }, {
            label : '采集人账号',
            name : 'collectorLoginName',
            width : 50,
            editable: true,
            align : 'left'
        }, {
            label : '采集人姓名',
            name : 'collectorName',
            width : 50,
            editable: true,
            align : 'left'
        }, {
            label : '文字描述',
            name : 'describes',
            width : 150,
            editable: true,
            align : 'left'
        }, {
            label : '操作',
            name : 'opt',
            formatter : function(cellValue, options, rowdata, action) {
                var urldetail = WEB_ROOT + '/engineering/special/details.htm?id='+rowdata.id;
                var result = '<a data-toggle="dynamic-tab" data-url="'+urldetail+'"  href="javascript:;" title="查看详情"><button type="button" class="btn btn-primary">查看详情</button></a>&nbsp;';
                return result;
            },
            width : 45,
            editable: false,
            sortable: false,
            align : 'left'
        } ],
        delurl : WEB_ROOT + '/engineering/special/delete.json'
    });
});
