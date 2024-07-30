package pro.sky.telegrambot.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class NotificationTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer chat_id;
    private String message;
    private LocalDate datetime;

    public NotificationTask() {
    }

    public NotificationTask(Long id, Integer chat_id, String message, LocalDate datetime) {
        this.id = id;
        this.chat_id = chat_id;
        this.message = message;
        this.datetime = datetime;
    }
}
