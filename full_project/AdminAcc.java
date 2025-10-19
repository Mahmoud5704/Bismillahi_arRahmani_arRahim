package full_project;


import java.util.Scanner;

public class AdminAcc {
    public static void pannel(){
        AdminRole acc = new AdminRole();
        boolean logged_in = true;
        Scanner ascanner = new Scanner(System.in);
        EmployeeUser[] employees;
        while(logged_in){
            System.out.println("=================================");
            System.out.println("A) Add employee\nB) list employees\nC) remove employee\nQ) logout");
            String command_str = ascanner.nextLine();
            char command = Validation.parseChoice(command_str);
            switch(command){
                case 'a':
                    System.out.print("employee name: ");
                    String name = ascanner.nextLine();
                    if(!Validation.verifyName(name)){
                        System.out.println("Names must only contain letters!");
                        break;
                    }
                    System.out.print("employee email: ");
                    String email = ascanner.nextLine();
                    if(!Validation.verifyEmail(email)){
                        System.out.println("incorrect email format!");
                        break;
                    }
                    System.out.print("employee address: ");
                    String address = ascanner.nextLine();
                    System.out.print("employee phone number: ");
                    String phone_num = ascanner.nextLine();
                    if(!Validation.verifyNum(phone_num, 11)){
                        System.out.println("phone number must contain 11 digits");
                        break;
                    }
                    //generate ID for employee
                    employees = acc.getListOfEmployees();
                    String[] IDs = new String[employees.length];
                    for(int i = 0; i < IDs.length; i++){
                        IDs[i] = employees[i].getSearchKey();
                    }
                    String ID = Validation.generateID("E", IDs);
                    acc.addEmployee(ID, name, email, address, phone_num);
                    break;
                case 'b':
                    employees = acc.getListOfEmployees();
                    System.out.println("ID, name, email, address, phone number");
                    for(int i = 0; i < employees.length; i++){
                        System.out.println(employees[i].lineRepresentation());
                    }
                    break;
                case 'c':
                    employees = acc.getListOfEmployees(); //list of employees before modification
                    System.out.print("Enter ID of the employee to be removed: ");
                    String target_ID = ascanner.nextLine();
                    boolean does_employee_exist = false;
                    for(int i = 0; i < employees.length; i++){
                        if (employees[i].getSearchKey().equals(target_ID)){
                            does_employee_exist = true;
                            break;
                        }
                    }
                    if(does_employee_exist){
                        acc.removeEmployee(target_ID);
                    }
                    else{
                        System.out.println("no employee has the given ID... Please try again");
                    }
                    break;
                case 'q':
                    acc.logout();
                    logged_in = false;
                    break;
                default:
                    System.out.println("incorrect input! you must select one of the following options:");
            }
        }
    }
}
