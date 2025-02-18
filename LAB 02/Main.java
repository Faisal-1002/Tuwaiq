import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner data = new Scanner(System.in);

        //1.Write a program to find all the longest word in a given dictionary.

        String[] words = {"cat","dog","red","is","am"};
        int max = words[0].length();
        for(String word : words){
            if(word.length()>max){
                max = word.length();
            }
        }
        for (String word : words){
            if(word.length()==max)
                System.out.print(word + ", ");
        }
        System.out.println();

        //2. Write a program that displays the number of occurrences of an element in the array.

        ArrayList<Integer> number = new ArrayList<>();
        number.add(3);
        number.add(3);
        number.add(4);
        number.add(4);
        number.add(5);

        while(true){
            System.out.println("Enter a number to see how many it occurs in the array or o to exit");
            int num = data.nextInt();
            if (num == 0)
                break;
            int sum = 0;
            for (int num1 : number){
                if(num1 == num){
                    sum++;
                }
            }
            System.out.println(num + " occurs " + sum + " times");
        }

        //3.Write a program to find the k largest elements in a given array.
        //Elements in the array can be in any order.

        ArrayList<Integer> numbers = new ArrayList<Integer>();
        numbers.add(1);
        numbers.add(4);
        numbers.add(17);
        numbers.add(7);
        numbers.add(25);
        numbers.add(3);
        numbers.add(100);

        System.out.println("Enter the k largest numbers");
        int k = data.nextInt();

        for (int i = 0; i < k; i++) {
            int max1 = 0;
            for (int j = 0; j < numbers.size() -1 ; j++) {
                if(numbers.get(j+1)>numbers.get(j)){
                    max1 = numbers.get(j+1);
                }
            }
            System.out.print(max1 + " ");
            numbers.removeAll(Arrays.asList(max1));
        }

        //4. Create a method to reverse an array of integers.
        //Implement the method without creating a new array.

        System.out.println();
        int[] arrayOfIntegers = {5,4,3,2,1};
        System.out.println("The array after reverse: " + Arrays.toString(reverse(arrayOfIntegers)));

        //5. Write a menu-driven Java program with following option:

        int[] array ={0};
        while (true){
            System.out.println("Enter a number to select an option: ");
            System.out.println("1. Accept element of an array");
            System.out.println("2. Display elements of an array");
            System.out.println("3. Search the element within array");
            System.out.println("4. Sort the array");
            System.out.println("5. To Stop");
            int input = data.nextInt();
            if(input==1){
                System.out.println("Enter the size of the array");
                int size = data.nextInt();
                array = new int[size];
                System.out.println("Enter the elements of the array");
                for (int i = 0; i < size; i++) {
                    array[i] = data.nextInt();
                }
            }
            if(input==2){
                for (int num : array){
                    System.out.print(num + " ");
                }
                System.out.println();
            }
            if(input==3){
                System.out.println("Enter the elemnt you want to search");
                int temp = data.nextInt();
                boolean found = false;
                for(int num : array){
                    if(num == temp){
                        found = true;
                        break;
                    }
                }
                if(found){
                    System.out.println(temp + " is found");
                } else System.out.println(temp + " is not found");
            }
            if (input == 4) {
                for (int i = 0; i < array.length - 1; i++) {
                    for (int j = 0; j < array.length - i - 1; j++) {
                        if (array[j] > array[j + 1]) {
                            int temp = array[j];
                            array[j] = array[j + 1];
                            array[j + 1] = temp;
                        }
                    }
                }
                System.out.println("Array is sorted");
            }
            if(input==5){
                break;
            }

        }

        //6. Create a method that generates a random number within a given range.
        //Allow the user to specify the range and call the method to display random numbers.
        //Hint: use Random class

        System.out.print("Enter the minimum value of the range: ");
        int theMin = data.nextInt();
        System.out.print("Enter the maximum value of the range: ");
        int theMax = data.nextInt();
        System.out.print("Enter the number of random numbers to generate: ");
        int amount = data.nextInt();

        randomNumber(theMin,theMax,amount);

        //7. Write a program that checks the strength of a password.
        //Create a method that evaluates a password based on criteria like length,
        //inclusion of special characters, and uppercase/lowercase letters.

        System.out.println();
        data.nextLine();
        System.out.println("Enter a password");
        String password = data.nextLine();
        int score = checkLength(password) + checkSpecialCharacters(password) + checkUpperCaseLowerCase(password);
        System.out.println("your score : " + score);
        if (score>=8){
            System.out.println("Your password is strong");
        } else if (score>=5 && score<8) {
            System.out.println("Your password is moderate");
        } else if (score<5) {
            System.out.println("Your password is weak");
        }

        //8. Create a method that generates the Fibonacci sequence up to a specified number of terms.

        System.out.println("how many terms of Fibonacci you want to print?");
        int terms = data.nextInt();

        fibonacci(terms);



    }

    public static int[] reverse(int[] numbers){
        int size = numbers.length;
        for (int i = 0; i < size/2; i++) {
            int temp = numbers[i];
            numbers[i] = numbers[size-i-1];
            numbers[size-i-1] = temp;
        }
        return numbers;
    }

    public static void randomNumber(int min, int max, int amount){
        Random random = new Random();
        for (int i = 0; i < amount; i++) {
            System.out.print(random.nextInt(min,max) + " ");
        }
    }

    public static int checkLength(String password){
        if(password.length()>=8){
            return 3;
        } else if (password.length()>=6 && password.length()<=7){
            return 2;
        } else {
            return 0;
        }
    }

    public static int checkSpecialCharacters(String password){
        String special = ".*[!@#$%^&*(),.?\":{}|<>].*";
        if (password.matches(special)) {
            return 2;
        } else {
            return 0;
        }
    }

    public static int checkUpperCaseLowerCase(String password){
        if (password.matches(".*[a-z].*") && password.matches(".*[A-Z].*")) {
            return 3;
        } else {
            return 0;
        }

    }

    public static void fibonacci(int terms){
        int firstTerm = 0;
        int secondTerm = 1;

        if (terms == 0){
            System.out.println("Nothing");
        } else if (terms == 1) {
            System.out.println(firstTerm);
        } else if (terms==2) {
            System.out.print(firstTerm + " ");
            System.out.print(secondTerm + " ");
        } else {
            System.out.print(firstTerm + " ");
            System.out.print(secondTerm + " ");
            for (int i = 2; i < terms ; i++) {

                System.out.print((firstTerm+secondTerm) + " ");
                int temp = secondTerm;
                secondTerm += firstTerm;
                firstTerm = temp;
            }
        }
    }
}