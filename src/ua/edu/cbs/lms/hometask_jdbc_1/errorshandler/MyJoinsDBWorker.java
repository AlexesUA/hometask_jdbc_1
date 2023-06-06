package ua.edu.cbs.lms.hometask_jdbc_1.errorshandler;

public class MyJoinsDBWorker {
    private static final String url = "localhost:3306";
    private static final String login = "root";
    private static final String password = "Q11w22e33";

    private MyJoinsDBWorker(){}

    public static MyJoinsDBWorker createDBWorker(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loading success!");
            return new MyJoinsDBWorker();
        }catch (Exception error){
            ErrorsHandler.errorHandler(error);
            return null;
        }
    }
}
