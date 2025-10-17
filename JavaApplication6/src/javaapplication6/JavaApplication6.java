/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication6;

import java.util.Scanner;

/**
 *
 * @author zeyad
 */
public class JavaApplication6 {

    /**
     * @param args the command line arguments
     */
    public static boolean verifyID(String ID){
        if(ID.length() <= 1)
            return false;
        //make sure the ID contains letters and numbers only!
        return true;
    }
    public static boolean verifyName(String name){
        return true; //verify name
    }
    public static boolean verifyEmail(String email){
        return true; //verify email
    }
    public static boolean verifyPhoneNum(String num){
        return true; //verify phone number
    }
    public static char parseChoice(String choice_str){
        if (choice_str.length() > 1)
            return ' '; //throw away val
        else
            return choice_str.charAt(0);
    }
    public static void AdminAcc(){
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
                case 'A':
                    System.out.print("employee ID: ");
                    String ID = ascanner.nextLine();
                    if(!verifyID(ID)){
                        System.out.println("ID must contains letters and numbers only!");
                        break;
                    }
                    System.out.print("employee name: ");
                    String name = ascanner.nextLine();
                    if(!verifyName(name)){
                        System.out.println("Names must only contain letters!");
                        break;
                    }
                    System.out.print("employee email: ");
                    String email = ascanner.nextLine();
                    if(!verifyEmail(email)){
                        System.out.println("incorrect email format!");
                        break;
                    }
                    System.out.print("employee address: ");
                    String address = ascanner.nextLine();
                    System.out.print("employee phone number: ");
                    String phone_num = ascanner.nextLine();
                    if(!verifyPhoneNum(phone_num)){
                        System.out.println("phone number must contain 11 digits");
                        break;
                    }
                    acc.addEmployee(ID, name, email, address, phone_num);
                    break;
                case 'b':
                case 'B':
                    break;
                case 'c':
                case 'C':
                    break;
            }
        }
    }
    public static void EmployeeAcc(){
        
    }
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scanner = new Scanner(System.in);
        boolean quit_flag = false;
        while(!quit_flag){
            System.out.println("Welcome!\nselect user:");
            System.out.println("A) admin\nB) employee\nQ) quit");
            String acc_choice_str = scanner.nextLine();
            if (acc_choice_str.length() > 1){
                System.out.println("your choice must be a character!");
            }
            char acc_choice = acc_choice_str.charAt(0);
            switch(acc_choice){
                case 'a':
                case 'A':
                    AdminAcc();
                    break;
                case 'b':
                case 'B':
                    EmployeeAcc();
                    break;
                case 'q':
                case 'Q':
                    quit_flag = true;
            }
        }
    }
    
}
