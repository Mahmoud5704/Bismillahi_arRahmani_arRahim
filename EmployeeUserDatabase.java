
public class EmployeeUserDatabase extends Abstrat_FileDatabase<EmployeeUser> {

    public EmployeeUserDatabase(String filename) {
        super(filename);
    }

    @Override
    public String getkey(EmployeeUser ob) {
        return ob.getSearchKey();
    }

    @Override
    public String getline(EmployeeUser ob) {
        return ob.lineRepresentation();

    }

    @Override
    public EmployeeUser createRecordFrom(String line) {
        String[] parts = line.split(",");

        EmployeeUser obj1 = new EmployeeUser(parts[0], parts[1], parts[2], parts[3], parts[4]);

        if (contains(parts[0])) {
            System.out.println("Invalid, id is repeated!!!");
            return null;
        }
        return obj1;

    }
}
