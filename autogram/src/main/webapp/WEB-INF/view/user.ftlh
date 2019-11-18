<#macro include id name fullName status img subscription subscriber owner>
<div class="row profile no-gutters">
    <div class="col-2">
        <img src="/static/image/${img}" class="avatar" alt="">
    </div>
    <div class="col-8 user-info">
        <div class="user-info-wrapper">
            <div>
                <span class="nickname">${name}</span>
                <span class="user-full-name">(${fullName})</span>
            </div>
            <div class="status">${status}</div>
            <#if owner == "false">
            <div class="social-actions">
                <div class="subscribe">Подписаться</div>
                <div class="message">Сообщение</div>
            </div>
            <#else>
                <a href="/edit" class="edit">Редактировать</a>
            </#if>
        </div>
        <div class="row no-gutters subscriber-info">
            <div class="col-6">
                <div class="subscriber-info-name">Подписчики</div>
                <a href="http://localhost:8080/subscriber?id=${id}" class="number">${subscriber}</a>
            </div>
            <div class="col-6">
                <div class="subscriber-info-name">Подписки</div>
                <a href="http://localhost:8080/subscription?id=${id}" class="number">${subscription}</a>
            </div>
        </div>
    </div>
</div>
</#macro>