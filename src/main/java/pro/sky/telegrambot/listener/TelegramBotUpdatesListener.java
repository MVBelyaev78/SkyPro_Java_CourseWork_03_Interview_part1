package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.configuration.TelegramBotConfiguration;
import pro.sky.telegrambot.model.NotificationTask;
import pro.sky.telegrambot.repository.NotificationTaskRepository;
import pro.sky.telegrambot.utility.ProcessResult;
import pro.sky.telegrambot.utility.ProcessUtility;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private final NotificationTaskRepository notificationTaskRepository;

    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    @Autowired
    private final TelegramBot telegramBot = (new TelegramBotConfiguration()).telegramBot();

    public TelegramBotUpdatesListener(NotificationTaskRepository notificationTaskRepository) {
        this.notificationTaskRepository = notificationTaskRepository;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            // Process your updates here
            final long chatId = update.message().chat().id();
            ProcessUtility processUtility = new ProcessUtility();
            processUtility.process(update.message().text());
            if (processUtility.getResult() == ProcessResult.DATABASE ||
                    processUtility.getResult() == ProcessResult.BOTH) {
                notificationTaskRepository.save(new NotificationTask(chatId,
                        processUtility.getNotificationTask(),
                        processUtility.getDateTime()));
            }
            if (processUtility.getResult() == ProcessResult.TELEGRAM ||
                    processUtility.getResult() == ProcessResult.BOTH) {
                telegramBot.execute(new SendMessage(chatId, processUtility.getTelegramMessage()));
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

}
