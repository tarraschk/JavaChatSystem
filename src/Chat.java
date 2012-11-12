import java.util.*;

public interface Chat extends java.rmi.Remote {
	public void send(String message) throws java.rmi.RemoteException;
	public void connect(int id) throws java.rmi.RemoteException;
	public void bye() throws java.rmi.RemoteException;
	public List<String> who() throws java.rmi.RemoteException;
}

