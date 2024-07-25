$(document).ready(function() {

	// Call api login.
	this.login = function() {
		const body = {
			"username": $("#inpLoginEmail").val(),
			"password": $("#inpLoginPassword").val()
		}
		Http.post(`${domain}/api/login`, body)
			.then(res => {
				if (res.success) {
					localStorage.setItem("userInfo", JSON.stringify(res.data));
					window.location.href = `${domain}/page-admin`;
				} else {
					localStorage.removeItem("userInfo");
					toastr.error(res.errMsg);
				}
			})
			.catch(err => {
				localStorage.removeItem("userInfo");
				toastr.error(err.errMsg);
			});
	};

});