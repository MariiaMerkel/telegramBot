package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import jakarta.annotation.PostConstruct;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.entity.NotificationTask;
import pro.sky.telegrambot.exception.CustomNotificationTaskException;
import pro.sky.telegrambot.service.NotificationTaskService;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);
    private final NotificationTaskService notificationTaskService;
    private final TelegramBot telegramBot;

    public TelegramBotUpdatesListener(NotificationTaskService notificationTaskService, TelegramBot telegramBot) {
        this.notificationTaskService = notificationTaskService;
        this.telegramBot = telegramBot;
    }


    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            Message message = update.message();
            try {
                NotificationTask notificationTask = notificationTaskService.add(message);
                sendMessage(notificationTask);
            } catch (CustomNotificationTaskException e) {
                sendMessage(message.chat().id(), e.getMessage());
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    private void sendMessage(NotificationTask notificationTask) {
        String sendMessageText = String.format(
                "Задача была добавлена в расписание, и я напомню %s в %s",
                notificationTask.getMessage(),
                notificationTask.getDateTime().
                                format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")));
        SendMessage sendMessage = new SendMessage(notificationTask.getChat_id(), sendMessageText);
        telegramBot.execute(sendMessage);
    }

    private void sendMessage(Long chat_id, String exception) {
        SendMessage sendMessage = new SendMessage(chat_id, exception);
        telegramBot.execute(sendMessage);
    }
}
