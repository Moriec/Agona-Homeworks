create table students
(
    student_id      serial primary key,
    full_name       varchar(100) not null,
    enrollment_year int
);

create table courses
(
    course_id   serial primary key,
    course_name varchar(100) not null unique,
    credits     int          not null
);

create table enrollments
(
    student_id int references students (student_id),
    course_id  int references courses (course_id),
    grade      char(2) not null,
    primary key (student_id, course_id)
);

create table professors
(
    professor_id serial primary key,
    full_name    varchar(100) not null,
    department   varchar(50)
);

create table course_assignment
(
    course_id    int primary key references courses (course_id),
    professor_id int references professors (professor_id)
);

create index idx_students on students (full_name);
create index idx_professors on professors (department);