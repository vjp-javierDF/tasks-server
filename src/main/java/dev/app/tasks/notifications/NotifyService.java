package dev.app.tasks.notifications;

    import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.app.tasks.users.User;

@Service
public class NotifyService {
    @Autowired
    private NotifyRepository notifyRepository;
    // create new notification
    public Notification createNotification(String idUserReceiver, User userSender, String taskID) {
        String idNotification = UUID.randomUUID().toString();
        Notification notification = new Notification();
        notification.setIdNotification(idNotification);
        notification.setIdUserReceiver(idUserReceiver);
        notification.setIdUserSender(userSender.getIdUser());
        notification.setTaskID(taskID);
        String message = "Hello, \n you are invited to a task. Click the link to see details and collaborate.";

        notification.setMessage(message);
        String link = "/show/" + taskID;
        notification.setLink(link);
        return notifyRepository.save(notification);
    }
    // delete notification by notification id
    public void deleteNotification(String idNotification) {
        notifyRepository.deleteByIdNotification(idNotification);
    }
    // find notification by id user receiver
    public List<Notification> findNotifyByidUser(String idUserReceiver){
        return notifyRepository.findNotifyByidUserReceiver(idUserReceiver);
    }
}
