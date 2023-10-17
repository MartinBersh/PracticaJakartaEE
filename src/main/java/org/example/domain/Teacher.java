package org.example.domain;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import jakarta.enterprise.context.SessionScoped;

@SessionScoped
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class Teacher implements Serializable {
        private long id_Teacher;
        private String name;
        private String email;
}
