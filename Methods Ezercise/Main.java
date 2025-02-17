import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner data = new Scanner(System.in);

        //1 - Write a Java method to find the smallest number among three numbers.

        System.out.print("Enter first number : ");
        int num1 = data.nextInt();;
        System.out.print("Enter second number : ");
        int num2 = data.nextInt();;
        System.out.print("Enter third number : ");
        int num3 = data.nextInt();;
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        numbers.add(num1);
        numbers.add(num2);
        numbers.add(num3);

        System.out.println("The smallest value is " + smallestNumber(numbers));

        //2 - Write a Java method that check if the entered number is negative or positive or zero.

        System.out.print("Enter a number : ");
        int number = data.nextInt();

        checkNumber(number);

        //3 - Write a Java method to check whether a string is a valid password.
        //Password rules:
        //A password must have at least ten characters.
        //A password consists of only letters and digits.
        //A password must contain at least two digits.

        data.nextLine();
        System.out.print("Input a password (You are agreeing to the above Terms and Conditions.): ");
        String password = data.nextLine();
        checkPassword(password);

    }

    public static int smallestNumber(ArrayList<Integer> numbers){
        int smallest = numbers.getFirst();

        for (int number : numbers){
            if(number<smallest){
                smallest = number;
            }
        }
        return smallest;
    }

    public static void checkNumber(int number){
        if (number>0){
            System.out.println("Number is positive");
        } else if (number==0) {
            System.out.println("Number is zero");
        } else {
            System.out.println("Number is negative");
        }
    }

    public static void checkPassword(String password){
        int digitCount = 0;
        for (int i = 0; i < password.length(); i++) {
                if(Character.isDigit(password.charAt(i))){
                    digitCount++;
            }
        }
        if(password.length()>=10 && password.matches("[0-9a-zA-Z]+") && digitCount>=2){
            System.out.println("Your password is valid");
        } else {
            System.out.println("You password is not valid");
        }
    }

}