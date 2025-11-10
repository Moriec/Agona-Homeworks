package com.vinogradov.repository;

import com.vinogradov.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("select distinct s from Student s join fetch s.courses")
    List<Student> findAllWithCoursesInnerJoin();

    @Query("select distinct s from Student s left join fetch s.courses")
    List<Student> findAllWithCoursesLeftJoin();
}