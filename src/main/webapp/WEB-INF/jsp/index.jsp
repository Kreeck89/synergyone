<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <meta name="_csrf" content="${_csrf.token}"/>
    <!-- default header name is X-CSRF-TOKEN -->
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <script>
        $(function () {
            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");
            $(document).ajaxSend(function(e, xhr, options) {
                xhr.setRequestHeader(header, token);
            });
        });

        $(document).ready(function () {
            getAll();

            function getAll() {
                $.ajax({
                    type: 'GET',
                    url: 'http://localhost:8080/client/all',
                    success: function (orders) {
                        var createdUL = document.getElementById('ulId0');
                        if (createdUL === null) {
                            $.each(orders, function (i, data) {
                                var ul = document.createElement('ul');
                                var li1 = document.createElement('li');
                                var li2 = document.createElement('li');
                                var li3 = document.createElement('li');
                                var li4 = document.createElement('li');
                                var li5 = document.createElement('li');
                                var li6 = document.createElement('li');
                                ul.setAttribute('id', 'ulId' + i);
                                li1.setAttribute('id', 'id' + i);
                                li2.setAttribute('id', 'name' + i);
                                li3.setAttribute('id', 'surname' + i);
                                li4.setAttribute('id', 'birthday' + i);
                                li5.setAttribute('id', 'sex' + i);
                                li6.setAttribute('id', 'ITN' + i);
                                ul.appendChild(li1);
                                ul.appendChild(li2);
                                ul.appendChild(li3);
                                ul.appendChild(li4);
                                ul.appendChild(li5);
                                ul.appendChild(li6);
                                var div = document.getElementById('clients');
                                div.appendChild(ul);
                                $('#id' + i).text(data.id);
                                $('#name' + i).text(data.name);
                                $('#surname' + i).text(data.surname);
                                $('#birthday' + i).text(data.birthday);
                                $('#ITN' + i).text(data.itn);
                                $('#sex' + i).text(data.sex);
                            });
                        } else {
                            $.each(orders, function (i, data) {
                                $('#id' + i).text(data.id);
                                $('#name' + i).text(data.name);
                                $('#surname' + i).text(data.surname);
                                $('#birthday' + i).text(data.birthday);
                                $('#ITN' + i).text(data.itn);
                                $('#sex' + i).text(data.sex);
                            });
                        }
                    }
                });
            }

            $('#clientIdSubmit').on('click', function (event) {
                event.preventDefault();
                $.ajax({
                    type: 'GET',
                    url: 'http://localhost:8080/client/getById/' + $('#clientId').val(),
                    success: function (data) {
                        // var updateById = document.getElementById('updateById');
                        // if (updateById === null) {
                        var inputId = document.createElement('input');
                        var inputName = document.createElement('input');
                        var inputSurname = document.createElement('input');
                        var inputBirthday = document.createElement('input');
                        var inputSex = document.createElement('input');
                        var inputItn = document.createElement('input');
                        inputId.setAttribute('id', 'inputId');
                        inputId.setAttribute('editable', false);
                        inputName.setAttribute('id', 'inputName');
                        inputSurname.setAttribute('id', 'inputSurname');
                        inputBirthday.setAttribute('id', 'inputBirthday');
                        inputSex.setAttribute('id', 'inputSex');
                        inputItn.setAttribute('id', 'inputItn');
                        var div = document.getElementById('clientById');
                        div.appendChild(inputId);
                        div.appendChild(inputName);
                        div.appendChild(inputSurname);
                        div.appendChild(inputBirthday);
                        div.appendChild(inputSex);
                        div.appendChild(inputItn);
                        // }
                        $('#inputId').val(data.id);
                        $('#inputName').val(data.name);
                        $('#inputSurname').val(data.surname);
                        $('#inputBirthday').val(data.birthday);
                        $('#inputSex').val(data.sex);
                        $('#inputItn').val(data.itn);
                    }
                });
            });

            $('#updateById').on('click', function (event) {
                event.preventDefault();

                var updatedClient = {
                    // id: $("#inputId").val(),
                    name: $("#inputName").val(),
                    surname: $("#inputSurname").val(),
                    birthday: $("#inputBirthday").val(),
                    sex: $("#inputSex").val(),
                    itn: $("#inputItn").val()
                };
                $.ajax({
                    type: 'PUT',
                    url: 'http://localhost:8080/client/updateClient/' + $("#inputId").val(),
                    dataType: 'json',
                    contentType: "application/json; charset=utf-8",
                    data: JSON.stringify(updatedClient),
                    success: function (data) {
                        getAll();
                    }
                });

            });


            $('#createSubmit').on('click', function (event) {
                event.preventDefault();
                var newClient = JSON.stringify({
                    name: $("#clientName").val(),
                    surname: $("#clientSurname").val(),
                    birthday: $("#clientBirthday").val(),
                    sex: $("#clientSex").val(),
                    itn: $("#clientItn").val()
                });
                $.ajax({
                    type: 'POST',
                    url: 'http://localhost:8080/client/createNew',
                    dataType: 'json',
                    contentType: "application/json; charset=utf-8",
                    data: newClient
                });
                location.reload();
                // getAll();
            });

            $.ajax({
                type: 'GET',
                url: 'http://localhost:8080/order/all',
                success: function (orders) {
                    $.each(orders, function (i, data) {
                        var orderUlAll = document.createElement('ul');
                        var orderIdAll = document.createElement('li');
                        var orderDateAll = document.createElement('li');
                        var orderStatusAll = document.createElement('li');
                        var orderStatusAllInput = document.createElement('input');
                        var orderStatusAllSubmit = document.createElement('input');
                        var orderValueAll = document.createElement('li');
                        var orderCurrencyAll = document.createElement('li');
                        var orderClientIdAll = document.createElement('li');

                        orderUlAll.setAttribute('id', 'orderUlAll' + i);
                        orderIdAll.setAttribute('id', 'orderIdAll' + i);
                        orderDateAll.setAttribute('id', 'orderDateAll' + i);
                        orderStatusAllInput.setAttribute('id', 'orderStatusAllInput' + i);
                        orderStatusAllSubmit.setAttribute('id', 'orderStatusAllSubmit' + i);
                        orderStatusAllSubmit.setAttribute('class', 'orderStatusAllSubmit');

                        orderStatusAllSubmit.setAttribute('onClick', 'changeStatusOrder(' + i + ')');

                        orderStatusAllSubmit.setAttribute('type', 'submit');
                        orderStatusAllInput.setAttribute('placeholder', 'inProcessing,confirmed,accomplished');
                        orderStatusAll.setAttribute('id', 'orderStatusAll' + i);
                        orderValueAll.setAttribute('id', 'orderValueAll' + i);
                        orderCurrencyAll.setAttribute('id', 'orderCurrencyAll' + i);
                        orderClientIdAll.setAttribute('id', 'orderClientIdAll' + i);

                        orderStatusAll.appendChild(orderStatusAllInput);
                        orderStatusAll.appendChild(orderStatusAllSubmit);

                        orderUlAll.appendChild(orderIdAll);
                        orderUlAll.appendChild(orderDateAll);
                        orderUlAll.appendChild(orderStatusAll);
                        orderUlAll.appendChild(orderValueAll);
                        orderUlAll.appendChild(orderCurrencyAll);
                        orderUlAll.appendChild(orderClientIdAll);

                        var allOrderListDiv = document.getElementById('allOrderList');
                        allOrderListDiv.appendChild(orderUlAll);

                        $('#orderIdAll' + i).append(data.id);
                        $('#orderDateAll' + i).append(data.date);
                        $('#orderStatusAllInput' + i).val(data.status);
                        $('#orderValueAll' + i).append(data.value);
                        $('#orderCurrencyAll' + i).append(data.currency);
                        $('#orderClientIdAll' + i).append(data.client.id);
                    });
                }
            });

            $('#ordersByClientIdSubmit').on('click', function (event) {
                event.preventDefault();

                $.ajax({
                    type: 'GET',
                    url: 'http://localhost:8080/order/byClientId/' + $("#ordersByClientId").val(),
                    success: function (orders) {
                        $.each(orders, function (i, data) {
                            var orderUl = document.createElement('ul');
                            var orderId = document.createElement('li');
                            var orderDate = document.createElement('li');
                            var orderStatus = document.createElement('li');
                            var orderValue = document.createElement('li');
                            var orderCurrency = document.createElement('li');
                            var orderClientId = document.createElement('li');

                            orderUl.setAttribute('id', 'orderUl' + i);
                            orderId.setAttribute('id', 'orderId' + i);
                            orderDate.setAttribute('id', 'orderDate' + i);
                            orderStatus.setAttribute('id', 'orderStatus' + i);
                            orderValue.setAttribute('id', 'orderValue' + i);
                            orderCurrency.setAttribute('id', 'orderCurrency' + i);
                            orderClientId.setAttribute('id', 'orderClientId' + i);

                            orderUl.appendChild(orderId);
                            orderUl.appendChild(orderDate);
                            orderUl.appendChild(orderStatus);
                            orderUl.appendChild(orderValue);
                            orderUl.appendChild(orderCurrency);
                            orderUl.appendChild(orderClientId);
                            var ordersByIdList = document.getElementById('ordersByIdList');
                            ordersByIdList.appendChild(orderUl);

                            $('#orderId' + i).append(data.id);
                            $('#orderDate' + i).append(data.date);
                            $('#orderStatus' + i).append(data.status);
                            $('#orderValue' + i).append(data.value);
                            $('#orderCurrency' + i).append(data.currency);
                            $('#orderClientId' + i).append(data.clientId);
                        });
                    }
                });
            });

            $('#createOrder').on('click', function (event) {
                event.preventDefault();

                var newOrder = JSON.stringify({
                    date: $("#orderDate").val(),
                    status: $("#orderStatus").val(),
                    value: $("#orderValue").val(),
                    currency: $("#orderCurrency").val(),
                    client: {
                        id: $("#orderClientId").val()
                    }
                });
                $.ajax({
                    type: 'POST',
                    url: 'http://localhost:8080/order/createNew',
                    dataType: 'json',
                    contentType: "application/json; charset=utf-8",
                    data: newOrder
                });
                location.reload();
                // getAll();
            });

            // $(".orderStatusAllSubmit").on("click", function(event) {
            //     var currentDel = event.target.id;
            //
            //     console.log("Hello");
            //     if(currentDel.indexOf('delete') + 1) {
            //         var currentDel = event.target;
            //         var updateOrder = JSON.stringify({
            //             id: $('orderIdAll' + currentDel).val(),
            //             date: $('orderDateAll' + currentDel).val(),
            //             status: $('orderStatusAllInput' + currentDel).val(),
            //             value: $('orderValueAll' + currentDel).val(),
            //             currency: $('orderCurrencyAll' + currentDel).val(),
            //             client: $('orderClientIdAll' + currentDel).val()
            //         });
            //     }
            //         $.ajax({
            //             type: 'PATCH',
            //             url: 'http://localhost:8080/order/updateStatus',
            //             dataType: 'json',
            //             contentType: "application/json; charset=utf-8",
            //             data: updateOrder
            //         });
            //         // location.reload();
            //         // getAll();
            //
            // });

        });

        function changeStatusOrder(i) {
            console.log("i: " + i);
            // var current = event.target.id;

            // if(current.indexOf('orderStatusAllSubmit')) {
            //     var currentDel = event.target;
            var updateOrder = JSON.stringify({
                id: document.getElementById('orderIdAll' + i).innerHTML,
                date: document.getElementById('orderDateAll' + i).innerHTML,
                status: document.getElementById('orderStatusAllInput' + i).value,
                value: document.getElementById('orderValueAll' + i).innerHTML,
                currency: document.getElementById('orderCurrencyAll' + i).innerHTML,
                client: {
                    id: document.getElementById('orderClientIdAll' + i).innerHTML
                }
                // id: $('#orderIdAll' + i).val(),
                // date: $('#orderDateAll' + i).val(),
                // status: $('#orderStatusAllInput' + i).val(),
                // value: $('#orderValueAll' + i).val(),
                // currency: $('#orderCurrencyAll' + i).val(),
                // client: {
                //     id: $('#orderClientIdAll' + i).val()
                // }
            });
            console.log(document.getElementById('orderStatusAllInput' + i).value);

            // }
            $.ajax({
                type: 'PATCH',
                url: 'http://localhost:8080/order/updateStatus',
                dataType: 'json',
                contentType: "application/json; charset=utf-8",
                data: updateOrder
            });
            // location.reload();
            // getAll();

        };



    </script>
