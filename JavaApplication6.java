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
    
    public static char parseChoice(String choice_str){
        if (choice_str.length() > 1)
            return ' '; //throw away val
        else
            return Character.toLowerCase(choice_str.charAt(0)); //converts it to lower case so that it is case insensitive
    }
    public static String generateID(String prefix, String[] IDList){
        while(true){
            int num_part = (int) (Math.random() * 1000); //from 0 to 999
            
            String ID = prefix + num_part;
            boolean already_exist = false;
            for(int i = 0; i < IDList.length; i++){
                if(IDList[i].equals(ID)){
                    already_exist = true;
                    break;
                }
            }
            if(!already_exist){
                return ID;
            }
        }
    }
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scanner = new Scanner(System.in);
        boolean quit_flag = false;
        while(!quit_flag){
            System.out.println("Welcome!\nselect user:");
            System.out.println("A) admin\nB) employee\nQ) quit");
            String acc_choice_str = scanner.nextLine();
            char acc_choice = parseChoice(acc_choice_str);
            switch(acc_choice){
                case 'a':
                    AdminAcc.pannel();
                    break;
                case 'b':
                    EmployeeAcc.pannel();
                    break;
                case 'q':
                    quit_flag = true;
                    break;
                default:
                    System.out.println("You must choose A, B or Q! and your input must be a single character");
            }
        }
    }
    
}
