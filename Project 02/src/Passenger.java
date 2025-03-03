import java.util.List;

public abstract class Passenger {

    private String name;
    private String Id;
    private Car car;
    private double tripCost;

    public Passenger(){

    }

    public Passenger(String name, String Id) {
        this.name = name;
        this.Id = Id;
    }

    public Passenger(String name, String id, Car car, double tripCost) {
        this.name = name;
        Id = id;
        this.car = car;
        this.tripCost = tripCost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public double getTripCost() {
        return tripCost;
    }

    public void setTripCost(double tripCost) {
        this.tripCost = tripCost;
    }

    public abstract void reserve(Car car);

    @Override
    public String toString() {
        return "Passenger{" +
                "Name='" + name + '\'' +
                ", ID='" + Id + '\'' +
                ", Car Code=" + car +
                ", Trip Cost=" + tripCost +
                '}';
    }
}
