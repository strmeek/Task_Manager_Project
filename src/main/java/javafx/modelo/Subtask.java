package javafx.modelo;

public class Subtask {
    private int id_subtask;
    private String type_subtask;
    private String title_subtask;
    private String description_subtask;
    private String priority_subtask;
    private String status_subtask;
    private String subtask_createdAt;
    private String subtask_updatedAt;
    private String subtask_startedAt;
    private String subtask_finishedAt;
    private String subtask_plannedStart;
    private String subtask_plannedFinish;
    private int foreign_idTask;

    public Subtask() {
    }

    public Subtask(int id_subtask,
                   String type_subtask,
                   String title_subtask,
                   String description_subtask,
                   String priority_subtask,
                   String status_subtask,
                   String subtask_createdAt,
                   String subtask_updatedAt,
                   String subtask_startedAt,
                   String subtask_finishedAt,
                   String subtask_plannedStart,
                   String subtask_plannedFinish,
                   int foreign_idTask) {
        this.id_subtask = id_subtask;
        this.type_subtask = type_subtask;
        this.title_subtask = title_subtask;
        this.description_subtask = description_subtask;
        this.priority_subtask = priority_subtask;
        this.status_subtask = status_subtask;
        this.subtask_createdAt = subtask_createdAt;
        this.subtask_updatedAt = subtask_updatedAt;
        this.subtask_startedAt = subtask_startedAt;
        this.subtask_finishedAt = subtask_finishedAt;
        this.subtask_plannedStart = subtask_plannedStart;
        this.subtask_plannedFinish = subtask_plannedFinish;
        this.foreign_idTask = foreign_idTask;
    }

    public String getSubtask_updatedAt() {
        return subtask_updatedAt;
    }

    public void setSubtask_updatedAt(String subtask_updatedAt) {
        this.subtask_updatedAt = subtask_updatedAt;
    }

    public int getId_subtask() {
        return id_subtask;
    }

    public void setId_subtask(int id_subtask) {
        this.id_subtask = id_subtask;
    }

    public String getTitle_subtask() {
        return title_subtask;
    }

    public void setTitle_subtask(String title_subtask) {
        this.title_subtask = title_subtask;
    }

    public String getDescription_subtask() {
        return description_subtask;
    }

    public void setDescription_subtask(String description_subtask) {
        this.description_subtask = description_subtask;
    }

    public String getPriority_subtask() {
        return priority_subtask;
    }

    public void setPriority_subtask(String priority_subtask) {
        this.priority_subtask = priority_subtask;
    }

    public String getStatus_subtask() {
        return status_subtask;
    }

    public void setStatus_subtask(String status_subtask) {
        this.status_subtask = status_subtask;
    }

    public String getSubtask_createdAt() {
        return subtask_createdAt;
    }

    public void setSubtask_createdAt(String subtask_createdAt) {
        this.subtask_createdAt = subtask_createdAt;
    }

    public String getSubtask_startedAt() {
        return subtask_startedAt;
    }

    public void setSubtask_startedAt(String subtask_startedAt) {
        this.subtask_startedAt = subtask_startedAt;
    }

    public String getSubtask_finishedAt() {
        return subtask_finishedAt;
    }

    public void setSubtask_finishedAt(String subtask_finishedAt) {
        this.subtask_finishedAt = subtask_finishedAt;
    }

    public String getSubtask_plannedStart() {
        return subtask_plannedStart;
    }

    public void setSubtask_plannedStart(String subtask_plannedStart) {
        this.subtask_plannedStart = subtask_plannedStart;
    }

    public String getSubtask_plannedFinish() {
        return subtask_plannedFinish;
    }

    public void setSubtask_plannedFinish(String subtask_plannedFinish) {
        this.subtask_plannedFinish = subtask_plannedFinish;
    }

    public int getForeign_idTask() {
        return foreign_idTask;
    }

    public void setForeign_idTask(int foreign_idTask) {
        this.foreign_idTask = foreign_idTask;
    }

    public String getType_subtask() {
        return type_subtask;
    }

    public void setType_subtask(String type_subtask) {
        this.type_subtask = type_subtask;
    }
}
