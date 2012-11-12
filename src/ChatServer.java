import java.util.*;
import java.rmi.server.UnicastRemoteObject;

public class ChatServer extends UnicastRemoteObject implements Chat {
	protected LinkedList<Client> listeClients;
	protected LinkedList<String> historiqueMessages;
	protected int port;
	
	/* Méthodes liées à l'argument listeClients */
	public LinkedList<Client> getListeClients() {
		return listeClients;
	}
	public void setListeClients(LinkedList<Client> listeClients) {
		this.listeClients = listeClients;
	}
	public void addClient(Client client) {
		this.listeClients.addLast(client);
	}
	public void removeClient(Client client) {
		this.listeClients.remove(client);
	}

	/* Méthodes liées à l'historique des messages */
	public LinkedList<String> getHistoriqueMessages() {
		return historiqueMessages;
	}
	public void setHistoriqueMessages(LinkedList<String> historiqueMessages) {
		this.historiqueMessages = historiqueMessages;
	}
	public void addMessage(String message, Client auteur) {
		this.historiqueMessages.addLast(auteur+" : "+message);
	}
	
	/* Méthodes du port */
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}

	// Implémentation du constructeur
	public ChatServer() throws java.rmi.RemoteException {
		LinkedList<Client> liste = new LinkedList<Client>();
		LinkedList<String> historique = new LinkedList<String>();
		listeClients = liste;
		historiqueMessages = historique;
	}
	
	// Implémentation de la méthode distante
	public void afficherHistoriqueMessage() throws java.rmi.RemoteException {
		for(String s:historiqueMessages)
			System.out.println(s);
	}
	
	public void send(String message){
		
	}
	
	public void connect(int id){
		
	}
	
	public void bye(){
		
	}
	
	public LinkedList<Client> who() {
		
		return listeClients;
	}
}