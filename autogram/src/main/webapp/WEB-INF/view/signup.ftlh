<#import "base.ftlh" as base>
<@base.extends title="${title}" style="${style}" js="${js}">
<div class="row">
    <form action="" method="post" enctype="multipart/form-data"
          class="col-10 col-sm-8 col-md-6 col-lg-5 col-xl-4 shadow login-form">
        <div class="text">Регистрация</div>
        <label for="file" class="label">
            <img src="/static/image/avatar.jpg" id="avatar" class="avatar" alt="">
            <input id="file" type="file" name="file" class="hidden">
        </label>
        <div class="error" <#if errors?? && errors["file"]??>style="display:block"</#if>>
            <#if errors?? && errors["file"]??>
                ${errors["file"]}
            </#if>
        </div>
        <input type="text" class="input" placeholder="Никнейм"
               name="name" autocomplete="false" value="<#if name??>${name}</#if>">
        <div class="error" <#if errors?? && errors["name"]??>style="display:block"</#if>>
            <#if errors?? && errors["name"]??>
                ${errors["name"]}
            </#if>
        </div>
        <input type="text" class="input" placeholder="Имя"
               name="first_name" autocomplete="false" value="<#if first_name??>${first_name}</#if>">
        <div class="error" <#if errors?? && errors["first_name"]??>style="display:block"</#if>>
            <#if errors?? && errors["first_name"]??>
                ${errors["first_name"]}
            </#if>
        </div>
        <input type="text" class="input" placeholder="Фамилия"
               name="last_name" autocomplete="false" value="<#if last_name??>${last_name}</#if>">
        <div class="error" <#if errors?? && errors["last_name"]??>style="display:block"</#if>>
            <#if errors?? && errors["last_name"]??>
                ${errors["last_name"]}
            </#if>
        </div>
        <input type="text" class="input" placeholder="Email"
               name="email" autocomplete="false" value="<#if email??>${email}</#if>">
        <div class="error" <#if errors?? && errors["email"]??>style="display:block"</#if>>
            <#if errors?? && errors["email"]??>
                ${errors["email"]}
            </#if>
        </div>
        <input type="password" class="input" placeholder="Пароль"
               name="password" autocomplete="false" value="<#if password??>${password}</#if>">
        <div class="error" <#if errors?? && errors["password"]??>style="display:block"</#if>>
            <#if errors?? && errors["password"]??>
                ${errors["password"]}
            </#if>
        </div>
        <input type="submit" id="signup" name="signup" class="submit" value="Зарегситрироваться">
    </form>
</div>
</@base.extends>