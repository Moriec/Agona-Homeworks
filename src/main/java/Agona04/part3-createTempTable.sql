CREATE TEMPORARY TABLE new_courses (
    course_id   INT,
    course_name VARCHAR(100) NOT NULL,
    credits     INT NOT NULL
);

INSERT INTO new_courses (course_id, course_name, credits) VALUES
    (NULL, 'Машинное обучение', 5),
    (3,    'Математический анализ: углублённый курс', 5);

MERGE INTO courses
    USING new_courses
    ON courses.course_id = new_courses.course_id
    WHEN MATCHED THEN
        UPDATE SET
            course_name = new_courses.course_name,
            credits     = new_courses.credits
    WHEN NOT MATCHED THEN
        INSERT (course_name, credits)
            VALUES (new_courses.course_name, new_courses.credits);
