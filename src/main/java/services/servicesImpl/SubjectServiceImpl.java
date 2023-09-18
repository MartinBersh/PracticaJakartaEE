package services.servicesImpl;

import lombok.NoArgsConstructor;
import org.example.conexion.ConexionDB;
import org.example.domain.Subject;
import org.example.domain.Teacher;
import org.example.mapping.dto.SubjectDto;
import org.example.mapping.mappers.SubjectMapper;
import repository.repositoryImpl.SubjectRepositoryImp;
import services.SubjectService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor


public class SubjectServiceImpl implements SubjectService {
    SubjectRepositoryImp repo = new SubjectRepositoryImp();

    @Override
    public List<SubjectDto> list() {
        return repo.list();
    }

    @Override
    public SubjectDto byId(Long id) {
        return repo.byId(id);
    }

    @Override
    public void update(SubjectDto subject) {
        repo.update(subject);
    }

    @Override
    public void delete(Long id) {
        repo.delete(id);
    }
}
