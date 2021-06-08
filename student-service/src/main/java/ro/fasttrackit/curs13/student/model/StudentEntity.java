package ro.fasttrackit.curs13.student.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("students")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentEntity {
    @Id
    private String id;
    private String name;
    private int age;
}
