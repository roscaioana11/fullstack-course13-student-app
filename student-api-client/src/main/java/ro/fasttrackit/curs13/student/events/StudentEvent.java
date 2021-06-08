package ro.fasttrackit.curs13.student.events;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;
import ro.fasttrackit.curs13.student.dto.Student;

@Value
@Builder
@JsonDeserialize(builder = StudentEvent.StudentEventBuilder.class)
public class StudentEvent {
    Student student;
    StudentEventType type;

    @JsonPOJOBuilder(withPrefix = "")
    public static class StudentEventBuilder {
    }
}
