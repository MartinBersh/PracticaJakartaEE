import org.example.conexion.ConexionDB;
import org.example.domain.Student;
import org.example.domain.Subject;
import org.example.domain.Teacher;
import org.example.mapping.dto.GradesDto;
import org.example.mapping.dto.StudentDto;
import org.example.mapping.dto.SubjectDto;
import org.example.mapping.dto.TeacherDto;
import services.servicesImpl.GradesServiceImpl;
import services.servicesImpl.StudentServiceImpl;
import services.servicesImpl.SubjectServiceImpl;
import services.servicesImpl.TeacherServiceImpl;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = ConexionDB.getInstance()) {
            StudentServiceImpl studentService = new StudentServiceImpl();
            TeacherServiceImpl teacherService = new TeacherServiceImpl();
            SubjectServiceImpl subjectService = new SubjectServiceImpl();
            GradesServiceImpl gradesService = new GradesServiceImpl();
            Student student = new Student();
            Subject subject = new Subject();
            Teacher teacher = new Teacher();

            System.out.println(studentService.list());
            System.out.println(studentService.byId(2L));
            studentService.update(new StudentDto(1L, "Juan Rojas", "jrojas@cue.edu.co", "IV", "Ingeniería"));
            //studentRepository.delete(1L);

            System.out.println(teacherService.list());
            System.out.println(teacherService.byId(1L));
            teacherService.update(new TeacherDto(1L, "Daniel Orozco", "dorozco@cue.edu.co"));
            //teacherRepository.delete(2L);

            System.out.println(subjectService.list());
            System.out.println(subjectService.byId(2L));
            subjectService.update(new SubjectDto(1L,"Bases de datos", teacher ));
            //subjectRepository.delete(1L);

            System.out.println(gradesService.list());
            System.out.println(gradesService.byId(1L));
            gradesService.update(new GradesDto(1L, student, subject, "3"));
            //gradesService.delete(1L);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}