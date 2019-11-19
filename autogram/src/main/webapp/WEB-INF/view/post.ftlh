<#import "base.ftlh" as base>
<@base.extends title="${title}" style="${style}" js="${js}">
		<div class="row no-gutters feed">
            <div class="col-7 post">
                <div class="image-wrapper">
                    <div class="right-button" id="left"></div>
                    <img src="/static/image/${post.preview}" class="post-image"
                         id="image" alt="">
                    <div class="left-button" id="right"></div>
                    <div class="hidden" id="images">
                        <#list images as image>/static/image/${image.src},</#list>
                    </div>
                </div>
            </div>
            <div class="col-5 description">
                <div class="row no-gutters author">
                    <div class="col-3">
                        <img src="/static/image/${user.avatar}" class="avatar" alt="">
                    </div>
                    <div class="col-9 name">
                        ${user.name}
                    </div>
                </div>
                <div class="text">${post.text}</div>
                <div class="text">
                    <#list tags as tag>
                        <a href="/tag?search=${tag?substring(1)}">${tag} </a>
                    </#list>
                </div>
                <div><i class="fa fa-heart<#if isLiked == "false">-o</#if> fa-lg" id="like"></i>
                    <span class="hidden" id="user-id">${id}</span>
                    <span class="hidden" id="post-id">${post.id}</span>
                    <span id="counter">${post.likes}</span> <span class="comment-text-header">Комментарии</span>
                </div>
                <div class="send">
                    <form action="/comment" method="post" class="row no-gutters">
                        <textarea name="text" class="col-11 new-comment-text" placeholder="Комментарии"></textarea>
                        <input type="hidden" name="user_id" value="${id}">
                        <input type="hidden" name="post_id" value="${post.id}">
                        <button type="submit" class="col-1 send-button" value="">
                            <i class="fa fa-paper-plane" aria-hidden="true"></i>
                        </button>
                    </form>
                </div>
                <div class="comments">
                    <#list comments as comment>
                        <div class="row no-gutters comment">
                            <div>
                                <img src="/static/image/${comment.authorAvatar}"
                                     class="comment-author" alt="">
                                <span class="author-name">
                                    ${comment.authorName}
                                </span>
                            </div>
                            <div class="comment-text">
                                ${comment.text}
                            </div>
                        </div>
                    </#list>
                </div>
            </div>
        </div>
</@base.extends>