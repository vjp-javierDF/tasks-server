package dev.app.tasks;

import java.util.ArrayList;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "tasks")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    private ObjectId _id;
    private String taskID;
    private String title;
    private Date date;
    private ArrayList<String> items;
    private String priority;
    private String status;
    private String idUser;
    private String time;

    public Task(String taskID, String title, Date date, ArrayList<String> items, String priority, String status, String idUser, String time){
        this.taskID = taskID;
        this.title = title;
        this.date = date;
        this.items = items;
        this.priority = priority;
        this.status = status;
        this.idUser = idUser;
        this.time = time;
    }
}