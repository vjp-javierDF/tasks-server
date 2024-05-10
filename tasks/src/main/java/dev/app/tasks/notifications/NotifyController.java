package dev.app.tasks.notifications;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.app.tasks.users.User;
import dev.app.tasks.users.UserService;

@RestController
@RequestMapping("/api/v1/notify")
public class NotifyController {
    @Autowired
    private NotifyService notifyService;
    @Autowired
    private UserService userService;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    // find notify by idUser
    @GetMapping("/messages/{idUser}")
    public ResponseEntity<List<Notification>> findNotifyByidUser(@PathVariable String idUser) {
        List<Notification> notifications = notifyService.findNotifyByidUser(idUser);
        return new ResponseEntity<List<Notification>>(notifications, HttpStatus.OK);
    }
    // create a notification with taskID, email and userSender id
    @PostMapping("/invite/{taskID}")
    public ResponseEntity<Void> InviteWithNotify(@PathVariable String taskID,
            @RequestBody InviteRequest inviteRequest) {
        User userSender = inviteRequest.getUserSender();
        String email = inviteRequest.getEmail();
        User userReceiver = userService.SearchEmailIntheDatabase(email);

        if (userReceiver != null) {
            notifyService.createNotification(userReceiver.getIdUser(), userSender, taskID);
            messagingTemplate.convertAndSend("/topic/updates", "");
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    // delete a notification by id notification
    @DeleteMapping("/delete/{idNotification}")
    public ResponseEntity<Void> deleteTask(@PathVariable String idNotification) {
        notifyService.deleteNotification(idNotification);
        messagingTemplate.convertAndSend("/topic/updates", "");
        System.out.println("DELETE NOTIFICATION " + idNotification);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
