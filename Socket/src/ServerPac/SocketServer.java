package ServerPac;
import java.io.*;  
import java.net.*;  


public class SocketServer {
	protected ServerSocket server = null;
	protected Socket inComingSocket = null;

	public void AcceptConnection(int ListenPort) {
		try {
			server = new ServerSocket(ListenPort,5);
			while(true) {
				inComingSocket = server.accept();
				handelConnection();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	public void handelConnection() {
		new Thread(new Connectionhandler(inComingSocket)).start();
	}
		

	public static void main (String [] args) {
		SocketServer server = new SocketServer();
		server.AcceptConnection(3000);
	}
}