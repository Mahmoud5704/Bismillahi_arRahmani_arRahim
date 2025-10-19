package javaapplication7;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;
public class EmployeeAcc {

    public static boolean verifySSN(String SSN) {
        if(!Validation.verifySSSN(SSN, SSN.length())) {
            System.out.println("invalid SSN");
            return false;
        }
        return true;
    }

    public static boolean verifyProductID(String productID) {
        if(!Validation.verifyID(productID)) {
            System.out.println("ID must start with a letter followed by 4 integers");
            return false;
        }
        return true;
    }

    public static LocalDate parseDate(String dateStr) {
        try {
            return LocalDate.parse(dateStr);
        } catch (Exception e) {
            System.out.println("incorrect date format!");
            return null;
        }
    }

    
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
                    
                   String productID = Validation.generateID(productID);
                    
                   
                    acc.addProduct(productID, productName, manufacturerName, supplierName, quant);
                    acc.logout();
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
                    if(!verifySSN(SSN)){
                        break;
                    }
                    System.out.print("Enter product id: ");
                    productID = escanner.nextLine();
                    if(!verifyProductID(productID)){
                        break;
                    }
                    System.out.print("Enter purchase date: ");
                    purchaseDate_str = escanner.nextLine();
                    purchaseDate = parseDate(purchaseDate_str);
                    if(purchaseDate == null){
                        break;
                    }
                    success = acc.purchaseProduct(SSN, productID, purchaseDate);
                    if(success)
                    {
                        System.out.println("product purchased successfully");
                        acc.logout();
                    }
                    else
                        System.out.println("this product is not in stock!");
                    break;
                case 'e':
                    System.out.print("Enter customer SSN: ");
                    SSN = escanner.nextLine();
                    if(!verifySSN(SSN)){
                        break;
                    }
                    System.out.print("Enter product ID: ");
                    productID = escanner.nextLine();
                    if(!verifyProductID(productID)){
                        break;
                    }
                    System.out.print("Enter purchase date: ");
                    purchaseDate_str = escanner.nextLine();
                    purchaseDate = parseDate(purchaseDate_str);
                    if(purchaseDate == null){
                        break;
                    }
                    System.out.print("Enter return date: ");
                    String returnDate_str = escanner.nextLine();
                    LocalDate returnDate = parseDate(returnDate_str);
                    if(returnDate == null){
                        break; 
                    }
                    double result = acc.returnProduct(SSN, productID, purchaseDate, returnDate);
                    if(result == -1){
                        System.out.println("unable to return product, verify your input!");
                    }
                    else{
                        acc.logout();
                        System.out.println("product sold for " + result + " EGP returned successfully!");
                    }
                    break;
                case 'f':
                    System.out.print("Enter customer SSN: ");
                    SSN = escanner.nextLine();
                    if(!verifySSN(SSN)){
                        break;
                    }
                    System.out.print("Enter purchase date: ");
                    purchaseDate_str = escanner.nextLine();
                    purchaseDate = parseDate(purchaseDate_str);
                    if(purchaseDate == null){
                        break;
                    }
                    success = acc.applyPayment(SSN, purchaseDate);
                    if(success){
                        acc.logout();
                        System.out.println("payment applied successfully!");
                    }
                    else{
                        System.out.println("this record doesn't exist or it has already been paid!");
                    }
                    break;
                case 'q':
                 //   acc.logout();
                    logged_in = false;
                    break;
                default:
                    System.out.println("incorrect choice.");
            }
        }
    }
}

  
