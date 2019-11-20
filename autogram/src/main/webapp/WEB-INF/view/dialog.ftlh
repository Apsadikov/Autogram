<#import "base.ftlh" as base>
<@base.extends title="${title}" style="${style}">
<div class="no-gutters dialogs">
	<#list dialogs as dialog>
        <div class="row no-gutters dialog">
            <div class="col-1">
                <img src="/static/image/${dialog.avatar}" alt="" class="avatar-miniature">
            </div>
            <div class="col-11 interlocutor">
                <div>
                    <span class="username"><a href="/message?user_id=${dialog.fromId}">${dialog.name}</a></span>
                </div>
            </div>
        </div>
    </#list>
</div>
</@base.extends>