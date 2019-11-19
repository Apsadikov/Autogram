<#import "base.ftlh" as base>
<#import "user.ftlh" as user>
<@base.extends title="${title}" style="${style}">
    <@user.include id="${id}" name="${userProfile.name}" fullName="${userProfile.firstName} ${userProfile.lastName}"
    status="${userProfile.status}" img="${userProfile.avatar}"
    subscriber="${userProfile.subscriber}" subscription="${userProfile.subscription}" owner="${owner}" subscribe="${isSubscribe}"/>
    <div class="no-gutters subscriptions">
        <#list subscriptions as subscription>
            <div class="row no-gutters subscription">
                <div class="col-1">
                    <img src="/static/image/${subscription.avatar}" alt=""
                         class="avatar-miniature">
                </div>
                <div class="col-11">
                    <div class="username">${subscription.name}</div>
                    <div class="full-name">${subscription.firstName} ${subscription.lastName}</div>
                </div>
            </div>
        </#list>
    </div>
</@base.extends>
