package org.example.services.servicesImpl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import org.example.anotations.Login;
import org.example.mapping.dto.TeacherDto;
import org.example.services.TeacherService;
import repository.Repository;


import java.util.List;
@NoArgsConstructor
@ApplicationScoped

public class TeacherServiceImpl implements TeacherService {

    @Inject
    private Repository<TeacherDto> repo;

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
