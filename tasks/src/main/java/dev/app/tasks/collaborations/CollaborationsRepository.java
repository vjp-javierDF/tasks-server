package dev.app.tasks.collaborations;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository // INDICATES THAT THIS CLASS IS A REPOSITORY AND THAT IT IS CONNECTED TO THE MONGODB DATABASE
public interface CollaborationsRepository extends MongoRepository<Collaborations, String>{
    void removeByTaskIDAndIdUserCollaborator(String taskID, String idUserCollaborator);
    void removeAllByTaskID(String taskID);
    List<Collaborations> findByidUserCollaborator(String idUserCollaborator);
    List<Collaborations> findByTaskID(String taskID);
}
