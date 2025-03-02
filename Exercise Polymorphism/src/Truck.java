public class Truck implements Vehicle{

    private String type;
    private double week;
    static final double weeklyRate = 500;

    public Truck(){

    }

    public Truck(String type, double week) {
        this.type = type;
        this.week = week;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getWeek() {
        return week;
    }

    public void setWeek(double week) {
        this.week = week;
    }

    @Override
    public double calculateRentalCost() {
        return week*weeklyRate;
    }

    @Override
    public void displayDetails() {
        System.out.println("Truck type : " + type);
        System.out.println("Weekly Rental Rate : " + weeklyRate);
        System.out.println("Rental Cost : " + calculateRentalCost());
    }
}
