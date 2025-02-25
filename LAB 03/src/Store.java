import java.util.ArrayList;

public class Store {

    private ArrayList<User> user;
    private ArrayList<Media> media;

    public Store() {
        this.user = new ArrayList<>();
        this.media = new ArrayList<>();
    }

    public Store(ArrayList<User> user, ArrayList<Media> media) {
        this.user = user;
        this.media = media;
    }

    public ArrayList<User> getUser() {
        return user;
    }

    public void setUser(ArrayList<User> user) {
        this.user = user;
    }

    public ArrayList<Media> getMedia() {
        return media;
    }

    public void setMedia(ArrayList<Media> media) {
        this.media = media;
    }

    public void addUser(User user){
        this.user.add(user);
    }

    public ArrayList<User> displayUsers(){
        System.out.println("Registered Users:");
        for (User user : user) {
            System.out.println(user);
        }
        return user;
    }

    public void addMedia(Media media){
        this.media.add(media);
    }

    public ArrayList<Media> displayMedias(){
        System.out.println("Available Media:");
        for (Media media : media) {
            System.out.println(media);
        }
        return media;
    }

    public Media searchBook(String title) {
        for (Media media : media) {
            if (media.getTitle().equalsIgnoreCase(title)) {
                return media;
            }
        }
        return null;
    }
}
