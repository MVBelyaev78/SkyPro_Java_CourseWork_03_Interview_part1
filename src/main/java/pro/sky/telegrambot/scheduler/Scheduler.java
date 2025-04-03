package pro.sky.telegrambot.scheduler;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.configuration.TelegramBotConfiguration;
import pro.sky.telegrambot.repository.NotificationTaskRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class Scheduler {

    final NotificationTaskRepository notificationTaskRepository;

    @Autowired
    private final TelegramBot telegramBot = (new TelegramBotConfiguration()).telegramBot();

    public Scheduler(NotificationTaskRepository notificationTaskRepository) {
        this.notificationTaskRepository = notificationTaskRepository;
    }

    @Scheduled(cron = "0 0/1 * * * *")
    void run() {
        notificationTaskRepository
                .findByDateTime(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES))
                .forEach(notificationTask -> {
            telegramBot.execute(new SendMessage(notificationTask.getChatNumber(),
                    notificationTask.getNotificationTask()));
        });
    }
}
