package br.com.alura.projeto.category;

import br.com.alura.projeto.course.CourseDTO;
import java.util.List;

public record CategoryWithCoursesDTO(
        String name,
        String code,
        String iconPath,
        List<CourseDTO> courses
) {
    public CategoryWithCoursesDTO(Category category, List<CourseDTO> courses) {
        this(
                category.getName(),
                category.getCode(),
                category.getIconPath(),
                courses
        );
    }
}