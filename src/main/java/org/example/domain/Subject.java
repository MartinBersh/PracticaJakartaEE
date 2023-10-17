package org.example.domain;

import lombok.*;
import jakarta.enterprise.context.SessionScoped;

import java.io.Serial;
import java.io.Serializable;

@SessionScoped
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Subject implements Serializable {

    private Long id_Subject;
    private String name;
    private Teacher teacher;

}
