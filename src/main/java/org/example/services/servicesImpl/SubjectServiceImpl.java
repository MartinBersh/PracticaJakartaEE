package org.example.services.servicesImpl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import org.example.anotations.Login;
import org.example.mapping.dto.SubjectDto;
import org.example.services.SubjectService;
import repository.Repository;

import java.util.List;
@NoArgsConstructor
@ApplicationScoped

public class SubjectServiceImpl implements SubjectService {

    @Inject
    private Repository<SubjectDto> repo;
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
