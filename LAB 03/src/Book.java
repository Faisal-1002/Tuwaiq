import java.util.ArrayList;

public class Book extends Media{

    private int stock;
    private ArrayList<Review> review;

    public Book(String title, String auteur, String ISBN, double price, int stock) {
        super(title, auteur, ISBN, price);
        this.stock = stock;
        this.review = new ArrayList<>();
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public ArrayList<Review> getReview() {
        return review;
    }

    public void setReview(ArrayList<Review> review) {
        this.review = review;
    }

    public void addReview(Review r){
        review.add(r);
    }

    public double getAverageRating() {
        double sum = 0;
        for (Review review : review) {
            sum += review.getRating();
        }
        if (review.size()>0){
            return sum/review.size();
        }else {
            return 0;
        }
    }

    public void purchase(User user) {
        if (stock > 0) {
            stock--;
            user.addToCart(this);
        }
        System.out.println("Book is out of stock");
    }

    public boolean isBestSeller(){
        return getAverageRating()>=4.5;
    }

    public void restock(int quantity) {
        stock += quantity;
        System.out.println(quantity + " units have been added to the stock. New stock: " + stock);
    }

    @Override
    public String getMediaType() {
        if (isBestSeller()){
            return "Bestselling Book";
        } else {
            return "Book";
        }
    }

    @Override
    public String toString() {
        return super.toString() +
                " | Stock: " + stock +
                " | Average Rating: " + (Math.round(getAverageRating() * 100.0) / 100.0) +
                " | Bestseller: " + isBestSeller();
    }
}
