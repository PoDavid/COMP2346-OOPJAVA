import java.util.*;
import java.io.*;

public class Jukebox {
	
	ArrayList<Song> songList = new ArrayList <Song>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new Jukebox().go();

	}
	
	public void go() {
		getSongs();
		
		printSongs();
		
		// Subtask1. Sort the ArrayList<Song> wrt title of the Song
		Collections.sort(songList,Song.Comparators.Title);
		
		printSongs();
		
		// Subtask 2. Also sort the ArrayList<Song> wrt artist of the song
		Collections.sort(songList,Song.Comparators.Artist);

		printSongs();
	}
	
	void printSongs() {
		System.out.println("List of songs");
		System.out.println("=============");
		Iterator<Song> iter = songList.iterator();
		while ( iter.hasNext() ) {
			iter.next().printSong();
		}
		System.out.println("\n\n");
	}
	
	void getSongs() {
		try {
			File f = new File("C:\\SongList.txt");
			//File f = new File("D:\\Users\\David\\Documents\\School\\HKU\\Year 4\\Sem2\\COMP2396\\COMP2396 Program\\Exam\\src\\SongList.txt");
			BufferedReader reader=new BufferedReader(new FileReader(f));
			String line = null;
			
			while ((line = reader.readLine()) != null ) 
				addSong(line);
			
		} catch (Exception e) {

		}
	}
	
	void addSong(String lineToParse) {
		String[] toks = lineToParse.split("/");
		Song aSong = new Song(toks[0],toks[1],toks[2]);
		songList.add(aSong);
	}

}

