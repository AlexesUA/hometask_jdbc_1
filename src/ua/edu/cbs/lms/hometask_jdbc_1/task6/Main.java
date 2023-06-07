package ua.edu.cbs.lms.hometask_jdbc_1.task6;

import ua.edu.cbs.lms.hometask_jdbc_1.errorshandler.ErrorsHandler;

public class Main {
    public static void main(String[] args) {
        DBWorker dbWorker = DBWorker.createDBWorker();

        if(dbWorker!=null){
            try{
                dbWorker.createDB("db1 int");
                System.out.println("= = = = = = = = = = = = = = = = = = ");
                dbWorker.showAllLines();

                System.out.println("= = = = = = = = = = = = = = = = = = ");
                dbWorker.updateFacultet("Physics", "Quantum Physics");
                dbWorker.showAllLines();

                System.out.println("= = = = = = = = = = = = = = = = = = ");
                dbWorker.updateCourse();
                System.out.println("= = = = = = = = = = = = = = = = = = ");
                dbWorker.updateCourse();
                System.out.println("= = = = = = = = = = = = = = = = = = ");
                dbWorker.updateCourse();

                dbWorker.showAllLines();




            }catch (Exception error){
                ErrorsHandler.errorHandler(error);
            }

        }
    }
}
