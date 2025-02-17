import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner data = new Scanner(System.in);
        int sumEven = 0;
        int sumOdd = 0;
        int input = 0;
        ArrayList<Integer> even = new ArrayList<Integer>();
        ArrayList<Integer> odd = new ArrayList<Integer>();

        while (true){
            System.out.println("Enter a number or 0 to finish");
            input = data.nextInt();
            if (input!=0){
                if (input%2==0){
                    even.add(input);
                    sumEven += input;
                }else {
                    odd.add(input);
                    sumOdd += input;
                }
            }else {
                break;
            }
        }

        System.out.println("The even array is : " + even);
        System.out.println("The sum of even array is : " + sumEven);
        System.out.println("The number of elements in the even array is : " + even.size() +"\n");
        System.out.println("The odd array is : " + odd);
        System.out.println("The sum of odd array is : " + sumOdd);
        System.out.println("The number of elements in the odd array is : " + odd.size());
    }
}