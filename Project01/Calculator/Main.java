import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner data = new Scanner(System.in);
        ArrayList<Double> resultHistory = new ArrayList<>();

        while (true) {
            try {
                displayMenu();
                int choice = getUserChoice(data);

                if (choice == 0) {
                    System.out.println("Exiting Calculator. Goodbye!");
                    break;
                }

                if (choice >= 1 && choice <= 8) {
                    double num1 = getValidNumber(data, "Enter first number: ");
                    double num2 = getValidNumber(data, "Enter second number: ");
                    performOperation(choice, num1, num2, resultHistory);
                } else if (choice == 9) {
                    System.out.println("Last Calculation Result: " +
                            (resultHistory.isEmpty() ? "No calculations performed yet!" :
                                    resultHistory.getLast()));
                } else if (choice == 10) {
                    System.out.println("All Calculation Results: " + resultHistory);
                } else {
                    System.out.println("Invalid choice! Please enter a valid option.");
                }
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
                System.out.println("Restarting calculator...");
                data.nextLine();
            }
        }
    }
    
    public static void displayMenu() {
        System.out.println("\n========== Calculator Menu ==========");
        System.out.println("Enter 1 to Add the numbers");
        System.out.println("Enter 2 to Subtract the numbers");
        System.out.println("Enter 3 to Multiply the numbers");
        System.out.println("Enter 4 to Divide the numbers");
        System.out.println("Enter 5 to Find Modulus of numbers");
        System.out.println("Enter 6 to Find Minimum number");
        System.out.println("Enter 7 to Find Maximum number");
        System.out.println("Enter 8 to Find Average of numbers");
        System.out.println("Enter 9 to Print last result");
        System.out.println("Enter 10 to Print all past results");
        System.out.println("Enter 0 to Exit");
        System.out.println("=====================================");
        System.out.print("Choose an option: ");
    }

    public static int getUserChoice(Scanner data) {
        while (true) {
            try {
                return data.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number between 0-10.");
                data.next();
                System.out.print("Choose an option: ");
            }
        }
    }

    public static double getValidNumber(Scanner data, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return data.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                data.next();
            }
        }
    }

    public static void performOperation(int choice, double num1, double num2, ArrayList<Double> resultHistory) {
        double result = 0;
        switch (choice) {
            case 1:
                result = add(num1, num2);
                break;
            case 2:
                result = subtract(num1, num2);
                break;
            case 3:
                result = multiply(num1, num2);
                break;
            case 4:
                result = divide(num1, num2);
                break;
            case 5:
                result = modulus(num1, num2);
                break;
            case 6:
                result = min(num1, num2);
                break;
            case 7:
                result = max(num1, num2);
                break;
            case 8:
                result = average(num1, num2);
                break;
            default:
                System.out.println("Invalid operation choice.");
                return;
        }
        resultHistory.add(result);
        System.out.println("Result: " + result);
    }

    public static double add(double a, double b) {
        return a + b;
    }

    public static double subtract(double a, double b) {
        return a - b;
    }

    public static double multiply(double a, double b) {
        return a * b;
    }

    public static double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero!");
        }
        return a / b;
    }

    public static double modulus(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot modulus by zero!");
        }
        return a % b;
    }

    public static double min(double a, double b) {
        return Math.min(a, b);
    }

    public static double max(double a, double b) {
        return Math.max(a, b);
    }

    public static double average(double a, double b) {
        return (a + b) / 2;
    }
}
