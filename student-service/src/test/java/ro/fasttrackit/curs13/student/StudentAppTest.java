package ro.fasttrackit.curs13.student;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ro.fasttrackit.curs13.student.dto.Student;
import ro.fasttrackit.curs13.student.model.StudentEntity;
import ro.fasttrackit.curs13.student.repository.StudentRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentAppTest {
    @Autowired
    MockMvc mvc;
    @Autowired
    StudentRepository repository;

    @Test
    @DisplayName("GET /students")
    @SneakyThrows
    void getStudentsTest() {
        repository.save(StudentEntity.builder()
                .name("Ana")
                .age(33)
                .build());

        MvcResult mvcResult = mvc.perform(get("/students"))
                .andDo(print())
                .andReturn();
        String strResponse = mvcResult.getResponse().getContentAsString();
        List<Student> result = new ObjectMapper().readValue(strResponse, new TypeReference<List<Student>>() {
        });

        assertThat(result)
                .extracting("name", "age")
                .containsExactly(tuple("Ana", 33));
    }
}
