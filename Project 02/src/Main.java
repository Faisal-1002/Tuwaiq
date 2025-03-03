import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Car Pooling System\n");
        //Initialling 3 routes
        Route r1 = new Route("Dammam", "Riyadh", 500);
        Route r2 = new Route("Riyadh", "Jeddah", 1000);
        Route r3 = new Route("Makkah", "Qassim", 700);
        //Initialling 3 cars and assigning their routes
        Car c1 = new Car("111", r1, 2);
        Car c2 = new Car("222", r2, 1);
        Car c3 = new Car("333", r3, 0);
        //Array list of passengers
        List<Passenger> passengerList = new ArrayList<>();
        passengerList.add(new Subscribers("Faisal", "123"));
        passengerList.add(new NonSubscribers("Ali", "456",true));
        passengerList.add(new NonSubscribers("Fahad", "789",false));
        passengerList.add(new NonSubscribers("Salem", "000",false));
        //Reserve cars
        //I added a validation method where a passenger cannot reserve a car twice
        checkReserve(passengerList.get(0), c1);
        checkReserve(passengerList.get(1), c1);
        checkReserve(passengerList.get(1), c1);
        checkReserve(passengerList.get(2), c2);
        checkReserve(passengerList.get(3), c3);
        //Passengers info
        System.out.println("\nPassengers info:");
        for (Passenger passenger : passengerList){
            System.out.println(passenger);
        }
    }
    public static void checkReserve(Passenger passenger, Car car){
        if (!car.getCarPassenger().contains(passenger))
            passenger.reserve(car);
        else
            System.out.println("Sorry, this passenger is already added.");
    }
}