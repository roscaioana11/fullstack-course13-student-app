package ro.fasttrackit.curs13.student.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ro.fasttrackit.curs13.student.dto.Student;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;

@Slf4j
@Component
public class StudentApiClient {
    private final String baseUrl;
    private final RestTemplate rest;

    public StudentApiClient(@Value("${student.service.location:NOT_DEFINED}") String baseUrl) {
        this.baseUrl = baseUrl;
        this.rest = new RestTemplate();
    }

    public Student addStudent(Student student) {
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path("/students")
                .toUriString();

        return rest.postForObject(url, student, Student.class);
    }

    public List<Student> getAllStudents() {
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path("/students")
                .toUriString();
        log.info("Getting all students: " + url);
        return rest.exchange(url, GET, new HttpEntity<>(null), new ParameterizedTypeReference<List<Student>>() {
        }).getBody();
    }

    public Student deleteStudent(String studentId) {
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path("/students/")
                .path(studentId)
                .toUriString();
        return rest.exchange(url, DELETE, new HttpEntity<>(null), Student.class).getBody();
    }

    public Optional<Student> getById(String studentId) {
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path("/students/")
                .path(studentId)
                .toUriString();
        try {
            return ofNullable(rest.getForObject(url, Student.class));
        } catch (HttpClientErrorException ex) {
            return empty();
        }
    }
}
