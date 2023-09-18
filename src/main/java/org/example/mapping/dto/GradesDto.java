package org.example.mapping.dto;

import org.example.domain.Student;
import org.example.domain.Subject;

public record GradesDto(Long id_Grades,
                        Student student,
                        Subject subject,
                        String corte){

}
