package br.com.alura.projeto.registration;

import br.com.alura.projeto.course.Course;
import br.com.alura.projeto.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    boolean existsByUserAndCourse(User user, Course course);

    List<Registration> findAllByUser_Email(String email);

    @Query(value = """
            SELECT
                c.name AS courseName,
                c.code AS courseCode,
                u.name AS instructorName,
                u.email AS instructorEmail,
                COUNT(r.id) AS totalRegistrations
            FROM
                Registration r
            JOIN
                Course c ON r.course_id = c.id
            JOIN
                User u ON c.instructor_id = u.id
            GROUP BY
                c.id
            ORDER BY
                totalRegistrations DESC
            """, nativeQuery = true)
    List<RegistrationReportItem> findRegistrationReport();
}