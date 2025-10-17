/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6;

import java.time.LocalDate;
import java.util.Scanner;
import static javaapplication6.JavaApplication6.generateID;
import static javaapplication6.JavaApplication6.parseChoice;

/**
 *
 * @author zeyad
 */
public class EmployeeAcc {
    public static void pannel(){
        acc = EmployeeRole();
        boolean logged_in = true;
        Scanner escanner = new Scanner(System.in);
        System.out.println("select command:");
        System.out.println("A) Add product\nB) list products\nC) list purchasing operations\nD) purchase product");
        System.out.println("E) return product\nF) apply payment\nQ) logout");
        String command_str = escanner.nextLine();
        char choice = parseChoice(command_str);
        switch(choice){
            case 'a':
                System.out.print("Enter product name: ");
                String productName = escanner.nextLine();
                //verify product name?
                System.out.print("Enter manufacturer name: ");
                String manufacturerName = escanner.nextLine();
                //verify manufacturer name?
                System.out.print("Enter supplier name: ");
                String supplierName = escanner.nextLine();
                System.out.print("Enter quantity: ");
                String quant_str = escanner.nextLine();
                int quant;
                try{
                    quant = Integer.parseInt(quant_str);
                    if (quant <= 0){
                        System.out.println("Quantity must be a positive number!");
                        break;
                    }
                }
                catch(Exception e){
                    System.out.println("Quantity must be a positive number!");
                    break;
                }
                Product[] products = acc.getListOfProducts();
                String[] IDs = new String[products.length];
                for(int i = 0; i < IDs.length; i++){
                    IDs[i] = products[i].getSearchKey();
                }
                String ID = generateID("P", IDs);
                acc.addProduct(ID, productName, manufacturerName, supplierName, quant);
                break;
            case 'b':
                Product[] products = acc.getListOfProducts();
                System.out.println("ID, product name, manufacturer name, supplier name, quantity");
                for(int i = 0; i < products.length; i++){
                    System.out.println(products[i].lineRepresentation());
                }
                break;
            case 'c':
                CustomerProduct[] customerProducts = acc.getListOfPurchasingOperations();
                System.out.println("customer SSN, product ID, purchase date");
                for(int i = 0; i < customerProducts.length; i++){
                    System.out.println(customerProducts[i].lineRepresentation());
                }
                break;
            case 'd':
                System.out.print("Enter customerSSN: ");
                String SSN = escanner.nextLine();
                //validate SSN
                System.out.print("Enter product id: ");
                String productID = escanner.nextLine();
                //validate ID
                System.out.print("Enter purchase date: ");
                String date = escanner.nextLine();
                try{
                    LocalDate purchaseDate = LocalDate.parse(date); //check date format.
                }
                catch(Exception e){
                    System.out.println("incorrect date format!");
                    break;
                }
                boolean success = acc.purchaseProduct(SSN, productID, purchaseDate);
                if(success)
                    System.out.println("product purchased successfully");
                else
                    System.out.println("this product is not in stock!");
                break;
            case 'e':
                
        }
    }
}
