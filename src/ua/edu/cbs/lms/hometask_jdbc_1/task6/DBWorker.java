package ua.edu.cbs.lms.hometask_jdbc_1.task6;

import ua.edu.cbs.lms.hometask_jdbc_1.errorshandler.ErrorsHandler;
import ua.edu.cbs.lms.hometask_jdbc_1.task3_5.MyJoinsDBWorker;

public class DBWorker {
    private final String url = "jdbc:mysql://localhost:3306";
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

}
