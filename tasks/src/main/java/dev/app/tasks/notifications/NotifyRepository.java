package dev.app.tasks.notifications;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotifyRepository extends MongoRepository<Notification, String> {
    void deleteByIdNotification(String idNotification);
    void deleteAllByidUserSender(String idUserSender);
    void deleteAllByidUserReceiver(String idUserReceiver);
    List<Notification> findNotifyByidUserReceiver(String idUserReceiver);
}
