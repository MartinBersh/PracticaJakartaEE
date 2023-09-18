package org.example.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Subject {

    private Long id_Subject;
    private String name;
    private Teacher teacher;

}
