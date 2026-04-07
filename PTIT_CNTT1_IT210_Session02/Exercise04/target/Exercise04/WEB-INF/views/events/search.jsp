<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Kết quả tìm kiếm sự kiện</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 24px;
        }

        h2, h3 {
            margin-bottom: 8px;
        }

        .summary {
            margin-bottom: 16px;
        }

        .empty-message {
            padding: 12px;
            background: #fff3cd;
            color: #856404;
            border: 1px solid #ffeeba;
            border-radius: 6px;
            margin: 12px 0;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 16px;
        }

        th, td {
            border: 1px solid #ccc;
            padding: 10px;
            text-align: left;
            vertical-align: middle;
        }

        th {
            background-color: #f5f5f5;
        }

        .badge-free {
            display: inline-block;
            padding: 4px 8px;
            background: #28a745;
            color: white;
            border-radius: 4px;
            font-weight: bold;
        }

        .sold-out {
            color: red;
            font-weight: bold;
        }

        .low-ticket {
            color: orange;
            font-weight: bold;
        }

        .available {
            color: green;
            font-weight: bold;
        }

        .btn-book {
            display: inline-block;
            padding: 6px 12px;
            background: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 4px;
        }

        .btn-disabled {
            display: inline-block;
            padding: 6px 12px;
            background: #999;
            color: white;
            text-decoration: none;
            border-radius: 4px;
            pointer-events: none;
            cursor: not-allowed;
            opacity: 0.7;
        }

        .footer-box {
            margin-top: 20px;
            padding: 12px;
            background: #f8f9fa;
            border-radius: 6px;
        }
    </style>
</head>
<body>

    <h2>
        Kết quả tìm kiếm cho:
        <c:out value="${keyword}" />
    </h2>

    <div class="summary">
        Tìm thấy ${empty events ? 0 : fn:length(events)} sự kiện
    </div>

    <c:if test="${empty events}">
        <div class="empty-message">
            Không tìm thấy sự kiện nào phù hợp.
        </div>
    </c:if>

    <c:if test="${not empty events}">
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
                        <td>${status.count}</td>
                        <td><c:out value="${event.name}" /></td>
                        <td><c:out value="${event.eventDate}" /></td>
                        <td>
                            <c:choose>
                                <c:when test="${event.price == 0}">
                                    <span class="badge-free">MIỄN PHÍ</span>
                                </c:when>
                                <c:otherwise>
                                    <fmt:formatNumber value="${event.price}" type="number" />
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${event.remainingTickets == 0}">
                                    <span class="sold-out">HẾT VÉ</span>
                                </c:when>
                                <c:when test="${event.remainingTickets < 10}">
                                    <span class="low-ticket">
                                        Sắp hết (còn <c:out value="${event.remainingTickets}" /> vé)
                                    </span>
                                </c:when>
                                <c:otherwise>
                                    <span class="available">
                                        <c:out value="${event.remainingTickets}" />
                                    </span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${event.remainingTickets == 0}">
                                    <span class="btn-disabled">Đặt vé</span>
                                </c:when>
                                <c:otherwise>
                                    <c:url var="bookUrl" value="/events/${event.id}/book" />
                                    <a class="btn-book" href="${bookUrl}">Đặt vé</a>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>

    <div class="footer-box">
        <c:if test="${not empty events}">
            <div>
                Tên sự kiện đầu tiên:
                <c:out value="${fn:toUpperCase(events[0].name)}" />
            </div>
        </c:if>

        <div>
            Số ký tự của từ khóa tìm kiếm:
            ${fn:length(keyword)} ký tự
        </div>
    </div>

</body>
</html>