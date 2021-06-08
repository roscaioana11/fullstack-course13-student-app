package ro.fasttrackit.curs13.student.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.curs13.exceptions.ResourceNotFoundException;
import ro.fasttrackit.curs13.student.dto.Student;
import ro.fasttrackit.curs13.student.model.mappers.StudentMappers;
import ro.fasttrackit.curs13.student.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService service;
    private final StudentMappers mapper;

    @GetMapping
    List<Student> getAll() {
        return mapper.toApi(service.getAll());
    }

    @GetMapping("{studentId}")
    Student getStudent(@PathVariable String studentId) {
        return service.getStudent(studentId)
                .map(mapper::toApi)
                .orElseThrow(() -> new ResourceNotFoundException("Student with id " + studentId + " is not found"));
    }

    @PostMapping
    Student addStudent(@RequestBody Student student) {
        return mapper.toApi(service.addStudent(mapper.toDb(student)));

    }

    @DeleteMapping("{studentId}")
    Student deleteStudent(@PathVariable String studentId) {
        return service.deleteStudent(studentId)
                .map(mapper::toApi)
                .orElse(null);
    }
}
