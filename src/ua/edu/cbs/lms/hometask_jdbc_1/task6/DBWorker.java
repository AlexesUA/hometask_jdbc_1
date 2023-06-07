package ua.edu.cbs.lms.hometask_jdbc_1.task6;

import ua.edu.cbs.lms.hometask_jdbc_1.errorshandler.ErrorsHandler;
import ua.edu.cbs.lms.hometask_jdbc_1.task3_5.MyJoinsDBWorker;

import java.sql.*;

public class DBWorker {
    private final String url = "jdbc:mysql://localhost:3306";
    private String urlNewDB;
    private final String login = "root";
    private final String password = "Q11w22e33";

    private DBWorker(){}

    public static DBWorker createDBWorker(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loading success!");
            return new DBWorker();
        }catch (Exception error){
            ErrorsHandler.errorHandler(error);
            return null;
        }
    }
    
    public void createDB(String nameDB){

        nameDB = nameDB.toLowerCase();
        nameDB = nameDB.replace(" ", "_");
        if(nameDB.length() == 0) return;

        try(Connection connection = DriverManager.getConnection(url,login,password);
            Statement statement = connection.createStatement()){

            statement.execute("DROP DATABASE IF EXISTS " + nameDB + ";");
            statement.execute("CREATE DATABASE IF NOT EXISTS " + nameDB + ";");
            statement.execute("USE " + nameDB + ";");

            urlNewDB = url + "/" + nameDB;

            System.out.println("Created database '" + nameDB + "'");

            statement.execute("CREATE TABLE students(" +
                    "student_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                    "student_name VARCHAR(40) NOT NULL," +
                    "student_age INT NOT NULL," +
                    "student_facultet VARCHAR(40) NOT NULL," +
                    "student_course INT NOT NULL) ;");

            System.out.println("Created table 'students'");

            int res = statement.executeUpdate("INSERT INTO students (student_name, student_age, student_facultet, student_course) VALUES\n" +
                    "    ('John Doe', 20, 'Computer Science', 2),\n" +
                    "    ('Jane Smith', 22, 'Physics', 3),\n" +
                    "    ('Michael Johnson', 19, 'Mathematics', 1),\n" +
                    "    ('Emily Davis', 21, 'Chemistry', 2),\n" +
                    "    ('Robert Wilson', 20, 'Biology', 1),\n" +
                    "    ('Sarah Brown', 23, 'Engineering', 4),\n" +
                    "    ('David Taylor', 19, 'Computer Science', 1),\n" +
                    "    ('Jennifer Clark', 22, 'Physics', 3),\n" +
                    "    ('Christopher Lee', 21, 'Mathematics', 2),\n" +
                    "    ('Jessica Miller', 20, 'Chemistry', 1);");

            System.out.println("In table 'students' added " + res + " lines.");

        }catch (Exception error){
            ErrorsHandler.errorHandler(error);
        }
        
    }

    public void showAllLines(){
        try(Connection connection = DriverManager.getConnection(urlNewDB,login,password);
            Statement statement = connection.createStatement()){

            ResultSet resultSet = statement.executeQuery("SELECT * FROM students;");
            System.out.println("Result for all lines:");
            while (resultSet.next()){
                int student_id = resultSet.getInt("student_id");
                String student_name = resultSet.getString("student_name");
                int student_age = resultSet.getInt("student_age");
                String student_facultet = resultSet.getString("student_facultet");
                int student_course = resultSet.getInt("student_course");

                System.out.println(student_id + " | " + student_name + " | " + student_age + " | " + student_facultet + " | " + student_course);
            }

        }catch (Exception error){
            ErrorsHandler.errorHandler(error);
        }
    }

    public void updateFacultet(String oldFacultet, String newFacultet){

        try(Connection connection = DriverManager.getConnection(urlNewDB,login,password);
            Statement statement = connection.createStatement()){

            int res = statement.executeUpdate("UPDATE students SET  student_facultet='" + newFacultet + "' " +
                    "WHERE student_facultet='" + oldFacultet + "'");

            System.out.println("Updated " + res + " lines");

        }catch (Exception error){
            ErrorsHandler.errorHandler(error);
        }
    }

    public void updateCourse(){

        try(Connection connection = DriverManager.getConnection(urlNewDB,login,password);
            Statement statement = connection.createStatement()){

            int res = statement.executeUpdate("UPDATE students SET  student_course=student_course+1");

            System.out.println("Updated " + res + " lines");

            res = statement.executeUpdate("DELETE FROM students WHERE student_course > 5;");
            System.out.println(res + " students finish studies");

        }catch (Exception error){
            ErrorsHandler.errorHandler(error);
        }
    }

}
