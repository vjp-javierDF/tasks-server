package dev.app.tasks;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends MongoRepository<Task, ObjectId> {
    Task findBytaskID(String taskID);
    void deleteByTaskID(String taskID);
    void deleteAllByidUser(String idUser);
    List<Task> findTasksByidUser(String idUser);
}
