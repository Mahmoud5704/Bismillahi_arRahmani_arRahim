package full_project;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeRole implements interface_UserRole{
    // variable:
    private ProductDatabase productsDatabase;
    private CustomerProductDatabase customerproductDatabase;

    // constructor:
    public EmployeeRole() {

        productsDatabase = new ProductDatabase(main.Productsfile);
        productsDatabase.readFromFile();
        customerproductDatabase = new CustomerProductDatabase(main.CustomersProductsfile);
        customerproductDatabase.readFromFile();
    }

    // helper private class
    private void updateProductQuantity(Product product, int delta) {
        
        product.setQuantity(product.getQuantity() + delta);
        productsDatabase.saveToFile();
    }

    // class contains six methods:
    // 1
    public void addProduct(String productID, String productName, String manufacturerName, String supplierName,
            int quantity) {
                     System.out.print("Enter product price: ");
                    Scanner escanner = new Scanner(System.in);
                    String price_str = escanner.nextLine();
                    float price;
                    try{
                        price = Float.parseFloat(price_str);
                        if(price <= 0){
                            System.out.println("Price must be a positive number!");
                            return;
                        }
                    }
                    catch(Exception e){
                        System.out.println("Price must be a positive number!");
                        return;
                    }
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
        Product product = productsDatabase.getRecord(productID);
        if (product == null ||  product.getQuantity() <= 0)
            return false;

        else {

            updateProductQuantity(product, -1);

            CustomerProduct customerProduct = new CustomerProduct(customerSSN, productID, purchaseDate);
            customerproductDatabase.insertRecord(customerProduct);
            customerproductDatabase.saveToFile();
            return true;
        }

    }

    // 5-**
    public double returnProduct(String customerSSN, String productID, LocalDate purchaseDate, LocalDate returnDate) {
         productsDatabase.readFromFile();
         customerproductDatabase.readFromFile();
        if (returnDate.isBefore(purchaseDate) || !productsDatabase.contains(productID)
                ||customerproductDatabase.contains(customerSSN)
                || ChronoUnit.DAYS.between(purchaseDate, returnDate) > 14)
            return -1;
        

        Product product = productsDatabase.getRecord(productID);
        updateProductQuantity(product, 1);
        customerproductDatabase.readFromFile();
        customerproductDatabase.deleteRecord(customerSSN);
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

    @Override
    public void logout() {
        productsDatabase.saveToFile();
        customerproductDatabase.saveToFile();
    }
}
