$(document).ready(function() {

	var $pagination = $('#webSettingPagination');
	var inpSearchWebSettingType = '';
	$pagination.twbsPagination(defaultOpts);

	this.onSearchByType = function() {
		inpSearchWebSettingType = $('#inpSearchWebSettingType').val();
		this.getWebSettings(0, defaultPageSize, inpSearchWebSettingType);
	}

	this.getWebSettings = function(page = 0, size = defaultPageSize, type = '') {
		Http.get(`${domain}/admin/api/websetting?type=filter&page=${page}&size=${size}&typeFilter=${type}`)
			.then(res => {
				let appendHTML = '';
				$('#tblWebSettings').empty();
				$pagination.twbsPagination('destroy');
				if (!res.success || res.data.totalRecord === 0) {
					$('#tblWebSettings').append(`<tr><td colspan='10' style='text-align: center;'>No Data</td></tr>`);
					return;
				}
				for (const record of res.data.records) {
					appendHTML += '<tr>';
					appendHTML += `<td>${record.id}</td>`;
					appendHTML += `<td>${record.type}</td>`;
					appendHTML += `<td>${record.status}</td>`;
					appendHTML += `<td>${record.content}</td>`;
					appendHTML += `<td>${record.image}</td>`;
					appendHTML += `<td>${record.createdBy}</td>`;
					appendHTML += `<td>${record.createdDate}</td>`;
					appendHTML += `<td>${record.updatedBy}</td>`;
					appendHTML += `<td>${record.updatedDate}</td>`;
					appendHTML += `<td class='text-right'>
                        <a class='btn btn-info btn-sm' onclick='switchViewWebSetting(false, ${record.id})'>
                            <i class='fas fa-pencil-alt'></i>
                        </a>
                        <a class='btn btn-danger btn-sm' onclick='deleteWebSetting(${record.id})'>
                            <i class='fas fa-trash'></i>
                        </a>
                    </td>`;
					appendHTML += '</tr>';
				}
				$pagination.twbsPagination($.extend({}, defaultOpts, {
					startPage: res.data.page + 1,
					totalPages: Math.ceil(res.data.totalRecord / res.data.size)
				}));
				$pagination.on('page', (event, num) => {
					this.getWebSettings(num - 1, defaultPageSize, inpSearchWebSettingType);
				});
				$('#tblWebSettings').append(appendHTML);
			})
			.catch(err => {
				toastr.error(err.errMsg);
			})
	}

	this.deleteWebSetting = function(id) {
		Http.delete(`${domain}/admin/api/websetting?id=${id}`)
			.then(res => {
				if (res.success) {
					this.switchViewWebSetting(true);
					toastr.success('Delete web setting success !')
				} else {
					toastr.error(res.errMsg);
				}
			})
			.catch(err => {
				toastr.error(err.errMsg);
			})
	}

	this.getWebSettingById = function(id) {
		Http.get(`${domain}/admin/api/websetting?type=getOne&id=${id}`)
			.then(res => {
				if (res.success) {
					$('#inpWebSettingId').val(id);
					$('#inpWebSettingType').val(res.data.type);
					$('#inpWebSettingContent').val(res.data.content);
					$('#inpWebSettingImage').val(null);
				} else {
					toastr.error(res.errMsg);
				}
			})
			.catch(err => {
				toastr.error(err.errMsg);
			})
	}

	this.saveWebSetting = function() {
		const currentId = $('#inpWebSettingId').val();
		const payload = {
			'type': $('#inpWebSettingType').val(),
			'content': $('#inpWebSettingContent').val(),
			'status': $('#inpWebSettingStatus').val()
		}
		var formData = new FormData();
		var files = $('#inpWebSettingImage')[0].files;
		if (files.length > 0) {
			formData.append('image', files[0]);
		}
		formData.append('payload', JSON.stringify(payload));
		if (currentId) {
			Http.putFormData(`${domain}/admin/api/websetting?id=${currentId}`, formData)
				.then(res => {
					if (res.success) {
						this.switchViewWebSetting(true);
						toastr.success(`Update web setting success !`)
					} else {
						toastr.error(res.errMsg);
					}
				})
				.catch(err => {
					toastr.error(err.errMsg);
				});
		} else {
			Http.postFormData(`${domain}/admin/api/websetting`, formData)
				.then(res => {
					if (res.success) {
						this.switchViewWebSetting(true);
						toastr.success(`Create web setting success !`)
					} else {
						toastr.error(res.errMsg);
					}
				})
				.catch(err => {
					toastr.error(err.errMsg);
				});
		}
	};

	this.switchViewWebSetting = function(isViewTable, id = null) {
		if (isViewTable) {
			$('#websetting-table').css('display', 'block');
			$('#websetting-form').css('display', 'none');
			this.getWebSettings(0, defaultPageSize);
		} else {
			$('#websetting-table').css('display', 'none');
			$('#websetting-form').css('display', 'block');
			if (id == null) {
				$('#inpWebSettingType').val(null);
				$('#inpWebSettingContent').val(null);
				$('#inpWebSettingImage').val(null);
				$('#inpWebSettingStatus').val('ACTIVE');
			} else {
				this.getWebSettingById(id);
			}
		}
	};

	$('#inpWebSettingImage').change(function(e) {
		if (e.target.files.length) {
			$(this).next('.custom-file-label').html(e.target.files[0].name);
		}
	});

	this.switchViewWebSetting(true);

});
