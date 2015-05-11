$(function() {
    $(".form-ucenter-custom-custom-inputBasic").data("formOptions", {
        bindEvents : function() {
            var $form = $(this);
            $form.find('.region-select').click(function() {
                $(this).popupDialog({
                    url : WEB_ROOT + '/basedata/region/select.htm',
                    callback : function(rowdata) {
                        $form.find('#region_name').val(rowdata.name);
                        $form.find('#region_id').val(rowdata.id);
                    }
                })
            });
            $form.find('#image_url').on('click', function(){
                $form.closest('div').find('#upload-custom').prev().find('.ke-upload-file').trigger('click');
            });
            var uploadbutton = KindEditor.uploadbutton({
                button : $('#upload-custom')[0],
                fieldName : 'headFile',
                url : WEB_ROOT + '/upload.json',
                afterUpload : function(data) {
                    $form.find('#image_url').attr('src', data.url);
                    $form.find('#headUrl').val(data.url);
                }
            });
            uploadbutton.fileBox.change(function(e) {
                uploadbutton.submit();
            });
        }
    });
    Tools.EnumSelectList('SexEnum', '.form-ucenter-custom-custom-inputBasic #sex', '.form-ucenter-custom-custom-inputBasic #sexKey');
//    Tools.EnumUnitList('SexEnum', 'sex');
});