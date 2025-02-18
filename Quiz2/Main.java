import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner data = new Scanner(System.in);
        System.out.println("Enter the number of names you want: ");
        int size = data.nextInt();
        data.nextLine();
        ArrayList<String> names = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            System.out.println("Enter a name");
            names.add(data.nextLine());
        }

        System.out.println("Enter the max size of names you want to print: ");
        int max = data.nextInt();
        System.out.println("The array of names less than or equal to " + max + " letters is : " + arrayOfNames(names,max));

    }

    public static ArrayList<String> arrayOfNames(ArrayList<String> names, int max){
        ArrayList<String> newNames = new ArrayList<>();
        for (String name : names){
            if(name.length()<=max){
                newNames.add(name);
            }
        }
        return newNames;
    }

}