package ServerPac;

import java.io.*;
import java.net.Socket;

public class Connectionhandler implements Runnable{
	protected Socket socketTohandler = null;
	Connectionhandler(Socket socketneedhandler){
		socketTohandler = socketneedhandler;
	}
	public void run() {
		
		try {
			BufferedReader inputFromSocket = new BufferedReader(new InputStreamReader(socketTohandler.getInputStream()));
			PrintWriter outputToSocket = new PrintWriter(new OutputStreamWriter(socketTohandler.getOutputStream()));
			
			FileReader fileReader = new FileReader(inputFromSocket.readLine());
			BufferedReader fileBufferedReader = new BufferedReader(fileReader);
			
			String contextString=null;
			while((contextString=fileBufferedReader.readLine())!=null) {
				outputToSocket.println(contextString);
				outputToSocket.flush();
				}
			
			fileBufferedReader.close();
			inputFromSocket.close();
			outputToSocket.close();
			}catch (FileNotFoundException e) {
					e.printStackTrace();
			}catch (IOException e) {
					e.printStackTrace();
			}
		}
}
