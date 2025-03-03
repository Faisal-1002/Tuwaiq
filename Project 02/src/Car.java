import java.util.ArrayList;
import java.util.List;

public class Car {

    private String code;
    private Route route;
    private int maxPassengers;
    private List<Passenger> carPassenger;

    public Car(){

    }

    public Car(String code, Route route, int maxPassengers) {
        this.code = code;
        this.route = route;
        this.maxPassengers = maxPassengers;
        carPassenger = new ArrayList<>();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public int getMaxPassengers() {
        return maxPassengers;
    }

    public void setMaxPassengers(int maxPassengers) {
        this.maxPassengers = maxPassengers;
    }

    public List<Passenger> getCarPassenger() {
        return carPassenger;
    }

    public void setCarPassenger(List<Passenger> carPassenger) {
        this.carPassenger = carPassenger;
    }

    public boolean addPassenger(Passenger passenger){
        try {
            if (this.carPassenger.size()<maxPassengers){
                this.carPassenger.add(passenger);
                System.out.println("Passenger has been added! The car has now " + (maxPassengers-carPassenger.size()) + " available seats");
                return true;
            } else
                throw new Exception("Sorry, the car is full of passengers");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }

    }

    @Override
    public String toString() {
        return "Car{" +
                "code='" + code + '\'' +
                ", route=" + route +
                ", maxPassengers=" + maxPassengers +
                ", passengersCount=" + carPassenger.size() +
                '}';
    }
}
