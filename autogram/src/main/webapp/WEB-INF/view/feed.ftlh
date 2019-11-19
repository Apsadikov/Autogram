<#import "base.ftlh" as base>
<@base.extends title="${title}" style="${style}">
<div class="row no-gutters">
    <div class="col-10 search">
        <input type="text" class="search-box" placeholder="Поиск..">
        <div class="results">
            <div class="row no-gutters result">
                <div class="col-1">
                    <img src="https://sun9-61.userapi.com/c624418/v624418543/4fca9/ZlGtFq6vi_4.jpg?ava=1" class="avatar"
                         alt="">
                </div>
                <div class="col-11 name">
                    Ivan_018
                </div>
            </div>
            <div class="row no-gutters result">
                <div class="col-1">
                    <img src="https://sun9-61.userapi.com/c624418/v624418543/4fca9/ZlGtFq6vi_4.jpg?ava=1" class="avatar"
                         alt="">
                </div>
                <div class="col-11 name">
                    Ivan_018
                </div>
            </div>
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