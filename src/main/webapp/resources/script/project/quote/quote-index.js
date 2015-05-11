$(function() {
    $('.quote-search-div').find('.hidden-inline-xs').click(function(){
        $('.quote-search-div').find('#quote_search_engineeringCode').select2('val', '');
    });
    $('.quote-search-div').find('#quote_search_engineeringCode').select2({
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

    $(".grid-project-quote-quote-index").data("gridOptions", {
        url : WEB_ROOT + '/project/quote/findByPage.json',
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
            label : '报价编号',
            name : 'code',
            width : 60,
            editable: true,
            align : 'right'
        }, {
            label : '报价名称',
            name : 'name',
            width : 30,
            editable: true,
            align : 'left'
        }, {
            label : '报价描述',
            name : 'describes',
            width : 200,
            editable: true,
            align : 'left'
        }, {
            label : '报价人姓名',
            name : 'quoter',
            width : 30,
            editable: true,
            align : 'left'
        } ],
        editurl : WEB_ROOT + '/project/quote/save.json',
        delurl : WEB_ROOT + '/project/quote/delete.json',
        fullediturl : WEB_ROOT + '/project/quote/inputTabs.htm'
    });
});
