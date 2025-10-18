/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package full_project;

import java.util.Scanner;

/**
 *
 * @author zeyad
 */
public class main {
    public static final String Employeefile = "src/full_project/Employees.txt";
    public static final String Productsfile = "src/full_project/Products.txt";
    public static final String CustomersProductsfile = "src/full_project/CustomersProducts.txt";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scanner = new Scanner(System.in);
        boolean quit_flag = false;
        while(!quit_flag){
            System.out.println("Welcome!\nselect user:");
            System.out.println("A) admin\nB) employee\nQ) quit");
            String acc_choice_str = scanner.nextLine();
            char acc_choice = Validation.parseChoice(acc_choice_str);
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
