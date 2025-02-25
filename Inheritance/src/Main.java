
public class Main {
    public static void main(String[] args) {

        //1. Shape
        System.out.println("Shape info");
        Shape shape = new Shape("Yello", true);
        shape.setColor("Black");
        shape.setFilled(false);
        System.out.println("The color of the shape is : " + shape.getColor());
        System.out.println("Is the shape filled : " + shape.isFilled());
        System.out.println(shape);
        System.out.println("------------------------------------------------------------");

        //2. Circle
        System.out.println("Circle info");
        Circle circle = new Circle("Red", true, 3);
        circle.setColor("Blue");
        circle.setFilled(false);
        circle.setRadius(5);
        System.out.println("The color of the circle is : " + circle.getColor());
        System.out.println("Is the circle filled : " + circle.isFilled());
        System.out.println("The radius of the circle is : " + circle.getRadius());
        System.out.println("The area of the circle is : " + circle.getArea());
        System.out.println("The perimeter of the circle is : " + circle.getPerimeter());
        System.out.println(circle);
        System.out.println("------------------------------------------------------------");

        //3. Rectangle
        System.out.println("Rectangle info");
        Rectangle rectangle = new Rectangle("Pink", false, 3, 5);
        rectangle.setColor("White");
        rectangle.setFilled(true);
        rectangle.setWidth(5);
        rectangle.setLength(7);
        System.out.println("The color of the rectangle is : " + rectangle.getColor());
        System.out.println("Is the rectangle filled : " + rectangle.isFilled());
        System.out.println("The width of the rectangle is : " + rectangle.getWidth());
        System.out.println("The length of the rectangle is : " + rectangle.getLength());
        System.out.println("The area of the rectangle is : " + rectangle.getArea());
        System.out.println("The perimeter of the rectangle is : " + rectangle.getPerimeter());
        System.out.println(rectangle);
        System.out.println("------------------------------------------------------------");

        //4. Square
        System.out.println("Square info");
        Square square = new Square("Orange", true, 6);
        square.setColor("Brown");
        square.setFilled(false);
        square.setSide(8);
        System.out.println("The color of the square is : " + square.getColor());
        System.out.println("Is the square filled : " + square.isFilled());
        System.out.println("The side of the square is : " + square.getSide());
        System.out.println("The area of the square is : " + square.getArea());
        System.out.println("The perimeter of the square is : " + square.getPerimeter());
        System.out.println(square);
    }
}