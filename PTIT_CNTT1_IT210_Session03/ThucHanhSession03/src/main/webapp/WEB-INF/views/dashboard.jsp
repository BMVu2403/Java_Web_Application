<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<head>
    <title>StudentHub - Báo cáo Tổng quan</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; background-color: #f4f7f6; }
        h1 { color: #333; }
        .card {
            background: #fff;
            border: 1px solid #ccc;
            padding: 15px; margin: 10px 0;
            border-radius: 8px;
        }
        .highlight {
            background-color: #f0f8ff;
            border-left: 5px solid #007bff;
        }
        .status-item { margin-bottom: 8px; }
        .status-studying { color: green; font-weight: bold; }
        .status-on-leave { color: orange; font-weight: bold; }
        .status-graduated { color: blue; font-weight: bold; }
        .btn-back { display: inline-block; margin-top: 20px; padding: 10px 15px; background-color: #6c757d; color: white; text-decoration: none; border-radius: 4px; }
        .btn-back:hover { background-color: #5a6268; }
    </style>
</head>
<body>

<h1>📊 Báo cáo Tổng quan — StudentHub</h1>
<hr>

<%-- 1. Tổng số sinh viên --%>
<div class="card">
    <h3>Tổng số sinh viên trong nhóm</h3>
    <p>Số lượng: <strong>${totalStudents}</strong> sinh viên</p>
</div>

<%-- 2. GPA trung bình --%>
<div class="card">
    <h3>GPA Trung bình toàn nhóm</h3>
    <p>Điểm số:
        <strong>
            <fmt:formatNumber value="${averageGpa}" maxFractionDigits="2" />
        </strong>
    </p>
</div>

<%-- 3. Thủ khoa --%>
<div class="card highlight">
    <h3>🏆 Thủ khoa của nhóm</h3>
    <c:choose>
        <c:when test="${not empty topStudent}">
            <p>Họ tên: <strong>${topStudent.fullName}</strong></p>
            <p>Mã SV: ${topStudent.studentCode}</p>
            <p>GPA: <span style="color: #d9534f; font-weight: bold;">${topStudent.gpa}</span></p>
        </c:when>
        <c:otherwise>
            <p>Chưa có dữ liệu sinh viên.</p>
        </c:otherwise>
    </c:choose>
</div>

<%-- 4. Tỷ lệ % theo trạng thái --%>
<div class="card">
    <h3>📈 Thống kê trạng thái học tập</h3>
    <c:forEach var="entry" items="${statusStats}">
        <div class="status-item">
            <c:choose>
                <c:when test="${entry.key == 'Đang học'}">
                    <span class="status-studying">${entry.key}:</span>
                </c:when>
                <c:when test="${entry.key == 'Bảo lưu'}">
                    <span class="status-on-leave">${entry.key}:</span>
                </c:when>
                <c:when test="${entry.key == 'Tốt nghiệp'}">
                    <span class="status-graduated">${entry.key}:</span>
                </c:when>
                <c:otherwise>
                    <span>${entry.key}:</span>
                </c:otherwise>
            </c:choose>
            <strong>
                <fmt:formatNumber value="${entry.value}" maxFractionDigits="1" />%
            </strong>
        </div>
    </c:forEach>
</div>

<%-- 5. Link quay về danh sách --%>
<a href="${pageContext.request.contextPath}/students" class="btn-back">
    ⬅️ Quay lại danh sách sinh viên
</a>

</body>
</html>