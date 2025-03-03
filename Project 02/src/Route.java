public class Route {

    private String startAddress;
    private String destinationAddress;
    private double tripPrice;

    public Route(){

    }

    public Route(String startAddress, String destinationAddress, double tripPrice) {
        this.startAddress = startAddress;
        this.destinationAddress = destinationAddress;
        this.tripPrice = tripPrice;
    }

    public String getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public double getTripPrice() {
        return tripPrice;
    }

    public void setTripPrice(double tripPrice) {
        this.tripPrice = tripPrice;
    }

    @Override
    public String toString() {
        return "Route{" +
                "startAddress='" + startAddress + '\'' +
                ", destinationAddress='" + destinationAddress + '\'' +
                ", tripPrice=" + tripPrice +
                '}';
    }
}
