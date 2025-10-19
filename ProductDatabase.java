package full_project;

public class ProductDatabase extends Abstrat_FileDatabase<Product> {
    public ProductDatabase(String filename) {
        super(filename);
    }

    @Override
    public Product createRecordFrom(String line) {
        String[] parts = line.split(",");

        Product obj1 = new Product(parts[0], parts[1], parts[2], parts[3], Integer.parseInt(parts[4]),
                Float.parseFloat(parts[5]));
        return obj1;

    }
}
