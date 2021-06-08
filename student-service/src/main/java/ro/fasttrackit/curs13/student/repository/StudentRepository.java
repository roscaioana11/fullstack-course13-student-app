package ro.fasttrackit.curs13.student.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ro.fasttrackit.curs13.student.model.StudentEntity;

public interface StudentRepository extends MongoRepository<StudentEntity, String> {
}
