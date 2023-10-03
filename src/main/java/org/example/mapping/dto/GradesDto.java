package org.example.mapping.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.example.domain.Student;
import org.example.domain.Subject;

@Builder
public record GradesDto(Long id_Grades,
                        Student student,
                        Subject subject,
                        String corte){

    public GradesDto(Long id_Grades, Student student, Subject subject, String corte) {
        this.id_Grades = id_Grades;
        this.student = student;
        this.subject = subject;
        this.corte = corte;
    }
}
