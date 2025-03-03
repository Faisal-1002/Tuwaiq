public class Subscribers extends Passenger{

    public Subscribers(String name, String id) {
        super(name, id);
    }

    @Override
    public void reserve(Car car) {
        if (car.addPassenger(this)) {
            setCar(car);
            setTripCost(car.getRoute().getTripPrice() / 2);
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
