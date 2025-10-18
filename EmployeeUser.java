package full_project;

public class EmployeeUser implements interface_Record {
    private String employeeId;
    private String Name;
    private String Email;
    private String Address;
    private String PhoneNumber;

    public EmployeeUser(String employeeId, String name, String email, String address, String phoneNumber) {
        this.employeeId = employeeId;
        this.Name = name;
        this.Email = email;
        this.Address = address;
        this.PhoneNumber = phoneNumber;
    }
    @Override
    public String lineRepresentation() {
        return this.employeeId + "," + this.Name + "," + this.Email + "," + this.Address + "," + this.PhoneNumber;
    }
    @Override
    public String getSearchKey() {
        return this.employeeId;
    }
}