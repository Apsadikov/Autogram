<#import "base.ftlh" as base>
<@base.extends title="${title}" style="${style}" js="${js}">
<div class="row no-gutters">
    <div class="col-10 search">
        <input type="text" class="search-box" id="search" placeholder="Поиск..">
        <div class="results" id="result">
        </div>
    </div>
</div>
    <#list posts as post>
    <div class="row no-gutters feed">
        <div class="col-10 post">
            <div class="row no-gutters author">
                <div class="col-1">
                    <img src="/static/image/${post.authorAvatar}" class="avatar"
                         alt="">
                </div>
                <div class="col-11 name">
                    ${post.authorName}
                </div>
            </div>
            <div class="image-wrapper">
                <a href="/post?id=${post.id}">
                    <img src="/static/image/${post.preview}" class="post-image" alt="">
                </a>
            </div>
            <div class="row no-gutters social-info">
                <div class="col-1">${post.likes} <i class="fa fa-heart-o fa-lg" aria-hidden="true"></i></div>
            </div>
        </div>
    </div>
    </#list>
</@base.extends>