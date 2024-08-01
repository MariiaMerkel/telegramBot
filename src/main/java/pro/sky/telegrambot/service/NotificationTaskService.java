package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.model.Message;
import java.util.List;
import pro.sky.telegrambot.entity.NotificationTask;

public interface NotificationTaskService {

    NotificationTask add(Message message);

    List<NotificationTask> getByDateTime();
}
