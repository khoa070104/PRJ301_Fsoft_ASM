<div class="row" id="websetting-table">
    <div class="col-12">
        <div class="card card-primary">
            <div class="card-header">
                <h3 class="card-title">Web Settings</h3>
            </div>
            <div class="row p-2">
                <div class="col-6">
                    <div class="btn-group">
                        <button type="button" class="btn btn-primary btn-sm" onclick="switchViewWebSetting(false)">
                            <i class="fa fa-plus"></i> Create
                        </button>
                    </div>
                </div>
                <div class="col-6">
                    <div class="input-group input-group-sm float-right" style="width: 250px">
                        <input type="text" name="table_search" class="form-control float-right" placeholder="Search" id="inpSearchWebSettingType" oninput="onSearchByType()">
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
                        <th>Type</th>
                        <th>Image URL</th>
                        <th>Status</th>
                        <th>Updated By</th>
                        <th>Updated Date</th>
                        <th style="width: 95px;">Actions</th>
                    </tr>
                    </thead>
                    <tbody id="tblWebSettings">
                    </tbody>
                </table>
                <div class="card-footer clearfix">
                    <ul id="webSettingPagination" class="pagination-sm float-right"></ul>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="row" id="websetting-form">
    <div class="col-12">
        <div class="card card-primary">
            <div class="card-header">
                <h3 class="card-title">Create/Edit Web Setting</h3>
            </div>
            <div class="card-body">
                <div class="form-group" style="display: none">
                    <label for="name">id</label>
                    <input type="text" name="id" class="form-control" id="inpWebSettingId">
                </div>
                <div class="form-group">
                    <label for="name">Type</label>
                    <input class="form-control" placeholder="Type" id="inpWebSettingType">
                </div>
                <div class="form-group">
                    <label for="name">Content</label>
                    <textarea class="form-control" placeholder="Content" id="inpWebSettingContent"></textarea>
                </div>
                <div class="form-group" style="display: none">
                    <label for="name">Status</label>
                    <select class="form-control" id="inpWebSettingStatus">
                        <option value="ACTIVE">ACTIVE</option>
                        <option value="IN_ACTIVE">IN_ACTIVE</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="name">Image</label>
                    <div class="custom-file">
                        <input type="file" class="custom-file-input" id="inpWebSettingImage" multiple>
                        <label class="custom-file-label" for="inpWebSettingImage">Choose files</label>
                    </div>
                </div>
            </div>
            <div class="card-footer">
                <div class="float-right">
                    <button type="button" class="btn btn-primary" onclick="saveWebSetting()">
                        <i class="fas fa-save"></i> Save
                    </button>
                </div>
                <button type="button" class="btn btn-default" onclick="switchViewWebSetting(true)">
                    <i class="fas fa-times"></i> Discard
                </button>
            </div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/page-admin/features/websetting/index.js"></script>
