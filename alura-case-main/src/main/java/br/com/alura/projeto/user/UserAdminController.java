package br.com.alura.projeto.user;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserAdminController {

    private final UserRepository userRepository;

    public UserAdminController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/admin/students")
    public String listStudents(Model model) {
        List<UserListItemDTO> students = userRepository.findByRole(Role.STUDENT)
                .stream()
                .map(UserListItemDTO::new)
                .toList();
        model.addAttribute("students", students);
        model.addAttribute("newStudentUserDTO", new NewStudentUserDTO());
        return "admin/student/list";
    }

    @PostMapping("/admin/students/new")
    public String createStudent(@Valid NewStudentUserDTO newStudentUserDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<UserListItemDTO> students = userRepository.findByRole(Role.STUDENT)
                    .stream()
                    .map(UserListItemDTO::new)
                    .toList();
            model.addAttribute("students", students);
            model.addAttribute("newStudentUserDTO", newStudentUserDTO);
            return "admin/student/list";
        }

        if (userRepository.existsByEmail(newStudentUserDTO.getEmail())) {
            result.rejectValue("email", "email.exists", "Email já cadastrado no sistema");
            List<UserListItemDTO> students = userRepository.findByRole(Role.STUDENT)
                    .stream()
                    .map(UserListItemDTO::new)
                    .toList();
            model.addAttribute("students", students);
            return "admin/student/list";
        }

        userRepository.save(newStudentUserDTO.toModel());
        return "redirect:/admin/students";
    }
}