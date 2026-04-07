<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng nhập hệ thống</title>
</head>
<body>

<h2>Đăng nhập</h2>

<c:if test="${not empty error}">
    <p style="color:red;">
        <c:out value="${error}" />
    </p>
</c:if>

<form action="${pageContext.request.contextPath}/login" method="post">
    <div>
        <label>Tên đăng nhập:</label>
        <input type="text" name="username" />
    </div>
    <br>
    <div>
        <label>Mật khẩu:</label>
        <input type="password" name="password" />
    </div>
    <br>
    <button type="submit">Đăng nhập</button>
</form>

<p>Tài khoản mẫu:</p>
<ul>
    <li>admin / admin123</li>
    <li>staff / staff123</li>
</ul>

</body>
</html>