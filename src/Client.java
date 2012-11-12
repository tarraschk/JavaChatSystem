import java.io.*;
import java.util.*;

public class Client {
	protected int id;
	
	/* Getters et setters de id */
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Récupère la commande tapée par le client et sépare la commande de l'argument
	 * (pour l'instant) affiche command - argument
	 * @throws IOException
	 * @throws java.rmi.RemoteException
	 */
	public void lire() throws IOException, java.rmi.RemoteException{
		BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
		String cmd = keyboard.readLine();
		if (cmd.indexOf(' ') != -1) {
			String command = cmd.substring(0, cmd.indexOf(' '));
			String message = cmd.substring(cmd.indexOf(' '));
			System.out.println("Commande : "+command+" - "+message);
		}
	}
	
	public static void main(String args[]) throws IOException {
		Client Clienttest = new Client();
		Clienttest.lire();
		
	}
}
