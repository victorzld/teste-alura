package br.com.alura.projeto.course;

import br.com.alura.projeto.category.Category;
import br.com.alura.projeto.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    @Length(min = 4, max = 10)
    @Pattern(regexp = "^[a-z]+(-[a-z]+)*$", message = "Code must contain only lowercase letters and hyphens, without leading/trailing hyphens")
    @Column(unique = true)
    private String code;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private User instructor;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    private String description;

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    @Column(name = "inactivation_date")
    private LocalDateTime inactivationDate;

    @Deprecated
    public Course() {}

    public Course(String name, String code, String description, User instructor, Category category) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.instructor = instructor;
        this.category = category;
    }

    public void inactivate() {
        this.status = Status.INACTIVE;
        this.inactivationDate = LocalDateTime.now();
    }

    public String getName() { return name; }
    public String getCode() { return code; }
    public User getInstructor() { return instructor; }
    public Category getCategory() { return category; }
    public Status getStatus() { return status; }
}