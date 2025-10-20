package Agona06;

import java.util.HashSet;
import java.util.Set;

public class Student {
    private Long studentId;
    private String studentName;
    private Integer age;
    private Float gpa;
    private Boolean isActive;
    private Set<Course> courses = new HashSet<>();

    public Student() {}

    public Student(String studentName, Integer age, Float gpa, Boolean isActive) {
        this.studentName = studentName;
        this.age = age;
        this.gpa = gpa;
        this.isActive = isActive;
    }

    // Getters and Setters
    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Float getGpa() {
        return gpa;
    }

    public void setGpa(Float gpa) {
        this.gpa = gpa;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", age=" + age +
                ", gpa=" + gpa +
                ", isActive=" + isActive +
                '}';
    }
}
