package org.example.mapping.dto;

import lombok.*;
import org.example.domain.Teacher;

@Builder
public record SubjectDto(Long id_Subject,
                         String name,
                         Teacher teacher) {

    public SubjectDto(Long id_Subject, String name, Teacher teacher) {
        this.id_Subject = id_Subject;
        this.name = name;
        this.teacher = teacher;
    }
}
