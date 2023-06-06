package ua.edu.cbs.lms.hometask_jdbc_1.errorshandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MyJoinsDBWorker {
    private final String url = "localhost:3306";
    private final String login = "root";
    private final String password = "Q11w22e33";

    private final String selectContactData = "SELECT employees.first_name, employees.last_name, " +
            "employees.phone_number, personal_data.home_address\n" +
            "FROM employees\n" +
            "JOIN personal_data ON personal_data.personal_data_id = employees.personal_data_id;";

    private final String selectNoMarriageEmployee = "SELECT employees.first_name, employees.last_name, " +
            "employees.phone_number, personal_data.date_of_birthd\n" +
            "FROM employees\n" +
            "JOIN personal_data ON personal_data.personal_data_id = employees.personal_data_id\n" +
            "JOIN merital_status ON merital_status.merital_status_id = personal_data.merital_status_id\n" +
            "WHERE merital_status.merital_status != 'Married' AND  merital_status.merital_status != 'In a relationship' ;";

    private final String selectEmployeeByPositionName = "SELECT employees.first_name, employees.last_name, " +
            "employees.phone_number, personal_data.date_of_birthd\n" +
            "FROM employees\n" +
            "JOIN personal_data ON personal_data.personal_data_id = employees.personal_data_id\n" +
            "JOIN salary ON salary.salary_id = employees.salary_id\n" +
            "JOIN positions ON positions.position_id = salary.salary_id\n" +
            "WHERE positions.position_name = '?';";

    private MyJoinsDBWorker(){}

    public static MyJoinsDBWorker createDBWorker(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loading success!");
            return new MyJoinsDBWorker();
        }catch (Exception error){
            ErrorsHandler.errorHandler(error);
            return null;
        }
    }

    public void showContactData(){
        try(Connection connection = DriverManager.getConnection(url,login,password);
            PreparedStatement preparedStatement = connection.prepareStatement(selectContactData)){
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("Result:");

            while(resultSet.next()){
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                String phone_number = resultSet.getString("phone_number");
                String home_address = resultSet.getString("home_address");

                System.out.println(first_name + " " + last_name + "; " + phone_number  + "; " + home_address);
            }


        }catch (Exception error){
            ErrorsHandler.errorHandler(error);
        }

    }


}
