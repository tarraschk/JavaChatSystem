import java.util.*;

public interface Chat extends java.rmi.Remote {
	public void send(String message,Client auteur) throws java.rmi.RemoteException;
	public boolean connect(int id, Client client) throws java.rmi.RemoteException;
	public void bye() throws java.rmi.RemoteException;
	public void who() throws java.rmi.RemoteException;
	public String afficher() throws java.rmi.RemoteException;
}

