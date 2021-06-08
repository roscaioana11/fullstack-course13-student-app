package ro.fasttrackit.curs13.student.model.mappers;

import org.springframework.stereotype.Component;
import ro.fasttrackit.curs13.student.dto.Student;
import ro.fasttrackit.curs13.student.model.StudentEntity;
import ro.fasttrackit.curs13.utils.ModelMappers;

@Component
public class StudentMappers implements ModelMappers<Student, StudentEntity> {

    public Student toApi(StudentEntity source) {
        return Student.builder()
                .id(source.getId())
                .name(source.getName())
                .age(source.getAge())
                .build();
    }

    public StudentEntity toDb(Student source) {
        return StudentEntity.builder()
                .id(source.getId())
                .name(source.getName())
                .age(source.getAge())
                .build();
    }
}
