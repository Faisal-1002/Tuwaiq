public class NonSubscribers extends Passenger{

    private boolean discountCoupon;

    public NonSubscribers(String name, String id, boolean discountCoupon) {
        super(name, id);
        this.discountCoupon = discountCoupon;
    }

    public boolean isDiscountCoupon() {
        return discountCoupon;
    }

    public void setDiscountCoupon(boolean discountCoupon) {
        this.discountCoupon = discountCoupon;
    }

    @Override
    public void reserve(Car car) {
        if (car.addPassenger(this)){
            setCar(car);
            if (discountCoupon){
                setTripCost(car.getRoute().getTripPrice()*0.9);
            } else {
                setTripCost(car.getRoute().getTripPrice());
            }
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
