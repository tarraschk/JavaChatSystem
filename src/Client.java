import java.io.*;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.util.*;

public class Client  implements Serializable {
	protected int id;
	protected boolean connectStatus = false;
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
	 * R�cup�re la commande tap�e par le client et s�pare la commande de l'argument
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
			commande[1] = cmd.substring(cmd.indexOf(' ')+1);
			
			if(commande[1]== null)
				commande[1]="0";
			
			return commande;
		}
		else {
			System.out.println("Erreur de commande");
			return null;
		}
	}
	
	public static void main(String args[]) throws IOException, NotBoundException{
		Client clienttest = new Client();
		String[] commandeMessage = new String[2];
		commandeMessage = clienttest.lire();
		System.out.println(commandeMessage[0]);
		System.out.println(commandeMessage[1]);
		while(commandeMessage[0] != "bye") {
			if(commandeMessage[0].equals("connect")){
				if(!clienttest.getConnectStatus()) {
					Chat obj;
					try {
						System.out.println("Connexion � //Adri-VAIO:8000/ChatServer...");
						obj = (Chat) Naming.lookup("//Adri-VAIO:8000/ChatServer");
						clienttest.setServChat(obj);
						clienttest.setConnectStatus(obj.connect(Integer.parseInt(commandeMessage[1]), clienttest));
						System.out.println("Bienvenue sur le serveur !");
					} catch (Exception e) {
						System.out.println("Erreur de connexion !");
						e.printStackTrace();
					}
				}
				else {
					System.out.println("Vous �tes d�j� connect�, essayez une autre commande (send, who, bye)...");
				}
					
			}
			else if(commandeMessage[0].equals("send") && clienttest.getConnectStatus()){
				clienttest.getServChat().send(commandeMessage[1],clienttest);
				for(String s : ((ChatServer) clienttest.getServChat()).getHistoriqueMessages()){
					System.out.println(s);
				}
			}
			else if(commandeMessage[0].equals("who") && clienttest.getConnectStatus()) {
				clienttest.getServChat().who();
			}
			else if((commandeMessage[0].equals("who") || commandeMessage[0].equals("send")) && !clienttest.getConnectStatus()) {
				System.out.println("Veuillez vous connecter auparavant");
			}
			
			commandeMessage = clienttest.lire();
		}
		
		System.out.println("A bient�t !");
		
	}
}
