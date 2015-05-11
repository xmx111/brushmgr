$(function() {
    $('.form-project-drawing-drawing-inputBasic').data('formOptions', {
        bindEvents : function() {
            var $form = $(this);
            $('.date').datetimepicker({
                language:'zh-cn',
                format:"YYYY-MM-DD HH:mm:ss",
                showToday: true
            });
            $form.find('.contract-select').click(function() {
                $(this).popupDialog({
                    url : WEB_ROOT + '/project/contract/select.htm',
                    callback : function(rowdata) {
                        $form.find('#engineering_code').val(rowdata.engineeringCode).attr('readonly','readonly');
                        $form.find('#engineering_name').val(rowdata.engineeringName);
                        $form.find('#contract_id').val(rowdata.id);
                    }
                })
            });
            $form.find('.max-version').on('click', function(){
                var cid = $form.find('#contract_id').val();
                var type = $form.find('#type').val();
                if (cid == ''){
                    Global.notify('error','请先选择工程');
                    return false;
                }
                if (type == ''){
                    Global.notify('error','请先选择图纸类别');
                    return false;
                }
                $.ajax({
                    type: 'POST',
                    cache: false,
                    url: WEB_ROOT+'/project/drawing/getMaxVersion.json',
                    data: {id : cid, type : type},
                    dataType: 'json',
                    success: function (res) {
                        if (res == 0){
                            Global.notify('error','请先选择工程');
                        } else {
                            $form.find('#version').val(res);
                        }

                    }
                });
            });
            $form.find('.max-code').on('click', function(){
                var cid = $form.find('#contract_id').val();
                var type = $form.find('#type').val();
                var version = $form.find('#version').val();
                if (cid == ''){
                    Global.notify('error','请先选择工程');
                    return false;
                }
                if (type == ''){
                    Global.notify('error','请先选择图纸类别');
                    return false;
                }
                if (version == ''){
                    Global.notify('error','请先选择图纸版本');
                    return false;
                }
                $.ajax({
                    type: 'POST',
                    cache: false,
                    url: WEB_ROOT+'/project/drawing/getMaxCode.json',
                    data: {contractId : cid, type : type, version : version},
                    dataType: 'json',
                    success: function (res) {
                        if (res == 0){
                            Global.notify('error','请先选择相关数据');
                        } else {
                            $form.find('#code').val(res);
                        }
                    }
                });
            });
            $form.find('#drawing_url').on('click', function(){
                $form.closest('div').find('#upload-drawing').prev().find('.ke-upload-file').trigger('click');
            });
            var uploadbutton = KindEditor.uploadbutton({
                button : $('#upload-drawing')[0],
                fieldName : 'drawingFile',
                url : WEB_ROOT + '/upload.json',
                afterUpload : function(data) {
                    $form.find('#drawing_url').attr('src', data.url);
                    $form.find('#url').val(data.url);
                }
            });
            uploadbutton.fileBox.change(function(e) {
                uploadbutton.submit();
            });
        }
    });
    Tools.EnumSelectList('DrawingEnum', '.form-project-drawing-drawing-inputBasic #type', '.form-project-drawing-drawing-inputBasic #typeKey');
    Tools.EnumUnitList('DrawingEnum', 'type');
});