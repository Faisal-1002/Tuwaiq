import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner data = new Scanner(System.in);

        //1. Write a Java program that accept three numbers from the user and print the largest number .

        System.out.print("Enter first number: ");
        int num1 = data.nextInt();
        System.out.print("Enter second number: ");
        int num2 = data.nextInt();
        System.out.print("Enter third number: ");
        int num3 = data.nextInt();

        if (num1>=num2 && num1>=num3)
            System.out.println("Largest : " + num1);
        else if (num2>=num1 && num2>=num3) {
            System.out.println("Largest : " + num2);
        } else if (num3>=num1 && num3>= num2) {
            System.out.println("Largest : " + num3);
        }
        data.nextLine();

        //2. Write a Java program that accept a String and a number from the user,then print the character in the given index .

        System.out.println("Enter a string : ");
        String word = data.nextLine();
        System.out.println("Enter an index : ");
        int index = data.nextInt();

        System.out.println("The character in the given index is : " + word.charAt(index));

        //3. Write a program to enter the numbers till the user wants and at the end it should display the sum entered .

        int sum = 0;
        while (true){
            System.out.println("Enter a number to be sum or 'exit' to see result");
            if (data.hasNextInt()){
                sum += data.nextInt();
            } else if (data.nextLine().equalsIgnoreCase("exit")) {
                break;
            }
        }
        System.out.println("Sum is : " + sum + "\n");

        //4. Write a Java program to find positive and negative numbers of a given array.

        int[] numbers = {10,-21,30,31,-25};

        for (int num : numbers){
            if (num>0){
                System.out.println(num + " is positive number");
            } else if (num<0) {
                System.out.println(num + " is a negative number");
            }
        }

        //5.Write a Java program to find  the shortest word of a given array.

        String[] words = {"Tuwaiq", "Bootcamp", "Student", "JAVA"};
        String smallest = words[0];
        for (int i = 0; i < words.length; i++) {
            if (smallest.length()>words[i].length()){
                smallest = words[i];
            }
        }
        System.out.println("\nshortest word is : " + smallest);
    }
}