import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner data = new Scanner(System.in);

        //1.Write a program that prints the numbers from 1 to 100 such that:
        //If the number is a multiple of 3, you need to print "Fizz" instead of that number.
        //If the number is a multiple of 5, you need to print "Buzz" instead of that number.
        //If the number is a multiple of both 3 and 5, you need to print "FizzBuzz" instead of that number.

        for (int i = 1; i <=100 ; i++) {
            if (i%3==0 && i%5==0){
                System.out.println("FizzBuzz");
            } else if (i%3==0){
                System.out.println("Fizz");
            } else if (i%5==0) {
                System.out.println("Buzz");
            } else
                System.out.println(i);

        }

        //2.Write a Java program to reverse a string.
        //Test Data: Input a string: The quick brown fox Expected Output: Reverse string: xof nworb kciuq ehT
        // Get the input string from the user

        System.out.println("Input a string: ");
        String input = data.nextLine();
        String reversed = "";

        for (int i = input.length() - 1; i >= 0; i--) {
            reversed += input.charAt(i);
        }
        System.out.println("Reverse string: " + reversed);

        //3.Write a program to find the factorial value of any number entered through the keyboard.

        System.out.println("Enter a number to find its factorial: ");
        int number = data.nextInt();
        int factorial = 1;

        for (int i = 1; i <= number; i++) {
            factorial *= i;
        }
        System.out.println("Factorial of " + number + " is: " + factorial);

        //4.Two numbers are entered through the keyboard.
        // Write a program to find the value of one number raised to the power of another.
        // (Do not use Java built-in method)

        System.out.println("Enter the base number: ");
        int base = data.nextInt();
        System.out.println("Enter the exponent: ");
        int exponent = data.nextInt();
        int result = 1;

        for (int i = 1; i <= exponent; i++) {
            result *= base;
        }
        System.out.println(base + " raised to the power of " + exponent + " is: " + result);

        //5.Write a program that reads a set of integers, and then prints the sum of the even and odd integers.

        System.out.println("Enter the number of integers: ");
        int n = data.nextInt();
        int sumEven = 0;
        int sumOdd = 0;

        System.out.println("Enter the integers:");
        for (int i = 0; i < n; i++) {
            int num = data.nextInt();
            if (num % 2 == 0) {
                sumEven += num;
            } else {
                sumOdd += num;
            }
        }

        System.out.println("Sum of even integers: " + sumEven);
        System.out.println("Sum of odd integers: " + sumOdd);

        //6.Write a program that prompts the user to input a positive integer.
        //It should then output a message indicating whether the number is a prime number.

        System.out.println("Enter a positive integer: ");
        int num = data.nextInt();

        if (num <= 0) {
            System.out.println("Please enter a positive integer.");
        } else if (num == 1) {
            System.out.println(num + " is not a prime number.");
        } else {
            boolean isPrime = true;
            for (int i = 2; i <= Math.sqrt(num); i++) {
                if (num % i == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                System.out.println(num + " is a prime number.");
            } else {
                System.out.println(num + " is not a prime number.");
            }
        }

        //7.Use a for loop to print headings for four weeks (Weeks 1 - 4). Then use another for loop to print the days (Days 1 -7) for each week.
        //Expected Output:

        for (int week = 1; week <= 4; week++) {
            System.out.println("Week " + week);

            // Inner loop for days
            for (int day = 1; day <= 7; day++) {
                System.out.println("Day" + day);
            }
        }

        //8.Write a program that's check if the word is a palindrome or not.
        //hint: A string is said to be a palindrome if it is the same if we start reading it from left to right or right to left.

        data.nextLine();
        System.out.print("Enter a word: ");
        String word = data.nextLine();
        boolean isPalindrome = true;
        word = word.toLowerCase();

        for (int i = 0; i < word.length() / 2; i++) {
            if (word.charAt(i) != word.charAt(word.length() - 1 - i)) {
                isPalindrome = false;
                break;
            }
        }

        if (isPalindrome) {
            System.out.println(word + " is a palindrome.");
        } else {
            System.out.println(word + " is not a palindrome.");
        }

    }
}