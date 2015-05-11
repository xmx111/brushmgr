$(function() {
    $(".form-ucenter-employee-employee-inputBasic").data("formOptions", {
        bindEvents : function() {
            var $form = $(this);

            $('.date').datetimepicker({
                language:'zh-cn',
                pickTime: false,
                format:"YYYY-MM-DD"
            });

            $form.find('#image_url').on('click', function(){
                $form.closest('div').find('#upload-employee').prev().find('.ke-upload-file').trigger('click');
            });
            var uploadbutton = KindEditor.uploadbutton({
                button : $('#upload-employee')[0],
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
    Tools.EnumSelectList('SexEnum', '.form-ucenter-employee-employee-inputBasic #sex', '.form-ucenter-employee-employee-inputBasic #sexKey');
    Tools.EnumSelectList('EmployeeEnum', '.form-ucenter-employee-employee-inputBasic #type', '.form-ucenter-employee-employee-inputBasic #typeKey');
    Tools.EnumSelectList('StationEnum', '.form-ucenter-employee-employee-inputBasic #station', '.form-ucenter-employee-employee-inputBasic #stationKey');
    Tools.EnumSelectList('EmployeeStatusEnum', '.form-ucenter-employee-employee-inputBasic #status', '.form-ucenter-employee-employee-inputBasic #statusKey');
//    Tools.EnumUnitList('SexEnum', 'sex');
//    Tools.EnumUnitList('EmployeeEnum', 'type');
//    Tools.EnumUnitList('StationEnum', 'station');
//    Tools.EnumUnitList('EmployeeStatusEnum', 'status');
});