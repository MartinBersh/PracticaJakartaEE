package services.servicesImpl;

import lombok.NoArgsConstructor;
import org.example.conexion.ConexionDB;
import org.example.domain.Grades;
import org.example.domain.Student;
import org.example.domain.Subject;
import org.example.domain.Teacher;
import org.example.mapping.dto.GradesDto;
import org.example.mapping.dto.StudentDto;
import org.example.mapping.mappers.GradesMapper;
import repository.Repository;
import repository.repositoryImpl.GradesRepositoryImp;
import repository.repositoryImpl.StudentRepositoryImp;
import services.GradesService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor

public class GradesServiceImpl implements GradesService {

    private Repository<GradesDto> repo;
    public GradesServiceImpl(Connection connection) {
        this.repo = new GradesRepositoryImp(connection);
    }

    @Override
    public List<GradesDto> list() {
        return repo.list();
    }

    @Override
    public GradesDto byId(Long id) {
        return repo.byId(id);
    }

    @Override
    public void update(GradesDto grades) {
        repo.update(grades);
    }

    @Override
    public void delete(Long id) {
        repo.delete(id);
    }
}
