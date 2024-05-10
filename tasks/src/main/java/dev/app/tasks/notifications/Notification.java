package dev.app.tasks.notifications;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// NOTIFICATIONS
@Document(collection = "notifications")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    @Id
    private ObjectId _id;
    private String idNotification;
    private String idUserSender;
    private String idUserReceiver;
    private String taskID;
    private String message;
    private String link;

    public Notification(String idNotification, String idUserSender, String idUserReceiver, String taskID,
            String message, String link) {
        this.idNotification = idNotification;
        this.idUserSender = idUserSender;
        this.idUserReceiver = idUserReceiver;
        this.taskID = taskID;
        this.message = message;
        this.link = link;
    }
}
