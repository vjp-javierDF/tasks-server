package dev.app.tasks;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {
    @Autowired
    private TaskService service;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    // find all tasks
    @GetMapping("/")
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = service.findAllTasks();
        return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
    }

    // find all idUser Tasks
    @GetMapping("/user/{idUser}")
    public ResponseEntity<List<Task>> findTasksByidUser(@PathVariable String idUser) {
        List<Task> tasks = service.findTasksByidUser(idUser);
        return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
    }

    // find a single task
    @GetMapping("/{taskID}")
    public ResponseEntity<Task> getSingleTask(@PathVariable String taskID) {
        Task task = service.findTaskById(taskID);
        return new ResponseEntity<Task>(task, HttpStatus.OK);
    }

    // insert
    @PostMapping("/new")
    public ResponseEntity<Task> createTask(@RequestBody Map<String, Object> payload) {
        LocalDate date = null;
        try {
            String dateStr = (String) payload.get("date");
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
            date = LocalDate.parse(dateStr, formatter);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String timeStr = (String) payload.get("time");

        ArrayList<String> items = (ArrayList<String>) payload.get("items");
        Task newTask = service.createTask((String) payload.get("title"),
                Date.from(date.atStartOfDay(ZoneOffset.UTC).toInstant()), items, // Use UTC instead of system default
                (String) payload.get("priority"), (String) payload.get("status"), (String) payload.get("idUser"),
                timeStr);
        messagingTemplate.convertAndSend("/topic/updates", "");
        return new ResponseEntity<Task>(newTask, HttpStatus.OK);
    }

    // delete task by taskID
    @DeleteMapping("/delete/{taskID}")
    public ResponseEntity<Void> deleteTask(@PathVariable String taskID) {
        service.deleteTask(taskID);
        messagingTemplate.convertAndSend("/topic/updates", "");
        System.out.println("DELETE TASK " + taskID);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // update task by taskID
    @PutMapping("/update/{taskID}")
    public ResponseEntity<Task> updateTask(@PathVariable String taskID, @RequestBody Map<String, Object> payload) {
        LocalDate date = null;
        try {
            String dateStr = (String) payload.get("date");
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
            date = LocalDate.parse(dateStr, formatter);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String timeStr = (String) payload.get("time");

        ArrayList<String> items = (ArrayList<String>) payload.get("items");
        Task updatedTask = service.updateTask(taskID, payload.get("title").toString(),
                Date.from(date.atStartOfDay(ZoneOffset.UTC).toInstant()), // Use UTC instead of system default
                payload.get("priority").toString(), items, payload.get("status").toString(), timeStr);
        if (updatedTask != null) {
            messagingTemplate.convertAndSend("/topic/updates", "");
            System.out.println("UPDATE TASK " + taskID);
            return new ResponseEntity<Task>(updatedTask, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
