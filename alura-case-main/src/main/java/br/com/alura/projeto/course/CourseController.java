package br.com.alura.projeto.course;

import br.com.alura.projeto.category.CategoryRepository;
import br.com.alura.projeto.user.Role;
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
        model.addAttribute("instructors", userRepository.findByRole(Role.INSTRUCTOR));
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
    @Transactional
    public ResponseEntity<?> updateStatus(@PathVariable("code") String courseCode) {
        return courseRepository.findByCode(courseCode)
                .map(course -> {
                    course.inactivate();
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/admin/course/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid course Id:" + id));
        NewCourseForm form = new NewCourseForm();
        form.setName(course.getName());
        form.setCode(course.getCode());
        form.setDescription(course.getDescription());
        form.setInstructorId(course.getInstructor().getId());
        form.setCategoryId(course.getCategory().getId());
        form.setStatus(course.getStatus()); // Carrega o status atual
        model.addAttribute("newCourseForm", form);
        model.addAttribute("courseId", id);
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("instructors", userRepository.findByRole(Role.INSTRUCTOR));
        return "admin/course/editForm";
    }

    @PostMapping("/admin/course/edit/{id}")
    @Transactional
    public String update(@PathVariable Long id, @Valid NewCourseForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("courseId", id);
            model.addAttribute("categories", categoryRepository.findAll());
            model.addAttribute("instructors", userRepository.findByRole(Role.INSTRUCTOR));
            return "admin/course/editForm";
        }

        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid course Id:" + id));

        if (!course.getCode().equals(form.getCode()) && courseRepository.existsByCode(form.getCode())) {
            result.rejectValue("code", "code.exists", "Course code already in use");
            model.addAttribute("courseId", id);
            model.addAttribute("categories", categoryRepository.findAll());
            model.addAttribute("instructors", userRepository.findByRole(Role.INSTRUCTOR));
            return "admin/course/editForm";
        }

        var instructor = userRepository.findById(form.getInstructorId()).orElseThrow(() -> new IllegalArgumentException("Instructor not found"));
        var category = categoryRepository.findById(form.getCategoryId()).orElseThrow(() -> new IllegalArgumentException("Category not found"));

        course.setName(form.getName());
        course.setCode(form.getCode());
        course.setDescription(form.getDescription());
        course.setInstructor(instructor);
        course.setCategory(category);

        if (form.getStatus() != course.getStatus()) {
            if (form.getStatus() == Status.ACTIVE) {
                course.activate();
            } else {
                course.inactivate();
            }
        }

        courseRepository.save(course);
        return "redirect:/admin/courses";
    }
}