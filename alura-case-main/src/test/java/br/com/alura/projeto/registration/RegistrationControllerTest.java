package br.com.alura.projeto.registration;

import br.com.alura.projeto.course.Course;
import br.com.alura.projeto.course.CourseRepository;
import br.com.alura.projeto.user.User;
import br.com.alura.projeto.user.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RegistrationController.class)
class RegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private CourseRepository courseRepository;

    @MockBean
    private RegistrationRepository registrationRepository;

    @Test
    void createRegistration__should_return_created_when_data_is_valid() throws Exception {
        NewRegistrationDTO newRegistration = new NewRegistrationDTO();
        newRegistration.setCourseCode("java-basic");
        newRegistration.setStudentEmail("aluno@email.com");

        User student = new User("Aluno", "aluno@email.com", null, "123");
        Course course = new Course("Java Básico", "java-basic", "", null, null);

        when(userRepository.findByEmail("aluno@email.com")).thenReturn(Optional.of(student));
        when(courseRepository.findByCode("java-basic")).thenReturn(Optional.of(course));
        when(registrationRepository.existsByUserAndCourse(any(), any())).thenReturn(false);
        when(registrationRepository.save(any(Registration.class))).thenReturn(new Registration(student, course));

        mockMvc.perform(post("/registration/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newRegistration)))
                .andExpect(status().isCreated());
    }
}