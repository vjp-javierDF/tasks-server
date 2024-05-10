package dev.app.tasks;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.app.tasks.collaborations.CollaborationsRepository;

@Service
public class TaskService {
    @Autowired
    private TaskRepository repository;
    @Autowired
    private CollaborationsRepository collaborationsRepository;

    // find all tasks
    public List<Task> findAllTasks() {
        return repository.findAll();
    }

    // find tasks by user id
    public List<Task> findTasksByidUser(String idUser) {
        return repository.findTasksByidUser(idUser);
    }

    // find a single task
    public Task findTaskById(String taskID) {
        return repository.findBytaskID(taskID);
    }

    // insert new task
    public Task createTask(String title, Date date, ArrayList<String> items, String priority, String status,
            String idUser, String time) {
        String taskID = UUID.randomUUID().toString();
        Task task = repository.insert(new Task(taskID, title, date, items, priority, status, idUser, time));
        return task;
    }

    // delete task
    public void deleteTask(String taskID) {
        collaborationsRepository.removeAllByTaskID(taskID);
        repository.deleteByTaskID(taskID);
    }

    // update task
    public Task updateTask(String taskID, String newTitle, Date newDate, String newPriority, ArrayList<String> newItems,
            String newStatus, String time) {
        Task Task = repository.findBytaskID(taskID);
        if (Task != null) {
            Task taskNew = Task;
            taskNew.setTitle(newTitle);
            taskNew.setDate(newDate);
            taskNew.setPriority(newPriority);
            taskNew.setItems(newItems);
            taskNew.setStatus(newStatus);
            taskNew.setTime(time);
            repository.save(taskNew);
            return taskNew;
        } else {
            return null;
        }
    }

}