package repository.repositoryImpl;

import org.example.conexion.ConexionDB;
import org.example.domain.Student;
import org.example.mapping.dto.StudentDto;
import org.example.mapping.mappers.StudentMapper;
import repository.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepositoryImp implements Repository<StudentDto> {
    private Connection getConnection() throws SQLException {
        return ConexionDB.getInstance();
    }
    private Student createStudent(ResultSet rs) throws SQLException {
        Student student = new Student();
        student.setId_Student(rs.getLong("id_student"));
        student.setName(rs.getString("nombre"));
        student.setEmail(rs.getString("email"));
        student.setCareer(rs.getString("career"));
        student.setSemester(rs.getString("semester"));
        return student;
    }
    @Override
    public List<StudentDto> list() {
        List<Student> studentList = new ArrayList<>();

        try (Statement statement = getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * from student")) {
            while (resultSet.next()) {
                Student student = createStudent(resultSet);
                studentList.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return StudentMapper.mapFrom(studentList);
    }

    @Override
    public StudentDto byId(Long id) {
        Student student = null;
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement("SELECT * FROM student WHERE id_student=?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                student = createStudent(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return StudentMapper.mapFrom(student);
    }

    @Override
    public void update(StudentDto student) {
        String sql;
        if (student.id_Student() != null && student.id_Student()>0) {
            sql = "UPDATE student SET name=?, career=?, email=?, semester=? WHERE id_student=?";
        } else {
            sql = "INSERT INTO student (name, career, email, semester) VALUES(?,?,?,?)";
        }
        try(PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, student.name());
            stmt.setString(2, student.career());

            if (student.id_Student() != null && student.id_Student() > 0) {
                stmt.setString(3, student.email());
                stmt.setLong(4, student.id_Student());
            }
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try(PreparedStatement stmt = getConnection().prepareStatement("DELETE FROM student WHERE id_student =?")){
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
