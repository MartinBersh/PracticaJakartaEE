package org.example.services.servicesImpl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.NoArgsConstructor;
import org.example.anotations.Login;
import org.example.mapping.dto.StudentDto;
import org.example.services.StudentService;
import repository.Repository;

import java.util.List;
@NoArgsConstructor
@ApplicationScoped

public class StudentServiceImpl implements StudentService {

    @Inject
    @Named("defaultRepository")
    private Repository<StudentDto> repo;

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
