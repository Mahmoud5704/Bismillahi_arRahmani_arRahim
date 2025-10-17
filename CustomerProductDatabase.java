package tasks.absract_database;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomerProductDatabase extends Abstrat_FileDatabase<CustomerProduct> {
    public CustomerProductDatabase(String filename) {
        super(filename);
    }

    @Override
    public String getkey(CustomerProduct ob) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return ob.getCustomerSSN() + "," + ob.getProductID() + "," + ob.getPurchaseDate().format(formatter);
    }

    @Override
    public String getline(CustomerProduct ob) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return ob.getCustomerSSN() + "," + ob.getProductID() + "," + ob.getPurchaseDate().format(formatter)
                + "," + ob.isPaid();
    }

    @Override
    public CustomerProduct createRecordFrom(String line) {
        String[] parts = line.trim().split(",");
        if (contains(parts[0] + "," + parts[1] + "," + parts[2])) {
            System.out.print("invalid,key is repeated!!!");
            return null;
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate date = LocalDate.parse(parts[2], formatter);
            CustomerProduct obj1 = new CustomerProduct(parts[0], parts[1], date);
            obj1.setPaid(Boolean.parseBoolean(parts[3]));
            return obj1;

        }
    }
}
