package org.example.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Student {

    private Long id_Student;
    private String name;
    private String email;
    private String semester;
    private String career;


}
