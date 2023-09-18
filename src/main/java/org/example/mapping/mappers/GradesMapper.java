package org.example.mapping.mappers;

import org.example.domain.Grades;
import org.example.mapping.dto.GradesDto;

import java.util.List;

public class GradesMapper {
    public static GradesDto mapFrom(Grades source) {
        return new GradesDto(source.getId_Grades(),
                source.getStudent(),
                source.getSubject(),
                source.getCorte());
    }

    public static Grades mapFrom(GradesDto source){
        return new Grades(source.id_Grades(),
                source.student(),
                source.subject(),
                source.corte());
    }

    public static List<GradesDto> mapFrom(List<Grades> sources){
        return sources.parallelStream().map(e-> mapFrom(e)).toList();
    }
}
