
public class Main {
    public static void main(String[] args) {
        //1. Circle class
        Circle circle = new Circle(3);
        System.out.println("Circle info");
        //Test setters
        circle.setRadius(5);
        //Test getters
        System.out.println("The radius of the circle is : " + circle.getRadius());
        //Test area
        System.out.println("The area of the circle is : " + circle.calculateArea());
        //Test circumference
        System.out.println("The circumference of the circle is : " + circle.calculateCircumference());
        //Test toString()
        System.out.println(circle);
        System.out.println();

        //2. Rectangle class
        Rectangle rectangle = new Rectangle(5,6);
        System.out.println("Rectangle info");
        //Test setters
        rectangle.setHeight(7);
        rectangle.setWidth(4);
        //Test getters
        System.out.println("The height of the Rectangle is : " + rectangle.getHeight());
        System.out.println("The width of the Rectangle is : " + rectangle.getWidth());
        //Test area
        System.out.println("The area of the Rectangle is : " + rectangle.calculateArea());
        //Test circumference
        System.out.println("The circumference of the Rectangle is : " + rectangle.calculateCircumference());
        //Test toString()
        System.out.println(rectangle);
        System.out.println();

        //3. Triangle class
        Triangle triangle = new Triangle(3,4);
        System.out.println("Triangle info");
        //Test setters
        triangle.setHeight(8);
        triangle.setBase(2);
        //Test getters
        System.out.println("The height of the Triangle is : " + triangle.getHeight());
        System.out.println("The base of the Triangle is : " + triangle.getBase());
        //Test area
        System.out.println("The area of the Triangle is : " + triangle.calculateArea());
        //Test circumference
        System.out.println("The circumference of the Triangle is : " + triangle.calculateCircumference());
        //Test toString()
        System.out.println(triangle);
    }
}