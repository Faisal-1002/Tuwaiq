import java.util.ArrayList;

public class Music extends Media{

    private String artist;

    public Music(String title, String auteur, String ISBN, double price, String artist) {
        super(title, auteur, ISBN, price);
        this.artist = artist;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void listen(User user) {
        user.addToCart(this);
    }

    public ArrayList<Music> generatePlaylist(ArrayList<Music> musicCatalog) {
        ArrayList<Music> playlist = new ArrayList<>();
        for (Music music : musicCatalog) {
            if (music.getArtist().equalsIgnoreCase(this.artist) && !music.getTitle().equals(this.getTitle())) {
                playlist.add(music);
            }
        }
        return playlist;
    }

    @Override
    public String getMediaType() {
        if (getPrice() >= 10)
            return "Premium Music";
        else
            return "Music";
    }

    // Override toString to include music specific details
    @Override
    public String toString() {
        return super.toString() +
                " | Artist: " + artist +
                " | Type: " + getMediaType();
    }

}
