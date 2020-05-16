import java.util.Comparator;

public class Song{
	
	String title;
	String artist;
	String rating;
	
	Song(String t, String a, String r) {
		title = t; artist = a; rating = r;
	}
	
	public String getTitle() { return title; }
	public String getArtist() { return artist; }
	public String getRating() { return rating; }
	public String toString() { return (title + ":" + artist); }
	
	public void printSong() {
		System.out.println(getTitle() + ", " + getArtist() + ", " + getRating());
	}

	public static class Comparators{
		public static Comparator<Song> Title = Comparator.comparing(o -> o.title);
        public static Comparator<Song> Artist = Comparator.comparing(o -> o.artist);
	}
}
