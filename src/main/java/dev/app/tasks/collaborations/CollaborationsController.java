package dev.app.tasks.collaborations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.app.tasks.Task;
import dev.app.tasks.TaskService;

@RestController
@RequestMapping("/api/v1/collaborations")
public class CollaborationsController {
    @Autowired
    private CollaborationsService collaborationsService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    
    // DELETE COLLABORATION WITH TASKID AND IDUSER
    @DeleteMapping("/deleteCollaboration/{taskID}/{idUser}")
    public ResponseEntity<Void> removeCollaboration(@PathVariable String taskID, @PathVariable String idUser) {
        collaborationsService.deleteCollaboration(taskID, idUser);
        messagingTemplate.convertAndSend("/topic/updates", "");
        System.out.println("COLLABORATION REMOVED for taskID: " + taskID + " and idUser: " + idUser);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    // ADD COLLABORATIONS 
    @PostMapping("/addCollaborator/{taskID}/{idUserCollaborator}")
    public ResponseEntity<Void> addCollaborator(@PathVariable String taskID, @PathVariable String idUserCollaborator) {
        collaborationsService.addCollaborator(taskID, idUserCollaborator);
        messagingTemplate.convertAndSend("/topic/updates", "");
        return new ResponseEntity<>(HttpStatus.OK);
    }
    // GET COLLABORATIONS BY IDUSER
    @GetMapping("/collaborations/{idUser}")
    public ResponseEntity<List<Task>> findCollaborationsByidUser(@PathVariable String idUser) {
        List<Collaborations> collaborations = collaborationsService.findCollaborationsByidUser(idUser);

        List<Task> tasks = new ArrayList<>();
        for (Collaborations collaboration : collaborations) {
            Task task = taskService.findTaskById(collaboration.getTaskID());
            tasks.add(task);
        }

        return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
    }
    // GET COLLABORATIONS BY TASKID
    @GetMapping("/collaborations/task/{taskID}")
    public ResponseEntity<List<Collaborations>> getCollaborationsForTask(@PathVariable String taskID) {
        List<Collaborations> collaborations = collaborationsService.getCollaborationsForTask(taskID);
        return new ResponseEntity<>(collaborations, HttpStatus.OK);
    }
    // DELETE ALL COLLABORATIONS FOR TASKID
    @DeleteMapping("/deleteAllCollaborations/{taskID}")
    public ResponseEntity<Void> removeAllCollaborations(@PathVariable String taskID) {
    collaborationsService.deleteAllCollaborations(taskID);
    messagingTemplate.convertAndSend("/topic/updates", "");
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}

}
