<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<head>
    <title>StudentHub - Danh sách Sinh viên</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; background-color: #f4f7f6; }
        h1 { color: #333; }
        .status-studying { color: green; font-weight: bold; }
        .status-on-leave { color: orange; font-weight: bold; }
        .status-graduated { color: blue; font-weight: bold; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #ddd; padding: 10px; text-align: left; }
        th { background-color: #f2f2f2; }
        th a { text-decoration: none; color: #007bff; }
        th a:hover { text-decoration: underline; }
        .search-info { background-color: #e7f3fe; padding: 10px; margin-bottom: 15px; border-left: 6px solid #2196F3; }
        .btn-dashboard { display: inline-block; padding: 10px 15px; background-color: #007bff; color: white; text-decoration: none; border-radius: 5px; margin-bottom: 20px; }
        .btn-dashboard:hover { background-color: #0056b3; }
    </style>
</head>
<body>

    <h1>Hệ thống Quản lý Sinh viên - StudentHub</h1>

    <%-- UC-03: Hiển thị thông báo tìm kiếm nếu có search/faculty --%>
    <c:if test="${not empty search or not empty faculty}">
        <div class="search-info">
            <p>Kết quả lọc cho:
                <strong>${not empty search ? 'Tên: ' : ''}${search}</strong>
                <strong>${not empty faculty ? ' Khoa: ' : ''}${faculty}</strong>
            </p>
            <p>Tìm thấy <strong>${totalCount}</strong> sinh viên phù hợp.</p>
            <a href="<c:url value='/students'/>">Xóa bộ lọc</a>
        </div>
    </c:if>

    <%-- Link đến Dashboard --%>
    <a href="<c:url value='/dashboard'/>" class="btn-dashboard">📊 Xem Dashboard Thống kê</a>

    <%-- Bảng danh sách sinh viên --%>
    <table>
        <thead>
        <tr>
            <th>STT</th>
            <th>Mã SV</th>
            <th>
                <a href="<c:url value='/students'><c:param name='sortBy' value='name'/></c:url>">Họ tên ▲</a>
            </th>
            <th>Khoa</th>
            <th>Năm nhập học</th>
            <th>
                <a href="<c:url value='/students'><c:param name='sortBy' value='gpa'/></c:url>">GPA ▼</a>
            </th>
            <th>Trạng thái</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${studentList}" var="student" varStatus="loop">
            <tr>
                <td>${loop.count}</td>
                <td>${student.studentCode}</td>
                <td>
                    <a href="<c:url value='/students/detail'><c:param name='id' value='${student.id}'/></c:url>">
                        ${student.fullName}
                    </a>
                </td>
                <td>${student.faculty}</td>
                <td>${student.enrollmentYear}</td>
                <td>${student.gpa}</td>
                <td>
                    <c:choose>
                        <c:when test="${student.status == 'Đang học'}">
                            <span class="status-studying">${student.status}</span>
                        </c:when>
                        <c:when test="${student.status == 'Bảo lưu'}">
                            <span class="status-on-leave">${student.status}</span>
                        </c:when>
                        <c:when test="${student.status == 'Tốt nghiệp'}">
                            <span class="status-graduated">${student.status}</span>
                        </c:when>
                        <c:otherwise>
                            ${student.status}
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <c:if test="${empty studentList}">
        <p style="text-align: center; color: red;">Không có dữ liệu sinh viên nào phù hợp!</p>
    </c:if>

</body>
</html>
