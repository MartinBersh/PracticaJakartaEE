package repository.repositoryImpl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;
import org.example.anotations.MysqlConn;
import org.example.domain.Grades;
import org.example.domain.Student;
import org.example.domain.Subject;
import org.example.domain.Teacher;
import org.example.mapping.dto.GradesDto;
import org.example.mapping.mappers.GradesMapper;
import repository.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@NoArgsConstructor

public class GradesRepositoryImp implements Repository<GradesDto> {

    @Inject
    @MysqlConn
    private Connection conn;
    public GradesRepositoryImp(Connection conn) {
        this.conn = conn;
    }


    private Grades buildObject(ResultSet resultSet) throws SQLException {
        Grades grades = new Grades();
        grades.setId_Grades(resultSet.getLong("id_grades"));
        grades.setCorte(resultSet.getString("corte"));

        Student student = new Student();
        student.setId_Student(resultSet.getLong("id_student"));
        student.setName(resultSet.getString("name"));
        student.setEmail(resultSet.getString("email"));
        student.setCareer(resultSet.getString("career"));
        student.setSemester(resultSet.getString("semester"));
        grades.setStudent(student);

        Subject subject = new Subject();
        subject.setId_Subject(resultSet.getLong("id_subject"));
        subject.setName(resultSet.getString("name"));

        Teacher teacher = new Teacher();
        teacher.setId_Teacher(resultSet.getLong("id_teacher"));
        teacher.setName(resultSet.getString("name"));
        teacher.setEmail(resultSet.getString("email"));
        subject.setTeacher(teacher);

        grades.setSubject(subject);

        return grades;
    }

    @Override
    public List<GradesDto> list() {
        List<Grades> gradesList = new ArrayList<>();
        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT student.id_student ,student.name, student.email," +
                     " student.career, student.semester, subject.name, teachers.name, teachers.email, grades.corte FROM" +
                     " grades INNER JOIN student on grades.id_student=student.id_student INNER JOIN subject on " +
                     "grades.id_subject=subject.id_subject inner join teachers on " +
                     "subject.id_teacher=teachers.id_teacher;")) {
            while (resultSet.next()) {
                Grades grades = buildObject(resultSet);
                gradesList.add(grades);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return GradesMapper.mapFrom(gradesList);
    }

    @Override
    public GradesDto byId(Long id) {
        Grades grades = null;
        try (PreparedStatement preparedStatement = conn
                .prepareStatement("SELECT student.id_student ,student.name, student.email, student.career, " +
                        "student.semester, subject.name, teachers.name, teachers.email, grades.corte FROM grades " +
                        "INNER JOIN student on grades.id_student=student.id_student INNER JOIN subject on " +
                        "grades.id_subject=subject.id_subject inner join teachers on " +
                        "subject.id_teacher=teachers.id_teacher WHERE grades.id_grades = ?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                grades = buildObject(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return GradesMapper.mapFrom(grades);
    }


    @Override
    public void update(GradesDto grades) {
        String sql;
        if (grades.id_Grades() != null && grades.id_Grades() > 0) {
            sql = "UPDATE grades SET id_student=?, id_subject=? , corte=?  WHERE id_grades=?";
        } else {
            sql = "INSERT INTO grades (id_student, id,subject, corte) VALUES(?,?)";
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, grades.student().getId_Student());
            stmt.setLong(2, grades.subject().getId_Subject());
            stmt.setString(3, grades.corte());

            if (grades.id_Grades() != null && grades.id_Grades() > 0) {
                stmt.setLong(4, grades.id_Grades());
            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM gradess WHERE id_grades =?")) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
