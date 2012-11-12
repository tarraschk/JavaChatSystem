import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * 12 nov. 2012 by  maxime
 * JavaChatSystem
 */

/**
 * @author maxime
 *
 */
public class Application {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String args[]) throws IOException {
		Client Clienttest = new Client();
		Clienttest.lire();
		int port; String URL;
		try { // transformation d'une chaîne de caractères en entier
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
			Chat obj = new ChatServer();
			// Calcul de l'URL du serveur
			URL = "//"+InetAddress.getLocalHost().getHostName()+":"+port+"/ChatServer";
			Naming.rebind(URL, obj);
		} catch (Exception exc) { }
	}
}
