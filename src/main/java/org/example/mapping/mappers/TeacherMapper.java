package org.example.mapping.mappers;

import org.example.domain.Teacher;
import org.example.mapping.dto.TeacherDto;

import java.util.List;

public class TeacherMapper {

    public static TeacherDto mapFrom(Teacher source){
        return new TeacherDto(source.getId_Teacher(),
                source.getName(),
                source.getEmail());
    }

    public static Teacher mapFrom(TeacherDto source){
        return new Teacher(source.id_Teacher(),
                source.name(),
                source.email());
    }

    public static List<TeacherDto> mapFrom(List<Teacher> sources){
        return sources.parallelStream().map(e-> mapFrom(e)).toList();
    }
}
