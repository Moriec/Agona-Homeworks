package Agona06;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        StudentRepository repository = new StudentRepository();

        Student student1 = new Student("Иван Иванов", 20, 4.5f, true);
        Student student2 = new Student("Мария Петрова", 21, 4.8f, true);
        Student student3 = new Student("Петр Сидоров", 19, 3.9f, true);

        Course course1 = new Course("Java Programming", 5, 7.5f, true);
        Course course2 = new Course("Database Systems", 4, 8.0f, true);
        Course course3 = new Course("Web Development", 3, 6.5f, false);

        student1.getCourses().add(course1);
        student1.getCourses().add(course2);
        student2.getCourses().add(course1);
        student2.getCourses().add(course3);
        student3.getCourses().add(course2);
        student3.getCourses().add(course3);

        repository.save(student1);
        repository.save(student2);
        repository.save(student3);

        List<Student> allStudents = repository.getAll();
        allStudents.forEach(System.out::println);
        System.out.println();

        Optional<Student> foundStudent = repository.findById(1L);
        foundStudent.ifPresent(s -> System.out.printf("Найден студент: %s%n", s));
        System.out.println();

        Set<Course> courses = repository.getCoursesForStudent(1L);
        System.out.println("Курсы студента с ID=1:");
        courses.forEach(System.out::println);
        System.out.println();

        foundStudent.ifPresent(student -> {
            student.setAge(22);
            student.setGpa(4.9f);
            student.setStudentName("Иван Иванов (обновлено)");
            repository.update(student);
            System.out.printf("Студент обновлен: %s%n", student);
        });
        System.out.println();

        Boolean deleted = repository.deleteById(3L);
        System.out.println("Студент с ID=3 удален: " + deleted);
        System.out.println();

        List<Student> remainingStudents = repository.getAll();
        System.out.printf("Осталось студентов: %s%n", remainingStudents.size());
        remainingStudents.forEach(System.out::println);

        //HibernateUtil.shutdown();
    }
}