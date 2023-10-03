package org.example.mapping.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
public record TeacherDto(Long id_Teacher,
                         String name,
                         String email) {
    public TeacherDto(Long id_Teacher, String name, String email) {
        this.id_Teacher = id_Teacher;
        this.name = name;
        this.email = email;
    }
}
