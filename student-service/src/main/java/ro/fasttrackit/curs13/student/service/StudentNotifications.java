package ro.fasttrackit.curs13.student.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ro.fasttrackit.curs13.student.events.StudentEvent;
import ro.fasttrackit.curs13.student.model.StudentEntity;
import ro.fasttrackit.curs13.student.model.mappers.StudentMappers;

import static ro.fasttrackit.curs13.student.events.StudentEventType.DELETED;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentNotifications {
    private final RabbitTemplate rabbit;
    private final StudentMappers mapper;
    private final FanoutExchange studentExchange;

    public void notifyStudentDeleted(StudentEntity student) {
        StudentEvent event = StudentEvent.builder()
                .student(mapper.toApi(student))
                .type(DELETED)
                .build();
        log.info("Sending event : " + event);
        rabbit.convertAndSend(studentExchange.getName(), "", event);
    }
}
