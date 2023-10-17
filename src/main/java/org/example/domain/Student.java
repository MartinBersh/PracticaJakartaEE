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

public class Student implements Serializable {

    private Long id_Student;
    private String name;
    private String email;
    private String semester;
    private String career;


}
