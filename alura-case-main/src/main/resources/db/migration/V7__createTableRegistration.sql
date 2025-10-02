CREATE TABLE Registration (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    course_id BIGINT NOT NULL,
    registration_date DATETIME NOT NULL,
    FOREIGN KEY (user_id) REFERENCES User(id),
    FOREIGN KEY (course_id) REFERENCES Course(id),
    UNIQUE (user_id, course_id)
);