import java.io.*;
import java.util.ArrayList;

public class Phonebook {
	ArrayList<PhonebookEntry> entry_list;
	int entry_count = 0;
	final String phfile = "D:\\Users\\David\\Documents\\School\\HKU\\Year 4\\Sem2\\COMP2396\\COMP2396 Program\\Exam Task5\\src\\Phonebook.sav";
	// final String phfile = "Phonebook.sav";
	File pbfile;
	ObjectInputStream pbis;
	ObjectOutputStream pbos;
	boolean autosave;
	
	public Phonebook(boolean autosaveflag) {
		autosave = autosaveflag;
		try {
			pbfile = new File(phfile);
			if ( pbfile.exists() ) {
				pbis = new ObjectInputStream(new FileInputStream(pbfile));
				entry_list = (ArrayList<PhonebookEntry>) pbis.readObject();
				
				entry_count = entry_list.size();
				subtask_5_2();
			}
			else {
				entry_list = new ArrayList<PhonebookEntry>();
				System.out.println("Creating new phonebook");
			}
			
			if ( pbis != null )
				pbis.close();
		} catch (IOException e) {
			System.out.println("Unable to handle input Phonebook.sav");
			System.exit(0);;
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found exception: PhonebookEntry");
			System.exit(0);
		} 
		
	}
		
	public void updatePBFile() {
		
		if ( autosave ) return;
		
		try {
			
			pbos = new ObjectOutputStream(new FileOutputStream(pbfile));
			pbos.writeObject(entry_list);			
			pbos.close();
			
		} catch (IOException e) {
			System.out.println("Unable to handle update Phonebook.sav");
			System.exit(0);;
		}		
		
	}

	// entryExist(nm): check if name nm is in the entry_list or not
	//		returns –1 if nm is not in the entry_list
	//		returns index to the entry_list array 
	public int entryExist(String nm) {
		nm = nm.toUpperCase();
		int i;
		for (i=0; i<entry_count; i++ ) {
			if ( entry_list.get(i).name.equals(nm) ) return i;
		}
		return -1;
	}

	public ArrayList<Integer> matchingEntries(String nm) {
		nm = nm.toUpperCase();
		int i;
		ArrayList<Integer> matching = new ArrayList<>();
		for (i=0; i<entry_count; i++ ) {
			if ( entry_list.get(i).name.toUpperCase().contains(nm) ){
				matching.add(i);
			}
		}
		return matching;
	}
	// addEntry(nm,phonebook_entry):  add the name nm with phonebook_entry to the
	//						phonebook
	//		returns false if name nm is already in the entry_list or the list is full
	public boolean addEntry(String nm, PhonebookEntry pbe) {
		
		if ( entryExist(nm) >= 0 ) return false;
		entry_list.add(pbe);
		entry_count++;
		
		updatePBFile();
		
		return true;
	}

		
	// getEntry(int i)
	public PhonebookEntry getEntry(int i) {
		return entry_list.get(i);
	}
	
	public void subtask_5_2() {
		
		// Subtask 5.2a
		System.out.print("Restoring Phonebook from " + phfile);
		System.out.println();
		
		// Subtask 5.2b
		System.out.print("Number of PhonebookEntry restored " + entry_count);
		System.out.println();
		
		// Subtask 5.2c
		// List all entries of the Phonebook line by line
		for(PhonebookEntry entry : entry_list){
			System.out.println(entry.name + "," + entry.phone_no + "," + entry.address);
		}
		
	}

	public void updateEntry(int recno,PhonebookEntry pbe){
		entry_list.set(recno, pbe);
		updatePBFile();
	}

	public void deleteEntry(int recno){
		entry_list.remove(recno);
		entry_count--;
		updatePBFile();
	}

	public boolean recnoExist(int recno){
		return entry_count>recno;
	}
}
