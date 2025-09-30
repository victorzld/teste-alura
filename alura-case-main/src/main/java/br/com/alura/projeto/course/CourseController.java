package br.com.alura.projeto.course;

import br.com.alura.projeto.category.CategoryRepository;
import br.com.alura.projeto.user.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CourseController {

    private final CourseRepository courseRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public CourseController(CourseRepository courseRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/admin/courses")
    public String list(Model model) {
        model.addAttribute("courses", courseRepository.findAll().stream().map(CourseDTO::new).toList());
        return "admin/course/list";
    }

    @GetMapping("/admin/course/new")
    public String create(NewCourseForm form, Model model) {
        model.addAttribute("newCourseForm", form);
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("instructors", userRepository.findAll());
        return "admin/course/newForm";
    }

    @PostMapping("/admin/course/new")
    @Transactional
    public String save(@Valid NewCourseForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return create(form, model);
        }
        if (courseRepository.existsByCode(form.getCode())) {
            result.rejectValue("code", "code.exists", "Course code already in use");
            return create(form, model);
        }

        var instructor = userRepository.findById(form.getInstructorId()).orElseThrow(() -> new IllegalArgumentException("Instructor not found"));
        var category = categoryRepository.findById(form.getCategoryId()).orElseThrow(() -> new IllegalArgumentException("Category not found"));
        courseRepository.save(new Course(form.getName(), form.getCode(), form.getDescription(), instructor, category));
        return "redirect:/admin/courses";
    }

    @PostMapping("/course/{code}/inactive")
    public ResponseEntity<?> updateStatus(@PathVariable("code") String courseCode) {
        // TODO: Implementar a Questão 2 - Inativação de Curso aqui...
        return ResponseEntity.ok().build();
    }
}