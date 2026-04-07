package org.example.thuchanhsession03.repository;

import org.example.thuchanhsession03.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository {

    private final List<Student> students = new ArrayList<>();

    // dữ liệu Lê Phú Toàn
    // Phạm Phương Anh
    // Lê Phương Linh
    // Trần Đăng Việt
    // Lê Duy Minh
    // Lê Tiến Đức
    // Bùi Minh Vũ
    // Phùng Văn Vượng
    // Trần Minh Quang
    public StudentRepository() {
        // Dữ liệu mẫu — thông tin thành viên nhóm
        students.add(new Student(1, "SV001", "Lê Phú Toàn", "CNTT", 2024, 3.3, "Tốt nghiệp"));
        students.add(new Student(2, "SV002", "Phạm Phương Anh", "CNTT", 2025, 3.2, "Bảo lưu"));
        students.add(new Student(3, "SV003", "Lê Phương Linh", "Kinh tế", 2026, 3.8, "Đang học"));
        students.add(new Student(4, "SV004", "Trần Đăng Việt", "Ngoại ngữ", 2024, 2.5, "Tốt nghiệp"));
        students.add(new Student(5, "SV005", "Lê Duy Minh", "CNTT", 2026, 3.9, "Đang học"));
        students.add(new Student(6, "SV006", "Lê Tiến Đức", "CNTT", 2024, 3.2, "Bảo lưu"));
        students.add(new Student(7, "SV007", "Bùi Minh Vũ", "Kinh tế", 2023, 3.8, "Tốt nghiệp"));
        students.add(new Student(8, "SV008", "Phùng Văn Vượng", "Ngoại ngữ", 2026, 2.5, "Đang học"));
        students.add(new Student(9, "SV009", "Trần Minh Quang", "CNTT", 2026, 3.2, "Đang học"));
    }

    // Trả về toàn bộ danh sách (bản copy để tránh side-effect)
    public List<Student> findAll() {
        return new ArrayList<>(students);
    }

    // Tìm sinh viên theo ID
    public Student findById(int id) {
        return students.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
