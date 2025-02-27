public abstract class Shape {
    // x and y are the coordination of the center of the shape
    private int x;
    private int y;

    public Shape(){}

    public Shape(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    //Abstract method of area
    public abstract double calculateArea();
    //Abstract method of circumference
    public abstract double calculateCircumference();

    @Override
    public String toString() {
        return "Shape{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
