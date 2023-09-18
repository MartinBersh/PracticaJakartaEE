package org.example.domain;

import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class Teacher {
        private long id_Teacher;
        private String name;
        private String email;
}
