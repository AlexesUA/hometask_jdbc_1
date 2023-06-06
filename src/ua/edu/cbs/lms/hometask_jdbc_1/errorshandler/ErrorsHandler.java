package ua.edu.cbs.lms.hometask_jdbc_1.errorshandler;

public class ErrorsHandler {
    public static void errorHandler(Exception error){
        System.out.println("ERROR: " + error.getMessage());
    }
}
