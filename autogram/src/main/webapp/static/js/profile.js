var file = document.getElementById("file");
var gallery = document.getElementById("gallery");
var subscribe = document.getElementById("subscribe");
var id = document.getElementById("id");

if (file != null) {
    file.addEventListener("change", function (ev) {
        if (file.files) {
            gallery.innerHTML = "";
            for (let i = 0; i < file.files.length; i++) {
                var reader = new FileReader();

                reader.onload = function (e) {
                    let image = document.createElement("img");
                    image.setAttribute("src", e.target.result);
                    image.setAttribute("class", 'post-image');
                    gallery.append(image);
                };

                reader.readAsDataURL(file.files[i]);
            }
        }
    }, false);
}

if (subscribe != null) {
    subscribe.addEventListener("click", function (ev) {
        $.ajax({
            url: '/subscriber',
            method: 'post',
            data: {
                id: id.innerText
            }
        });
        subscribe.style.background = "cornflowerblue";
        subscribe.style.color = "#fff";
        subscribe.innerText = "Вы подписанны";
    });
}