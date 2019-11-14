var images = document.getElementById("images");
var image = document.getElementById("image");
var left = document.getElementById("left");
var right = document.getElementById("right");
var imageSrcs = images.innerHTML.split(',');
var current = 0;

left.addEventListener("click", function(ev) {
	if (current > 0) {
		current -= 1;
		image.setAttribute("src", imageSrcs[current]);
	}
});

right.addEventListener("click", function(ev) {
	if (current < imageSrcs.length - 1) {
		current += 1;
		image.setAttribute("src", imageSrcs[current]);
	}
});