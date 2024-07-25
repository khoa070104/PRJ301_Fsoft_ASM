<jsp:include page="/page-admin/components/head.jsp" />
<body class="hold-transition login-page">
  <div class="login-box">
    <div class="card card-outline card-primary">
      <div class="card-header text-center">
        <a href="#" class="h1"><b>Admin</b>SWM</a>
      </div>
      <div class="card-body">
        <p class="login-box-msg">Sign in to start your session</p>

        <form>
          <div class="input-group mb-3">
            <input id="inpLoginEmail" type="text" class="form-control" placeholder="Username or email">
            <div class="input-group-append">
              <div class="input-group-text">
                <span class="fas fa-envelope"></span>
              </div>
            </div>
          </div>
          <div class="input-group mb-3">
            <input id="inpLoginPassword" type="password" class="form-control" placeholder="Password">
            <div class="input-group-append">
              <div class="input-group-text">
                <span class="fas fa-lock"></span>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-8">
              <div class="icheck-primary">
                <input type="checkbox" id="remember"> <label for="remember"> Remember Me </label>
              </div>
            </div>
            <div class="col-4">
              <button type="button" class="btn btn-primary btn-block" onclick="login()">Sign In</button>
            </div>
          </div>
        </form>
        <p class="mb-1">
          <a href="#">I forgot my password</a>
        </p>
        <p class="mb-0">
          <a href="#" class="text-center">Register a new membership</a>
        </p>
      </div>
    </div>
  </div>
  <script src="${pageContext.request.contextPath}/page-admin/features/login/index.js"></script>
  <jsp:include page="/page-admin/components/required-script.jsp" />
</body>
</html>
