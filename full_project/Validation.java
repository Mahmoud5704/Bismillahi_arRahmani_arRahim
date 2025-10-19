
package full_project;
import java.util.regex.Pattern;

public class Validation {
    public static char parseChoice(String choice_str){
        if (choice_str.length() > 1)
            return ' '; //throw away val
        else
            return Character.toLowerCase(choice_str.charAt(0)); //converts it to lower case so that it is case insensitive
    }
    public static String generateID(String prefix, String[] IDList){
        while(true){
            int num_part = (int) ((Math.random() * 9000) + 1000); //from 1000 to 9999
            
            String ID = prefix + num_part;
            boolean already_exist = false;
            for(int i = 0; i < IDList.length; i++){
                if(IDList[i].equals(ID)){
                    already_exist = true;
                    break;
                }
            }
            if(!already_exist){
                return ID;
            }
        }
    }
    public static boolean verifyID(String ID){
        if(ID.length() != 5)
            return false;
        char firstChar = ID.charAt(0);
        if (!Character.isUpperCase(firstChar)){ //make sure it only returns true with upper case LETTERS
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
        if(email.length() > 100 || email.length() < 6) //max and min sizes for email
            return false;
//        boolean areTherePattern.matches("[0-9]{" + num_part.length() + "}", num_part);
        String regex = "^[a-zA-Z0-9._-]+[@][a-zA-Z0-9]+[a-zA-Z0-9._-]*[a-zA-Z0-9]+[.][a-zA-Z]+$";
        boolean result = Pattern.matches(regex, email);
        return result;
//        return email.matches(regex);
//        return true;
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
