import java.util.Scanner;
import java.lang.Math;

public class Main {
    public static void main(String[] args) {

        Scanner data = new Scanner(System.in);

        //1. Write a Java program to print the sum (addition), multiply, subtract, divide and remainder of two numbers , takes two numbers as input
        //Test Data:
        //Input first number: 125
        //Input second number: 24 Expected Output :
        //125 + 24 = 149
        //125 - 24 = 101
        //125 x 24 = 3000
        //125 / 24 = 5
        //125 mod 24 = 5

        System.out.println("Enter first number");
        int num1 = data.nextInt();
        System.out.println("Enter second number");
        int num2 = data.nextInt();

        System.out.println(num1 + " + " + num2 + " = " + (num1+num2));
        System.out.println(num1 + " - " + num2 + " = " + (num1-num2));
        System.out.println(num1 + " x " + num2 + " = " + (num1*num2));
        System.out.println(num1 + " / " + num2 + " = " + (num1/num2));
        System.out.println(num1 + " mod " + num2 + " = " + (num1%num2));

        //2. Write a Java program that takes a number as input and prints its multiplication table up to 10.
        //Test Data:
        //Input a number: 8 Expected Output :
        //8 x 1 = 8
        //8 x 2 = 16
        //8 x 3 = 24
        //...
        //8 x 10 = 80

        System.out.println("Enter a number");
        int number = data.nextInt();

        for (int i = 1; i <= 10; i++) {
            System.out.println(number + " * " + i + " = " + (number*i));
        }

        //3. Write a Java program to print the area and perimeter of a circle.

        System.out.println("Enter the radius of the circle");
        double radius = data.nextDouble();

        System.out.println("Perimeter is = " + (radius*2*Math.PI));
        System.out.println("Area is = " + (radius*radius*Math.PI));

        //4. Java program to find out the average of a set of integers

        System.out.println("Enter the count of integer numbers: ");
        int count = data.nextInt();
        int sum = 0;
        for (int i = 0; i < count; i++) {
            System.out.println("Enter an integer");
            sum += data.nextInt();
        }

        System.out.println("The average is = " + (sum/count));

        //5. Write a Java program that accepts three integers as input, adds the first two integers together,
        //and then determines whether the sum is equal to the third integer.

        System.out.println("Enter the first integer:");
        int firstInteger = data.nextInt();
        System.out.println("Enter the second integer:");
        int secondInteger = data.nextInt();
        System.out.println("Enter the third integer:");
        int thirdInteger = data.nextInt();

        if((firstInteger+secondInteger) == thirdInteger){
            System.out.println("The result is : true");
        }else {
            System.out.println("The result is : false");
        }

        //6. Write a Java program to reverse a word.
        data.nextLine();
        System.out.println("Enter a string to be reversed");
        String word = data.nextLine();
        String reversedWord = "";
        int length = word.length();

        for (int i = 0; i < length; i++) {
            reversedWord += word.charAt(length-i-1);
        }
        System.out.println(reversedWord);

        //7 - Java program to check whether the given number is even or odd

        System.out.println("Enter a number");
        int inputNumber = data.nextInt();

        if (inputNumber%2==0){
            System.out.println("The number is even");
        }else {
            System.out.println("The number is odd");
        }

        //8 - Java program to convert the temperature in Centigrade to Fahrenheit

        System.out.println("Enter temperature in Centigrade:");
        double temp = data.nextDouble();

        System.out.println("Temperature in Fahrenheit is: " + (temp*1.8+32.0));

        //9.Write a Java program that takes a string and a number from the user,
        //then prints the character in the given index.
        data.nextLine();
        System.out.println("Enter a string:");
        String sentence = data.nextLine();
        System.out.println("Enter the index:");
        int index = data.nextInt();

        if (index>=0 && index<sentence.length()){
            System.out.println("the char at the index you entered is: " + sentence.charAt(index));
        }else {
            System.out.println("Your index is out of range");
        }

        //10. Write a Java program to print the area and perimeter of a rectangle.

        System.out.println("Enter the width of the rectangle");
        double width = data.nextDouble();
        System.out.println("Enter the Height of the rectangle");
        double height = data.nextDouble();

        System.out.println("Area is " + width + " * " + height + " = " + width*height);
        System.out.println("Perimeter is 2 * (" + width + " + " + height + ") = " + 2*(width+height));


        //11. Write a Java program to compare two numbers.

        System.out.println("Enter first Integer");
        int firstNumber = data.nextInt();
        System.out.println("Enter second Integer");
        int secondNumber = data.nextInt();

        if (firstNumber==secondNumber){
            System.out.println(firstNumber + "==" + secondNumber);
        }else {
            System.out.println(firstNumber + "!=" + secondNumber);
        }

        if (firstNumber>secondNumber){
            System.out.println(firstNumber + ">" + secondNumber);
        }else {
            System.out.println(firstNumber + "<=" + secondNumber);
        }

        if (firstNumber<secondNumber){
            System.out.println(firstNumber + "<" + secondNumber);
        }else {
            System.out.println(firstNumber + ">=" + secondNumber);
        }

        //12. Write a Java program to convert seconds to hours, minutes and seconds.

        System.out.println("Enter the seconds to be converted");
        int input = data.nextInt();

        int hours = input/3600;
        int remainder = input%3600;
        int min = remainder/60;
        int sec = remainder%60;
        System.out.println("Result: " + hours + ":" + min + ":" + sec);

        //13. Write a Java program that accepts four integers from the user and
        //prints equal if all four are equal, and not equal otherwise.

        System.out.println("Enter first number");
        int number1 = data.nextInt();
        System.out.println("Enter second number");
        int number2 = data.nextInt();
        System.out.println("Enter third number");
        int number3 = data.nextInt();
        System.out.println("Enter fourth number");
        int number4 = data.nextInt();

        if (number1==number2 && number2==number3 && number3==number4){
            System.out.println("They are all equal");
        }else {
            System.out.println("They are not equal!!!");
        }

        //14. Write a Java program that reads an integer
        //and check whether it is negative, zero, or positive.

        System.out.println("Enter a number");
        int testNumber = data.nextInt();

        if (testNumber>0){
            System.out.println("Number is positive");
        } else if (testNumber==0) {
            System.out.println("Number is zero !!");
        } else {
            System.out.println("Number is negative");
        }

        //15.Write a program to enter the numbers till the user wants and at the end
        //it should display the count of positive, negative and zeros entered
        //(End loop use -1 , Don’t count -1).

        int positive = 0;
        int negative = 0;
        int zero = 0;
        int j = 0;

        while (j!=-1){
            System.out.println("Enter a number or -1 to exit");
            j = data.nextInt();
            if (j>0){
                positive++;
            } else if (j==0) {
                zero++;
            } else if (j<-1) {
                negative++;
            }
        }

        System.out.println("Positive : " + positive);
        System.out.println("Zero : " + zero);
        System.out.println("Negative : " + negative);


        //16 - Write a program that prompts the user to input an integer and
        //then outputs the number with the digits reversed.

        System.out.println("Enter an integer to be reversed");
        int integerToBeReversed = data.nextInt();
        String intgerToString = "" + integerToBeReversed;
        String reversedInteger = "";

        for (int i = 0; i < intgerToString.length(); i++) {
            reversedInteger += intgerToString.charAt(intgerToString.length()-i-1);
        }
        System.out.println(reversedInteger);

        //17 - Write a program to enter the numbers till the user wants and
        //at the end the program should display the largest and smallest numbers entered.

        int largest = 0;
        int lowest = 0;
        boolean firstIrritation = true;

        while (true){
            System.out.println("Enter a number or zero to exit");
            int num = data.nextInt();
            if(firstIrritation){
                largest = num;
                lowest = num;
                firstIrritation = false;
            }
            if(num == 0){
                break;
            } else if (num>largest) {
                largest = num;
            } else if (num<lowest) {
                lowest = num;
            }
        }
        System.out.println("Largest : " + largest);
        System.out.println("Lowest : " + lowest);

        //18 - Determine and print the number of times
        //the character ‘a’ appears in the input entered by the user.
        data.nextLine();
        System.out.println("Enter a string to determine how many a's it has:");
        String inputString = data.nextLine();
        int counter = 0;

        for (int i = 0; i < inputString.length(); i++) {
            if(inputString.charAt(i) == 'a'){
                counter++;
            }
        }
        System.out.println("Number of a's : " + counter);
    }
}