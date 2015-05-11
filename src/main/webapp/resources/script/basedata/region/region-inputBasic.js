$(function() {
    $(".form-basedata-region-region-inputBasic").data("formOptions", {
        bindEvents : function() {
            var $form = $(this);
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
});