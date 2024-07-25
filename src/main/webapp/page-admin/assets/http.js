var Http = (function($, storage, self) {
	var _contentType = 'application/json; charset=utf-8',
		_key = 'userInfo';

	function getToken() {
		const userInfo = storage.getItem(_key);
		if (userInfo) {
			return JSON.parse(userInfo).jwtToken;
		}
		return null;
	}

	function setAuthHeader(xhr) {
		var token = getToken();
		if (!token) return;
		xhr.setRequestHeader('Authorization', 'Bearer ' + token);
	}

	function get(url) {
		var method = 'GET';
		var deferred = $.Deferred();
		ajax({
			deferred: deferred,
			method: method,
			url: url
		});
		return deferred.promise();
	}

	function post(url, payload) {
		var method = 'POST';
		var deferred = $.Deferred();
		var json = JSON.stringify(payload);
		ajax({
			deferred: deferred,
			json: json,
			method: method,
			url: url
		});
		return deferred.promise();
	}

	function postFormData(url, formData) {
		var method = 'POST';
		var deferred = $.Deferred();
		ajaxFormData({
			deferred: deferred,
			formData,
			method: method,
			url: url
		});
		return deferred.promise();
	}

	function patch(url, payload) {
		var method = 'PATCH';
		var deferred = $.Deferred();
		var json = JSON.stringify(payload);
		ajax({
			deferred: deferred,
			json: json,
			method: method,
			url: url
		});
		return deferred.promise();
	}

	function put(url, payload) {
		var method = 'PUT';
		var deferred = $.Deferred();
		var json = JSON.stringify(payload);
		ajax({
			deferred: deferred,
			json: json,
			method: method,
			url: url
		});
		return deferred.promise();
	}

	function putFormData(url, formData) {
		var method = 'PUT';
		var deferred = $.Deferred();
		ajaxFormData({
			deferred: deferred,
			formData,
			method: method,
			url: url
		});
		return deferred.promise();
	}
	
	function del(url) {
		var method = 'DELETE';
		var deferred = $.Deferred();
		ajax({
			deferred: deferred,
			method: method,
			url: url,
		});
		return deferred.promise();
	}

	function ajaxFormData(cfg) {
		$.ajax({
			type: cfg.method,
			crossDomain: true,
			url: cfg.url,
			processData: false,
			data: cfg.formData,
			contentType: false,
			beforeSend: setAuthHeader,
			success: function(response) {
				cfg.deferred.resolve(response);
			},
			error: function(xhr, ajaxOptions, thrownError) {
				cfg.deferred.reject(thrownError);
			}
		});
	}

	function ajax(cfg) {
		$.ajax({
			type: cfg.method,
			crossDomain: true,
			url: cfg.url,
			data: cfg.json,
			contentType: _contentType,
			beforeSend: setAuthHeader,
			success: function(response) {
				cfg.deferred.resolve(response);
			},
			error: function(xhr, ajaxOptions, thrownError) {
				cfg.deferred.reject(thrownError);
			}
		});
	}

	self.post = post;
	self.patch = patch;
	self.put = put;
	self.get = get;
	self.delete = del;
	self.getToken = getToken;
	self.postFormData = postFormData;
	self.putFormData = putFormData;
	return self;
}(jQuery, window.localStorage, Http || {}));