package br.com.alura.projeto.category;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    @Length(min = 4, max = 10)
    private String code;

    @NotBlank
    private String color;

    @NotNull
    @Min(1)
    @Column(name = "`order`")
    private int order;

    private String description;

    @Column(name = "icon_path")
    private String iconPath;

    private LocalDateTime createdAt = LocalDateTime.now();

    @Deprecated
    public Category() {}

    public Category(String name, String code, String color, int order, String description, String iconPath) {
        this.name = name;
        this.code = code;
        this.color = color;
        this.order = order;
        this.description = description;
        this.iconPath = iconPath;
    }

    public String getDescription() {
        return description;
    }

    public String getIconPath() {
        return iconPath;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public int getOrder() {
        return order;
    }

    public String getCode() {
        return code;
    }
}