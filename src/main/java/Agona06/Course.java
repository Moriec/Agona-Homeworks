package Agona06;

import java.util.HashSet;
import java.util.Set;

public class Course {
    private Long courseId;
    private String courseName;
    private Integer credits;
    private Float difficulty;
    private Boolean isMandatory;
    private Set<Student> students = new HashSet<>();

    public Course() {}

    public Course(String courseName, Integer credits, Float difficulty, Boolean isMandatory) {
        this.courseName = courseName;
        this.credits = credits;
        this.difficulty = difficulty;
        this.isMandatory = isMandatory;
    }

    // Getters and Setters
    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public Float getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Float difficulty) {
        this.difficulty = difficulty;
    }

    public Boolean getIsMandatory() {
        return isMandatory;
    }

    public void setIsMandatory(Boolean isMandatory) {
        this.isMandatory = isMandatory;
    }

    public Boolean getMandatory() {
        return isMandatory;
    }

    public void setMandatory(Boolean mandatory) {
        isMandatory = mandatory;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", credits=" + credits +
                ", difficulty=" + difficulty +
                ", isMandatory=" + isMandatory +
                '}';
    }
}
