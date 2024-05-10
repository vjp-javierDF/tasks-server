package dev.app.tasks.users;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private ObjectId _id;
    private String idUser;
    private String name;
    private String email;
    private String password;
    private String city;

    public User(String idUser, String name, String email, String password, String city){
        this.idUser = idUser;
        this.name = name;
        this.email = email;
        this.password = password;
        this.city = city;
    }
}