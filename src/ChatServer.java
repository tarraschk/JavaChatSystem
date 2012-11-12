import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ChatServer extends UnicastRemoteObject implements Chat {
	protected LinkedList<Client> listeClients;
	protected LinkedList<String> historiqueMessages;
	protected int port;
	protected String URL;
	
	/* Getters et setters de Url */
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	
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
	
	/**
	 * affiche l'historique des messages (= la conversation)
	 * @throws java.rmi.RemoteException
	 */
	public void afficherHistoriqueMessage() throws java.rmi.RemoteException {
		for(String s:historiqueMessages)
			System.out.println(s);
	}
	
	public void send(String message) throws java.rmi.RemoteException{
		
	}
	
	public void connect(int id) throws java.rmi.RemoteException{
		
	}
	
	public void bye() throws java.rmi.RemoteException{
		
	}
	
	public LinkedList<Client> who() throws java.rmi.RemoteException{
		
		return listeClients;
	}
	
	public static void main(String args[]) throws IOException {
		int port; String URL;
		try { // transformation d'une chaine de caractères en entier
			Integer I = new Integer(args[0]); port = I.intValue();
		} catch (Exception ex) {
			System.out.println(" Please enter: Server <port>");
			BufferedReader portKeyboard = new BufferedReader(new InputStreamReader(System.in));
			String portString = portKeyboard.readLine();
			port = Integer.parseInt(portString);
		}
		try {
			// Création du serveur de nom - rmiregistry
			Registry registry = LocateRegistry.createRegistry(port);
			// Création d'une instance de l'objet serveur
			ChatServer obj = new ChatServer();
			obj.setPort(port);
			Chat objChat = (Chat) obj;
			// Calcul de l'URL du serveur
			URL = "//"+InetAddress.getLocalHost().getHostName()+":"+port+"/ChatServer";
			Naming.rebind(URL, obj);
		} catch (Exception exc) { }
	}
}