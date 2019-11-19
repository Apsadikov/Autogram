<#import "base.ftlh" as base>
<#import "user.ftlh" as user>
<@base.extends title="${title}" style="${style}" js="${js}">
    <@user.include id="${userProfile.id}" name="${userProfile.name}" fullName="${userProfile.firstName} ${userProfile.lastName}"
    status="${userProfile.status}" img="${userProfile.avatar}"
    subscriber="${userProfile.subscriber}" subscription="${userProfile.subscription}" owner="${owner}" subscribe="${isSubscribe}"/>
    <#if owner == "true">
    <div class="row no-gutters add-post">
        <form action="/new-post" method="post" class="col-12" enctype="multipart/form-data">
            <textarea name="text" id="" class="post-text" placeholder="Текст.."></textarea>
            <div id="gallery">
            </div>
            <div class="row no-gutters new-post-bottom">
                <div class="col-6">
                    <label for="file" class="label">
                        <i class="fa fa-camera fa-lg" aria-hidden="true"></i>
                        <input id="file" type="file" class="hidden" name="file" multiple>
                    </label>
                </div>
                <div class="col-6 send-col">
                    <input type="submit" class="send" value="Отправить">
                </div>
            </div>
        </form>
    </div>
    </#if>
    <div class="row no-gutters gallery">
        <#list posts as post>
            <div class="col-4 image">
                <a href="/post?id=${post.id}">
                    <img src="/static/image/${post.preview}" alt="">
                </a>
            </div>
        </#list>
    </div>
</@base.extends>