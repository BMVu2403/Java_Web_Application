<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- Khai báo thư viện JSTL để dùng thẻ c:forEach, c:if --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Báo cáo điểm sinh viên</title>
    <style>
        table { border-collapse: collapse; width: 100%; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        .pass { color: green; font-weight: bold; }
        .fail { color: red; font-weight: bold; }
    </style>
</head>
<body>
<%-- Ghi chú nội bộ: Đã chuyển sang JSP comment để bảo mật --%>
<h1>Danh sách sinh viên lớp: ${className}</h1>

<p>Người thực hiện:
    <%-- Chống XSS bằng cách dùng c:out --%>
    <c:out value="${param.user}" default="Guest" />
</p>

<table>
    <thead>
    <tr>
        <th>STT</th>
        <th>Họ tên</th>
        <th>Điểm</th>
        <th>Kết quả</th>
    </tr>
    </thead>
    <tbody>
    <%-- Dùng c:forEach thay thế cho vòng lặp for của Java --%>
    <c:forEach var="student" items="${studentList}" varStatus="status">
        <tr>
            <td>${status.index + 1}</td>
            <td>${student.name}</td>
            <td>${student.score}</td>
            <td>
                    <%-- Dùng c:choose thay thế cho if-else --%>
                <c:choose>
                    <c:when test="${student.score >= 5}">
                        <span class="pass">Đậu</span>
                    </c:when>
                    <c:otherwise>
                        <span class="fail">Trượt</span>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<p>Tổng số sinh viên: ${studentList.size()}</p>
</body>
</html>
//bai2 /report?user=GiaBao