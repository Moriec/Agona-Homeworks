--drop table student;
--drop table course;
--drop table student_course;

CREATE TABLE student
(
    student_id   SERIAL PRIMARY KEY,
    student_name VARCHAR(100) NOT NULL,
    age          INT          NOT NULL,
    gpa          FLOAT        NOT NULL,
    is_active    BOOLEAN DEFAULT true
);


CREATE TABLE course
(
    course_id    SERIAL PRIMARY KEY,
    course_name  VARCHAR(100) NOT NULL,
    credits      INT          NOT NULL,
    difficulty   FLOAT        NOT NULL,
    is_mandatory BOOLEAN DEFAULT false
);


CREATE TABLE student_course
(
    student_id INT NOT NULL,
    course_id  INT NOT NULL,
    PRIMARY KEY (student_id, course_id),
    FOREIGN KEY (student_id) REFERENCES student (student_id) ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES course (course_id) ON DELETE CASCADE
);