package org.example.domain;

import lombok.*;
import jakarta.enterprise.context.SessionScoped;

import java.io.Serializable;

@SessionScoped
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class Grades implements Serializable {

    private Long id_Grades;
    private Student student;
    private Subject subject;
    private String corte;

}
