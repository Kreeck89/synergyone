console.log("sdsfsgdfs");
$(document).ready(function() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/orders/all',
        success: function (orders) {
            $.each(orders, function (i, data) {
                $('#id').append(data.id);
                $('#date').append(data.date);
                $('#status').append(data.status);
                $('#value').append(data.value);
                $('#currency').append(data.currency);
                $('#client').append(data.client);
            });
        }
    });
}