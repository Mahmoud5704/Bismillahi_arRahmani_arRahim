/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6;

import java.util.Scanner;
import static javaapplication6.JavaApplication6.generateID;
import static javaapplication6.JavaApplication6.parseChoice;

/**
 *
 * @author zeyad
 */
public class AdminAcc {
    public static void pannel(){
        acc = AdminRole();
        boolean logged_in = true;
        Scanner ascanner = new Scanner(System.in);
        while(logged_in){
            System.out.println("=================================");
            System.out.println("A) Add employee\nB) list employees\nC) remove employee\nQ) logout");
            String command_str = ascanner.nextLine();
            char command = parseChoice(command_str);
            switch(command){
                case 'a':
                    System.out.print("employee name: ");
                    String name = ascanner.nextLine();
                    if(!Validation.verifyName(name)){
                        System.out.println("Names must only contain letters!");
                        break;
                    }
                    System.out.print("employee email: ");
                    String email = ascanner.nextLine();
                    if(!Validation.verifyEmail(email)){
                        System.out.println("incorrect email format!");
                        break;
                    }
                    System.out.print("employee address: ");
                    String address = ascanner.nextLine();
                    System.out.print("employee phone number: ");
                    String phone_num = ascanner.nextLine();
                    if(!Validation.verifyPhoneNum(phone_num)){
                        System.out.println("phone number must contain 11 digits");
                        break;
                    }
                    //generate ID for employee
                    EmployeeUser[] employees = acc.getListOfEmployees();
                    String[] IDs = new String[employees.length];
                    for(int i = 0; i < IDs.length; i++){
                        IDs[i] = employees[i].getSearchKey();
                    }
                    String ID = generateID("E", IDs);
                    acc.addEmployee(ID, name, email, address, phone_num);
                    break;
                case 'b':
                    EmployeeUser[] employees = acc.getListOfEmployees();
                    System.out.println("ID, name, email, address, phone number");
                    for(int i = 0; i < employees.length; i++){
                        System.out.println(employees[i].lineRepresentation());
                    }
                    break;
                case 'c':
                    EmployeeUser[] employees = acc.getListOfEmployees(); //list of employees before modification
                    System.out.print("Enter ID of the employee to be removed: ");
                    String target_ID = ascanner.nextLine();
                    boolean does_employee_exist = false;
                    for(int i = 0; i < employees.length; i++){
                        if (employees[i].getSearchKey() == target_ID){
                            does_employee_exist = true;
                            break;
                        }
                    }
                    if(does_employee_exist){
                        acc.removeEmployee(target_ID);
                    }
                    else{
                        System.out.println("no employee has the given ID... Please try again");
                    }
                    break;
                case 'q':
                    acc.logout();
                    logged_in = false;
                    break;
                default:
                    System.out.println("incorrect input! you must select one of the following options:");
            }
        }
    }
}
