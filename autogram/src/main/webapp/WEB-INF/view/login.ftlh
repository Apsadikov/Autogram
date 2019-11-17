<#import "base.ftlh" as base>
<@base.extends title="${title}" style="${style}" js="${js}">
    <div class="row">
        <form action="" method="post" class="col-10 col-sm-8 col-md-6 col-lg-5 col-xl-4 shadow login-form">
            <div class="text">Вход</div>
            <input type="text" class="input" name="email" placeholder="Email" autocomplete="false">
            <div class="error"></div>
            <input type="password" class="input" name="password" placeholder="Пароль" autocomplete="false">
            <div class="error"></div>
            <input type="submit" id="signup" class="submit" value="Вход">
            <label for="remember-me" class="remember-me">
                <input id="remember-me" type="checkbox" name="remember_me" class="hidden">
                <span class="checkbox"></span>
                <span class="remember-me-text">Запомнить меня</span>
            </label>
            <a href="/sign-up" class="register">Еще нет аккаунта? Зарегистрироваться</a>
        </form>
    </div>
</@base.extends>
