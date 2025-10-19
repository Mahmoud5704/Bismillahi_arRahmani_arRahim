package javaapplication7;
import java.util.ArrayList;

public class AdminRole implements interface_UserRole{
    // variable:
    private EmployeeUserDatabase database;

    // constructor:
    public AdminRole() {
        database = new EmployeeUserDatabase(main.Employeefile);
        database.readFromFile();
    }

    // class contains four methods:
    // 1
    public void addEmployee(String employeeId, String name, String email, String address, String phoneNumber) {
        EmployeeUser user = new EmployeeUser(employeeId, name, email, address, phoneNumber);
        database.insertRecord(user);
        database.saveToFile();
    }

    // 2
    public EmployeeUser[] getListOfEmployees() {

        ArrayList<EmployeeUser> employees = database.returnAllRecords();
        if (employees == null || employees.isEmpty()) {
            System.out.printf("\nthere is NO Employees in the file\n");
            return new EmployeeUser[0];
        }
        return employees.toArray(new EmployeeUser[0]);
    }

    // 3
    public void removeEmployee(String key) {
        database.deleteRecord(key);
        database.saveToFile();
    }

    // 4
    @Override
    public void logout() {
        database.saveToFile();
    }

}

