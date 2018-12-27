package be.mo.consult.model;

import be.mo.consult.model.utils.TaskType;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.UUID;

public class Task implements TaskInterface {

    private UUID uuid;
    private TaskType taskType;
    private ArrayList operations;
    private ArrayList<TimeTable> timeTables;
    private ObjectId _id;

    public Task(TaskType taskType) {
        this.taskType = taskType;
        this.operations = new ArrayList();
        this.timeTables = new ArrayList<TimeTable>();
        this.uuid = UUID.randomUUID();
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    @Override
    public TaskType getTaskType(){
        return taskType;
    }

    @Override
    public UUID getUuid() {
        return uuid;
    }

    @Override
    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public ArrayList getOperations() {
        return operations;
    }

    @Override
    public void setOperations(ArrayList operations) {
        this.operations = operations;
    }

    @Override
    public ArrayList<TimeTable> getTimeTables() {
        return timeTables;
    }

    @Override
    public void setTimeTables(ArrayList<TimeTable> timeTables) {
        this.timeTables = timeTables;
    }
}
