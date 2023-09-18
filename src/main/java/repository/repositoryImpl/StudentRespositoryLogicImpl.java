package repository.repositoryImpl;

import org.example.domain.Student;
import org.example.exception.UniversityException;
import org.example.mapping.dto.StudentDto;
import org.example.mapping.mappers.StudentMapper;
import org.example.mapping.mappers.TeacherMapper;
import repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class StudentRespositoryLogicImpl implements Repository<StudentDto> {

    private List<Student> students;

    public StudentRespositoryLogicImpl(){
        Student s1 = new Student(1L,"Mieguel", "Mgaliendo@cue.edu.com",
                "4","Ing. industrial" );
        Student s2 = new Student(2L,"Felipe", "Felipe@cue.edu.com",
                "3", "Ing. software");
        Student s3 = new Student(3L,"Ricardo", "Ricardo@cue.edu.com",
                "2","Derecho");
        students = new ArrayList<>(List.of(s1, s2,s3));
    }
    @Override
    public List<StudentDto> list() {
        return StudentMapper.mapFrom(students);
    }

    @Override
    public StudentDto byId(Long id) {
        return students.stream()
                .filter(e->e.getId_Student() == (e.getId_Student()))
                .findFirst()
                .map(StudentMapper::mapFrom)
                .orElseThrow(()-> new UniversityException("Student not found"));
    }

    @Override
    public void update(StudentDto student) {
         StudentMapper.mapFrom(students);

    }

    @Override
    public void delete(Long id) {
         StudentMapper.mapFrom(students);

    }

}
