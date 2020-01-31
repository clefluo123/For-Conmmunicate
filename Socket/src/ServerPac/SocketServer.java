package ServerPac;
import java.io.*;  
import java.net.*;  
  
public class SocketServer{
	protected ServerSocket server = null;
	protected Socket inComingSocket = null;
	protected File targetFile = null;
	protected FileReader fileReader = null;
	protected BufferedReader fileBufferedReader = null;
	protected BufferedReader inputFromSocket = null;
	protected PrintWriter outputToSocket = null;
	public void AcceptConnection(int ListenPort) {
		try {
			server = new ServerSocket(ListenPort);
			while(true) {
				inComingSocket = server.accept();
				handelConnection(inComingSocket);
					if(inComingSocket.isConnected()) {
						System.out.println("Client disconnected");
						inComingSocket.close();
						}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void handelConnection(Socket inComingSocket) {
		try {
			inputFromSocket = new BufferedReader(new InputStreamReader(inComingSocket.getInputStream()));
			outputToSocket = new PrintWriter(new OutputStreamWriter(inComingSocket.getOutputStream()));
			
//			targetFile = new File(inputFromSocket.readLine());
			fileReader = new FileReader(inputFromSocket.readLine());
			fileBufferedReader = new BufferedReader(fileReader);
			
//			StringBuffer stringBuffer = new StringBuffer();
			String contextString=null;
			
			while((contextString=fileBufferedReader.readLine())!=null) {
				outputToSocket.println(contextString);
				outputToSocket.flush();
			}
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
//	public void CloseConnection() {
//		try {
//			fileReader.close();
//			inComingSocket.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	public static void main (String [] args) {
		SocketServer server = new SocketServer();
		server.AcceptConnection(3000);
	}
}