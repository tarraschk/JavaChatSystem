import java.io.*;
import java.util.*;


public class Client implements Chat {
	
	public void lire() throws IOException {
		BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
		String cmd = keyboard.readLine();
		if (cmd.indexOf(' ') != -1) {
			String command = cmd.substring(0, cmd.indexOf(' '));
			String message = cmd.substring(cmd.indexOf(' '));
			System.out.println("Commande : "+command+" - "+message);
		}
	}
	
	public void send(String message){
		
	}
	
	public void connect(int id){
		
	}
	
	public void bye(){
		
	}
	
	public LinkedList<Client> who() {
		return null;
	 
	}
}
