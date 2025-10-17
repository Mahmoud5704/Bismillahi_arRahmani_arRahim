/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.time.LocalDate;  
import java.time.temporal.ChronoUnit;
import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
public class EmployeeRole 
{
    //variable:
    private ProductsDatabase productsDatabase;
    private CustomerProductDatabase customerproductDatabase;
    //constructor:
    public EmployeeRole()
    {
        
        productsDatabase = new ProductsDatabase(mainclass.Productsfile);
        productsDatabase.readFromFile();
        customerproductDatabase = new CustomerProductDatabase(mainclass.CustomersProductsfile);
        customerproductDatabase.readFromFile();       
    }
    //class contains six methods:
    //1-adds a new product to the file named Products.txt.
    public void addProduct(String productID, String productName, String manufacturerName, String supplierName, int quantity)
    {
        Product product = new Product(productID,productName,manufacturerName,supplierName,quantity);
        productsDatabase.insertRecord(product);
        productsDatabase.saveToFile();
    }
    //2-returns an array that contains all the products stored in the file named Products.txt.
    public Product[] getListOfProducts()
    {
        ArrayList<Product> products = productsDatabase.returnAllRecords();
        if(products == null||products.isEmpty())
        {
            System.out.printf("\nthere is NO products in the file\n");
            return new Product[0];//empty array
        }
        return products.toArray(new Product[0]);
    }
    //3-returns an array that contains all the purchasing operations stored in the file named CustomersProducts.txt
    public CustomerProduct[] getListOfPurchasingOperations()
    {
        ArrayList<CustomerProduct> customerproduct = customerproductDatabase.returnAllRecords();
        if(customerproduct == null||customerproduct.isEmpty())
        {
            System.out.printf("\nthere is NO PurchasingOperations in the file\n");
            return new CustomerProduct[0];
        }
        return customerproduct.toArray(new CustomerProduct[0]);
    }
    //4-returns false if the quantity variable of the product whose product id matches the parameter productID is zero. Otherwise, the method decreases the quantity variable of the product by one, adds a new purchasing operation to the file named CustomersProducts.txt, updates the file Products.txt and returns true.
    public boolean purchaseProduct(String customerSSN, String productID, LocalDate purchaseDate)
    {
        Product product = productsDatabase.getRecord(productID);
        if(product == null)
            return false;
        int q = product.getQuantity();
        if(q==0)
        {
            return false;
        }
        else
        {
            product.setQuantity(--q);
            productsDatabase.saveToFile();
            CustomerProduct customerProduct = new CustomerProduct(customerSSN,productID,purchaseDate);
            customerproductDatabase.insertRecord(customerProduct);
            customerproductDatabase.saveToFile();
            return true;
        }
            
    }
    //5-**
    public double returnProduct(String customerSSN, String productID, LocalDate purchaseDate ,LocalDate returnDate)
    {
        if(returnDate.isBefore(purchaseDate))
            return -1;
        else if(productsDatabase.contains(productID)==false)
            return -1;
        else if(customerproductDatabase.contains(customerSSN+","+productID+","+ purchaseDate)==false)
            return -1;
        else if(ChronoUnit.DAYS.between(purchaseDate, returnDate)>14)
            return -1;
        Product product = productsDatabase.getRecord(productID);
        int q = product.getQuantity();        
        
        
            
            product.setQuantity(++q);
            customerproductDatabase.deleteRecord(customerSSN+","+productID+","+ purchaseDate);
            customerproductDatabase.saveToFile();
            productsDatabase.saveToFile();
            
            String dataOfProduct = product.lineRepresentation();
            String []datas =  dataOfProduct.split("[,]+");
            return Double.parseDouble(datas[5]);
        
    }
    //6-This method marks a customer purchase a product. It searches for the purchaserecord in the that matches the given customerSSN and purchaseDate. If the record exists and is not already marked as paid, the method updates the paid flag to true and saves the update.
    public boolean applyPayment(String customerSSN, LocalDate purchaseDate)
    {
        ArrayList<CustomerProduct> datas = customerproductDatabase.returnAllRecords();
        for(CustomerProduct data : datas)
        {
            if(customerSSN.equals(data.getCustomerSSN()) && purchaseDate.equals(data.getPurchaseDate()) && !data.isPaid())
            {
                data.setPaid(true);
                customerproductDatabase.saveToFile(); 
                return true;
            }
        }
        return false;
     
           
    }
    @Override
    public void logout()
    {
        productsDatabase.saveToFile();
        customerproductDatabase.saveToFile();
    }
}
