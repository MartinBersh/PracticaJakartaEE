package org.example.mapping.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
public record StudentDto(Long id_Student,
                         String name,
                         String email,
                         String semester,
                         String career) {
    public StudentDto(Long id_Student, String name, String email, String semester, String career) {
        this.id_Student = id_Student;
        this.name = name;
        this.email = email;
        this.semester = semester;
        this.career = career;
    }
}
