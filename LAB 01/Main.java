import java.util.Scanner;
import java.lang.Math;

public class Main {
    public static void main(String[] args) {

        Scanner data = new Scanner(System.in);

        //1. Write a Java program to print the sum (addition), multiply, subtract, divide and remainder of two numbers , takes two numbers as input

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

        System.out.println("Enter a number");
        int number = data.nextInt();

        for (int i = 1; i <= 10; i++) {
            System.out.println(number + " * " + i + " = " + (number*i));
        }

        //3. Write a Java program to print the area and perimeter of a circle.

        System.out.println("Enter the radius of the circle");
        double radius = data.nextDouble();

        //here the math.pi is used to accurate answers
        System.out.println("Perimeter is = " + (radius*2*Math.PI));
        System.out.println("Area is = " + (radius*radius*Math.PI));

        //4. Java program to find out the average of a set of integers

        System.out.println("Enter the count of integer numbers: ");
        int count = data.nextInt();
        int sum = 0;
        //this for loop will sum all the numbers
        for (int i = 0; i < count; i++) {
            System.out.println("Enter an integer");
            sum += data.nextInt();
        }
        //average is count/total
        System.out.println("The average is = " + (sum/count));

        //5. Write a Java program that accepts three integers as input, adds the first two integers together,
        //and then determines whether the sum is equal to the third integer.

        System.out.println("Enter the first integer:");
        int firstInteger = data.nextInt();
        System.out.println("Enter the second integer:");
        int secondInteger = data.nextInt();
        System.out.println("Enter the third integer:");
        int thirdInteger = data.nextInt();

        //the logic here is sum the first two numbers and then compare it to the third one
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

        //reversedWord will add the last charsets form the String the used entered
        for (int i = 0; i < length; i++) {
            reversedWord += word.charAt(length-i-1);
        }
        System.out.println(reversedWord);

        //7 - Java program to check whether the given number is even or odd

        System.out.println("Enter a number");
        int inputNumber = data.nextInt();

        //if the remainder of the division is 0 even otherwise odd
        if (inputNumber%2==0){
            System.out.println("The number is even");
        }else {
            System.out.println("The number is odd");
        }

        //8 - Java program to convert the temperature in Centigrade to Fahrenheit

        System.out.println("Enter temperature in Centigrade:");
        double temp = data.nextDouble();

        //to conver C to F the equation is C * (9/5) + 32 = F
        System.out.println("Temperature in Fahrenheit is: " + (temp*1.8+32.0));

        //9.Write a Java program that takes a string and a number from the user,
        //then prints the character in the given index.

        data.nextLine();
        System.out.println("Enter a string:");
        String sentence = data.nextLine();
        System.out.println("Enter the index:");
        int index = data.nextInt();

        //taking the index from the user and put it in charAt as an argument
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

        //Area and perimeter laws of rectangle
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

        //division by 3600 to get the hours
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

        //comparing all the numbers here
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

        //j is not -1 so the loop will run and based on the user input the program will close
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

        //convert integer to string
        String intgerToString = "" + integerToBeReversed;
        String reversedInteger = "";

        //reversedInteger will be filled with last digit of integer the user already entered
        for (int i = 0; i < intgerToString.length(); i++) {
            reversedInteger += intgerToString.charAt(intgerToString.length()-i-1);
        }
        System.out.println(reversedInteger);

        //17 - Write a program to enter the numbers till the user wants and
        //at the end the program should display the largest and smallest numbers entered.

        int largest = 0;
        int lowest = 0;
        //this boolean used for if condition in the loop
        boolean firstIrritation = true;

        while (true){
            System.out.println("Enter a number or write (exit) to end the program");
            int num = 0;

            //this if condition will check the user input whether it is integer or sting
            if(data.hasNextInt()){
                num = data.nextInt();
            }else if(data.nextLine().equalsIgnoreCase("exit")){
                break;
            }

            //this if condition runs just once to take the first input of the user and make the largest and the lowest number
            if(firstIrritation){
                largest = num;
                lowest = num;
                firstIrritation = false;
            }
            if (num>largest) {
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

        //this for loop will check each chat in the input sting and compare to 'a'
        for (int i = 0; i < inputString.length(); i++) {
            if(inputString.charAt(i) == 'a'){
                counter++;
            }
        }
        System.out.println("Number of a's : " + counter);
    }
}