package br.com.alura.projeto.course;

public record CourseDTO(String name, String code, String instructorName, String categoryName, Status status) {
    public CourseDTO(Course course) {
        this(course.getName(), course.getCode(), course.getInstructor().getName(), course.getCategory().getName(), course.getStatus());
    }
}