/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.util.ArrayList;
import java.util.List;


public class AdminRole 
{
    //variable:
    private EmployeeUserDatabase database;
    //constructor:
    public AdminRole()
    {
       database = new EmployeeUserDatabase(mainclass.Employeefile);
       database.readFromFile();
    }
    //class contains four methods:
    //1-adds a new employee to the file named Employees.txt
    public void addEmployee(String employeeId, String name, String email, String address, String phoneNumber)
    {
        EmployeeUser user = new EmployeeUser (employeeId,name,email,address,phoneNumber);
        database.insertRecord(user);
        database.saveToFile();   
    }
    //2-returns an array that contains all the employees stored in the file named Employees.txt
    public EmployeeUser[] getListOfEmployees()
    {
        
        
        List<EmployeeUser> employees  = database.returnAllRecords();
        if(employees == null|| employees.isEmpty())
        {
            System.out.printf("\nthere is NO Employees in the file\n");
            return new EmployeeUser [0];
        }
        return employees.toArray(new EmployeeUser[0]);
    }
    //3-: removes the employee whose employee id equals the parameter key from the file named Employees.txt
    public void removeEmployee(String key)
    {
        database.deleteRecord(key);
        database.saveToFile();
    }
    //4-writes all unsaved data to the file named Employees.txt
    @Override
    public void logout()
    { 
        database.saveToFile();
    }
}


