INSERT INTO Course (name, code, description, instructor_id, category_id, status) VALUES
('Java Basico', 'java-basic', '', 1, 1, 'ACTIVE'),
('Spring Boot', 'spring-bt', '', 1, 1, 'ACTIVE'),
('HTML e CSS', 'html-css', '', 1, 2, 'ACTIVE');

INSERT INTO Registration (user_id, course_id, registration_date) VALUES (2, 1, NOW());
INSERT INTO Registration (user_id, course_id, registration_date) VALUES (3, 1, NOW());
INSERT INTO Registration (user_id, course_id, registration_date) VALUES (4, 1, NOW());
INSERT INTO Registration (user_id, course_id, registration_date) VALUES (5, 1, NOW());
INSERT INTO Registration (user_id, course_id, registration_date) VALUES (2, 2, NOW());
INSERT INTO Registration (user_id, course_id, registration_date) VALUES (3, 2, NOW());
INSERT INTO Registration (user_id, course_id, registration_date) VALUES (4, 3, NOW());