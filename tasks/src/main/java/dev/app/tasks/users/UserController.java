package dev.app.tasks.users;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    // Register user
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody Map<String, Object> payload) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String email = (String) payload.get("email");
        String name = (String) payload.get("name");
        String password = (String) payload.get("password");
        String city = (String) payload.get("city");
        User newUser = userService.register(email, name, password, city);
        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }

    // login
    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody Map<String, Object> payload) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String email = (String) payload.get("email");
        String password = (String) payload.get("password");
        User loggedUser = userService.login(email, password);
        if (loggedUser != null) {
            return new ResponseEntity<User>(loggedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
    // update user account
    @PutMapping("/update/{idUser}")
    public ResponseEntity<User> updateUser(@PathVariable String idUser, @RequestBody Map<String, Object> payload) {
        User updatedUser = userService.updatedUser(idUser, payload.get("name").toString(),
                payload.get("email").toString(), payload.get("city").toString());
        if (updatedUser != null) {
            return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // change password by user id
    @PutMapping("/changePassword/{idUser}")
    public ResponseEntity<User> changePasswordUser(@PathVariable String idUser, @RequestBody Map<String, Object> payload) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String password = payload.get("password").toString();
        System.out.println(password);
        User changePasswordUser = userService.changePasswordUser(idUser, password);
        if (changePasswordUser != null) {
            return new ResponseEntity<User>(changePasswordUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    // delete user account
    @DeleteMapping("/delete/{idUser}")
    public ResponseEntity<Void> deleteUser(@PathVariable String idUser) {
        userService.deleteAllNotificationsSender(idUser);
        userService.deleteAllNotificationsReceiver(idUser);
        userService.deleteAllTasks(idUser);
        userService.deleteUser(idUser);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
