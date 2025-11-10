package com.vinogradov.service;


import com.vinogradov.model.Course;
import com.vinogradov.model.Student;
import com.vinogradov.repository.CourseRepository;
import com.vinogradov.repository.StudentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @PersistenceContext
    private EntityManager em;

    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Transactional
    public void createStudent(String name) {
        Student s = new Student();
        s.setName(name);
        studentRepository.save(s);
    }

    @Transactional
    public void updateStudent(Long id, String newName) {
        Student s = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
        s.setName(newName);
        studentRepository.save(s);
    }


    public List<Student> findByCourseTitle(String title) {
        String jpql = "select distinct s from Student s join s.courses c where c.title = :title";

        Query query = em.createQuery(jpql);
        query.setParameter("title", title);

        return (List<Student>) query.getResultList();
    }

    @Transactional
    public void initData() {
        // Курсы
        Course math = new Course();
        math.setTitle("Math");

        Course java = new Course();
        java.setTitle("Java");

        Course spring = new Course();
        spring.setTitle("Spring");

        courseRepository.save(math);
        courseRepository.save(java);
        courseRepository.save(spring);

        Student s1 = new Student();
        s1.setName("Ivan");
        s1.getCourses().add(math);
        s1.getCourses().add(java);

        Student s2 = new Student();
        s2.setName("Petr");
        s2.getCourses().add(java);
        s2.getCourses().add(spring);

        Student s3 = new Student();
        s3.setName("Anna");
        s3.getCourses().add(math);

        studentRepository.save(s1);
        studentRepository.save(s2);
        studentRepository.save(s3);
    }
}
