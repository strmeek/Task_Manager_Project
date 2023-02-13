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
}
