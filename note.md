## Servlet
- Muốn định nghĩa nhiều url cho 1 class thì dùng syntax: 
@WebServlet(urlPatterns = { "/HomeController", "/Home1Controller" })
- Muốn chuyển hướng 1 Controller đến 1 trang web khác hoặc file .jsp thì dùng
response.sendRedirect("home.jsp");
- Muốn load nội dung của 1 file .jsp lên controller thì dùng:
RequestDispatcher requestDispatcher = request.getRequestDispatcher("home.jsp");
requestDispatcher.forward(request, response);
- Muốn truyền giá trị xuống 1 trang .jsp thì dùng syntax
RequestDispatcher requestDispatcher = request.getRequestDispatcher("home.jsp");
request.setAttribute("username", "SinhCL");
requestDispatcher.forward(request, response);
- Config upload files.
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 50, // 50MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB
## JSP
- Muốn hiện thị 1 biến từ server trả về (jstl)
${username}
- Muốn import 1 file .jsp vào file .jsp thì dùng
<jsp:include page="components/head.jsp" />
- Muốn Truyền thêm param cho include:
<jsp:include page="components/head.jsp" >
  <jsp:param name="title" value="Home" />
</jsp:include>

## Bootstrap
- Active menu when click
$(document).ready(function () {
  let menus = document.getElementsByClassName("nav-link");
  for (const menu of menus) {
    menu.addEventListener("click", function () {
      let current = document.getElementsByClassName("active");
      if (current.length > 0) {
        current[0].className = current[0].className.replace(
          " active", ""
        );
      }
      this.className += " active";
    });
  }
  $('.sidebar-menu').click(function (e) {
    e.preventDefault();
    $("#content").load($(this).attr('href'));
  });
  $("#default-menu").click();
});


