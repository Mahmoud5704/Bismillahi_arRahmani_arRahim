package javaapplication7;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class EmployeeRole implements interface_UserRole{
    // variable:
    private ProductDatabase productsDatabase;
    private CustomerProductDatabase customerproductDatabase;

    // constructor:
    public EmployeeRole() {

      productsDatabase = new ProductDatabase("Product.txt");
        customerproductDatabase = new CustomerProductDatabase("CustomerProducts.txt");
        
    }

    // class contains six methods:
    // 1
    public void addProduct(String productID, String productName, String manufacturerName, String supplierName,
            int quantity) {
        float price =250;
        Product product = new Product(productID, productName, manufacturerName, supplierName, quantity, price); //missing price argument
       
        productsDatabase.insertRecord(product);
        productsDatabase.saveToFile();
    }

    // 2
    public Product[] getListOfProducts() {
        ArrayList<Product> products = productsDatabase.returnAllRecords();
        if (products == null || products.isEmpty()) {
            System.out.printf("\nthere is NO products in the file\n");
            return new Product[0];// empty array
        }
        return products.toArray(new Product[0]);
    }
  
    // 3
    public CustomerProduct[] getListOfPurchasingOperations() {
        ArrayList<CustomerProduct> customerproduct = customerproductDatabase.returnAllRecords();
        if (customerproduct == null || customerproduct.isEmpty()) {
            System.out.printf("\nthere is NO PurchasingOperations in the file\n");
            return new CustomerProduct[0];
        }
        return customerproduct.toArray(new CustomerProduct[0]);
    }

    // 4
    public boolean purchaseProduct(String customerSSN, String productID, LocalDate purchaseDate) {
       // productsDatabase.readFromFile();
        Product product = productsDatabase.getRecord(productID);
          if (product == null) {
        System.out.println(" ERROR: Product with ID " + productID + " not found in database!");
        return false;
    }
            
        System.out.println(product.lineRepresentation());
        if (product == null ||  product.getQuantity() <= 0)
            return false;

        else {
            Product product = productsDatabase.getRecord(productID);
            product.setQuantity(product.getQuantity() - 1);
            productsDatabase.saveToFile();
            CustomerProduct customerProduct = new CustomerProduct(customerSSN, productID, purchaseDate);
            customerproductDatabase.insertRecord(customerProduct);
            customerproductDatabase.saveToFile();
            return true;
        }
    }

    // 5-
    public double returnProduct(String customerSSN, String productID, LocalDate purchaseDate, LocalDate returnDate) {
    
       //  customerproductDatabase.readFromFile();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String key = customerSSN + "," + productID + "," + purchaseDate.format(formatter);
        if (returnDate.isBefore(purchaseDate) || !productsDatabase.contains(productID)
                || !customerproductDatabase.contains(key)
                || ChronoUnit.DAYS.between(purchaseDate, returnDate) > 14) {
            return -1;
        }
        Product product = productsDatabase.getRecord(productID);
        product.setQuantity(product.getQuantity() + 1);
        productsDatabase.saveToFile();
        
        customerproductDatabase.deleteRecord(key);
        customerproductDatabase.saveToFile();

        String dataOfProduct = product.lineRepresentation();
        String[] datas = dataOfProduct.split("[,]+");
        return Double.parseDouble(datas[5]);
    }

    // 6-
    public boolean applyPayment(String customerSSN, LocalDate purchaseDate) {
        ArrayList<CustomerProduct> datas = customerproductDatabase.returnAllRecords();
        for (CustomerProduct data : datas) {
            if (customerSSN.equals(data.getCustomerSSN()) && purchaseDate.equals(data.getPurchaseDate())
                    && !data.isPaid()) {
                data.setPaid(true);
                customerproductDatabase.saveToFile();
                return true;
            }
        }
        return false;

    }
    //7-
    @Override
    public void logout() {
        productsDatabase.saveToFile();
        customerproductDatabase.saveToFile();
    }
}
