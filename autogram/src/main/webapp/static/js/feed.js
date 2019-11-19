var search = document.getElementById("search");
var results = document.getElementById("result");
results.style.display = "none";

search.oninput = function() {
    if (search.value.length <= 1) {
        results.style.display = "none";
        return;
    }
    $.ajax({
        url: '/user',
        method: 'post',
        data: {
            search: search.value,
        },
        success: function(data){
            if (data != "") {
                results.innerHTML = data;
                results.style.display = "block";
            } else {
                results.style.display = "none";
            }
        }
    });
};