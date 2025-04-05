package pro.sky.telegrambot.utility;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ProcessUtility {

    private String telegramMessage;
    private LocalDateTime dateTime;
    private String notificationTask;
    private ProcessResult result = ProcessResult.NONE;

    public ProcessUtility() {
    }

    public String getTelegramMessage() {
        return telegramMessage;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getNotificationTask() {
        return notificationTask;
    }

    public ProcessResult getResult() {
        return result;
    }

    public void process(String message) {
        if (message.equals("/start")) {
            result = ProcessResult.TELEGRAM;
            telegramMessage = "Hello!";
        } else {
            final Matcher matcher = Pattern
                    .compile("(\\d{2}\\.\\d{2}\\.\\d{4}\\s\\d{2}:\\d{2})(\\s+)(.+)")
                    .matcher(message);
            if (matcher.matches()) {
                result = ProcessResult.BOTH;
                notificationTask = matcher.group(3);
                dateTime = LocalDateTime.parse(matcher.group(1), DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
                telegramMessage = "Notification saved";
            }
        }
    }
}
