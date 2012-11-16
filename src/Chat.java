import java.util.*;

public interface Chat extends java.rmi.Remote {
	public void send(String message,Client auteur) throws java.rmi.RemoteException;
	public boolean connect(String id, Client client) throws java.rmi.RemoteException;
	public void bye(Client client) throws java.rmi.RemoteException;
	public String who() throws java.rmi.RemoteException;
	public String afficher(int lectureId) throws java.rmi.RemoteException;
	public void vider() throws java.rmi.RemoteException;
	public int getSize() throws java.rmi.RemoteException;
}

