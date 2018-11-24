var ajaxUrl = "/ajax/profile/meals/";
var filter = "filter/";

var datatableApi;

function filterMeals() {
    $.ajax({
        url: ajaxUrl + filter,
        type: "POST",
        data: $("#filter").serialize(),
        success: function (data) {
            datatableApi.clear().rows.add(data).draw();
            successNoty("Filtered");
        }
    });
}

function filterReset(){
    $("#filter").find(":input").val("");
}

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