public class Circle extends Shape{

    private double radius;

    public Circle(){}

    public Circle(double radius){
        this.radius = radius;
    }

    public Circle(int x, int y, double radius) {
        super(x, y);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        if (radius>=0)
            this.radius = radius;
        else
            System.out.println("Radius cannot be negative");
    }

    //This is the override method we got form the abstract class Shape.
    //The area of Circle is : radius^2*PI
    @Override
    public double calculateArea() {
        return radius*radius*Math.PI;
    }

    //This is the override method we got form the abstract class Shape.
    //The circumference of Circle is : radius*2*PI
    @Override
    public double calculateCircumference() {
        return 2*radius*Math.PI;
    }

    @Override
    public String toString() {
        return "Circle{" +
                "radius=" + radius +
                '}' + super.toString();
    }
}
