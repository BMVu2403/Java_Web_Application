<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <%-- Tiêu đề trang báo cáo --%>
    <title>Báo cáo điểm</title>
</head>
<body>

<h1><c:out value="${reportTitle}" /></h1>

<table border="1">
    <tr>
        <th>STT</th>
        <th>Họ tên</th>
        <th>Điểm</th>
        <th>Xếp loại</th>
    </tr>

    <c:forEach var="sv" items="${studentList}" varStatus="loop">
        <tr>
            <td><c:out value="${loop.index + 1}" /></td>
            <td><c:out value="${sv.fullName}" /></td>
            <td><c:out value="${sv.score}" /></td>
            <td>
                <c:choose>
                    <c:when test="${sv.score >= 90}">Xuất sắc</c:when>
                    <c:when test="${sv.score >= 80}">Giỏi</c:when>
                    <c:when test="${sv.score >= 70}">Khá</c:when>
                    <c:when test="${sv.score >= 60}">Trung bình khá</c:when>
                    <c:when test="${sv.score >= 50}">Trung bình</c:when>
                    <c:otherwise>Yếu</c:otherwise>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>