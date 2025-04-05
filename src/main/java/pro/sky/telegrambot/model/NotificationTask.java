package pro.sky.telegrambot.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tbl_notification_task", schema = "public")
public class NotificationTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notification_task")
    private Long id;

    @Column(name = "nn_chat")
    private Long chatNumber;

    @Column(name = "vl_notification_task")
    private String notificationTask;

    @Column(name = "dt_notification_task")
    private LocalDateTime dateTime;

    public NotificationTask() {
    }

    public NotificationTask(Long chatNumber, String notificationTask, LocalDateTime dateTime) {
        this.chatNumber = chatNumber;
        this.notificationTask = notificationTask;
        this.dateTime = dateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChatNumber() {
        return chatNumber;
    }

    public void setChatNumber(Long chatNumber) {
        this.chatNumber = chatNumber;
    }

    public String getNotificationTask() {
        return notificationTask;
    }

    public void setNotificationTask(String notificationTask) {
        this.notificationTask = notificationTask;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object object) {
        return (this == object ||
                object != null &&
                        getClass() == object.getClass() &&
                        Objects.equals(chatNumber, ((NotificationTask) object).chatNumber) &&
                        Objects.equals(notificationTask, ((NotificationTask) object).notificationTask) &&
                        Objects.equals(dateTime, ((NotificationTask) object).dateTime));
    }

    @Override
    public int hashCode() {
        return Objects.hash(chatNumber, notificationTask, dateTime);
    }

    @Override
    public String toString() {
        return String.format("NotificationTask: {id=%s, chatNumber='%s', notificationTask=%s, dateTime=%s}",
                id, chatNumber, notificationTask, dateTime);
    }
}
