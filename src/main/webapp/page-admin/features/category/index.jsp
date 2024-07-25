<div class="row" id="category-table">
  <div class="col-12">
    <div class="card card-primary">
      <div class="card-header">
        <h3 class="card-title">Category</h3>
      </div>
      <div class="row p-2">
        <div class="col-6">
          <div class="btn-group">
            <button type="button" class="btn btn-primary btn-sm" onclick="swicthViewCategory(false)">
              <i class="fa fa-plus"></i> Create
            </button>
          </div>
        </div>
        <div class="col-6">
          <div class="input-group input-group-sm float-right" style="width: 250px">
            <input type="text" name="table_search" class="form-control float-right" placeholder="Search" id=inpSearchCategoryName oninput="onSearchByName()">
            <div class="input-group-append">
              <button type="submit" class="btn btn-default">
                <i class="fas fa-search"></i>
              </button>
            </div>
          </div>
        </div>
      </div>
      <div class="card-body table-responsive p-0">
        <table class="table table-head-fixed text-wrap table-sm table-striped">
          <thead>
            <tr>
              <th>Id</th>
              <th>Name</th>
              <th>Description</th>
              <th>Status</th>
              <th>Create By</th>
              <th>Create Date</th>
              <th>Updated By</th>
              <th>Updated Date</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody id="tblCategories">
          </tbody>
        </table>
        <div class="card-footer clearfix">
          <ul id="categoryPagination" class="pagination-sm float-right"></ul>
        </div>
      </div>
    </div>
  </div>
</div>
<div class="row" id="category-form">
  <!-- left column -->
  <div class="col-md-12">
    <div class="card card-primary">
      <div class="card-header">
        <h3 class="card-title">Create/Edit Category</h3>
      </div>
      <form id="quickForm">
        <div class="card-body">
          <div class="form-group" style="display: none">
            <label for="name">id</label> <input type="text" name="id" class="form-control" id="inpCategoryId">
          </div>
          <div class="form-group">
            <label for="name">Name</label> <input type="text" name="name" class="form-control" id="inpCategoryName" placeholder="Enter name">
          </div>
          <div class="form-group">
            <label for="description">Description</label> <input type="text" name="description" class="form-control" id="inpCategoryDesc" placeholder="Description">
          </div>
        </div>
        <div class="card-footer">
          <button type="button" class="btn btn-default btn-sm" onclick="swicthViewCategory(true)"><i class="fas fa-times"></i> Cancel</button>
          <button type="button" class="btn btn-primary btn-sm" onclick="saveCategory()"><i class="fas fa-save"></i> Save</button>
        </div>
      </form>
    </div>
  </div>
</div>
<script src="${pageContext.request.contextPath}/page-admin/features/category/index.js"></script>