package org.example.services.servicesImpl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.example.anotations.Login;
import org.example.mapping.dto.GradesDto;
import org.example.services.GradesService;
import repository.Repository;

import java.util.List;

@ApplicationScoped

public class GradesServiceImpl implements GradesService {

    @Inject
    private Repository<GradesDto> gradeRepository;
    private Repository<GradesDto> repo;


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
