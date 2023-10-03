package services;

import org.example.mapping.dto.GradesDto;

import java.util.List;

public interface GradesService {

    List<GradesDto> list();
    GradesDto byId(Long id);
    void update(GradesDto t);
    void delete(Long id);

}
