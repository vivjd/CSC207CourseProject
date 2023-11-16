package use_case.note.delete;

import org.bson.types.ObjectId;

public class DeleteNoteInputData {


    private final ObjectId objectId;

    public DeleteNoteInputData(ObjectId objectId) {
        this.objectId = objectId;
    }

    public ObjectId getObjectId() {
        return objectId;
    }
}
