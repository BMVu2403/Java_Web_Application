<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Đăng nhập hệ thống</title>
    <style>
        .error { color: red; margin-bottom: 10px; }
        .login-box { width: 300px; margin: 50px auto; padding: 20px; border: 1px solid #ccc; border-radius: 5px; }
        input { width: 100%; margin-bottom: 10px; padding: 8px; box-sizing: border-box; }
        button { width: 100%; padding: 10px; background-color: #007bff; color: white; border: none; cursor: pointer; }
    </style>
</head>
<body>
<div class="login-box">
    <h2>Đăng nhập</h2>

    <%-- Hiển thị thông báo lỗi nếu có (Request Scope) --%>
    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>

    <form action="<c:url value='/login'/>" method="post">
        <label>Tài khoản:</label>
        <input type="text" name="username" placeholder="admin hoặc staff" required>

        <label>Mật khẩu:</label>
        <input type="password" name="password" placeholder="admin123 hoặc staff123" required>

        <button type="submit">Đăng nhập</button>
    </form>
</div>
</body>
</html>
/login
1. Tài khoản Quản lý (Admin)
Tài khoản: admin
Mật khẩu: admin123

2. Tài khoản Nhân viên (Staff)
Tài khoản: staff
Mật khẩu: staff123