package pro.sky.telegrambot.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.NotificationTask;
import pro.sky.telegrambot.repository.NotificationTaskRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class Scheduler {

    private final Logger logger = LoggerFactory.getLogger(Scheduler.class);

    final NotificationTaskRepository notificationTaskRepository;

    public Scheduler(NotificationTaskRepository notificationTaskRepository) {
        this.notificationTaskRepository = notificationTaskRepository;
    }

    @Scheduled(cron = "0 0/1 * * * *")
    void run() {
        final LocalDateTime dt = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        logger.info("datetime: " + dt);
        for (NotificationTask nt : notificationTaskRepository.findByDateTime(dt)) {
            logger.info("datetime: " + dt + " iteration");
            logger.info(String.valueOf(nt));
        }
    }
}
