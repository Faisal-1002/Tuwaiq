import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner data = new Scanner(System.in);
        List<Route> routes = new ArrayList<>();
        List<Car> cars = new ArrayList<>();
        List<Passenger> passengers = new ArrayList<>();
        System.out.println("Car Pooling System");

        try {
            // Takes routes info from the user
            while (true) {
                System.out.println("\nPlease enter 1 to add a route details or 0 to exit!");
                int choice = data.nextInt();
                if (choice != 0) {
                    System.out.println("Enter start address");
                    String startAddress = data.next();
                    System.out.println("Enter destination address");
                    String destinationAddress = data.next();
                    System.out.println("Enter trip price");
                    double tripPrice = data.nextDouble();
                    routes.add(new Route(startAddress, destinationAddress, tripPrice));
                } else {
                    break;
                }
            }

            // Takes car info from the user
            while (true) {
                System.out.println("\nPlease enter 1 to add a car details or 0 to exit!");
                int choice = data.nextInt();
                if (choice != 0) {
                    System.out.println("Enter car code");
                    String carCode = data.next();
                    System.out.println("Choose one route of the following: (expected input is 1,2,3 ... etc)");
                    for (int i = 0; i < routes.size(); i++) {
                        System.out.println((i + 1) + ". " + routes.get(i));
                    }
                    int chosenRoute = data.nextInt();
                    if (chosenRoute < 1 || chosenRoute > routes.size()) {
                        System.out.println("Invalid route selection. Please try again.");
                        continue;
                    }
                    System.out.println("Enter max passengers number");
                    int maxPassenger = data.nextInt();
                    cars.add(new Car(carCode, routes.get(chosenRoute - 1), maxPassenger));
                } else {
                    break;
                }
            }

            // Takes passengers info from the user
            while (true) {
                System.out.println("\nPlease enter 1 to add a passenger details or 0 to exit!");
                int choice = data.nextInt();
                if (choice != 0) {
                    System.out.println("Passenger name");
                    String passengerName = data.next();
                    System.out.println("Enter passenger ID");
                    String passengerId = data.next();
                    System.out.println("Is the passenger subscriber? (true or false)");
                    boolean isSubscriber = data.nextBoolean();
                    if (isSubscriber) {
                        passengers.add(new Subscribers(passengerName, passengerId));
                    } else {
                        System.out.println("Does the passenger have a coupon? (true or false)");
                        boolean hasCoupon = data.nextBoolean();
                        passengers.add(new NonSubscribers(passengerName, passengerId, hasCoupon));
                    }
                } else {
                    break;
                }
            }

            // The user reserves a car for a passenger
            while (true) {
                System.out.println("\nPlease enter 1 to reserve a car for a passenger or 0 to exit!");
                int choice = data.nextInt();
                if (choice != 0) {
                    System.out.println("Choose one car of the following: (expected input is 1,2,3 ... etc)");
                    for (int i = 0; i < cars.size(); i++) {
                        System.out.println((i + 1) + ". " + cars.get(i));
                    }
                    int chosenCar = data.nextInt();
                    if (chosenCar < 1 || chosenCar > cars.size()) {
                        System.out.println("Invalid car selection. Please try again.");
                        continue;
                    }
                    System.out.println("Choose one passenger of the following: (expected input is 1,2,3 ... etc)");
                    for (int i = 0; i < passengers.size(); i++) {
                        System.out.println((i + 1) + ". " + passengers.get(i));
                    }
                    int chosenPassenger = data.nextInt();
                    if (chosenPassenger < 1 || chosenPassenger > passengers.size()) {
                        System.out.println("Invalid passenger selection. Please try again.");
                        continue;
                    }
                    checkReserve(cars.get(chosenCar - 1), passengers.get(chosenPassenger - 1));
                } else {
                    break;
                }
            }

            // Passengers info
            System.out.println("\nFinal Passengers Information:");
            for (Passenger passenger : passengers) {
                System.out.println(passenger);
            }

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter the correct data type (e.g., numbers for choices, true/false for boolean).");
        } catch (NullPointerException e) {
            System.out.println("A null value was encountered. Please check your inputs and try again.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        } finally {
            data.close();
        }
    }

    public static void checkReserve(Car car, Passenger passenger) {
        if (!car.getCarPassenger().contains(passenger)) {
            passenger.reserve(car);
        } else {
            System.out.println("Sorry, this passenger is already added.");
        }
    }
}