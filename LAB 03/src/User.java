import java.util.ArrayList;

public class User {

    private String username;
    private String email;
    private ArrayList<Media> purchaseMediaList;
    private ArrayList<Media> shoppingCart;

    public User(){
        this.purchaseMediaList = new ArrayList<>();
        this.shoppingCart = new ArrayList<>();
    }

    public User(String username, String email) {
        this.purchaseMediaList = new ArrayList<>();
        this.shoppingCart = new ArrayList<>();
        this.username = username;
        this.email = email;
    }

    public User(String username, String email, ArrayList<Media> purchaseMediaList, ArrayList<Media> shoppingCart) {
        this.username = username;
        this.email = email;
        this.purchaseMediaList = purchaseMediaList;
        this.shoppingCart = shoppingCart;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Media> getPurchaseMediaList() {
        return purchaseMediaList;
    }

    public void setPurchaseMediaList(ArrayList<Media> purchaseMediaList) {
        this.purchaseMediaList = purchaseMediaList;
    }

    public ArrayList<Media> getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ArrayList<Media> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public void addToCart(Media media){
        shoppingCart.add(media);
    }

    public void removeFromCart(Media media){
        shoppingCart.remove(media);
    }

    public void checkout() {
        for (Media media : new ArrayList<>(shoppingCart)) {
            purchaseMediaList.add(media);
            shoppingCart.remove(media);
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", purchaseMediaList=" + purchaseMediaList.size() +
                ", shoppingCart=" + shoppingCart.size() +
                '}';
    }

}