</head>
<body>
<div class="client" id="clients">
    <H2>All client list:</H2>
</div>

<form method="get">
    <h2>For found client by id enter and push submit:</h2>
    <input type="number" id="clientId" placeholder="enter client id">
    <input type="submit" value="submit" id="clientIdSubmit">
</form>

<form method="put">
    <div id="clientById">
        <input type="submit" id="updateById">
    </div>
</form>

<form method="post">
    <h2>For save new client enter data and push submit:</h2>
    <input type="text" id="clientName" placeholder="name">
    <input type="text" id="clientSurname" placeholder="surname">
    <input type="date" id="clientBirthday" placeholder="birthday">
    <input type="text" id="clientSex" placeholder="sex(MAN/WOMAN)">
    <input type="number" id="clientItn" placeholder="ITN">
    <input type="submit" id="createSubmit" value="submit">
</form>

<div id="allOrderList">
    <h2>All order list:</h2>
</div>

<form method="get">
    <h2>For found orders by client id enter and push submit:</h2>
    <input type="number" id="ordersByClientId" placeholder="enter client id">
    <input type="submit" value="submit" id="ordersByClientIdSubmit">
</form>

<div id="ordersByIdList">

</div>

<form method="post">
    <h2>For save new order enter data and push submit:</h2>
    <input type="date" id="orderDate" placeholder="date">
    <input type="text" id="orderStatus" placeholder="status(inProcessing,confirmed,accomplished)">
    <input type="number" id="orderValue" placeholder="value">
    <input type="text" id="orderCurrency" placeholder="currency(UAH,USD,EUR)">
    <input type="number" id="orderClientId" placeholder="clientId">
    <input type="submit" id="createOrder" value="submit">
</form>
</body>
</html>
