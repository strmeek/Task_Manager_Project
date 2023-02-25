package javafx.modelo;

public class Project {
    private int id_Project;
    private String type_Project;
    private String title_Project;
    private String description_Project;
    private String priority_Project;
    private String status_Project;
    private String createdAt_Project;
    private String startedAt_Project;
    private String finishedAt_Project;
    private String plannedStart_Project;
    private String plannedFinish_Project;

    public Project() {
    }

    public Project(int id_Project,
                   String type_Project,
                   String title_Project,
                   String description_Project,
                   String priority_Project,
                   String status_Project,
                   String createdAt_Project,
                   String startedAt_Project,
                   String finishedAt_Project,
                   String plannedStart_Project,
                   String plannedFinish_Project) {
        this.id_Project = id_Project;
        this.type_Project = type_Project;
        this.title_Project = title_Project;
        this.description_Project = description_Project;
        this.priority_Project = priority_Project;
        this.status_Project = status_Project;
        this.createdAt_Project = createdAt_Project;
        this.startedAt_Project = startedAt_Project;
        this.finishedAt_Project = finishedAt_Project;
        this.plannedStart_Project = plannedStart_Project;
        this.plannedFinish_Project = plannedFinish_Project;
    }

    public int getId_Project() {
        return id_Project;
    }

    public void setId_Project(int id_Project) {
        this.id_Project = id_Project;
    }

    public String getType_Project() {
        return type_Project;
    }

    public void setType_Project(String type_Project) {
        this.type_Project = type_Project;
    }

    public String getTitle_Project() {
        return title_Project;
    }

    public void setTitle_Project(String title_Project) {
        this.title_Project = title_Project;
    }

    public String getDescription_Project() {
        return description_Project;
    }

    public void setDescription_Project(String description_Project) {
        this.description_Project = description_Project;
    }

    public String getPriority_Project() {
        return priority_Project;
    }

    public void setPriority_Project(String priority_Project) {
        this.priority_Project = priority_Project;
    }

    public String getStatus_Project() {
        return status_Project;
    }

    public void setStatus_Project(String status_Project) {
        this.status_Project = status_Project;
    }

    public String getCreatedAt_Project() {
        return createdAt_Project;
    }

    public void setCreatedAt_Project(String createdAt_Project) {
        this.createdAt_Project = createdAt_Project;
    }

    public String getStartedAt_Project() {
        return startedAt_Project;
    }

    public void setStartedAt_Project(String startedAt_Project) {
        this.startedAt_Project = startedAt_Project;
    }

    public String getFinishedAt_Project() {
        return finishedAt_Project;
    }

    public void setFinishedAt_Project(String finishedAt_Project) {
        this.finishedAt_Project = finishedAt_Project;
    }

    public String getPlannedStart_Project() {
        return plannedStart_Project;
    }

    public void setPlannedStart_Project(String plannedStart_Project) {
        this.plannedStart_Project = plannedStart_Project;
    }

    public String getPlannedFinish_Project() {
        return plannedFinish_Project;
    }

    public void setPlannedFinish_Project(String plannedFinish_Project) {
        this.plannedFinish_Project = plannedFinish_Project;
    }
}
