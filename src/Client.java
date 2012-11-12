import java.io.*;
import java.util.*;

public class Client {
	protected int id;
	protected boolean connectStatus;
	
	public boolean getConnectStatus() {
		return connectStatus;
	}
	public void setConnectStatus(boolean connectStatus) {
		this.connectStatus = connectStatus;
	}
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
	public String[] lire() throws IOException, java.rmi.RemoteException{
		System.out.println("Entrer une commande :");
		BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
		String cmd = keyboard.readLine();
		if (cmd.indexOf(' ') != -1) {
			String[] commande = new String[2];
			commande[0] = cmd.substring(0, cmd.indexOf(' '));
			commande[1] = cmd.substring(cmd.indexOf(' '));
			System.out.println("Commande : "+commande[0]+" - "+commande[1]);
			
			return commande;
		}
		else {
			System.out.println("Erreur de commande");
			return null;
		}
	}
	
	public static void main(String args[]) throws IOException {
		Client Clienttest = new Client();
		String[] commandeMessage = new String[2];
		commandeMessage = Clienttest.lire();
		
		while(commandeMessage[0] != "bye") {
			if(commandeMessage[0]=="connect"){
				Clienttest.setConnectStatus(connect(commandeMessage[1]));
			}
			else if(commandeMessage[0]=="send" && Clienttest.getConnectStatus()){
				send(commandeMessage[1]);
			}
			else if(commandeMessage[0]=="who" && Clienttest.getConnectStatus()) {
				who();
			}
			else if((commandeMessage[0]=="who" || commandeMessage[0]=="send") && !Clienttest.getConnectStatus()) {
				System.out.println("Veuillez vous connecter auparavant");
			}
			
			commandeMessage = Clienttest.lire();
		}
		
		System.out.println("A bientôt !");
		
	}
}
