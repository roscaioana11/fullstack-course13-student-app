package ro.fasttrackit.curs13.student.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.fasttrackit.curs13.student.model.StudentEntity;
import ro.fasttrackit.curs13.student.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentNotifications studentNotifications;

    public List<StudentEntity> getAll() {
        return studentRepository.findAll();
    }

    public Optional<StudentEntity> deleteStudent(String studentId) {
        Optional<StudentEntity> student = studentRepository.findById(studentId);

        student.ifPresent(this::deleteExistingStudent);
        return student;
    }

    private void deleteExistingStudent(StudentEntity studentEntity) {
        log.info("Deleting " + studentEntity);
        studentRepository.delete(studentEntity);
        studentNotifications.notifyStudentDeleted(studentEntity);
    }

    public Optional<StudentEntity> getStudent(String studentId) {
        return studentRepository.findById(studentId);
    }

    public StudentEntity addStudent(StudentEntity newStudent) {
        newStudent.setId(null);
        return studentRepository.save(newStudent);
    }
}
