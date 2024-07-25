<div class="row" id="message-table">
    <div class="col-12">
        <div class="card card-primary">
            <div class="card-header">
                <h3 class="card-title">Messages</h3>
            </div>
            <div class="row p-2">
                <div class="col-6" style="margin-left: 50%;">
                    <div class="input-group input-group-sm float-right" style="width: 250px">
                        <input type="text" name="table_search" class="form-control float-right" placeholder="Search" id="inpSearchMessageEmail" oninput="onSearchByEmail()">
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
                        <th>Email</th>
                        <th>Subject</th>
                        <th>Message</th>
                        <th>Created Date</th>
                        <th style="width: 95px;">Actions</th>
                    </tr>
                    </thead>
                    <tbody id="tblMessages">
                    </tbody>
                </table>
                <div class="card-footer clearfix">
                    <ul id="messagePagination" class="pagination-sm float-right"></ul>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/page-admin/features/messages/index.js"></script>
