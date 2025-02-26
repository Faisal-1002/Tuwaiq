
public class Main {
    public static void main(String[] args) {

        //Q1
        Book book = new Book("Math", 200, "Faisal");
        Movie movie = new Movie("Horror Movie", 500, "Ali");

        System.out.println("Book price before discount : " + book.getPrice());
        System.out.println("Book price after discount : " + book.getDiscount());
        System.out.println();
        System.out.println("Movie price before discount : " + movie.getPrice());
        System.out.println("Movie price after discount : " + movie.getDiscount());
        System.out.println();

        //Q2
        MovablePoint movablePoint = new MovablePoint(3,3,5,5);

        System.out.print("Move up from " + movablePoint.getY() + " to ");
        movablePoint.moveUp();
        System.out.print("Move down from " + movablePoint.getY() + " to ");
        movablePoint.moveDown();
        System.out.print("Move left from " + movablePoint.getX() + " to ");
        movablePoint.moveLeft();
        System.out.print("Move right from " + movablePoint.getX() + " to ");
        movablePoint.moveRight();
    }
}