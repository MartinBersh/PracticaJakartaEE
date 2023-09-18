package services.servicesImpl;

import lombok.NoArgsConstructor;
import org.example.mapping.dto.SubjectDto;
import repository.repositoryImpl.StudentRespositoryLogicImpl;
import repository.repositoryImpl.SubjectRepositoryImpl;
import repository.repositoryImpl.SubjectRepositoryLogicImpl;
import services.SubjectService;

import java.util.List;
@NoArgsConstructor


public class SubjectServiceImpl implements SubjectService {
    SubjectRepositoryLogicImpl repo = new SubjectRepositoryLogicImpl();

    public SubjectServiceImpl(SubjectRepositoryLogicImpl repository) {
        this.repo = repository;
    }

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
