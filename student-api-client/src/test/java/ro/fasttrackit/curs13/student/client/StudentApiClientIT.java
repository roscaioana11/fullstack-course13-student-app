package ro.fasttrackit.curs13.student.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ro.fasttrackit.curs13.student.dto.Student;

import java.util.List;

class StudentApiClientIT {
    private StudentApiClient studentClient;

    @BeforeEach
    void setup() {
        this.studentClient = new StudentApiClient("http://localhost:8000");
    }

    @Test
    void getAllStudents() {
        List<Student> allStudents = studentClient.getAllStudents();

        System.out.println(allStudents);
    }

    @Test
    void deleteStudent() {
        System.out.println(studentClient.deleteStudent("60be720dbc270d075d89a7e3"));
    }

    @Test
    void addAStudent() {
        studentClient.addStudent(Student.builder()
                .name("Ana")
                .age(33)
                .build());
    }
}
