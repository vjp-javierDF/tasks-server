package dev.app.tasks.notifications;

import dev.app.tasks.users.User;

public class InviteRequest {
    private User userSender;
    private String email;

    public User getUserSender() {
        return userSender;
    }
    public void setUserSender(User userSender) {
        this.userSender = userSender;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }    
}
