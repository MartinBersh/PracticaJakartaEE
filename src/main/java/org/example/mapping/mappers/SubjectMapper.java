package org.example.mapping.mappers;

import org.example.domain.Subject;
import org.example.mapping.dto.SubjectDto;

import java.util.List;

public class SubjectMapper {

    public static SubjectDto mapFrom(Subject source){
        return new SubjectDto(source.getId_Subject(),
                source.getName(),
                source.getTeacher());
    }

    public static Subject mapFrom(SubjectDto source){
        return new Subject(source.id_Subject(),
                source.name(),
                source.teacher());
    }

    public static List<SubjectDto> mapFrom(List<Subject> sources){
        return sources.parallelStream().map(e-> mapFrom(e)).toList();
    }

}
