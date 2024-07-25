$(document).ready(function() {
	var $pagination = $('#messagePagination');
	var inpSearchMessageEmail = '';
	$pagination.twbsPagination(defaultOpts);

	this.onSearchByEmail = function() {
		inpSearchMessageEmail = $('#inpSearchMessageEmail').val();
		this.getMessages(0, defaultPageSize, inpSearchMessageEmail);
	}

	this.getMessages = function(page = 0, size = defaultPageSize, email = '') {
		Http.get(`${domain}/admin/api/messages?type=filter&page=${page}&size=${size}&email=${email}`)
			.then(res => {
				let appendHTML = '';
				$('#tblMessages').empty();
				$pagination.twbsPagination('destroy');
				if (!res.success || res.data.totalRecord === 0) {
					$('#tblMessages').append(`<tr><td colspan='6' style='text-align: center;'>No Data</td></tr>`);
					return;
				}
				for (const record of res.data.records) {
					appendHTML += '<tr>';
					appendHTML += `<td>${record.id}</td>`;
					appendHTML += `<td>${record.email}</td>`;
					appendHTML += `<td>${record.subject}</td>`;
					appendHTML += `<td>${record.message}</td>`;
					appendHTML += `<td>${record.createdDate}</td>`;
					appendHTML += `<td class='text-right'>
                        <a class='btn btn-danger btn-sm' onclick='deleteMessage(${record.id})'>
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
					this.getMessages(num - 1, defaultPageSize, inpSearchMessageEmail);
				});
				$('#tblMessages').append(appendHTML);
			})
			.catch(err => {
				toastr.error(err.errMsg);
			})
	}

	this.deleteMessage = function(id) {
		Http.delete(`${domain}/admin/api/messages?id=${id}`)
			.then(res => {
				if (res.success) {
					this.getMessages(0, defaultPageSize);
					toastr.success('Delete message success !')
				} else {
					toastr.error(res.errMsg);
				}
			})
			.catch(err => {
				toastr.error(err.errMsg);
			})
	}

	this.onSearchByEmail = function() {
		inpSearchMessageEmail = $('#inpSearchMessageEmail').val();
		this.getMessages(0, defaultPageSize, inpSearchMessageEmail);
	}

	this.switchViewMessages = function(isViewTable) {
		if (isViewTable) {
			$('#message-table').css('display', 'block');
			$('#message-form').css('display', 'none');
			this.getMessages(0, defaultPageSize);
		} else {
			$('#message-table').css('display', 'none');
			$('#message-form').css('display', 'block');
		}
	};

	this.switchViewMessages(true);
});
