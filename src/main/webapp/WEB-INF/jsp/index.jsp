<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <script>
        $(document).ready(function() {
            $.ajax({
                type: 'GET',
                url: 'http://localhost:8080/order/all',
                success: function (orders) {
                    $.each(orders, function (i, data) {
                        console.log(data);
                        $('#id').append(data.id);
                        $('#date').append(data.date);
                        $('#status').append(data.status);
                        $('#value').append(data.value);
                        $('#currency').append(data.currency);
                        $('#client').append(data.client.id);
                    });
                }
            });
            $.ajax({
                type: 'GET',
                url: 'http://localhost:8080/client/all',
                success: function (orders) {
                    $.each(orders, function (i, data) {
                        var ul = document.createElement('ul');
                        var li1 = document.createElement('li');
                        var li2 = document.createElement('li');
                        var li3 = document.createElement('li');
                        var li4 = document.createElement('li');
                        var li5 = document.createElement('li');
                        var li6 = document.createElement('li');
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
                        $('#id'+ i).append(data.id);
                        $('#name' + i).append(data.name);
                        $('#surname' + i).append(data.surname);
                        $('#birthday' + i).append(data.birthday);
                        $('#ITN' + i).append(data.itn);
                        $('#sex' + i).append(data.sex);
                    });
                }
            });
        })

        $('#clientIdSubmit').on('click', function (event) {
            event.preventDefault();
            $.ajax({
                type: 'POST',
                url: 'http://localhost:8080/client/getById',
                data: {
                    id: $('#clientId').value
                },
                success: function (data) {
                        var ul = document.createElement('ul');
                        var li = document.createElement('li');
                        li.setAttribute('id', 'id' + i);
                        ul.appendChild(li);
                        var div = document.getElementById('clientById');
                        div.appendChild(ul);
                        $('#id'+ i).append(data.id);
                        $('#name' + i).append(data.name);
                        $('#surname' + i).append(data.surname);
                        $('#birthday' + i).append(data.birthday);
                        $('#ITN' + i).append(data.itn);
                        $('#sex' + i).append(data.sex);
                }
            });
        })
    </script>
</head>
<body>
<div class="client" id="clients">
</div>
<div id="clientById">
</div>
<p id="id"></p>
<p id="date"></p>
<p id="status"></p>
<p id="value"></p>
<p id="currency"></p>
<p id="client"></p>
<form method="post">
<input type="number" id="clientId">
    <input type="submit" value="submit" id="clientIdSubmit">
</form>
</body>
</html>
