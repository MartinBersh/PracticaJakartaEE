package repository.repositoryImpl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;
import org.example.anotations.MysqlConn;
import org.example.conexion.ConexionDB;
import org.example.domain.Student;
import org.example.exception.ServiceJdbcException;
import org.example.mapping.dto.StudentDto;
import org.example.mapping.mappers.StudentMapper;
import repository.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@ApplicationScoped
public class StudentRepositoryImp implements Repository<StudentDto> {

    @Inject
    @MysqlConn
    private Connection conn;
    public StudentRepositoryImp(Connection conn) {
        this.conn = conn;
    }

    private Student createStudent(ResultSet rs) throws SQLException {
        Student student = new Student();
        student.setId_Student(rs.getLong("id_student"));
        student.setName(rs.getString("name"));
        student.setEmail(rs.getString("email"));
        student.setCareer(rs.getString("career"));
        student.setSemester(rs.getString("semester"));
        return student;
    }
    @Override
    public List<StudentDto> list(){
        List<Student> studentList = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * from student")) {
            while (rs.next()) {
                Student ps= createStudent(rs);
                studentList.add(ps);
            }
        } catch (SQLException e) {
            throw new ServiceJdbcException("Unable to list info");
        }
        return StudentMapper.mapFrom(studentList);
    }


    @Override
    public StudentDto byId(Long id) {
        Student student = null;
        try (PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM student WHERE id_student=?")) {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    student = createStudent(rs);
                }
            }
        } catch (SQLException e) {
            throw new ServiceJdbcException("Unable to find info");
        }
        return StudentMapper.mapFrom(student);
    }

    @Override
    public void update(StudentDto student) {
        String sql;
        if (student.id_Student() != null && student.id_Student() > 0) {
            sql = "UPDATE student SET name=?, career=?, email=?, semester=? WHERE id_student=?";
        } else {
            sql = "INSERT INTO student (name, career, email, semester) VALUES(?,?,?,?)";
        }
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, student.name());
            pstmt.setString(2, student.career());
            pstmt.setString(3, student.email());
            pstmt.setString(4, student.semester());

            if (student.id_Student() != null && student.id_Student() > 0) {
                pstmt.setLong(5, student.id_Student());
            }
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException("Unable to save info");
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement pstmt = conn.prepareStatement("DELETE FROM student WHERE id_student = ?")) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new ServiceJdbcException("Unable to delete info");
        }
    }


}
