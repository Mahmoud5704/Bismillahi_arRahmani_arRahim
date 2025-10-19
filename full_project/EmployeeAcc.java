package full_project;

import java.time.LocalDate;
import java.util.Scanner;


public class EmployeeAcc {
    public static void pannel(){
        EmployeeRole acc = new EmployeeRole();
        boolean logged_in = true;
        Scanner escanner = new Scanner(System.in);
        while(logged_in){
        System.out.println("select command:");
        System.out.println("A) Add product\nB) list products\nC) list purchasing operations\nD) purchase product");
        System.out.println("E) return product\nF) apply payment\nQ) logout");
        String command_str = escanner.nextLine();
        char choice = Validation.parseChoice(command_str);
        String SSN;
        String purchaseDate_str;
        LocalDate purchaseDate;
        String productID;
        boolean success;
        Product products[];
        switch(choice){
            case 'a':
                System.out.print("Enter product name: ");
                String productName = escanner.nextLine();
                if(!Validation.verifyName(productName)){ //modify the function to allow digits for product names?
                        System.out.println("Names must only contain letters!");
                        break;
                }
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
                products = acc.getListOfProducts();
                String[] IDs = new String[products.length];
                for(int i = 0; i < IDs.length; i++){
                    IDs[i] = products[i].getSearchKey();
                }
//                String ID = generateID("P", IDs);
                System.out.print("Enter product ID: ");
                productID = escanner.nextLine();
                if(!Validation.verifyID(productID)){
                    System.out.println("ID must start with a letter followed by 4 integers");
                    break;
                }
                System.out.print("Enter product price: ");
                String price_str = escanner.nextLine();
                float price;
                try{
                    price = Float.parseFloat(price_str);
                    if(price <= 0){
                        System.out.println("Price must be a positive number!");
                        break;
                    }
                }
                catch(Exception e){
                    System.out.println("Price must be a positive number!");
                    break;
                }
                acc.addProduct(productID, productName, manufacturerName, supplierName, quant, price);
                break;
            case 'b':
                products = acc.getListOfProducts();
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
                SSN = escanner.nextLine();
                if(!Validation.verifyNum(SSN, 14)){
                    System.out.println("invalid SSN, SSN must consist of 14 digits");
                    break;
                }
                System.out.print("Enter product id: ");
                productID = escanner.nextLine();
                if(!Validation.verifyID(productID)){
                    System.out.println("ID must start with a letter followed by 4 integers");
                }
                System.out.print("Enter purchase date: ");
                purchaseDate_str = escanner.nextLine();
                try{
                    purchaseDate = LocalDate.parse(purchaseDate_str); //check date format.
                }
                catch(Exception e){
                    System.out.println("incorrect date format!");
                    break;
                }
                success = acc.purchaseProduct(SSN, productID, purchaseDate);
                if(success)
                    System.out.println("product purchased successfully");
                else
                    System.out.println("this product is not in stock!");
                break;
            case 'e':
                System.out.print("Enter customer SSN");
                SSN = escanner.nextLine();
                if(!Validation.verifyNum(SSN, 14)){
                    System.out.println("invalid SSN, SSN must consist of 14 digits");
                    break;
                }
                System.out.print("Enter product ID: ");
                productID = escanner.nextLine();
                if(!Validation.verifyID(productID)){
                    System.out.println("ID must start with a letter followed by 4 integers");
                    break;
                }
                System.out.print("Enter purchase date: ");
                purchaseDate_str = escanner.nextLine();
                //validate date
                try{
                    purchaseDate = LocalDate.parse(purchaseDate_str);
                }
                catch(Exception e){
                    System.out.println("incorrect date formate!");
                    break;
                }
                System.out.println("Enter return date: ");
                String returnDate_str = escanner.nextLine();
                LocalDate returnDate;
                try{
                    returnDate = LocalDate.parse(returnDate_str);
                }
                catch(Exception e){
                    System.out.println("incorrect date formate!");
                    break; 
                }
                //validate date
                double result = acc.returnProduct(SSN, productID,purchaseDate ,returnDate);
                if(result == -1){
                    System.out.println("unable to return product, verify your input!");
                }
                else{
                    System.out.println("product sold for " + result + " EGP successfully!");
                }
                break;
            case 'f':
                System.out.print("Enter customer SSN: ");
                SSN = escanner.nextLine();
                if(!Validation.verifyNum(SSN, 14)){
                    System.out.println("invalid SSN, SSN must consist of 14 digits");
                    break;
                }
                System.out.print("Enter purchase date: ");
                purchaseDate_str = escanner.nextLine();
                try{
                    purchaseDate = LocalDate.parse(purchaseDate_str);
                }
                catch(Exception e){
                    System.out.println("incorrect date format!");
                    break;
                }
                success = acc.applyPayment(SSN, purchaseDate);
                if(success){
                    System.out.println("payment applied successfully!");
                }
                else{
                    System.out.println("this record doesn't exist or it has already been paid!");
                }
                break;
            case 'q':
                acc.logout();
                logged_in = false;
                break;
            default:
                System.out.println("incorrect choice.");
        }
        }
    }
}
