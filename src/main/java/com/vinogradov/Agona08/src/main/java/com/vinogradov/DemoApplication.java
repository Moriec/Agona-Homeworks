package com.vinogradov;

import com.vinogradov.model.Student;
import com.vinogradov.repository.StudentRepository;
import com.vinogradov.service.StudentService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class DemoApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.vinogradov");

        StudentService studentService = context.getBean(StudentService.class);
        StudentRepository studentRepository = context.getBean(StudentRepository.class);

        studentService.create("Ivan");

        List<Student> all = studentService.getAll();
        System.out.println(all);

        Student student = studentService.getById(1L);
        System.out.println(student);

        if (!all.isEmpty()) {
            Student first = all.get(0);
            studentService.update(first.getId(), "Ivan Petrov");
            System.out.println(studentService.getById(first.getId()));
        }


        List<Student> inner = studentRepository.findAllWithCoursesInnerJoin();
        System.out.println(inner);

        List<Student> left = studentRepository.findAllWithCoursesLeftJoin();
        System.out.println(left);

        List<Student> mathStudents = studentService.findByCourseTitle("Math");
        System.out.println(mathStudents);

        context.close();
    }
}
