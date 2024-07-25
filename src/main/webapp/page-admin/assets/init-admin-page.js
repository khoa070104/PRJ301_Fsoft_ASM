$(document).ready(function() {
	this.handleLoginPage = () => {
		let userInfo = localStorage.getItem("userInfo");
		if (!userInfo) {
			window.location.href = `${domain}/login`
		} else {
			userInfo = JSON.parse(userInfo);
			Http.get(`${domain}/api/verify-token`)
				.then(res => {
					if (!res.success) {
						window.location.href = `${domain}/login`;
						localStorage.removeItem("userInfo");
					} else {
						$("#txtDisplayName").text(userInfo.username);
					}
				})
				.catch(err => {
					console.log(err);
					window.location.href = `${domain}/login`;
					localStorage.removeItem("userInfo");
				})
		}
	}
	// Call function to check user login and has permission ADMIN can access to page.
	this.handleLoginPage();

	this.handleLogout = () => {
		localStorage.removeItem("userInfo");
		window.location.href = `${domain}/login`
	}
});
