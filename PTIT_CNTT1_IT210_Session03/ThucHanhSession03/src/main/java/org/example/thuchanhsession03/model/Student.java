package org.example.thuchanhsession03.model;

/**
 * MODEL: Student — POJO đại diện cho một sinh viên trong hệ thống.
 * 7 thuộc tính: id, studentCode, fullName, faculty, enrollmentYear, gpa, status
 */
public class Student {

    private int id;
    private String studentCode;
    private String fullName;
    private String faculty;
    private int enrollmentYear;
    private double gpa;
    private String status;

    // Constructor không tham số
    public Student() {
    }

    // Constructor đầy đủ 7 tham số
    public Student(int id, String studentCode, String fullName, String faculty, int enrollmentYear, double gpa, String status) {
        this.id = id;
        this.studentCode = studentCode;
        this.fullName = fullName;
        this.faculty = faculty;
        this.enrollmentYear = enrollmentYear;
        this.gpa = gpa;
        this.status = status;
    }

    // Getter & Setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public int getEnrollmentYear() {
        return enrollmentYear;
    }

    public void setEnrollmentYear(int enrollmentYear) {
        this.enrollmentYear = enrollmentYear;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
