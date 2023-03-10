package javafx.modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase {
    /*The Objective of this class is connect the program to the database
    * Database Located at(in my case, local machine):
    * example: "jdbc:mysql://localhost:3306/task_manager", "root", "" */
    static final String db_Url = "jdbc:mysql://localhost:3306/task_manager";
    static final String db_User = "root";
    static final String db_Password = "";

    /* connection = DriverManager.getConnection(db_Url,db_User,db_Password); */

    /*Add Project to the DB*/
    public static void addProject(Project project){
        Connection connection = null;
        PreparedStatement psAdd = null;

        try{
            connection = DriverManager.getConnection(db_Url,db_User,db_Password);
            psAdd = connection.prepareStatement("INSERT INTO project " +
                    "(title_project, description_project, type_project, priority_project, status_project, planned_start, planned_finish, created_at) " +
                    "VALUES (?,?,?,?,?,?,?,?)");

            psAdd.setString(1, project.getTitle_Project());
            psAdd.setString(2, project.getDescription_Project());
            psAdd.setString(3, project.getType_Project());
            psAdd.setString(4, project.getPriority_Project());
            psAdd.setString(5, project.getStatus_Project());
            psAdd.setDate(6, Date.valueOf(project.getPlannedStart_Project()));
            psAdd.setDate(7, Date.valueOf(project.getPlannedFinish_Project()));

            Timestamp createdAt = new Timestamp(System.currentTimeMillis());
            psAdd.setTimestamp(8, createdAt);

            psAdd.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                if(psAdd != null){
                    psAdd.close();
                }
                if (connection != null){
                    connection.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    /*Add the Task to the DB*/
    public static void addTask(Task task){
        Connection connection = null;
        PreparedStatement psAddSubtask = null;

        try{
            connection = DriverManager.getConnection(db_Url,db_User,db_Password);
            psAddSubtask = connection.prepareStatement("INSERT INTO task " +
                    "(title_task, description_task, priority_task, status_task, created_at, planned_start, planned_finish, type_subtask, id_project) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

            psAddSubtask.setString(1, task.getTitle_task());
            psAddSubtask.setString(2, task.getDescription_task());
            psAddSubtask.setString(3, task.getPriority_task());
            psAddSubtask.setString(4, task.getStatus_task());
            psAddSubtask.setDate(6, Date.valueOf(task.getPlanned_start()));
            psAddSubtask.setDate(7, Date.valueOf(task.getPlanned_finish()));
            psAddSubtask.setString(8,task.getType_task());
            psAddSubtask.setInt(9, task.getForeign_idProject());

            Timestamp createdAt = new Timestamp(System.currentTimeMillis());
            psAddSubtask.setTimestamp(5, createdAt);

            psAddSubtask.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                if(psAddSubtask != null){
                    psAddSubtask.close();
                }
                if (connection != null){
                    connection.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public static void addSubTask(Subtask subtask){
        Connection connection = null;
        PreparedStatement psAddSubtask = null;

        try{
            connection = DriverManager.getConnection(db_Url,db_User,db_Password);
            psAddSubtask = connection.prepareStatement("INSERT INTO subtask " +
                    "(title_subtask, description_subtask, priority_subtask, status_subtask, created_at, planned_start, planned_finish, type_subtask, id_task) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

            psAddSubtask.setString(1, subtask.getTitle_subtask());
            psAddSubtask.setString(2, subtask.getDescription_subtask());
            psAddSubtask.setString(3, subtask.getPriority_subtask());
            psAddSubtask.setString(4, subtask.getStatus_subtask());
            psAddSubtask.setDate(6, Date.valueOf(subtask.getSubtask_plannedStart()));
            psAddSubtask.setDate(7, Date.valueOf(subtask.getSubtask_plannedFinish()));
            psAddSubtask.setString(8,subtask.getType_subtask());
            psAddSubtask.setInt(9, subtask.getForeign_idTask());

            Timestamp createdAt = new Timestamp(System.currentTimeMillis());
            psAddSubtask.setTimestamp(5, createdAt);

            psAddSubtask.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                if(psAddSubtask != null){
                    psAddSubtask.close();
                }
                if (connection != null){
                    connection.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public static void editTask(Task task){
        Connection connection = null;
        PreparedStatement psEdit = null;

        try{
            connection = DriverManager.getConnection(db_Url,db_User,db_Password);

            Timestamp now = new Timestamp(System.currentTimeMillis());

            psEdit = connection.prepareStatement("UPDATE task " +
                    "SET type_task = ?, title_task = ?, priority_task = ?, planned_finish = ?, updated_at = \"" + now + "\" " +
                    "WHERE id_task = ?");
            psEdit.setInt(5, task.getId_task());
            psEdit.setString(1, task.getType_task());
            psEdit.setString(2, task.getTitle_task());
            psEdit.setString(3, task. getPriority_task());
            psEdit.setDate(4, Date.valueOf(task.getPlanned_finish()));

            psEdit.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                if(psEdit != null){
                    psEdit.close();
                }
                if(connection != null){
                    connection.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public static void editSubtask(Subtask subtask){
        Connection connection = null;
        PreparedStatement psEdit = null;

        try{
            connection = DriverManager.getConnection(db_Url,db_User,db_Password);

            Timestamp now = new Timestamp(System.currentTimeMillis());

            psEdit = connection.prepareStatement("UPDATE subtask " +
                    "SET title_subtask = ?, description_subtask = ?, planned_finish = ?, updated_at = \"" + now + "\" " +
                    "WHERE id_subtask = ?");
            psEdit.setInt(4, subtask.getId_subtask());
            psEdit.setString(1, subtask.getTitle_subtask());
            psEdit.setString(2, subtask.getDescription_subtask());
            psEdit.setDate(3, Date.valueOf(subtask.getSubtask_plannedFinish()));

            psEdit.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                if(psEdit != null){
                    psEdit.close();
                }
                if(connection != null){
                    connection.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public static void deleteProject(Project project){
        Connection connection = null;
        PreparedStatement psDelete = null;
        try{
            connection = DriverManager.getConnection(db_Url,db_User,db_Password);
            psDelete = connection.prepareStatement("DELETE FROM project WHERE id_project = ?");
            psDelete.setInt(1, project.getId_Project());

            psDelete.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                if(psDelete != null){
                    psDelete.close();
                }
                if (connection != null){
                    connection.close();
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void deleteTask(Task task){
        Connection connection = null;
        PreparedStatement psDelete = null;
        try{
            connection = DriverManager.getConnection(db_Url,db_User,db_Password);
            psDelete = connection.prepareStatement("DELETE FROM task WHERE id_task = ?");
            psDelete.setInt(1, task.getId_task());

            psDelete.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                if(psDelete != null){
                    psDelete.close();
                }
                if (connection != null){
                    connection.close();
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void deleteSubtask(Subtask subtask){
        Connection connection = null;
        PreparedStatement psDelete = null;
        try{
            connection = DriverManager.getConnection(db_Url,db_User,db_Password);
            psDelete = connection.prepareStatement("DELETE FROM subtask WHERE id_subtask = ?");
            psDelete.setInt(1, subtask.getId_subtask());

            psDelete.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                if(psDelete != null){
                    psDelete.close();
                }
                if (connection != null){
                    connection.close();
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void deleteAllTask(Project project){
        Connection connection = null;
        PreparedStatement psDelete = null;
        try{
            connection = DriverManager.getConnection(db_Url,db_User,db_Password);
            psDelete = connection.prepareStatement("DELETE FROM task WHERE id_project = ?");
            psDelete.setInt(1, project.getId_Project());

            psDelete.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                if(psDelete != null){
                    psDelete.close();
                }
                if (connection != null){
                    connection.close();
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void deleteAllSubtask(Task task){
        Connection connection = null;
        PreparedStatement psDelete = null;
        try{
            connection = DriverManager.getConnection(db_Url,db_User,db_Password);
            psDelete = connection.prepareStatement("DELETE FROM subtask WHERE id_task = ?");
            psDelete.setInt(1, task.getId_task());

            psDelete.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                if(psDelete != null){
                    psDelete.close();
                }
                if (connection != null){
                    connection.close();
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void completeProject(Project project){
        Connection connection = null;
        PreparedStatement psComplete = null;

        try{
            connection = DriverManager.getConnection(db_Url,db_User,db_Password);

            Timestamp now = new Timestamp(System.currentTimeMillis());

            psComplete = connection.prepareStatement("UPDATE project " +
                    "SET status_project = \"Done\", finished_at = \"" + now + "\" " +
                    "WHERE id_project = ?");
            psComplete.setInt(1, project.getId_Project());

            psComplete.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                if(psComplete != null){
                    psComplete.close();
                }
                if (connection != null){
                    connection.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public static void completeTask(Task task){
        Connection connection = null;
        PreparedStatement psComplete = null;

        try{
            connection = DriverManager.getConnection(db_Url,db_User,db_Password);

            Timestamp now = new Timestamp(System.currentTimeMillis());

            psComplete = connection.prepareStatement("UPDATE task " +
                    "SET status_task = \"Done\", finished_at = \"" + now + "\" " +
                    "WHERE id_task = ?");
            psComplete.setInt(1,task.getId_task());

            psComplete.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                if(psComplete != null){
                    psComplete.close();
                }
                if (connection != null){
                    connection.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public static void completeSubtask(Subtask subtask){
        Connection connection = null;
        PreparedStatement psComplete = null;

        try{
            connection = DriverManager.getConnection(db_Url,db_User,db_Password);

            Timestamp now = new Timestamp(System.currentTimeMillis());

            psComplete = connection.prepareStatement("UPDATE subtask " +
                    "SET status_subtask = \"Done\", finished_at = \"" + now + "\" " +
                    "WHERE id_subtask = ?");
            psComplete.setInt(1, subtask.getId_subtask());

            psComplete.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                if(psComplete != null){
                    psComplete.close();
                }
                if (connection != null){
                    connection.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public static void completeAllTask(Project project){
        Connection connection = null;
        PreparedStatement psComplete = null;

        try{
            connection = DriverManager.getConnection(db_Url,db_User,db_Password);

            Timestamp now = new Timestamp(System.currentTimeMillis());

            psComplete = connection.prepareStatement("UPDATE task " +
                    "SET status_task = \"Done\", finished_at = \"" + now + "\" " +
                    "WHERE id_project = ?");
            psComplete.setInt(1, project.getId_Project());

            psComplete.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                if(psComplete != null){
                    psComplete.close();
                }
                if (connection != null){
                    connection.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public static void completeAllSubtask(Task task){
        Connection connection = null;
        PreparedStatement psComplete = null;

        try{
            connection = DriverManager.getConnection(db_Url,db_User,db_Password);

            Timestamp now = new Timestamp(System.currentTimeMillis());

            psComplete = connection.prepareStatement("UPDATE subtask " +
                    "SET status_subtask = \"Done\", finished_at = \"" + now + "\" " +
                    "WHERE id_task = ?");
            psComplete.setInt(1, task.getId_task());

            psComplete.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                if(psComplete != null){
                    psComplete.close();
                }
                if (connection != null){
                    connection.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public static List<Project> gridListProjects() {
        List<Project> grid = new ArrayList<>();

        Connection connection = null;
        PreparedStatement psList = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(db_Url, db_User, db_Password);
            psList = connection.prepareStatement("SELECT * FROM project");
            resultSet = psList.executeQuery();

            while (resultSet.next()) {
                Project project = new Project();
                project.setId_Project(resultSet.getInt("id_project"));
                project.setType_Project(resultSet.getString("type_project"));
                project.setTitle_Project(resultSet.getString("title_project"));
                project.setDescription_Project(resultSet.getString("description_project"));
                project.setPriority_Project(resultSet.getString("priority_project"));
                project.setStatus_Project(resultSet.getString("status_project"));
                project.setCreatedAt_Project(resultSet.getDate("created_at").toString());
                project.setPlannedStart_Project(resultSet.getDate("planned_start").toString());
                project.setPlannedFinish_Project(resultSet.getDate("planned_finish").toString());

                if (resultSet.getDate("updated_at") == null) {
                    project.setUpdatedAt_Project("None");
                } else {
                    project.setUpdatedAt_Project(resultSet.getDate("updated_at").toString());
                }
                if (resultSet.getDate("started_at") == null) {
                    project.setStartedAt_Project("None");
                } else {
                    project.setStartedAt_Project(resultSet.getDate("started_at").toString());
                }
                if (resultSet.getDate("finished_at") == null) {
                    project.setFinishedAt_Project("None");
                } else {
                    project.setFinishedAt_Project(resultSet.getDate("finished_at").toString());
                }

                grid.add(project);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (psList != null) {
                    psList.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return grid;
    }

    public static List<Task> grid_List_tasks() {
        List<Task> grid = new ArrayList<>();

        Connection connection = null;
        PreparedStatement psList = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(db_Url, db_User, db_Password);
            psList = connection.prepareStatement("SELECT * FROM task");
            resultSet = psList.executeQuery();

            while (resultSet.next()) {
                Task task = new Task();
                task.setId_task(resultSet.getInt("id_task"));
                task.setType_task(resultSet.getString("type_task"));
                task.setTitle_task(resultSet.getString("title_task"));
                task.setDescription_task(resultSet.getString("description_task"));
                //task.setProject_task(resultSet.getString("id_project"));
                task.setPriority_task(resultSet.getString("priority_task"));
                task.setStatus_task(resultSet.getString("status_task"));
                task.setTask_created_at(resultSet.getDate("created_at").toString());
                task.setPlanned_start(resultSet.getDate("planned_start").toString());
                task.setPlanned_finish(resultSet.getDate("planned_finish").toString());

                if (resultSet.getDate("updated_at") == null) {
                    task.setTask_updated_at("None");
                } else {
                    task.setTask_updated_at(resultSet.getDate("updated_at").toString());
                }
                if (resultSet.getDate("started_at") == null) {
                    task.setTask_started_at("None");
                } else {
                    task.setTask_started_at(resultSet.getDate("started_at").toString());
                }
                if (resultSet.getDate("finished_at") == null) {
                    task.setFinished_at("None");
                } else {
                    task.setFinished_at(resultSet.getDate("finished_at").toString());
                }

                grid.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (psList != null) {
                    psList.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return grid;
    }

    public static List<Subtask> gridListSubtask(int idTask) {
        List<Subtask> grid = new ArrayList<>();

        Connection connection = null;
        PreparedStatement psList = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(db_Url, db_User, db_Password);
            psList = connection.prepareStatement("SELECT * FROM subtask WHERE id_task = ?");
            psList.setInt(1, idTask);
            resultSet = psList.executeQuery();

            while (resultSet.next()) {
                Subtask subtask = new Subtask();
                subtask.setId_subtask(resultSet.getInt("id_subtask"));
                subtask.setType_subtask(resultSet.getString("type_subtask"));
                subtask.setTitle_subtask(resultSet.getString("title_subtask"));
                subtask.setDescription_subtask(resultSet.getString("description_subtask"));
                subtask.setPriority_subtask(resultSet.getString("priority_subtask"));
                subtask.setStatus_subtask(resultSet.getString("status_subtask"));
                subtask.setSubtask_createdAt(resultSet.getDate("created_at").toString());
                subtask.setSubtask_plannedStart(resultSet.getDate("planned_start").toString());
                subtask.setSubtask_plannedFinish(resultSet.getDate("planned_finish").toString());

                if (resultSet.getDate("started_at") == null) {
                    subtask.setSubtask_startedAt("None");
                } else {
                    subtask.setSubtask_startedAt(resultSet.getDate("started_at").toString());
                }
                if (resultSet.getDate("finished_at") == null) {
                    subtask.setSubtask_finishedAt("None");
                } else {
                    subtask.setSubtask_finishedAt(resultSet.getDate("finished_at").toString());
                }
                if (resultSet.getDate("updated_at") == null) {
                    subtask.setSubtask_updatedAt("None");
                } else {
                    subtask.setSubtask_updatedAt(resultSet.getDate("updated_at").toString());
                }
                grid.add(subtask);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (psList != null) {
                    psList.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return grid;
    }
    /*Devolve o id do projeto com o seu titulo (resolver problema com titulos iguais depois)*/
    public static int findProject(String project){
        Connection connection = null;
        PreparedStatement psFind = null;
        ResultSet resultSet = null;

        int id_project = 0;

        try{
            connection = DriverManager.getConnection(db_Url, db_User, db_Password);
            psFind = connection.prepareStatement("SELECT id_project FROM project WHERE title_project = ?");
            psFind.setString(1, project);
            resultSet = psFind.executeQuery();

            if (resultSet.first()){
                id_project = resultSet.getInt("id_project");
            }
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (psFind != null) {
                    psFind.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return id_project;
    }
}