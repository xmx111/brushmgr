$(function() {
    $(".form-project-contract-contract-inputBasic").data("formOptions", {
        bindEvents : function() {
            var $form = $(this);
            $form.find(".custom-select").click(function() {
                $(this).popupDialog({
                    url : WEB_ROOT + '/ucenter/custom/select.htm',
                    callback : function(rowdata) {
                        $form.find("#custom_code").val(rowdata.code).attr("readonly","readonly");
                        $form.find("#custom_name").val(rowdata.name).attr("readonly","readonly");
                        $form.find("#custom_phone").val(rowdata.phone);
                        $form.find("#custom_id").val(rowdata.id);
                    }
                })
            });
            $form.find(".employee-select").click(function() {
                $(this).popupDialog({
                    url : WEB_ROOT + '/ucenter/employee/select.htm',
                    callback : function(rowdata) {
                        $form.find("#employee_name").val(rowdata.name).attr("readonly","readonly");
                        $form.find("#employee_id").val(rowdata.id);
                    }
                })
            });
        }
    });
    Tools.EnumSelectList('ContractEnum', '.form-project-contract-contract-inputBasic #type', '.form-project-contract-contract-inputBasic #typeKey');
});