
package full_project;
import java.util.regex.Pattern;

public class Validation {
    public static char parseChoice(String choice_str){
        if (choice_str.length() > 1)
            return ' '; //throw away val
        else
            return Character.toLowerCase(choice_str.charAt(0)); //converts it to lower case so that it is case insensitive
    }
    public static String generateID(String prefix) {
    EmployeeUserDatabase db = new EmployeeUserDatabase("Employee.txt");  
    db.readFromFile();  
    while (true) {
        int num_part = (int) ((Math.random() * 9000) + 1000);
        String id = prefix + num_part;

        boolean exists = db.contains(id);
        if (!exists) {
            return id;
        }
    }
}
    public static boolean verifyID(String ID){
        char firstChar = ID.charAt(0);
        if ( ID.length() != 5 || !Character.isUpperCase(firstChar)){ 
            return false;
        }
        String num_part = ID.substring(1);
        boolean result = Pattern.matches("[0-9]{" + num_part.length() + "}", num_part);
        return result;
    }
    public static boolean verifyName(String name){
        if(name.length() < 1)
            return false;
        boolean result = Pattern.matches("[a-zA-Z]+( [a-zA-Z]+)*", name); //make sure all the characters of name are between a and z 
        return result;
    }
    public static boolean verifyEmail(String email){
    if (email.length() > 100 || email.length() < 6)
        return false;

    String regex = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9]+[a-zA-Z0-9._-]*[a-zA-Z0-9]+\\.[a-zA-Z]+$";
    return Pattern.matches(regex, email);
}
    

    public static boolean verifyNum(String num, int length) 
    {
    if (num == null) 
        return false;
    if (num.length() != length) 
        return false;
    return num.matches("^01[2501]\\d{8}$");
    }

    }

