var ajaxUrl = "/ajax/profile/meals/";
var filter = "filter/";

var datatableApi;

$("#filter").submit(function () {
    $.ajax({
        url: ajaxUrl + filter,
        type: "POST",
        data: $("#filter").serialize(),
        success: function (data) {
            // updateTable();
            successNoty("Filtered");
        }
    });
});

$(function () {
    datatableApi = $("#datatable").DataTable({
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "dateTime"
            },
            {
                "data": "description"
            },
            {
                "data": "calories"
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
                "desc"
            ]
        ]
    });
    makeEditable();
});