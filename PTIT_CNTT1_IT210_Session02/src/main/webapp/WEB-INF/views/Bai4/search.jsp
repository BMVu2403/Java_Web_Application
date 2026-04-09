<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>Tìm kiếm sự kiện - Bài 4</title>
    <style>
        .out-of-stock { color: red; font-weight: bold; }
        .low-stock { color: orange; font-weight: bold; }
        .in-stock { color: green; }
        .badge-free { background: #28a745; color: white; padding: 3px 8px; border-radius: 4px; font-size: 0.8em; }
        table { border-collapse: collapse; width: 100%; margin-top: 15px; }
        th, td { border: 1px solid #ddd; padding: 12px; text-align: left; }
        th { background-color: #f8f9fa; }
    </style>
</head>
<body>

<%-- A. Phần header tìm kiếm --%>
<h2>Kết quả tìm kiếm cho: <c:out value="${keyword}"/></h2>
<p>Độ dài từ khóa: ${fn:length(keyword)} ký tự</p>

<c:choose>
    <%-- Kiểm tra nếu danh sách sự kiện rỗng --%>
    <c:when test="${empty events}">
        <h3 style="color: gray;">Không tìm thấy sự kiện nào phù hợp.</h3>
    </c:when>

    <c:otherwise>
        <p>Tìm thấy <strong>${events.size()}</strong> sự kiện.</p>

        <%-- B. Bảng kết quả --%>
        <table>
            <thead>
            <tr>
                <th>STT</th>
                <th>Tên sự kiện</th>
                <th>Ngày tổ chức</th>
                <th>Giá vé</th>
                <th>Vé còn lại</th>
                <th>Thao tác</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="event" items="${events}" varStatus="status">
                <tr>
                        <%-- Dùng varStatus.count để đánh số thứ tự từ 1 --%>
                    <td>${status.count}</td>

                        <%-- Dùng c:out để chống XSS cho tên sự kiện --%>
                    <td><c:out value="${event.name}"/></td>

                    <td>${event.date}</td>

                    <td>
                        <c:choose>
                            <c:when test="${event.price == 0}">
                                <span class="badge-free">MIỄN PHÍ</span>
                            </c:when>
                            <c:otherwise>
                                <fmt:formatNumber value="${event.price}" type="number" groupingUsed="true"/> VNĐ
                            </c:otherwise>
                        </c:choose>
                    </td>

                    <td>
                        <c:choose>
                            <c:when test="${event.remainingTickets == 0}">
                                <span class="out-of-stock">HẾT VÉ</span>
                            </c:when>
                            <c:when test="${event.remainingTickets < 10}">
                                <span class="low-stock">Sắp hết (còn ${event.remainingTickets} vé)</span>
                            </c:when>
                            <c:otherwise>
                                <span class="in-stock">${event.remainingTickets}</span>
                            </c:otherwise>
                        </c:choose>
                    </td>

                    <td>
                        <c:choose>
                            <c:when test="${event.remainingTickets == 0}">
                                <span style="color: gray; text-decoration: line-through;">Đặt vé</span>
                            </c:when>
                            <c:otherwise>
                                <%-- Dùng c:url để tự động thêm context path --%>
                                <a href="<c:url value='/events/${event.id}/book'/>">Đặt vé</a>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:otherwise>
</c:choose>

<%-- C. Phần footer --%>
<hr style="margin-top: 30px;">
<c:if test="${not empty events}">
    <%-- Dùng fn:toUpperCase để viết hoa tên sự kiện đầu tiên --%>
    <p>Sự kiện nổi bật nhất: <strong>${fn:toUpperCase(events[0].name)}</strong></p>
</c:if>

</body>
</html>

//bai4 /search