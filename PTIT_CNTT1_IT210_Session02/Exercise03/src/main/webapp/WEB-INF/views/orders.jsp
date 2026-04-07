<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh sách đơn hàng</title>
</head>
<body>

<h2>
    Xin chào, <c:out value="${sessionScope.loggedUser}" />!
    Vai trò: <c:out value="${sessionScope.role}" />
</h2>

<p>
    <a href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
</p>

<table border="1" cellpadding="8" cellspacing="0">
    <tr>
        <th>STT</th>
        <th>Mã đơn</th>
        <th>Tên sản phẩm</th>
        <th>Tổng tiền</th>
        <th>Ngày đặt</th>
    </tr>

    <c:forEach var="order" items="${requestScope.orderList}" varStatus="loop">
        <tr>
            <td><c:out value="${loop.index + 1}" /></td>
            <td><c:out value="${order.orderCode}" /></td>
            <td><c:out value="${order.productName}" /></td>
            <td>
                <fmt:formatNumber value="${order.totalAmount}" type="number" groupingUsed="true" />
                VNĐ
            </td>
            <td>
                <fmt:formatDate value="${order.orderDate}" pattern="dd/MM/yyyy" />
            </td>
        </tr>
    </c:forEach>
</table>

<br>

<p>
    Tổng lượt xem đơn hàng toàn hệ thống:
    <strong><c:out value="${applicationScope.totalViewCount}" /></strong>
</p>

</body>
</html>