package ua.edu.cbs.lms.hometask_jdbc_1.task3_5;

public class Main {
    public static void main(String[] args) {
        MyJoinsDBWorker dbWorker = MyJoinsDBWorker.createDBWorker();

        assert dbWorker != null;
        if(dbWorker != null) {
            dbWorker.showContactData();

            System.out.println("= = = = = = = = = = = = = = = = = ");

            dbWorker.showNoMarriageEmployee();

            System.out.println("= = = = = = = = = = = = = = = = = ");

            dbWorker.showEmployeeByPositionName("Manager");

            System.out.println("= = = = = = = = = = = = = = = = = ");

            dbWorker.showEmployeeByPositionName("Developer");
        }
    }

}
