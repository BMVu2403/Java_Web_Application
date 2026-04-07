package org.example.thuchanhsession03.controller;

import org.example.thuchanhsession03.model.Student;
import org.example.thuchanhsession03.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * CONTROLLER: StudentController — Tầng Presentation
 * Nguyên tắc "Thin Controller": nhận param → gọi Service → trả view.
 */
@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    // ===== UC-01 + UC-03: Danh sách + Sắp xếp + Tìm kiếm/Lọc =====
    @GetMapping("/students")
    public String listStudents(
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "faculty", required = false) String faculty,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            Model model) {

        List<Student> studentList;

        if (search != null && !search.isEmpty()) {
            // UC-03: Tìm kiếm theo tên
            studentList = studentService.search(search);
        } else if (faculty != null && !faculty.isEmpty()) {
            // UC-03: Lọc theo khoa
            studentList = studentService.filterByFaculty(faculty);
        } else if (sortBy != null && !sortBy.isEmpty()) {
            // UC-01: Sắp xếp
            studentList = studentService.findAllSorted(sortBy);
        } else {
            // Mặc định: Lấy toàn bộ
            studentList = studentService.findAll();
        }

        model.addAttribute("studentList", studentList);
        model.addAttribute("totalCount", studentList.size());
        model.addAttribute("search", search);
        model.addAttribute("faculty", faculty);
        model.addAttribute("sortBy", sortBy);
        return "student/list";
    }

    // ===== UC-02: Chi tiết sinh viên =====
    @GetMapping("/students/detail")
    public String getStudentDetail(@RequestParam("id") int id, Model model) {
        Student student = studentService.findById(id);
        model.addAttribute("student", student);
        return "student/detail";
    }

    // ===== UC-04: Dashboard =====
    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        model.addAttribute("totalStudents", studentService.getTotalStudents());
        model.addAttribute("averageGpa", studentService.getAverageGpa());
        model.addAttribute("topStudent", studentService.getTopStudent());
        model.addAttribute("statusStats", studentService.getStatusPercentages());
        return "dashboard";
    }
}
