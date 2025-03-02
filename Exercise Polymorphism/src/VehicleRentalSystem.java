import java.util.*;

public class VehicleRentalSystem {
    public static void main(String[] args) {
        Scanner data = new Scanner(System.in);
        List<Vehicle> rentedVehicles = new ArrayList<>();
        while (true){
            System.out.println("Vehicle Rental System\n" +
                    "Choose one option : \n" +
                    "1.Rent a Car\n" +
                    "2.Rent a Bike\n" +
                    "3.Rent a Truck\n" +
                    "4.View Rented Vehicles\n" +
                    "5.Exit");
            int option = data.nextInt();
            if (option == 1){
                System.out.println("Enter car model : ");
                String model = data.next();
                System.out.println("Enter rental days : ");
                double rentalDays = data.nextDouble();;
                rentedVehicles.add(new Car(model,rentalDays));
                rentedVehicles.getLast().displayDetails();
            }
            if (option == 2){
                System.out.println("Enter bike brand : ");
                String brand = data.next();
                System.out.println("Enter rental hours : ");
                double rentalHours = data.nextDouble();;
                rentedVehicles.add(new Bike(brand,rentalHours));
                rentedVehicles.getLast().displayDetails();
            }
            if (option == 3){
                System.out.println("Enter truck type : ");
                String type = data.next();
                System.out.println("Enter rental weeks : ");
                double rentalWeeks = data.nextDouble();;
                rentedVehicles.add(new Truck(type,rentalWeeks));
                rentedVehicles.getLast().displayDetails();
            }
            if (option == 4){
                System.out.println();
                for (Vehicle v : rentedVehicles){
                    v.displayDetails();
                    System.out.println();
                }
            }
            if (option == 5){
                System.out.println("Thank you for using the Vehicle Rental System!");
                break;
            }
            System.out.println();
        }
    }
}