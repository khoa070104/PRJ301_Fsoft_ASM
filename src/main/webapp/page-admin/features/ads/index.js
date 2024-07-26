$(document).ready(function() {

	var $pagination = $('#adsPagination');
	var inpSearchAdsPosition = '';
	$pagination.twbsPagination(defaultOpts);

	this.onSearchByPosition = function() {
		inpSearchAdsPosition = $('#inpSearchAdsPosition').val();
		this.getAds(0, defaultPageSize, inpSearchAdsPosition);
	}

	this.getAds = function(page = 0, size = defaultPageSize, position = '') {
		Http.get(`${domain}/admin/api/ads?type=filter&page=${page}&size=${size}&position=${position}`)
			.then(res => {
				let appendHTML = '';
				$('#tblAds').empty();
				$pagination.twbsPagination('destroy');
				if (!res.success || res.data.totalRecord === 0) {
					$('#tblAds').append(`<tr><td colspan='10' style='text-align: center;'>No Data</td></tr>`);
					return;
				}
				for (const record of res.data.records) {
					appendHTML += '<tr>';
					appendHTML += `<td>${record.id}</td>`;
					appendHTML += `<td>${record.position}</td>`;
					appendHTML += `<td>${record.width}</td>`;
					appendHTML += `<td>${record.height}</td>`;
					appendHTML +=
						`<td>
						<span class='badge ${record.status === 'ACTIVE' ? 'bg-success' : 'bg-danger'}'>
							${record.status}
						</span>
					</td>`;
					appendHTML += `<td>${record.url}</td>`;
					appendHTML += `<td>${record.images}</td>`;
					appendHTML += `<td>${record.updatedBy}</td>`;
					appendHTML += `<td>${record.updatedDate}</td>`;
					appendHTML += `<td class='text-right'>
                        <a class='btn btn-info btn-sm' onclick='switchViewAds(false, ${record.id})'>
                            <i class='fas fa-pencil-alt'></i>
                        </a>
                        <a class='btn btn-danger btn-sm' onclick='deleteAds(${record.id})'>
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
					this.getAds(num - 1, defaultPageSize, inpSearchAdsPosition);
				});
				$('#tblAds').append(appendHTML);
			})
			.catch(err => {
				toastr.error(err.errMsg);
			})
	}

	this.deleteAds = function(id) {
		Http.delete(`${domain}/admin/api/ads?id=${id}`)
			.then(res => {
				if (res.success) {
					this.switchViewAds(true);
					toastr.success('Delete ads success !')
				} else {
					toastr.error(res.errMsg);
				}
			})
			.catch(err => {
				toastr.error(err.errMsg);
			})
	}

	this.getAdsById = function(id) {
		Http.get(`${domain}/admin/api/ads?type=getOne&id=${id}`)
			.then(res => {
				if (res.success) {
					$('#inpAdsId').val(id);
					$('#inpAdsPosition').val(res.data.position);
					$('#inpAdsWidth').val(res.data.width);
					$('#inpAdsHeight').val(res.data.height);
					$('#inpAdsURI').val(res.data.url);
					$('#currentAdsImages').val(res.data.images);
					$('#inpAdsImages').val(null);
				} else {
					toastr.error(res.errMsg);
				}
			})
			.catch(err => {
				toastr.error(err.errMsg);
			})
	}


	this.saveAds = function() {
		const currentId = $('#inpAdsId').val();
		const payload = {
			'position': $('#inpAdsPosition').val(),
			'width': $('#inpAdsWidth').val(),
			'height': $('#inpAdsHeight').val(),
			'url': $('#inpAdsURI').val()
		};
		var formData = new FormData();
		var files = $('#inpAdsImages')[0].files;

		if (files.length > 0) {
			for (var i = 0; i < files.length; i++) {
				formData.append('images', files[i]);
			}
		}

		formData.append('payload', JSON.stringify(payload));

		if (currentId) {
			Http.putFormData(`${domain}/admin/api/ads?id=${currentId}`, formData)
				.then(res => {
					if (res.success) {
						this.switchViewAds(true);
						toastr.success(`Update ads success !`);
					} else {
						toastr.error(res.errMsg);
					}
				})
				.catch(err => {
					toastr.error(err.errMsg);
				});
		} else {
			Http.postFormData(`${domain}/admin/api/ads`, formData)
				.then(res => {
					if (res.success) {
						this.switchViewAds(true);
						toastr.success(`Create ads success !`);
					} else {
						toastr.error(res.errMsg);
					}
				})
				.catch(err => {
					toastr.error(err.errMsg);
				});
		}
	};


	this.switchViewAds = function(isViewTable, id = null) {
		if (isViewTable) {
			$('#ads-table').css('display', 'block');
			$('#ads-form').css('display', 'none');
			this.getAds(0, defaultPageSize);
		} else {
			$('#ads-table').css('display', 'none');
			$('#ads-form').css('display', 'block');
			if (id == null) {
				$('#inpAdsId').val('');
				$('#inpAdsPosition').val('');
				$('#inpAdsWidth').val('');
				$('#inpAdsHeight').val('');
				$('#inpAdsURI').val('');
				$('#currentAdsImages').val('');
				$('#inpAdsImages').val(null);
				$('#inpAdsImages').next('.custom-file-label').html('Choose file');
			} else {
				this.getAdsById(id);
			}
		}
	};

	$('#inpAdsImages').change(function(e) {
		if (e.target.files.length) {
			$(this).next('.custom-file-label').html(e.target.files.length + ' files selected');
		} else {
			$(this).next('.custom-file-label').html('Choose files');
		}
	});

	this.switchViewAds(true);

});
