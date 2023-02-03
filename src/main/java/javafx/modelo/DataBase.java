package javafx.modelo;

import java.sql.*;

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
    subtask (foreign key)

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

    to do the statements faster:
    (title_subtask,description_subtask,priority_subtask,status_subtask,created_at,started_at,finished_at,planned_start,planned_finish)
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
            Date created_at = new Date(System.currentTimeMillis());
            psAdd.setDate(6, created_at);

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
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void editTask(){

    }

    public static void deleteTask(){

    }

    public static void completeTask(){
    }
}
