<#list users as user>
    <div class="row no-gutters result">
        <div class="col-1">
            <img src="/static/image/${user.avatar}" class="avatar"
                 alt="">
        </div>
        <div class="col-11 name">
            <a href="/profile?id=${user.id}">${user.name}</a>
        </div>
    </div>
</#list>