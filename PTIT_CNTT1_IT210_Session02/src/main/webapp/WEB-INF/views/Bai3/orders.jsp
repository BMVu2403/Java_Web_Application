<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Danh sách đơn hàng</title>
    <style>
        table { border-collapse: collapse; width: 100%; margin-top: 20px; }
        th, td { border: 1px solid #ddd; padding: 12px; text-align: left; }
        th { background-color: #f2f2f2; }
        .header-info { display: flex; justify-content: space-between; align-items: center; }
        .footer-counter { margin-top: 30px; padding: 15px; background: #eef; border-radius: 5px; font-weight: bold; }
    </style>
</head>
<body>
<div class="header-info">
    <%-- Đọc thông tin từ Session Scope --%>
    <h2>Xin chào, <span style="color: blue;">${sessionScope.loggedUser}</span>!
        (Vai trò: ${sessionScope.role})
    </h2>
    <a href="<c:url value='/logout'/>" style="color: red; text-decoration: none; font-weight: bold;">[ Đăng xuất ]</a>
</div>

<hr>

<h3>Danh sách đơn hàng của bạn:</h3>
<table>
    <thead>
    <tr>
        <th>Mã đơn</th>
        <th>Tên sản phẩm</th>
        <th>Tổng tiền (VNĐ)</th>
        <th>Ngày đặt</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="order" items="${orders}">
        <tr>
            <td>${order.id}</td>
            <td>${order.name}</td>
            <td>
                    <%-- Định dạng số tiền kiểu VNĐ --%>
                <fmt:formatNumber value="${order.price}" type="currency" currencySymbol="₫" />
            </td>
            <td>
                    <%-- Định dạng ngày tháng dd/MM/yyyy --%>
                <fmt:formatDate value="${order.date}" pattern="dd/MM/yyyy" />
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<div class="footer-counter">
    <%-- Đọc từ Application Scope --%>
    Tổng lượt xem đơn hàng toàn hệ thống:
    <span style="font-size: 1.5em; color: red;">${applicationScope.totalViewCount}</span>
</div>
</body>
</html>