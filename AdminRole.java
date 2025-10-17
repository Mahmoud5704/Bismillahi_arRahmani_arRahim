/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

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
         

        
    }
    //2-returns an array that contains all the employees stored in the file named Employees.txt
    public EmployeeUser[] getListOfEmployees()
    {
        
        
        List<EmployeeUser> employees  = database.returnAllRecords();
        if(employees == null)
        {
            System.out.printf("\nthere is NO Employees in the file\n");
            return null;
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




































































EmployeeUser user = new EmployeeUser (employeeId,name,email,address,phoneNumber);
        
        if(contains(user.employeeId))
        {
            System.out.printf("\nthe employee whose id is %s is already exist\n",user.employeeId );
            return;
        }
        
        String infro = EmployeeUser.lineRepresentation();
        try
        {
        BufferedWriter writer = new BufferedWriter(new FileWriter (mainclass.Employeefile));
        writer.write(infro);
        writer.close(mainclass.Employeefile);
        }
        catch(IOException e)
        {
            System.out.printf("\nthe file %s is NOT exist\n",mainclass.Employeefile);
                    
        }