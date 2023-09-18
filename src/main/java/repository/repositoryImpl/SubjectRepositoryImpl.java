package repository.repositoryImpl;

import org.example.conexion.ConexionDB;
import org.example.domain.Subject;
import org.example.domain.Teacher;
import org.example.mapping.dto.SubjectDto;
import org.example.mapping.mappers.SubjectMapper;
import repository.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectRepositoryImpl implements Repository<SubjectDto> {
    private Connection getConnection() throws SQLException {
        return ConexionDB.getInstance();
    }

    private Subject buildObject(ResultSet resultSet) throws
            SQLException {
        Subject subject = new Subject();
        subject.setId_Subject(resultSet.getLong("id_subject"));
        subject.setName(resultSet.getString("name"));
        Teacher teacher = new Teacher();
        teacher.setId_Teacher(resultSet.getLong("id_teacher"));
        teacher.setName(resultSet.getString("name"));
        teacher.setEmail(resultSet.getString("email"));
        subject.setTeacher(teacher);

        return subject;
    }

    @Override
    public List<SubjectDto> list() {
        List<Subject> SubjectList = new ArrayList<>();
        try (Statement statement = getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT subject.name, teachers.name, teachers.email " +
                     "FROM subject INNER JOIN teachers on subject.id_teacher=teachers.id_teacher;")) {
            while (resultSet.next()) {
                Subject Subject = buildObject(resultSet);
                SubjectList.add(Subject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return SubjectMapper.mapFrom(SubjectList);
    }

    @Override
    public SubjectDto byId(Long id) {
        Subject Subject = null;
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement("SELECT subject.name, teachers.name, teachers.email FROM subject INNER JOIN " +
                        "teachers on subject.id_teacher=teachers.id_teacher WHERE subject.id_subject = ?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Subject = buildObject(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return SubjectMapper.mapFrom(Subject);
    }

    @Override
    public void update(SubjectDto Subject) {
        String sql;
        if (Subject.id_Subject() != null && Subject.id_Subject() > 0) {
            sql = "UPDATE subjects SET name=?, id_teacher=? WHERE id_subject=?";
        } else {
            sql = "INSERT INTO subjects (name, id_teacher) VALUES(?,?)";
        }
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, Subject.name());
            stmt.setLong(2, Subject.teacher().getId_Teacher());

            if (Subject.id_Subject() != null && Subject.id_Subject() > 0) {
                stmt.setLong(3, Subject.id_Subject());
            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try(PreparedStatement stmt = getConnection().prepareStatement("DELETE FROM subjects WHERE id_subject =?")) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
}
