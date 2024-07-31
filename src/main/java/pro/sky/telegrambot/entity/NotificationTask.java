package pro.sky.telegrambot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class NotificationTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long chat_id;
    private String message;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    public NotificationTask() {
    }

    public NotificationTask(Long id, Long chat_id, String message, LocalDateTime dateTime) {
        this.id = id;
        this.chat_id = chat_id;
        this.message = message;
        this.dateTime = dateTime;
    }

    @Override public String toString() {
        return "NotificationTask{" +
                "id=" + id +
                ", chat_id=" + chat_id +
                ", message='" + message + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NotificationTask that = (NotificationTask) o;
        return Objects.equals(id, that.id) && Objects.equals(chat_id, that.chat_id) && Objects.equals(message, that.message) && Objects.equals(dateTime, that.dateTime);
    }

    @Override public int hashCode() {
        return Objects.hash(id, chat_id, message, dateTime);
    }
}
