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

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public Student getById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Transactional
    public void create(String name) {
        Student s = new Student();
        s.setName(name);
        studentRepository.save(s);
    }

    @Transactional
    public void update(Long id, String newName) {
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
}
