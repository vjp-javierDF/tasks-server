package dev.app.tasks.users;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.app.tasks.TaskRepository;
import dev.app.tasks.notifications.NotifyRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired 
    private TaskRepository taskRepository;
    @Autowired 
    private NotifyRepository notifyRepository;
    private PasswordEncript encript = new PasswordEncript();

    // user register
    public User register(String email, String name, String password, String city) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        User user = new User();
        String idUser = UUID.randomUUID().toString();
        user.setIdUser(idUser);
        user.setEmail(email);
        user.setName(name);
        user.setPassword(encript.encryptPassword(password));
        user.setCity(city);
        return userRepository.save(user);
    }

    // login
    public User login(String email, String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(encript.encryptPassword(password))) {
            return user;
        } else {
            return null;
        }
    }

    // search email in the database
    public User SearchEmailIntheDatabase(String email) {
        User user = userRepository.findByEmail(email);
        if( user != null){
            return user;
        }else{
            return null;
        }
    }

    // update user data
    public User updatedUser(String idUser, String name, String email, String city) {
        User user = userRepository.findByIdUser(idUser);
        if (user != null) {
            User userNew = user;
            userNew.setName(name);
            userNew.setEmail(email);
            userNew.setCity(city);
            userRepository.save(userNew);
            return userNew;
        } else {
            return null;
        }
    }
    // change user password
    public User changePasswordUser(String idUser, String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        User user = userRepository.findByIdUser(idUser);
        if (user != null) {
            User userNew = user;
            userNew.setPassword(encript.encryptPassword(password));
            userRepository.save(userNew);
            return userNew;
        } else {
            return null;
        }
    }

    // delete user
    public void deleteUser(String idUser) {
        userRepository.deleteByIdUser(idUser);
    }
    // delete all tasks by user id
    public void deleteAllTasks(String idUser) {
        taskRepository.deleteAllByidUser(idUser);
    }
    // delete all notifications by user sender
    public void deleteAllNotificationsSender(String idUserSender) {
        notifyRepository.deleteAllByidUserSender(idUserSender);
    }
    // delete all notifications by user reciver
    public void deleteAllNotificationsReceiver(String idUserReceiver) {
        notifyRepository.deleteAllByidUserReceiver(idUserReceiver);
    }
}
