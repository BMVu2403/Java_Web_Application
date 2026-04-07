package org.example.thuchanhsession03.service;

import org.example.thuchanhsession03.model.Student;
import org.example.thuchanhsession03.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // ===== UC-01: Danh sách + Sắp xếp =====

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public List<Student> findAllSorted(String sortBy) {
        List<Student> students = studentRepository.findAll();
        if ("name".equalsIgnoreCase(sortBy)) {
            students.sort((s1, s2) -> s1.getFullName().compareToIgnoreCase(s2.getFullName()));
        } else if ("gpa".equalsIgnoreCase(sortBy)) {
            students.sort((s1, s2) -> Double.compare(s2.getGpa(), s1.getGpa())); // Cao -> Thấp
        }
        return students;
    }

    // ===== UC-02: Chi tiết sinh viên =====

    public Student findById(int id) {
        return studentRepository.findById(id);
    }

    // ===== UC-03: Tìm kiếm / Lọc =====

    public List<Student> search(String keyword) {
        List<Student> allStudents = studentRepository.findAll();
        if (keyword == null || keyword.trim().isEmpty()) {
            return allStudents;
        }
        return allStudents.stream()
                .filter(s -> s.getFullName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Student> filterByFaculty(String faculty) {
        List<Student> allStudents = studentRepository.findAll();
        if (faculty == null || faculty.trim().isEmpty()) {
            return allStudents;
        }
        return allStudents.stream()
                .filter(s -> s.getFaculty().equalsIgnoreCase(faculty))
                .collect(Collectors.toList());
    }

    // ===== UC-04: Dashboard =====

    public int getTotalStudents() {
        return studentRepository.findAll().size();
    }

    public double getAverageGpa() {
        List<Student> list = studentRepository.findAll();
        if (list.isEmpty()) return 0.0;

        double sum = 0;
        for (Student s : list) {
            sum += s.getGpa();
        }
        return sum / list.size();
    }

    public Student getTopStudent() {
        List<Student> list = studentRepository.findAll();
        if (list.isEmpty()) return null;

        Student top = list.get(0);
        for (Student s : list) {
            if (s.getGpa() > top.getGpa()) {
                top = s;
            }
        }
        return top;
    }

    public Map<String, Double> getStatusPercentages() {
        List<Student> studentList = studentRepository.findAll();
        Map<String, Double> stats = new HashMap<>();
        if (studentList.isEmpty()) {
            return stats;
        }

        int total = studentList.size();
        int active = 0, reserved = 0, graduated = 0;
        for (Student s : studentList) {
            switch (s.getStatus()) {
                case "Đang học": active++; break;
                case "Bảo lưu": reserved++; break;
                case "Tốt nghiệp": graduated++; break;
            }
        }

        stats.put("Đang học", (active * 100.0) / total);
        stats.put("Bảo lưu", (reserved * 100.0) / total);
        stats.put("Tốt nghiệp", (graduated * 100.0) / total);

        return stats;
    }
}
