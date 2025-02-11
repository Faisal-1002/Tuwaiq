import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Scanner data = new Scanner(System.in);

        //1.Write a program that checks the role of the user
        //If the role is admin print "welcome admin"
        //If the role is superuser print "welcome superuser"
        //If the role is user print "welcome user" (tip:
        //use if else)

        System.out.println("please enter your role");
        String role = data.nextLine();

        // This if statements are checking the user input (role) and print the proper message for it
        if(role.equals("admin")){
            System.out.println("welcome admin");
        } else if (role.equals("superuser")) {
            System.out.println("welcome superuser");
        }else if (role.equals("user")){
            System.out.println("welcome user");
        }else {
            System.out.println("your role is not found");
        }

        //2.Take three numbers from the user and print the greatest number.
        //        Test Data
        //Input the 1st number: 25
        //Input the 2nd number: 78
        //Input the 3rd number: 87
        //Expected Output : The greatest: 87

        System.out.println("please enter first number");
        int num1 = data.nextInt();
        System.out.println("please enter second number");
        int num2 = data.nextInt();
        System.out.println("please enter third number");
        int num3 = data.nextInt();

        // First if condition compare num1 with num2 and num3, second if condition compare num2 with num1 and num3, third if condition compare num3 with num1 and num2
        if(num1>num2 && num1>num3){
            System.out.println("Greatest: " + num1);
        } else if (num2>num1 && num2>num3) {
            System.out.println("Greatest: " + num2);
        }else if (num3>num1 && num3>num2) {
            System.out.println("Greatest: " + num3);
        } else {
            System.out.println("your input is wrong");
        }

        //3.Write a Java program that generates an integer between 1 and 7 and displays the name of the weekday.
        //        Test Data number: 4
        //Expected Output:
        //Wednesday

        //initiate random class
        Random random = new Random();
        //the argument of nextInt is the range of random numbers and since it start from 0 then we need to +1 to make it from 1 - 7
        int randomInt = random.nextInt(7) + 1;

        switch(randomInt){
            case 1:
                System.out.println("Sunday");
                break;
            case 2:
                System.out.println("Monday");
                break;
            case 3:
                System.out.println("Tuesday");
                break;
            case 4:
                System.out.println("Wednesday");
                break;
            case 5:
                System.out.println("Thursday");
                break;
            case 6:
                System.out.println("Friday");
                break;
            case 7:
                System.out.println("Saturday");
                break;
            default:
                System.out.println("the number is incorrect");
        }

        //4. Write a program that takes a numeric score as input and prints the corresponding letter grade using the following grading scale:
        //A: 90-100
        //B: 80-89
        //C: 70-79
        //D: 60-69 F: 0-59
        //Enter your numeric score: 85
        //Numeric Score: 85
        //Letter Grade: B

        System.out.println("Enter your numeric score: ");
        int score = data.nextInt();

        //each if condition checks the range of the score and print the corresponding grade to it. So 100-90 is A and 89-80 is B and so on
        if(score<=100 && score>=90){
            System.out.println("Numeric Score: " + score);
            System.out.println("Letter Grade: A");
        } else if (score<=89 && score>=80) {
            System.out.println("Numeric Score: " + score);
            System.out.println("Letter Grade: B");
        }else if (score<=79 && score>=70) {
            System.out.println("Numeric Score: " + score);
            System.out.println("Letter Grade: C");
        }else if (score<=69 && score>=60) {
            System.out.println("Numeric Score: " + score);
            System.out.println("Letter Grade: D");
        }else if (score<=59 && score>=0) {
            System.out.println("Numeric Score: " + score);
            System.out.println("Letter Grade: F");
        }else {
            System.out.println("your score is wrong");
        }

        //5. Write a Java program that takes a person's age as input and categorizes them into one of three age categories: "Child," "Teenager," or "Adult" using an if statement.
        //use an if statement to categorize the age based on the following criteria:
        //• If the age is less than 13, categorize them as a "Child."
        //• If the age is between 13 and 19 (inclusive), categorize them as a "Teenager."
        //• If the age is 20 or older, categorize them as an "Adult."
        //Sample Output:
        //Enter your age: 25
        //You are an Adult.

        System.out.println("Enter your age");
        int age = data.nextInt();

        //if condition here checks the age and print the proper message to it. so 0-12 is child, 13-19 teenager and 20+ is adult
        if(age>=0 && age<13){
            System.out.println("You are Child");
        } else if (age>=13 && age<=19) {
            System.out.println("You are Teenager");
        } else if (age>=20) {
            System.out.println("You are Adult");
        } else {
            System.out.println("Your input is wrong");
        }

    }
}