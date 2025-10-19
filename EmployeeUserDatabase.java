package full_project;

public class EmployeeUserDatabase extends Abstrat_FileDatabase<EmployeeUser> {

    public EmployeeUserDatabase(String filename) {
        super(filename);
    }



    @Override
    public EmployeeUser createRecordFrom(String line) {
        String[] parts = line.split(",");
        EmployeeUser obj1 = new EmployeeUser(parts[0], parts[1], parts[2], parts[3], parts[4]);
        return obj1;

    }
}
