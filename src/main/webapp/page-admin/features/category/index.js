$(document).ready(function() {
	var $pagination = $('#categoryPagination');
	var inpSearchCategoryName = "";

	$pagination.twbsPagination(defaultOpts);

	this.onSearchByName = function() {
		inpSearchCategoryName = $("#inpSearchCategoryName").val();
		this.getCategories(0, defaultPageSize, inpSearchCategoryName);
	}

	// Call API get categories.
	this.getCategories = function(page = 0, size = defaultPageSize, name = "") {
		Http.get(`${domain}/admin/api/category?type=filter&page=${page}&size=${size}&name=${name}`)
			.then(res => {
				let appendHTML = "";
				$("#tblCategories").empty();
				// Reset pagination.
				$pagination.twbsPagination('destroy');
				if (!res.success || res.data.totalRecord === 0) {
					// Append text No Data when records empty;
					$("#tblCategories").append(`<tr><td colspan="9" style="text-align: center;">No Data</td></tr>`);
					return;
				}
				for (const record of res.data.records) {
					appendHTML += "<tr>";
					appendHTML += `<td>${record.id}</td>`;
					appendHTML += `<td>${record.name}</td>`;
					appendHTML += `<td>${record.description}</td>`;
					appendHTML +=
						`<td>
						<span class="badge ${record.status.toLocaleLowerCase() === 'active' ? 'bg-success' : 'bg-danger'}">
							${record.status}
						</span>
					</td>`;
					appendHTML += `<td>${record.createdBy}</td>`;
					appendHTML += `<td>${record.createdDate}</td>`;
					appendHTML += `<td>${record.updatedBy}</td>`;
					appendHTML += `<td>${record.updatedDate}</td>`;
					appendHTML +=
						`<td class="text-right">
							<a class="btn btn-info btn-sm" onclick="swicthViewCategory(false, ${record.id})">
								<i class="fas fa-pencil-alt"></i>
							</a>
							<a class="btn btn-danger btn-sm" onclick="deleteCategory(${record.id})">
								<i class="fas fa-trash"></i>
							</a>
						</td>`;
					appendHTML += "</tr>";
				}

				// Build pagination with twbsPagination.
				$pagination.twbsPagination($.extend({}, defaultOpts, {
					startPage: res.data.page + 1,
					totalPages: Math.ceil(res.data.totalRecord / res.data.size)
				}));

				// Add event listener when page change.
				$pagination
					.on('page', (event, num) => {
						this.getCategories(num - 1, defaultPageSize, inpSearchCategoryName);
					});

				// Append html table into tBody.
				$("#tblCategories").append(appendHTML);
			})
			.catch(err => {
				toastr.error(err.errMsg);
			})
	}

	// Action change display screen between Table and Form Create/Edit.
	this.swicthViewCategory = function(isViewTable, id = null) {
		if (isViewTable) {
			$("#category-table").css("display", "block");
			$("#category-form").css("display", "none");
			this.getCategories(0, defaultPageSize);
		} else {
			$("#category-table").css("display", "none");
			$("#category-form").css("display", "block");
			if (id == null) {
				$("#inpCategoryId").val(null);
				$("#inpCategoryName").val(null);
				$("#inpCategoryDesc").val(null);
			} else {
				this.getCategoryById(id);
			}
		}
	};

	// Set default view mode is table.
	this.swicthViewCategory(true);

	// Call API delete category.
	this.deleteCategory = function(id) {
		Http.delete(`${domain}/admin/api/category?id=${id}`)
			.then(res => {
				if (res.success) {
					this.swicthViewCategory(true);
					toastr.success('Delete category success !')
				} else {
					toastr.error(res.errMsg);
				}
			})
			.catch(err => {
				toastr.error(err.errMsg);
			})
	}

	// Call API get category by id.
	this.getCategoryById = function(id) {
		Http.get(`${domain}/admin/api/category?type=getOne&id=${id}`)
			.then(res => {
				if (res.success) {
					$("#inpCategoryId").val(id);
					$("#inpCategoryName").val(res.data.name);
					$("#inpCategoryDesc").val(res.data.description);
				} else {
					toastr.error(res.errMsg);
				}
			})
			.catch(err => {
				toastr.error(err.errMsg);
			})
	}

	// Call API create/edit category.
	this.saveCategory = function() {
		const currentId = $("#inpCategoryId").val();
		const body = {
			"name": $("#inpCategoryName").val(),
			"description": $("#inpCategoryDesc").val()
		}
		if (currentId) {
			Http.put(`${domain}/admin/api/category?id=${currentId}`, body)
				.then(res => {
					if (res.success) {
						this.swicthViewCategory(true);
						toastr.success(`Update category success !`)
					} else {
						toastr.error(res.errMsg);
					}
				})
				.catch(err => {
					toastr.error(err.errMsg);
				});
		} else {
			Http.post(`${domain}/admin/api/category`, body)
				.then(res => {
					if (res.success) {
						this.swicthViewCategory(true);
						toastr.success(`Create category success !`)
					} else {
						toastr.error(res.errMsg);
					}
				})
				.catch(err => {
					toastr.error(err.errMsg);
				});
		}
	};


});