package services.servicesImpl;

import lombok.NoArgsConstructor;
import org.example.conexion.ConexionDB;
import org.example.domain.Student;
import org.example.exception.ServiceJdbcException;
import org.example.mapping.dto.StudentDto;
import org.example.mapping.mappers.StudentMapper;
import repository.Repository;
import repository.repositoryImpl.StudentRepositoryImp;
import repository.repositoryImpl.StudentRespositoryLogicImpl;
import services.StudentService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor

public class StudentServiceImpl implements StudentService {

    private Repository<StudentDto> repo;
    public StudentServiceImpl(Connection connection) {
        this.repo = new StudentRepositoryImp(connection);
    }

    @Override
    public List<StudentDto> list() {
        return repo.list();
    }

    @Override
    public StudentDto byId(Long id) {
        return repo.byId(id);
    }

    @Override
    public void update(StudentDto student) {
        repo.update(student);
    }

    @Override
    public void delete(Long id) {
        repo.delete(id);
    }
}
