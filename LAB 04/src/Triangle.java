public class Triangle extends Shape{

    private double height;
    private double base;

    public Triangle(){}

    public Triangle(double height, double base){
        this.height = height;
        this.base = base;
    }

    public Triangle(int x, int y, double height, double base) {
        super(x, y);
        this.height = height;
        this.base = base;
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

    public double getBase() {
        return base;
    }

    public void setBase(double base) {
        if (base>=0)
            this.base = base;
        else
            System.out.println("Base cannot be negative");
    }

    //This is the override method we got form the abstract class Shape.
    //The area of Triangle is : height*base*0.5
    @Override
    public double calculateArea() {
        return 0.5*base*height;
    }

    //This is the override method we got form the abstract class Shape.
    //The circumference of Triangle is : 3*base (because it is equilateral triangles)
    @Override
    public double calculateCircumference() {
        return 3*base;
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "height=" + height +
                ", base=" + base +
                '}' + super.toString();
    }
}
