package services.servicesImpl;

import lombok.NoArgsConstructor;
import org.example.mapping.dto.SubjectDto;
import repository.Repository;
import repository.repositoryImpl.StudentRespositoryLogicImpl;
import repository.repositoryImpl.SubjectRepositoryImpl;
import repository.repositoryImpl.SubjectRepositoryLogicImpl;
import services.SubjectService;

import java.sql.Connection;
import java.util.List;
@NoArgsConstructor


public class SubjectServiceImpl implements SubjectService {
    private Repository<SubjectDto> repo;

    public SubjectServiceImpl(Connection connection){
        this.repo = new SubjectRepositoryImpl(connection);
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
