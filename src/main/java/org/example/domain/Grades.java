package org.example.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Grades {

    private Long id_Grades;
    private Student student;
    private Subject subject;
    private String corte;

}
