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

    /*Table: task
    Columns:
    id_task int AI PK
    type_task varchar(16)
    title_task varchar(144)
    description_task varchar(512)
    priority_task varchar(6)
    status_task varchar(11)
    created_at datetime
    updated_at datetime
    started_at datetime
    finished_at datetime
    planned_start datetime
    planned_finish datetime

    to do the statements faster:
    (type_task,title_task,description_task,priority_task,status_task,created_at,updated_at,started_at,finished_at,planned_start,planned_finish)
    */



    /*Table: subtask
    Columns:
    id_subtask int AI PK
    title_subtask varchar(144)
    description_subtask varchar(512)
    priority_subtask varchar(6)
    status_subtask varchar(11)
    created_at datetime
    started_at datetime
    finished_at datetime
    planned_start datetime
    planned_finish datetime
    id_task (foreign key)

    to do the statements faster:
    (title_subtask,description_subtask,priority_subtask,status_subtask,created_at,started_at,finished_at,planned_start,planned_finish,id_task)
    */

    /*Add the tasks to the DB*/
    public static void addTask(String type,
                               String title,
                               String description,
                               String priority,
                               String status,
                               Date planned_start,
                               Date planned_finish){
        Connection connection = null;
        PreparedStatement psAdd = null;

        try{
            connection = DriverManager.getConnection(db_Url,db_User,db_Password);
            psAdd = connection.prepareStatement("INSERT INTO task " +
                    "(type_task,title_task,description_task,priority_task,status_task,created_at,planned_start,planned_finish) " +
                    "VALUES (?,?,?,?,?,?,?,?)");
            //sets with the information that came from the user
            psAdd.setString(1, type);
            psAdd.setString(2, title);
            psAdd.setString(3, description);
            psAdd.setString(4, priority);
            psAdd.setString(5, status);
            psAdd.setDate(7, planned_start);
            psAdd.setDate(8, planned_finish);

            //Gets the datetime in the instant that the program adds in the DB
            Timestamp created_at = new Timestamp(System.currentTimeMillis());
            psAdd.setTimestamp(6, created_at);

            psAdd.executeUpdate();

        } catch(SQLException e){
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

    public static void addSubTask(Subtask subtask){
        Connection connection = null;
        PreparedStatement psAddSubtask = null;

        try{
            connection = DriverManager.getConnection(db_Url,db_User,db_Password);
            psAddSubtask = connection.prepareStatement("INSERT INTO subtask " +
                    "(title_subtask, description_subtask, priority_subtask, status_subtask, created_at, planned_start, planned_finish, id_task) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

            psAddSubtask.setString(1, subtask.getTitle_subtask());
            psAddSubtask.setString(2, subtask.getDescription_subtask());
            psAddSubtask.setString(3, subtask.getPriority_subtask());
            psAddSubtask.setString(4, subtask.getStatus_subtask());
            psAddSubtask.setDate(6, Date.valueOf(subtask.getSubtask_plannedStart()));
            psAddSubtask.setDate(7, Date.valueOf(subtask.getSubtask_plannedFinish()));
            psAddSubtask.setInt(8, subtask.getForeign_idTask());

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

    /* public static void editTask(Task task){
        Connection connection = null;
        PreparedStatement psEdit = null;
        ResultSet resultSet;

        try{
            connection = DriverManager.getConnection(db_Url,db_User,db_Password);
            psEdit = connection.prepareStatement("SELECT * FROM task WHERE id_task = ?");
            psEdit.setInt(1, task.getId_task());
            resultSet = psEdit.executeQuery();

            if (resultSet.next()){
            }
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

    } */

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

    public static void completeTask(Task task){
        Connection connection = null;
        PreparedStatement psComplete = null;

        try{
            connection = DriverManager.getConnection(db_Url,db_User,db_Password);

            Timestamp now = new Timestamp(System.currentTimeMillis());

            psComplete = connection.prepareStatement("UPDATE task " +
                    "SET status_task = \"Completed\", finished_at = \"" + now + "\" " +
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
                    "SET status_subtask = \"Completed\", finished_at = \"" + now + "\" " +
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

    public static void completeAllSubtask(Task task){
        Connection connection = null;
        PreparedStatement psComplete = null;

        try{
            connection = DriverManager.getConnection(db_Url,db_User,db_Password);

            Timestamp now = new Timestamp(System.currentTimeMillis());

            psComplete = connection.prepareStatement("UPDATE subtask " +
                    "SET status_subtask = \"Completed\", finished_at = \"" + now + "\" " +
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

    public static List<Subtask> gridListSubtask(int idTask){
        List<Subtask> grid = new ArrayList<>();

        Connection connection = null;
        PreparedStatement psList = null;
        ResultSet resultSet = null;

        try{
            connection = DriverManager.getConnection(db_Url, db_User, db_Password);
            psList = connection.prepareStatement("SELECT * FROM subtask WHERE id_task = ?");
            psList.setInt(1, idTask);
            resultSet = psList.executeQuery();

            while (resultSet.next()){
                Subtask subtask = new Subtask();
                subtask.setId_subtask(resultSet.getInt("id_subtask"));
                subtask.setTitle_subtask(resultSet.getString("title_subtask"));
                subtask.setDescription_subtask(resultSet.getString("description_subtask"));
                subtask.setPriority_subtask(resultSet.getString("priority_subtask"));
                subtask.setStatus_subtask(resultSet.getString("status_subtask"));
                subtask.setSubtask_createdAt(resultSet.getDate("created_at").toString());
                subtask.setSubtask_plannedStart(resultSet.getDate("planned_start").toString());
                subtask.setSubtask_plannedFinish(resultSet.getDate("planned_finish").toString());

                if(resultSet.getDate("started_at") == null){
                    subtask.setSubtask_startedAt("None");
                }else {
                    subtask.setSubtask_startedAt(resultSet.getDate("started_at").toString());
                }
                if(resultSet.getDate("finished_at") == null){
                    subtask.setSubtask_finishedAt("None");
                }else {
                    subtask.setSubtask_finishedAt(resultSet.getDate("finished_at").toString());
                }
                if(resultSet.getDate("updated_at") == null){
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
}
