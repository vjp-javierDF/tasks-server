package dev.app.tasks.collaborations;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
// COLLABORATIONS
@Document(collection = "collaborations")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Collaborations {
    @Id
    private ObjectId _id;
    private String idCollaboration;
    private String idUserCollaborator;
    private String taskID;

    public Collaborations(String idUserCollaborator, String taskID, String idCollaboration) {
        this.idCollaboration = idCollaboration;
        this.idUserCollaborator = idUserCollaborator;
        this.taskID = taskID;
    }
}
