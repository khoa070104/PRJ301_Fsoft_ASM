<jsp:include page="components/head.jsp" />
<body class="hold-transition sidebar-mini">
<div class="wrapper">
  <!-- Navbar -->
  <jsp:include page="components/navbar.jsp" />
  <!-- /.navbar -->

  <!-- Main Sidebar Container -->
  <jsp:include page="components/main-sidebar.jsp" />
  <!-- /Main Sidebar Container -->
  
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
	<div id="content" class="p-2">
	</div>
    <!-- content web -->
  </div>
  <!-- /.content-wrapper -->

  <!-- Control Sidebar -->
  <aside class="control-sidebar control-sidebar-dark">
    <!-- Control sidebar content goes here -->
  </aside>
  <!-- /.control-sidebar -->
  <jsp:include page="components/footer.jsp" />
  <!-- Main Footer -->
  
</div>
<!-- ./wrapper -->
<!-- REQUIRED SCRIPTS -->
<script src="${pageContext.request.contextPath}/page-admin/assets/init-admin-page.js"></script>
<script src="${pageContext.request.contextPath}/page-admin/dist/js/demo.js"></script>
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
<script src="${pageContext.request.contextPath}/page-admin/assets/jquery.twbsPagination.js"></script>
<script src="${pageContext.request.contextPath}/page-admin/assets/menu.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/js/select2.full.js"></script>
<script src="${pageContext.request.contextPath}/page-admin/assets/select2.util.js"></script>
<jsp:include page="components/required-script.jsp" />
</body>
</html>
