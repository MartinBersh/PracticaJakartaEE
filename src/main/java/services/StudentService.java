package services;

import org.example.mapping.dto.StudentDto;

import java.util.List;

public interface StudentService {

    List<StudentDto> list();
    StudentDto byId(Long id);
    void update(StudentDto t);
    void delete(Long id);

}
