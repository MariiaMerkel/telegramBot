package pro.sky.telegrambot.listener;

import static org.assertj.core.api.Assertions.assertThat;

import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import java.time.format.DateTimeFormatter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class TelegramBotUpdatesListenerTest {

//    @Autowired
//    private TelegramBotUpdatesListener telegramBotUpdatesListener;

    @Autowired
    private TestRestTemplate testRestTemplate;

    private String url;

    @BeforeEach
    void setUp() {
        url = "https://api.telegram.org/bot6251835362:AAF3UEyXNaXhkFyfWsmbMrZNJTgNWnqbduM/sendMessage?text=hello&chat_id=1134627004";
    }

//    @Test
//    void contextLoads() {
//        Assertions.assertThat(telegramBotUpdatesListener).isNotNull();
//    }

    @Test
    void successSendMessage() {

        String sendMessageText = String.format(
                "Задача была добавлена в расписание, и я напомню %s в %s",
                "Hello",
                "2022-04-30 20:00");
        SendMessage sendMessage = new SendMessage(1134627004, sendMessageText);

        String actual = testRestTemplate.postForObject(url, sendMessage, String.class);
        //SendResponse response = telegramBot.execute(sendMessage);

        assertThat(actual).isEqualTo("Hello");
    }
}
