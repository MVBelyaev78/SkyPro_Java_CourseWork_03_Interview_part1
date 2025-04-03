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

    @Column(name = "kd_chat")
    private Integer chatNumber;

    @Column(name = "vl_notification_task")
    private String notificationTask;

    @Column(name = "dt_notification_task")
    private LocalDateTime dateTime;

    public static final Integer chatNumberTelegram = 1;

    public NotificationTask() {
    }

    public NotificationTask(Integer chatNumber, String notificationTask, LocalDateTime dateTime) {
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

    public Integer getChatNumber() {
        return chatNumber;
    }

    public void setChatNumber(Integer chatNumber) {
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
        return String.format("Student: {id=%s, chatNumber='%s', notificationTask=%s, dateTime=%s}",
                id, chatNumber, notificationTask, dateTime);
    }
}
