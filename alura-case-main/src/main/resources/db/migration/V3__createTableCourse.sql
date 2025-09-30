CREATE TABLE Course (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    code VARCHAR(10) NOT NULL UNIQUE,
    description TEXT,
    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
    inactivation_date DATETIME,
    instructor_id BIGINT,
    category_id BIGINT,
    CONSTRAINT fk_instructor FOREIGN KEY (instructor_id) REFERENCES User(id),
    CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES Category(id)
);