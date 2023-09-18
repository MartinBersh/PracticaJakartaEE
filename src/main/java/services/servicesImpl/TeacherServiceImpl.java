package services.servicesImpl;

import lombok.NoArgsConstructor;
import org.example.conexion.ConexionDB;
import org.example.domain.Teacher;
import org.example.mapping.dto.TeacherDto;
import org.example.mapping.mappers.TeacherMapper;
import repository.repositoryImpl.StudentRespositoryLogicImpl;
import repository.repositoryImpl.TeacherRepositoryImpl;
import repository.repositoryImpl.TeacherRepositoryLogicImpl;
import services.TeacherService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor


public class TeacherServiceImpl implements TeacherService {
    TeacherRepositoryLogicImpl repo =  new TeacherRepositoryLogicImpl();

    public TeacherServiceImpl(TeacherRepositoryLogicImpl repository) {
        this.repo = repository;
    }
    @Override
    public List<TeacherDto> list() {
        return repo.list();
    }

    @Override
    public TeacherDto byId(Long id) {
        return repo.byId(id);
    }

    @Override
    public void update(TeacherDto teacher) {
        repo.update(teacher);
    }

    @Override
    public void delete(Long id) {
        repo.delete(id);
    }
}
