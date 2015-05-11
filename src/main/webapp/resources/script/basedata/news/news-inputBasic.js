$(function() {
    $(".form-basedata-news-news-inputBasic").data("formOptions", {
        bindEvents : function() {
            var $form = $(this);
        }
    });
    Tools.EnumSelectList('TypeEnum', '.form-basedata-news-news-inputBasic #type', '.form-basedata-news-news-inputBasic #typeKey');
});