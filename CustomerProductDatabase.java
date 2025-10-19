package javaapplication7;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomerProductDatabase extends Abstrat_FileDatabase<CustomerProduct> {
    public CustomerProductDatabase(String filename) {
        super(filename);
    }

    @Override
    public CustomerProduct createRecordFrom(String line) {
        String[] parts = line.trim().split(",");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate date = LocalDate.parse(parts[2], formatter);
            CustomerProduct obj1 = new CustomerProduct(parts[0], parts[1], date);
            obj1.setPaid(Boolean.parseBoolean(parts[3]));
            return obj1;
    }
}
