package br.com.alura.projeto.registration;

import java.time.LocalDateTime;

public record RegistrationResponseDTO(String studentName, String courseName, String courseCode, LocalDateTime registrationDate) {
    public RegistrationResponseDTO(Registration registration) {
        this(registration.getUser().getName(), registration.getCourse().getName(), registration.getCourse().getCode(), registration.getRegistrationDate());
    }
}