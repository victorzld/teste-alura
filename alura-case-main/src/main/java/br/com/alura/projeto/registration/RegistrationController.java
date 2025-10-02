package br.com.alura.projeto.registration;

import br.com.alura.projeto.course.Course;
import br.com.alura.projeto.course.CourseDTO;
import br.com.alura.projeto.course.CourseRepository;
import br.com.alura.projeto.course.Status;
import br.com.alura.projeto.user.User;
import br.com.alura.projeto.user.UserRepository;
import br.com.alura.projeto.util.ErrorItemDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class RegistrationController {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final RegistrationRepository registrationRepository;

    public RegistrationController(UserRepository userRepository, CourseRepository courseRepository, RegistrationRepository registrationRepository) {
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
        this.registrationRepository = registrationRepository;
    }

    @PostMapping("/registration/new")
    public ResponseEntity<?> createRegistration(@Valid @RequestBody NewRegistrationDTO newRegistration) {
        Optional<User> userOptional = userRepository.findByEmail(newRegistration.getStudentEmail());
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorItemDTO("studentEmail", "Student not found"));
        }

        Optional<Course> courseOptional = courseRepository.findByCode(newRegistration.getCourseCode());
        if (courseOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorItemDTO("courseCode", "Course not found"));
        }

        User user = userOptional.get();
        Course course = courseOptional.get();

        if (course.getStatus() == Status.INACTIVE) {
            return ResponseEntity.badRequest().body(new ErrorItemDTO("course", "Course is inactive"));
        }

        if (registrationRepository.existsByUserAndCourse(user, course)) {
            return ResponseEntity.badRequest().body(new ErrorItemDTO("course", "User is already enrolled in this course"));
        }

        Registration registration = registrationRepository.save(new Registration(user, course));

        return ResponseEntity.status(HttpStatus.CREATED).body(new RegistrationResponseDTO(registration));
    }

    @GetMapping("/registrations/user/{userEmail}")
    public ResponseEntity<List<CourseDTO>> getCoursesByUser(@PathVariable String userEmail) {
        if (!userRepository.existsByEmail(userEmail)) {
            return ResponseEntity.notFound().build();
        }

        List<Registration> registrations = registrationRepository.findAllByUser_Email(userEmail);
        List<CourseDTO> courses = registrations.stream()
                .map(registration -> new CourseDTO(registration.getCourse()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(courses);
    }

    @GetMapping("/registration/report")
    public ResponseEntity<List<RegistrationReportItem>> report() {
        List<RegistrationReportItem> items = registrationRepository.findRegistrationReport();
        return ResponseEntity.ok(items);
    }
}