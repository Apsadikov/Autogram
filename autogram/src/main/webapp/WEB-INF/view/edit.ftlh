<#import "base.ftlh" as base>
<#import "user.ftlh" as user>
<@base.extends title="${title}" style="${style}" js="${js}">
    <form action="/edit" method="post" enctype="multipart/form-data">
        <div class="row profile no-gutters">
            <div class="save">
                <button><i class="fa fa-check" aria-hidden="true"></i></button>
            </div>
            <div class="col-2">
                <label for="file" class="label">
                    <img src="/static/image/${userProfile.avatar}" id="avatar"
                         class="avatar" alt="">
                    <input id="file" name="file" type="file" class="hidden">
                </label>
            </div>
            <div class="col-8 user-info">
                <div>
                    <span class="nickname">${userProfile.name}</span>
                    <span class="fullname">(${userProfile.firstName} ${userProfile.lastName})</span>
                </div>
                <div class="status">
                    <textarea name="status" class="status-edit" placeholder="Статус..">${userProfile.status}</textarea>
                </div>
            </div>
        </div>
    </form>
</@base.extends>