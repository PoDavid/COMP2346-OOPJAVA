import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CountObjects {
	
	public void CountObjects() { }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if ( args.length < 1 ) {
			System.err.println("Usage: java CountObjects <s>");
				return;
		}
		
		CountObjects cobj = new CountObjects();
		cobj.doCountObjects(args[0]);

	}
	
	public void doCountObjects(String filename) {
		
		int obj_count = 0;
		
		System.out.println("Esimtating number of objects creating in file " + filename);
		
		// estimating the number of newly created in the filename
		// store the count in obj_count
		BufferedReader reader;
		try{
			reader = new BufferedReader(new FileReader(filename));
			String line = reader.readLine();
			int newCount = 0;
			while(line!=null){
				// System.out.println(line);
				newCount += line.split("new", -1).length - 1;
				line = reader.readLine();
			}
			obj_count+=newCount;
		} catch (IOException e) {
			e.printStackTrace();
		}


		System.out.println("Estimated number of object in file " + filename + " is "
				+ obj_count);
		
	}

}
