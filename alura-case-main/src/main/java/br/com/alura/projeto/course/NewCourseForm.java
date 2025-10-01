package br.com.alura.projeto.course;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public class NewCourseForm {

    @NotBlank
    private String name;

    @NotBlank
    @Length(min = 4, max = 10)
    @Pattern(regexp = "^[a-z]+(-[a-z]+)*$", message = "Code must contain only lowercase letters and hyphens, without leading/trailing hyphens")
    private String code;

    private String description;

    @NotNull
    private Long instructorId;

    @NotNull
    private Long categoryId;

    private Status status;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Long getInstructorId() { return instructorId; }
    public void setInstructorId(Long instructorId) { this.instructorId = instructorId; }
    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
}