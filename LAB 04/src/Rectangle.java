public class Rectangle extends Shape{

    private double height;
    private double width;

    public Rectangle(){}

    public Rectangle(double height, double width){
        this.height = height;
        this.width = width;
    }

    public Rectangle(int x, int y, double height, double width) {
        super(x, y);
        this.height = height;
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        if (height>=0)
            this.height = height;
        else
            System.out.println("Height cannot be negative");
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        if (width>=0)
            this.width = width;
        else
            System.out.println("Width cannot be negative");
    }

    //This is the override method we got form the abstract class Shape.
    //The area of Rectangle is : height*width
    @Override
    public double calculateArea() {
        return height*width;
    }

    //This is the override method we got form the abstract class Shape.
    //The circumference of Rectangle is : 2*height+2*width
    @Override
    public double calculateCircumference() {
        return height+height+width+width;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "height=" + height +
                ", width=" + width +
                '}' + super.toString();
    }
}
