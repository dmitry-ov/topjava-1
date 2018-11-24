var ajaxUrl = "ajax/admin/users/";
var changeUrl = "changeEnable";

var datatableApi;

function changeEnableState(id) {
    $.ajax({
        url: ajaxUrl + changeUrl + "/" + id,
        type: "POST",
        success: function (data) {
            let selector = '.' + id;
            if (data == true) {
                $(selector).css('background-color', 'blue');
            } else {
                $(selector).css('background-color', '');
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