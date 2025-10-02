package br.com.alura.projeto.course;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CourseRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CourseRepository courseRepository;

    @Test
    void existsByCode__should_return_true_when_code_exists() {
        Course course = new Course("Java Básico", "java-basic", "Descrição", null, null);
        entityManager.persist(course);

        boolean exists = courseRepository.existsByCode("java-basic");

        assertThat(exists).isTrue();
    }

    @Test
    void findByCode__should_return_course_when_code_exists() {
        Course course = new Course("Spring Boot", "spring-bt", "Descrição", null, null);
        entityManager.persist(course);

        Optional<Course> foundCourse = courseRepository.findByCode("spring-bt");

        assertThat(foundCourse).isPresent();
        assertThat(foundCourse.get().getName()).isEqualTo("Spring Boot");
    }

    @Test
    void findAllByStatus__should_return_only_active_courses() {
        Course activeCourse = new Course("Java Básico", "java-basic", "Descrição", null, null);
        Course inactiveCourse = new Course("Spring Boot", "spring-bt", "Descrição", null, null);
        inactiveCourse.inactivate();
        entityManager.persist(activeCourse);
        entityManager.persist(inactiveCourse);

        List<Course> activeCourses = courseRepository.findAllByStatus(Status.ACTIVE);

        assertThat(activeCourses).hasSize(1);
        assertThat(activeCourses.get(0).getName()).isEqualTo("Java Básico");
    }
}