package be.mo.consult.model;

import be.mo.consult.model.utils.TaskType;

import java.util.ArrayList;
import java.util.UUID;

public class Task {

    private UUID uuid;
    private TaskType taskType;
    private ArrayList operations;
    private ArrayList<TimeTable> timeTables;

    public Task() {
        this.taskType = taskType;
        this.operations = new ArrayList();
        this.timeTables = new ArrayList<TimeTable>();
    }

    public TaskType getTaskType(){
        return taskType;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public ArrayList getOperations() {
        return operations;
    }

    public void setOperations(ArrayList operations) {
        this.operations = operations;
    }

    public ArrayList<TimeTable> getTimeTables() {
        return timeTables;
    }

    public void setTimeTables(ArrayList<TimeTable> timeTables) {
        this.timeTables = timeTables;
    }
}
