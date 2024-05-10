package dev.app.tasks.collaborations;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollaborationsService {
    @Autowired
    private CollaborationsRepository collaborationRepository;  // access to repository
    // delete a collaboration
    public void deleteCollaboration(String taskID, String idUserCollaborator) {
        collaborationRepository.removeByTaskIDAndIdUserCollaborator(taskID, idUserCollaborator);
    }
    // add a collaboration
    public Collaborations addCollaborator(String taskID, String idUserCollaborator) {
        String idCollaboration = UUID.randomUUID().toString();
        Collaborations collaborations = new Collaborations();
        collaborations.setIdCollaboration(idCollaboration);
        collaborations.setTaskID(taskID);
        collaborations.setIdUserCollaborator(idUserCollaborator);
        return collaborationRepository.save(collaborations);
    }
    // get all collaborations for a idUser  
    public List<Collaborations> findCollaborationsByidUser(String idUserCollaborator) {
        return collaborationRepository.findByidUserCollaborator(idUserCollaborator);
    }
    // get all collaborations for a taskID
    public List<Collaborations> getCollaborationsForTask(String taskID) {
        return collaborationRepository.findByTaskID(taskID);
    }
    // delete all collaborations for a taskID
    public void deleteAllCollaborations(String taskID) {
        collaborationRepository.removeAllByTaskID(taskID);
    }
}
