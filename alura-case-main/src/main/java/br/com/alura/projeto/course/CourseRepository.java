package br.com.alura.projeto.course;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    boolean existsByCode(String code);

    Optional<Course> findByCode(String code);

    List<Course> findAllByStatus(Status status);
}