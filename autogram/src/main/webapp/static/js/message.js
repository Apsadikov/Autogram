var userId = document.getElementById("id");
var message = document.getElementById("message");
var send = document.getElementById("btn");
var messages = document.getElementById("messages");

send.addEventListener("click", function (ev) {
    $.ajax({
        url: '/message',
        method: 'post',
        data: {
            to_id: userId.value,
            message: message.value
        }
    });
    messages.innerHTML += "<div class=\"col-12\"><div class=\"message rigth-message\">" + message.value + "</div></div>";
});