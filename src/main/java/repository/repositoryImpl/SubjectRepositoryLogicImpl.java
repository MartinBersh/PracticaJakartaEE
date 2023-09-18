package repository.repositoryImpl;

import org.example.domain.Subject;
import org.example.domain.Teacher;
import org.example.exception.UniversityException;
import org.example.mapping.dto.SubjectDto;
import org.example.mapping.mappers.SubjectMapper;
import org.example.mapping.mappers.TeacherMapper;
import repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class SubjectRepositoryLogicImpl implements Repository<SubjectDto> {

    private List<Subject> subjects;

    public SubjectRepositoryLogicImpl(){
        Teacher t1 = new Teacher(1L, "Monica","mtobon@cue.edu.com");
        Teacher t2 = new Teacher(2L, "Arle","Armorales@cue.edu.com");
        Subject sub1 = new Subject(1L,"Programacion 1",t1);
        Subject sub2 = new Subject(2L,"Programacion 2", t2);
        subjects = new ArrayList<>(List.of(sub1,sub2));
    }
    @Override
    public List<SubjectDto> list() {
        return SubjectMapper.mapFrom(subjects);
    }

    @Override
    public SubjectDto byId(Long id) {
        return subjects.stream()
                .filter(e->e.getId_Subject() == (e.getId_Subject()))
                .findFirst()
                .map(SubjectMapper::mapFrom)
                .orElseThrow(()-> new UniversityException("Subject not found"));    }

    @Override
    public void update(SubjectDto subjectDto) {
        SubjectMapper.mapFrom(subjects);

    }

    @Override
    public void delete(Long id) {
         SubjectMapper.mapFrom(subjects);

    }
}
