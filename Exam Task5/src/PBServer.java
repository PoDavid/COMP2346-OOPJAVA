import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class PBServer {
	
	AccessControlManager ac_store;
	Phonebook pb;
	boolean autosave = false;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		PBServer cs = new PBServer();
		cs.go();
	}
	
	public void go() {		
		
		ac_store = new AccessControlManager();
		autosave = true;
		pb = new Phonebook(autosave);
			
		try {
			
			System.out.println("Server listening");
			ServerSocket ss = new ServerSocket(5000);

			while (true) {
				Socket s = ss.accept();
				
				System.out.println("Accepting connection");
				
				Thread t = new Thread(new ClientHandler(s));
				t.start();
				
			}
		} catch (Exception e) { e.printStackTrace(); }
		
	}
	
	class ClientHandler implements Runnable {
		
		OutputStream os;
		ObjectOutputStream oos;
		InputStream is;
		ObjectInputStream ois;
		
		public ClientHandler(Socket s) {		
			try {
				os = s.getOutputStream();
				oos = new ObjectOutputStream(os);
				is = s.getInputStream();
				ois = new ObjectInputStream(is);
			} catch (IOException e) {
				System.out.println("ClientHandler error");
				e.printStackTrace();
			}
		}
			
		
		public void run() {
			
			boolean more_msg = true;
			
			while ( more_msg ) {
				
				try {
					
					SPBMessage m = (SPBMessage) ois.readObject();
					System.out.println("Received msg:" + m);
					System.out.println("Recieved command: " + m.command);
					
					if ( m.command.equals("LOGIN") ) {
						if ( ac_store.verifyPassword(m.username, m.password) ) {
							m.reply_msg = "LOGIN success";
						}
						else 
							m.reply_msg = "LOGIN fail";
						
						oos.writeObject(m);
						continue;
					}
				
					String entry_name = m.entry.name;

					if ( ! ac_store.verifyPassword(m.username,m.password) )
					{
						m.reply_msg = "Incorrect password";
					}
					else if ( m.command.equals("GETBYNAME") )
					{
						System.out.println("GETBYNAME: " + entry_name);
						m.entry = null;
						if ( ac_store.checkReadPerm(m.username) )
						{
							ArrayList<Integer> allMatching = pb.matchingEntries(entry_name);
							if ( allMatching.size()>0 )
							{
								int i = allMatching.get(0);
								m.entry = pb.getEntry(i);
								m.recno = i;
								m.reply_msg = "GETBYNAME success";
								if (allMatching.size()>1){
									m.reply_msg += ":";
									for(Integer no : allMatching){
										m.reply_msg += " " + no + ",";
									}
									m.reply_msg = m.reply_msg.substring(0, m.reply_msg.length() - 1);
								}
							}
							else
								m.reply_msg = "Phonebook entry does not exist";
						} else {
							m.reply_msg = "Invalid permission";
						}
					}
					else if ( m.command.equals("GETBYRECNO") )
					{
						// code to perform get by rec no.
						System.out.println("GETBYRECNO: " + entry_name);
						m.entry = null;
						if ( ac_store.checkReadPerm(m.username) )
						{
							int i =  Integer.parseInt(entry_name);
							if (pb.recnoExist(i))
							{
								m.entry = pb.getEntry(i);
								m.recno = i;
								m.reply_msg = "GETBYRECNO success";
							}
							else
								m.reply_msg = "Phonebook entry does not exist";
						} else {
							m.reply_msg = "Invalid permission";
						}
					}				
					else  if ( m.command.equals("ADD") )
					{
						if ( ac_store.checkWritePerm(m.username) )
						{
							int i = pb.entryExist(m.entry.name);
							if ( i < 0 ) 
							{
								pb.addEntry(m.entry.name, m.entry);
								int recno = pb.entryExist(m.entry.name);
								m.reply_msg = "ADD success";
								m.recno = recno;
							}
						else
								m.reply_msg = "Phonebook entry exists";
						} else {
							m.reply_msg = "Invalid permission";
						}
					}
					else if ( m.command.equals("UPDATE") )
					{
						// code to update the Phonebook
						if ( ac_store.checkWritePerm(m.username) )
						{
							int i = pb.entryExist(m.entry.name);
							if ( i >= 0 )
							{
								PhonebookEntry pbe;
								pbe = new PhonebookEntry(m.entry.name, m.entry.phone_no, m.entry.address);
								pb.updateEntry(i,pbe);
								m.reply_msg = "UPDATE success";
							}
						else
								m.reply_msg = "Phonebook entry does not exist";
						} else {
							m.reply_msg = "Invalid permission";
						}
					}
					else if ( m.command.equals("DELETE") )
					{
						if ( ac_store.checkWritePerm(m.username) )
						{
							int i = pb.entryExist(m.entry.name);
							if ( i >= 0 )
							{
								pb.deleteEntry(i);
								m.reply_msg = "Delete success";
							}
						else
								m.reply_msg = "Phonebook entry does not exist";
						} else {
							m.reply_msg = "Invalid permission";
						}
					}
					else if ( m.command.equals("PREV") ) {
						System.out.println("PREV: " + entry_name);
						if (entry_name.isEmpty()) {
							m.reply_msg = "Empty record no. field.";
						} else {
							m.entry = null;
							if (ac_store.checkReadPerm(m.username)) {
								int i = Integer.parseInt(entry_name) - 1;
								if (pb.recnoExist(i)) {
									m.entry = pb.getEntry(i);
									m.recno = i;
									m.reply_msg = "PREV success";
								} else
									m.reply_msg = "Phonebook entry out of bound";
							} else {
								m.reply_msg = "Invalid permission";
							}
						}
					}
					else if ( m.command.equals("NEXT") ) {
						System.out.println("NEXT: " + entry_name);
						if (entry_name.isEmpty()) {
							m.reply_msg = "Empty record no. field.";
						} else {
							m.entry = null;
							if (ac_store.checkReadPerm(m.username)) {
								int i = Integer.parseInt(entry_name) + 1;
								if (pb.recnoExist(i)) {
									m.entry = pb.getEntry(i);
									m.recno = i;
									m.reply_msg = "NEXT success";
								} else
									m.reply_msg = "Phonebook entry does not exist";
							} else {
								m.reply_msg = "Invalid permission";
							}
						}
					}
					else if ( m.command.equals("LOGOFF") )
					{
						more_msg = false;
						m.reply_msg = "LOGOFF success";
						oos.writeObject(m);
						oos.close();
						ois.close();
						return;
					}

					oos.writeObject(m);
				
				} catch ( Exception e ) {
					System.out.println("Exception in command processing");
					e.printStackTrace();
					return;
				}
					
			}		
											
		}

	}
}





