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
    public static String [] input_SSN_ID_purchaseDate(Scanner escanner){
        int counter = 1;
        String SSN = "";
        String productID = "";
        String purchaseDate_str = "";
        while(true){
            switch(counter){
                case 1:
                    System.out.print("Enter customerSSN: ");
                    SSN = escanner.nextLine();
                    if(!verifySSN(SSN))
                        continue;
                    counter++;
                case 2:
                    System.out.print("Enter product id: ");
                    productID = escanner.nextLine();
                    if(!verifyProductID(productID))
                        continue;
                    counter++;
                case 3:
                    System.out.print("Enter purchase date: ");
                    purchaseDate_str = escanner.nextLine();
                    LocalDate purchaseDate = parseDate(purchaseDate_str);
                    if(purchaseDate == null)
                        continue;
                default:
                  String[] result = {SSN, productID, purchaseDate_str};
                  return result;
            }
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
            String SSN = "";
            String purchaseDate_str = "";
            LocalDate purchaseDate = LocalDate.now();
            String returnDate_str = "";
            LocalDate returnDate = LocalDate.now();
            String productID = "";
            boolean success = false;
            Product products[];
            int quant = 0;
            String productName = "";
            String manufacturerName = "";
            String supplierName = "";
            String quant_str = "";
            
            boolean taking_input = false;
            int counter = 1;
            switch(choice){
                case 'a':
                    taking_input = true;
                    while(taking_input){
                        switch(counter){
                            case 1:
                                System.out.print("Enter product name: ");
                                productName = escanner.nextLine();
                                if(!Validation.verifyName(productName)){ //modify the function to allow digits for product names?
                                    System.out.println("Names must only contain letters!");
                                    continue;
                                }
                                counter++;
                            case 2:
                                System.out.print("Enter manufacturer name: ");
                                manufacturerName = escanner.nextLine();
                                System.out.print("Enter supplier name: ");
                                supplierName = escanner.nextLine();
                                counter++;
                            case 3:
                                System.out.print("Enter quantity: ");
                                quant_str = escanner.nextLine();
                                try{
                                    quant = Integer.parseInt(quant_str);
                                    if (quant <= 0){
                                        System.out.println("Quantity must be a positive number!");
                                        continue;
                                    }
                                }
                                catch(Exception e){
                                    System.out.println("Quantity must be a positive number!");
                                    continue;
                                }
                                counter++;
                            default:
                                taking_input = false;
                                counter = 1;
                                products = acc.getListOfProducts();
                                String[] IDs = new String[products.length];
                                for(int i = 0; i < IDs.length; i++){
                                    IDs[i] = products[i].getSearchKey();
                                }
                                //  String ID = Validation.generateID(productID);
                                System.out.print("Enter product ID: ");
                                productID = escanner.nextLine();
                                if(!verifyProductID(productID)){
                                    continue;
                                }
                        
                                acc.addProduct(productID, productName, manufacturerName, supplierName, quant);
                                acc.logout();
                        }
                    }
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
                    String[] inputs = input_SSN_ID_purchaseDate(escanner);
                    success = acc.purchaseProduct(inputs[0], inputs[1], LocalDate.parse(inputs[2]));
                    if(success){
                        System.out.println("product purchased successfully");
                        acc.logout();
                    }
                    else
                        System.out.println("this product is not in stock!");
                    break;
                    
                case 'e':
                    String[] Inputs = input_SSN_ID_purchaseDate(escanner);
                    while(true){
                        System.out.print("Enter return date: ");
                        returnDate_str = escanner.nextLine();
                        returnDate = parseDate(returnDate_str);
                        if(returnDate != null){
                            break; 
                        }
                    }
                    double result = acc.returnProduct(Inputs[0], Inputs[1], LocalDate.parse(Inputs[2]), returnDate);
                    if(result == -1){
                        System.out.println("unable to return product...");
                    }
                    else{
                        acc.logout();
                        System.out.println("product sold for " + result + " EGP returned successfully!");
                    }
                    break;
                case 'f':
                    taking_input = true;
                    while(taking_input){
                        switch(counter){
                            case 1:
                                System.out.print("Enter customer SSN: ");
                                SSN = escanner.nextLine();
                                if(!verifySSN(SSN)){
                                    continue;
                                }
                                counter++;
                            case 2:
                                System.out.print("Enter purchase date: ");
                                purchaseDate_str = escanner.nextLine();
                                purchaseDate = parseDate(purchaseDate_str);
                                if(purchaseDate == null){
                                    continue;
                                }
                            default:
                                counter = 1;
                                taking_input = false;
                        }
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