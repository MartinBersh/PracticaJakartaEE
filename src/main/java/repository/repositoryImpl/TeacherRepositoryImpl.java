package repository.repositoryImpl;

import org.example.conexion.ConexionDB;
import org.example.domain.Teacher;
import org.example.mapping.dto.TeacherDto;
import org.example.mapping.mappers.TeacherMapper;
import repository.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherRepositoryImpl implements Repository<TeacherDto> {
    private Connection getConnection() throws SQLException {
        return ConexionDB.getInstance();
    }

    private Teacher buildObject(ResultSet resultSet) throws
            SQLException {
        Teacher teacher = new Teacher();
        teacher.setId_Teacher(resultSet.getLong("id_teacher"));
        teacher.setName(resultSet.getString("name"));
        teacher.setEmail(resultSet.getString("email"));

        return teacher;
    }

    @Override
    public List<TeacherDto> list() {
        List<Teacher> teacherList = new ArrayList<>();
        try (Statement statement = getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * from teachers")) {
            while (resultSet.next()) {
                Teacher teacher = buildObject(resultSet);
                teacherList.add(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return TeacherMapper.mapFrom(teacherList);
    }

    @Override
    public TeacherDto byId(Long id) {
        Teacher teacher = null;
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement("SELECT * FROM teachers WHERE id_teachers =?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                teacher = buildObject(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return TeacherMapper.mapFrom(teacher);
    }

    @Override
    public void update(TeacherDto teacher) {
        String sql;
        if (teacher.id_Teacher() != null && teacher.id_Teacher() > 0) {
            sql = "UPDATE teachers SET name=?, email=? WHERE id_teacher=?";
        } else {
            sql = "INSERT INTO teachers (name, email) VALUES(?,?)";
        }
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, teacher.name());
            stmt.setString(2, teacher.email());

            if (teacher.id_Teacher() != null && teacher.id_Teacher() > 0) {
                stmt.setLong(3, teacher.id_Teacher());
            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try(PreparedStatement stmt = getConnection().prepareStatement("DELETE FROM teachers WHERE id_teachers =?")) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
}

