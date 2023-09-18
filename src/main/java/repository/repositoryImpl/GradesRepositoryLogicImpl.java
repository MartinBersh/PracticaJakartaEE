package repository.repositoryImpl;

import org.example.domain.Grades;
import org.example.domain.Student;
import org.example.domain.Subject;
import org.example.domain.Teacher;
import org.example.mapping.dto.GradesDto;
import org.example.mapping.mappers.GradesMapper;
import repository.Repository;

import java.util.List;

public class GradesRepositoryLogicImpl implements Repository<GradesDto> {

    private List<Grades> grades;

    public GradesRepositoryLogicImpl(){
        Teacher t1 = new Teacher(1L, "Monica","mtobon@cue.edu.com");
        Teacher t2 = new Teacher(2L, "Arle","Armorales@cue.edu.com");

        Subject sub1 = new Subject(1L,"Programacion 1",t1);
        Subject sub2 = new Subject(2L,"Programacion 2", t2);

        Student s1 = new Student(1L,"Mieguel", "Mgaliendo@cue.edu.com",
                "4","Ing. industrial" );
        Student s2 = new Student(2L,"Felipe", "Felipe@cue.edu.com",
                "3", "Ing. software");

        Grades g1 = new Grades(1L,s1,sub1,"1");
        Grades g2 = new Grades(2L,s2,sub2,"3");
    }
    @Override
    public List<GradesDto> list() {
        return GradesMapper.mapFrom(grades);
    }

    @Override
    public GradesDto byId(Long id) {
        return null;
    }

    @Override
    public void update(GradesDto gradesDto) {
        GradesMapper.mapFrom(grades);
    }

    @Override
    public void delete(Long id) {
        GradesMapper.mapFrom(grades);
    }
}
