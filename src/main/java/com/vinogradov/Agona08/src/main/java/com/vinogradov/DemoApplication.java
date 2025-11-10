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
        //studentService.initData();

        //Создание сущности
        studentService.createStudent("Ivan");

        //Получить всех
        List<Student> all = studentService.getAllStudents();
        System.out.println(all);

        //Получение по id
        Student student = studentService.getStudentById(1L);
        System.out.println(student);

        //Изменение сущности
        if (!all.isEmpty()) {
            Student first = all.get(0);
            studentService.updateStudent(first.getId(), "Ivan Petrov");
            System.out.println(studentService.getStudentById(first.getId()));
        }


        // Inner Join
        List<Student> inner = studentRepository.findAllWithCoursesInnerJoin();
        System.out.println(inner);

        // Left Join
        List<Student> left = studentRepository.findAllWithCoursesLeftJoin();
        System.out.println(left);


        // Query
        List<Student> mathStudents = studentService.findByCourseTitle("Math");
        System.out.println(mathStudents);

        context.close();
    }
}
