package ro.fasttrackit.curs13.student.dto;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentSerializationTest {
    @Test
    void studentToJson() throws JsonProcessingException {
        Student student = new Student("abc", "Ana", 33);
        String result = new ObjectMapper().writeValueAsString(student);

        assertEquals(result, """
                {"id":"abc","name":"Ana","age":33}
                """.trim());
    }

    @Test
    void jsonToStudent() throws JsonProcessingException {
        String json = """
                {"id":"abc","name":"Ana","age":33}
                """;
        Student result = new ObjectMapper().readValue(json, Student.class);
        assertEquals(result, new Student("abc", "Ana", 33));
    }
}
