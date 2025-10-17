/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6;

/**
 *
 * @author zeyad
 */
public class Validation {
//    public static boolean verifyID(String ID){
//        if(ID.length() <= 1)
//            return false;
//        //make sure the ID contains letters and numbers only!
//        return true;
//    }
    public static boolean verifyName(String name){
        return true; //verify name
    }
    public static boolean verifyEmail(String email){
        return true; //verify email
    }
    public static boolean verifyPhoneNum(String num){
        try{
            int num_test = Integer.parseInt(num);
            if(num.length() == 11){
                return true;
            }
            else{
                return false;
            }
        }
        catch(Exception e){
            return false;
        }
    }
}
