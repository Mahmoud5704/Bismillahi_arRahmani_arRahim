package javaapplication7;


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
            String name = ""; //initialize empty strings to avoid compilation errors
            String email = "";
            String address = "";
            String phone_num = "";
            String ID = "";
            int counter = 1;
            boolean taking_input = false;
            switch(command){
                case 'a':
                    taking_input = true;
                    while(taking_input){
                        switch(counter){
                            case 1:
                                System.out.print("employee name: ");
                                name = ascanner.nextLine();
                                if(!Validation.verifyName(name)){
                                    System.out.println("Names must only contain letters!");
                                    continue;
                                }
                                counter++;
                            case 2:
                                System.out.print("employee email: ");
                                email = ascanner.nextLine();
                                if(!Validation.verifyEmail(email)){
                                    System.out.println("incorrect email format!");
                                    continue;
                                }
                                counter++;
                            case 3:
                                System.out.print("employee address: ");
                                address = ascanner.nextLine();
                                counter++;
                            case 4:
                                System.out.print("employee phone number: ");
                                phone_num = ascanner.nextLine();
                                if(!Validation.verifyNum(phone_num, 11)){
                                    System.out.println("phone number must contain 11 digits");
                                    continue;
                                }
                                counter++;
                            default: //all inputs are correct
                                ID = Validation.generateID("E");
                                acc.addEmployee(ID, name, email, address, phone_num);
                                taking_input = false;
                                counter = 1;
                        }
                    }
                    break;
                case 'b':
                    employees = acc.getListOfEmployees();
                    System.out.println("ID, name, email, address, phone number");
                    for(int i = 0; i < employees.length; i++){
                        System.out.println(employees[i].lineRepresentation());
                    }
                    break;
               
               case 'c':
                    taking_input = true;
                    while(taking_input){
                        System.out.print("Enter ID of the employee to be removed: ");
                        ID = ascanner.nextLine();
                        EmployeeUserDatabase db = new EmployeeUserDatabase(main.Employeefile);
            
                        if (db.contains(ID)) {
                             acc.removeEmployee(ID);
                             taking_input = false;
                        }
                        else {
                            System.out.println("no employee has the given ID... Please try again");
                        }
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
