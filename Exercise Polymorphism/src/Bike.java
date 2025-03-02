public class Bike implements Vehicle{

    private String brand;
    private double hour;
    static final double hourlyRate = 10;

    public Bike(){

    }

    public Bike(String brand, double hour) {
        this.brand = brand;
        this.hour = hour;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getHour() {
        return hour;
    }

    public void setHour(double hour) {
        this.hour = hour;
    }

    @Override
    public double calculateRentalCost() {
        return hour*hourlyRate;
    }

    @Override
    public void displayDetails() {
        System.out.println("Bike Brand : " + brand);
        System.out.println("Hourly Rental Rate : " + hourlyRate);
        System.out.println("Rental Cost : " + calculateRentalCost());
    }
}
