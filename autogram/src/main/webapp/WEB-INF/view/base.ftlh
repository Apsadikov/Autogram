<#macro extends title style js="">
<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <meta charset="UTF-8">
    <title>${title}</title>
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="/static/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" type="text/css" href="/static/css/base.css">
    <link rel="stylesheet" type="text/css" href="/static/css/${style}.css">
</head>
<body>
<div class="container no-gutters menu">
    <div class="row no-gutters">
        <#if Session["token"]??>
            <div class="col-2 menu-item right-border">Лента</div>
            <div class="col-2 menu-item right-border">Сообщения</div>
            <div class="col-4 menu-item right-border">Профиль</div>
            <div class="col-2 menu-item right-border">О сайте</div>
            <div class="col-2"><a href="http://localhost:8080/logout" class="menu-item">Выйти</a></div>
        <#else>
            <div class="col-6 menu-item right-border">О сайте</div>
            <div class="col-6"><a href="http://localhost:8080/login" class="menu-item">Вход</a></div>
        </#if>
    </div>
</div>
<div class="container content">
    <#nested>
</div>
<div class="container footer">
    КФУ Высшая школа ИТИС<br>
    ©Autogram
</div>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
        integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
        crossorigin="anonymous"></script>
<#if js != "">
<script src="/static/js/${js}.js"></script>
</#if>
</body>
</html>
</#macro>