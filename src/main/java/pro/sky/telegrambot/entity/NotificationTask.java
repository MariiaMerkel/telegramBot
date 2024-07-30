package pro.sky.telegrambot.entity;

import java.sql.Time;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
public class NotificationTask {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Long chat_id;
    private String message;
    @Column(name="date_time")
    private Timestamp dateTime;

    public NotificationTask() {
    }

    public NotificationTask(Long id, Long chat_id, String message, Timestamp dateTime) {
        this.id = id;
        this.chat_id = chat_id;
        this.message = message;
        this.dateTime = dateTime;
    }
}
