package repository.repositoryImpl;

import org.example.domain.Teacher;
import org.example.exception.UniversityException;
import org.example.mapping.dto.TeacherDto;
import org.example.mapping.mappers.TeacherMapper;
import repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class TeacherRepositoryLogicImpl implements Repository<TeacherDto> {

    private List<Teacher> teachers;

    public TeacherRepositoryLogicImpl(){
        Teacher t1 = new Teacher(1L, "Monica","mtobon@cue.edu.com");
        Teacher t2 = new Teacher(2L, "Arle","Armorales@cue.edu.com");
        Teacher t3 = new Teacher(3L, "Mario","Mruiz@cue.edu.com");
        teachers = new ArrayList<>(List.of(t1,t2,t3));

    }


    @Override
    public List<TeacherDto> list() {
        return TeacherMapper.mapFrom(teachers);
    }

    @Override
    public TeacherDto byId(Long id) {
        return teachers.stream()
                .filter(e->e.getId_Teacher() == (e.getId_Teacher()))
                .findFirst()
                .map(TeacherMapper::mapFrom)
                .orElseThrow(()-> new UniversityException("Teacher not found"));

    }

    @Override
    public void update(TeacherDto teacher) {
        TeacherMapper.mapFrom(teachers);
    }

    @Override
    public void delete(Long id) {
        TeacherMapper.mapFrom(teachers);
    }
}
