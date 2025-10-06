SELECT
    students.student_id,
    students.full_name,
    COUNT(enrollments.course_id) AS courses_count
FROM students
         LEFT JOIN enrollments ON students.student_id = enrollments.student_id
GROUP BY students.student_id, students.full_name


SELECT
    professors.professor_id,
    professors.full_name,
    COUNT(course_assignment.course_id) AS courses_count
FROM professors
         JOIN course_assignment ON professors.professor_id = course_assignment.professor_id
GROUP BY professors.professor_id, professors.full_name
ORDER BY courses_count DESC
    LIMIT 1;

ALTER TABLE enrollments
DROP CONSTRAINT enrollments_student_id_fkey,
    ADD CONSTRAINT enrollments_student_id_fkey
        FOREIGN KEY (student_id)
        REFERENCES students(student_id)
        ON DELETE CASCADE;