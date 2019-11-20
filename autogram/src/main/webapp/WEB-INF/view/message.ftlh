<#import "base.ftlh" as base>
<@base.extends title="${title}" style="${style}" js="${js}">
    <div class="no-gutters dialogs col-8 dialog">
        <div class="interlocutor">
            <img class="avatar" src="/static/image/${user.avatar}" alt="">
            <div class="interlocutor-name">${user.name}</div>
        </div>
        <div class="row no-gutters messages" id="messages">
            <#list messages as message >
                <#if message.fromId != Session["id"]>
                     <div class="col-12">
                         <div class="message">
                             ${message.text}
                         </div>
                     </div>
                <#else>
                    <div class="col-12">
                        <div class="message rigth-message">
                            ${message.text}
                        </div>
                    </div>
                </#if>
            </#list>
        </div>
        <div class="row no-gutters message-form">
            <textarea name="" id="message" class="col-11 new-message" placeholder="Сообщение"></textarea>
            <input type="hidden" value="${user.id}" id="id">
            <button type="submit" class="col-1 send-button" value="" id="btn">
                <i class="fa fa-paper-plane" aria-hidden="true"></i>
            </button>
        </form>
    </div>
</@base.extends>