import java.rmi.*;
import java.util.*;
import java.rmi.server.UnicastRemoteObject;

public class ChatServer extends UnicastRemoteObject implements Chat {
	LinkedList<Client> listeClients;
	LinkedList<String> historiqueMessages;
	
	// Implémentation du constructeur
	public ChatServer(String msg) throws java.rmi.RemoteException {
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
