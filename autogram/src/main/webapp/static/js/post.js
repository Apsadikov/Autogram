var images = document.getElementById("images");
var image = document.getElementById("image");
var left = document.getElementById("left");
var right = document.getElementById("right");
var imageSrcs = images.innerHTML.split(',');
imageSrcs = imageSrcs.slice(0, imageSrcs.length - 1);
var current = 0;
var counter = document.getElementById("counter");
var like = document.getElementById("like");
var userId = document.getElementById("user-id");
var postId = document.getElementById("post-id");

like.addEventListener("click", function (ev) {
    if (like.getAttribute("class") === "fa fa-heart-o fa-lg") {
        $.ajax({
            url: '/like',
            method: 'post',
            data: {
                user_id: userId.innerText,
                post_id: postId.innerText,
                add: true,
            }
        });
        like.setAttribute("class", "fa fa-heart fa-lg");
        counter.innerText = (parseInt(counter.innerText) + 1);
    } else {
        $.ajax({
            url: '/like',
            method: 'post',
            data: {
                user_id: userId.innerText,
                post_id: postId.innerText,
                add: false,
            }
        });
        like.setAttribute("class", "fa fa-heart-o fa-lg");
        counter.innerText = (parseInt(counter.innerText) - 1);
    }
});

left.addEventListener("click", function (ev) {
    if (current > 0) {
        current -= 1;
        image.setAttribute("src", imageSrcs[current]);
    }
});

right.addEventListener("click", function (ev) {
    if (current < imageSrcs.length - 1) {
        current += 1;
        image.setAttribute("src", imageSrcs[current]);
    }
});