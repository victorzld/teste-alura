package br.com.alura.projeto.login;

import br.com.alura.projeto.category.Category;
import br.com.alura.projeto.category.CategoryRepository;
import br.com.alura.projeto.category.CategoryWithCoursesDTO;
import br.com.alura.projeto.course.Course;
import br.com.alura.projeto.course.CourseDTO;
import br.com.alura.projeto.course.CourseRepository;
import br.com.alura.projeto.course.Status;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class LoginController {

    private final CategoryRepository categoryRepository;
    private final CourseRepository courseRepository;

    public LoginController(CategoryRepository categoryRepository, CourseRepository courseRepository) {
        this.categoryRepository = categoryRepository;
        this.courseRepository = courseRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Category> categories = categoryRepository.findAllByOrderByOrderAsc();
        List<Course> activeCourses = courseRepository.findAllByStatus(Status.ACTIVE);

        Map<Long, List<CourseDTO>> coursesByCategory = activeCourses.stream()
                .map(CourseDTO::new)
                .collect(Collectors.groupingBy(CourseDTO::categoryId));

        List<CategoryWithCoursesDTO> categoriesWithCourses = categories.stream()
                .map(category -> {
                    List<CourseDTO> courseList = coursesByCategory.getOrDefault(category.getId(), List.of());
                    return new CategoryWithCoursesDTO(category, courseList);
                })
                .toList();

        model.addAttribute("categoriesWithCourses", categoriesWithCourses);

        return "login";
    }
}