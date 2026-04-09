package org.example.SS02.Exercise02.controller; // Bạn có thể tạo package Bai2 riêng

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ReportController {
    @RequestMapping("/report")
    public String showReport(Model model) {
        List<Student> list = new ArrayList<>();
        list.add(new Student("Nguyễn Văn A", 8.5));
        list.add(new Student("Trần Thị B", 4.0));
        list.add(new Student("Lê Văn C", 7.0));

        model.addAttribute("studentList", list);
        model.addAttribute("className", "Java Web 8386");

        return "report";
    }
}


class Student {
    private String name;
    private double score;

    public Student(String name, double score) {
        this.name = name;
        this.score = score;
    }

    // BẮT BUỘC PHẢI CÓ GETTER THÌ JSP MỚI ĐỌC ĐƯỢC
    public String getName() {
        return name;
    }

    public double getScore() {
        return score;
    }

    // Nếu có Setter càng tốt (không bắt buộc cho bài này)
    public void setName(String name) { this.name = name; }
    public void setScore(double score) { this.score = score; }
}