package ques1;
import java.util.Scanner;

public class Lab_2_p1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("enter 3 positive integers separated by ' - ' :  ");
        String input = scanner.nextLine();
        String[] parts = input.trim().split("-");
        if(parts.length>3){
                            System.out.println("must be three numbers only!");
                            return;
        }
        try {
            int a = Integer.parseInt(parts[0].trim());
            int b = Integer.parseInt(parts[1].trim());
            int c = Integer.parseInt(parts[2].trim());
            if (a > 0 && b > 0 && c > 0) {
                if (a == b && b == c) {
                    System.out.println("all equal");
                } else if (a == b || b == c || a == c) {
                    System.out.println("neither");
                } else {
                    System.out.println("all different");
                }
            }
            else
                System.out.println("all inputs must greater than zero");
            
        } catch (MatchException | NumberFormatException e  ) {
            System.out.println("invalid inputs(check that all are +ev integer number only)");
        }
    }
}