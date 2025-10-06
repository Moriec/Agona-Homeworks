--2 Выберите всех студентов с их курсами и оценками (JOIN enrollments).
SELECT
    students.student_id,
    students.full_name,
    students.enrollment_year,
    courses.course_name,
    courses.credits,
    enrollments.grade
FROM students
         JOIN enrollments ON students.student_id = enrollments.student_id
         JOIN courses ON enrollments.course_id = courses.course_id;



--3 Выведите список профессоров и курсы, которые они ведут (JOIN professors + courses).
SELECT
    professors.professor_id,
    professors.full_name,
    professors.department,
    courses.course_id,
    courses.course_name,
    courses.credits
FROM professors
         JOIN course_assignment ON professors.professor_id = course_assignment.professor_id
         JOIN courses ON course_assignment.course_id = courses.course_id;




--4 Найдите всех студентов, которые не записаны ни на один курс (используйте LEFT JOIN + IS NULL).
SELECT
    students.student_id,
    students.full_name,
    students.enrollment_year
FROM students
         LEFT JOIN enrollments ON students.student_id = enrollments.student_id
WHERE enrollments.student_id IS NULL;



--5 Найдите среднюю оценку по каждому курсу (GROUP BY + агрегатные функции).
SELECT
    courses.course_id,
    courses.course_name,
    COUNT(enrollments.grade) AS total_students,
    ROUND(AVG(
                  CASE
                      WHEN enrollments.grade = 'A+' THEN 4.3
                      WHEN enrollments.grade = 'A' THEN 4.0
                      WHEN enrollments.grade = 'A-' THEN 3.7
                      WHEN enrollments.grade = 'B+' THEN 3.3
                      WHEN enrollments.grade = 'B' THEN 3.0
                      WHEN enrollments.grade = 'B-' THEN 2.7
                      WHEN enrollments.grade = 'C+' THEN 2.3
                      WHEN enrollments.grade = 'C' THEN 2.0
                      WHEN enrollments.grade = 'C-' THEN 1.7
                      WHEN enrollments.grade = 'D' THEN 1.0
                      WHEN enrollments.grade = 'F' THEN 0.0
                      END
          ), 2) AS average_grade
FROM courses
         LEFT JOIN enrollments ON courses.course_id = enrollments.course_id
GROUP BY courses.course_id, courses.course_name;
