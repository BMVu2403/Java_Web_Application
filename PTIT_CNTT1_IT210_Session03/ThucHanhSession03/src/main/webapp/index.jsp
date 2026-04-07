<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--
    Trang index mặc định — redirect về danh sách sinh viên.
    Khi deploy lên Tomcat, truy cập "/" sẽ tự động chuyển đến /students.
--%>
<% response.sendRedirect(request.getContextPath() + "/students"); %>