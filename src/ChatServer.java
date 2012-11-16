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
	protected Hashtable<Integer,Client> listeClients;
	protected LinkedList<String> historiqueMessages;
	protected int port;
	protected String URL;
	
	/* Getters et setters de Url */
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		this.URL = uRL;
	}
	
	/* Méthodes liées à l'argument listeClients */
	public Hashtable<Integer,Client> getListeClients() {
		return listeClients;
	}
	public void setListeClients(Hashtable<Integer,Client> listeClients) {
		this.listeClients = listeClients;
	}
	public void addClient(int id, Client client) {
		this.listeClients.put(id,client);
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
		Hashtable<Integer,Client> liste = new Hashtable<Integer,Client>();
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
	
	public void send(String message, Client auteur) throws java.rmi.RemoteException{
		this.addMessage(message,auteur);
	}
	
	public boolean connect(int id, Client client) throws java.rmi.RemoteException{
		this.addClient(id, client);
		
		return true;
	}
	
	public void bye() throws java.rmi.RemoteException{
		
	}
	
	public void who() throws java.rmi.RemoteException{
		
		 for (Enumeration<Integer> e = listeClients.keys() ; e.hasMoreElements() ;) {
	         System.out.println(e.nextElement()+" : "+listeClients.get(e));
	     }
		
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
			URL = "//"+InetAddress.getLocalHost().getHostName()+":"+port+"/ChatServer";
			System.out.println("Le serveur sera lancé sur l'adresse : "+URL);
			Naming.unbind(URL);
			// Création du serveur de nom - rmiregistry
			Registry registry = LocateRegistry.createRegistry(port);
			// Création d'une instance de l'objet serveur
			ChatServer obj = new ChatServer();
			obj.setPort(port);
			obj.setURL(URL);
			Chat objChat = (Chat) obj;
			// Calcul de l'URL du serveur
			Naming.rebind(URL, obj);
		} catch (Exception exc) {
			System.out.println(exc);
		}
	}
}