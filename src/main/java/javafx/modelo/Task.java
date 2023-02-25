package javafx.modelo;

public class Task {
    private int id_task;
    private String type_task;
    private String title_task;
    private String priority_task;
    private String status_task;
    private String task_created_at;
    private String planned_finish;
    private String planned_start;
    private String task_started_at;
    private String description_task;
    private String task_updated_at;
    private String finished_at;
    private String foreign_idProject;//mudar para int

    public Task() {
    }

    public Task(int id_task,
                String type_task,
                String title_task,
                String foreign_idProject,
                String priority_task,
                String status_task,
                String task_created_at,
                String planned_finish,
                String planned_start,
                String task_started_at,
                String description_task,
                String task_updated_at,
                String finished_at) {
        this.id_task = id_task;
        this.type_task = type_task;
        this.title_task = title_task;
        this.foreign_idProject = foreign_idProject;
        this.priority_task = priority_task;
        this.status_task = status_task;
        this.task_created_at = task_created_at;
        this.planned_finish = planned_finish;
        this.planned_start = planned_start;
        this.task_started_at = task_started_at;
        this.description_task = description_task;
        this.task_updated_at = task_updated_at;
        this.finished_at = finished_at;
    }

    public String getFinished_at() {
        return finished_at;
    }

    public void setFinished_at(String finished_at) {
        this.finished_at = finished_at;
    }

    public String getType_task() {
        return type_task;
    }

    public void setType_task(String type_task) {
        this.type_task = type_task;
    }

    public String getTitle_task() {
        return title_task;
    }

    public void setTitle_task(String title_task) {
        this.title_task = title_task;
    }

    public String getPriority_task() {
        return priority_task;
    }

    public void setPriority_task(String priority_task) {
        this.priority_task = priority_task;
    }

    public String getStatus_task() {
        return status_task;
    }

    public void setStatus_task(String status_task) {
        this.status_task = status_task;
    }

    public String getTask_created_at() {
        return task_created_at;
    }

    public void setTask_created_at(String task_created_at) {
        this.task_created_at = task_created_at;
    }

    public String getPlanned_finish() {
        return planned_finish;
    }

    public void setPlanned_finish(String planned_finish) {
        this.planned_finish = planned_finish;
    }

    public String getPlanned_start() {
        return planned_start;
    }

    public void setPlanned_start(String planned_start) {
        this.planned_start = planned_start;
    }

    public String getTask_started_at() {
        return task_started_at;
    }

    public void setTask_started_at(String task_started_at) {
        this.task_started_at = task_started_at;
    }

    public String getDescription_task() {
        return description_task;
    }

    public void setDescription_task(String description_task) {
        this.description_task = description_task;
    }

    public String getTask_updated_at() {
        return task_updated_at;
    }

    public void setTask_updated_at(String task_updated_at) {
        this.task_updated_at = task_updated_at;
    }

    public int getId_task() {
        return id_task;
    }

    public void setId_task(int id_task) {
        this.id_task = id_task;
    }

    public String getProject_task() {
        return foreign_idProject;
    }

    public void setProject_task(String project_task) {
        foreign_idProject = project_task;
    }
}
