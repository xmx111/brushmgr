$(function() {
    $('.engineering-info-search-div').find('.hidden-inline-xs').click(function(){
        $('.engineering-info-search-div').find('#engi_info_search_engineeringCode').select2('val', '');
    });
    $('.engineering-info-search-div').find('#engi_info_search_engineeringCode').select2({
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

    $(".grid-project-engineering-engineering-info-index").data("gridOptions", {
        url : WEB_ROOT + '/project/engineering/engineeringinfo/findByPage.json',
        colModel : [ {
            label : '流水号',
            name : 'id',
            hidden : true                          
        }, {
            label : '工程编号',
            name : 'contract.engineeringCode',
            width : 80,
            editable: true,
            align : 'left'
        }, {
            label : '工程名称',
            name : 'contract.engineeringName',
            width : 100,
            editable: true,
            align : 'left'
        }, {
            label : '编辑状态',
            name : 'type',
            formatter : 'select',
            searchoptions : {
                value : Util.getCacheEnumsByType('EditTypeEnum')
            },
            width : 80,
            editable: true,
            align : 'center'
        }, {
            label : '编辑时间',
            name : 'updateTime',
            width : 100,
            editable: true,
            align : 'left'
        }, {
            label : '编辑操作员姓名',
            name : 'editorName',
            width : 100,
            editable: true,
            align : 'left'
//        }, {
//            label : '操作',
//            width : 100,
//            editable: true,
//            align : 'center',
//            formatter : function(cellValue, options, rowdata, action) {
//                var url = WEB_ROOT + '/project/engineering/engineeringinfo/viewBasic.htm?id=' + rowdata.id;
//                return '<a href="' + url + '" data-toggle="modal-ajaxify" title="工程关联信息预览"><button class="btn btn-info">预览</button></a>';
//            }
        } ],
        editurl : WEB_ROOT + '/project/engineering/engineeringinfo/save.json',
        delurl : WEB_ROOT + '/project/engineering/engineeringinfo/delete.json',
        fullediturl : WEB_ROOT + '/project/engineering/engineeringinfo/inputTabs.htm'
    });
});
