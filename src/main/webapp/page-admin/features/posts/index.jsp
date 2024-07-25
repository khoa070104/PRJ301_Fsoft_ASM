<div class="row" id="posts-table">
  <div class="col-12">
    <div class="card card-primary">
      <div class="card-header">
        <h3 class="card-title">Posts</h3>
      </div>
      <div class="row p-2">
        <div class="col-6">
          <div class="btn-group">
            <button type="button" class="btn btn-primary btn-sm" onclick="swicthViewPosts(false)">
              <i class="fa fa-plus"></i> Create
            </button>
          </div>
        </div>
        <div class="col-6">
          <div class="input-group input-group-sm float-right" style="width: 250px">
            <input type="text" name="table_search" class="form-control float-right" placeholder="Search" id=inpSearchPostsName oninput="onSearchByName()">
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
            <th>Title</th>
            <th>Category</th>
            <th>Banner URL</th>
            <th>Status</th>
            <th>Create By</th>
            <th>Create Date</th>
            <th>Updated By</th>
            <th>Updated Date</th>
            <th style="width: 95px;">Actions</th>
          </tr>
          </thead>
          <tbody id="tblPosts">
          </tbody>
        </table>
        <div class="card-footer clearfix">
          <ul id="postsPagination" class="pagination-sm float-right"></ul>
        </div>
      </div>
    </div>
  </div>
</div>
<div class="row" id="posts-form">
  <div class="col-12">
    <div class="card card-primary">
      <div class="card-header">
        <h3 class="card-title">Create/Edit Posts</h3>
      </div>
      <!-- /.card-header -->
      <div class="card-body">
        <div class="form-group" style="display: none">
          <label for="name">id</label> <input type="text" name="id" class="form-control" id="inpPostsId">
        </div>
        <div class="form-group">
          <label for="name">Title</label> <input class="form-control" placeholder="Title" id="inpPostsTitle">
        </div>
        <div class="form-group">
          <label for="name">Category</label> <select id="selPostsCategory">
        </select>
        </div>
        <div class="form-group">
          <label for="name">Banner</label>
          <div class="custom-file">
            <input type="file" class="custom-file-input" id="inpPostsBanner"> <label class="custom-file-label" for="inpPostsBanner">Choose file</label>
          </div>
        </div>
        <div class="form-group">
          <textarea id="inpPostContent" class="form-control"></textarea>
        </div>
      </div>
      <!-- /.card-body -->
      <div class="card-footer">
        <div class="float-right">
          <button type="button" class="btn btn-default" onclick="draftPosts()">
            <i class="fas fa-pencil-alt"></i> Draft
          </button>
          <button type="button" class="btn btn-primary" onclick="savePosts()">
            <i class="fas fa-save"></i> Save
          </button>
        </div>
        <button type="button" class="btn btn-default" onclick="swicthViewPosts(true)">
          <i class="fas fa-times"></i> Discard
        </button>
      </div>
      <!-- /.card-footer -->
    </div>
    <!-- /.card -->
  </div>
</div>
<script src="${pageContext.request.contextPath}/page-admin/features/posts/index.js"></script>