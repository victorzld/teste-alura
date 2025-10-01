package br.com.alura.projeto.course;

import java.time.format.DateTimeFormatter;

public record CourseDTO(
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
                course.getName(),
                course.getCode(),
                course.getInstructor().getName(),
                course.getCategory().getId(),
                course.getCategory().getName(),
                course.getStatus(),
                course.getInactivationDate() != null ? course.getInactivationDate().format(FORMATTER) : ""
        );
    }
}