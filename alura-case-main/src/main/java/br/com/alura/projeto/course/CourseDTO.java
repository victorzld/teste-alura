package br.com.alura.projeto.course;

import java.time.format.DateTimeFormatter;

public record CourseDTO(
        Long id, // Adicionado
        String name,
        String code,
        String instructorName,
        Long categoryId,
        String categoryName,
        Status status,
        String inactivationDate
) {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public CourseDTO(Course course) {
        this(
                course.getId(),
                course.getName(),
                course.getCode(),
                course.getInstructor() != null ? course.getInstructor().getName() : "",
                course.getCategory() != null ? course.getCategory().getId() : null,
                course.getCategory() != null ? course.getCategory().getName() : "",
                course.getStatus(),
                course.getInactivationDate() != null ? course.getInactivationDate().format(FORMATTER) : ""
        );
    }
}