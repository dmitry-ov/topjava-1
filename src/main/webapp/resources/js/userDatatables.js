var ajaxUrl = "ajax/admin/users/";
var changeUrl = "changeEnable";

var datatableApi;

function changeEnableState(id) {
    $.ajax({
        url: ajaxUrl + changeUrl + "/" + id,
        type: "POST",
        success: function (data) {
            let selectorTh = '.' + id;
            let selectorCheckBox = '#' + id;
            if (data == true) {
                $(selectorTh).css('background-color', '');
                $(selectorCheckBox).prop('checked', true);
            } else {
                $(selectorTh).css('background-color', 'yellow');
                $(selectorCheckBox).prop('checked', false);
            }
        }
    });
}

// $(document).ready(function () {
$(function () {
    datatableApi = $("#datatable").DataTable({
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "name"
            },
            {
                "data": "email"
            },
            {
                "data": "roles"
            },
            {
                "data": "enabled"
            },
            {
                "data": "registered"
            },
            {
                "defaultContent": "Edit",
                "orderable": false
            },
            {
                "defaultContent": "Delete",
                "orderable": false
            }
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ]
    });
    makeEditable();
});