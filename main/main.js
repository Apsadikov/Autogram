const addReplaceListener = (buttonClass, url) => {

    const button = document.querySelector(buttonClass);

    button.addEventListener('click', () => {
        window.location.replace(url);
    });
}
addReplaceListener('.btn_signin', '/Autogram-new-frontend%20(1)/Autogram-new-frontend/login/index.html?_ijt=o31snmcdhqmft825shq47am7uq');
addReplaceListener('.btn_signup', '/Autogram-new-frontend%20(1)/Autogram-new-frontend/signup/index.html?_ijt=7foumq28psj918mlqhlrlc6117');
addReplaceListener('.btn col-4 menu-item right-border', '/Autogram-new-frontend/feed/index.html?_ijt=e4eooqtndalp8jnlm7bp5edmhm');