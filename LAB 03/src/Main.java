
public class Main {
    public static void main(String[] args) {

        Store store = new Store();
        Media media = new Media("Global News", "Various", "000111222", 5.99);
        Book book1 = new Book("Effective Java", "Joshua Bloch", "0123456789", 40.00, 15);
        Novel novel1 = new Novel("The Hobbit", "J.R.R. Tolkien", "9876543210", 22.90, 12, "Fantasy");
        AcademicBook academicBook1 = new AcademicBook("Principles of Quantum Mechanics", "R. Shankar", "1112131415", 60.50, 5, "Physics");
        Movie movie1 = new Movie("Inception", "Christopher Nolan", "5678901234", 14.99, 148);
        Music music1 = new Music("Thriller", "Michael Jackson", "2345678901", 18.99, "Pop");

        store.addMedia(media);
        store.addMedia(book1);
        store.addMedia(novel1);
        store.addMedia(academicBook1);
        store.addMedia(movie1);
        store.addMedia(music1);

        // User setup
        User user1 = new User("alice123", "alice@example.com");
        User user2 = new User("bob456", "bob@example.com");

        // Add users to store
        store.addUser(user1);
        store.addUser(user2);

        // Shopping interactions
        user1.addToCart(book1);
        user1.addToCart(novel1);
        user2.addToCart(movie1);
        user2.addToCart(music1);

        // Reviews and ratings
        Review review1 = new Review("alice123", 4.5, "Very insightful book.");
        book1.addReview(review1);
        Review review2 = new Review("bob456", 4, "Good read but a bit long.");
        novel1.addReview(review2);

        // Display before checkout
        System.out.println("Before checkout:");
        System.out.println();
        store.displayMedias();
        System.out.println();
        store.displayUsers();
        System.out.println();

        // Users perform checkout
        user1.checkout();
        user2.checkout();

        // Display after checkout
        System.out.println("After checkout:");
        System.out.println();
        store.displayMedias();
        System.out.println();
        store.displayUsers();
        System.out.println();

        // Additional tests
        System.out.println("Searching for 'Effective Java':");
        System.out.println();
        Media foundBook = store.searchBook("Effective Java");
        if (foundBook != null) {
            System.out.println("Found Book: " + foundBook);
        } else {
            System.out.println("Book not found.");
        }

        // Display reviews and ratings
        System.out.println(book1.getTitle() + " Average Rating: " + book1.getAverageRating());
        System.out.println(novel1.getTitle() + " Average Rating: " + novel1.getAverageRating());
        System.out.println();

        // Display user purchased media
        System.out.println();
        System.out.println(user1.getUsername() + "'s Purchased Media:");
        System.out.println();
        for (Media m : user1.getPurchaseMediaList()) {
            System.out.println(m);
        }
        System.out.println();
        System.out.println(user2.getUsername() + "'s Purchased Media:");
        System.out.println();
        for (Media m : user2.getPurchaseMediaList()) {
            System.out.println(m);
        }
    }
}