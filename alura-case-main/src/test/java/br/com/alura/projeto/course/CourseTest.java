package br.com.alura.projeto.course;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class CourseTest {

    @Test
    void inactivate__should_set_status_to_inactive_and_set_inactivation_date() {
        Course course = new Course("Java Básico", "java-basic", "Descrição", null, null);
        course.inactivate();

        assertThat(course.getStatus()).isEqualTo(Status.INACTIVE);
        assertThat(course.getInactivationDate()).isNotNull();
    }

    @Test
    void activate__should_set_status_to_active_and_remove_inactivation_date() {
        Course course = new Course("Java Básico", "java-basic", "Descrição", null, null);
        course.inactivate();
        course.activate();

        assertThat(course.getStatus()).isEqualTo(Status.ACTIVE);
        assertThat(course.getInactivationDate()).isNull();
    }
}