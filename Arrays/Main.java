import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        //1.Write a Java program to test if the first and the last element of an array of integers are same.
        //The length of the array must be greater than or equal to 2 Test Data:

        int[] numbers1 = {50,-20,0,30,40,60,10};

        if(numbers1.length>=2){
            if (numbers1[0] == numbers1[numbers1.length-1]){
                System.out.println("First and last numbers in the array are equal");
            }else {
                System.out.println("First and last numbers in the array are not equal");
            }
        }else {
            System.out.println("Sorry, the length of the array must be greater than or equal to 2");
        }

        //2.Write a Java program to find the numbers greater than the average of the numbers
        //of a given array. Original Array: [1, 4, 17, 7, 25, 3, 100]

        int[] numbers2 = {1,4,17,7,25,3,100};
        ArrayList greaterThanAvg = new ArrayList();
        int sum = 0;
        int avg = 0;
        for (int num : numbers2){
            sum += num;
        }
        avg = sum/numbers2.length;
        for (int num : numbers2){
            if(num>avg)
                greaterThanAvg.add(num);
        }
        System.out.println("The average of the array is : " + avg);
        System.out.println("The numbers in the said array that are greater than the average are: " + greaterThanAvg);

        //3.Write a Java program to get the larger value between first and last element of an array of integers.
        //Original Array: [20, 30, 40]

        ArrayList<Integer> numbers3 = new ArrayList<Integer>();
        numbers3.add(20);
        numbers3.add(30);
        numbers3.add(40);

        if(numbers3.getFirst() > numbers3.getLast()){
            System.out.println("Larger value between first and last element: " + numbers3.getFirst());
        }else {
            System.out.println("Larger value between first and last element: " + numbers3.getLast());
        }

        //4.Write a Java program to swap the first and last elements of an array and create a new array.
        //Original Array: [20, 30, 40]

        int[] numbers4 = {20,30,40};
        ArrayList<Integer> numbers5= new ArrayList<Integer>();
        for (int i = 0; i < numbers4.length; i++) {
            if(i==0){
                numbers5.add(numbers4[numbers4.length-1]);
            } else if (i==numbers4.length-1) {
                numbers5.add(numbers4[0]);
            } else {
                numbers5.add(numbers4[i]);
            }
        }
        System.out.println(numbers5);

        //5. Write a program that places the odd elements of an array before the even elements.
        //Original Array: [2,3,40,1,5,9,4,10,7]

        int[] numbers6 = {2,3,40,1,5,9,4,10,7};
        ArrayList<Integer> numbers7 = new ArrayList<Integer>();

        for (int num : numbers6){
            if (num%2==1)
                numbers7.add(num);
        }
        for (int num : numbers6){
            if (num%2==0)
                numbers7.add(num);
        }
        System.out.println(numbers7);

        //6. Write a program that test the equality of two arrays.
        //[2,3,6,6,4] [2,3,6,6,4]

        int[] numbers8 = {2,3,6,6,4};
        int[] numbers9 = {2,3,6,6,4};
        boolean equality = true;

        for (int i = 0; i < numbers8.length; i++) {
            if(numbers8[i]!=numbers9[i])
                equality = false;
        }

        System.out.println("Result : " + equality);

    }
}