var file = document.getElementById("file");
var avatar = document.getElementById("avatar");

file.addEventListener("change", function(ev) {
	if (file.files[0]) {
		var reader = new FileReader();

		console.log(file.files[0]);
		
		reader.onload = function(e) {
			avatar.setAttribute("src", e.target.result);
		};

		reader.readAsDataURL(file.files[0]);
	}
}, false);