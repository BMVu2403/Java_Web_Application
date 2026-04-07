<%--
    ===== VIEW: Chi tiết Sinh viên (UC-02) =====
    File: /WEB-INF/views/student/detail.jsp

    NGƯỜI PHỤ TRÁCH: Phùng Văn Vượng

    DỮ LIỆU NHẬN TỪ CONTROLLER (qua Model):
      - ${student}  → Object Student chứa đầy đủ thông tin

    YÊU CẦU HIỂN THỊ:
    1. Hiển thị TẤT CẢ thuộc tính của Student:
       - ${student.id}
       - ${student.studentCode}
       - ${student.fullName}
       - ${student.faculty}
       - ${student.enrollmentYear}
       - ${student.gpa}
       - ${student.status}
    2. Trạng thái có màu (giống trang list)
    3. Nút "Quay lại danh sách" → link về /students

    TAGLIB CẦN KHAI BÁO:
      <%@ taglib prefix="c" uri="jakarta.tags.core" %>
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>StudentHub - Chi tiết Sinh viên</title>
    <style>
        body { font-family: Arial, sans-serif; line-height: 1.6; margin: 40px; background-color: #f4f7f6; }
        .container { max-width: 600px; background: #fff; padding: 20px; border-radius: 8px; shadow: 0 2px 4px rgba(0,0,0,0.1); border: 1px solid #ddd; }
        h2 { border-bottom: 2px solid #eee; padding-bottom: 10px; color: #333; }
        .info-group { margin-bottom: 15px; display: flex; border-bottom: 1px inset #fafafa; padding-bottom: 5px; }
        .label { font-weight: bold; width: 180px; color: #555; }
        .value { color: #000; }
        .status-badge { padding: 4px 10px; border-radius: 4px; color: white; font-size: 0.9em; font-weight: bold; }
        .bg-success { background-color: #28a745; }
        .bg-warning { background-color: #fd7e14; }
        .bg-primary { background-color: #007bff; }
        .btn-back { display: inline-block; margin-top: 20px; padding: 10px 15px; background-color: #6c757d; color: white; text-decoration: none; border-radius: 4px; }
        .btn-back:hover { background-color: #5a6268; }
        .error-msg { color: #d9534f; font-weight: bold; }
    </style>
</head>
<body>

<div class="container">
    <h2>Thông tin chi tiết sinh viên</h2>

    <c:choose>
        <c:when test="${not empty student}">
            <div class="info-group">
                <span class="label">ID hệ thống:</span>
                <span class="value">${student.id}</span>
            </div>
            <div class="info-group">
                <span class="label">Mã sinh viên:</span>
                <span class="value">${student.studentCode}</span>
            </div>
            <div class="info-group">
                <span class="label">Họ và tên:</span>
                <span class="value">${student.fullName}</span>
            </div>
            <div class="info-group">
                <span class="label">Khoa:</span>
                <span class="value">${student.faculty}</span>
            </div>
            <div class="info-group">
                <span class="label">Năm nhập học:</span>
                <span class="value">${student.enrollmentYear}</span>
            </div>
            <div class="info-group">
                <span class="label">Điểm GPA:</span>
                <span class="value">${student.gpa}</span>
            </div>
            <div class="info-group">
                <span class="label">Trạng thái:</span>
                <span class="value">
                        <c:choose>
                            <c:when test="${student.status == 'Đang học'}">
                                <span class="status-badge bg-success">${student.status}</span>
                            </c:when>
                            <c:when test="${student.status == 'Bảo lưu'}">
                                <span class="status-badge bg-warning">${student.status}</span>
                            </c:when>
                            <c:otherwise>
                                <span class="status-badge bg-primary">${student.status}</span>
                            </c:otherwise>
                        </c:choose>
                    </span>
            </div>
        </c:when>
        <c:otherwise>
            <p class="error-msg">Dữ liệu sinh viên không tồn tại hoặc đã bị xóa.</p>
        </c:otherwise>
    </c:choose>

    <a href="${pageContext.request.contextPath}/students" class="btn-back">
        Quay lại danh sách
    </a>
</div>

</body>
</html>
