$(document).ready(function() {
	let menus = document.getElementsByClassName("nav-link");
	for (const menu of menus) {
		menu.addEventListener("click", function() {
			let current = document.getElementsByClassName("active");
			if (current.length > 0) {
				current[0].className = current[0].className.replace(
					" active", ""
				);
			}
			this.className += " active";
		});
	}
	$('.sidebar-menu').click(function(e) {
		e.preventDefault();
		$("#content").load($(this).attr('href'));
	});
	$("#default-menu").click();
});