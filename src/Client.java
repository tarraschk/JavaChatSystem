import java.io.*;
import java.rmi.Naming;
import java.util.*;

public class Client {
	protected int id;
	protected boolean connectStatus;
	protected Chat servChat;
	
	/**
	 * Getter et setter de l'objet chat
	 * @return
	 */
	public Chat getServChat() {
		return servChat;
	}
	public void setServChat(Chat servChat) {
		this.servChat = servChat;
	}
	
	/**
	 * Getter et setter de statut de connexion
	 * @return
	 */
	public boolean getConnectStatus() {
		return connectStatus;
	}
	public void setConnectStatus(boolean connectStatus) {
		this.connectStatus = connectStatus;
	}
	
	/** 
	 * Getters et setters de id 
	 * @return
	 */
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
		System.out.println("Entrez une commande :");
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
				if(!Clienttest.getConnectStatus()) {
					Chat obj = (Chat) Naming.lookup("//Adri-VAIO/ChatServer");
					Clienttest.setServChat(obj);
					System.out.println("Bienvenue sur le serveur !");
					Clienttest.setConnectStatus(obj.connect(Integer.parseInt(commandeMessage[1])));
				}
				else {
					System.out.println("Vous êtes déjà connecté, essayez une autre commande (send, who, bye)...");
				}
					
			}
			else if(commandeMessage[0]=="send" && Clienttest.getConnectStatus()){
				Clienttest.getServChat().send(commandeMessage[1]);
			}
			else if(commandeMessage[0]=="who" && Clienttest.getConnectStatus()) {
				Clienttest.getServChat().who();
			}
			else if((commandeMessage[0]=="who" || commandeMessage[0]=="send") && !Clienttest.getConnectStatus()) {
				System.out.println("Veuillez vous connecter auparavant");
			}
			
			commandeMessage = Clienttest.lire();
		}
		
		System.out.println("A bientôt !");
		
	}
}
